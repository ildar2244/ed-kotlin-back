package helpers

import AdsContext
import models.AdsCommand

fun AdsContext.isUpdatableCommand() =
    this.command in listOf(AdsCommand.CREATE, AdsCommand.UPDATE, AdsCommand.DELETE)