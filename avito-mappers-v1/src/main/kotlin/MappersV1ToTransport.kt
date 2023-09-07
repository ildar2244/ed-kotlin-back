import models.*
import org.example.api.v1.models.*

fun AvitoContext.toTransport(): IResponse = when (command) {
    AvitoCommand.CREATE -> toTransportCreate()
    AvitoCommand.READ -> toTransportRead()
    AvitoCommand.UPDATE -> toTransportUpdate()
    AvitoCommand.DELETE -> toTransportDelete()
    AvitoCommand.SEARCH -> toTransportSearch()
    AvitoCommand.NONE -> throw UnknownAvitoCommand(command)
}

fun AvitoContext.toTransportCreate() = CreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AvitoState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    offer = offerResponse.toTransport(),
)

fun AvitoContext.toTransportRead() = ReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AvitoState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    offer = offerResponse.toTransport(),
)

fun AvitoContext.toTransportUpdate() = UpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AvitoState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    offer = offerResponse.toTransport(),
)

fun AvitoContext.toTransportDelete() = DeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AvitoState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    offer = offerResponse.toTransport(),
)

fun AvitoContext.toTransportSearch() = SearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AvitoState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    offers = offersListResponse.toTransportOffers()
)

private fun List<AvitoError>.toTransport(): List<Error>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun AvitoError.toTransport() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun Offer.toTransport() = ResponseObject(
    title = title.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    price = price.takeIf { it.isNotBlank() },
    dateCreate = dateCreate.takeIf { it.isNotBlank() },
    phone = phone.takeIf { it.isNotBlank() },
    telegramId = telegramId.takeIf { it.isNotBlank() },
    vkId = vkId.takeIf { it.isNotBlank() },
    offerId = id.takeIf { it != OfferId.NONE }?.asString(),
    ownerId = ownerId.takeIf { it != AvitoUserId.NONE }?.asString(),
)

private fun List<Offer>.toTransportOffers(): List<ResponseObject>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

