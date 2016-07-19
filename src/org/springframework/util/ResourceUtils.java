package org.springframework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class ResourceUtils
{
  public static final String CLASSPATH_URL_PREFIX = "classpath:";
  public static final String FILE_URL_PREFIX = "file:";
  public static final String JAR_URL_SEPARATOR = "!/";
  public static final String URL_PROTOCOL_CODE_SOURCE = "code-source";
  public static final String URL_PROTOCOL_FILE = "file";
  public static final String URL_PROTOCOL_JAR = "jar";
  public static final String URL_PROTOCOL_VFS = "vfs";
  public static final String URL_PROTOCOL_VFSZIP = "vfszip";
  public static final String URL_PROTOCOL_WSJAR = "wsjar";
  public static final String URL_PROTOCOL_ZIP = "zip";
  
  public static URL extractJarFileURL(URL paramURL)
    throws MalformedURLException
  {
    String str = paramURL.getFile();
    int i = str.indexOf("!/");
    if (i != -1) {
      str = str.substring(0, i);
    }
    try
    {
      paramURL = new URL(str);
      return paramURL;
    }
    catch (MalformedURLException paramURL)
    {
      paramURL = str;
      if (!str.startsWith("/")) {
        paramURL = "/" + str;
      }
    }
    return new URL("file:" + paramURL);
  }
  
  public static File getFile(String paramString)
    throws FileNotFoundException
  {
    Assert.notNull(paramString, "Resource location must not be null");
    Object localObject;
    if (paramString.startsWith("classpath:"))
    {
      localObject = paramString.substring("classpath:".length());
      paramString = "class path resource [" + (String)localObject + "]";
      localObject = ClassUtils.getDefaultClassLoader().getResource((String)localObject);
      if (localObject == null) {
        throw new FileNotFoundException(paramString + " cannot be resolved to absolute file path " + "because it does not reside in the file system");
      }
      return getFile((URL)localObject, paramString);
    }
    try
    {
      localObject = getFile(new URL(paramString));
      return (File)localObject;
    }
    catch (MalformedURLException localMalformedURLException) {}
    return new File(paramString);
  }
  
  public static File getFile(URI paramURI)
    throws FileNotFoundException
  {
    return getFile(paramURI, "URI");
  }
  
  public static File getFile(URI paramURI, String paramString)
    throws FileNotFoundException
  {
    Assert.notNull(paramURI, "Resource URI must not be null");
    if (!"file".equals(paramURI.getScheme())) {
      throw new FileNotFoundException(paramString + " cannot be resolved to absolute file path " + "because it does not reside in the file system: " + paramURI);
    }
    return new File(paramURI.getSchemeSpecificPart());
  }
  
  public static File getFile(URL paramURL)
    throws FileNotFoundException
  {
    return getFile(paramURL, "URL");
  }
  
  public static File getFile(URL paramURL, String paramString)
    throws FileNotFoundException
  {
    Assert.notNull(paramURL, "Resource URL must not be null");
    if (!"file".equals(paramURL.getProtocol())) {
      throw new FileNotFoundException(paramString + " cannot be resolved to absolute file path " + "because it does not reside in the file system: " + paramURL);
    }
    try
    {
      paramString = new File(toURI(paramURL).getSchemeSpecificPart());
      return paramString;
    }
    catch (URISyntaxException paramString) {}
    return new File(paramURL.getFile());
  }
  
  public static URL getURL(String paramString)
    throws FileNotFoundException
  {
    Assert.notNull(paramString, "Resource location must not be null");
    URL localURL1;
    if (paramString.startsWith("classpath:"))
    {
      String str = paramString.substring("classpath:".length());
      localURL1 = ClassUtils.getDefaultClassLoader().getResource(str);
      paramString = localURL1;
      if (localURL1 != null) {
        break label102;
      }
      paramString = "class path resource [" + str + "]";
      throw new FileNotFoundException(paramString + " cannot be resolved to URL because it does not exist");
    }
    try
    {
      localURL1 = new URL(paramString);
      paramString = localURL1;
      label102:
      return paramString;
    }
    catch (MalformedURLException localMalformedURLException1)
    {
      try
      {
        URL localURL2 = new File(paramString).toURI().toURL();
        return localURL2;
      }
      catch (MalformedURLException localMalformedURLException2)
      {
        throw new FileNotFoundException("Resource location [" + paramString + "] is neither a URL not a well-formed file path");
      }
    }
  }
  
  public static boolean isFileURL(URL paramURL)
  {
    paramURL = paramURL.getProtocol();
    return ("file".equals(paramURL)) || (paramURL.startsWith("vfs"));
  }
  
  public static boolean isJarURL(URL paramURL)
  {
    String str = paramURL.getProtocol();
    return ("jar".equals(str)) || ("zip".equals(str)) || ("wsjar".equals(str)) || (("code-source".equals(str)) && (paramURL.getPath().contains("!/")));
  }
  
  public static boolean isUrl(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    if (paramString.startsWith("classpath:")) {
      return true;
    }
    try
    {
      new URL(paramString);
      return true;
    }
    catch (MalformedURLException paramString) {}
    return false;
  }
  
  public static URI toURI(String paramString)
    throws URISyntaxException
  {
    return new URI(StringUtils.replace(paramString, " ", "%20"));
  }
  
  public static URI toURI(URL paramURL)
    throws URISyntaxException
  {
    return toURI(paramURL.toString());
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ResourceUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */