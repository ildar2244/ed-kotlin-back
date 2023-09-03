import models.*
import org.example.api.v1.models.*
import org.junit.Test
import stubs.AdsStubs
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

        val context = AdsContext()
        context.fromTransport(req)

        assertEquals(AdsStubs.SUCCESS, context.stubCase)
        assertEquals(AdsWorkMode.STUB, context.workMode)
        assertEquals("example title", context.adRequest.title)
        assertEquals("1199", context.adRequest.price)
        assertEquals("31.08.2023", context.adRequest.dateCreate)
        assertEquals("best_seller", context.adRequest.telegramId)
    }

    @Test
    fun toTransport() {
        val context = AdsContext(
            requestId = AdsRequestId("22"),
            command = AdsCommand.CREATE,
            adResponse = AdBase(
                title = "example title",
                description = "some text",
                price = "1199",
                dateCreate = "31.08.2023",
                telegramId = "best_seller",
            ),
            errors = mutableListOf(
                AdsError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = AdsState.RUNNING,
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