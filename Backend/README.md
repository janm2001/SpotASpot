# How to Run?
Server is running on port `8080`  

1. Create docker postgreSQL container (only do this once)
```
1. Run terminal inside this folder
2. type: "cd ./etc/docker"
3. type: "docker compose up"
```
2. Run server
```
1. Run terminal inside this folder
2. type: "./gradlew bootRun"
```

# API docs

## AUTHENTICATION:

> POST: `http://localhost:8080/api/v1/auth/register`  

Send user data and return JWT auth token
```
REQ-JSON:
{
    String firstName,
    String lastName,
    String email,
    String {USER, ORGANIZER} role,
    String username,
    String password
}
```
```
RES-JSON:
{
    String token
}
```
Example:
```
REQ:
{
    "firstName": "Ivan",
    "lastName": "Horvat",
    "email": "example@gmail.com",
    "role": "USER",
    "username": "ihorvat12",
    "password": "Jedan2"
}
RES:
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpaG9ydmF0MTIiLCJpYXQiOjE2Nzg5OTYwNzIsImV4cCI6MTY3ODk5NzUxMn0.5cr9fcbXGmMrNpLoRFriTd9yh5jHSxTZFRMben3zJBI"
}
```

## LOGIN:
> POST: `http://localhost:8080/api/v1/auth/authenticate`

Send user data and return JWT auth token
```
REQ-JSON:
{
    String username,
    String password
}
```
```
RES-JSON:
{
    String token
}
```
Example:
```
REQ:
{
    "username": "ihorvat12",
    "password": "Jedan2"
}
RES:
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpaG9ydmF0MTIiLCJpYXQiOjE2Nzg5OTYwNzIsImV4cCI6MTY3ODk5NzUxMn0.5cr9fcbXGmMrNpLoRFriTd9yh5jHSxTZFRMben3zJBI"
}
```
