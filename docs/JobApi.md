# JobApi

All URIs are relative to *http://localhost/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelJob**](JobApi.md#cancelJob) | **DELETE** /job/{job_id} | Cancels a job in Leia API
[**getJob**](JobApi.md#getJob) | **GET** /job/{job_id} | Retrieves a job from Leia API
[**getJobs**](JobApi.md#getJobs) | **GET** /job | Retrieves jobs (paginated)


<a name="cancelJob"></a>
# **cancelJob**
> cancelJob(token, jobId)

Cancels a job in Leia API

Cancels a job in Leia API (This will not really delete it, just mark it as cancelled, so dependent jobs will fail) 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    JobApi apiInstance = new JobApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String jobId = "jobId_example"; // String | The id of the job to delete
    try {
      apiInstance.cancelJob(token, jobId);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobApi#cancelJob");
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
 **jobId** | **String**| The id of the job to delete |

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
**204** | The job was successfully canceled |  -  |
**401** | Bad token |  -  |
**403** | Not the owner of the job |  -  |
**404** | Job not found (or already canceled) |  -  |

<a name="getJob"></a>
# **getJob**
> Job getJob(token, jobId)

Retrieves a job from Leia API

Retrieves a job from Leia API 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    JobApi apiInstance = new JobApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String jobId = "jobId_example"; // String | The id of the job to retrieve
    try {
      Job result = apiInstance.getJob(token, jobId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobApi#getJob");
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
 **jobId** | **String**| The id of the job to retrieve |

### Return type

[**Job**](Job.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved job |  -  |
**401** | Bad token |  -  |
**403** | Not the owner of the job |  -  |
**404** | Job not found |  -  |

<a name="getJobs"></a>
# **getJobs**
> List&lt;Job&gt; getJobs(token, applicationId, jobType, modelId, documentId, executeAfterId, parentJobId, status, createdAfter, createdBefore, sort, offset, limit)

Retrieves jobs (paginated)

Get jobs from the system.

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    JobApi apiInstance = new JobApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The id of the owner of the documents processed by this job
    String jobType = "jobType_example"; // String | The type of the job (predict, pdf-images, image-text or transform)
    String modelId = "modelId_example"; // String | The model used by the job (only for predict jobs)
    String documentId = "documentId_example"; // String | The document that this the job is processing
    String executeAfterId = "executeAfterId_example"; // String | The job that is a prerequisite for this job to run
    String parentJobId = "parentJobId_example"; // String | The job that is the parent of this job
    String status = "status_example"; // String | The status of the job
    OffsetDateTime createdAfter = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only jobs created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only jobs created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    String sort = "sort_example"; // String | If specified, sorts the jobs by a list of existing parameters separated by commas. Can be 'submitter_id', 'application_id', 'creation_time', 'starting_time', 'finished_time', 'job_type', 'model_id', 'document_ids', 'status', 'parent_job_id'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first job to send (pagination)
    Integer limit = 56; // Integer | Maximum number of jobs to send (pagination)
    try {
      List<Job> result = apiInstance.getJobs(token, applicationId, jobType, modelId, documentId, executeAfterId, parentJobId, status, createdAfter, createdBefore, sort, offset, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobApi#getJobs");
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
 **applicationId** | **String**| The id of the owner of the documents processed by this job | [optional]
 **jobType** | **String**| The type of the job (predict, pdf-images, image-text or transform) | [optional]
 **modelId** | **String**| The model used by the job (only for predict jobs) | [optional]
 **documentId** | **String**| The document that this the job is processing | [optional]
 **executeAfterId** | **String**| The job that is a prerequisite for this job to run | [optional]
 **parentJobId** | **String**| The job that is the parent of this job | [optional]
 **status** | **String**| The status of the job | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only jobs created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only jobs created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **sort** | **String**| If specified, sorts the jobs by a list of existing parameters separated by commas. Can be &#39;submitter_id&#39;, &#39;application_id&#39;, &#39;creation_time&#39;, &#39;starting_time&#39;, &#39;finished_time&#39;, &#39;job_type&#39;, &#39;model_id&#39;, &#39;document_ids&#39;, &#39;status&#39;, &#39;parent_job_id&#39;. Sorts in ascending order by default. If a parameter is preceded by &#39;-&#39;, it is sorted in descending order. | [optional]
 **offset** | **Integer**| Number of the first job to send (pagination) | [optional]
 **limit** | **Integer**| Maximum number of jobs to send (pagination) | [optional]

### Return type

[**List&lt;Job&gt;**](Job.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved jobs. Also contains pagination information in the headers:&lt;br /&gt; Content-Range: first-last/total&lt;br /&gt; Accept-Range: job max_limit  |  -  |
**401** | Not logged in |  -  |
**404** | No jobs found |  -  |

