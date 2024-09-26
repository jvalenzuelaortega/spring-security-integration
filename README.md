# Spring Security Integration

This application demonstrates how to integrate Spring Boot with Spring Security using JWT for authentication. It also uses an in-memory database (H2) for temporary data storage.
The application exposes three main endpoints that allow:

- Create a user: Generates a JWT token for the new user.
- Search for a user: Requires JWT authentication to access user information.
- Update a user: Allows updating the email and username, with JWT authentication required.
  
## Getting started 🚀

To run the application, you will need some specific tools. Below are the necessary requirements:

### Prerequisites 📋


| Tool | Description |
| --- | ----------- |
| Java | 17.0.11 (Temurin) |
| Gradle | 8.9 |

***Important***
To simplify the construction of certain objects, the Lombok library has been used. In some cases, additional configurations will be necessary for correct implementation. For more details, check the following link: https://www.baeldung.com/lombok-ide

### Installation 🔧
Clone the project:
```
git clone https://github.com/jvalenzuelaortega/spring-security-integration.git
```
Once cloned, to start the application, follow these steps:

- Navigate to the exercise directory.
- Open a terminal in that directory.
- Run the following command:

```
gradle bootRun
```

This command allows you to start the Spring Boot application.

#### Considerations for testing

It is important to note that when the application starts, an in-memory database (H2) is loaded with a preconfigured SQL schema and test data. These files are located in the **sql** folder inside the Spring **resources** directory.

📄 schema.sql
```sql
CREATE TABLE users
(
    id       UUID DEFAULT RANDOM_UUID(),
    name     VARCHAR(50) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(8) NOT NULL,
    token    VARCHAR(200) NOT NULL ,
    active   BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_ads PRIMARY KEY (id)
);

CREATE TABLE phone
(
    id   UUID DEFAULT RANDOM_UUID(),
    number_phone INT NOT NULL,
    city_code INT NOT NULL,
    country_code INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

```

📄 data.sql
```
-- Test data for validations
INSERT INTO users (name, email, password, token, active)
VALUES ('Juan Valenzuela', 'juan@valenzuela.org', 'hunter1', 'token12345',true);
```

#### Testing the application
This application offers three main endpoints that allow you to perform the following operations:

- Create a user
- Obtain a user's information
- Update a user's email and password

➡️ To create a user, use the following curl

```
curl --location 'localhost:8080/exercise/api/create-user' \
--header 'Content-Type: application/json' \
--data-raw '{
   "name":"Robert Rodriguez",
   "email":"robert@rodriguez.org",
   "password":"Hunters2",
   "phones":[
      {
         "number":"1234567",
         "citycode":"1",
         "contrycode":"57"
      },
       {
         "number":"1234567",
         "citycode":"1",
         "contrycode":"57"
      }
   ]
}'
```
##### Example

![Create user](images/create-user.gif)


This endpoint will generate a token associated with the user we are creating. This token will be used to authenticate to the other two endpoints. It is worth noting that only this endpoint can be used without a token.

✅ Expected response

```
{
    "created": "2024-08-12 22:28:03",
    "modified": "2024-08-12 22:28:03",
    "last_login": "2024-08-12 22:28:03",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb2JlcnQgUm9kcmlndWV6IiwiaWF0IjoxNzIzNTE2MDgzLCJleHAiOjE3MjM1MTk2ODN9.pyfUHE4C6zbcpQhjjROvLpdCA3LV6DAP1LnfwZCdzPU",
    "is_active": true,
    "id": "358ddbcc-02d9-4521-bbc7-68233003820d"
}
```

➡️ Curl to get user information: 
It is important to note that a parameter must be included in the route, which can be:

- default: Provides a more concise response (the same as when creating a user).
- detail: Provides complete information about the user.

```
curl --location 'localhost:8080/exercise/api/get-user-by-name-and-type/detail?name=Robert%20Rodriguez' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb2JlcnQgUm9kcmlndWV6IiwiaWF0IjoxNzIzNTE2MDgzLCJleHAiOjE3MjM1MTk2ODN9.pyfUHE4C6zbcpQhjjROvLpdCA3LV6DAP1LnfwZCdzPU'
```

![Get user](images/get-user.gif)

➡️ Curl to update email and password:
It is necessary to include the user name as a parameter in the URL.

```
curl --location --request PUT 'localhost:8080/exercise/api/update-email-and-password/Felipe Rodriguez' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGZWxpcGUgUm9kcmlndWV6IiwiaWF0IjoxNzIzNTYyMDg5LCJleHAiOjE3MjM1NjU2ODl9.1bR_p3j5og3gXOC2c-1rGpsP-Lra3q1Uj_EkeGusPgE' \
--data-raw '{
   "email":"newmail@rodriguez.org",
   "password":"Mondays5"
}'

```

![Update user](images/update-user.gif)

#### Important Links
To access Swagger UI or the H2 Console, use the following links, as long as the application is running:

- SwaggerUI (Endpoint Information): http://localhost:8080/swagger-ui/index.html
- H2 Console (In-Memory Database Console): http://localhost:8080/h2-console/

## Running the tests ⚙️

To start the unit tests, open the terminal in the **exercise** directory and run the following command

```
gradle test
```

## Built with 🛠️

* [Spring Boot](https://spring.io/projects/spring-boot) - Microservices development tool
* [Gradle](https://gradle.org/) - Dependency manager
* [Spring Security](https://spring.io/projects/spring-security) - Authentication and access control framework
* [Lombok](https://projectlombok.org/) - Library to simplify code construction
* [Swagger](https://swagger.io/) - API documentation tool
* [H2](https://www.h2database.com/html/main.html) - Java SQL database

## Authors ✒️

* **Juan Claudio Valenzuela** - *Initial Work* - [jvalenzuelaortega](https://github.com/jvalenzuelaortega)
