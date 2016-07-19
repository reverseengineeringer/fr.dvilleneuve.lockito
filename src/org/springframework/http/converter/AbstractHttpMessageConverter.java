package org.springframework.http.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

public abstract class AbstractHttpMessageConverter<T>
  implements HttpMessageConverter<T>
{
  private List<MediaType> supportedMediaTypes = Collections.emptyList();
  
  protected AbstractHttpMessageConverter() {}
  
  protected AbstractHttpMessageConverter(MediaType paramMediaType)
  {
    setSupportedMediaTypes(Collections.singletonList(paramMediaType));
  }
  
  protected AbstractHttpMessageConverter(MediaType... paramVarArgs)
  {
    setSupportedMediaTypes(Arrays.asList(paramVarArgs));
  }
  
  public boolean canRead(Class<?> paramClass, MediaType paramMediaType)
  {
    return (supports(paramClass)) && (canRead(paramMediaType));
  }
  
  protected boolean canRead(MediaType paramMediaType)
  {
    if (paramMediaType == null) {
      return true;
    }
    Iterator localIterator = getSupportedMediaTypes().iterator();
    while (localIterator.hasNext()) {
      if (((MediaType)localIterator.next()).includes(paramMediaType)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean canWrite(Class<?> paramClass, MediaType paramMediaType)
  {
    return (supports(paramClass)) && (canWrite(paramMediaType));
  }
  
  protected boolean canWrite(MediaType paramMediaType)
  {
    if ((paramMediaType == null) || (MediaType.ALL.equals(paramMediaType))) {
      return true;
    }
    Iterator localIterator = getSupportedMediaTypes().iterator();
    while (localIterator.hasNext()) {
      if (((MediaType)localIterator.next()).isCompatibleWith(paramMediaType)) {
        return true;
      }
    }
    return false;
  }
  
  protected Long getContentLength(T paramT, MediaType paramMediaType)
    throws IOException
  {
    return null;
  }
  
  protected MediaType getDefaultContentType(T paramT)
    throws IOException
  {
    paramT = getSupportedMediaTypes();
    if (!paramT.isEmpty()) {
      return (MediaType)paramT.get(0);
    }
    return null;
  }
  
  public List<MediaType> getSupportedMediaTypes()
  {
    return Collections.unmodifiableList(supportedMediaTypes);
  }
  
  public final T read(Class<? extends T> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException
  {
    return (T)readInternal(paramClass, paramHttpInputMessage);
  }
  
  protected abstract T readInternal(Class<? extends T> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException;
  
  public void setSupportedMediaTypes(List<MediaType> paramList)
  {
    Assert.notEmpty(paramList, "'supportedMediaTypes' must not be empty");
    supportedMediaTypes = new ArrayList(paramList);
  }
  
  protected abstract boolean supports(Class<?> paramClass);
  
  public final void write(T paramT, MediaType paramMediaType, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    HttpHeaders localHttpHeaders = paramHttpOutputMessage.getHeaders();
    if (localHttpHeaders.getContentType() == null)
    {
      MediaType localMediaType;
      if ((paramMediaType != null) && (!paramMediaType.isWildcardType()))
      {
        localMediaType = paramMediaType;
        if (!paramMediaType.isWildcardSubtype()) {}
      }
      else
      {
        localMediaType = getDefaultContentType(paramT);
      }
      if (localMediaType != null) {
        localHttpHeaders.setContentType(localMediaType);
      }
    }
    if (localHttpHeaders.getContentLength() == -1L)
    {
      paramMediaType = getContentLength(paramT, localHttpHeaders.getContentType());
      if (paramMediaType != null) {
        localHttpHeaders.setContentLength(paramMediaType.longValue());
      }
    }
    writeInternal(paramT, paramHttpOutputMessage);
    paramHttpOutputMessage.getBody().flush();
  }
  
  protected abstract void writeInternal(T paramT, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException;
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.AbstractHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */