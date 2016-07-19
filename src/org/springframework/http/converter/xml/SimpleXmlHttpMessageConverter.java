package org.springframework.http.converter.xml;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

public class SimpleXmlHttpMessageConverter
  extends AbstractHttpMessageConverter<Object>
{
  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  private Serializer serializer;
  
  public SimpleXmlHttpMessageConverter()
  {
    this(new Persister());
  }
  
  public SimpleXmlHttpMessageConverter(Serializer paramSerializer)
  {
    super(new MediaType[] { MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_WILDCARD_XML });
    setSerializer(paramSerializer);
  }
  
  private Charset getCharset(HttpHeaders paramHttpHeaders)
  {
    if ((paramHttpHeaders != null) && (paramHttpHeaders.getContentType() != null) && (paramHttpHeaders.getContentType().getCharSet() != null)) {
      return paramHttpHeaders.getContentType().getCharSet();
    }
    return DEFAULT_CHARSET;
  }
  
  public boolean canRead(Class<?> paramClass, MediaType paramMediaType)
  {
    return canRead(paramMediaType);
  }
  
  public boolean canWrite(Class<?> paramClass, MediaType paramMediaType)
  {
    return (paramClass.isAnnotationPresent(Root.class)) && (canWrite(paramMediaType));
  }
  
  protected Object readInternal(Class<? extends Object> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    paramHttpInputMessage = new InputStreamReader(paramHttpInputMessage.getBody(), getCharset(paramHttpInputMessage.getHeaders()));
    try
    {
      paramHttpInputMessage = serializer.read(paramClass, paramHttpInputMessage);
      if (!paramClass.isInstance(paramHttpInputMessage)) {
        throw new TypeMismatchException(paramHttpInputMessage, paramClass);
      }
    }
    catch (Exception paramHttpInputMessage)
    {
      throw new HttpMessageNotReadableException("Could not read [" + paramClass + "]", paramHttpInputMessage);
    }
    return paramHttpInputMessage;
  }
  
  public void setSerializer(Serializer paramSerializer)
  {
    Assert.notNull(paramSerializer, "'serializer' must not be null");
    serializer = paramSerializer;
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    throw new UnsupportedOperationException();
  }
  
  protected void writeInternal(Object paramObject, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    paramHttpOutputMessage = new OutputStreamWriter(paramHttpOutputMessage.getBody(), getCharset(paramHttpOutputMessage.getHeaders()));
    try
    {
      serializer.write(paramObject, paramHttpOutputMessage);
      paramHttpOutputMessage.close();
      return;
    }
    catch (Exception paramHttpOutputMessage)
    {
      throw new HttpMessageNotWritableException("Could not write [" + paramObject + "]", paramHttpOutputMessage);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */