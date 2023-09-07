import org.example.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {

    private val request = CreateRequest(
        requestId = "888",
        debug = AvitoDebug(
            mode = RequestDebugMode.STUB,
            stub = RequestDebugStubs.BAD_TITLE
        ),
        offer = CreateObject(
            title = "example title",
            description = "som text",
            price = "990",
            dateCreate = "29.08.2023",
            telegramId = "best_bayer"
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"title\":\\s*\"example title\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as CreateRequest

        assertEquals(request, obj)
    }
}