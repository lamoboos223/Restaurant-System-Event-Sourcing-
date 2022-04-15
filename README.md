![Untitled Diagram drawio (1)](https://user-images.githubusercontent.com/35597031/162061650-16b86bbe-c078-41e3-a4a4-af29c1bcd0cd.png)

## Docker Compose

To start the project components: zookeeper, kafka, schema registry, postgress and restaurant service
you should execute the following command first.

`docker-compose up -d`

To shutdown the components execute the following command.

`docker-compose down`

---

### Avro

worth to mention, to generate avro schema execute `mvn clean install`

---

## Resources
[kafka installation via docker](https://github.com/confluentinc/cp-all-in-one/blob/6.1.1-post/cp-all-in-one-community/docker-compose.yml#L46)


### Security

All the endpoints are secured so we need to get a token from this URL `https://dev-96351051.okta.com/oauth2/default/v1/token` and just use the token to get access from endpoints

Instructions
1. Get a request to this endpoint `https://dev-96351051.okta.com/oauth2/default/v1/token` adding the next body as x-www-form-urlencoded.
    ![](src/main/resources/images/token_request.jpg)

2. Make a request to the add order endpoint  `localhost:8080/api/v1/orders` and go to the `Authorization` section and 
   choose Bearer Token in Type field and add the token 
   ![](src/main/resources/images/authorized_request.jpg)

Note: token environments variables is created when we get the token.
- curl to get token 
`curl --location --request POST 'https://dev-96351051.okta.com/oauth2/default/v1/token' \
--header 'content-type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic MG9hNG9pM3l1NGlrbnRJc001ZDc6dE5tYTNJODA2djgzQ3I0SkFtWkVJOEprcUtGRWFzUGlNUUFHbkVRYg==' \
--header 'Cookie: JSESSIONID=4FDDE69DDEDB476C66ADF6B30AA78E5F' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=customScope'`


- curl to add orders
 `curl --location --request POST 'localhost:8080/api/v1/orders' \
  --header 'Authorization: Bearer eyJraWQiOiJFSEFXVm1veXExLXk5M3FoOXU5blBMdXZ3cVV5VGZuTXRQZzFYVzIzbldRIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULjBsWE42QTBYTmpxTmktMUtmcm5wZXNvNjdIOWhYN0s0VkFWeV8zdDE1UGMiLCJpc3MiOiJodHRwczovL2Rldi05NjM1MTA1MS5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2NTAwMzc3NzQsImV4cCI6MTY1MDA0MTM3NCwiY2lkIjoiMG9hNG9pM3l1NGlrbnRJc001ZDciLCJzY3AiOlsiY3VzdG9tU2NvcGUiXSwic3ViIjoiMG9hNG9pM3l1NGlrbnRJc001ZDcifQ.kG9k9jslw8psv6heHbWxmqFkzh_WKcwmJVyfmVRgNjIZaJ2bpgDc1kAoDDTQDNZ_ewgOfbSnrlwGTLmY2ShdEjVYrhMiyFQQierOKoOcksE40xLBqlFbZSCKEL6Kuvg0rYfl5jG1DzkZva_8CLjJD07HksJHJwXEyuheLL5DvRlnBJzgGwEcLPHXDKMVPdV9vzt-Ie-lVTpp7_ogpfJyWaB6qCvA7RMcW1ryIKqgnlkEsurYLfKgBXXye67q8vVohdfXDO42eGfuqaI1EV8fZWVWPvHX-VVcqDd_BCF2I7cVt0pm81xcguC5zN_xArouqXc1VP9pHjOcvMtSsP-JKg' \
  --header 'Content-Type: application/json' \
  --header 'Cookie: JSESSIONID=219410311A2101CB0533CEE6FB32EC9C' \
  --data-raw '{
  "name":"erick",
  "total":145,
  "status":"pending"
  }'`
