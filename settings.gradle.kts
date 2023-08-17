rootProject.name = "Technical Challenge"
rootProject.buildFileName = "build.gradle.kts"

include(":app")

val javaCompilerVersions = setOf(JavaVersion.VERSION_17)
val currentJavaVersion = JavaVersion.current()
if (currentJavaVersion !in javaCompilerVersions) {
    throw GradleException("""
        This build must be run with one of these JDKs: $javaCompilerVersions.
        Please see upgrade instructions at "Environment Setup Tips" section of README.md
    """.trimIndent())
}
