# ITEMS API - Challenge de Mercado Libre

>> Author: Guido Buchely [guidobuchely@gmail.com]

Esta API puede considerarse como un MVP funcional del requerimiento esperado..
En resumen, se ha utilizado el Framework Spring / Spring Boot para la implementación general de la solución.

Soporta las siguientes funcionalidades/características: 

i. API Rest para servicios de consulta de Items e Hijos; por ejemplo:
- http://localhost:8080/items/{ID}}
>> Spring MVC

ii. Llamado a APIS externas, consolidación de información.
>> Spring Rest Template, Completable Futures

iii. Persistencia de datos previos para minimizar el número de llamados a APIs externas.
>>> MySQL, JPA

iv. Metricas/Health Check de uso y rendimiento de la API y llamados a APIs externas.
- http://localhost:8080/health
>>> AspectJ, Interceptors

Se ha dejado por fuera las siguientes caracteristicas no funcionales, necesarias en otro caso:
- Pruebas unitarias y de integración
- Estandar de nombramiento. Se alterna Java Notation y SQL Notation
- Manejo de errores.  Se controlan pero no existe respuesta unificada hacia el cliente.
- Estandar en manejo de LOGs.  

## Asunciones

i. Para el servicio de Health Check se asume que únicamente se requiere un número configurable de Slots (o datos recogidos en 1 minuto, por ejemplo).
Esto porque se maneja datos en memoria local la cual debe liberarse frecuentemente.
Posiblemente los datos sean usados por una consola de operaciones que no requiera más que los datos del último segmento de operación.
Los valores son configurables (Ver configuración)


## Prerequisitos
- Docker
- Docker compose V3

### Ejecución Local
- i. Inicie la base de datos MySQL
```sh
$ docker-compose up
```
- ii. Espere que la DB se encuentre lista
- iii. Crear la imagen de la API con Docker:
```sh
$ docker build -t meli .
```
- iv: Ejecute la API en contenedor de Docker:
```sh
$ docker run --network host -p 8080:8080 meli
```

>> Propuesta de mejoramiento para Contenedores:

* Integrar el contenendor de la API en el servicio de Docker-Compose.  Antes se requiere encontrar un equivalente a las funcionalidades "on-depend & condition" ahora no soportadas en V3.
* Use imagenes "light-weigh" para Gradle/Groovy & JVM

## Pruebas locales
En el browser puede ejecutar llamados a los siguientes endpoints:
- http://localhost:8080/items/MLU460998489
- http://localhost:8080/health

O usando la consola y curl:
```sh
$ curl -X GET "http://localhost:8080/items/MLU460998489"
$ curl -X GET "http://localhost:8080/health"
```

### Configuration

Debe tener en cuenta las siguientes propiedades de negocio en el YAML (application.yml):

API de Mercado Libre para consuta items:
>external.uri.items: https://api.mercadolibre.com/items/{0}

API de Mercado Libre para consulta de hijos de Items:
>external.uri.children: https://api.mercadolibre.com/items/{0}/children

Formato de Fecha para transformaciones:
>external.date.pattern: yyyy-MM-dd'T'HH:mm:ss.SSS

Total de "últimos" slots de tiempo con datos para el servicio de Health Check:
>external.health.slots: 5

Tiempo en segundos del slot previo:
>external.health.split: 60

** Por defecto la API publica (pull) los últimos 5 minutos con datos en slots de 1 minuto.


## Propuestas de Mejora / Cambio

i. Implementar Swagger o OpenAPI para documentar la API
ii. Por la naturaleza de la información, el uso de un esquema de almacenamiento NoSQL puede ser más apropiado.
iii. Spring es bueno para implementaciones rápidas, pero para performance y en capa Web podría ser otras tecnologías.
iv. Evitar recoger métricas en un servicio REST.  Se prefiere usar LOGS y un servicio de monitoreo.
v. Usar un framework de caché real, como Redis.
v1. Unificar la estratégia de recopilar datos para métricas.  Por ejemplo, todo con AspectJ.
