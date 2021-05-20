FROM wnameless/oracle-xe-11g-r2:latest
VOLUME "/tmp"
EXPOSE 22
EXPOSE 1521
EXPOSE 8080
ENV ORACLE_HOME /u01/app/oracle/product/11.2.0/xe
COPY ./sql /home
ADD ./shell/script.sh /docker-entrypoint-initdb.d/