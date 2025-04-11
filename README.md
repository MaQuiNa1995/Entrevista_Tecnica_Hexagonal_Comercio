# medallas-metricas

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/442888e182654f06b199d057a1f2972f)](https://app.codacy.com/gh/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/dashboard?utm_source=github.com&utm_medium=referral&utm_content=MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio&utm_campaign=Badge_Coverage)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/442888e182654f06b199d057a1f2972f)](https://app.codacy.com/gh/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio&amp;utm_campaign=Badge_Grade)

# uml-del-proyecto

![alt text](https://github.com/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/blob/master/doc/main_hexagonal.svg?raw=true)

# prueba-tecnica

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas.

A continuación se muestra un ejemplo de la tabla con los campos relevantes:
 
| BRAND_ID | START_DATE | END_DATE | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1 | 35455 | 0 | 35.50 | EUR |
| 1 | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2 | 35455 | 1 | 25.45 | EUR |
| 1 | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3 | 35455 | 1 | 30.50 | EUR |
| 1 | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4 | 35455 | 1 | 38.95 | EUR |

## campos

* BRAND_ID: foreign key de la cadena del grupo (1 = Zara).
* START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
* PRICE_LIST: Identificador de la tarifa de precios aplicable.
* PRODUCT_ID: Identificador código de producto.
* PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
* PRICE: precio final de venta.
* CURR: iso de la moneda.
 
## se-pide

El objetivo principal es crear una aplicación o servicio utilizando el framework Spring Boot que ofrezca un punto de acceso REST para realizar consultas sobre esta base de datos de precios. Este endpoint REST deberá:

- Aceptar como parámetros de entrada la fecha de consulta (o aplicación), el identificador del producto y el identificador de la marca.

- Proporcionar como resultado el identificador del producto, el identificador de la marca, la tarifa que se aplica, el intervalo de fechas durante el cual se aplica el precio y el precio final que debe aplicarse.

### base de Datos

La aplicación debe utilizar una base de datos en memoria del tipo H2, la cual debe ser inicializada con los datos de ejemplo proporcionados en **Tabla de Base de Datos** en el siguiente punto. Si es necesario, puedes modificar los nombres de los campos o agregar nuevos campos según consideres apropiado. Asegúrate de elegir los tipos de datos adecuados para cada campo.

### test

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
                                                                                       
- Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (Zara)
- Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (Zara)
- Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (Zara)
- Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (Zara)
- Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (Zara)

## ejecucion

### compilacion

Tendremos que ejecutar el siguiente comando en la raíz del proyecto que lo compilará y creará la imagen 

`mvn clean install && cd application && mvn jib:dockerBuild`

En caso de que no queramos crear la imagen bastaría con el siguiente comando en la raíz del proyecto:

`mvn clean install`

Imagenes de la compilacion y creacion de la imagen:

![alt text](https://github.com/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/blob/master/doc/Ejecucion1.png?raw=true)

![alt text](https://github.com/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/blob/master/doc/Ejecucion2.png?raw=true)

### despliegue-en-docker

Teniendo docker levantado ejecutaremos:

`docker run -p 8080:8080 christian-prueba-tecnica:1`

### ejecucion-de-pruebas-funcionales-en-postman

Despues importaremos en postman la colección `Coleccion_Prueba_Tecnica.postman_collection.json` que está en la carpeta `server-api-definition` en `la raíz del proyecto` y ejecutaremos la colección

![alt text](https://github.com/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/blob/master/doc/Ejecucion3.png?raw=true)

### swagger-ui

Para acceder a swagger tendremos que ir a: http://localhost:8080/swagger-ui/index.html

![alt text](https://github.com/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/blob/master/doc/swaggerui.png?raw=true)


## Proximos Pasos:

En futuras fases de desarrollo, se implementará la observabilidad para mejorar la monitorización, el diagnóstico y la comprensión del comportamiento de la aplicación
en entornos de despliegue. Esto incluirá:

### Metricas:

Integración con un sistema de métricas (por ejemplo, Prometheus) para recopilar y exponer indicadores clave de rendimiento (KPIs) como latencia de las APIs, tasas de error, uso de recursos etc.
      
### Logs Estructurados

Implementación de un sistema de logging estructurado (SLF4j), el filtrado y el análisis de los logs por parte de herramientas de gestión de logs (Grafana)

### Trazado Distribuido
    
Incorporación de un sistema de trazado distribuido (Spring Cloud) para rastrear las solicitudes a través de los diferentes servicios y componentes de la aplicación.

### Health Checks Mejorados

Extensión de los health checks actuales para incluir indicadores más detallados sobre la salud de los componentes críticos y dependencias de la aplicación.

### Paneles de Control (Dashboards)

Creación de paneles de control visuales utilizando herramientas como Grafana para visualizar las métricas, los logs y los trazos
