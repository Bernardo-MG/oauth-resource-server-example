# OAuth 2 Resource Server Example

Example for setting up a OAuth 2 Resource Server on a web service with Spring Boot. Using Keycloak as the OAuth server.

## Usage

The project requires an authorization server. The Docker compose file will take care of this, while also running the project:

```
docker-compose -f docker/docker-compose.yml --project-name oauth2-resource-server-example up
```

Once started, the web service be available at [http://localhost:8080/](http://localhost:8080/).

The auth service is available at [http://localhost:8090/](http://localhost:8090/). The username and password are both admin.

## Requests with Postman

To make things easier import `src/test/resources/resource_server.postman_collection.json` into Postman. This file includes all the queries needed to test the project.

## Users

| User      | Password | Permissions |
|-----------|----------|-------------|
| test-user | 1234     | read, edit  |

Note that this web service requires secure tokens. The Postman requests includes an authenticated one, which takes care of this.

## Features

- [Spring MVC](https://spring.io/)
- [OAuth2](https://oauth.net/2/)
- Integrates with [Keycloak](https://www.keycloak.org/)
- Basic Spring Boot security auditing

## Collaborate

Any kind of help with the project will be well received, and there are two main ways to give such help:

- Reporting errors and asking for extensions through the issues management
- or forking the repository and extending the project

### Issues management

Issues are managed at the GitHub [project issues tracker][issues], where any Github user may report bugs or ask for new features.

### Getting the code

If you wish to fork or modify the code, visit the [GitHub project page][scm], where the latest versions are always kept. Check the 'master' branch for the latest release, and the 'develop' for the current, and stable, development version.

## License

The project has been released under the [MIT License][license].

[issues]: https://github.com/bernardo-mg/oauth-resource-server-example/issues
[license]: https://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/oauth-resource-server-example
