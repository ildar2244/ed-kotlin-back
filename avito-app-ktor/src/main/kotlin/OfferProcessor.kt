import models.AvitoCommand
import models.AvitoWorkMode

class OfferProcessor {

    fun exec(ctx: AvitoContext) {
        require(ctx.workMode == AvitoWorkMode.STUB) {
            "Currently working only in STUB mode."
        }

        when (ctx.command) {
            AvitoCommand.SEARCH -> {
                ctx.offersListResponse.addAll(AvitoStub.prepareSearchList())
            }
            else -> {
                ctx.offerResponse = AvitoStub.get()
            }
        }
    }
}