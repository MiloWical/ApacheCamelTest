#! /bin/bash

CWD=$(pwd)

cd ..

JAR_FILE="apachecameltest/target/apachecameltest-1.0-SNAPSHOT.jar"

if [[ ! -f "$JAR_FILE" ]]; then

    echo
    echo "apachecameltest-1.0-SNAPSHOT.jar does not exist. Building project..."
    echo

    cd build/

    ./build-project.sh

    cd ..
fi

echo

docker build -f ./docker/Dockerfile -t apachecameltest .

cd $CWD