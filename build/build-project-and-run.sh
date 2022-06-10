#! /bin/bash

CWD=$(pwd)

cd ../apachecameltest/

mvn clean install compile assembly:single

cd target/

java -jar apachecameltest-1.0-SNAPSHOT-jar-with-dependencies.jar $1 $2

cd $CWD