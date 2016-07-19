package org.springframework.http;

import org.springframework.util.MultiValueMap;

public class ResponseEntity<T>
  extends HttpEntity<T>
{
  private final HttpStatus statusCode;
  
  public ResponseEntity(T paramT, HttpStatus paramHttpStatus)
  {
    super(paramT);
    statusCode = paramHttpStatus;
  }
  
  public ResponseEntity(T paramT, MultiValueMap<String, String> paramMultiValueMap, HttpStatus paramHttpStatus)
  {
    super(paramT, paramMultiValueMap);
    statusCode = paramHttpStatus;
  }
  
  public ResponseEntity(HttpStatus paramHttpStatus)
  {
    statusCode = paramHttpStatus;
  }
  
  public ResponseEntity(MultiValueMap<String, String> paramMultiValueMap, HttpStatus paramHttpStatus)
  {
    super(paramMultiValueMap);
    statusCode = paramHttpStatus;
  }
  
  public HttpStatus getStatusCode()
  {
    return statusCode;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("<");
    localStringBuilder.append(statusCode.toString());
    localStringBuilder.append(' ');
    localStringBuilder.append(statusCode.getReasonPhrase());
    localStringBuilder.append(',');
    Object localObject = getBody();
    HttpHeaders localHttpHeaders = getHeaders();
    if (localObject != null)
    {
      localStringBuilder.append(localObject);
      if (localHttpHeaders != null) {
        localStringBuilder.append(',');
      }
    }
    if (localHttpHeaders != null) {
      localStringBuilder.append(localHttpHeaders);
    }
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.ResponseEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */