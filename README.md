# README #
# Descripción del API REST - Comercio Electrónico #

### PRICES API ###

La API PRICES proporciona un endpoint REST para la consulta de tarifas y precios finales aplicados a productos de una cadena en un rango de fechas específico en la base de datos de comercio electrónico de la compañía.

### 1.- Funcionalidades.- ###
Consultar Tarifas y Precios
Endpoint: /api/v1/prices/consulta
Método: GET
Descripción: Este endpoint permite realizar consultas para obtener la tarifa y el precio final aplicado a un producto de una cadena en una fecha determinada.

### 2.- Parámetros de Entrada: ###
applicationDate: Fecha en la que se desea aplicar la consulta (formato: "YYYY-MM-DD HH:mm:ss").
productId: Identificador único del producto.
brandId: Identificador único de la cadena o marca asociada al producto.

    Ejemplo de Cuerpo de la Solicitud (JSON):
        {       
            "applicationDate": "2020-06-15T10:00:00",
            "productId": 35455,
            "brandId": 1
        }

    Ejemplo de Respuesta (JSON):
    [
        {
            "productId": 35455,
            "brandId": 1,
            "priceList": 3,
            "applicationDate": "2020-06-15T10:00:00",
            "price": 30.5
        }
    ]
### Campos Relevantes en la Respuesta: ###
  - productId: ID del producto consultado.
  - brandId: ID de la cadena o marca asociada al producto.
  - priceList: ID de la tarifa aplicada.
  - applicationDate: Fecha en la que se aplica la tarifa y el precio final.
  - price: Precio final (PVP) del producto aplicado.

### 3.- Configuración y Datos de Ejemplo.- ###
La aplicación utiliza una base de datos en memoria (tipo H2) y se inicializa con los datos de ejemplo proporcionados. Se pueden cambiar los nombres de los campos y añadir otros nuevos según sea necesario, eligiendo el tipo de dato adecuado para cada uno.

* Pruebas del Endpoint.-

    Se han desarrollado pruebas unitarias para el endpoint /api/v1/prices/consulta que validan las siguientes peticiones al servicio con los datos de ejemplo:

  - Petrición 1:
  Fecha de Aplicación: 2020-06-14 10:00:00
  ID de Producto: 35455
  ID de Cadena: 1 (ZARA)

  - Petición 2:
   Fecha de Aplicación: 2020-06-14 16:00:00
   ID de Producto: 35455
   ID de Cadena: 1 (ZARA)

  - Petición 3:
    Fecha de Aplicación: 2020-06-14 21:00:00
    ID de Producto: 35455
    ID de Cadena: 1 (ZARA)

  - Petición 4:
    Fecha de Aplicación: 2020-06-15 10:00:00    
    ID de Producto: 35455
    ID de Cadena: 1 (ZARA)

  - Petición 5:
    Fecha de Aplicación: 2020-06-16 21:00:00
    ID de Producto: 35455
    ID de Cadena: 1 (ZARA)

### 4.- Notas Adicionales: ###

- Las fechas se proporcionan en formato "YYYY-MM-DD HH:mm:ss".
- La aplicación utiliza Arquitectura hexagonal, Spring Boot y H2 para facilitar el desarrollo y las pruebas.
- La direccion de la consola de adiministracion de la BD H2, se encuentra en la siguiente direccion:
  - http://localhost:8080/h2-console
- Los Test desarrollados al endpoint /api/v1/prices/consulta se encuentran en el siguiente directorio: 
  - arq-hexagonal\ecommerce\src\test\java\com\akkodis\xsoto\hexagonal
    - PricesControllerAssuredTest.java
    - PricesControllerMockMvcTest.java
- Las instrucciones del desarrollo del API Rest se encuentra en:
  - arq-hexagonal\TestJava2020.txt
- El archivo json de las pruebas realizadas en postman se encuentran en la siguiente direccion:
  - arq-hexagonal\TestArqHexagonal.postman_collection.json
