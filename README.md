# Welcome to Api Rest Ecommerce PVP service


```
    <java.version>11</java.version>
    <log4j2.version>2.17.1</log4j2.version>
    <rest-assured.version>4.3.3</rest-assured.version>
    <swagger-version>2.8.0</swagger-version>
```


## Branches available

> **___Utils___**
> 
> Makefile
> 
> Dockerfile

```master (default)```


All branches are public.

## Document API Swagger

```
http://localhost:8080/swagger-ui.html
```

## Install project 

```
./mvnw clean install 
```



## default docker local

Please check [Makefile] if something is missing or wrong, feel free to fix it (and contact your server cristianferrarys@gmail.com
```
make run-docker
```
delete images dangling
```
make docker-rmi
```
## Docker Hub 

https://hub.docker.com/repository/docker/cristianferrarys/ecommerce

```
docker pull cristianferrarys/ecommerce:1.0
`