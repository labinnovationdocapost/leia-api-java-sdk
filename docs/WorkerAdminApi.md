# WorkerAdminApi

All URIs are relative to *https://api.leia.io/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**killWorker**](WorkerAdminApi.md#killWorker) | **DELETE** /worker/{job_type} | Kills a worker (admin only)


<a name="killWorker"></a>
# **killWorker**
> killWorker(token, jobType)

Kills a worker (admin only)

Kills a worker for a given job_type. This method is only accessible to admins 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.WorkerAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    WorkerAdminApi apiInstance = new WorkerAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String jobType = "jobType_example"; // String | The job_type for which to kill a worker
    try {
      apiInstance.killWorker(token, jobType);
    } catch (ApiException e) {
      System.err.println("Exception when calling WorkerAdminApi#killWorker");
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
 **jobType** | **String**| The job_type for which to kill a worker |

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
**204** | A worker kill switch has been sent to the job queue |  -  |
**400** | Not running in worker mode |  -  |
**401** | Not logged in or not an admin |  -  |
**404** | No worker running |  -  |

