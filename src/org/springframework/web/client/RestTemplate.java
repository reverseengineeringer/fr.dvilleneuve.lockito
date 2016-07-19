package org.springframework.web.client;

import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.InterceptingHttpAccessor;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.feed.SyndFeedHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriUtils;

public class RestTemplate
  extends InterceptingHttpAccessor
  implements RestOperations
{
  private static final String TAG = "RestTemplate";
  private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();
  private final ResponseExtractor<HttpHeaders> headersExtractor = new HeadersExtractor(null);
  private List<HttpMessageConverter<?>> messageConverters = new ArrayList();
  
  public RestTemplate()
  {
    this(false);
  }
  
  public RestTemplate(ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    this(false, paramClientHttpRequestFactory);
  }
  
  public RestTemplate(boolean paramBoolean)
  {
    if (paramBoolean) {
      DefaultMessageConverters.init(messageConverters);
    }
  }
  
  public RestTemplate(boolean paramBoolean, ClientHttpRequestFactory paramClientHttpRequestFactory)
  {
    this(paramBoolean);
    setRequestFactory(paramClientHttpRequestFactory);
  }
  
  private void handleResponseError(HttpMethod paramHttpMethod, URI paramURI, ClientHttpResponse paramClientHttpResponse)
    throws IOException
  {
    if (Log.isLoggable("RestTemplate", 5)) {}
    try
    {
      Log.w("RestTemplate", paramHttpMethod.name() + " request for \"" + paramURI + "\" resulted in " + paramClientHttpResponse.getStatusCode() + " (" + paramClientHttpResponse.getStatusText() + "); invoking error handler");
      getErrorHandler().handleError(paramClientHttpResponse);
      return;
    }
    catch (IOException paramHttpMethod)
    {
      for (;;) {}
    }
  }
  
  private void logResponseStatus(HttpMethod paramHttpMethod, URI paramURI, ClientHttpResponse paramClientHttpResponse)
  {
    if (Log.isLoggable("RestTemplate", 3)) {}
    try
    {
      Log.d("RestTemplate", paramHttpMethod.name() + " request for \"" + paramURI + "\" resulted in " + paramClientHttpResponse.getStatusCode() + " (" + paramClientHttpResponse.getStatusText() + ")");
      return;
    }
    catch (IOException paramHttpMethod) {}
  }
  
  public void delete(String paramString, Map<String, ?> paramMap)
    throws RestClientException
  {
    execute(paramString, HttpMethod.DELETE, null, null, paramMap);
  }
  
  public void delete(String paramString, Object... paramVarArgs)
    throws RestClientException
  {
    execute(paramString, HttpMethod.DELETE, null, null, paramVarArgs);
  }
  
  public void delete(URI paramURI)
    throws RestClientException
  {
    execute(paramURI, HttpMethod.DELETE, null, null);
  }
  
  protected <T> T doExecute(URI paramURI, HttpMethod paramHttpMethod, RequestCallback paramRequestCallback, ResponseExtractor<T> paramResponseExtractor)
    throws RestClientException
  {
    Assert.notNull(paramURI, "'url' must not be null");
    Assert.notNull(paramHttpMethod, "'method' must not be null");
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    do
    {
      try
      {
        ClientHttpRequest localClientHttpRequest = createRequest(paramURI, paramHttpMethod);
        if (paramRequestCallback != null)
        {
          localObject2 = localObject3;
          localObject1 = localObject4;
          paramRequestCallback.doWithRequest(localClientHttpRequest);
        }
        localObject2 = localObject3;
        localObject1 = localObject4;
        paramRequestCallback = localClientHttpRequest.execute();
        localObject2 = paramRequestCallback;
        localObject1 = paramRequestCallback;
        if (!getErrorHandler().hasError(paramRequestCallback))
        {
          localObject2 = paramRequestCallback;
          localObject1 = paramRequestCallback;
          logResponseStatus(paramHttpMethod, paramURI, paramRequestCallback);
        }
        while (paramResponseExtractor != null)
        {
          localObject2 = paramRequestCallback;
          localObject1 = paramRequestCallback;
          paramURI = paramResponseExtractor.extractData(paramRequestCallback);
          paramHttpMethod = paramURI;
          if (paramRequestCallback != null)
          {
            paramRequestCallback.close();
            paramHttpMethod = paramURI;
          }
          return paramHttpMethod;
          localObject2 = paramRequestCallback;
          localObject1 = paramRequestCallback;
          handleResponseError(paramHttpMethod, paramURI, paramRequestCallback);
        }
        paramHttpMethod = null;
      }
      catch (IOException paramURI)
      {
        localObject1 = localObject2;
        throw new ResourceAccessException("I/O error: " + paramURI.getMessage(), paramURI);
      }
      finally
      {
        if (localObject1 != null) {
          ((ClientHttpResponse)localObject1).close();
        }
      }
    } while (paramRequestCallback == null);
    paramRequestCallback.close();
    return null;
  }
  
  public <T> ResponseEntity<T> exchange(String paramString, HttpMethod paramHttpMethod, HttpEntity<?> paramHttpEntity, Class<T> paramClass, Map<String, ?> paramMap)
    throws RestClientException
  {
    return (ResponseEntity)execute(paramString, paramHttpMethod, new HttpEntityRequestCallback(paramHttpEntity, paramClass, null), new ResponseEntityResponseExtractor(paramClass), paramMap);
  }
  
  public <T> ResponseEntity<T> exchange(String paramString, HttpMethod paramHttpMethod, HttpEntity<?> paramHttpEntity, Class<T> paramClass, Object... paramVarArgs)
    throws RestClientException
  {
    return (ResponseEntity)execute(paramString, paramHttpMethod, new HttpEntityRequestCallback(paramHttpEntity, paramClass, null), new ResponseEntityResponseExtractor(paramClass), paramVarArgs);
  }
  
  public <T> ResponseEntity<T> exchange(URI paramURI, HttpMethod paramHttpMethod, HttpEntity<?> paramHttpEntity, Class<T> paramClass)
    throws RestClientException
  {
    return (ResponseEntity)execute(paramURI, paramHttpMethod, new HttpEntityRequestCallback(paramHttpEntity, paramClass, null), new ResponseEntityResponseExtractor(paramClass));
  }
  
  public <T> T execute(String paramString, HttpMethod paramHttpMethod, RequestCallback paramRequestCallback, ResponseExtractor<T> paramResponseExtractor, Map<String, ?> paramMap)
    throws RestClientException
  {
    return (T)doExecute(new HttpUrlTemplate(paramString).expand(paramMap), paramHttpMethod, paramRequestCallback, paramResponseExtractor);
  }
  
  public <T> T execute(String paramString, HttpMethod paramHttpMethod, RequestCallback paramRequestCallback, ResponseExtractor<T> paramResponseExtractor, Object... paramVarArgs)
    throws RestClientException
  {
    return (T)doExecute(new HttpUrlTemplate(paramString).expand(paramVarArgs), paramHttpMethod, paramRequestCallback, paramResponseExtractor);
  }
  
  public <T> T execute(URI paramURI, HttpMethod paramHttpMethod, RequestCallback paramRequestCallback, ResponseExtractor<T> paramResponseExtractor)
    throws RestClientException
  {
    return (T)doExecute(paramURI, paramHttpMethod, paramRequestCallback, paramResponseExtractor);
  }
  
  public ResponseErrorHandler getErrorHandler()
  {
    return errorHandler;
  }
  
  public <T> ResponseEntity<T> getForEntity(String paramString, Class<T> paramClass, Map<String, ?> paramMap)
    throws RestClientException
  {
    AcceptHeaderRequestCallback localAcceptHeaderRequestCallback = new AcceptHeaderRequestCallback(paramClass, null);
    paramClass = new ResponseEntityResponseExtractor(paramClass);
    return (ResponseEntity)execute(paramString, HttpMethod.GET, localAcceptHeaderRequestCallback, paramClass, paramMap);
  }
  
  public <T> ResponseEntity<T> getForEntity(String paramString, Class<T> paramClass, Object... paramVarArgs)
    throws RestClientException
  {
    AcceptHeaderRequestCallback localAcceptHeaderRequestCallback = new AcceptHeaderRequestCallback(paramClass, null);
    paramClass = new ResponseEntityResponseExtractor(paramClass);
    return (ResponseEntity)execute(paramString, HttpMethod.GET, localAcceptHeaderRequestCallback, paramClass, paramVarArgs);
  }
  
  public <T> ResponseEntity<T> getForEntity(URI paramURI, Class<T> paramClass)
    throws RestClientException
  {
    AcceptHeaderRequestCallback localAcceptHeaderRequestCallback = new AcceptHeaderRequestCallback(paramClass, null);
    paramClass = new ResponseEntityResponseExtractor(paramClass);
    return (ResponseEntity)execute(paramURI, HttpMethod.GET, localAcceptHeaderRequestCallback, paramClass);
  }
  
  public <T> T getForObject(String paramString, Class<T> paramClass, Map<String, ?> paramMap)
    throws RestClientException
  {
    AcceptHeaderRequestCallback localAcceptHeaderRequestCallback = new AcceptHeaderRequestCallback(paramClass, null);
    paramClass = new HttpMessageConverterExtractor(paramClass, getMessageConverters());
    return (T)execute(paramString, HttpMethod.GET, localAcceptHeaderRequestCallback, paramClass, paramMap);
  }
  
  public <T> T getForObject(String paramString, Class<T> paramClass, Object... paramVarArgs)
    throws RestClientException
  {
    AcceptHeaderRequestCallback localAcceptHeaderRequestCallback = new AcceptHeaderRequestCallback(paramClass, null);
    paramClass = new HttpMessageConverterExtractor(paramClass, getMessageConverters());
    return (T)execute(paramString, HttpMethod.GET, localAcceptHeaderRequestCallback, paramClass, paramVarArgs);
  }
  
  public <T> T getForObject(URI paramURI, Class<T> paramClass)
    throws RestClientException
  {
    AcceptHeaderRequestCallback localAcceptHeaderRequestCallback = new AcceptHeaderRequestCallback(paramClass, null);
    paramClass = new HttpMessageConverterExtractor(paramClass, getMessageConverters());
    return (T)execute(paramURI, HttpMethod.GET, localAcceptHeaderRequestCallback, paramClass);
  }
  
  public List<HttpMessageConverter<?>> getMessageConverters()
  {
    return messageConverters;
  }
  
  public HttpHeaders headForHeaders(String paramString, Map<String, ?> paramMap)
    throws RestClientException
  {
    return (HttpHeaders)execute(paramString, HttpMethod.HEAD, null, headersExtractor, paramMap);
  }
  
  public HttpHeaders headForHeaders(String paramString, Object... paramVarArgs)
    throws RestClientException
  {
    return (HttpHeaders)execute(paramString, HttpMethod.HEAD, null, headersExtractor, paramVarArgs);
  }
  
  public HttpHeaders headForHeaders(URI paramURI)
    throws RestClientException
  {
    return (HttpHeaders)execute(paramURI, HttpMethod.HEAD, null, headersExtractor);
  }
  
  public Set<HttpMethod> optionsForAllow(String paramString, Map<String, ?> paramMap)
    throws RestClientException
  {
    return ((HttpHeaders)execute(paramString, HttpMethod.OPTIONS, null, headersExtractor, paramMap)).getAllow();
  }
  
  public Set<HttpMethod> optionsForAllow(String paramString, Object... paramVarArgs)
    throws RestClientException
  {
    return ((HttpHeaders)execute(paramString, HttpMethod.OPTIONS, null, headersExtractor, paramVarArgs)).getAllow();
  }
  
  public Set<HttpMethod> optionsForAllow(URI paramURI)
    throws RestClientException
  {
    return ((HttpHeaders)execute(paramURI, HttpMethod.OPTIONS, null, headersExtractor)).getAllow();
  }
  
  public <T> ResponseEntity<T> postForEntity(String paramString, Object paramObject, Class<T> paramClass, Map<String, ?> paramMap)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, paramClass, null);
    paramClass = new ResponseEntityResponseExtractor(paramClass);
    return (ResponseEntity)execute(paramString, HttpMethod.POST, (RequestCallback)paramObject, paramClass, paramMap);
  }
  
  public <T> ResponseEntity<T> postForEntity(String paramString, Object paramObject, Class<T> paramClass, Object... paramVarArgs)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, paramClass, null);
    paramClass = new ResponseEntityResponseExtractor(paramClass);
    return (ResponseEntity)execute(paramString, HttpMethod.POST, (RequestCallback)paramObject, paramClass, paramVarArgs);
  }
  
  public <T> ResponseEntity<T> postForEntity(URI paramURI, Object paramObject, Class<T> paramClass)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, paramClass, null);
    paramClass = new ResponseEntityResponseExtractor(paramClass);
    return (ResponseEntity)execute(paramURI, HttpMethod.POST, (RequestCallback)paramObject, paramClass);
  }
  
  public URI postForLocation(String paramString, Object paramObject, Map<String, ?> paramMap)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, null);
    return ((HttpHeaders)execute(paramString, HttpMethod.POST, (RequestCallback)paramObject, headersExtractor, paramMap)).getLocation();
  }
  
  public URI postForLocation(String paramString, Object paramObject, Object... paramVarArgs)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, null);
    return ((HttpHeaders)execute(paramString, HttpMethod.POST, (RequestCallback)paramObject, headersExtractor, paramVarArgs)).getLocation();
  }
  
  public URI postForLocation(URI paramURI, Object paramObject)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, null);
    return ((HttpHeaders)execute(paramURI, HttpMethod.POST, (RequestCallback)paramObject, headersExtractor)).getLocation();
  }
  
  public <T> T postForObject(String paramString, Object paramObject, Class<T> paramClass, Map<String, ?> paramMap)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, paramClass, null);
    paramClass = new HttpMessageConverterExtractor(paramClass, getMessageConverters());
    return (T)execute(paramString, HttpMethod.POST, (RequestCallback)paramObject, paramClass, paramMap);
  }
  
  public <T> T postForObject(String paramString, Object paramObject, Class<T> paramClass, Object... paramVarArgs)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, paramClass, null);
    paramClass = new HttpMessageConverterExtractor(paramClass, getMessageConverters());
    return (T)execute(paramString, HttpMethod.POST, (RequestCallback)paramObject, paramClass, paramVarArgs);
  }
  
  public <T> T postForObject(URI paramURI, Object paramObject, Class<T> paramClass)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, paramClass, null);
    paramClass = new HttpMessageConverterExtractor(paramClass, getMessageConverters());
    return (T)execute(paramURI, HttpMethod.POST, (RequestCallback)paramObject, paramClass);
  }
  
  public void put(String paramString, Object paramObject, Map<String, ?> paramMap)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, null);
    execute(paramString, HttpMethod.PUT, (RequestCallback)paramObject, null, paramMap);
  }
  
  public void put(String paramString, Object paramObject, Object... paramVarArgs)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, null);
    execute(paramString, HttpMethod.PUT, (RequestCallback)paramObject, null, paramVarArgs);
  }
  
  public void put(URI paramURI, Object paramObject)
    throws RestClientException
  {
    paramObject = new HttpEntityRequestCallback(paramObject, null);
    execute(paramURI, HttpMethod.PUT, (RequestCallback)paramObject, null);
  }
  
  public void setErrorHandler(ResponseErrorHandler paramResponseErrorHandler)
  {
    Assert.notNull(paramResponseErrorHandler, "'errorHandler' must not be null");
    errorHandler = paramResponseErrorHandler;
  }
  
  public void setMessageConverters(List<HttpMessageConverter<?>> paramList)
  {
    Assert.notEmpty(paramList, "'messageConverters' must not be empty");
    messageConverters = paramList;
  }
  
  private class AcceptHeaderRequestCallback
    implements RequestCallback
  {
    private final Class<?> responseType;
    
    private AcceptHeaderRequestCallback()
    {
      Class localClass;
      responseType = localClass;
    }
    
    public void doWithRequest(ClientHttpRequest paramClientHttpRequest)
      throws IOException
    {
      if (responseType != null)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator1 = getMessageConverters().iterator();
        while (localIterator1.hasNext())
        {
          Object localObject = (HttpMessageConverter)localIterator1.next();
          if (((HttpMessageConverter)localObject).canRead(responseType, null))
          {
            Iterator localIterator2 = ((HttpMessageConverter)localObject).getSupportedMediaTypes().iterator();
            while (localIterator2.hasNext())
            {
              MediaType localMediaType = (MediaType)localIterator2.next();
              localObject = localMediaType;
              if (localMediaType.getCharSet() != null) {
                localObject = new MediaType(localMediaType.getType(), localMediaType.getSubtype());
              }
              localArrayList.add(localObject);
            }
          }
        }
        if (!localArrayList.isEmpty())
        {
          MediaType.sortBySpecificity(localArrayList);
          if (Log.isLoggable("RestTemplate", 3)) {
            Log.d("RestTemplate", "Setting request Accept header to " + localArrayList);
          }
          paramClientHttpRequest.getHeaders().setAccept(localArrayList);
        }
      }
    }
  }
  
  private static class DefaultMessageConverters
  {
    private static final boolean jackson2Present;
    private static final boolean jacksonPresent;
    private static final boolean javaxXmlTransformPresent;
    private static final boolean romePresent;
    private static final boolean simpleXmlPresent;
    
    static
    {
      boolean bool2 = true;
      javaxXmlTransformPresent = ClassUtils.isPresent("javax.xml.transform.Source", RestTemplate.class.getClassLoader());
      simpleXmlPresent = ClassUtils.isPresent("org.simpleframework.xml.Serializer", RestTemplate.class.getClassLoader());
      if ((ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", RestTemplate.class.getClassLoader())) && (ClassUtils.isPresent("org.codehaus.jackson.JsonGenerator", RestTemplate.class.getClassLoader())))
      {
        bool1 = true;
        jacksonPresent = bool1;
        if ((!ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", RestTemplate.class.getClassLoader())) || (!ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", RestTemplate.class.getClassLoader()))) {
          break label111;
        }
      }
      label111:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        jackson2Present = bool1;
        romePresent = ClassUtils.isPresent("com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed", RestTemplate.class.getClassLoader());
        return;
        bool1 = false;
        break;
      }
    }
    
    public static void init(List<HttpMessageConverter<?>> paramList)
    {
      paramList.add(new ByteArrayHttpMessageConverter());
      paramList.add(new StringHttpMessageConverter());
      paramList.add(new ResourceHttpMessageConverter());
      if (javaxXmlTransformPresent)
      {
        paramList.add(new SourceHttpMessageConverter());
        paramList.add(new XmlAwareFormHttpMessageConverter());
        if (simpleXmlPresent) {
          paramList.add(new SimpleXmlHttpMessageConverter());
        }
        if (!jackson2Present) {
          break label154;
        }
        paramList.add(new MappingJackson2HttpMessageConverter());
      }
      for (;;)
      {
        if (romePresent) {
          paramList.add(new SyndFeedHttpMessageConverter());
        }
        return;
        paramList.add(new FormHttpMessageConverter());
        break;
        label154:
        if (jacksonPresent) {
          paramList.add(new MappingJacksonHttpMessageConverter());
        }
      }
    }
  }
  
  private static class HeadersExtractor
    implements ResponseExtractor<HttpHeaders>
  {
    public HttpHeaders extractData(ClientHttpResponse paramClientHttpResponse)
      throws IOException
    {
      return paramClientHttpResponse.getHeaders();
    }
  }
  
  private class HttpEntityRequestCallback
    extends RestTemplate.AcceptHeaderRequestCallback
  {
    private final HttpEntity<Object> requestEntity;
    
    private HttpEntityRequestCallback(Object paramObject)
    {
      this(paramObject, null);
    }
    
    private HttpEntityRequestCallback(Class<?> paramClass)
    {
      super(localClass, null);
      if ((paramClass instanceof HttpEntity))
      {
        requestEntity = ((HttpEntity)paramClass);
        return;
      }
      if (paramClass != null)
      {
        requestEntity = new HttpEntity(paramClass);
        return;
      }
      requestEntity = HttpEntity.EMPTY;
    }
    
    public void doWithRequest(ClientHttpRequest paramClientHttpRequest)
      throws IOException
    {
      super.doWithRequest(paramClientHttpRequest);
      if (!requestEntity.hasBody())
      {
        paramClientHttpRequest = paramClientHttpRequest.getHeaders();
        localObject = requestEntity.getHeaders();
        if (!((HttpHeaders)localObject).isEmpty()) {
          paramClientHttpRequest.putAll((Map)localObject);
        }
        if (paramClientHttpRequest.getContentLength() == -1L) {
          paramClientHttpRequest.setContentLength(0L);
        }
        return;
      }
      Object localObject = requestEntity.getBody();
      Class localClass = localObject.getClass();
      HttpHeaders localHttpHeaders = requestEntity.getHeaders();
      MediaType localMediaType = localHttpHeaders.getContentType();
      Iterator localIterator = getMessageConverters().iterator();
      while (localIterator.hasNext())
      {
        HttpMessageConverter localHttpMessageConverter = (HttpMessageConverter)localIterator.next();
        if (localHttpMessageConverter.canWrite(localClass, localMediaType))
        {
          if (!localHttpHeaders.isEmpty()) {
            paramClientHttpRequest.getHeaders().putAll(localHttpHeaders);
          }
          if (Log.isLoggable("RestTemplate", 3))
          {
            if (localMediaType == null) {
              break label229;
            }
            Log.d("RestTemplate", "Writing [" + localObject + "] as \"" + localMediaType + "\" using [" + localHttpMessageConverter + "]");
          }
          for (;;)
          {
            localHttpMessageConverter.write(localObject, localMediaType, paramClientHttpRequest);
            return;
            label229:
            Log.d("RestTemplate", "Writing [" + localObject + "] using [" + localHttpMessageConverter + "]");
          }
        }
      }
      localObject = "Could not write request: no suitable HttpMessageConverter found for request type [" + localClass.getName() + "]";
      paramClientHttpRequest = (ClientHttpRequest)localObject;
      if (localMediaType != null) {
        paramClientHttpRequest = (String)localObject + " and content type [" + localMediaType + "]";
      }
      throw new RestClientException(paramClientHttpRequest);
    }
  }
  
  private static class HttpUrlTemplate
    extends UriTemplate
  {
    private static final long serialVersionUID = 1L;
    
    public HttpUrlTemplate(String paramString)
    {
      super();
    }
    
    protected URI encodeUri(String paramString)
    {
      try
      {
        URI localURI = new URI(UriUtils.encodeHttpUrl(paramString, "UTF-8"));
        return localURI;
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new IllegalStateException(paramString);
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new IllegalArgumentException("Could not create HTTP URL from [" + paramString + "]: " + localURISyntaxException, localURISyntaxException);
      }
    }
  }
  
  private class ResponseEntityResponseExtractor<T>
    implements ResponseExtractor<ResponseEntity<T>>
  {
    private final HttpMessageConverterExtractor<T> delegate;
    
    public ResponseEntityResponseExtractor()
    {
      Object localObject;
      if ((localObject != null) && (!Void.class.equals(localObject)))
      {
        delegate = new HttpMessageConverterExtractor((Class)localObject, getMessageConverters());
        return;
      }
      delegate = null;
    }
    
    public ResponseEntity<T> extractData(ClientHttpResponse paramClientHttpResponse)
      throws IOException
    {
      if (delegate != null) {
        return new ResponseEntity(delegate.extractData(paramClientHttpResponse), paramClientHttpResponse.getHeaders(), paramClientHttpResponse.getStatusCode());
      }
      return new ResponseEntity(paramClientHttpResponse.getHeaders(), paramClientHttpResponse.getStatusCode());
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */