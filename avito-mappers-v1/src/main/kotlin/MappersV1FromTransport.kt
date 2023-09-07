import models.*
import org.example.api.v1.models.*
import stubs.AvitoStubs

fun AvitoContext.fromTransport(request: IRequest) = when (request) {
    is AdCreateRequest -> fromTransport(request)
    is AdReadRequest -> fromTransport(request)
    is AdUpdateRequest -> fromTransport(request)
    is AdDeleteRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

fun AvitoContext.fromTransport(request: AdCreateRequest) {
    command = AvitoCommand.CREATE
    requestId = request.requestId()
    adRequest = request.ad?.toInternal() ?: Offer()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: AdReadRequest) {
    command = AvitoCommand.READ
    requestId = request.requestId()
    adRequest = request.ad?.id.toWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: AdUpdateRequest) {
    command = AvitoCommand.UPDATE
    requestId = request.requestId()
    adRequest = request.ad?.toInternal() ?: Offer()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: AdDeleteRequest) {
    command = AvitoCommand.DELETE
    requestId = request.requestId()
    adRequest = request.ad?.id.toWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun IRequest.requestId() = this.requestId?.let { AvitoRequestId(it) } ?: AvitoRequestId.NONE

private fun String?.toWithId() = Offer(id = this.toAdId())

private fun String?.toAdId() = this?.let { OfferId(it) } ?: OfferId.NONE

private fun AdCreateObject.toInternal(): Offer = Offer(
    title = this.title ?: "",
    description = this.description ?: "",
    price = this.price ?: "",
    dateCreate = this.dateCreate ?: "",
    phone = this.phone ?: "",
    telegramId = this.telegramId ?: "",
    vkId = this.vkId ?: ""
)

private fun AdUpdateObject.toInternal(): Offer = Offer(
    id = this.id.toAdId(),
    title = this.title ?: "",
    description = this.description ?: "",
    dateCreate = this.dateCreate ?: "",
    price = this.price ?: "",
    phone = this.phone ?: "",
    telegramId = this.telegramId ?: "",
    vkId = this.vkId ?: ""
)

private fun AdDebug?.transportToWorkMode(): AvitoWorkMode = when (this?.mode) {
    AdRequestDebugMode.PROD -> AvitoWorkMode.PROD
    AdRequestDebugMode.TEST -> AvitoWorkMode.TEST
    AdRequestDebugMode.STUB -> AvitoWorkMode.STUB
    else -> AvitoWorkMode.PROD
}

private fun AdDebug?.transportToStubCase(): AvitoStubs = when (this?.stub) {
    AdRequestDebugStubs.SUCCESS -> AvitoStubs.SUCCESS
    AdRequestDebugStubs.NOT_FOUND -> AvitoStubs.NOT_FOUND
    AdRequestDebugStubs.BAD_ID -> AvitoStubs.BAD_ID
    AdRequestDebugStubs.BAD_TITLE -> AvitoStubs.BAD_TITLE
    AdRequestDebugStubs.BAD_DESCRIPTION -> AvitoStubs.BAD_DESCRIPTION
    AdRequestDebugStubs.BAD_PHONE -> AvitoStubs.BAD_PHONE
    AdRequestDebugStubs.BAD_TELEGRAM_ID -> AvitoStubs.BAD_TELEGRAM_ID
    AdRequestDebugStubs.BAD_VK_ID -> AvitoStubs.BAD_VK_ID
    AdRequestDebugStubs.CANNOT_DELETE -> AvitoStubs.CANNOT_DELETE
    else -> AvitoStubs.NONE
}
