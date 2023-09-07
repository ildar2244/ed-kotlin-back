package models

data class OfferFilter(
    var searchString: String = "",
    var ownerId: AvitoUserId = AvitoUserId.NONE,
)
