#! /bin/bash

CWD=$(pwd)

cd ..

JAR_FILE="apachecameltest/target/apachecameltest-1.0-SNAPSHOT.jar"

if [[ ! -f "$JAR_FILE" ]]; then
    ./build/build-project.sh
fi

echo

docker build -f ./docker/Dockerfile -t apachecameltest .

cd $CWD