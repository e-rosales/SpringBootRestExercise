# SpringBootRestExercise

Exercise project with Spring Boot, developing a RESTful account service for Technest.

To start off we create the Spring Boot project using the Spring Initializr (https://start.spring.io/) with the following dependencies:

- Spring Web (for MVC architecture and embedded Tomcat server)
- Spring Data JPA (for use of persistence APIs such as Spring Data and Hibernate)
- H2 Database (for embedded database for quick and easy testing)

We'll begin by setting up the database and the model object 'Account'.

For the account money-related properties, I'll be using the Joda Money library, which provides classes for currency and money management as well as useful methods for obtaining a valid currency and modifying the amount of money in an account.