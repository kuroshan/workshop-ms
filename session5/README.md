************************
*      DOCKERFILE      *
************************

mvn clean package -DskipTests -f ./infrastructure/ms-hr-config-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-discovery-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-security-server
mvn clean package -DskipTests -f ./infrastructure/ms-hr-zuul-server
mvn clean package -DskipTests -f ./microservices/ms-hr-users
mvn clean package -DskipTests -f ./microservices/ms-hr-areas
mvn clean package -DskipTests -f ./microservices/ms-hr-employees

docker build -t database-mysql:v1 ./database/mysql/.
docker build -t database-redis:v1 ./database/redis/.
docker build -t database-oracle:v1 ./database/oracle/.

docker build -t ms-hr-config-server:v1 ./infrastructure/ms-hr-config-server/.
docker build -t ms-hr-discovery-server:v1 ./infrastructure/ms-hr-discovery-server/.
docker build -t ms-hr-security-server:v1 ./infrastructure/ms-hr-security-server/.
docker build -t ms-hr-zuul-server:v1 ./infrastructure/ms-hr-zuul-server/.
docker build -t ms-hr-users:v1 ./microservices/ms-hr-users/.
docker build -t ms-hr-areas:v1 ./microservices/ms-hr-areas/.
docker build -t ms-hr-employees:v1 ./microservices/ms-hr-employees/.

************************
*        DOCKER        *
************************

docker network create --driver bridge workshopms

docker run -d --name database-mysql-dev --network workshopms -p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=secret \
-e MYSQL_DATABASE=securitydb \
-e MYSQL_USER=admin \
-e MYSQL_PASSWORD=mysql \
database-mysql:v1

docker run -d --name database-redis-dev --network workshopms -p 6379:6379  database-redis:v1

docker run -d --name database-oracle-dev --network workshopms -p 1521:1521 database-oracle:v1

docker run -d --name ms-hr-config-server-v1 --network workshopms -p 8888:8080 \
-e SPRING_CONFIG_SERVER_GIT_URI=https://github.com/kuroshan/workshop-ms \
-e SPRING_CONFIG_SERVER_GIT_BRANCH=16-container \
ms-hr-config-server:v1

docker run -d --name ms-hr-discovery-server-v1 --network workshopms -p 8761:8080 \
-e SPRING_PROFILES_ACTIVE=dev  \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
ms-hr-discovery-server:v1

docker run -d --name ms-hr-security-server-v1 --network workshopms -p 9090:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/ \
-e SPRING_REDIS_HOST=database-redis-dev \
ms-hr-security-server:v1

docker run -d --name ms-hr-zuul-server-v1 --network workshopms -p 8080:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/ \
ms-hr-zuul-server:v1

docker run -d --name ms-hr-users-v1 --network workshopms -p 8081:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/ \
-e SPRING_DATASOURCE_URL='jdbc:mysql://database-mysql-dev:3306/securitydb?useSSL=true&serverTimezone=UTC' \
-e SPRING_DATASOURCE_USERNAME=admin \
-e SPRING_DATASOURCE_PASSWORD=mysql \
ms-hr-users:v1

docker run -d --name ms-hr-areas-v1 --network workshopms -p 8082:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/ \
-e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//database-oracle-dev:1521/XE \
-e SPRING_DATASOURCE_USERNAME=hr \
-e SPRING_DATASOURCE_PASSWORD=hrpw \
ms-hr-areas:v1

docker run -d --name ms-hr-employees-v1 --network workshopms -p 8083:8080 \
-e SPRING_PROFILES_ACTIVE=dev \
-e SPRING_CONFIG_SERVER_URI=http://ms-hr-config-server-v1:8080/ \
-e SPRING_DISCOVERY_SERVER_URI=http://ms-hr-discovery-server-v1:8080/eureka/ \
-e SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//database-oracle-dev:1521/XE \
-e SPRING_DATASOURCE_USERNAME=hr \
-e SPRING_DATASOURCE_PASSWORD=hrpw \
ms-hr-employees:v1

docker logs -f <container_id>

docker stop ms-hr-config-server-v1 ms-hr-discovery-server-v1 ms-hr-zuul-server-v1 ms-hr-security-server-v1 \
ms-hr-users-v1 ms-hr-areas-v1 ms-hr-employees-v1 \
database-mysql-dev database-redis-dev database-oracle-dev

docker rm ms-hr-config-server-v1 ms-hr-discovery-server-v1 ms-hr-zuul-server-v1 ms-hr-security-server-v1 \
ms-hr-users-v1 ms-hr-areas-v1 ms-hr-employees-v1 \
database-mysql-dev database-redis-dev database-oracle-dev

docker rmi ms-hr-config-server:v1 ms-hr-discovery-server:v1 ms-hr-zuul-server:v1 ms-hr-security-server:v1 \
ms-hr-users:v1 ms-hr-areas:v1 ms-hr-employees:v1 \
database-mysql:v1 database-redis:v1 database-oracle:v1

docker network rm workshopms

************************
*    DOCKER-COMPOSE    *
************************

docker-compose --version

# docker-compose up -d

docker-compose -f ./database/docker-compose.yml up -d
docker-compose -f ./infrastructure/docker-compose.yml up -d
docker-compose -f ./microservices/docker-compose.yml up -d

docker-compose -f ./microservices/docker-compose.yml down
docker-compose -f ./infrastructure/docker-compose.yml down
docker-compose -f ./database/docker-compose.yml down

docker-compose -f ./microservices/docker-compose.yml up -d --scale ms-hr-areas=2
