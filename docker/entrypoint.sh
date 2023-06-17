#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail

# This will build the plugin and copy it to the plugins directory, and then
# start the server. This is the default script that is run when the container
# is started.
cd /code
./gradlew build

mkdir /plugins
cp build/libs/*-all.jar /plugins/kastle.jar

# Start the server with the default script.
/start
