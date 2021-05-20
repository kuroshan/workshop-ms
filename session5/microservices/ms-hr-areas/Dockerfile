FROM openjdk:8
VOLUME "/tmp"
EXPOSE 8080
ADD ./target/ms-hr-areas-*.jar ms-hr-areas.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=GMT", "-jar", "ms-hr-areas.jar"]
