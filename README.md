# springapp
Test project for Java 8 with Maven secured by OAuth2 with basic UI provided by JSP (front-end is not in the scope of this project).

### Technologies:
- Spring MVC 
- Spring Security (OAuth2 tokens)
- Spring Data
- Spring Boot
- Spring application events
- H2 (custom configured in-memory database)
- Hazelcast (to enable cache)
- JSP & Thymeleaf (basic UI)
- MapStruct
- Swagger
- and others..

### API blueprint
Exposed at /v2/api-docs

### Non-Secured endpoints (currently turned off, fix for auth server is WIP)
- /
- /customers
- /v2/api-docs

### Security
Authorization server has in-memory client with following credentials
- Id: "ClientId"
- secret: "secret
- auth grand type: "password"
- scope: read & write

Application starts up with default user in database
- username: david
- password: david

Authorization server grands tokens at /oauth/token

cUrl command
```
curl -X POST \
  '<host>/oauth/token?grant_type=password&username=david&password=david' \
  -H 'authorization: Basic Q2xpZW50SWQ6c2VjcmV0'
```
obtained token then send as param, i.e.:
```
<host>/customers?access_token=cfed3699-d095-4980-baa5-8e33bf7c201d
```
