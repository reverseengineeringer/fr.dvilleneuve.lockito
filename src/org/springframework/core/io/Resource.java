package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public abstract interface Resource
  extends InputStreamSource
{
  public abstract long contentLength()
    throws IOException;
  
  public abstract Resource createRelative(String paramString)
    throws IOException;
  
  public abstract boolean exists();
  
  public abstract String getDescription();
  
  public abstract File getFile()
    throws IOException;
  
  public abstract String getFilename();
  
  public abstract URI getURI()
    throws IOException;
  
  public abstract URL getURL()
    throws IOException;
  
  public abstract boolean isOpen();
  
  public abstract boolean isReadable();
  
  public abstract long lastModified()
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.core.io.Resource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */