FROM openjdk:17

ENV PULSAR_ENDPOINT "PULSAR"
ENV KAFKA_ENDPOINT "KAFKA"

WORKDIR /bin

COPY apachecameltest/target/apachecameltest-1.0-SNAPSHOT-jar-with-dependencies.jar .

ENTRYPOINT java -jar apachecameltest-1.0-SNAPSHOT-jar-with-dependencies.jar ${PULSAR_ENDPOINT} ${KAFKA_ENDPOINT}