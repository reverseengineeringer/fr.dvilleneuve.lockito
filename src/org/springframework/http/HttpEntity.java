package org.springframework.http;

import org.springframework.util.MultiValueMap;

public class HttpEntity<T>
{
  public static final HttpEntity<Object> EMPTY = new HttpEntity();
  private final T body;
  private final HttpHeaders headers;
  
  protected HttpEntity()
  {
    this(null, null);
  }
  
  public HttpEntity(T paramT)
  {
    this(paramT, null);
  }
  
  public HttpEntity(T paramT, MultiValueMap<String, String> paramMultiValueMap)
  {
    body = paramT;
    paramT = new HttpHeaders();
    if (paramMultiValueMap != null) {
      paramT.putAll(paramMultiValueMap);
    }
    headers = HttpHeaders.readOnlyHttpHeaders(paramT);
  }
  
  public HttpEntity(MultiValueMap<String, String> paramMultiValueMap)
  {
    this(null, paramMultiValueMap);
  }
  
  public T getBody()
  {
    return (T)body;
  }
  
  public HttpHeaders getHeaders()
  {
    return headers;
  }
  
  public boolean hasBody()
  {
    return body != null;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("<");
    if (body != null)
    {
      localStringBuilder.append(body);
      if (headers != null) {
        localStringBuilder.append(',');
      }
    }
    if (headers != null) {
      localStringBuilder.append(headers);
    }
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.HttpEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */