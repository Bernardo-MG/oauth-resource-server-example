# OAuth 2 Resource Server Example

OAuth 2 Resource Server Example. Requires the [OAuth 2 Authorization Server Example](https://github.com/Bernardo-MG/oauth-authorization-server-example) to be running, as it will handle request authorization.

Just get both projects runnings and make a request query to http://localhost:8080/rest/entity.

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Features

Several technologies are combined to make this work:

- [Spring MVC](https://spring.io/)
- [OAuth2](https://oauth.net/2/)

## Documentation

Documentation is always generated for the latest release, kept in the 'master' branch:

- The [latest release documentation page][site-release].
- The [latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [latest snapshot Javadoc site][javadoc-develop].

The documentation site is actually a Maven site, and its sources are included in the project. If required it can be generated by using the following Maven command:

```
mvn verify site -P development
```

The verify phase is required, otherwise some of the reports won't be generated.

## Usage

First of all, start the application environment:

```
docker-compose -f docker/docker-compose.yml up
```

This will start [a keycloak server](http://localhost:8090/) ready for the project. This will include a user named test-user, with the password 1234.

Afterwards you can start the resource server:

```
mvn spring-boot:run
```

An endpoint will be accessible at [http://localhost:8080/](http://localhost:8080/). You may check it for example by querying [http://localhost:8080/rest/entity](http://localhost:8080/rest/entity).

### Running the tests

The project requires a database and a server for being able to run the integration tests.

Just like running the project, an embedded server with an in-memory database can be used:

```
mvn verify -P development
```

### Keycloak

The included docker-compose file will set up and run keycloak:


```
docker-compose -f docker/docker-compose.yml up
```

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

[issues]: https://github.com/bernardo-mg/darksouls-explorer/issues
[javadoc-develop]: https://docs.bernardomg.com/development/maven/darksouls-explorer/apidocs
[javadoc-release]: https://docs.bernardomg.com/maven/darksouls-explorer/apidocs
[license]: https://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/darksouls-explorer
[site-develop]: https://docs.bernardomg.com/development/maven/darksouls-explorer
[site-release]: https://docs.bernardomg.com/maven/darksouls-explorer
