
# Gestión de Usuarios

Este proyecto es una API RESTful para la creación de usuarios utilizando autorización con JWT.

Se trata de un servicio que expone dos endpoints: `/api/auth` y `/api/user`.

Se ha implementado con Spring Boot, Spring Data JPA, Spring Security, JWT, H2, Lombok, OpenApi (Contract First), Jacoco y Checkstyle

## Pre-Requisitos
 1. Java 17
 2. Plugin Lombok (en su IDE Intellij IDEA o STS)
 3. Plugin Checkstyle (en su IDE Intellij IDEA o STS)
 4. Maven

## Ejecutar servicio API RESTful de manera local

 1. Clonar el repositorio de manera local.

 2. Ejecutar un `clean` e `install` con maven, ya se por comandos o desde un IDE. Esto permitirá que se generen las clases del contrato con  OpenApi.
 
 Por comandos sería:
 
```
mvn clean install
```

 3. Inicializar el servicio, esto varía según el IDE a utilizar. Por comandos de maven sería:
 
```
mvn spring-boot:run
```

**Nota: El servicio está configurado para inicializar en el puerto 8081. Para validar que está ejecutándose, se puede pegar la siguiente url en el navegador `http://localhost:8081/swagger-ui/index.html` para que se muestre el contrato de la API RESTful**

## Ejecución de Pruebas

### Postman

Se ha creado un `collection` y un `environment` en postman para facilitar las pruebas. Dentro de la carpeta `files` se encuentran los archivos json que se deben importar a la herramienta Postman. Para empezar a probar debe tener seleccionado el environment `TestNisum`.

A continuación, se explica la manera de probar un caso exitoso y los casos de error.

### Caso Exitoso

Teniendo el servicio en ejecución:

 1. Primero debemos autenticarnos para obtener un JWT.

Ejecutar un POST a la uri `/api/auth` con el siguiente body (json) para obtener un token JWT:

```
{
    "email": "martingarcia@espn.cl",
    "password": "martinG_1234"
}
```

**Nota: Por default se tienen 2 usuarios válidos para autenticación.**

| email | password |
| ----- | -------- |
| jorgediaz@nisum.cl | Jorge1234. |
| martingarcia@espn.cl | martinG_1234 |


 2. Ahora vamos a crear un usuario.

 Ejecutar un POST a la uri `/api/user` con el header:
 
```
Authorization: Bearer <jwt>
```

**Nota: Reemplazar `<jwt>` por el token obteniendo en el paso anterior.**


También agregar el siguiente body (json):

```
{
    "name": "Juan Rodriguez",
    "email": "juanrodriguez1234@cnn.cl",
    "password": "Juan1234.",
    "phones": [
        {
            "number": "123456789",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "987654321",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}
```

**Nota: Para validar la creación del usuario se puede ingresar a la consola de H2. Se debe pegar la url: `http://localhost:8081/h2-console` en el navegador y loguearse con el usuario `sa` y el password `1234`**

### Casos de Error

 1. **Forbidden**. Cuando se intenta ejecutar `/api/user` sin un token válido.
 2. **Unauthorized**. Cuando se ingresa mal email y/o password en `/api/auth`. También cuando el usuario no está activo. Se tiene cargado un usuario inactivo de ejemplo:

```
{
    "email": "luisperez@nike.cl",
    "password": "LuisPerez."
}
```
 3. **Conflict**. Cuando se intenta enviar un email que ya existe al crear un usuario con la uri `/api/user`.
 4. **Bad Request**. Cuando el email y/o password no tienen el formato correcto.