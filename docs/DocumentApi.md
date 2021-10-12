# DocumentApi

All URIs are relative to *https://api.leia.io/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createDocument**](DocumentApi.md#createDocument) | **POST** /document | Uploads a document to the Leia API
[**deleteDocument**](DocumentApi.md#deleteDocument) | **DELETE** /document/{document_id} | Deletes a document from Leia API
[**editDocument**](DocumentApi.md#editDocument) | **PATCH** /document/{document_id} | Updates a document in Leia API
[**getDocument**](DocumentApi.md#getDocument) | **GET** /document/{document_id} | Retrieves a document from Leia API
[**getDocumentContents**](DocumentApi.md#getDocumentContents) | **GET** /document/{document_id}/file_contents | Retrieves a document from Leia API
[**getDocuments**](DocumentApi.md#getDocuments) | **GET** /document | Retrieves documents from Leia API (paginated)
[**getDocumentsTags**](DocumentApi.md#getDocumentsTags) | **GET** /document/tag | Retrieves documents&#39; tags from Leia API
[**getDocumentsZip**](DocumentApi.md#getDocumentsZip) | **GET** /document/zip | Retrieves documents from Leia API (paginated)
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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

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
**401** | Not logged in |  -  |

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

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
**401** | Not logged in |  -  |
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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

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
**401** | Not logged in |  -  |
**404** | Document not found |  -  |

<a name="getDocument"></a>
# **getDocument**
> Document getDocument(token, documentId)

Retrieves a document from Leia API

Retrieves metadata for a document from Leia API 

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document to retrieve
    try {
      Document result = apiInstance.getDocument(token, documentId);
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
**200** | The retrieved document&#39;s metadata |  -  |
**401** | Not logged in |  -  |
**403** | Not the owner of the document |  -  |
**404** | Document not found |  -  |

<a name="getDocumentContents"></a>
# **getDocumentContents**
> File getDocumentContents(token, documentId, maxSize, jpegCompression)

Retrieves a document from Leia API

Retrieves a document&#39;s binary content from Leia API 

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id of the document to retrieve
    Integer maxSize = 56; // Integer | Restrict the size of the image to get (only applicable for documents of type image). The largest dimension of the image will be capped to this dimension
    Integer jpegCompression = 56; // Integer | JPEG compression rate, in percent (only applicable for documents of type image)
    try {
      File result = apiInstance.getDocumentContents(token, documentId, maxSize, jpegCompression);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#getDocumentContents");
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
 **maxSize** | **Integer**| Restrict the size of the image to get (only applicable for documents of type image). The largest dimension of the image will be capped to this dimension | [optional]
 **jpegCompression** | **Integer**| JPEG compression rate, in percent (only applicable for documents of type image) | [optional]

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
**200** | The retrieved document&#39;s binary contents |  -  |
**401** | Not logged in |  -  |
**403** | Not the owner of the document |  -  |
**404** | Document not found |  -  |

<a name="getDocuments"></a>
# **getDocuments**
> List&lt;Document&gt; getDocuments(token, documentId, filename, extension, mimeType, originalId, tags, createdAfter, createdBefore, tagResult, sort, offset, limit)

Retrieves documents from Leia API (paginated)

Retrieves documents which matches the query from Leia API as JSON metadata

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id the document to retrieve
    String filename = "filename_example"; // String | The file name of the documents to retrieve
    String extension = "extension_example"; // String | The extension of the documents to retrieve
    String mimeType = "mimeType_example"; // String | Filters by MIME type
    String originalId = "originalId_example"; // String | Filters by original id
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the documents by tag
    OffsetDateTime createdAfter = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only documents created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only documents created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    String tagResult = "tagResult_example"; // String | Atomically adds a tag to all retrieved values if specified. The added tag will not be returned in the result
    String sort = "sort_example"; // String | If specified, sorts the documents by a list of existing parameters separated by commas. Can be 'application_id', 'filename', 'extension', 'mime_type', 'original_id', 'page', 'creation_time'. Sorts in ascending order by default. If a parameter is preceded by '-', it is sorted in descending order.
    Integer offset = 56; // Integer | Number of the first document to send (pagination)
    Integer limit = 56; // Integer | Maximum number of documents to send (pagination)
    try {
      List<Document> result = apiInstance.getDocuments(token, documentId, filename, extension, mimeType, originalId, tags, createdAfter, createdBefore, tagResult, sort, offset, limit);
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
 **documentId** | **String**| The id the document to retrieve | [optional]
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
**200** | The retrieved documents&#39; metadata. Also contains pagination information in the headers:&lt;br /&gt; Content-Range: first-last/total&lt;br /&gt; Accept-Range: document max_limit  |  -  |
**401** | Not logged in |  -  |
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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

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
**401** | Not logged in |  -  |
**404** | Tags not found |  -  |

<a name="getDocumentsZip"></a>
# **getDocumentsZip**
> File getDocumentsZip(token, documentId, filename, extension, mimeType, originalId, tags, createdAfter, createdBefore)

Retrieves documents from Leia API (paginated)

Retrieves documents which matches the query from Leia API in a zip file

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    String documentId = "documentId_example"; // String | The id the document to retrieve
    String filename = "filename_example"; // String | The file name of the documents to retrieve
    String extension = "extension_example"; // String | The extension of the documents to retrieve
    String mimeType = "mimeType_example"; // String | Filters by MIME type
    String originalId = "originalId_example"; // String | Filters by original id
    List<String> tags = Arrays.asList(); // List<String> | If specified, filters the documents by tag
    OffsetDateTime createdAfter = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only documents created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    OffsetDateTime createdBefore = OffsetDateTime.now(); // OffsetDateTime | If specified, keeps only documents created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss)
    try {
      File result = apiInstance.getDocumentsZip(token, documentId, filename, extension, mimeType, originalId, tags, createdAfter, createdBefore);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentApi#getDocumentsZip");
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
 **documentId** | **String**| The id the document to retrieve | [optional]
 **filename** | **String**| The file name of the documents to retrieve | [optional]
 **extension** | **String**| The extension of the documents to retrieve | [optional]
 **mimeType** | **String**| Filters by MIME type | [optional]
 **originalId** | **String**| Filters by original id | [optional]
 **tags** | [**List&lt;String&gt;**](String.md)| If specified, filters the documents by tag | [optional]
 **createdAfter** | **OffsetDateTime**| If specified, keeps only documents created after given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]
 **createdBefore** | **OffsetDateTime**| If specified, keeps only documents created before given UTC timestamp (ISO 8601 format : yyyy-MM-ddThh:mm:ss) | [optional]

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
**200** | The retrieved documents in a zip file |  -  |
**401** | Not logged in |  -  |
**403** | At least of filter must be applied |  -  |
**404** | Document not found |  -  |

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

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
> Job transformDocumentAsync(token, documentIds, outputType, inputTag, outputTag, executeAfterId, pageRange, callbackUrl, transformParams, blockProcessing, transformBody)

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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    DocumentApi apiInstance = new DocumentApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    List<String> documentIds = Arrays.asList(); // List<String> | Comma separated list of document ids to process
    TransformTypes outputType = TransformTypes.fromValue("text"); // TransformTypes | The output type. May be:   * image (Extract any image from a PDF or a video)     * extract one image for each page in a PDF file     * extract one image for each frame in a video. By default only the first frame is returned, use \"image_slice\" in transform_params to specify any other frame range, view the \"page_range\" parameter for syntax of this option, use \"output_quality\" in transform_params to specify the quality of the images generated (between 1 and 100, default: 75)   * text (Use OCR on an image to get convert it to text)     * To use Google Vision OCR instead of normal LEIA OCR, add \"use_google_vision\":true in transform_params   * text_tree (text in the form of a JSON tree with information about text blocks and their position in the document)     * To use Google Vision OCR instead of normal LEIA OCR, add \"use_google_vision\":true in transform_params   * autorotate (Rotates an image that contains text so that it is in readable orientation)   * trim (Trims white space around a document)   * merge (Merge multiple text documents into a single one with a carriage return '\\n' between them)   * split (Splits a text document into chunks of 1000 words and replaces all spacing characters by single spaces (this number can be changed by setting split_size to another value in transform_params)) 
    String inputTag = "inputTag_example"; // String | The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id=document_ids[0] and that contain the specified tag
    String outputTag = "outputTag_example"; // String | The tag to add to the documents resulting from the transformation
    String executeAfterId = "executeAfterId_example"; // String | The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail
    String pageRange = "pageRange_example"; // String | The pages that should be used in previous job to process this one. Can only be used if execute_after_id is not null. Pages are indexed from 0. Syntax is the same as Python slices syntax (https://docs.python.org/3/whatsnew/2.3.html#extended-slices). Examples :   * Single positive integer : keep only this page (example 4 will keep only page 5 (Remember, pages are indexed from 0))   * Single negative integer : keep only this page, but starting from the end (example -4 will keep only page 7 if there are 10 total pages)   * Range (x:y) : keep only this range of pages (Including x but excluding y, indexed from 0)     Examples       * 2: will keep all pages starting from page 3       * :5 will keep only pages 1 to 5       * 2:5 will keep only pages 3, 4 and 5       * -4: will keep only pages 7 to 10 if there are 10 total pages)       * :-2 will keep only pages 1 to 8 if there are 10 total pages)       * -4:-2 will keep only pages 7 and 8 if there are 10 total pages)   * Stride (::w) : Keep 1 page every w pages starting at the first one (example 2 will keep only odd pages)   * Range and stride (x:y:w) : Keep 1 page every w pages within range (x:y) (example 1::2 will keep only even pages) You can use multiple ranges of page at once, comma separated (For example, 0,2:5,-2:-1 keeps the 1st page, plus pages 3->5, plus the second to last page) 
    String callbackUrl = "callbackUrl_example"; // String | Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback.
    Object transformParams = null; // Object | Free form parameters for the transformation
    Boolean blockProcessing = true; // Boolean | If true, blocks processing on the job until /job/{id}/start is called. Default is false
    TransformBody transformBody = new TransformBody(); // TransformBody | All the previous query parameters can also be passed as JSON in the body of the request
    try {
      Job result = apiInstance.transformDocumentAsync(token, documentIds, outputType, inputTag, outputTag, executeAfterId, pageRange, callbackUrl, transformParams, blockProcessing, transformBody);
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
 **outputType** | [**TransformTypes**](.md)| The output type. May be:   * image (Extract any image from a PDF or a video)     * extract one image for each page in a PDF file     * extract one image for each frame in a video. By default only the first frame is returned, use \&quot;image_slice\&quot; in transform_params to specify any other frame range, view the \&quot;page_range\&quot; parameter for syntax of this option, use \&quot;output_quality\&quot; in transform_params to specify the quality of the images generated (between 1 and 100, default: 75)   * text (Use OCR on an image to get convert it to text)     * To use Google Vision OCR instead of normal LEIA OCR, add \&quot;use_google_vision\&quot;:true in transform_params   * text_tree (text in the form of a JSON tree with information about text blocks and their position in the document)     * To use Google Vision OCR instead of normal LEIA OCR, add \&quot;use_google_vision\&quot;:true in transform_params   * autorotate (Rotates an image that contains text so that it is in readable orientation)   * trim (Trims white space around a document)   * merge (Merge multiple text documents into a single one with a carriage return &#39;\\n&#39; between them)   * split (Splits a text document into chunks of 1000 words and replaces all spacing characters by single spaces (this number can be changed by setting split_size to another value in transform_params))  | [enum: text, text_tree, image, autorotate, trim, merge, split, transcode]
 **inputTag** | **String**| The tag of the documents to process. If tag is present, document_ids should contain a single value, and the documents processed will be those where original_id&#x3D;document_ids[0] and that contain the specified tag | [optional]
 **outputTag** | **String**| The tag to add to the documents resulting from the transformation | [optional]
 **executeAfterId** | **String**| The id of a job that must be in PROCESSED status before this one can be started (used to chain jobs even before the first ones are terminated). If the referenced job becomes FAILED or is CANCELED, this one will fail | [optional]
 **pageRange** | **String**| The pages that should be used in previous job to process this one. Can only be used if execute_after_id is not null. Pages are indexed from 0. Syntax is the same as Python slices syntax (https://docs.python.org/3/whatsnew/2.3.html#extended-slices). Examples :   * Single positive integer : keep only this page (example 4 will keep only page 5 (Remember, pages are indexed from 0))   * Single negative integer : keep only this page, but starting from the end (example -4 will keep only page 7 if there are 10 total pages)   * Range (x:y) : keep only this range of pages (Including x but excluding y, indexed from 0)     Examples       * 2: will keep all pages starting from page 3       * :5 will keep only pages 1 to 5       * 2:5 will keep only pages 3, 4 and 5       * -4: will keep only pages 7 to 10 if there are 10 total pages)       * :-2 will keep only pages 1 to 8 if there are 10 total pages)       * -4:-2 will keep only pages 7 and 8 if there are 10 total pages)   * Stride (::w) : Keep 1 page every w pages starting at the first one (example 2 will keep only odd pages)   * Range and stride (x:y:w) : Keep 1 page every w pages within range (x:y) (example 1::2 will keep only even pages) You can use multiple ranges of page at once, comma separated (For example, 0,2:5,-2:-1 keeps the 1st page, plus pages 3-&gt;5, plus the second to last page)  | [optional]
 **callbackUrl** | **String**| Callback URL that should be called when the job becomes PROCESSED/FAILED/CANCELED. This URL will be called with a HTTP POST method, and the Job object as the payload. Callback server must answer with either a 200 or 204 HTTP response, to acknowledge the callback. Any other response code will be considered as a failure to call the callback. | [optional]
 **transformParams** | [**Object**](.md)| Free form parameters for the transformation | [optional]
 **blockProcessing** | **Boolean**| If true, blocks processing on the job until /job/{id}/start is called. Default is false | [optional]
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
**401** | Not logged in |  -  |
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
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

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

