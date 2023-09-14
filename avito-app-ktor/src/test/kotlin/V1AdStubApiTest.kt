import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import org.example.api.v1.models.*
import org.junit.Test
import kotlin.test.assertEquals

class V1AdStubApiTest {
    @Test
    fun create() = testApplication {
        val client = myClient()

        val response = client.post("v1/offer/create") {
            val requestObj = CreateRequest(
                requestId = "2323",
                offer = CreateObject(
                    title = "Ремонт",
                    description = "двери",
                    price = "4000",
                ),
                debug = AvitoDebug(
                    mode = RequestDebugMode.STUB,
                    stub = RequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<CreateResponse>()
        println(responseObj)
        assertEquals(200, response.status.value)
        assertEquals("42", responseObj.offer?.offerId)
    }

    @Test
    fun read() = testApplication {
        val client = myClient()

        val response = client.post("v1/offer/read") {
            val requestObj = ReadRequest(
                requestId = "2323",
                offer = ReadObject("42"),
                debug = AvitoDebug(
                    mode = RequestDebugMode.STUB,
                    stub = RequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<ReadResponse>()
        println(responseObj)
        assertEquals(200, response.status.value)
        assertEquals("42", responseObj.offer?.offerId)
    }

    @Test
    fun update() = testApplication {
        val client = myClient()

        val response = client.post("v1/offer/update") {
            val requestObj = UpdateRequest(
                requestId = "2323",
                offer = UpdateObject(
                    offerId = "42",
                    title = "Ремонт",
                    description = "двери",
                    price = "4000",
                ),
                debug = AvitoDebug(
                    mode = RequestDebugMode.STUB,
                    stub = RequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<UpdateResponse>()
        println(responseObj)
        assertEquals(200, response.status.value)
        assertEquals("42", responseObj.offer?.offerId)
    }

    @Test
    fun delete() = testApplication {
        val client = myClient()

        val response = client.post("v1/offer/delete") {
            val requestObj = DeleteRequest(
                requestId = "2323",
                offer = DeleteObject("42"),
                debug = AvitoDebug(
                    mode = RequestDebugMode.STUB,
                    stub = RequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<DeleteResponse>()
        println(responseObj)
        assertEquals(200, response.status.value)
        assertEquals("42", responseObj.offer?.offerId)
    }

    @Test
    fun search() = testApplication {
        val client = myClient()

        val response = client.post("v1/offer/search") {
            val requestObj = SearchRequest(
                requestId = "2323",
                filterOffers = SearchFilter(),
                debug = AvitoDebug(
                    mode = RequestDebugMode.STUB,
                    stub = RequestDebugStubs.SUCCESS
                )
            )
            contentType(ContentType.Application.Json)
            setBody(requestObj)
        }
        val responseObj = response.body<SearchResponse>()
        println(responseObj)
        assertEquals(200, response.status.value)
        assertEquals("55", responseObj.offers?.first()?.offerId)
    }

    private fun ApplicationTestBuilder.myClient() = createClient {
        install(ContentNegotiation) {
            jackson {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                enable(SerializationFeature.INDENT_OUTPUT)
                writerWithDefaultPrettyPrinter()
            }
        }
    }
}