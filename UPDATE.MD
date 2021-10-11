## Tools
- OpenApi Generator: https://github.com/OpenAPITools/openapi-generator

## Build
- The maven parameters is deprecated
- Use the gradle configuration

## Command to run
```shell
java -jar openapi-generator-cli-5.2.1.jar generate -i http://127.0.0.1:9000/leia/1.0.0/openapi.json -g java -o leia-api-java-sdk-test --model-package "io.leia.client.model" --api-package "io.leia.client.api"
```

## Update bindings

Edit file `ApiClient`
- at `T deserialize(Response response, Type returnType)` in [io.leia.client.ApiClient](src/main/java/io/leia/client/ApiClient.java) Add :
```
else if (("class " + InputStream.class.getName()).equals(returnType.toString())) {
    return (T) response.body().byteStream();
}
```
- at `RequestBody serialize(Object obj, String contentType)` in [io.leia.client.ApiClient](src/main/java/io/leia/client/ApiClient.java) Add :
``` 
else if (obj instanceof InputStream) {
     try {
         return RequestBody.create(MediaType.parse(contentType), ByteStreams.toByteArray((InputStream) obj));
     } catch (IOException e) {
         throw new ApiException("Cannot convert InputStream content to byte[]", e, 0, null);
     }
 }
```  