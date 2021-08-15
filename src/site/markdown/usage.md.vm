# Usage

The application is coded in Java, using Maven to manage the project.

## Profiles

Maven profiles are included for setting up the database.

| Profile     | Server                   |
|-------------|--------------------------|
| development | Development settings     |
| production  | Production settings      |

## Database

Before running, start a local Neo4j database. This will be populated with the dataset.

The database should accept the username neo4j with password secret.

## Running

To run the project locally use the following Maven command:

```
mvn spring-boot:run -P development
```

It will be accessible at [http://localhost:8080/](http://localhost:8080/).

## Running the tests

The project requires a database and a server for being able to run the integration tests.

Just like running the project, an embedded server with an in-memory database can be used:

```
mvn verify -P development
```
