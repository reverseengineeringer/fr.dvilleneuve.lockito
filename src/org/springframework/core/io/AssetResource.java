package org.springframework.core.io;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.util.Assert;

public class AssetResource
  extends AbstractResource
{
  private final AssetManager assetManager;
  private final String fileName;
  
  public AssetResource(AssetManager paramAssetManager, String paramString)
  {
    Assert.notNull(paramAssetManager, "assetManager must not be null");
    Assert.notNull(paramString, "fileName must not be null");
    assetManager = paramAssetManager;
    fileName = paramString;
  }
  
  public long contentLength()
    throws IOException
  {
    return assetManager.openFd(fileName).getLength();
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof AssetResource)) && (fileName.equals(fileName)));
  }
  
  public boolean exists()
  {
    boolean bool = false;
    try
    {
      InputStream localInputStream = assetManager.open(fileName);
      if (localInputStream != null) {
        bool = true;
      }
      return bool;
    }
    catch (IOException localIOException) {}
    return false;
  }
  
  public String getDescription()
  {
    return "asset [" + fileName + "]";
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    InputStream localInputStream = assetManager.open(fileName);
    if (localInputStream == null) {
      throw new FileNotFoundException(getDescription() + " cannot be opened because it does not exist");
    }
    return localInputStream;
  }
  
  public int hashCode()
  {
    return fileName.hashCode();
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.io.AssetResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */