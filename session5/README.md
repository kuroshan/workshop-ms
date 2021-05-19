************************
*      DOCKERFILE      *
************************

docker network create --driver bridge workshopms 

mvn clean package -DskipTests -f ./infrastructure/ms-hr-config-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-discovery-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-zuul-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-security-server

docker build -t ms-hr-config-server:v1 ./infrastructure/ms-hr-config-server/.
docker build -t ms-hr-discovery-server:v1 ./infrastructure/ms-hr-discovery-server/.
docker build -t ms-hr-zuul-server:v1 ./infrastructure/ms-hr-zuul-server/.
docker build -t ms-hr-security-server:v1 ./infrastructure/ms-hr-security-server/.

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



docker logs -f <container_id>
