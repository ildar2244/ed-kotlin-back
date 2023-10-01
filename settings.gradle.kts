
rootProject.name = "ed-kotlin-back"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val ktorVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false

        id("io.ktor.plugin") version ktorVersion apply false
    }
}

include("m1l1")
include("avito-api-v1-jackson")
include("avito-common")
include("avito-mappers-v1")
include("avito-stubs")
include("avito-biz")
include("avito-app-ktor")
include("avito-app-kafka")
