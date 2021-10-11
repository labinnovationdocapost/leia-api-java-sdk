# ModelAdminApi

All URIs are relative to *http://127.0.0.1:9000/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adminApplyModelAsync**](ModelAdminApi.md#adminApplyModelAsync) | **POST** /admin/{application_id}/model/{model_id}/apply/{document_ids} | Asynchronously applies a model on documents (admin only)
[**adminCreateModel**](ModelAdminApi.md#adminCreateModel) | **POST** /admin/{application_id}/model | Adds a new model to the system (admin only)
[**adminDeleteModel**](ModelAdminApi.md#adminDeleteModel) | **DELETE** /admin/{application_id}/model/{model_id} | Deletes a model (admin only)
[**adminEditModel**](ModelAdminApi.md#adminEditModel) | **PATCH** /admin/{application_id}/model/{model_id} | Modifies an existing model in the system (admin only)
[**adminGetModel**](ModelAdminApi.md#adminGetModel) | **GET** /admin/{application_id}/model/{model_id} | Get a model (admin only)
[**adminGetModelContents**](ModelAdminApi.md#adminGetModelContents) | **GET** /admin/{application_id}/model/{model_id}/file_contents | Get a model (admin only)
[**adminGetModels**](ModelAdminApi.md#adminGetModels) | **GET** /admin/model | Lists models (admin only) (paginated))
[**adminTagModel**](ModelAdminApi.md#adminTagModel) | **POST** /admin/{application_id}/model/{model_id}/tag/{tag} | Tags a model (admin only)
[**adminTrainModelAsync**](ModelAdminApi.md#adminTrainModelAsync) | **POST** /admin/{application_id}/model/{model_module}/train/{documents_tag} | Asynchronously trains a model on documents (admin only)
[**adminUntagModel**](ModelAdminApi.md#adminUntagModel) | **DELETE** /admin/{application_id}/model/{model_id}/tag/{tag} | Untags a model (admin only)


<a name="adminApplyModelAsync"></a>
# **adminApplyModelAsync**
> Job adminApplyModelAsync(token, applicationId, modelId, documentIds, tag, formatType, executeAfterId, pageRange, callbackUrl, modelParams, blockProcessing, applyBody)

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model belongs
    String modelId = "modelId_example"; // String | The id or the short name of the model to apply on the document
    List<String> documentIds = Arrays.asList(); // List<String> | Comma separated list of document ids to process
    String tag = "tag_example"; // String | The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id=document_ids[0] and that contain the specified tag
    FormatTypes formatType = FormatTypes.fromValue("classification"); // FormatTypes | The format in which the data should be returned. If empty, will return an array of key-value items. If it is classification, the result will be a Classification object.
    String executeAfterId = "executeAfterId_example"; // String | The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail
    String pageRange = "pageRange_example"; // String | The pages that should be used in previous job to process this one. Can only be used if execute_after_id is not null. Pages are indexed from 0. Syntax is the same as Python slices syntax (https://docs.python.org/3/whatsnew/2.3.html#extended-slices). Examples :   * Single positive integer : keep only this page (example 4 will keep only page 5 (Remember, pages are indexed from 0))   * Single negative integer : keep only this page, but starting from the end (example -4 will keep only page 7 if there are 10 total pages)   * Range (x:y) : keep only this range of pages (Including x but excluding y, indexed from 0)     Examples       * 2: will keep all pages starting from page 3       * :5 will keep only pages 1 to 5       * 2:5 will keep only pages 3, 4 and 5       * -4: will keep only pages 7 to 10 if there are 10 total pages)       * :-2 will keep only pages 1 to 8 if there are 10 total pages)       * -4:-2 will keep only pages 7 and 8 if there are 10 total pages)   * Stride (::w) : Keep 1 page every w pages starting at the first one (example 2 will keep only odd pages)   * Range and stride (x:y:w) : Keep 1 page every w pages within range (x:y) (example 1::2 will keep only even pages) You can use multiple ranges of page at once, comma separated (For example, 0,2:5,-2:-1 keeps the 1st page, plus pages 3->5, plus the second to last page) 
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Object modelParams = null; // Object | Additional parameters that will be passed as is to the model
    Boolean blockProcessing = true; // Boolean | If true, blocks processing on the job until /job/{id}/start is called. Default is false
    ApplyBody applyBody = new ApplyBody(); // ApplyBody | All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.adminApplyModelAsync(token, applicationId, modelId, documentIds, tag, formatType, executeAfterId, pageRange, callbackUrl, modelParams, blockProcessing, applyBody);
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
 **modelId** | **String**| The id or the short name of the model to apply on the document |
 **documentIds** | [**List&lt;String&gt;**](String.md)| Comma separated list of document ids to process |
 **tag** | **String**| The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id&#x3D;document_ids[0] and that contain the specified tag | [optional]
 **formatType** | [**FormatTypes**](.md)| The format in which the data should be returned. If empty, will return an array of key-value items. If it is classification, the result will be a Classification object. | [optional] [enum: classification]
 **executeAfterId** | **String**| The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail | [optional]
 **pageRange** | **String**| The pages that should be used in previous job to process this one. Can only be used if execute_after_id is not null. Pages are indexed from 0. Syntax is the same as Python slices syntax (https://docs.python.org/3/whatsnew/2.3.html#extended-slices). Examples :   * Single positive integer : keep only this page (example 4 will keep only page 5 (Remember, pages are indexed from 0))   * Single negative integer : keep only this page, but starting from the end (example -4 will keep only page 7 if there are 10 total pages)   * Range (x:y) : keep only this range of pages (Including x but excluding y, indexed from 0)     Examples       * 2: will keep all pages starting from page 3       * :5 will keep only pages 1 to 5       * 2:5 will keep only pages 3, 4 and 5       * -4: will keep only pages 7 to 10 if there are 10 total pages)       * :-2 will keep only pages 1 to 8 if there are 10 total pages)       * -4:-2 will keep only pages 7 and 8 if there are 10 total pages)   * Stride (::w) : Keep 1 page every w pages starting at the first one (example 2 will keep only odd pages)   * Range and stride (x:y:w) : Keep 1 page every w pages within range (x:y) (example 1::2 will keep only even pages) You can use multiple ranges of page at once, comma separated (For example, 0,2:5,-2:-1 keeps the 1st page, plus pages 3-&gt;5, plus the second to last page)  | [optional]
 **callbackUrl** | **String**| Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback. | [optional]
 **modelParams** | [**Object**](.md)| Additional parameters that will be passed as is to the model | [optional]
 **blockProcessing** | **Boolean**| If true, blocks processing on the job until /job/{id}/start is called. Default is false | [optional]
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
**200** | A JSON depending on the data that the model is able to output. Might be converted to Classification if format_type was specified |  -  |
**400** | The documents could not be processed by this model, or using a tag with multiple document_ids |  -  |
**401** | Not logged in |  -  |
**403** | Not a classification model |  -  |
**404** | Model or document not found |  -  |

<a name="adminCreateModel"></a>
# **adminCreateModel**
> Model adminCreateModel(token, applicationId, name, body, shortName, description, ttl, allowedApplicationIds, allowAllApplications, tags)

Adds a new model to the system (admin only)

Adds a new model to the system and prepares structures to be able to serve it. This method is only accessible to admins

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application that will own the model
    String name = "name_example"; // String | The name of the model
    File body = new File("/path/to/file"); // File | 
    String shortName = "shortName_example"; // String | The new short name of the model
    String description = "description_example"; // String | The description of the model
    Integer ttl = 56; // Integer | The TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200)
    List<String> allowedApplicationIds = Arrays.asList(); // List<String> | The applications allowed to use this model
    Boolean allowAllApplications = true; // Boolean | Is this model allowed for everyone ?
    List<String> tags = Arrays.asList(); // List<String> | The tags of the model
    try {
      Model result = apiInstance.adminCreateModel(token, applicationId, name, body, shortName, description, ttl, allowedApplicationIds, allowAllApplications, tags);
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
 **shortName** | **String**| The new short name of the model | [optional]
 **description** | **String**| The description of the model | [optional]
 **ttl** | **Integer**| The TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200) | [optional]
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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

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
> Model adminEditModel(token, applicationId, modelId, name, shortName, description, allowedApplicationIds, allowAllApplications, ttl)

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to modify belongs
    String modelId = "modelId_example"; // String | The id of the model
    String name = "name_example"; // String | The new name of the model
    String shortName = "shortName_example"; // String | The new short name of the model
    String description = "description_example"; // String | The new description of the model
    List<String> allowedApplicationIds = Arrays.asList(); // List<String> | The applications allowed to use this model. Clears the list if empty
    Boolean allowAllApplications = true; // Boolean | Is this model allowed for everyone ?
    Integer ttl = 56; // Integer | The new TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200)
    try {
      Model result = apiInstance.adminEditModel(token, applicationId, modelId, name, shortName, description, allowedApplicationIds, allowAllApplications, ttl);
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
 **shortName** | **String**| The new short name of the model | [optional]
 **description** | **String**| The new description of the model | [optional]
 **allowedApplicationIds** | [**List&lt;String&gt;**](String.md)| The applications allowed to use this model. Clears the list if empty | [optional]
 **allowAllApplications** | **Boolean**| Is this model allowed for everyone ? | [optional]
 **ttl** | **Integer**| The new TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200) | [optional]

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
> Model adminGetModel(token, applicationId, modelId)

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to retrieve belongs
    String modelId = "modelId_example"; // String | The id or the short name of the model to get
    try {
      Model result = apiInstance.adminGetModel(token, applicationId, modelId);
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
 **modelId** | **String**| The id or the short name of the model to get |

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

<a name="adminGetModelContents"></a>
# **adminGetModelContents**
> File adminGetModelContents(token, applicationId, modelId)

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model to retrieve belongs
    String modelId = "modelId_example"; // String | The id or the short name of the model to get
    try {
      File result = apiInstance.adminGetModelContents(token, applicationId, modelId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminGetModelContents");
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
 **modelId** | **String**| The id or the short name of the model to get |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A model |  -  |
**401** | Not logged in |  -  |
**404** | Model not found |  -  |

<a name="adminGetModels"></a>
# **adminGetModels**
> List&lt;Model&gt; adminGetModels(token, modelId, applicationId, modelType, name, shortName, description, inputTypes, tags, createdAfter, createdBefore, onlyMine, sort, offset, limit)

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String modelId = "modelId_example"; // String | The id of the models
    String applicationId = "applicationId_example"; // String | The application that owns the models
    ModelTypes modelType = ModelTypes.fromValue("classification"); // ModelTypes | Filter by type
    String name = "name_example"; // String | Filter by name
    String shortName = "shortName_example"; // String | Filter by short name
    String description = "description_example"; // String | Gets models that contain this string in their description
    List<ModelInputTypes> inputTypes = Arrays.asList(); // List<ModelInputTypes> | Filter by input type
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the models by tag
    OffsetDateTime createdAfter = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only models created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only models created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    Boolean onlyMine = true; // Boolean | If true, will list only models that strictly belong to application_id if present, or to connected application else (and not all the models that it can use) (false by default)
    String sort = "sort_example"; // String | If specified, sorts the models by a list of existing parameters separated by commas. Can be 'application_id', 'creation_time', 'name', 'description', 'model_type'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first model to send (pagination)
    Integer limit = 56; // Integer | Maximum number of models to send (pagination)
    try {
      List<Model> result = apiInstance.adminGetModels(token, modelId, applicationId, modelType, name, shortName, description, inputTypes, tags, createdAfter, createdBefore, onlyMine, sort, offset, limit);
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
 **modelId** | **String**| The id of the models | [optional]
 **applicationId** | **String**| The application that owns the models | [optional]
 **modelType** | [**ModelTypes**](.md)| Filter by type | [optional] [enum: classification, image_extraction, key_value_extraction]
 **name** | **String**| Filter by name | [optional]
 **shortName** | **String**| Filter by short name | [optional]
 **description** | **String**| Gets models that contain this string in their description | [optional]
 **inputTypes** | [**List&lt;ModelInputTypes&gt;**](ModelInputTypes.md)| Filter by input type | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| If specified, filters the models by tag | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only models created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only models created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **onlyMine** | **Boolean**| If true, will list only models that strictly belong to application_id if present, or to connected application else (and not all the models that it can use) (false by default) | [optional]
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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

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

<a name="adminTrainModelAsync"></a>
# **adminTrainModelAsync**
> Job adminTrainModelAsync(token, applicationId, modelModule, documentsTag, modelName, shortName, description, ttl, allowedApplicationIds, allowAllApplications, tags, executeAfterId, callbackUrl, modelParams, trainBody)

Asynchronously trains a model on documents (admin only)

Asynchronously trains a model on accessible documents and returns a Job, that will have to be polled to get the model. This method is only accessible to admins

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

    ModelAdminApi apiInstance = new ModelAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the model belongs
    String modelModule = "modelModule_example"; // String | The module name of the model to train on documents
    String documentsTag = "documentsTag_example"; // String | The tag of the documents to train with
    String modelName = "modelName_example"; // String | The future name of the model in database
    String shortName = "shortName_example"; // String | The new short name of the model
    String description = "description_example"; // String | The description of the model
    Integer ttl = 56; // Integer | The TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200)
    List<String> allowedApplicationIds = Arrays.asList(); // List<String> | The applications allowed to use this model
    Boolean allowAllApplications = true; // Boolean | Is this model allowed for everyone ?
    List<String> tags = Arrays.asList(); // List<String> | The tags of the model
    String executeAfterId = "executeAfterId_example"; // String | The job that is a prerequisite for this job to run
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Object modelParams = null; // Object | Additional parameters that will be passed as is to the train method
    TrainBody trainBody = new TrainBody(); // TrainBody | All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.adminTrainModelAsync(token, applicationId, modelModule, documentsTag, modelName, shortName, description, ttl, allowedApplicationIds, allowAllApplications, tags, executeAfterId, callbackUrl, modelParams, trainBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ModelAdminApi#adminTrainModelAsync");
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
 **modelModule** | **String**| The module name of the model to train on documents |
 **documentsTag** | **String**| The tag of the documents to train with |
 **modelName** | **String**| The future name of the model in database | [optional]
 **shortName** | **String**| The new short name of the model | [optional]
 **description** | **String**| The description of the model | [optional]
 **ttl** | **Integer**| The TTL of the model in seconds, if running in worker mode (negative for infinite TTL, default is 200) | [optional]
 **allowedApplicationIds** | [**List&lt;String&gt;**](String.md)| The applications allowed to use this model | [optional]
 **allowAllApplications** | **Boolean**| Is this model allowed for everyone ? | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| The tags of the model | [optional]
 **executeAfterId** | **String**| The job that is a prerequisite for this job to run | [optional]
 **callbackUrl** | **String**| Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback. | [optional]
 **modelParams** | [**Object**](.md)| Additional parameters that will be passed as is to the train method | [optional]
 **trainBody** | [**TrainBody**](TrainBody.md)| All the previous query parameters can also be passed as JSON in the body of the request | [optional]

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
**200** | A JSON depending on the data that the model is able to output. |  -  |
**400** | The documents could not be processed by this model |  -  |
**401** | Not logged in |  -  |
**403** | Not a trainable model |  -  |
**404** | Training documents not found |  -  |

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
    defaultClient.setBasePath("http://127.0.0.1:9000/leia/1.0.0");

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

