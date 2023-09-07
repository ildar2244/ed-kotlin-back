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
    var offerRequest: Offer = Offer(),
    var filterRequest: OfferFilter = OfferFilter(),
    var offerResponse: Offer = Offer(),
    var offersListResponse: MutableList<Offer> = mutableListOf(),
)
