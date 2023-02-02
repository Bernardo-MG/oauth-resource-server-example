# OAuth 2 Resource Server Example

OAuth 2 Resource Server Example. Integrates with keycloak to authenticate requests.

## Usage

The project requires an authorization server. The Docker compose file will take care of this, while running the project:

```
docker-compose -f docker/docker-compose.yml up
```

The resource server API is located at [http://localhost:8080/rest/entity](http://localhost:8080/rest/entity), accessible to the user test-user/1234.

## Postman

Import `src/test/resources/Oauth.postman_collection.json` to get queries for all the operations. Including authentication and reading.
