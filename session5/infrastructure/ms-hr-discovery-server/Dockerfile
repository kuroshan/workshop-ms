FROM openjdk:8
VOLUME "/tmp"
EXPOSE 8888
ADD ./target/ms-hr-discovery-server-*.jar ms-hr-discovery-server.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ms-hr-discovery-server.jar"]
