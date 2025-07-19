# Calculadora - Challenge RAVEN
- **Author:** Arnaldo Castilla (yo@elingaldo.com)

## Versión

- **IDE:** Spring Tool Suite 4
- **Version:** 4.27.0.RELEASE 
- **Spring Boot:** 3.5.3
- **Postgresql:** 14  
- **Java:** 21  



##API externa utilizada y razón de la elección

 **[mailboxlayer](https://mailboxlayer.com/)**

Las razones por las que se decidio usar esta api fueron:

- Agilidad al obtener access-key: Al crear la cuenta, bastó con rellenar el campo de formulario de creación de la cuenta para obtener el access-key.

- Sencilles en el objeto de respuesta: la estructura del objeto resultante una vez se llame a la api, es muy sencillo y facilita al momento de la deserialización, para entrar a menejar el pojo en java.

- más peticiones en plan free: esta api en su parte libre, es la que mayor capacidad tiene para hacer peticiones al api.
 

##Ejemplo de configuración de API key

1. entrar en [Crear una cuenta en mailboxplayer](https://mailboxlayer.com/signup?plan=797&billing=yearly), y rellenar tus datos.

2. Despúes de iniciar sesion ubicarte en [Dashboard](https://mailboxlayer.com/dashboard?logged_in=1)

3. en la sección ** Your API Access Key **, copia el acces key

4. en el archivo application.properties encontrar la variable ** validemail.api.access-key**
```
validemail.api.access-key=pegartuacceskeyaqui
```

> **NOTA:** Puedes usar la misma que se utiliza en el proyecto, ya que no hay indicios de que el acces_key expire pronto.

5. Ejecutar el proyecto (:


##Lógica aplicada para determinar si un email es aceptado

##Instrucciones de instalación

##Configuración de base de datos y API externa

##Ejemplos de uso con curl/httpie

##Decisiones técnicas tomadas


##Swagger
- http://localhost:8080/swagger-ui/index.html
