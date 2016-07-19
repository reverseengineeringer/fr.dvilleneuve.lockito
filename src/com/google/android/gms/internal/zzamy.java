package com.google.android.gms.internal;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class zzamy
{
  public String toString()
  {
    try
    {
      Object localObject = new StringWriter();
      zzaor localzzaor = new zzaor((Writer)localObject);
      localzzaor.setLenient(true);
      zzanz.zzb(this, localzzaor);
      localObject = ((StringWriter)localObject).toString();
      return (String)localObject;
    }
    catch (IOException localIOException)
    {
      throw new AssertionError(localIOException);
    }
  }
  
  public Number zzczg()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public String zzczh()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public double zzczi()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public long zzczj()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public int zzczk()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public boolean zzczl()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  
  public boolean zzczm()
  {
    return this instanceof zzamv;
  }
  
  public boolean zzczn()
  {
    return this instanceof zzanb;
  }
  
  public boolean zzczo()
  {
    return this instanceof zzane;
  }
  
  public boolean zzczp()
  {
    return this instanceof zzana;
  }
  
  public zzanb zzczq()
  {
    if (zzczn()) {
      return (zzanb)this;
    }
    String str = String.valueOf(this);
    throw new IllegalStateException(String.valueOf(str).length() + 19 + "Not a JSON Object: " + str);
  }
  
  public zzamv zzczr()
  {
    if (zzczm()) {
      return (zzamv)this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }
  
  public zzane zzczs()
  {
    if (zzczo()) {
      return (zzane)this;
    }
    throw new IllegalStateException("This is not a JSON Primitive.");
  }
  
  Boolean zzczt()
  {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzamy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */