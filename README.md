# Calculadora - Challenge RAVEN
- **Author:** Arnaldo Castilla - yo@elingaldo.com

### Maquina usada para la creación de este proyecto:

```
Apache Maven 3.9.10 (5f519b97e944483d878815739f519b2eade0a91d)
Maven home: C:\Users\Administrador\.m2\wrapper\dists\apache-maven-3.9.10\a38810a491b03367137adfdfbe7d14c4
Java version: 21, vendor: Oracle Corporation, runtime: C:\jdk-21
Default locale: es_CO, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

## 📑 Tabla de Contenido

- [1. Versión](#1-versión)
- [2. API externa utilizada y razón de la selección](#2-api-externa-utilizada-y-razón-de-la-selección)
- [3. Ejemplo de configuración de API key](#3-ejemplo-de-configuración-de-api-key)
- [4. Lógica aplicada para determinar si un email es aceptado](#4-lógica-aplicada-para-determinar-si-un-email-es-aceptado)
- [5. Instrucciones de instalación](#5-instrucciones-de-instalación)
- [6. Configuración de base de datos y API externa](#6-configuración-de-base-de-datos-y-api-externa)
  - [6.1 Configuración base de datos PostgreSQL-14](#61-configuración-base-de-datos-postgresql-14)
  - [6.2 Configuracion API externa](#62-configuracion-api-externa)
- [7. Ejemplos de uso con curl/httpie](#7-ejemplos-de-uso-con-curlhttpie)
  - [7.1 Usuarios](#71-usuarios)
  - [7.2 Operaciones](#72-operaciones)
- [8. Decisiones técnicas tomadas](#8-decisiones-técnicas-tomadas)
- [Documentación Swagger](#documentación-swagger)

## 1. Versión

- **IDE:** Spring Tool Suite 4
- **Version:** 4.27.0.RELEASE 
- **Spring Boot:** 3.5.3
- **Postgresql:** 14  
- **Maven:** 3.9.10  
- **Java:** 21  


## 2. API externa utilizada y razón de la selección

### **[mailboxlayer](https://mailboxlayer.com/)**

Las razones por las que se decidió usar la api de mailboxlayer son:

- Agilidad al obtener access-key: Al crear la cuenta, bastó con rellenar el campo de formulario de creación de la cuenta para obtener el access-key.

- Sencilles en el objeto de respuesta: la estructura del objeto resultante una vez se llame a la api, es muy sencillo y facilita al momento de la deserialización, para entrar a menejar el pojo en java.

- Más peticiones en plan free: esta api en su parte libre, es la que mayor capacidad tiene para hacer peticiones al api.
 

## 3. Ejemplo de configuración de API key

1. Entrar en [Crear una cuenta en mailboxplayer](https://mailboxlayer.com/signup?plan=797&billing=yearly), y rellenar tus datos.

2. Despúes de iniciar sesion ubicarte en [Dashboard](https://mailboxlayer.com/dashboard?logged_in=1).

3. En la sección **Your API Access Key**, copia el acces key.

4. En el archivo application.properties encontrar la variable **validemail.api.access-key**.
```
validemail.api.access-key=pegartuacceskeyaqui
```

> **NOTA:** Puedes usar la misma que se utiliza en el proyecto, ya que no hay indicios de que el acces_key expire pronto.

5. Ejecutar el proyecto. (:


## 4. Lógica aplicada para determinar si un email es aceptado
La lógica implementada para saber si un email es aceptado es muy sencilla, en el proceso de creación del usuario a nivel de controlador se agregó una linea de validación, el archivo que hace la validacion se encuentra en el paquete utils, así las cosas, una vez entra la petición del usuario se pasa como parametro el email del usuario al metodo que hace el llamado a la api de mailboxlayer, una vez responde la api, se deserializa el objeto y se pasa a pojo, una vez manipulable a nivel de java se hacen las siguientes validaciones:

```
if(validationEmail.getDisposable()) {
	throw new IllegalArgumentException("Disposable email addresses are not allowed");
}
	
if(!validationEmail.getFormat_valid()) {
	throw new IllegalArgumentException("No valid email addresses are not allowed");
}
	
if(!validationEmail.getMx_found()) {
	throw new IllegalArgumentException("No found mx email addresses are not allowed");
}
```
> **NOTA:** El primer condicional valida que si el email es desechable, el api al responder contiene un atributo llamado Disposable, es un booleano y si viene true, indica que el email es desechable, la siguiente validación indica el validez en el formato, si es formato valido es true pero nosotro queremos capturar cuando NO sea valido, es por ello la negación al inicio del condicional y final mente sucede lo mismo para los registros mx encontrados.

## 5. Instrucciones de instalación
1. Tener configurado la variable de entorno para java.
2. Tener instalado Docker desktop, para el contenedor de la base de datos
3. Tener una cuenta creada en mailboxlayer, en caso tal el access-key, utilizado deje de funcionar.
4. Tener clonado e importado en un IDE de preferencia
5. Ejecutar proyecto.


## 6. Configuración de base de datos y API externa
### 6.1 Configuración base de datos PostgreSQL-14
Para el desarrollo de este proyecto se utilizó una base de datos contenerizada en docker para mayor agilidad, es necesario tener instalado docker desktop en la maquina donde operará el aplicativo, si NO lo tienes estalado por favor visita este [enlace](https://www.docker.com/get-started/).

Una vez instalado y ejecutado docker desktop, abrir una terminal y ejecutar el siguiente comando:
```
docker run -p5432:5432 --name spring-sql -e POSTGRES_USER=calculator -e POSTGRES_PASSWORD=raven*1234 -e POSTGRES_DB=calculatordb -d postgres:14
```

> **NOTA:** Este comando creara un contenedor con una base de datos en postgresql llamada calculatordb, un usuario:  calculator y finalmente el password: raven*1234, todos de vital importancia para que el microservico pueda establacer conexion con la base de datos y operar a normalidad

**Configuración de base de datos en el application.properties**

```
spring.datasource.url=jdbc:postgresql://localhost:5432/calculatordb
spring.datasource.username=calculator
spring.datasource.password=raven*1234
```

### 6.2 Configuracion API externa
**En esta misma documentación revisar item 3, apartado 4.**

## 7. Ejemplos de uso con curl/httpie
### 7.1 Usuarios
**Crear usuarios**
```
curl --location 'localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"aldo1",
    "password": "qaz123",
    "email": "tu@elingaldo.com"
}'
```
**Iniciar sesion usuarios**

```
curl --location 'localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "aldo1",
    "password": "qaz123"
}'
```

### 7.2 Operaciones
**Hacer operación**
```
curl --location 'localhost:8080/api/calculate' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvMSIsImlhdCI6MTc1MjkwMjY1NiwiZXhwIjoxNzUyOTA2MjU2fQ.1j1rWb_UBW8iwZZVauUQ9-OtSbXhZUijDGPZBNIw5MI' \
--data '{
    "operation": "DIVIDE",
    "operandA": 5,
    "operandB": 1
}'
```

**Listar paginado y por ordenamiento de todas las operaciones**
```
curl --location 'localhost:8080/api/history?page=0&size=5&sort=operandA%2Cdesc' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvIiwiaWF0IjoxNzUyOTA1OTUxLCJleHAiOjE3NTI5MDk1NTF9.TFQC2eiwFWAr8GHjD6HJcVdNAtDNB701FVAtGLHQ2YY'
```

**Encontrar operación por ID**
```
curl --location 'localhost:8080/api/history/7' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvIiwiaWF0IjoxNzUyOTAwMDU0LCJleHAiOjE3NTI5MDM2NTR9.sPQ7M1hwlnlNVIFKdBWFmgRuDAm0gfKWZv3LkQHdVQI'
```

**Eliminar operación por ID**
```
curl --location --request DELETE 'localhost:8080/api/history/6' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvIiwiaWF0IjoxNzUyOTAwMDU0LCJleHAiOjE3NTI5MDM2NTR9.sPQ7M1hwlnlNVIFKdBWFmgRuDAm0gfKWZv3LkQHdVQI'
```
## 8. Decisiones técnicas tomadas

1. Las excepciones fueron definidas globales, contenidas en el archivo GlobalExceptionHandler, ubicado en el paquete exception.
2. Estructuración de proyectos por capas.
3. Implementación de Lombok para agilizar la creación de clases tipo DTO y servicios, reduciendo el boilerplate mediante anotaciones.
4. Uso de los principios de clean code en el desarrollo de los clases
5. implementación de la libreria estable de jwt para la generacion de tokens, evitando funciones deprecadas.
6. Configuración centralizada en el aplication.properties.
7. Se hace uso de orm para facilitar la persitencia de los datos,  asi como la relaciones entre entidades.

## Documentación Swagger 
Una vez el microservicio este ejecutado, en la pagina del navegador de su preferencia visitar la url
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) 

## Cobertura de Pruebas

![Cobertura de Pruebas](docs/coverage.png)