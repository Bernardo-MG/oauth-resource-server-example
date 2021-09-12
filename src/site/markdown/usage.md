# Usage

The project requires an authorization server. The Docker compose file will take care of this, while running the project:

```
docker-compose -f docker/docker-compose.yml up
```

The resource server API is located at [http://localhost:8080/rest/entity](http://localhost:8080/rest/entity), accessible to the user test-user/1234.

## Requests with Postman

Import `src/test/resources/Oauth.postman_collection.json` to get queries for all the operations. Including authentication and reading.

## Authentication Server

A Keycloak server is taking care of the authentication. It includes [an administration panel](http://localhost:8090/), accessible to the user admin/admin. Use it to make any change as needed.
