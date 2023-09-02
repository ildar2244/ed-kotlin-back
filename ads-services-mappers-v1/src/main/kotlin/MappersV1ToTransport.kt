import models.*
import org.example.api.v1.models.*

fun AdsContext.toTransport(): IResponse = when (command) {
    AdsCommand.CREATE -> toTransportCreate()
    AdsCommand.READ -> toTransportRead()
    AdsCommand.UPDATE -> toTransportUpdate()
    AdsCommand.DELETE -> toTransportDelete()
    AdsCommand.NONE -> throw UnknownAdsCommand(command)
}

fun AdsContext.toTransportCreate() = AdCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AdsState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    ad = adResponse.toTransport(),
)

fun AdsContext.toTransportRead() = AdReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AdsState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    ad = adResponse.toTransport(),
)

fun AdsContext.toTransportUpdate() = AdUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AdsState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    ad = adResponse.toTransport(),
)

fun AdsContext.toTransportDelete() = AdDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == AdsState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransport(),
    ad = adResponse.toTransport(),
)

private fun List<AdsError>.toTransport(): List<Error>? = this
    .map { it.toTransport() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun AdsError.toTransport() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun AdBase.toTransport() = AdResponseObject(
    title = title.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    price = price.takeIf { it.isNotBlank() },
    dateCreate = dateCreate.takeIf { it.isNotBlank() },
    phone = phone.takeIf { it.isNotBlank() },
    telegramId = telegramId.takeIf { it.isNotBlank() },
    vkId = vkId.takeIf { it.isNotBlank() },
    id = id.takeIf { it != AdId.NONE }?.asString(),
    ownerId = ownerId.takeIf { it != AdUserId.NONE }?.asString(),
)

