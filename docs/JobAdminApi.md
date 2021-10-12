# JobAdminApi

All URIs are relative to *https://api.leia.io/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adminCancelJob**](JobAdminApi.md#adminCancelJob) | **DELETE** /admin/{submitter_id}/job/{job_id} | Cancels a job in Leia API (admin only)
[**adminCreateConditionalJob**](JobAdminApi.md#adminCreateConditionalJob) | **POST** /admin/{application_id}/job/conditional/{execute_after_id} | Asynchronously and conditionaly applies model(s) on documents (admin only)
[**adminGetJob**](JobAdminApi.md#adminGetJob) | **GET** /admin/{submitter_id}/job/{job_id} | Retrieves a job from Leia API (admin only)
[**adminGetJobStatuses**](JobAdminApi.md#adminGetJobStatuses) | **GET** /admin/{submitter_id}/job/{job_ids}/status | Retrieves job statuses from Leia API (admin only)
[**adminGetJobs**](JobAdminApi.md#adminGetJobs) | **GET** /admin/job | Retrieves jobs (admin only) (paginated)
[**adminStartJob**](JobAdminApi.md#adminStartJob) | **POST** /admin/{submitter_id}/job/{job_id}/start | Starts a job in BLOCKED status within Leia API


<a name="adminCancelJob"></a>
# **adminCancelJob**
> adminCancelJob(token, submitterId, jobId)

Cancels a job in Leia API (admin only)

Cancels a job in Leia API (This will not really delete it, just mark it as cancelled, so dependent jobs will fail). This method is only accessible to admins 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    JobAdminApi apiInstance = new JobAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String submitterId = "submitterId_example"; // String | The application which submitted the job
    String jobId = "jobId_example"; // String | The id of the job to delete
    try {
      apiInstance.adminCancelJob(token, submitterId, jobId);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobAdminApi#adminCancelJob");
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
 **submitterId** | **String**| The application which submitted the job |
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
**401** | Not logged in |  -  |
**403** | Not the owner of the job |  -  |
**404** | Job not found (or already canceled) |  -  |

<a name="adminCreateConditionalJob"></a>
# **adminCreateConditionalJob**
> Job adminCreateConditionalJob(token, applicationId, executeAfterId, callbackUrl, blockProcessing, conditionalBody)

Asynchronously and conditionaly applies model(s) on documents (admin only)

This method is only accessible to admins.&lt;br /&gt; Asynchronously runs one or more list of jobs on accessible documents and returns a Job.&lt;br /&gt; The list of jobs to run and the documents on which they should be run will be chosen depending on the rules parameter that is set in the body of the request and the result of the execute_after_id job.&lt;br /&gt; Rules should be a map[string,object] where the key is a user chosen id and the value is a list of objects containing the same parameters as normal calls to /admin/{application_id}/model/{model_id}/apply{document_ids} or /admin/{application_id}/document/{document_ids}/transform/{output_type} and a conditions field.&lt;br /&gt; If all the field/values in the conditions of a rule are contained as is in the result of the execute_after_id job, then the list of jobs will be executed in order with the given parameters, each job depending on the previous one in the list, else it won&#39;t be executed at all&lt;br /&gt; Syntax for conditions is as follows:   * \&quot;field_name\&quot; : value In which case the field field_name must be equal to the value for the job to be executed. value can be any valid json type (int, float, string...)   * \&quot;field_name\&quot;: {\&quot;operator\&quot; : value} Where operator is a [Mongo like comparison operator](https://docs.mongodb.com/manual/reference/operator/query-comparison/). In this case the comparison between field field_name&#39;s value must be true for the job to be executed. value can be any valid json type (int, float, string...)   * \&quot;field_name\&quot;: [{\&quot;operator_1\&quot; : value_1}...{\&quot;operator_n\&quot; : value_n}] Where operator_i is a [Mongo like comparison operator](https://docs.mongodb.com/manual/reference/operator/query-comparison/). In this case the comparison between field field_name&#39;s value must be true for all items in the list for the job to be executed. value_i can be any valid json type (int, float, string...). {\&quot;$eq\&quot; : value_i} can be abbreviated as value_i in the list.  You can keep the document_ids field of any job empty. If it is, the job will use the results of previous job&#39;s as an input if no tag is set, or the document_ids of the execute_after_id job + tag if tag is set.&lt;br /&gt; If the conditions are not mutually exclusive, 2 or more models may be executed.&lt;br /&gt; The result will be sent back as a map of results where the key is the rule id, and containing one entry for list of jobs that was executed. This entry will contain all the results of the executed jobs, in execution order&lt;br /&gt; This is mostly but not necessarily meant to be used after a classifier model, so that an execution path can be chosen automatically depending on the result of the classification. 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    JobAdminApi apiInstance = new JobAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String applicationId = "applicationId_example"; // String | The application to which the models belongs
    String executeAfterId = "executeAfterId_example"; // String | The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Boolean blockProcessing = true; // Boolean | If true, blocks processing on the job until /job/{id}/start is called. Default is false
    ConditionalBody conditionalBody = new ConditionalBody(); // ConditionalBody | Contains the rules to choose the model to apply. All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.adminCreateConditionalJob(token, applicationId, executeAfterId, callbackUrl, blockProcessing, conditionalBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobAdminApi#adminCreateConditionalJob");
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
 **applicationId** | **String**| The application to which the models belongs |
 **executeAfterId** | **String**| The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail |
 **callbackUrl** | **String**| Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback. | [optional]
 **blockProcessing** | **Boolean**| If true, blocks processing on the job until /job/{id}/start is called. Default is false | [optional]
 **conditionalBody** | [**ConditionalBody**](ConditionalBody.md)| Contains the rules to choose the model to apply. All the previous query parameters can also be passed as JSON in the body of the request | [optional]

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

<a name="adminGetJob"></a>
# **adminGetJob**
> Job adminGetJob(token, submitterId, jobId)

Retrieves a job from Leia API (admin only)

Retrieves a job from Leia API. This method is only accessible to admins 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    JobAdminApi apiInstance = new JobAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String submitterId = "submitterId_example"; // String | The application which submitted the job
    String jobId = "jobId_example"; // String | The id of the job to retrieve
    try {
      Job result = apiInstance.adminGetJob(token, submitterId, jobId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobAdminApi#adminGetJob");
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
 **submitterId** | **String**| The application which submitted the job |
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
**401** | Not logged in |  -  |
**403** | Not the owner of the job |  -  |
**404** | Job not found |  -  |

<a name="adminGetJobStatuses"></a>
# **adminGetJobStatuses**
> Map&lt;String, Statuses&gt; adminGetJobStatuses(token, submitterId, jobIds)

Retrieves job statuses from Leia API (admin only)

Retrieves a list of job statuses from Leia API. This method is only accessible to admins 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    JobAdminApi apiInstance = new JobAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String submitterId = "submitterId_example"; // String | The id of the submitter of the job
    List<String> jobIds = Arrays.asList(); // List<String> | The ids of the jobs to retrieve, comma separated
    try {
      Map<String, Statuses> result = apiInstance.adminGetJobStatuses(token, submitterId, jobIds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobAdminApi#adminGetJobStatuses");
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
 **submitterId** | **String**| The id of the submitter of the job |
 **jobIds** | [**List&lt;String&gt;**](String.md)| The ids of the jobs to retrieve, comma separated |

### Return type

[**Map&lt;String, Statuses&gt;**](Statuses.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved job statuses, in a map indexed by job id |  -  |
**401** | Not logged in |  -  |
**404** | Job not found |  -  |

<a name="adminGetJobs"></a>
# **adminGetJobs**
> List&lt;Job&gt; adminGetJobs(token, jobId, submitterId, applicationId, jobType, modelId, documentId, executeAfterId, parentJobId, status, createdAfter, createdBefore, sort, offset, limit)

Retrieves jobs (admin only) (paginated)

Get jobs from the system. This method is only accessible to admins

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    JobAdminApi apiInstance = new JobAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String jobId = "jobId_example"; // String | The id of the job
    String submitterId = "submitterId_example"; // String | The id of the submitter of the job
    String applicationId = "applicationId_example"; // String | The id of the owner of the documents processed by this job
    JobTypes jobType = JobTypes.fromValue("predict"); // JobTypes | The type of the job (predict, pdf-images, image-text or transform)
    String modelId = "modelId_example"; // String | The model used by the job (only for predict jobs)
    String documentId = "documentId_example"; // String | The document that this the job is processing
    String executeAfterId = "executeAfterId_example"; // String | The job that is a prerequisite for this job to run
    String parentJobId = "parentJobId_example"; // String | The job that is the parent of this job
    Statuses status = Statuses.fromValue("BLOCKED"); // Statuses | The status of the job
    OffsetDateTime createdAfter = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only jobs created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only jobs created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    String sort = "sort_example"; // String | If specified, sorts the jobs by a list of existing parameters separated by commas. Can be 'submitter_id', 'application_id', 'creation_time', 'starting_time', 'finished_time', 'job_type', 'model_id', 'document_ids', 'status', 'parent_job_id'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first job to send (pagination)
    Integer limit = 56; // Integer | Maximum number of jobs to send (pagination)
    try {
      List<Job> result = apiInstance.adminGetJobs(token, jobId, submitterId, applicationId, jobType, modelId, documentId, executeAfterId, parentJobId, status, createdAfter, createdBefore, sort, offset, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobAdminApi#adminGetJobs");
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
 **jobId** | **String**| The id of the job | [optional]
 **submitterId** | **String**| The id of the submitter of the job | [optional]
 **applicationId** | **String**| The id of the owner of the documents processed by this job | [optional]
 **jobType** | [**JobTypes**](.md)| The type of the job (predict, pdf-images, image-text or transform) | [optional] [enum: predict, pdf-images, image-text, transform]
 **modelId** | **String**| The model used by the job (only for predict jobs) | [optional]
 **documentId** | **String**| The document that this the job is processing | [optional]
 **executeAfterId** | **String**| The job that is a prerequisite for this job to run | [optional]
 **parentJobId** | **String**| The job that is the parent of this job | [optional]
 **status** | [**Statuses**](.md)| The status of the job | [optional] [enum: BLOCKED, WAITING, READY, STARTING, LOADING_MODEL, PROCESSING, PROCESSED, FAILED, CANCELED]
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

<a name="adminStartJob"></a>
# **adminStartJob**
> Job adminStartJob(token, submitterId, jobId)

Starts a job in BLOCKED status within Leia API

Triggers a job in BLOCKED status 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.JobAdminApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    JobAdminApi apiInstance = new JobAdminApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String submitterId = "submitterId_example"; // String | The application which submitted the job
    String jobId = "jobId_example"; // String | The id of the job to start
    try {
      Job result = apiInstance.adminStartJob(token, submitterId, jobId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling JobAdminApi#adminStartJob");
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
 **submitterId** | **String**| The application which submitted the job |
 **jobId** | **String**| The id of the job to start |

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
**200** | The started job |  -  |
**401** | Not logged in |  -  |
**403** | Not the owner of the job |  -  |
**404** | Job not found |  -  |

