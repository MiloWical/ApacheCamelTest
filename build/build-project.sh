#! /bin/bash

CWD=$(pwd)

cd ../apachecameltest/

mvn clean install compile assembly:single

cd $CWD