# OAuth 2 Resource Server Example

Example for setting up a OAuth 2 Resource Server on a web service with Spring Boot. Using Keycloak as the OAuth server.

## Usage

The project requires an authorization server. The Docker compose file will take care of this, while running the project:

```
docker-compose -f docker/docker-compose.yml --project-name oauth2-resource-server-example up
```

And the web service be available at [http://localhost:8080/](http://localhost:8080/).

## Requests with Postman

To make things easier import `src/test/resources/Oauth.postman_collection.json` into Postman. It includes all the queries needed to test the project.

## Users

| User      | Password | Permissions |
|-----------|----------|-------------|
| test-user | 1234     | all         |

## Keycloak

To access the Keycloak admin app go to [http://localhost:8090/](http://localhost:8090/).

| User  | Password | Permissions |
|-------|----------|-------------|
| admin | admin    | all         |
