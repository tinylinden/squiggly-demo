# squiggly-demo

[![Stability: Experimental](https://masterminds.github.io/stability/experimental.svg)](https://masterminds.github.io/stability/experimental.html)

Experimental Spring Boot service implemented to check:

1. [Squiggly Filter for Jackson](https://github.com/bohnman/squiggly) as a generic solution to specify which
   fields should be returned in REST API response,
2. [OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) to generate server stubs from
   [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification).

## Usage

Just start the service with `./gradlew bootRun` and play with API using:

* Swagger UI available at [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/),
* `curl` - some examples below.

Sample fields filter expressions could be found in `curl` examples below. And full syntax is described in 
Squiggly Filter for Jackson [documentation](https://github.com/bohnman/squiggly/blob/master/README.md#general-usage).

### `curl` Examples

* All fields should be returned if no filter was provided
  ```
  curl -G http://localhost:8080/issue \
    -H "Accept: application/vnd.example.public.v1+json"
  ```
* Only _id_ field should be returned
  ```
  curl -G http://localhost:8080/issue \
    -H "Accept: application/vnd.example.public.v1+json" \
    --data-urlencode "fields=id"
  ```
* All fields but _actions_ and _properties_ should be returned
  ```
  curl -G http://localhost:8080/issue \
    -H "Accept: application/vnd.example.public.v1+json" \
    --data-urlencode "fields=-actions,-properties"
  ```
* Only _id_ and _actions.type_ fields should be returned
  ```
  curl -G http://localhost:8080/issue \
    -H "Accept: application/vnd.example.public.v1+json" \
    --data-urlencode "fields=id,actions[type]"
  ```