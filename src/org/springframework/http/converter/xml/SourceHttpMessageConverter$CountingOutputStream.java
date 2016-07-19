package org.springframework.http.converter.xml;

import java.io.IOException;
import java.io.OutputStream;

class SourceHttpMessageConverter$CountingOutputStream
  extends OutputStream
{
  private long count = 0L;
  
  public void write(int paramInt)
    throws IOException
  {
    count += 1L;
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    count += paramArrayOfByte.length;
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    count += paramInt2;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.xml.SourceHttpMessageConverter.CountingOutputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */