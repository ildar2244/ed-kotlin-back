import models.*
import org.example.api.v1.models.*
import stubs.AdsStubs

fun AdsContext.fromTransport(request: IRequest) = when (request) {
    is AdCreateRequest -> fromTransport(request)
    is AdReadRequest -> fromTransport(request)
    is AdUpdateRequest -> fromTransport(request)
    is AdDeleteRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

fun AdsContext.fromTransport(request: AdCreateRequest) {
    command = AdsCommand.CREATE
    requestId = request.requestId()
    adRequest = request.ad?.toInternal() ?: AdBase()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AdsContext.fromTransport(request: AdReadRequest) {
    command = AdsCommand.READ
    requestId = request.requestId()
    adRequest = request.ad?.id.toWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AdsContext.fromTransport(request: AdUpdateRequest) {
    command = AdsCommand.UPDATE
    requestId = request.requestId()
    adRequest = request.ad?.toInternal() ?: AdBase()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AdsContext.fromTransport(request: AdDeleteRequest) {
    command = AdsCommand.DELETE
    requestId = request.requestId()
    adRequest = request.ad?.id.toWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun IRequest.requestId() = this.requestId?.let { AdsRequestId(it) } ?: AdsRequestId.NONE

private fun String?.toWithId() = AdBase(id = this.toAdId())

private fun String?.toAdId() = this?.let { AdId(it) } ?: AdId.NONE

private fun AdCreateObject.toInternal(): AdBase = AdBase(
    title = this.title ?: "",
    description = this.description ?: "",
    price = this.price ?: "",
    dateCreate = this.dateCreate ?: "",
    phone = this.phone ?: "",
    telegramId = this.telegramId ?: "",
    vkId = this.vkId ?: ""
)

private fun AdUpdateObject.toInternal(): AdBase = AdBase(
    id = this.id.toAdId(),
    title = this.title ?: "",
    description = this.description ?: "",
    dateCreate = this.dateCreate ?: "",
    price = this.price ?: "",
    phone = this.phone ?: "",
    telegramId = this.telegramId ?: "",
    vkId = this.vkId ?: ""
)

private fun AdDebug?.transportToWorkMode(): AdsWorkMode = when (this?.mode) {
    AdRequestDebugMode.PROD -> AdsWorkMode.PROD
    AdRequestDebugMode.TEST -> AdsWorkMode.TEST
    AdRequestDebugMode.STUB -> AdsWorkMode.STUB
    else -> AdsWorkMode.PROD
}

private fun AdDebug?.transportToStubCase(): AdsStubs = when (this?.stub) {
    AdRequestDebugStubs.SUCCESS -> AdsStubs.SUCCESS
    AdRequestDebugStubs.NOT_FOUND -> AdsStubs.NOT_FOUND
    AdRequestDebugStubs.BAD_ID -> AdsStubs.BAD_ID
    AdRequestDebugStubs.BAD_TITLE -> AdsStubs.BAD_TITLE
    AdRequestDebugStubs.BAD_DESCRIPTION -> AdsStubs.BAD_DESCRIPTION
    AdRequestDebugStubs.BAD_PHONE -> AdsStubs.BAD_PHONE
    AdRequestDebugStubs.BAD_TELEGRAM_ID -> AdsStubs.BAD_TELEGRAM_ID
    AdRequestDebugStubs.BAD_VK_ID -> AdsStubs.BAD_VK_ID
    AdRequestDebugStubs.CANNOT_DELETE -> AdsStubs.CANNOT_DELETE
    else -> AdsStubs.NONE
}
