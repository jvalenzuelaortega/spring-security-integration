# Ejercicio de Spring Boot con Spring Security

Esta aplicación demuestra cómo integrar Spring Boot con Spring Security utilizando JWT para la autenticación. Además, utiliza una base de datos en memoria (H2) para el almacenamiento temporal de datos.

La aplicación expone tres endpoints principales que permiten:

- Crear un usuario: Genera un token JWT para el nuevo usuario.
- Buscar un usuario: Requiere autenticación JWT para acceder a la información del usuario.
- Actualizar un usuario: Permite actualizar el correo electrónico y el nombre de usuario, con autenticación JWT necesaria.
## Comenzando 🚀

Para ejecutar la aplicación, necesitarás algunas herramientas específicas. A continuación, se detallan los requisitos necesarios:

### Pre-requisitos 📋


| Herramienta | Description |
| --- | ----------- |
| Java | 17.0.11 (Temurin) |
| Gradle | 8.9 |

***Importante***
Para simplificar la construcción de ciertos objetos, se ha utilizado la biblioteca Lombok. En algunos casos, será necesario realizar configuraciones adicionales para su correcta implementación. Para mas detalles, revisar el siguiente link: https://www.baeldung.com/lombok-ide

### Instalación 🔧
Importante clonar el proyecto:
```
git clone https://github.com/jvalenzuelaortega/assessment-exercise.git
```
Una vez clonado, para iniciar la aplicación, sigue estos pasos:

- Navega al directorio exercise.
- Abre una terminal en ese directorio.
- Ejecuta el siguiente comando:

```
gradle bootRun
```

Este comando permitira arrancar la aplicacion SpringBoot

#### Consideraciones para probar

Es importante destacar que, al iniciar la aplicación, se carga una base de datos en memoria (H2) con un esquema SQL y datos de prueba preconfigurados. Estos archivos se encuentran en la carpeta **sql** dentro del directorio **resources** de Spring.

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

#### Probando la aplicacion
Esta aplicación ofrece tres endpoints principales que permiten realizar las siguientes operaciones:

- Crear un usuario
- Obtener la información de un usuario
- Actualizar el correo electrónico y la contraseña de un usuario

➡️ Para crear un usuario, ocupa el siguiente curl

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
##### Ejemplo

![Creacion de usuario](images/create-user.gif)


Este endpoint generará un token asociado al usuario que estamos creando. Este token se utilizará para autenticarse en los otros dos endpoints. Cabe destacar que solo este endpoint puede ser utilizado sin un token.

✅ Respuesta esperada

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

➡️ Curl para obtener informacion del usuario: 
Es importante destacar que se debe incluir un parámetro en la ruta, el cual puede ser:

- default: Proporciona una respuesta más concisa (la misma que al crear un usuario).
- detail: Ofrece información completa sobre el usuario.

```
curl --location 'localhost:8080/exercise/api/get-user-by-name-and-type/detail?name=Robert%20Rodriguez' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb2JlcnQgUm9kcmlndWV6IiwiaWF0IjoxNzIzNTE2MDgzLCJleHAiOjE3MjM1MTk2ODN9.pyfUHE4C6zbcpQhjjROvLpdCA3LV6DAP1LnfwZCdzPU'
```

![Obtener de usuario](images/get-user.gif)

➡️ Curl para actualizar correo y contrasena:
Es necesario incluir el nombre del usuario como parámetro en la URL.

```
curl --location --request PUT 'localhost:8080/exercise/api/update-email-and-password/Felipe Rodriguez' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJGZWxpcGUgUm9kcmlndWV6IiwiaWF0IjoxNzIzNTYyMDg5LCJleHAiOjE3MjM1NjU2ODl9.1bR_p3j5og3gXOC2c-1rGpsP-Lra3q1Uj_EkeGusPgE' \
--data-raw '{
   "email":"newmail@rodriguez.org",
   "password":"Mondays5"
}'

```

![Actualizar campos de usuario](images/update-user.gif)

#### Enlaces importantes
Para acceder a Swagger UI o a la consola de H2, utiliza los siguientes enlaces, siempre y cuando la aplicación esté en funcionamiento:

- SwaggerUI (Informacion de endpoints): http://localhost:8080/swagger-ui/index.html
- H2 Console (Consola de base de datos en memoria): http://localhost:8080/h2-console/

## Ejecutando las pruebas ⚙️

Para arrancar los test unitarios, abrir la terminal en el directorio **exercise** y ejecutar el siguiente comando

```
gradle test
```

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring Boot](https://spring.io/projects/spring-boot) - Herramienta de desarrollo de microservicios
* [Gradle](https://gradle.org/) - Manejador de dependencias
* [Spring Security](https://spring.io/projects/spring-security) - Marco de autenticación y control de acceso
* [Lombok](https://projectlombok.org/) - Libreria para simplificar la construccion de codigo
* [Swagger](https://swagger.io/) - Herramienta para documentacion de apis
* [H2](https://www.h2database.com/html/main.html) - Base de datos SQL de Java


## Autores ✒️

* **Juan Claudio Valenzuela** - *Trabajo Inicial* - [jvalenzuelaortega](https://github.com/jvalenzuelaortega)
