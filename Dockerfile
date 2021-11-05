# Step : Test and package
FROM maven:3.8.2-openjdk-16 as builder
WORKDIR /build
COPY pom.xml .

COPY src/ /build/src/
COPY .git /build/.git/
RUN mvn -B -DskipTests package

# Step : Package image
FROM openjdk:16-slim

LABEL maintainer="Michael Staehler"

VOLUME /tmp

WORKDIR /home/app

ENV JAVA_OPTS="-Duser.timezone=Europe/Berlin -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

COPY --from=builder /build/target/hackathon-occd-20211112-team1-0.1.jar /app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]