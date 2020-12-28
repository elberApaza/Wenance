# Wenance
Examen técnico para wenance:

A) Construir un microservicio que haciendo uso del siguiente servicio REST (https://cex.io/api/last_price/BTC/USD)
realice una llamada recurrente cada 10 segundos, almacene los datos y que exponga a través de un API rest las siguientes funcionalidades:

1. Obtener el precio del bitcoin en cierto timestamp.
2. Conocer el promedio de valor entre dos timestamps así como la diferencia porcentual entre ese valor promedio y el valor máximo almacenado para toda la serie temporal disponible.

B) incorporar un archivo READ.ME que contenga una descripción de la solución propuesta así como instrucciones de ejecución en entorno local.

Indicaciones:
* La aplicación deberá estar desarrollada usando Springboot y subida a un repositorio en github con permisos públicos de acceso y clonado.
* La aplicación deberá ser ejecutada en entorno local sin necesidad de dockerización ni de otro software más que java 1.8
* El uso de frameworks accesorios queda a la elección del candidato
* La persistencia de información se realizará en una estructura de datos en memoria lo más optimizada posible.

Puntos extras si:
- Se incorpora un conjunto de test unitarios que demuestran la corrección de la solución
- Se usa WebClient 

****************************************************************************************************************************************************************************

Esta api rest, se realizo en:
java 8
springboot 2.2.1
y se uso tambien lombok.

Para resolver el problema planteado se creo un historial que se carga cada 10 segundo gracias a @Scheduled. 

despues a través de un servicio se aplica la logia, mediante streams, sobre esta lista para poder obtener el bitcoin en cierto timestamp, para hacer el promedio y sacar el maximo valor almacenado.


