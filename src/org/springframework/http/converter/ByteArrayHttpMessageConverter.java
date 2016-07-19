package org.springframework.http.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

public class ByteArrayHttpMessageConverter
  extends AbstractHttpMessageConverter<byte[]>
{
  public ByteArrayHttpMessageConverter()
  {
    super(new MediaType[] { new MediaType("application", "octet-stream"), MediaType.ALL });
  }
  
  protected Long getContentLength(byte[] paramArrayOfByte, MediaType paramMediaType)
  {
    return Long.valueOf(paramArrayOfByte.length);
  }
  
  public byte[] readInternal(Class<? extends byte[]> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException
  {
    long l = paramHttpInputMessage.getHeaders().getContentLength();
    if (l >= 0L)
    {
      paramClass = new ByteArrayOutputStream((int)l);
      FileCopyUtils.copy(paramHttpInputMessage.getBody(), paramClass);
      return paramClass.toByteArray();
    }
    return FileCopyUtils.copyToByteArray(paramHttpInputMessage.getBody());
  }
  
  public boolean supports(Class<?> paramClass)
  {
    return byte[].class.equals(paramClass);
  }
  
  protected void writeInternal(byte[] paramArrayOfByte, HttpOutputMessage paramHttpOutputMessage)
    throws IOException
  {
    FileCopyUtils.copy(paramArrayOfByte, paramHttpOutputMessage.getBody());
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.ByteArrayHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */