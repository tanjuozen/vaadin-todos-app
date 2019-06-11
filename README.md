# Spring Boot with Vaadin

This application has been built with Spring Boot and uses Vaadin10 for UI:

* [Official Vaadin documentation](https://vaadin.com/docs/v10)

### Startup
To startup the application use the following command: `mvn spring-boot:run`
To run the tests only, use the following command: `mvn test`

Spring boot ships with an embedded Tomcat application server that will start up on port 8080. This port can be overridden with a parameter: `-Dserver.port=PORT` or in the application.properties.

###Setup
This is a simple application that consists of two parts. 

- A Backend service that consumes data from a remote API.
- Vaadin frontend that uses this backend service with an in-memory data provider to display the results in a grid.

There are some additional that were used to make development easier:

- [Spring actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html)
- [Lombok](https://projectlombok.org/features/all)
- [Spring Dev Tools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html)
- [Netflix Hystrix](https://github.com/Netflix/Hystrix)