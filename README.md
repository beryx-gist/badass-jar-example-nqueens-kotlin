[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](https://github.com/beryx-gist/badass-jar-example-nqueens-kotlin/blob/master/LICENSE)
[![Build Status](https://img.shields.io/github/workflow/status/beryx-gist/badass-jar-example-nqueens-kotlin/build)](https://github.com/beryx-gist/badass-jar-example-nqueens-kotlin/actions?query=workflow%22build%22)

## Badass-Jar example: N-Queens in Kotlin ##

A simple project that shows how to use the [Badass Jar Plugin](https://github.com/beryx/badass-jar-plugin/).
It implements a Kotlin library for solving the [N-Queens problem](https://en.wikipedia.org/wiki/Eight_queens_puzzle).


The plugin is configured in `build.gradle.kts` as follows:

```
plugins {
    kotlin("jvm") version "1.5.31"
    id("org.beryx.jar") version "2.0.0-rc-4"
}
...
val jvmVersion = "${findProperty("javaCompatibility") ?: "11"}".toInt()
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "${JavaVersion.toVersion(jvmVersion)}"
}
...
compileJava.options.release.set (jvmVersion)
moduleConfig.version.set("$version")
moduleConfig.neverCompileModuleInfo.set(true)
...
```

### Usage
To build a modular jar that targets Java 11 execute:
```
./gradlew build
```

To build a modular jar that targets Java 8 execute:
```
./gradlew -PjavaCompatibility=8 build
```
