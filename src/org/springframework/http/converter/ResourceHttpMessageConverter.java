package org.springframework.http.converter;

import java.io.IOException;
import java.io.OutputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

public class ResourceHttpMessageConverter
  extends AbstractHttpMessageConverter<Resource>
{
  public ResourceHttpMessageConverter()
  {
    super(MediaType.ALL);
  }
  
  protected Long getContentLength(Resource paramResource, MediaType paramMediaType)
    throws IOException
  {
    return Long.valueOf(paramResource.contentLength());
  }
  
  protected MediaType getDefaultContentType(Resource paramResource)
  {
    return MediaType.APPLICATION_OCTET_STREAM;
  }
  
  protected Resource readInternal(Class<? extends Resource> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    return new ByteArrayResource(FileCopyUtils.copyToByteArray(paramHttpInputMessage.getBody()));
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    return Resource.class.isAssignableFrom(paramClass);
  }
  
  protected void writeInternal(Resource paramResource, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    FileCopyUtils.copy(paramResource.getInputStream(), paramHttpOutputMessage.getBody());
    paramHttpOutputMessage.getBody().flush();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.ResourceHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */