
rootProject.name = "ed-kotlin-back"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
    }
}

include("m1l1")
include("ads-services-api-v1-jackson")
include("ads-services-common")
include("ads-services-mappers-v1")
