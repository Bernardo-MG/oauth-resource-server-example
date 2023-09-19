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
