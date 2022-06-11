#! /bin/bash

CWD=$(pwd)

cd ..

JAR_FILE="apachecameltest/target/apachecameltest-1.0-SNAPSHOT.jar"

if [[ ! -f "$JAR_FILE" ]]; then
    echo
    echo "Please run build-project.sh to ensure the correct files exist for the Docker build process to succeed."
    echo
    exit
fi

echo

docker build -f ./docker/Dockerfile -t apachecameltest .

cd $CWD