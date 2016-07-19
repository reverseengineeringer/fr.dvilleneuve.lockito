package org.springframework.http.converter.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

public class MappingJackson2HttpMessageConverter
  extends AbstractHttpMessageConverter<Object>
{
  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  private ObjectMapper objectMapper = new ObjectMapper();
  private boolean prefixJson = false;
  
  public MappingJackson2HttpMessageConverter()
  {
    super(new MediaType("application", "json", DEFAULT_CHARSET));
  }
  
  public boolean canRead(Class<?> paramClass, MediaType paramMediaType)
  {
    paramClass = getJavaType(paramClass);
    return (objectMapper.canDeserialize(paramClass)) && (canRead(paramMediaType));
  }
  
  public boolean canWrite(Class<?> paramClass, MediaType paramMediaType)
  {
    return (objectMapper.canSerialize(paramClass)) && (canWrite(paramMediaType));
  }
  
  protected JavaType getJavaType(Class<?> paramClass)
  {
    return objectMapper.constructType(paramClass);
  }
  
  protected JsonEncoding getJsonEncoding(MediaType paramMediaType)
  {
    if ((paramMediaType != null) && (paramMediaType.getCharSet() != null))
    {
      paramMediaType = paramMediaType.getCharSet();
      JsonEncoding[] arrayOfJsonEncoding = JsonEncoding.values();
      int j = arrayOfJsonEncoding.length;
      int i = 0;
      while (i < j)
      {
        JsonEncoding localJsonEncoding = arrayOfJsonEncoding[i];
        if (paramMediaType.name().equals(localJsonEncoding.getJavaName())) {
          return localJsonEncoding;
        }
        i += 1;
      }
    }
    return JsonEncoding.UTF8;
  }
  
  public ObjectMapper getObjectMapper()
  {
    return objectMapper;
  }
  
  protected Object readInternal(Class<?> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    paramClass = getJavaType(paramClass);
    try
    {
      paramClass = objectMapper.readValue(paramHttpInputMessage.getBody(), paramClass);
      return paramClass;
    }
    catch (IOException paramClass)
    {
      throw new HttpMessageNotReadableException("Could not read JSON: " + paramClass.getMessage(), paramClass);
    }
  }
  
  public void setObjectMapper(ObjectMapper paramObjectMapper)
  {
    Assert.notNull(paramObjectMapper, "ObjectMapper must not be null");
    objectMapper = paramObjectMapper;
  }
  
  public void setPrefixJson(boolean paramBoolean)
  {
    prefixJson = paramBoolean;
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    throw new UnsupportedOperationException();
  }
  
  protected void writeInternal(Object paramObject, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    JsonEncoding localJsonEncoding = getJsonEncoding(paramHttpOutputMessage.getHeaders().getContentType());
    paramHttpOutputMessage = objectMapper.getFactory().createJsonGenerator(paramHttpOutputMessage.getBody(), localJsonEncoding);
    try
    {
      if (prefixJson) {
        paramHttpOutputMessage.writeRaw("{} && ");
      }
      objectMapper.writeValue(paramHttpOutputMessage, paramObject);
      return;
    }
    catch (IOException paramObject)
    {
      throw new HttpMessageNotWritableException("Could not write JSON: " + ((IOException)paramObject).getMessage(), (Throwable)paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */