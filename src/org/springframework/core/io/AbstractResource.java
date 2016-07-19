package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.springframework.core.NestedIOException;
import org.springframework.util.ResourceUtils;

public abstract class AbstractResource
  implements Resource
{
  public long contentLength()
    throws IOException
  {
    return getFile().length();
  }
  
  public Resource createRelative(String paramString)
    throws IOException
  {
    throw new FileNotFoundException("Cannot create a relative resource for " + getDescription());
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof Resource)) && (((Resource)paramObject).getDescription().equals(getDescription())));
  }
  
  public boolean exists()
  {
    try
    {
      boolean bool = getFile().exists();
      return bool;
    }
    catch (IOException localIOException)
    {
      try
      {
        getInputStream().close();
        return true;
      }
      catch (Throwable localThrowable) {}
    }
    return false;
  }
  
  public File getFile()
    throws IOException
  {
    throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path");
  }
  
  protected File getFileForLastModifiedCheck()
    throws IOException
  {
    return getFile();
  }
  
  public String getFilename()
    throws IllegalStateException
  {
    throw new IllegalStateException(getDescription() + " does not have a filename");
  }
  
  public URI getURI()
    throws IOException
  {
    URL localURL = getURL();
    try
    {
      URI localURI = ResourceUtils.toURI(localURL);
      return localURI;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new NestedIOException("Invalid URI [" + localURL + "]", localURISyntaxException);
    }
  }
  
  public URL getURL()
    throws IOException
  {
    throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
  }
  
  public int hashCode()
  {
    return getDescription().hashCode();
  }
  
  public boolean isOpen()
  {
    return false;
  }
  
  public boolean isReadable()
  {
    return true;
  }
  
  public long lastModified()
    throws IOException
  {
    long l = getFileForLastModifiedCheck().lastModified();
    if (l == 0L) {
      throw new FileNotFoundException(getDescription() + " cannot be resolved in the file system for resolving its last-modified timestamp");
    }
    return l;
  }
  
  public String toString()
  {
    return getDescription();
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.AbstractResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */