import kotlinx.datetime.Instant
import models.*
import stubs.AdsStubs

data class AdsContext(
    var command: AdsCommand = AdsCommand.NONE,
    var state: AdsState = AdsState.NONE,
    val errors: MutableList<AdsError> = mutableListOf(),

    var workMode: AdsWorkMode = AdsWorkMode.PROD,
    var stubCase: AdsStubs = AdsStubs.NONE,

    var requestId: AdsRequestId = AdsRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var adRequest: AdBase = AdBase(),
    var adResponse: AdBase = AdBase(),
    var adsListResponse: MutableList<AdBase> = mutableListOf(),
)
