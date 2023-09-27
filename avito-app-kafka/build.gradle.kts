plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("org.example.avito.app.kafka.MainKt")
}

dependencies {
    val kafkaVersion: String by project
    val coroutinesVersion: String by project
    val atomicfuVersion: String by project
    val logbackVersion: String by project
    val kotlinLoggingJvmVersion: String by project
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:atomicfu:$atomicfuVersion")

    // log
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingJvmVersion")

    // transport models
    implementation(project(":avito-common"))
    implementation(project(":avito-api-v1-jackson"))
    implementation(project(":avito-mappers-v1"))
    // logic
    implementation(project(":avito-biz"))

    testImplementation(kotlin("test-junit"))
}
