package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamResource
  extends AbstractResource
{
  private final String description;
  private final InputStream inputStream;
  private boolean read = false;
  
  public InputStreamResource(InputStream paramInputStream)
  {
    this(paramInputStream, "resource loaded through InputStream");
  }
  
  public InputStreamResource(InputStream paramInputStream, String paramString)
  {
    if (paramInputStream == null) {
      throw new IllegalArgumentException("InputStream must not be null");
    }
    inputStream = paramInputStream;
    if (paramString != null) {}
    for (;;)
    {
      description = paramString;
      return;
      paramString = "";
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof InputStreamResource)) && (inputStream.equals(inputStream)));
  }
  
  public boolean exists()
  {
    return true;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public InputStream getInputStream()
    throws IOException, IllegalStateException
  {
    if (read) {
      throw new IllegalStateException("InputStream has already been read - do not use InputStreamResource if a stream needs to be read multiple times");
    }
    read = true;
    return inputStream;
  }
  
  public int hashCode()
  {
    return inputStream.hashCode();
  }
  
  public boolean isOpen()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.InputStreamResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */