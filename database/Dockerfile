FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=Banty.1208
ENV MYSQL_DATABASE=spe
# copy custom SQL scripts to docker-entrypoint-initdb.d folder
COPY spe.sql /docker-entrypoint-initdb.d/

EXPOSE 3306
