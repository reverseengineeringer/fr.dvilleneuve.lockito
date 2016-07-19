package org.springframework.http.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;

class FormHttpMessageConverter$MultipartHttpOutputMessage
  implements HttpOutputMessage
{
  private final HttpHeaders headers = new HttpHeaders();
  private boolean headersWritten = false;
  private final OutputStream os;
  
  public FormHttpMessageConverter$MultipartHttpOutputMessage(FormHttpMessageConverter paramFormHttpMessageConverter, OutputStream paramOutputStream)
  {
    os = paramOutputStream;
  }
  
  private void writeHeaders()
    throws IOException
  {
    if (!headersWritten)
    {
      Iterator localIterator = headers.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (Map.Entry)localIterator.next();
        byte[] arrayOfByte1 = getAsciiBytes((String)((Map.Entry)localObject).getKey());
        localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
        while (((Iterator)localObject).hasNext())
        {
          byte[] arrayOfByte2 = getAsciiBytes((String)((Iterator)localObject).next());
          os.write(arrayOfByte1);
          os.write(58);
          os.write(32);
          os.write(arrayOfByte2);
          FormHttpMessageConverter.access$000(this$0, os);
        }
      }
      FormHttpMessageConverter.access$000(this$0, os);
      headersWritten = true;
    }
  }
  
  protected byte[] getAsciiBytes(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("US-ASCII");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new IllegalStateException(paramString);
    }
  }
  
  public OutputStream getBody()
    throws IOException
  {
    writeHeaders();
    return os;
  }
  
  public HttpHeaders getHeaders()
  {
    if (headersWritten) {
      return HttpHeaders.readOnlyHttpHeaders(headers);
    }
    return headers;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.FormHttpMessageConverter.MultipartHttpOutputMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */