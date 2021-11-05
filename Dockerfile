FROM adoptopenjdk/openjdk16:jdk-16.0.2_7-alpine-slim@sha256:4a6bde43208d64604404f5069ba273757bbeac3c9b818f622886d02768c38077

WORKDIR /home/app

COPY target/hackathon-occd-20211112-team1-0.1.jar app.jar

EXPOSE 8080

CMD java ${JAVA_OPTS} -jar app.jar