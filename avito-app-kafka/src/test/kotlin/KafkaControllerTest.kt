import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import org.example.api.v1.models.*
import org.junit.Test
import java.util.Collections
import kotlin.test.assertEquals

class KafkaControllerTest {

    companion object {
        const val PARTITION = 0
    }

    @Test
    fun runKafka() {
        val consumer = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        val producer = MockProducer(true, StringSerializer(), StringSerializer())
        val config = AppKafkaConfig()
        val inputTopic = config.kafkaTopicInV1
        val outputTopic = config.kafkaTopicOutV1

        val app = AppKafkaConsumer(config, listOf(ConsumerStrategyV1()), consumer = consumer, producer = producer)
        consumer.schedulePollTask {
            consumer.rebalance(Collections.singletonList(TopicPartition(inputTopic, 0)))
            consumer.addRecord(
                ConsumerRecord(
                    inputTopic,
                    PARTITION,
                    0L,
                    "test-1",
                    apiV1RequestSerialize(CreateRequest(
                        requestId = "1111-2222-3333-4444",
                        offer = CreateObject(
                            title = "title offer example",
                            description = "about offer info",
                            price = "1500",
                            phone = "89997774433"
                        ),
                        debug = AvitoDebug(
                            mode = RequestDebugMode.STUB,
                            stub = RequestDebugStubs.SUCCESS
                        )
                    ))
                )
            )
            app.stop()
        }

        val startOffsets: MutableMap<TopicPartition, Long> = mutableMapOf()
        val tp = TopicPartition(inputTopic, PARTITION)
        startOffsets[tp] = 0L
        consumer.updateBeginningOffsets(startOffsets)

        app.run()

        val message = producer.history().first()
        val result = apiV1ResponseDeserialize<CreateResponse>(message.value())
        assertEquals(outputTopic, message.topic())
        assertEquals("1111-2222-3333-4444", result.requestId)
        assertEquals(true, result.offer?.title?.contains("Ремонт") ?: "")
    }
}