plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "kanjava202505"
include("app")

project(":app").name = "kanjava202505"