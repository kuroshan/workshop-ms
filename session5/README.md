************************
*      DOCKERFILE      *
************************

docker network create --driver bridge workshopms 

mvn clean package -DskipTests -f ./infrastructure/ms-hr-config-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-discovery-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-zuul-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-security-server
mvn clean package -DskipTests -f ./microservices/ms-hr-users
mvn clean package -DskipTests -f ./microservices/ms-hr-areas
mvn clean package -DskipTests -f ./microservices/ms-hr-employees

docker build -t ms-hr-config-server:v1 ./infrastructure/ms-hr-config-server/.
docker build -t ms-hr-discovery-server:v1 ./infrastructure/ms-hr-discovery-server/.
docker build -t ms-hr-zuul-server:v1 ./infrastructure/ms-hr-zuul-server/.
docker build -t ms-hr-security-server:v1 ./infrastructure/ms-hr-security-server/.
docker build -t ms-hr-users:v1 ./microservices/ms-hr-users/.
docker build -t ms-hr-areas:v1 ./microservices/ms-hr-areas/.
docker build -t ms-hr-employees:v1 ./microservices/ms-hr-employees/.

docker run -d --name ms-hr-config-server-v1 --network workshopms -p 8888:8080 ms-hr-config-server:v1

docker run -d --name ms-hr-discovery-server-v1 --network workshopms -p 8761:8080 \
-e SPRING_PROFILES_ACTIVE=dev  \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
ms-hr-discovery-server:v1

docker run -d --name ms-hr-zuul-server-v1 --network workshopms -p 8080:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/  \
ms-hr-zuul-server:v1

docker run -d --name ms-hr-security-server-v1 --network workshopms -p 9090:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/  \
ms-hr-security-server:v1

docker run -d --name ms-hr-users-v1 --network workshopms -p 8081:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/  \
-e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/securitydb?useSSL=true&serverTimezone=UTC \
-e SPRING_DATASOURCE_USERNAME=admin \
-e SPRING_DATASOURCE_PASSWORD=mysql \
ms-hr-users:v1

docker run -d --name ms-hr-areas-v1 --network workshopms -p 8082:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/  \
-e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//localhost:1521/XE \
-e SPRING_DATASOURCE_USERNAME=hr \
-e SPRING_DATASOURCE_PASSWORD=hrpw \
ms-hr-areas:v1

docker run -d --name ms-hr-employees-v1 --network workshopms -p 8081:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/  \
-e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//localhost:1521/XE \
-e SPRING_DATASOURCE_USERNAME=hr \
-e SPRING_DATASOURCE_PASSWORD=hrpw \
ms-hr-employees:v1

docker logs -f <container_id>

docker stop ms-hr-config-server-v1 ms-hr-discovery-server-v1 ms-hr-zuul-server-v1 ms-hr-security-server-v1 \
ms-hr-users-v1 ms-hr-areas-v1 ms-hr-employees-v1

docker rm ms-hr-config-server-v1 ms-hr-discovery-server-v1 ms-hr-zuul-server-v1 ms-hr-security-server-v1 \
ms-hr-users-v1 ms-hr-areas-v1 ms-hr-employees-v1

docker rmi ms-hr-config-server:v1 ms-hr-discovery-server:v1 ms-hr-zuul-server:v1 ms-hr-security-server:v1 \
ms-hr-users:v1 ms-hr-areas:v1 ms-hr-employees:v1

docker network rm workshopms
