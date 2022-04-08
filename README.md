![Untitled Diagram drawio (1)](https://user-images.githubusercontent.com/35597031/162061650-16b86bbe-c078-41e3-a4a4-af29c1bcd0cd.png)

## Docker Compose

To start the project components: zookeeper, kafka, schema registry, postgress and restaurant service
you should execute the following command first.

`docker-compose up -d`

To shutdown the components execute the following command.

`docker-compose down`


---

## Schema Registry

### register new schema

```shell
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema": "{\"type\":\"record\",\"name\":\"Orders\",\"namespace\":\"com.example.restaurant\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"total\",\"type\":\"float\"},{\"name\":\"status\",\"type\":\"string\"}]}"}' http://localhost:8081/subjects/orders/versions
```


---

## Resources
[kafka installation via docker](https://github.com/confluentinc/cp-all-in-one/blob/6.1.1-post/cp-all-in-one-community/docker-compose.yml#L46)




