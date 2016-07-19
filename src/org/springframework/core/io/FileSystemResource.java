package org.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class FileSystemResource
  extends AbstractResource
  implements WritableResource
{
  private final File file;
  private final String path;
  
  public FileSystemResource(File paramFile)
  {
    Assert.notNull(paramFile, "File must not be null");
    file = paramFile;
    path = StringUtils.cleanPath(paramFile.getPath());
  }
  
  public FileSystemResource(String paramString)
  {
    Assert.notNull(paramString, "Path must not be null");
    file = new File(paramString);
    path = StringUtils.cleanPath(paramString);
  }
  
  public Resource createRelative(String paramString)
  {
    return new FileSystemResource(StringUtils.applyRelativePath(path, paramString));
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof FileSystemResource)) && (path.equals(path)));
  }
  
  public boolean exists()
  {
    return file.exists();
  }
  
  public String getDescription()
  {
    return "file [" + file.getAbsolutePath() + "]";
  }
  
  public File getFile()
  {
    return file;
  }
  
  public String getFilename()
  {
    return file.getName();
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return new FileInputStream(file);
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
    return new FileOutputStream(file);
  }
  
  public final String getPath()
  {
    return path;
  }
  
  public URI getURI()
    throws IOException
  {
    return file.toURI();
  }
  
  public URL getURL()
    throws IOException
  {
    return file.toURI().toURL();
  }
  
  public int hashCode()
  {
    return path.hashCode();
  }
  
  public boolean isReadable()
  {
    return (file.canRead()) && (!file.isDirectory());
  }
  
  public boolean isWritable()
  {
    return (file.canWrite()) && (!file.isDirectory());
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.FileSystemResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */