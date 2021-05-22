FROM openjdk:8
VOLUME "/tmp"
EXPOSE 8080
ADD ./target/ms-hr-security-server-*.jar ms-hr-security-server.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ms-hr-security-server.jar"]
