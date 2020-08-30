# Simple CRUD-Person

Possible solution to the quarkus.io/guides/rest-data-panache guide.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Configure your settings in application.properties

Add the relevant configuration properties in application.properties.
```
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=<your username>
quarkus.datasource.password=<your password>

quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/hibernate_orm_test
quarkus.datasource.jdbc.min-size=4
quarkus.datasource.jdbc.max-size=16
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `rest-data-panache-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/rest-data-panache-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/rest-data-panache-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
