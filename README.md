# ihmtest

## Build and Run
Use gradle to build:
```
gradlew build
```

Run the jar that gradle builds:
```
java -jar build\libs\advertiser-0.1.0.jar
```

## Access H2 DB
While the application is running, you can access the in-memory H2 database via
the [H2 console](http://localhost:8080/h2). Login information is set in the [application.properties](src/main/resources/application.properties) file.

