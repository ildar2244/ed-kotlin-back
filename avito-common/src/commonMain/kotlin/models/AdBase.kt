package models

data class AdBase(
    var id: AdId = AdId.NONE,
    var ownerId: AdUserId = AdUserId.NONE,
    var title: String = "",
    var description: String = "",
    var dateCreate: String = "",
    var price: String = "",
    var phone: String = "",
    var telegramId: String = "",
    var vkId: String = "",
)