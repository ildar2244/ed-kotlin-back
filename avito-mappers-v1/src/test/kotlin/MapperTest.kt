import models.*
import org.example.api.v1.models.*
import org.junit.Test
import stubs.AvitoStubs
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun fromTransport() {
        val req = AdCreateRequest(
            requestId = "22",
            debug = AdDebug(
                mode = AdRequestDebugMode.STUB,
                stub = AdRequestDebugStubs.SUCCESS,
            ),
            ad = AdCreateObject(
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
        assertEquals("example title", context.adRequest.title)
        assertEquals("1199", context.adRequest.price)
        assertEquals("31.08.2023", context.adRequest.dateCreate)
        assertEquals("best_seller", context.adRequest.telegramId)
    }

    @Test
    fun toTransport() {
        val context = AvitoContext(
            requestId = AvitoRequestId("22"),
            command = AvitoCommand.CREATE,
            adResponse = Offer(
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

        val req = context.toTransport() as AdCreateResponse

        assertEquals("22", req.requestId)
        assertEquals("example title", req.ad?.title)
        assertEquals("some text", req.ad?.description)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}