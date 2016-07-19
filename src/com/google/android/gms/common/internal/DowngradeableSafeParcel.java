package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.lang.reflect.Field;

public abstract class DowngradeableSafeParcel
  extends AbstractSafeParcelable
{
  private static final Object ye = new Object();
  private static ClassLoader yf = null;
  private static Integer yg = null;
  private boolean yh = false;
  
  protected static ClassLoader zzaso()
  {
    synchronized (ye)
    {
      return null;
    }
  }
  
  protected static Integer zzasp()
  {
    synchronized (ye)
    {
      return null;
    }
  }
  
  private static boolean zzd(Class<?> paramClass)
  {
    try
    {
      boolean bool = "SAFE_PARCELABLE_NULL_STRING".equals(paramClass.getField("NULL").get(null));
      return bool;
    }
    catch (IllegalAccessException paramClass)
    {
      return false;
    }
    catch (NoSuchFieldException paramClass) {}
    return false;
  }
  
  protected static boolean zzhl(String paramString)
  {
    ClassLoader localClassLoader = zzaso();
    if (localClassLoader == null) {
      return true;
    }
    try
    {
      boolean bool = zzd(localClassLoader.loadClass(paramString));
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  protected boolean zzasq()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.DowngradeableSafeParcel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */