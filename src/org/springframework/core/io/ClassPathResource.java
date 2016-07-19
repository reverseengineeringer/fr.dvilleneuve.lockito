package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ClassPathResource
  extends AbstractFileResolvingResource
{
  private ClassLoader classLoader;
  private Class<?> clazz;
  private final String path;
  
  public ClassPathResource(String paramString)
  {
    this(paramString, (ClassLoader)null);
  }
  
  public ClassPathResource(String paramString, Class<?> paramClass)
  {
    Assert.notNull(paramString, "Path must not be null");
    path = StringUtils.cleanPath(paramString);
    clazz = paramClass;
  }
  
  public ClassPathResource(String paramString, ClassLoader paramClassLoader)
  {
    Assert.notNull(paramString, "Path must not be null");
    String str = StringUtils.cleanPath(paramString);
    paramString = str;
    if (str.startsWith("/")) {
      paramString = str.substring(1);
    }
    path = paramString;
    if (paramClassLoader != null) {}
    for (;;)
    {
      classLoader = paramClassLoader;
      return;
      paramClassLoader = ClassUtils.getDefaultClassLoader();
    }
  }
  
  protected ClassPathResource(String paramString, ClassLoader paramClassLoader, Class<?> paramClass)
  {
    path = StringUtils.cleanPath(paramString);
    classLoader = paramClassLoader;
    clazz = paramClass;
  }
  
  public Resource createRelative(String paramString)
  {
    return new ClassPathResource(StringUtils.applyRelativePath(path, paramString), classLoader, clazz);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof ClassPathResource)) {
        break;
      }
      paramObject = (ClassPathResource)paramObject;
    } while ((path.equals(path)) && (ObjectUtils.nullSafeEquals(classLoader, classLoader)) && (ObjectUtils.nullSafeEquals(clazz, clazz)));
    return false;
    return false;
  }
  
  public boolean exists()
  {
    if (clazz != null) {}
    for (URL localURL = clazz.getResource(path); localURL != null; localURL = classLoader.getResource(path)) {
      return true;
    }
    return false;
  }
  
  public final ClassLoader getClassLoader()
  {
    if (classLoader != null) {
      return classLoader;
    }
    return clazz.getClassLoader();
  }
  
  public String getDescription()
  {
    StringBuilder localStringBuilder = new StringBuilder("class path resource [");
    if (clazz != null)
    {
      localStringBuilder.append(ClassUtils.classPackageAsResourcePath(clazz));
      localStringBuilder.append('/');
    }
    localStringBuilder.append(path);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public String getFilename()
  {
    return StringUtils.getFilename(path);
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    if (clazz != null) {}
    for (InputStream localInputStream = clazz.getResourceAsStream(path); localInputStream == null; localInputStream = classLoader.getResourceAsStream(path)) {
      throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
    }
    return localInputStream;
  }
  
  public final String getPath()
  {
    return path;
  }
  
  public URL getURL()
    throws IOException
  {
    if (clazz != null) {}
    for (URL localURL = clazz.getResource(path); localURL == null; localURL = classLoader.getResource(path)) {
      throw new FileNotFoundException(getDescription() + " cannot be resolved to URL because it does not exist");
    }
    return localURL;
  }
  
  public int hashCode()
  {
    return path.hashCode();
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.ClassPathResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */