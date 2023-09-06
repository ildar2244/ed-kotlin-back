plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":avito-api-v1-jackson"))
    implementation(project(":avito-common"))

    testImplementation(kotlin("test-junit"))
}