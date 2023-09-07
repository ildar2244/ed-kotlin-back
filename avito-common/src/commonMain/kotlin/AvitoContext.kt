import kotlinx.datetime.Instant
import models.*
import stubs.AvitoStubs

data class AvitoContext(
    var command: AvitoCommand = AvitoCommand.NONE,
    var state: AvitoState = AvitoState.NONE,
    val errors: MutableList<AvitoError> = mutableListOf(),

    var workMode: AvitoWorkMode = AvitoWorkMode.PROD,
    var stubCase: AvitoStubs = AvitoStubs.NONE,

    var requestId: AvitoRequestId = AvitoRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var adRequest: Offer = Offer(),
    var adResponse: Offer = Offer(),
    var adsListResponse: MutableList<Offer> = mutableListOf(),
)
