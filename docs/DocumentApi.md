# DocumentApi

All URIs are relative to *http://localhost/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createDocument**](DocumentApi.md#createDocument) | **POST** /document | Uploads a document to the Leia API
[**deleteDocument**](DocumentApi.md#deleteDocument) | **DELETE** /document/{document_id} | Deletes a document from Leia API
[**editDocument**](DocumentApi.md#editDocument) | **PATCH** /document/{document_id} | Updates a document in Leia API
[**getDocument**](DocumentApi.md#getDocument) | **GET** /document/{document_id} | Retrieves a document from Leia API
[**getDocuments**](DocumentApi.md#getDocuments) | **GET** /document | Retrieves documents from Leia API (paginated)
[**getDocumentsTags**](DocumentApi.md#getDocumentsTags) | **GET** /document/tag | Retrieves documents&#39; tags from Leia API
[**tagDocument**](DocumentApi.md#tagDocument) | **POST** /document/{document_id}/tag/{tag} | Tags a document
[**transformDocumentAsync**](DocumentApi.md#transformDocumentAsync) | **POST** /document/{document_ids}/transform/{output_type} | Asynchronously converts a document within Leia API
[**untagDocument**](DocumentApi.md#untagDocument) | **DELETE** /document/{document_id}/tag/{tag} | Untags an document


<a name="createDocument"></a>
# **createDocument**
> Document createDocument(token, filename, body, b64, ttl, tags)

Uploads a document to the Leia API

Uploads a document to Leia API for future use 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String filename = "filename_example"; // String | The name of the file (if present, extension will be separated from filename in metadata of the document)
    File body = new File("/path/to/file"); // File | 
    Boolean b64 = true; // Boolean | Set to true if the body is b64 encoded
    Integer ttl = 56; // Integer | The TTL (in seconds, not less than 60) for the document (if present, the document and any sub documents, annotations, or jobs linked to it will be deleted after the TTL is expired)
    List<String> tags = Arrays.asList(); // List<String> | The tags of the document
    try {
      Document result = apiInstance.createDocument(token, filename, body, b64, ttl, tags);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#createDocument");
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
 **filename** | **String**| The name of the file (if present, extension will be separated from filename in metadata of the document) |
 **body** | **File**|  |
 **b64** | **Boolean**| Set to true if the body is b64 encoded | [optional]
 **ttl** | **Integer**| The TTL (in seconds, not less than 60) for the document (if present, the document and any sub documents, annotations, or jobs linked to it will be deleted after the TTL is expired) | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| The tags of the document | [optional]

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A description of the uploaded document |  -  |
**401** | Bad token |  -  |

<a name="deleteDocument"></a>
# **deleteDocument**
> deleteDocument(token, documentId)

Deletes a document from Leia API

Deletes a document from Leia API 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document to delete
    try {
      apiInstance.deleteDocument(token, documentId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#deleteDocument");
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
 **documentId** | **String**| The id of the document to delete |

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
**204** | The document was successfully deleted |  -  |
**401** | Bad token |  -  |
**403** | Not the owner of the document |  -  |
**404** | Document not found |  -  |

<a name="editDocument"></a>
# **editDocument**
> Document editDocument(token, documentId, filename, rotationAngle, ttl)

Updates a document in Leia API

Updates metadata for a document

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document to update
    String filename = "filename_example"; // String | The new file name of the document
    Integer rotationAngle = 56; // Integer | The new rotation angle of the document
    Integer ttl = 56; // Integer | The TTL (in seconds, not less than 60) for the document (if present, the document and any sub documents, annotations, or jobs linked to it will be deleted after the TTL is expired)
    try {
      Document result = apiInstance.editDocument(token, documentId, filename, rotationAngle, ttl);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#editDocument");
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
 **documentId** | **String**| The id of the document to update |
 **filename** | **String**| The new file name of the document | [optional]
 **rotationAngle** | **Integer**| The new rotation angle of the document | [optional]
 **ttl** | **Integer**| The TTL (in seconds, not less than 60) for the document (if present, the document and any sub documents, annotations, or jobs linked to it will be deleted after the TTL is expired) | [optional]

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The documents&#39; metadata, updated with new values |  -  |
**401** | Bad token |  -  |
**404** | Document not found |  -  |

<a name="getDocument"></a>
# **getDocument**
> Document getDocument(token, documentId, maxSize, jpegCompression, fileContents)

Retrieves a document from Leia API

Retrieves a document from Leia API, either as metadata, or the binary contents of the file 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document to retrieve
    Integer maxSize = 56; // Integer | Restrict the size of the image to get (only applicable for documents of type image). The largest dimension of the image will be capped to this dimension. Use only if file_contents=true
    Integer jpegCompression = 56; // Integer | JPEG compression rate, in percent (only applicable for documents of type image). Use only if file_contents=true
    Boolean fileContents = true; // Boolean | Should Leia API return the document binary contents, or the document metadata as JSON (false by default)
    try {
      Document result = apiInstance.getDocument(token, documentId, maxSize, jpegCompression, fileContents);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#getDocument");
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
 **documentId** | **String**| The id of the document to retrieve |
 **maxSize** | **Integer**| Restrict the size of the image to get (only applicable for documents of type image). The largest dimension of the image will be capped to this dimension. Use only if file_contents&#x3D;true | [optional]
 **jpegCompression** | **Integer**| JPEG compression rate, in percent (only applicable for documents of type image). Use only if file_contents&#x3D;true | [optional]
 **fileContents** | **Boolean**| Should Leia API return the document binary contents, or the document metadata as JSON (false by default) | [optional]

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved document&#39;s metadata, or its binary contents |  -  |
**401** | Bad token |  -  |
**403** | Not the owner of the document |  -  |
**404** | Document not found |  -  |

<a name="getDocuments"></a>
# **getDocuments**
> List&lt;Document&gt; getDocuments(token, zipfile, filename, extension, mimeType, originalId, tags, createdAfter, createdBefore, tagResult, sort, offset, limit)

Retrieves documents from Leia API (paginated)

Retrieves documents which matches the query from Leia API, either as JSON metadata or in a zip file

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    Boolean zipfile = true; // Boolean | Should Leia API return a zip file with documents, or the documents' metadata as JSON (false by default)
    String filename = "filename_example"; // String | The file name of the documents to retrieve
    String extension = "extension_example"; // String | The extension of the documents to retrieve
    String mimeType = "mimeType_example"; // String | Filters by MIME type
    String originalId = "originalId_example"; // String | Filters by original id
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the documents by tag
    OffsetDateTime createdAfter = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only documents created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = new OffsetDateTime(); // OffsetDateTime | If specified, keeps only documents created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    String tagResult = "tagResult_example"; // String | Atomically adds a tag to all retrieved values if specified. The added tag will not be returned in the result
    String sort = "sort_example"; // String | If specified, sorts the documents by a list of existing parameters separated by commas. Can be 'application_id', 'filename', 'extension', 'mime_type', 'original_id', 'page', 'creation_time'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first document to send (pagination)
    Integer limit = 56; // Integer | Maximum number of documents to send (pagination)
    try {
      List<Document> result = apiInstance.getDocuments(token, zipfile, filename, extension, mimeType, originalId, tags, createdAfter, createdBefore, tagResult, sort, offset, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#getDocuments");
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
 **zipfile** | **Boolean**| Should Leia API return a zip file with documents, or the documents&#39; metadata as JSON (false by default) | [optional]
 **filename** | **String**| The file name of the documents to retrieve | [optional]
 **extension** | **String**| The extension of the documents to retrieve | [optional]
 **mimeType** | **String**| Filters by MIME type | [optional]
 **originalId** | **String**| Filters by original id | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| If specified, filters the documents by tag | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only documents created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only documents created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **tagResult** | **String**| Atomically adds a tag to all retrieved values if specified. The added tag will not be returned in the result | [optional]
 **sort** | **String**| If specified, sorts the documents by a list of existing parameters separated by commas. Can be &#39;application_id&#39;, &#39;filename&#39;, &#39;extension&#39;, &#39;mime_type&#39;, &#39;original_id&#39;, &#39;page&#39;, &#39;creation_time&#39;. Sorts in ascending order by default. If a parameter is preceded by &#39;-&#39;, it is sorted in descending order. | [optional]
 **offset** | **Integer**| Number of the first document to send (pagination) | [optional]
 **limit** | **Integer**| Maximum number of documents to send (pagination) | [optional]

### Return type

[**List&lt;Document&gt;**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved documents&#39; metadata, or a zip file containing the documents. Also contains pagination information in the headers:&lt;br /&gt; Content-Range: first-last/total&lt;br /&gt; Accept-Range: document max_limit  |  -  |
**401** | Bad token |  -  |
**403** | At least of filter must be applied |  -  |
**404** | Document not found |  -  |

<a name="getDocumentsTags"></a>
# **getDocumentsTags**
> List&lt;String&gt; getDocumentsTags(token)

Retrieves documents&#39; tags from Leia API

Retrieves tags from documents that the application can access.

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    try {
      List<String> result = apiInstance.getDocumentsTags(token);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#getDocumentsTags");
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

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The retrieved documents&#39; tags  |  -  |
**401** | Bad token |  -  |
**404** | Tags not found |  -  |

<a name="tagDocument"></a>
# **tagDocument**
> Document tagDocument(token, documentId, tag)

Tags a document

Tags a document

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document
    String tag = "tag_example"; // String | The tag to add to the document
    try {
      Document result = apiInstance.tagDocument(token, documentId, tag);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#tagDocument");
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
 **documentId** | **String**| The id of the document |
 **tag** | **String**| The tag to add to the document |

### Return type

[**Document**](Document.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The document with the new tag |  -  |
**401** | Not logged in |  -  |
**404** | Document not found |  -  |

<a name="transformDocumentAsync"></a>
# **transformDocumentAsync**
> Job transformDocumentAsync(token, documentIds, outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, transformBody)

Asynchronously converts a document within Leia API

Asynchronously transforms a document from its current type to the output_type. May generate multiple new documents (for example converting a PDF to image will generate one new image document for each page of the PDF). Returns a Job, that will have to be polled to get the result.

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    List<String> documentIds = Arrays.asList(); // List<String> | Comma separated list of document ids to process
    String outputType = "outputType_example"; // String | The output type. May be:<ul> <li>image (extract one image for each page in a PDF file)</li> <li>text (Use OCR on an image to get convert it to text)</li> <li>text_tree (text in the form of a JSON tree with information about text blocks and their position in the document)</li> <li>autorotate (Rotates an image that contains text so that it is in readable orientation)</li> <li>trim (Trims white space around a document)</li> <li>deskew (Deskew a document)</li> </ul> 
    String inputTag = "inputTag_example"; // String | The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id=document_ids[0] and that contain the specified tag
    String outputTag = "outputTag_example"; // String | The tag to add to the documents resulting from the transformation
    String executeAfterId = "executeAfterId_example"; // String | The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Object transformParams = null; // Object | Free form parameters for the transformation
    TransformBody transformBody = new TransformBody(); // TransformBody | All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.transformDocumentAsync(token, documentIds, outputType, inputTag, outputTag, executeAfterId, callbackUrl, transformParams, transformBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#transformDocumentAsync");
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
 **documentIds** | [**List&lt;String&gt;**](String.md)| Comma separated list of document ids to process |
 **outputType** | **String**| The output type. May be:&lt;ul&gt; &lt;li&gt;image (extract one image for each page in a PDF file)&lt;/li&gt; &lt;li&gt;text (Use OCR on an image to get convert it to text)&lt;/li&gt; &lt;li&gt;text_tree (text in the form of a JSON tree with information about text blocks and their position in the document)&lt;/li&gt; &lt;li&gt;autorotate (Rotates an image that contains text so that it is in readable orientation)&lt;/li&gt; &lt;li&gt;trim (Trims white space around a document)&lt;/li&gt; &lt;li&gt;deskew (Deskew a document)&lt;/li&gt; &lt;/ul&gt;  |
 **inputTag** | **String**| The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id&#x3D;document_ids[0] and that contain the specified tag | [optional]
 **outputTag** | **String**| The tag to add to the documents resulting from the transformation | [optional]
 **executeAfterId** | **String**| The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail | [optional]
 **callbackUrl** | **String**| Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback. | [optional]
 **transformParams** | [**Object**](.md)| Free form parameters for the transformation | [optional]
 **transformBody** | [**TransformBody**](TransformBody.md)| All the previous query parameters can also be passed as JSON in the body of the request | [optional]

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
**200** | Document contents |  -  |
**401** | Bad token |  -  |
**403** | Not the owner of the document |  -  |
**404** | Document not found |  -  |

<a name="untagDocument"></a>
# **untagDocument**
> untagDocument(token, documentId, tag)

Untags an document

Untags a document

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.DocumentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document
    String tag = "tag_example"; // String | The tag to delete from the document
    try {
      apiInstance.untagDocument(token, documentId, tag);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#untagDocument");
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
 **documentId** | **String**| The id of the document |
 **tag** | **String**| The tag to delete from the document |

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
**200** | The document without the deleted tag |  -  |
**401** | Not logged in |  -  |
**404** | Document not found |  -  |

