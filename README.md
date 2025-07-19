# Calculadora - Challenge RAVEN
- **Author:** Arnaldo Castilla (yo@elingaldo.com)

## 1. Versión

- **IDE:** Spring Tool Suite 4
- **Version:** 4.27.0.RELEASE 
- **Spring Boot:** 3.5.3
- **Postgresql:** 14  
- **Java:** 21  


## 2. API externa utilizada y razón de la elección

### **[mailboxlayer](https://mailboxlayer.com/)**

Las razones por las que se decidió usar la api de mailboxlayer son:

- Agilidad al obtener access-key: Al crear la cuenta, bastó con rellenar el campo de formulario de creación de la cuenta para obtener el access-key.

- Sencilles en el objeto de respuesta: la estructura del objeto resultante una vez se llame a la api, es muy sencillo y facilita al momento de la deserialización, para entrar a menejar el pojo en java.

- más peticiones en plan free: esta api en su parte libre, es la que mayor capacidad tiene para hacer peticiones al api.
 

## 3. Ejemplo de configuración de API key

1. entrar en [Crear una cuenta en mailboxplayer](https://mailboxlayer.com/signup?plan=797&billing=yearly), y rellenar tus datos.

2. Despúes de iniciar sesion ubicarte en [Dashboard](https://mailboxlayer.com/dashboard?logged_in=1).

3. en la sección **Your API Access Key**, copia el acces key.

4. en el archivo application.properties encontrar la variable **validemail.api.access-key**.
```
validemail.api.access-key=pegartuacceskeyaqui
```

> **NOTA:** Puedes usar la misma que se utiliza en el proyecto, ya que no hay indicios de que el acces_key expire pronto.

5. Ejecutar el proyecto. (:


## 4. Lógica aplicada para determinar si un email es aceptado

## 5. Instrucciones de instalación

## 6. Configuración de base de datos y API externa

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

**Listar paginado y por ordenamiento todas las operaciones**
```
curl --location 'localhost:8080/api/history?page=0&size=5&sort=operandA%2Cdesc' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvIiwiaWF0IjoxNzUyOTA1OTUxLCJleHAiOjE3NTI5MDk1NTF9.TFQC2eiwFWAr8GHjD6HJcVdNAtDNB701FVAtGLHQ2YY'
```

**Encontrar operacion por ID**
```
curl --location 'localhost:8080/api/history/7' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvIiwiaWF0IjoxNzUyOTAwMDU0LCJleHAiOjE3NTI5MDM2NTR9.sPQ7M1hwlnlNVIFKdBWFmgRuDAm0gfKWZv3LkQHdVQI'
```

**Eliminar operacion por ID**
```
curl --location --request DELETE 'localhost:8080/api/history/6' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGRvIiwiaWF0IjoxNzUyOTAwMDU0LCJleHAiOjE3NTI5MDM2NTR9.sPQ7M1hwlnlNVIFKdBWFmgRuDAm0gfKWZv3LkQHdVQI'
```
## 8. Decisiones técnicas tomadas


## [Swagger](http://localhost:8080/swagger-ui/index.html) 
