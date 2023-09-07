package helpers

import AvitoContext
import models.AvitoCommand

fun AvitoContext.isUpdatableCommand() =
    this.command in listOf(AvitoCommand.CREATE, AvitoCommand.UPDATE, AvitoCommand.DELETE)