# Índice

1. [Medallas Métricas](#medallas-metricas)
2. [Uml Del proyecto](#uml-del-proyecto)
3. [Prueba técnica](#prueba-tecnica)
    - 3.1. Campos
    - 3.2. Se pide
    - 3.3. Recomendaciones
    - 3.4. Ejecución
        - 3.4.1. Despliegue en docker
        - 3.4.2. Ejecución de pruebas funcionales en postman

# medallas-metricas

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/442888e182654f06b199d057a1f2972f)](https://www.codacy.com/gh/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/dashboard?utm_source=github.com&utm_medium=referral&utm_content=MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio&utm_campaign=Badge_Coverage)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/442888e182654f06b199d057a1f2972f)](https://www.codacy.com/gh/MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MaQuiNa1995/Entrevista_Tecnica_Hexagonal_Comercio&amp;utm_campaign=Badge_Grade)

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
 
Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
 
Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
 
Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).
              
Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
                                                                                       
- Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (Zara)
- Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (Zara)
- Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (Zara)
- Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (Zara)
- Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (Zara)

## recomendaciones

- [x] Código funcionando: El código debe compilar y ejecutarse correctamente.

- [x] Estructura de código clara y limpia: Uso obligatorio de la arquitectura hexagonal para una correcta separación de responsabilidades y mantenibilidad.

- [x] Código mínimo: Implementar solo lo estrictamente necesario. Cuidado con las dependencias del pom.xml. Se pueden añadir librerías como Lombok o Swagger si tienen sentido y aportan valor.

- [x] Nada de código muerto: Eliminar cualquier código que no se utilice.

- [x] Calidad del código: El código debe ser legible, bien formateado y seguir buenas prácticas de programación.

- [x] Tests unitarios: Implementar tests unitarios para cubrir la lógica de negocio y los componentes individuales.

- Tests funcionales:
    - [x] Se piden al menos 5 casuísticas testeadas.
    - [x] Se pueden implementar como tests de integración.
    - [x] Se valora positivamente tener tests e2e (Karate, Cucumber, RestAssured, Postman, etc.).

- [x] API First: Se recomienda encarecidamente diseñar la API primero.

- [x] Gestión de excepciones: Controlar los casos de "no se encuentra el precio" o de "parámetros incorrectos" mediante excepciones.

- [x] README: Crear un archivo README que explique cómo ejecutar el programa y los tests, e incluya cualquier detalle relevante sobre el código.

- Valoraciones positivas:
    - [x] Configuración de SwaggerUI.
    - [x] Configuración de Docker.
    
## ejecucion

### despliegue-en-docker

Teniendo previamente el entorno de docker levantado tendrás que ir a la raíz del proyecto y ejecutar el siguiente comando que creará y ejecutará la imagen

`docker build --tag=pruebatecnica:1 . && docker run -p8080:8080 pruebatecnica:1`

### ejecucion-de-pruebas-funcionales-en-postman

Despues importaremos en postman la colección `Coleccion_Prueba_Tecnica.postman_collection.json` que está en la raíz del proyecto y ejecutaremos la colección 
