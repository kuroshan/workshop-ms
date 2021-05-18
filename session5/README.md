************************
*      DOCKERFILE      *
************************

docker network create --driver bridge workshopms 

mvn clean package -DskipTests

docker build -t ms-hr-config-server:v1 .
docker run -d --name ms-hr-config-server-v1 --network workshopms -p 8888:8080 ms-hr-config-server:v1
docker logs -f ms-hr-config-server-v1

docker build -t ms-hr-discovery-server:v1 .
docker run -d --name ms-hr-discovery-server-v1 --network workshopms -p 8761:8080 \
-e SPRING_PROFILES_ACTIVE=dev  \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
ms-hr-discovery-server:v1
docker logs -f ms-hr-discovery-server-v1

docker build -t ms-hr-zuul-server:v1 .
docker run -d --name ms-hr-zuul-server-v1 --network workshopms -p 8080:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/  \
ms-hr-zuul-server:v1
docker logs -f ms-hr-zuul-server-v1
