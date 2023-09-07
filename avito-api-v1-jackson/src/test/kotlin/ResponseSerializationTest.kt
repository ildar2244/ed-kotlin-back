import org.example.api.v1.models.CreateResponse
import org.example.api.v1.models.ResponseObject
import org.example.api.v1.models.IResponse
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {

    private val response = CreateResponse(
        requestId = "4444",
        offer = ResponseObject(
            title = "example title",
            description = "some text",
            price = "1500",
            dateCreate = "30.08.2023",
            phone = "79008007722"
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"title\":\\s*\"example title\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as CreateResponse

        assertEquals(response, obj)
    }
}