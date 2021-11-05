# Step : Test and package
FROM 149297508447.dkr.ecr.eu-central-1.amazonaws.com/maven:3.8.2-openjdk-16 as builder
WORKDIR /build
COPY pom.xml .

COPY src/ /build/src/
# COPY .git /build/.git/
RUN mvn -B -ntp -DskipTests package

# Step : Package image
FROM 149297508447.dkr.ecr.eu-central-1.amazonaws.com/openjdk:16-slim

LABEL maintainer="Michael Staehler"

VOLUME /tmp

WORKDIR /home/app

ENV JAVA_OPTS="-Duser.timezone=Europe/Berlin -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

COPY --from=builder /build/target/hackathon-occd-20211112-team1-0.1.jar /app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]