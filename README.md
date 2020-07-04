# Lostworld Web Service

Back end section of Lostworld web service - application enabling access to travel knowledge and creating a database of your own experience.

## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[MySQL](https://www.mysql.com/) - Open-Source Relational Database Management System
* 	[Git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more
* 	[JSON Web Token](https://www.jsonwebtoken.io/) - Open-Source library enabling creating and verification of jwt tokens in authentication module

To build and run the application from a fresh clone of this repo:

## Configure MySQL

1. Create a database in your MySQL instance.
2. Update the application.properties file in the `src/main/resources` folder with the URL, username and password for your MySQL instance. The table schema for the Todo objects will be created for you in the database.


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `pl.lostworld.lostworldbackend.LostworldBackendApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
