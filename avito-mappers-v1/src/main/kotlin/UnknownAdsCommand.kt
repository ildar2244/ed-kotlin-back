import models.AdsCommand

class UnknownAdsCommand(command: AdsCommand): Throwable("Wrong command $command at mapping toTransport stage")