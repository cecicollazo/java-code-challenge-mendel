# Java Code Challenge Mendel

[![Build Status](https://app.travis-ci.com/cecicollazo/java-code-challenge-mendel.svg?branch=main)](https://app.travis-ci.com/cecicollazo/java-code-challenge-mendel)

Esta es una API RESTful que permite almacenar y consultar transacciones. Las transacciones se pueden vincular entre sí a través de un "parent_id" y se pueden consultar por tipo o calcular la suma de las transacciones vinculadas.

### Swagger

Puede acceder a la documentación de Swagger en [swagger-v1.0.yaml](src/main/resources/swagger/swagger-v1.0.yaml)


### Métodos Disponibles

- `PUT /transactions/{transaction_id}`: Permite crear una nueva transacción. Se requiere el ID de la transacción, el monto, el tipo y un "parent_id" opcional.
- `GET /transactions/types/{type}`: Devuelve una lista de IDs de transacciones para un tipo específico.
- `GET /transactions/sum/{transaction_id}`: Calcula la suma de todas las transacciones vinculadas transitivamente por su "parent_id" a una transacción específica.



