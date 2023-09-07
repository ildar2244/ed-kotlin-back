import models.*
import org.example.api.v1.models.*
import org.junit.Test
import stubs.AvitoStubs
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun fromTransport() {
        val req = CreateRequest(
            requestId = "22",
            debug = AvitoDebug(
                mode = RequestDebugMode.STUB,
                stub = RequestDebugStubs.SUCCESS,
            ),
            offer = CreateObject(
                title = "example title",
                description = "some text",
                price = "1199",
                dateCreate = "31.08.2023",
                telegramId = "best_seller",
            ),
        )

        val context = AvitoContext()
        context.fromTransport(req)

        assertEquals(AvitoStubs.SUCCESS, context.stubCase)
        assertEquals(AvitoWorkMode.STUB, context.workMode)
        assertEquals("example title", context.offerRequest.title)
        assertEquals("1199", context.offerRequest.price)
        assertEquals("31.08.2023", context.offerRequest.dateCreate)
        assertEquals("best_seller", context.offerRequest.telegramId)
    }

    @Test
    fun toTransport() {
        val context = AvitoContext(
            requestId = AvitoRequestId("22"),
            command = AvitoCommand.CREATE,
            offerResponse = Offer(
                title = "example title",
                description = "some text",
                price = "1199",
                dateCreate = "31.08.2023",
                telegramId = "best_seller",
            ),
            errors = mutableListOf(
                AvitoError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = AvitoState.RUNNING,
        )

        val req = context.toTransport() as CreateResponse

        assertEquals("22", req.requestId)
        assertEquals("example title", req.offer?.title)
        assertEquals("some text", req.offer?.description)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}