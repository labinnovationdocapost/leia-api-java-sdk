# ModelAdminApi

All URIs are relative to *http://localhost/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adminApplyModelAsync**](ModelAdminApi.md#adminApplyModelAsync) | **POST** /admin/{application_id}/model/{model_id}/apply/{document_ids} | Asynchronously applies a model on documents (admin only)
[**adminCreateModel**](ModelAdminApi.md#adminCreateModel) | **POST** /admin/{application_id}/model | Adds a new model to the system (admin only)
[**adminDeleteModel**](ModelAdminApi.md#adminDeleteModel) | **DELETE** /admin/{application_id}/model/{model_id} | Deletes a model (admin only)
[**adminEditModel**](ModelAdminApi.md#adminEditModel) | **PATCH** /admin/{application_id}/model/{model_id} | Modifies an existing model in the system (admin only)
[**adminGetModel**](ModelAdminApi.md#adminGetModel) | **GET** /admin/{application_id}/model/{model_id} | Get a model (admin only)
[**adminGetModels**](ModelAdminApi.md#adminGetModels) | **GET** /admin/model | Lists models (admin only) (paginated))
[**adminTagModel**](ModelAdminApi.md#adminTagModel) | **POST** /admin/{application_id}/model/{model_id}/tag/{tag} | Tags a model (admin only)
[**adminUntagModel**](ModelAdminApi.md#adminUntagModel) | **DELETE** /admin/{application_id}/model/{model_id}/tag/{tag} | Untags a model (admin only)


<a name="adminApplyModelAsync"></a>
# **adminApplyModelAsync**
> Job adminApplyModelAsync(token, applicationId, modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody)

Asynchronously applies a model on documents (admin only)

Asynchronously applies an accessible model on accessible documents and returns a Job, that will have to be polled to get the result. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model belongs
    String modelId = "modelId_example"; // String | The id of the model to apply on the document
    List<String> documentIds = Arrays.asList(); // List<String> | Comma separated list of document ids to process
    String tag = "tag_example"; // String | The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id=document_ids[0] and that contain the specified tag
    String formatType = "formatType_example"; // String | The format in which the data should be returned. If empty, will return an array of key-value items. If it is classification, the result will be a Classification object, if it is image_extraction, it will be an ImageExtraction object.
    String executeAfterId = "executeAfterId_example"; // String | The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Object modelParams = null; // Object | Additional parameters that will be passed as is to the model
    ApplyBody applyBody = new ApplyBody(); // ApplyBody | All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.adminApplyModelAsync(token, applicationId, modelId, documentIds, tag, formatType, executeAfterId, callbackUrl, modelParams, applyBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminApplyModelAsync");
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
 **applicationId** | **String**| The application to which the model belongs |
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

<a name="adminCreateModel"></a>
# **adminCreateModel**
> Model adminCreateModel(token, applicationId, name, body, description, ttl, allowedApplicationIds, allowAllApplications, tags)

Adds a new model to the system (admin only)

Adds a new model to the system and prepares AWS structures to be able to serve it. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application that will own the model
    String name = "name_example"; // String | The name of the model
    File body = new File("/path/to/file"); // File | 
    String description = "description_example"; // String | The description of the model
    String ttl = "ttl_example"; // String | The TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200)
    List<String> allowedApplicationIds = Arrays.asList(); // List<String> | The applications allowed to use this model
    Boolean allowAllApplications = true; // Boolean | Is this model allowed for everyone ?
    List<String> tags = Arrays.asList(); // List<String> | The tags of the model
    try {
      Model result = apiInstance.adminCreateModel(token, applicationId, name, body, description, ttl, allowedApplicationIds, allowAllApplications, tags);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminCreateModel");
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
 **applicationId** | **String**| The application that will own the model |
 **name** | **String**| The name of the model |
 **body** | **File**|  |
 **description** | **String**| The description of the model | [optional]
 **ttl** | **String**| The TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200) | [optional]
 **allowedApplicationIds** | [**List&lt;String&gt;**](String.md)| The applications allowed to use this model | [optional]
 **allowAllApplications** | **Boolean**| Is this model allowed for everyone ? | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| The tags of the model | [optional]

### Return type

[**Model**](Model.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Model created |  -  |
**400** | Invalid model data |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | Owner application not found |  -  |

<a name="adminDeleteModel"></a>
# **adminDeleteModel**
> adminDeleteModel(token, applicationId, modelId)

Deletes a model (admin only)

Get a model in the system that the application can access. This method is only accessible to admins. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to delete belongs
    String modelId = "modelId_example"; // String | The id of the model to delete
    try {
      apiInstance.adminDeleteModel(token, applicationId, modelId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminDeleteModel");
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
 **applicationId** | **String**| The application to which the model to delete belongs |
 **modelId** | **String**| The id of the model to delete |

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
**204** | Model deleted |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | Model not found |  -  |

<a name="adminEditModel"></a>
# **adminEditModel**
> Model adminEditModel(token, applicationId, modelId, name, description, allowedApplicationIds, allowAllApplications, ttl)

Modifies an existing model in the system (admin only)

Modifies a model already in the system. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to modify belongs
    String modelId = "modelId_example"; // String | The id of the model
    String name = "name_example"; // String | The new name of the model
    String description = "description_example"; // String | The new description of the model
    List<String> allowedApplicationIds = Arrays.asList(); // List<String> | The applications allowed to use this model. Clears the list if empty
    Boolean allowAllApplications = true; // Boolean | Is this model allowed for everyone ?
    String ttl = "ttl_example"; // String | The new TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200)
    try {
      Model result = apiInstance.adminEditModel(token, applicationId, modelId, name, description, allowedApplicationIds, allowAllApplications, ttl);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminEditModel");
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
 **applicationId** | **String**| The application to which the model to modify belongs |
 **modelId** | **String**| The id of the model |
 **name** | **String**| The new name of the model | [optional]
 **description** | **String**| The new description of the model | [optional]
 **allowedApplicationIds** | [**List&lt;String&gt;**](String.md)| The applications allowed to use this model. Clears the list if empty | [optional]
 **allowAllApplications** | **Boolean**| Is this model allowed for everyone ? | [optional]
 **ttl** | **String**| The new TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200) | [optional]

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
**200** | Model modified |  -  |
**400** | Invalid model data |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | Owner application not found |  -  |

<a name="adminGetModel"></a>
# **adminGetModel**
> Model adminGetModel(token, applicationId, modelId, fileContents)

Get a model (admin only)

Get a model in the system that the application can access. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to retrieve belongs
    String modelId = "modelId_example"; // String | The id of the model to get
    Boolean fileContents = true; // Boolean | Should Leia API return the model binary contents, or the model metadata as JSON (false by default)
    try {
      Model result = apiInstance.adminGetModel(token, applicationId, modelId, fileContents);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminGetModel");
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
 **applicationId** | **String**| The application to which the model to retrieve belongs |
 **modelId** | **String**| The id of the model to get |
 **fileContents** | **Boolean**| Should Leia API return the model binary contents, or the model metadata as JSON (false by default) | [optional]

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

<a name="adminGetModels"></a>
# **adminGetModels**
> List&lt;Model&gt; adminGetModels(token, applicationId, modelType, name, description, inputTypes, tags, createdAfter, createdBefore, onlyMine, sort, offset, limit)

Lists models (admin only) (paginated))

Lists all models. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application that owns the models
    String modelType = "modelType_example"; // String | Filter by type
    String name = "name_example"; // String | Filter by name
    String description = "description_example"; // String | Gets models that contain this string in their description
    List<String> inputTypes = Arrays.asList(); // List<String> | Filter by input type
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the models by tag
    OffsetDateTime createdAfter = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only models created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only models created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    String onlyMine = "onlyMine_example"; // String | If true, will list only models that strictly belong to application_id if present, or to connected application else (and not all the models that it can use) (false by default)
    String sort = "sort_example"; // String | If specified, sorts the models by a list of existing parameters separated by commas. Can be 'application_id', 'creation_time', 'name', 'description', 'model_type'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first model to send (pagination)
    Integer limit = 56; // Integer | Maximum number of models to send (pagination)
    try {
      List<Model> result = apiInstance.adminGetModels(token, applicationId, modelType, name, description, inputTypes, tags, createdAfter, createdBefore, onlyMine, sort, offset, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminGetModels");
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
 **applicationId** | **String**| The application that owns the models | [optional]
 **modelType** | **String**| Filter by type | [optional]
 **name** | **String**| Filter by name | [optional]
 **description** | **String**| Gets models that contain this string in their description | [optional]
 **inputTypes** | [**List&lt;String&gt;**](String.md)| Filter by input type | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| If specified, filters the models by tag | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only models created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only models created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **onlyMine** | **String**| If true, will list only models that strictly belong to application_id if present, or to connected application else (and not all the models that it can use) (false by default) | [optional]
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

<a name="adminTagModel"></a>
# **adminTagModel**
> Model adminTagModel(token, applicationId, modelId, tag)

Tags a model (admin only)

Tags a model. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to tag belongs
    String modelId = "modelId_example"; // String | The id of the model
    String tag = "tag_example"; // String | The tag to add to the model
    try {
      Model result = apiInstance.adminTagModel(token, applicationId, modelId, tag);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminTagModel");
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
 **applicationId** | **String**| The application to which the model to tag belongs |
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

<a name="adminUntagModel"></a>
# **adminUntagModel**
> adminUntagModel(token, applicationId, modelId, tag)

Untags a model (admin only)

Untags a model. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ModelAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to untag belongs
    String modelId = "modelId_example"; // String | The id of the model
    String tag = "tag_example"; // String | The tag to delete from the model
    try {
      apiInstance.adminUntagModel(token, applicationId, modelId, tag);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminUntagModel");
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
 **applicationId** | **String**| The application to which the model to untag belongs |
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

