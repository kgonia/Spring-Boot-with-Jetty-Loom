## Spring Boot Jetty Loom Starter

This repository contains simple library with Jetty server backed by virtual threads, Spring autoconfigration and simple app.

This repo is inspired by [Brian Goetz](https://twitter.com/briangoetz) quote: "I think Project Loom is going to kill Reactive Programming"

jetty-loom module code is taken mostly from [Rodrigo Vedovato](https://github.com/rodrigovedovato/jetty-loom) and [Jetty Non-Eclipse Repositories](https://github.com/jetty-project/jetty-loom)


### Modules:

- **jetty-loom**: The sample library with Jetty Thread Pool backed by virtual threads.

- **jetty-loom-spring-boot-autoconfigure**: The project containing the auto-configuration for the library.

- **jetty-loom--spring-boot-starter**: The custom starter for the library.

- **jetty-loom--spring-boot-simple-webapp**: The sample web app that uses the custom starter.


## Running simple web app

### Requirements

For building and running the application you need:

- [JDK 19](https://jdk.java.net/19/)
- [Maven 3](https://maven.apache.org)

Before running simple webapp locally you must build & install modules

```shell
mvn install
```

### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `org.kgonia.jetty.loom.showcase.SpringJettyLoomApplication` class in the `jetty-loom-spring-boot-simple-webapp` module from your IDE.

If you are using RC version add --enable-preview do jvm arguments.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn -pl jetty-loom-spring-boot-simple-webapp spring-boot:run
```


