import models.*
import org.example.api.v1.models.*
import stubs.AvitoStubs

fun AvitoContext.fromTransport(request: IRequest) = when (request) {
    is CreateRequest -> fromTransport(request)
    is ReadRequest -> fromTransport(request)
    is UpdateRequest -> fromTransport(request)
    is DeleteRequest -> fromTransport(request)
    is SearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

fun AvitoContext.fromTransport(request: CreateRequest) {
    command = AvitoCommand.CREATE
    requestId = request.requestId()
    offerRequest = request.offer?.toInternal() ?: Offer()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: ReadRequest) {
    command = AvitoCommand.READ
    requestId = request.requestId()
    offerRequest = request.offer?.offerId.toWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: UpdateRequest) {
    command = AvitoCommand.UPDATE
    requestId = request.requestId()
    offerRequest = request.offer?.toInternal() ?: Offer()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: DeleteRequest) {
    command = AvitoCommand.DELETE
    requestId = request.requestId()
    offerRequest = request.offer?.offerId.toWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun AvitoContext.fromTransport(request: SearchRequest) {
    command = AvitoCommand.SEARCH
    requestId = request.requestId()
    filterRequest = request.filterOffers.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun IRequest.requestId() = this.requestId?.let { AvitoRequestId(it) } ?: AvitoRequestId.NONE

private fun String?.toWithId() = Offer(id = this.toOfferId())

private fun String?.toOfferId() = this?.let { OfferId(it) } ?: OfferId.NONE

private fun CreateObject.toInternal(): Offer = Offer(
    title = this.title ?: "",
    description = this.description ?: "",
    price = this.price ?: "",
    dateCreate = this.dateCreate ?: "",
    phone = this.phone ?: "",
    telegramId = this.telegramId ?: "",
    vkId = this.vkId ?: ""
)

private fun UpdateObject.toInternal(): Offer = Offer(
    id = this.offerId.toOfferId(),
    title = this.title ?: "",
    description = this.description ?: "",
    dateCreate = this.dateCreate ?: "",
    price = this.price ?: "",
    phone = this.phone ?: "",
    telegramId = this.telegramId ?: "",
    vkId = this.vkId ?: ""
)

private fun SearchFilter?.toInternal(): OfferFilter = OfferFilter(
    searchString = this?.searchString ?: ""
)

private fun AvitoDebug?.transportToWorkMode(): AvitoWorkMode = when (this?.mode) {
    RequestDebugMode.PROD -> AvitoWorkMode.PROD
    RequestDebugMode.TEST -> AvitoWorkMode.TEST
    RequestDebugMode.STUB -> AvitoWorkMode.STUB
    else -> AvitoWorkMode.PROD
}

private fun AvitoDebug?.transportToStubCase(): AvitoStubs = when (this?.stub) {
    RequestDebugStubs.SUCCESS -> AvitoStubs.SUCCESS
    RequestDebugStubs.NOT_FOUND -> AvitoStubs.NOT_FOUND
    RequestDebugStubs.BAD_ID -> AvitoStubs.BAD_ID
    RequestDebugStubs.BAD_TITLE -> AvitoStubs.BAD_TITLE
    RequestDebugStubs.BAD_DESCRIPTION -> AvitoStubs.BAD_DESCRIPTION
    RequestDebugStubs.BAD_PHONE -> AvitoStubs.BAD_PHONE
    RequestDebugStubs.BAD_TELEGRAM_ID -> AvitoStubs.BAD_TELEGRAM_ID
    RequestDebugStubs.BAD_VK_ID -> AvitoStubs.BAD_VK_ID
    RequestDebugStubs.CANNOT_DELETE -> AvitoStubs.CANNOT_DELETE
    else -> AvitoStubs.NONE
}
