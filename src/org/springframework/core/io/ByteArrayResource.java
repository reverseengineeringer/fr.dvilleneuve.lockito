package org.springframework.core.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ByteArrayResource
  extends AbstractResource
{
  private final byte[] byteArray;
  private final String description;
  
  public ByteArrayResource(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, "resource loaded from byte array");
  }
  
  public ByteArrayResource(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("Byte array must not be null");
    }
    byteArray = paramArrayOfByte;
    if (paramString != null) {}
    for (;;)
    {
      description = paramString;
      return;
      paramString = "";
    }
  }
  
  public long contentLength()
  {
    return byteArray.length;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof ByteArrayResource)) && (Arrays.equals(byteArray, byteArray)));
  }
  
  public boolean exists()
  {
    return true;
  }
  
  public final byte[] getByteArray()
  {
    return byteArray;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return new ByteArrayInputStream(byteArray);
  }
  
  public int hashCode()
  {
    return byte[].class.hashCode() * 29 * byteArray.length;
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.ByteArrayResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */