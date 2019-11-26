# ModelApi

All URIs are relative to *http://localhost/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**applyModelAsync**](ModelApi.md#applyModelAsync) | **POST** /model/{model_id}/apply/{document_ids} | Asynchronously applies a model on documents
[**getModel**](ModelApi.md#getModel) | **GET** /model/{model_id} | Get a model
[**getModels**](ModelApi.md#getModels) | **GET** /model | Lists models (paginated)
[**tagModel**](ModelApi.md#tagModel) | **POST** /model/{model_id}/tag/{tag} | Tags a model
[**untagModel**](ModelApi.md#untagModel) | **DELETE** /model/{model_id}/tag/{tag} | Untags a model


<a name="applyModelAsync"></a>
# **applyModelAsync**
> Job applyModelAsync(token, modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody)

Asynchronously applies a model on documents

Asynchronously applies an accessible model on accessible documents and returns a Job, that will have to be polled to get the result

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelApi apiInstance = new ModelApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String modelId = "modelId_example"; // String | The id of the model to apply on the document
    List<String> documentIds = Arrays.asList(); // List<String> | Comma separated list of document ids to process
    String tag = "tag_example"; // String | The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id=document_ids[0] and that contain the specified tag
    String formatType = "formatType_example"; // String | The format in which the data should be returned. If empty, will return an array of key-value items. If it is classification, the result will be a Classification object, if it is image_extraction, it will be an ImageExtraction object.
    String executeAfterId = "executeAfterId_example"; // String | The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Object modelParams = null; // Object | Additional parameters that will be passed as is to the model
    ApplyBody applyBody = new ApplyBody(); // ApplyBody | All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.applyModelAsync(token, modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelApi#applyModelAsync");
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
 **modelId** | **String**| The id of the model to apply on the document |
 **documentIds** | [**List&lt;String&gt;**](String.md)| Comma separated list of document ids to process |
 **tag** | **String**| The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id&#x3D;document_ids[0] and that contain the specified tag | [optional]
 **formatType** | **String**| The format in which the data should be returned. If empty, will return an array of key-value items. If it is classification, the result will be a Classification object, if it is image_extraction, it will be an ImageExtraction object. | [optional]
 **executeAfterId** | **String**| The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail | [optional]
 **callbackUrl** | **String**| Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback. | [optional]
 **modelParams** | [**Object**](.md)| Additional parameters that will be passed as is to the model | [optional]
 **applyBody** | [**ApplyBody**](ApplyBody.md)| All the previous query parameters can also be passed as JSON in the body of the request | [optional]

### Return type

[**Job**](Job.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A JSON depending on the data that the model is able to output. Might be converted to Classification or ImageExtraction if format_type was specified |  -  |
**400** | The documents could not be processed by this model, or using a tag with multiple document_ids |  -  |
**401** | Not logged in |  -  |
**403** | Not a classification model |  -  |
**404** | Model or document not found |  -  |

<a name="getModel"></a>
# **getModel**
> Model getModel(token, modelId)

Get a model

Get a model in the system that the application can access

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelApi apiInstance = new ModelApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String modelId = "modelId_example"; // String | The id of the model to get
    try {
      Model result = apiInstance.getModel(token, modelId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelApi#getModel");
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
 **modelId** | **String**| The id of the model to get |

### Return type

[**Model**](Model.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A model |  -  |
**401** | Not logged in |  -  |
**404** | Model not found |  -  |

<a name="getModels"></a>
# **getModels**
> List&lt;Model&gt; getModels(token, modelType, name, description, inputTypes, tags, createdAfter, createdBefore, onlyMine, sort, offset, limit)

Lists models (paginated)

Lists models corresponding to the filters that the application can access

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelApi apiInstance = new ModelApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String modelType = "modelType_example"; // String | Filter by type
    String name = "name_example"; // String | Filter by name
    String description = "description_example"; // String | Gets models that contain this string in their description
    List<String> inputTypes = Arrays.asList(); // List<String> | Filter by input type
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the models by tag
    OffsetDateTime createdAfter = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only models created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only models created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    String onlyMine = "onlyMine_example"; // String | If true, will list only models that strictly belong to logged in application (and not all the models that it can use) (false by default)
    String sort = "sort_example"; // String | If specified, sorts the models by a list of existing parameters separated by commas. Can be 'application_id', 'creation_time', 'name', 'description', 'model_type'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first model to send (pagination)
    Integer limit = 56; // Integer | Maximum number of models to send (pagination)
    try {
      List<Model> result = apiInstance.getModels(token, modelType, name, description, inputTypes, tags, createdAfter, createdBefore, onlyMine, sort, offset, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelApi#getModels");
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
 **modelType** | **String**| Filter by type | [optional]
 **name** | **String**| Filter by name | [optional]
 **description** | **String**| Gets models that contain this string in their description | [optional]
 **inputTypes** | [**List&lt;String&gt;**](String.md)| Filter by input type | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| If specified, filters the models by tag | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only models created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only models created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **onlyMine** | **String**| If true, will list only models that strictly belong to logged in application (and not all the models that it can use) (false by default) | [optional]
 **sort** | **String**| If specified, sorts the models by a list of existing parameters separated by commas. Can be &#39;application_id&#39;, &#39;creation_time&#39;, &#39;name&#39;, &#39;description&#39;, &#39;model_type&#39;. Sorts in ascending order by default. If a parameter is preceded by &#39;-&#39;, it is sorted in descending order. | [optional]
 **offset** | **Integer**| Number of the first model to send (pagination) | [optional]
 **limit** | **Integer**| Maximum number of models to send (pagination) | [optional]

### Return type

[**List&lt;Model&gt;**](Model.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Model list. Also contains pagination information in the headers:&lt;br /&gt; Content-Range: first-last/total&lt;br /&gt; Accept-Range: model max_limit  |  -  |
**401** | Not logged in |  -  |

<a name="tagModel"></a>
# **tagModel**
> Model tagModel(token, modelId, tag)

Tags a model

Tags a model

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelApi apiInstance = new ModelApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String modelId = "modelId_example"; // String | The id of the model
    String tag = "tag_example"; // String | The tag to add to the model
    try {
      Model result = apiInstance.tagModel(token, modelId, tag);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelApi#tagModel");
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
 **modelId** | **String**| The id of the model |
 **tag** | **String**| The tag to add to the model |

### Return type

[**Model**](Model.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The model with the new tag |  -  |
**401** | Not logged in |  -  |
**404** | Model not found |  -  |

<a name="untagModel"></a>
# **untagModel**
> untagModel(token, modelId, tag)

Untags a model

Untags a model

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelApi apiInstance = new ModelApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String modelId = "modelId_example"; // String | The id of the model
    String tag = "tag_example"; // String | The tag to delete from the model
    try {
      apiInstance.untagModel(token, modelId, tag);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelApi#untagModel");
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
 **modelId** | **String**| The id of the model |
 **tag** | **String**| The tag to delete from the model |

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
**200** | The model without the deleted tag |  -  |
**401** | Not logged in |  -  |
**404** | Model not found |  -  |

