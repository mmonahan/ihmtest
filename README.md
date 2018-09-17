# Advertiser API

## Build and Run
Use gradle to build:
```
gradlew build
```

Run the jar that gradle builds:
```
java -jar build\libs\advertiser-1.0.0.jar
```

## Testing
Testing happens automatically during the build process, and includes code coverage.
Reports can be found in:
* build/reports/jacoco
* build/reports/tests

## Admin

### H2 Database
While the application is running, you can access the in-memory H2 database via
the [H2 console](http://localhost:8080/h2). Login information is set in
the [application.properties](src/main/resources/application.properties) file.

### Actuator
Everybody loves a good Actuator endpoint, and for this application they
reside at [localhost:8090/actuator](http://localhost:8090/actuator). One
of my personal favorites is the [info](http://localhost:8090/actuator/info)
endpoint.

### Swagger UI
Swagger is a great way to get to know the available features of this API.
Go [take a look](http://localhost:8080/swagger-ui.html)!