# LeiaWS-Client

LEIA RESTful API for AI
- API version: 1.0.0
  - Build date: 2019-11-15T16:56:35.439+01:00[Europe/Paris]

Leia API

## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.leia</groupId>
  <artifactId>leia-api-java-sdk</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.leia:leia-api-java-sdk:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/leia-api-java-sdk-1.0.0.jar`
* `target/lib/*.jar`

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
Remove all File class to InputStream
## Documentation for Models

 - [Annotation](docs/Annotation.md)
 - [Application](docs/Application.md)
 - [ApplyBody](docs/ApplyBody.md)
 - [Classification](docs/Classification.md)
 - [Document](docs/Document.md)
 - [ImageExtraction](docs/ImageExtraction.md)
 - [Job](docs/Job.md)
 - [LoginToken](docs/LoginToken.md)
 - [Model](docs/Model.md)
 - [TransformBody](docs/TransformBody.md)
 - [Worker](docs/Worker.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

sebastien.favre@docapost.fr

