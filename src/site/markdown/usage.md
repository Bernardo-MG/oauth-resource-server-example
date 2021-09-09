# Usage

The project requires an authorization server. For this reason a docker compose file is included, which will set up the environment:

```
docker-compose -f docker/docker-compose.yml up
```

It's only missing authenticated users. Open the authentication server administration panel at [http://localhost:8090/](http://localhost:8090/). Using the administrator user admin/admin add a new user to the bmg realm. After this the resource server will be accessible to that user.

The resource server API is located at [http://localhost:8080/rest/entity](http://localhost:8080/rest/entity).

### Using Postman

The file at src/test/resources/Oauth.postman_collection.json contains queries for all the operations. Including authentication and reading.
