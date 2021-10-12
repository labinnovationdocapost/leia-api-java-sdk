# ApplicationAdminApi

All URIs are relative to *https://api.leia.io/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adminCreateApplication**](ApplicationAdminApi.md#adminCreateApplication) | **POST** /admin/application | Adds a new application to the system (admin only)
[**adminDeleteAlwaysOnSchedule**](ApplicationAdminApi.md#adminDeleteAlwaysOnSchedule) | **DELETE** /admin/application/{application_id}/always_on_schedules/{always_on_schedule_id} | Removes a schedule from an application
[**adminDeleteApplication**](ApplicationAdminApi.md#adminDeleteApplication) | **DELETE** /admin/application/{application_id} | Deletes an application (admin only)
[**adminEditApplication**](ApplicationAdminApi.md#adminEditApplication) | **PATCH** /admin/application/{application_id} | Modifies an existing application in the system (admin only)
[**adminGetApplication**](ApplicationAdminApi.md#adminGetApplication) | **GET** /admin/application/{application_id} | Retrieves an application (admin only)
[**adminGetApplications**](ApplicationAdminApi.md#adminGetApplications) | **GET** /admin/application | Retrieves applications (admin only) (paginated)
[**adminResetApiKey**](ApplicationAdminApi.md#adminResetApiKey) | **POST** /admin/application/{application_id}/reset_api_key | Resets an API key (admin only)


<a name="adminCreateApplication"></a>
# **adminCreateApplication**
> Application adminCreateApplication(token, application)

Adds a new application to the system (admin only)

Adds a new application to the system. This method is only accessible to admins. An API key will be generated for the new application when calling this method. Note or store it carefully, it will not be recoverable after this call.

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    Application application = new Application(); // Application | 
    try {
      Application result = apiInstance.adminCreateApplication(token, application);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminCreateApplication");
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
 **application** | [**Application**](Application.md)|  | [optional]

### Return type

[**Application**](Application.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Application created |  -  |
**400** | Invalid application data |  -  |
**401** | Not logged in or not an admin |  -  |
**409** | An existing application already exists |  -  |

<a name="adminDeleteAlwaysOnSchedule"></a>
# **adminDeleteAlwaysOnSchedule**
> adminDeleteAlwaysOnSchedule(token, applicationId, alwaysOnScheduleId)

Removes a schedule from an application

Removes a schedule from an application

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The id of the application
    String alwaysOnScheduleId = "alwaysOnScheduleId_example"; // String | The id of the schedule to delete
    try {
      apiInstance.adminDeleteAlwaysOnSchedule(token, applicationId, alwaysOnScheduleId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminDeleteAlwaysOnSchedule");
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
 **applicationId** | **String**| The id of the application |
 **alwaysOnScheduleId** | **String**| The id of the schedule to delete |

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
**204** | The schedule was successfully deleted |  -  |
**401** | Not logged in |  -  |
**404** | Application or schedule not found |  -  |

<a name="adminDeleteApplication"></a>
# **adminDeleteApplication**
> adminDeleteApplication(token, applicationId)

Deletes an application (admin only)

Retrieves a new application from the system. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The id of the application to delete
    try {
      apiInstance.adminDeleteApplication(token, applicationId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminDeleteApplication");
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
 **applicationId** | **String**| The id of the application to delete |

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
**200** | Application deleted |  -  |
**401** | Not logged in or not an admin |  -  |
**403** | Application still has documents/annotations/models |  -  |
**404** | Application not found |  -  |

<a name="adminEditApplication"></a>
# **adminEditApplication**
> Application adminEditApplication(token, applicationId, applicationName, email, firstName, lastName, defaultJobCallbackUrl, dedicatedWorkers, dedicatedWorkersTtl, dedicatedWorkersMaxModels, reduceCallbackPayloads, alwaysOnNumber, alwaysOnStartDays, alwaysOnStartHour, alwaysOnStopHour, alwaysOnStartMinute, alwaysOnStopMinute, alwaysOnWorkersModelIds)

Modifies an existing application in the system (admin only)

Modifies an application already in the system. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to modify
    String applicationName = "applicationName_example"; // String | The new name of the application
    String email = "email_example"; // String | The new email of the application
    String firstName = "firstName_example"; // String | The new first name of the application
    String lastName = "lastName_example"; // String | The new last name of the application
    String defaultJobCallbackUrl = "defaultJobCallbackUrl_example"; // String | The new default_job_callback_url of the application
    Boolean dedicatedWorkers = true; // Boolean | Should this application use dedicated workers ?
    Integer dedicatedWorkersTtl = 56; // Integer | When using dedicated workers, TTL of the worker (in seconds)
    Integer dedicatedWorkersMaxModels = 56; // Integer | When using dedicated workers, maximum number of models loaded at the same time
    Boolean reduceCallbackPayloads = true; // Boolean | Specifies if the callback should be sent as is, or if the potential base64 encoded documents generated should be saved as sub documents of the original document
    Integer alwaysOnNumber = 56; // Integer | Adds a schedule for always on workers. Will start number always on workers when start time happens and stop them at stop time. (Check always_on_* parameters to set other parameters)
    List<Integer> alwaysOnStartDays = Arrays.asList(); // List<Integer> | Adds a schedule for always on workers. Set the day(s) of the week at which the worker will start (1 is Monday, 7 is Sunday, comma separated). Stop day will be the same day. (Check always_on_* parameters to set other parameters)
    Integer alwaysOnStartHour = 56; // Integer | Adds a schedule for always on workers. Set the hour at which the worker will start (between 0 and 23). (Check always_on_* parameters to set other parameters)
    Integer alwaysOnStopHour = 56; // Integer | Adds a schedule for always on workers. Set the hour at which the worker will stop (between 0 and 23, stop must happen after start). (Check always_on_* parameters to set other parameters)
    Integer alwaysOnStartMinute = 56; // Integer | Adds a schedule for always on workers. Set the minute at which the worker will start (between 0 and 59). (Check always_on_* parameters to set other parameters)
    Integer alwaysOnStopMinute = 56; // Integer | Adds a schedule for always on workers. Set the minute at which the worker will stop (between 0 and 59, stop must happen after start). (Check always_on_* parameters to set other parameters)
    List<String> alwaysOnWorkersModelIds = Arrays.asList(); // List<String> | When using dedicated workers with always on schedule, the models that should be loaded on start of the worker
    try {
      Application result = apiInstance.adminEditApplication(token, applicationId, applicationName, email, firstName, lastName, defaultJobCallbackUrl, dedicatedWorkers, dedicatedWorkersTtl, dedicatedWorkersMaxModels, reduceCallbackPayloads, alwaysOnNumber, alwaysOnStartDays, alwaysOnStartHour, alwaysOnStopHour, alwaysOnStartMinute, alwaysOnStopMinute, alwaysOnWorkersModelIds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminEditApplication");
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
 **applicationId** | **String**| The application to modify |
 **applicationName** | **String**| The new name of the application | [optional]
 **email** | **String**| The new email of the application | [optional]
 **firstName** | **String**| The new first name of the application | [optional]
 **lastName** | **String**| The new last name of the application | [optional]
 **defaultJobCallbackUrl** | **String**| The new default_job_callback_url of the application | [optional]
 **dedicatedWorkers** | **Boolean**| Should this application use dedicated workers ? | [optional]
 **dedicatedWorkersTtl** | **Integer**| When using dedicated workers, TTL of the worker (in seconds) | [optional]
 **dedicatedWorkersMaxModels** | **Integer**| When using dedicated workers, maximum number of models loaded at the same time | [optional]
 **reduceCallbackPayloads** | **Boolean**| Specifies if the callback should be sent as is, or if the potential base64 encoded documents generated should be saved as sub documents of the original document | [optional]
 **alwaysOnNumber** | **Integer**| Adds a schedule for always on workers. Will start number always on workers when start time happens and stop them at stop time. (Check always_on_* parameters to set other parameters) | [optional]
 **alwaysOnStartDays** | [**List&lt;Integer&gt;**](Integer.md)| Adds a schedule for always on workers. Set the day(s) of the week at which the worker will start (1 is Monday, 7 is Sunday, comma separated). Stop day will be the same day. (Check always_on_* parameters to set other parameters) | [optional]
 **alwaysOnStartHour** | **Integer**| Adds a schedule for always on workers. Set the hour at which the worker will start (between 0 and 23). (Check always_on_* parameters to set other parameters) | [optional]
 **alwaysOnStopHour** | **Integer**| Adds a schedule for always on workers. Set the hour at which the worker will stop (between 0 and 23, stop must happen after start). (Check always_on_* parameters to set other parameters) | [optional]
 **alwaysOnStartMinute** | **Integer**| Adds a schedule for always on workers. Set the minute at which the worker will start (between 0 and 59). (Check always_on_* parameters to set other parameters) | [optional]
 **alwaysOnStopMinute** | **Integer**| Adds a schedule for always on workers. Set the minute at which the worker will stop (between 0 and 59, stop must happen after start). (Check always_on_* parameters to set other parameters) | [optional]
 **alwaysOnWorkersModelIds** | [**List&lt;String&gt;**](String.md)| When using dedicated workers with always on schedule, the models that should be loaded on start of the worker | [optional]

### Return type

[**Application**](Application.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Application modified |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | Application not found |  -  |

<a name="adminGetApplication"></a>
# **adminGetApplication**
> Application adminGetApplication(token, applicationId)

Retrieves an application (admin only)

Retrieves a new application from the system. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The id of the application to retrieve
    try {
      Application result = apiInstance.adminGetApplication(token, applicationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminGetApplication");
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
 **applicationId** | **String**| The id of the application to retrieve |

### Return type

[**Application**](Application.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved application |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | Application not found |  -  |

<a name="adminGetApplications"></a>
# **adminGetApplications**
> List&lt;Application&gt; adminGetApplications(token, applicationId, email, applicationName, firstName, lastName, applicationType, createdAfter, createdBefore, dedicatedWorkers, offset, limit, sort)

Retrieves applications (admin only) (paginated)

Retrieves applications from the system. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | If specified, filters by application id
    String email = "email_example"; // String | If specified, filters by application email
    String applicationName = "applicationName_example"; // String | If specified, filters by application name
    String firstName = "firstName_example"; // String | If specified, filters by application first_name
    String lastName = "lastName_example"; // String | If specified, filters by application last_name
    ApplicationTypes applicationType = ApplicationTypes.fromValue("developer"); // ApplicationTypes | If specified, filters by application application_type
    OffsetDateTime createdAfter = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only applications created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only applications created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    Boolean dedicatedWorkers = true; // Boolean | If specified, filters by dedicated_workers value
    Integer offset = 56; // Integer | Number of the first document to send (pagination)
    Integer limit = 56; // Integer | Maximum number of documents to send (pagination)
    String sort = "sort_example"; // String | If specified, sorts the applications by a list of existing parameters separated by commas. Can be 'application_name', 'application_type', 'creation_time', 'first_name', 'last_name', 'email', 'dedicated_workers'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    try {
      List<Application> result = apiInstance.adminGetApplications(token, applicationId, email, applicationName, firstName, lastName, applicationType, createdAfter, createdBefore, dedicatedWorkers, offset, limit, sort);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminGetApplications");
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
 **applicationId** | **String**| If specified, filters by application id | [optional]
 **email** | **String**| If specified, filters by application email | [optional]
 **applicationName** | **String**| If specified, filters by application name | [optional]
 **firstName** | **String**| If specified, filters by application first_name | [optional]
 **lastName** | **String**| If specified, filters by application last_name | [optional]
 **applicationType** | [**ApplicationTypes**](.md)| If specified, filters by application application_type | [optional] [enum: developer, admin]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only applications created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only applications created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **dedicatedWorkers** | **Boolean**| If specified, filters by dedicated_workers value | [optional]
 **offset** | **Integer**| Number of the first document to send (pagination) | [optional]
 **limit** | **Integer**| Maximum number of documents to send (pagination) | [optional]
 **sort** | **String**| If specified, sorts the applications by a list of existing parameters separated by commas. Can be &#39;application_name&#39;, &#39;application_type&#39;, &#39;creation_time&#39;, &#39;first_name&#39;, &#39;last_name&#39;, &#39;email&#39;, &#39;dedicated_workers&#39;. Sorts in ascending order by default. If a parameter is preceded by &#39;-&#39;, it is sorted in descending order. | [optional]

### Return type

[**List&lt;Application&gt;**](Application.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved applications. Also contains pagination information in the headers:&lt;br /&gt; Content-Range: first-last/total&lt;br /&gt; Accept-Range: application max_limit  |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | Application not found |  -  |

<a name="adminResetApiKey"></a>
# **adminResetApiKey**
> Application adminResetApiKey(token, applicationId)

Resets an API key (admin only)

Resets the API key of the application corresponding to application_id, and returns a new one. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationAdminApi apiInstance = new ApplicationAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The id of the application to reset
    try {
      Application result = apiInstance.adminResetApiKey(token, applicationId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationAdminApi#adminResetApiKey");
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
 **applicationId** | **String**| The id of the application to reset |

### Return type

[**Application**](Application.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | API key reset |  -  |
**400** | Invalid application data |  -  |
**401** | Not logged in or not an admin |  -  |
**403** | Cannot reset your own api key |  -  |
**404** | No application found |  -  |

