docker pull wnameless/oracle-xe-11g-r2
docker run --name ms-oracle -d -p 1521:1521 wnameless/oracle-xe-11g-r2
docker exec -it ms-oracle /bin/bash
sqlplus sys/oracle@//localhost:1521/XE as sysdba

docker cp ./workshop_ms.sql ms-oracle:/home/
docker cp ./hr_main.sql ms-oracle:/home/
docker cp ./hr_cre.sql ms-oracle:/home/
docker cp ./hr_popul.sql ms-oracle:/home/
docker cp ./hr_idx.sql ms-oracle:/home/
docker cp ./hr_comnt.sql ms-oracle:/home/
docker cp ./hr_analz.sql ms-oracle:/home/
docker cp ./mkverify.sql ms-oracle:/home/

docker exec -it ms-oracle /bin/bash

sqlplus sys/oracle@//localhost:1521/XE as sysdba

# @/home/mksample <SYSTEM_password> <SYS_password> <HR_password> EXAMPLE TEMP $ORACLE_HOME/demo/schema/log/ localhost:1521/pdb
@/home/workshop_ms.sql oracle oracle hrpw SYSTEM TEMP /home/log/ localhost:1521/XE


docker pull mysql:8.0.24 
docker run -d -p 3306:3306 --name ms-mysql -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=securitydb -e MYSQL_USER=admin -e MYSQL_PASSWORD=mysql mysql:8.0.24

docker pull redis:6.2.3
docker run -d -p 6379:6379 --name ms-redis redis:6.2.3
docker exec -it ms-redis /bin/bash
redis-cli FLUSHDB
