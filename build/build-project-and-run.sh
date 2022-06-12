#! /bin/bash

CWD=$(pwd)

./build-project.sh

cd target/

java -jar apachecameltest-1.0-SNAPSHOT-jar-with-dependencies.jar $1 $2

cd $CWD