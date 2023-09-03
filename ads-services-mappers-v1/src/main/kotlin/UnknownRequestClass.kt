import org.example.api.v1.models.IRequest

class UnknownRequestClass(clazz: Class<IRequest>) : RuntimeException("Class $clazz cannot be mapped to AdsContext")
