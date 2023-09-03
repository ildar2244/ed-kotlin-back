package helpers

import AdsContext
import models.AdsError

fun Throwable.asAdsError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = AdsError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun AdsContext.addError(vararg error: AdsError) = errors.addAll(error)