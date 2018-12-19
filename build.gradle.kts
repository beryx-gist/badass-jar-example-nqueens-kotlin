import org.jetbrains.kotlin.daemon.common.DaemonOptions
import org.jetbrains.kotlin.daemon.common.configureDaemonOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    id("org.beryx.jar") version "1.1.0"
}

repositories {
    jcenter()
}

java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
group = "org.beryx.jar.example"

val version: String by project

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
    onlyIf { java.sourceCompatibility <= JavaVersion.VERSION_1_8 }
}

dependencies {
    compile(kotlin("reflect"))
    compile(kotlin("stdlib"))
    compile("org.beryx:streamplify:1.1.1")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to version
        ))
    }
}
