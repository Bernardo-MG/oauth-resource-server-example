# OAuth 2 Resource Server Example

Example for setting up a OAuth 2 Resource Server on a web service with Spring Boot. Using Keycloak as the OAuth server.

## Usage

The project requires an authorization server. The Docker compose file will take care of this, while also running the project:

```
docker-compose -f docker/docker-compose.yml --project-name oauth2-resource-server-example up
```

Once started, the web service be available at [http://localhost:8080/](http://localhost:8080/).

## Requests with Postman

To make things easier import `src/test/resources/resource_server.postman_collection.json` into Postman. This file includes all the queries needed to test the project.

## Users

For testing, any of these will work.

| User      | Password | Permissions |
|-----------|----------|-------------|
| test-user | 1234     | all         |

Note that this web service requires secure tokens. The Postman requests includes an authenticated one, which takes care of this.

## Keycloak

To access the Keycloak admin app go to [http://localhost:8090/](http://localhost:8090/). A default admin user is ready for any setup which may be required.

| User  | Password | Permissions |
|-------|----------|-------------|
| admin | admin    | all         |
