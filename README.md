# SpringBootRestExercise

Exercise project with Spring Boot, developing a RESTful account service for Technest.

To start off we create the Spring Boot project using the Spring Initializr (https://start.spring.io/) with the following dependencies:

- Spring Web (for MVC architecture and embedded Tomcat server)
- Spring Data JPA (for use of persistence APIs such as Spring Data and Hibernate)
- H2 Database (for embedded database for quick and easy testing)

We'll begin by setting up the database and the model object 'Account'.

For the account money-related properties, I'll be using the Joda Money library, which provides classes for currency and money management as well as useful methods for obtaining a valid currency and modifying the amount of money in an account.

To convert between the CurrencyUnit and Money Java types and SQL Types, I opted to declare these complex types as transient and use the String and Double types to map these properties with database columns, then use these to create the complex objects at runtime. This is not the best solution but it's easy and quick to implement, if time were less an issue a better approach would be to use AttributeConverter implementations to convert between db and Java types.

Now we create a JPA CrudRepository to manipulate the entities in the database.

With this we'll begin creating unit tests to test the Data Access layer. We'll only test the methods that we implemented, since we trust that the inherited methods from the CrudRepository library will work.