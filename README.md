It's a web based **Movie Review Application** where users will be able to write reviews on movies.

## Features

- #01 : User Authentication

  - Contributors :
    - [suvro47](https://github.com/suvro47/)

- #02 Movies (home page with movies grid and movie detail page)

  - Contributors :
    - [Shifat](http://www.github.com/jspw)
    - [Rashid](http://www.github.com/rashid54)

- #03 Reviews

  - Contributors :
    - Sohel

- #04 Comments on Review
  - Contributors :
    - Bashim
    - Nahid


## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= root

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
```

# Hibernate ddl auto (create, create-drop, validate, update)
```
spring.jpa.hibernate.ddl-auto= update
```
# App Properties
```
nsl.erp.jwtSecret= SecretKey
nsl.erp.jwtExpirationMs = 86400000

```
## Run Spring Boot application
```
mvn spring-boot:run
```
##Follow the below steps
```
1. First install postgresql 
2. Postgesql  default usename is : postgres
3. Postgresql database password should be : root
4. Need to create a database named "dsi" 
5. Change database postgres to dsi
6. Run the code, that will create all tables in testdb database.
8. Then signin as an admin using username "admin" and password "admin"
9. Signin as an user using username "user" and password "user"
10. Signin as an editor using username "editor" and password "editor"
11. Signin as an creator using username "creator" and password "creator"
```
