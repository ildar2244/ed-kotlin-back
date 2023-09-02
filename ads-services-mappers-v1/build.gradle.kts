plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":ads-services-api-v1-jackson"))
    implementation(project(":ads-services-common"))

    testImplementation(kotlin("test-junit"))
}