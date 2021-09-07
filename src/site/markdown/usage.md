# Usage

Start Keycloak. It will be needed for authenticating:

```
docker-compose -f docker/docker-compose.yml up
```

Now you can open it at [http://localhost:8090/](http://localhost:8090/). Go there and, with the user admin/admin, add a new user to the bmg realm.

Afterwards you can start the resource server:

```
mvn spring-boot:run
```

An endpoint will be accessible at [http://localhost:8080/](http://localhost:8080/). You may check it for example by querying [http://localhost:8080/rest/entity](http://localhost:8080/rest/entity).

### Using Postman

The file at src/test/resources/Oauth.postman_collection.json contains queries for all the operations. Including authentication and reading.

### Generating tokens

Send a post request to [http://localhost:8090/auth/realms/bmg/protocol/openid-connect/token](http://localhost:8090/auth/realms/bmg/protocol/openid-connect/token). The body shoul encode, as a x-www-form-urlencoded, the following information:

- client_id
- username
- password
- grant_type

The value for grant_type is password. All the other fields should match the user which you want to authenticate.
