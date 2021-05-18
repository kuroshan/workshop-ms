************************
*      DOCKERFILE      *
************************

docker network create --driver bridge workshopms 

mvn clean package -DskipTests
docker build -t ms-hr-config-server:v1 .
docker run -d --name ms-hr-config-server-v1 --network workshopms -p 8888:8888 ms-hr-config-server:v1
docker logs -f ms-hr-config-server-v1

