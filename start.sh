docker-compose up -d

curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema": "{\"type\":\"record\",\"name\":\"Orders\",\"namespace\":\"com.example.restaurant\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"total\",\"type\":\"float\"},{\"name\":\"status\",\"type\":\"string\"}]}"}' http://localhost:8081/subjects/orders/versions

