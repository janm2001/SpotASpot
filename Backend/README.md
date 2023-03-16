# How to Run?
Server is running on port `8080`  

1. Create docker postgreSQL container (only do this once)
```
1. Run terminal inside backend folder
2. type: "cd ./etc/docker"
3. type: "docker compose up"
```
2. Run server
```
java -jar ./build/libs/SpotASpot-0.0.1-SNAPSHOT.jar
```
replace 0.0.1 with version number
# API tutorial

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
