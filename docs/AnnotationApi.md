# AnnotationApi

All URIs are relative to *http://localhost/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createAnnotation**](AnnotationApi.md#createAnnotation) | **POST** /annotation/{document_id} | Creates an annotation
[**deleteAnnotation**](AnnotationApi.md#deleteAnnotation) | **DELETE** /annotation/{annotation_id} | Deletes an annotation
[**getAnnotation**](AnnotationApi.md#getAnnotation) | **GET** /annotation/{annotation_id} | Retrieves an annotation
[**getAnnotations**](AnnotationApi.md#getAnnotations) | **GET** /annotation | Retrieves annotations (paginated)
[**tagAnnotation**](AnnotationApi.md#tagAnnotation) | **POST** /annotation/{annotation_id}/tag/{tag} | Tags an annotation
[**untagAnnotation**](AnnotationApi.md#untagAnnotation) | **DELETE** /annotation/{annotation_id}/tag/{tag} | Untags an annotation
[**updateAnnotation**](AnnotationApi.md#updateAnnotation) | **PATCH** /annotation/{annotation_id} | Updates an annotation


<a name="createAnnotation"></a>
# **createAnnotation**
> Annotation createAnnotation(token, documentId, annotationType, body, name, tags)

Creates an annotation

Creates an annotation

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document to annotate
    String annotationType = TYPING; // String | The type of the annotation
    Object body = null; // Object | The prediction that should be associated to document in this annotation, in free form json
    String name = "name_example"; // String | The name of the annotation (for information purposes only)
    List<String> tags = Arrays.asList(); // List<String> | The tags of the annotation
    try {
      Annotation result = apiInstance.createAnnotation(token, documentId, annotationType, body, name, tags);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#createAnnotation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **documentId** | **String**| The id of the document to annotate |
 **annotationType** | **String**| The type of the annotation |
 **body** | **Object**| The prediction that should be associated to document in this annotation, in free form json |
 **name** | **String**| The name of the annotation (for information purposes only) | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| The tags of the annotation | [optional]

### Return type

[**Annotation**](Annotation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK, annotation created |  -  |
**401** | Not logged in |  -  |

<a name="deleteAnnotation"></a>
# **deleteAnnotation**
> deleteAnnotation(token, annotationId)

Deletes an annotation

Deletes an annotation

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String annotationId = "annotationId_example"; // String | The id of the annotation (for information purposes only)
    try {
      apiInstance.deleteAnnotation(token, annotationId);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#deleteAnnotation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **annotationId** | **String**| The id of the annotation (for information purposes only) |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Annotation deleted |  -  |
**401** | Not logged in |  -  |
**403** | Annotation used in a model |  -  |
**404** | Annotation not found |  -  |

<a name="getAnnotation"></a>
# **getAnnotation**
> Annotation getAnnotation(token, annotationId)

Retrieves an annotation

Retrieves an annotation

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String annotationId = "annotationId_example"; // String | The id of the annotation (for information purposes only)
    try {
      Annotation result = apiInstance.getAnnotation(token, annotationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#getAnnotation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **annotationId** | **String**| The id of the annotation (for information purposes only) |

### Return type

[**Annotation**](Annotation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved annotation |  -  |
**401** | Not logged in |  -  |
**404** | Annotation not found |  -  |

<a name="getAnnotations"></a>
# **getAnnotations**
> List&lt;Annotation&gt; getAnnotations(token, annotationType, name, tags, documentId, createdAfter, createdBefore, offset, limit)

Retrieves annotations (paginated)

Retrieves annotations

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String annotationType = TYPING; // String | If specified, filters the annotations by type
    String name = "name_example"; // String | If specified, filters the annotations by name
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the annotations by tag
    String documentId = "documentId_example"; // String | If specified, filters the annotations attached to a given document
    OffsetDateTime createdAfter = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only annotations created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only annotations created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    Integer offset = 56; // Integer | Number of the first annotation to send (pagination)
    Integer limit = 56; // Integer | Maximum number of annotation to send (pagination)
    try {
      List<Annotation> result = apiInstance.getAnnotations(token, annotationType, name, tags, documentId, createdAfter, createdBefore, offset, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#getAnnotations");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **annotationType** | **String**| If specified, filters the annotations by type | [optional]
 **name** | **String**| If specified, filters the annotations by name | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| If specified, filters the annotations by tag | [optional]
 **documentId** | **String**| If specified, filters the annotations attached to a given document | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only annotations created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only annotations created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **offset** | **Integer**| Number of the first annotation to send (pagination) | [optional]
 **limit** | **Integer**| Maximum number of annotation to send (pagination) | [optional]

### Return type

[**List&lt;Annotation&gt;**](Annotation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved annotations. Also contains pagination information in the headers:&lt;br /&gt; Content-Range: first-last/total&lt;br /&gt; Accept-Range: annotation max_limit  |  -  |
**401** | Not logged in |  -  |
**404** | Annotation not found |  -  |

<a name="tagAnnotation"></a>
# **tagAnnotation**
> Annotation tagAnnotation(token, annotationId, tag)

Tags an annotation

Tags an annotation

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String annotationId = "annotationId_example"; // String | The id of the annotation
    String tag = "tag_example"; // String | The tag to add to the annotation
    try {
      Annotation result = apiInstance.tagAnnotation(token, annotationId, tag);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#tagAnnotation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **annotationId** | **String**| The id of the annotation |
 **tag** | **String**| The tag to add to the annotation |

### Return type

[**Annotation**](Annotation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The annotation with the new tag |  -  |
**401** | Not logged in |  -  |
**404** | Annotation not found |  -  |

<a name="untagAnnotation"></a>
# **untagAnnotation**
> untagAnnotation(token, annotationId, tag)

Untags an annotation

Untags an annotation

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String annotationId = "annotationId_example"; // String | The id of the annotation
    String tag = "tag_example"; // String | The tag to delete from the annotation
    try {
      apiInstance.untagAnnotation(token, annotationId, tag);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#untagAnnotation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **annotationId** | **String**| The id of the annotation |
 **tag** | **String**| The tag to delete from the annotation |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The annotation without the deleted tag |  -  |
**401** | Not logged in |  -  |
**404** | Annotation not found |  -  |

<a name="updateAnnotation"></a>
# **updateAnnotation**
> Annotation updateAnnotation(token, annotationId, body, name)

Updates an annotation

Updates an annotation

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.AnnotationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    AnnotationApi apiInstance = new AnnotationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String annotationId = "annotationId_example"; // String | The id of the annotation to modify
    Object body = null; // Object | The new prediction that should be associated to document in this annotation, in free form json
    String name = "name_example"; // String | The new name of the annotation (won't change if not set)
    try {
      Annotation result = apiInstance.updateAnnotation(token, annotationId, body, name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AnnotationApi#updateAnnotation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**| The login token obtained via GET /login/{api_key} |
 **annotationId** | **String**| The id of the annotation to modify |
 **body** | **Object**| The new prediction that should be associated to document in this annotation, in free form json |
 **name** | **String**| The new name of the annotation (won&#39;t change if not set) | [optional]

### Return type

[**Annotation**](Annotation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK, annotation created |  -  |
**401** | Not logged in |  -  |
**404** | Annotation not found |  -  |

