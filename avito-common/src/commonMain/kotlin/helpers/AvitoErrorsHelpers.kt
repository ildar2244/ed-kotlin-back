package helpers

import AvitoContext
import models.AvitoError

fun Throwable.asAdsError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = AvitoError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun AvitoContext.addError(vararg error: AvitoError) = errors.addAll(error)