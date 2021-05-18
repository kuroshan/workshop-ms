FROM openjdk:8
VOLUME "/tmp"
EXPOSE 8080
ADD ./target/ms-hr-config-server-*.jar ms-hr-config-server.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ms-hr-config-server.jar"]
