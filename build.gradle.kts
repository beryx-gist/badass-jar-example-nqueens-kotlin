import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.JavaVersion

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.beryx.jar") version "2.0.0-rc-4"
}

repositories {
    mavenCentral()
}

val jvmVersion = "${findProperty("javaCompatibility") ?: "11"}".toInt()

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "${JavaVersion.toVersion(jvmVersion)}"
}
group = "org.beryx.jar.example"

val version: String by project

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDirectory.set(compileKotlin.destinationDirectory)

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation("org.beryx:streamplify:1.1.1")
    testImplementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
        ))
    }
}

moduleConfig.version.set("$version")
moduleConfig.neverCompileModuleInfo.set(true)

compileJava.options.release.set (jvmVersion)
