#!/bin/sh

docker-compose up -d
sleep 20
schemaId=$(curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema": "{\"type\":\"record\",\"name\":\"orders\",\"namespace\":\"com.example.restaurant\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"total\",\"type\":\"float\"},{\"name\":\"status\",\"type\":\"string\"}]}"}' http://localhost:8081/subjects/orders/versions | \
          grep -o -E "\"id\":[0-9]+" | awk -F\: '{print $2}')

echo "Registering orders schema into Confluent Schema Registry"
curl -X GET http://localhost:8081/schemas/ids/$schemaId