import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.example.api.v1.models.*

suspend fun ApplicationCall.createOffer(processor: OfferProcessor) {
    val request = receive<CreateRequest>()
    val context = AvitoContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportCreate())
}

suspend fun ApplicationCall.readOffer(processor: OfferProcessor) {
    val request = receive<ReadRequest>()
    val context = AvitoContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportRead())
}

suspend fun ApplicationCall.updateOffer(processor: OfferProcessor) {
    val request = receive<UpdateRequest>()
    val context = AvitoContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportUpdate())
}

suspend fun ApplicationCall.deleteOffer(processor: OfferProcessor) {
    val request = receive<DeleteRequest>()
    val context = AvitoContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportDelete())
}

suspend fun ApplicationCall.searchOffer(processor: OfferProcessor) {
    val request = receive<SearchRequest>()
    val context = AvitoContext()
    context.fromTransport(request)
    processor.exec(context)
    respond(context.toTransportSearch())
}