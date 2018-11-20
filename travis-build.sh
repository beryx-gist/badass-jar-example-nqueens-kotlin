#!/bin/bash
set -ev
./gradlew -PjavaCompatibility=11 --no-daemon -i -s build
./gradlew --no-daemon -i -s build
