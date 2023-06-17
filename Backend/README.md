# How to Run?
Server is running on port `8080`  

1. Create docker postgreSQL container (only do this once)
```
1. Run terminal inside this folder
2. type: "cd ./etc/docker"
3. type: "docker compose up"
```
2. Create S3Bucket with a name "spotaspot-images" and paste aws key in /home/.aws/credentials  


3. Run server
```
1. Run terminal inside this folder
2. type: "mvn spring-boot:run -Dspring-boot.run.profiles=dev"
```

# API docs

> In browser: `http://localhost:8080/swagger-ui/index.html`  
