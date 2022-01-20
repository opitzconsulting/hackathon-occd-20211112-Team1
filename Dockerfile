FROM openjdk:17-slim

LABEL maintainer="Sascha Wiedenfeld"

VOLUME /tmp

WORKDIR /home/app

ENV JAVA_OPTS="-Duser.timezone=Europe/Berlin -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

COPY target/hackathon*.jar /app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
