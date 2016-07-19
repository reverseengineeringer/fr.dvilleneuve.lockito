package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.util.ResourceUtils;

public abstract class AbstractFileResolvingResource
  extends AbstractResource
{
  public long contentLength()
    throws IOException
  {
    Object localObject = getURL();
    if (ResourceUtils.isFileURL((URL)localObject)) {
      return super.contentLength();
    }
    localObject = ((URL)localObject).openConnection();
    ((URLConnection)localObject).setUseCaches(false);
    if ((localObject instanceof HttpURLConnection)) {
      ((HttpURLConnection)localObject).setRequestMethod("HEAD");
    }
    return ((URLConnection)localObject).getContentLength();
  }
  
  public boolean exists()
  {
    int i;
    do
    {
      for (;;)
      {
        try
        {
          Object localObject1 = getURL();
          if (ResourceUtils.isFileURL((URL)localObject1)) {
            return getFile().exists();
          }
          URLConnection localURLConnection = ((URL)localObject1).openConnection();
          localURLConnection.setUseCaches(false);
          if ((localURLConnection instanceof HttpURLConnection))
          {
            localObject1 = (HttpURLConnection)localURLConnection;
            if (localObject1 != null)
            {
              ((HttpURLConnection)localObject1).setRequestMethod("HEAD");
              i = ((HttpURLConnection)localObject1).getResponseCode();
              if (i != 200) {
                break;
              }
              return true;
            }
            if (localURLConnection.getContentLength() >= 0) {
              return true;
            }
            if (localObject1 != null)
            {
              ((HttpURLConnection)localObject1).disconnect();
              return false;
            }
            getInputStream().close();
            return true;
          }
        }
        catch (IOException localIOException)
        {
          return false;
        }
        Object localObject2 = null;
      }
    } while (i != 404);
    return false;
  }
  
  public File getFile()
    throws IOException
  {
    return ResourceUtils.getFile(getURL(), getDescription());
  }
  
  protected File getFile(URI paramURI)
    throws IOException
  {
    return ResourceUtils.getFile(paramURI, getDescription());
  }
  
  protected File getFileForLastModifiedCheck()
    throws IOException
  {
    URL localURL = getURL();
    if (ResourceUtils.isJarURL(localURL)) {
      return ResourceUtils.getFile(ResourceUtils.extractJarFileURL(localURL), "Jar URL");
    }
    return getFile();
  }
  
  public boolean isReadable()
  {
    try
    {
      if (ResourceUtils.isFileURL(getURL()))
      {
        File localFile = getFile();
        if (localFile.canRead())
        {
          boolean bool = localFile.isDirectory();
          if (bool) {}
        }
      }
      else
      {
        return true;
      }
      return false;
    }
    catch (IOException localIOException) {}
    return false;
  }
  
  public long lastModified()
    throws IOException
  {
    Object localObject = getURL();
    if ((ResourceUtils.isFileURL((URL)localObject)) || (ResourceUtils.isJarURL((URL)localObject))) {
      return super.lastModified();
    }
    localObject = ((URL)localObject).openConnection();
    ((URLConnection)localObject).setUseCaches(false);
    if ((localObject instanceof HttpURLConnection)) {
      ((HttpURLConnection)localObject).setRequestMethod("HEAD");
    }
    return ((URLConnection)localObject).getLastModified();
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.AbstractFileResolvingResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */