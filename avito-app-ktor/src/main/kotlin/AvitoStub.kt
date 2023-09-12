import AvitoStubRemont.OFFER_REMONT
import models.AvitoUserId
import models.Offer
import models.OfferId

object AvitoStub {
    fun get(): Offer = OFFER_REMONT.copy()

    private var startId: Int = 55
    fun prepareSearchList() = listOf(
        offerRemont("${startId++}"),
        offerRemont("${startId++}"),
        offerRemont("${startId++}"),
        offerRemont("${startId++}"),
        offerRemont("${startId++}"),
        offerRemont("${startId++}"),
    )

    private fun avitoOffer(offer: Offer, id: String, title: String, detail: String, price: Int) = offer.copy(
        id = OfferId(id),
        title = title,
        description = detail,
        price = "$price ₽"
    )

    private fun offerRemont(id: String) = OFFER_REMONT.copy(id = OfferId(id))
}

object AvitoStubRemont {
    private val price = (250..3400).random()
    private val subTitle = listOf(
        "холодильника",
        "телефона",
        "компьютера",
        "телевизора",
        "квартиры",
        "офиса",
        "ванны",
        "двери",
        "электрики",
        "сантехники",
    ).random()

    val OFFER_REMONT: Offer
        get() = Offer(
            id = OfferId("42"),
            ownerId = AvitoUserId("user-5"),
            title = "Ремонт $subTitle",
            description = "Ремонт $subTitle за 1 день",
            dateCreate = "14-08-2023",
            price = "$price ₽",
            phone = "9120337725",
            telegramId = "",
            vkId = "",
        )
}