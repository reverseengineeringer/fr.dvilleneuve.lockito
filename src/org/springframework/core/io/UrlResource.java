package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class UrlResource
  extends AbstractFileResolvingResource
{
  private final URL cleanedUrl;
  private final URI uri;
  private final URL url;
  
  public UrlResource(String paramString)
    throws MalformedURLException
  {
    Assert.notNull(paramString, "Path must not be null");
    url = new URL(paramString);
    cleanedUrl = getCleanedUrl(url, paramString);
    uri = null;
  }
  
  public UrlResource(URI paramURI)
    throws MalformedURLException
  {
    Assert.notNull(paramURI, "URI must not be null");
    url = paramURI.toURL();
    cleanedUrl = getCleanedUrl(url, paramURI.toString());
    uri = paramURI;
  }
  
  public UrlResource(URL paramURL)
  {
    Assert.notNull(paramURL, "URL must not be null");
    url = paramURL;
    cleanedUrl = getCleanedUrl(url, paramURL.toString());
    uri = null;
  }
  
  private URL getCleanedUrl(URL paramURL, String paramString)
  {
    try
    {
      paramString = new URL(StringUtils.cleanPath(paramString));
      return paramString;
    }
    catch (MalformedURLException paramString) {}
    return paramURL;
  }
  
  public Resource createRelative(String paramString)
    throws MalformedURLException
  {
    String str = paramString;
    if (paramString.startsWith("/")) {
      str = paramString.substring(1);
    }
    return new UrlResource(new URL(url, str));
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof UrlResource)) && (cleanedUrl.equals(cleanedUrl)));
  }
  
  public String getDescription()
  {
    return "URL [" + url + "]";
  }
  
  public File getFile()
    throws IOException
  {
    if (uri != null) {
      return super.getFile(uri);
    }
    return super.getFile();
  }
  
  public String getFilename()
  {
    return new File(url.getFile()).getName();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    URLConnection localURLConnection = url.openConnection();
    localURLConnection.setUseCaches(false);
    try
    {
      InputStream localInputStream = localURLConnection.getInputStream();
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      if ((localURLConnection instanceof HttpURLConnection)) {
        ((HttpURLConnection)localURLConnection).disconnect();
      }
      throw localIOException;
    }
  }
  
  public URI getURI()
    throws IOException
  {
    if (uri != null) {
      return uri;
    }
    return super.getURI();
  }
  
  public URL getURL()
    throws IOException
  {
    return url;
  }
  
  public int hashCode()
  {
    return cleanedUrl.hashCode();
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.UrlResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */