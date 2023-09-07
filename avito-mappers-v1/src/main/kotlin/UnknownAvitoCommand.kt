import models.AvitoCommand

class UnknownAvitoCommand(command: AvitoCommand): Throwable("Wrong command $command at mapping toTransport stage")