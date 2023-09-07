package models

data class Offer(
    var id: OfferId = OfferId.NONE,
    var ownerId: AvitoUserId = AvitoUserId.NONE,
    var title: String = "",
    var description: String = "",
    var dateCreate: String = "",
    var price: String = "",
    var phone: String = "",
    var telegramId: String = "",
    var vkId: String = "",
)