# WorkerApi

All URIs are relative to *https://api.leia.io/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getWorker**](WorkerApi.md#getWorker) | **GET** /worker/{job_type} | Retrieves worker information from Leia API
[**getWorkers**](WorkerApi.md#getWorkers) | **GET** /worker | Retrieves worker information from Leia API


<a name="getWorker"></a>
# **getWorker**
> Worker getWorker(token, jobType)

Retrieves worker information from Leia API

Retrieves worker information from Leia API 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.WorkerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    WorkerApi apiInstance = new WorkerApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String jobType = "jobType_example"; // String | The job_type for which to get worker info
    try {
      Worker result = apiInstance.getWorker(token, jobType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WorkerApi#getWorker");
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
 **jobType** | **String**| The job_type for which to get worker info |

### Return type

[**Worker**](Worker.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved worker information |  -  |
**400** | Not running in worker mode |  -  |
**401** | Not logged in |  -  |
**404** | No worker running |  -  |

<a name="getWorkers"></a>
# **getWorkers**
> List&lt;Worker&gt; getWorkers(token)

Retrieves worker information from Leia API

Retrieves worker information from Leia API 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.WorkerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    WorkerApi apiInstance = new WorkerApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    try {
      List<Worker> result = apiInstance.getWorkers(token);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling WorkerApi#getWorkers");
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

### Return type

[**List&lt;Worker&gt;**](Worker.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved worker information |  -  |
**400** | Not running in worker mode |  -  |
**401** | Not logged in |  -  |
**404** | No worker running |  -  |

