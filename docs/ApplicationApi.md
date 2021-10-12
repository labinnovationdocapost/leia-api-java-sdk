# ApplicationApi

All URIs are relative to *https://api.leia.io/leia/1.0.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**loginApplication**](ApplicationApi.md#loginApplication) | **GET** /login/{api_key} | (DEPRECATED) Logs into Leia API
[**loginApplicationPost**](ApplicationApi.md#loginApplicationPost) | **POST** /login | Logs into Leia API
[**logoutApplication**](ApplicationApi.md#logoutApplication) | **GET** /logout | Logs out from Leia API
[**whoAmI**](ApplicationApi.md#whoAmI) | **GET** /whoami | Gets the currently connected application


<a name="loginApplication"></a>
# **loginApplication**
> LoginToken loginApplication(apiKey)

(DEPRECATED) Logs into Leia API

/!\\ DEPRECATED, use POST /login instead. Logs an application into Leia API using its API key. Returns a token that will have to be set in all subsequent requests, to identify the logged in application 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationApi apiInstance = new ApplicationApi(defaultClient);
    String apiKey = "apiKey_example"; // String | The API key
    try {
      LoginToken result = apiInstance.loginApplication(apiKey);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationApi#loginApplication");
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
 **apiKey** | **String**| The API key |

### Return type

[**LoginToken**](LoginToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A token for future requests |  -  |
**403** | Bad API key |  -  |

<a name="loginApplicationPost"></a>
# **loginApplicationPost**
> LoginToken loginApplicationPost(loginBody)

Logs into Leia API

Logs an application into Leia API using its API key. Returns a token that will have to be set in all subsequent requests, to identify the logged in application 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationApi apiInstance = new ApplicationApi(defaultClient);
    LoginBody loginBody = new LoginBody(); // LoginBody | 
    try {
      LoginToken result = apiInstance.loginApplicationPost(loginBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationApi#loginApplicationPost");
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
 **loginBody** | [**LoginBody**](LoginBody.md)|  |

### Return type

[**LoginToken**](LoginToken.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | A token for future requests |  -  |
**403** | Bad API key |  -  |

<a name="logoutApplication"></a>
# **logoutApplication**
> logoutApplication(token)

Logs out from Leia API

Logs a connected application out of Leia API using its token 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationApi apiInstance = new ApplicationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    try {
      apiInstance.logoutApplication(token);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationApi#logoutApplication");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Logout OK |  -  |
**401** | Not logged in |  -  |

<a name="whoAmI"></a>
# **whoAmI**
> Application whoAmI(token)

Gets the currently connected application

Gets the currently connected application. Also use this method to reset token timeout 

### Example
```java
// Import classes:
import io.leia.client.ApiClient;
import io.leia.client.ApiException;
import io.leia.client.Configuration;
import io.leia.client.models.*;
import io.leia.client.api.ApplicationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.leia.io/leia/1.0.0");

    ApplicationApi apiInstance = new ApplicationApi(defaultClient);
    String token = "token_example"; // String | The login token obtained via GET /login/{api_key}
    try {
      Application result = apiInstance.whoAmI(token);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ApplicationApi#whoAmI");
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

[**Application**](Application.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The currently connected application |  -  |
**401** | Not logged in |  -  |

