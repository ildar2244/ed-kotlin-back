import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.v1Offer(processor: OfferProcessor) {
    route("offer") {
        post("create") {
            call.createOffer(processor)
        }
        post("read") {
            call.readOffer(processor)
        }
        post("update") {
            call.updateOffer(processor)
        }
        post("delete") {
            call.deleteOffer(processor)
        }
        post("search") {
            call.searchOffer(processor)
        }
    }
}