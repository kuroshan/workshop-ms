mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9001
mvn spring-boot:run -Dspring-boot.run.profiles=dev
java -jar ./target/ms-rh-areas-1.0.0.jar --server.port=9001
java -Dserver.port=5566 -jar ./target/ms-rh-areas-1.0.0.jar
