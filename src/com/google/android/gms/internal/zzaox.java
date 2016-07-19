package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzaox<M extends zzaow<M>, T>
{
  protected final Class<T> bau;
  protected final boolean bic;
  public final int tag;
  protected final int type;
  
  private zzaox(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    type = paramInt1;
    bau = paramClass;
    tag = paramInt2;
    bic = paramBoolean;
  }
  
  public static <M extends zzaow<M>, T extends zzapc> zzaox<M, T> zza(int paramInt, Class<T> paramClass, long paramLong)
  {
    return new zzaox(paramInt, paramClass, (int)paramLong, false);
  }
  
  private T zzaw(List<zzape> paramList)
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      localObject = (zzape)paramList.get(i);
      if (bil.length != 0) {
        zza((zzape)localObject, localArrayList);
      }
      i += 1;
    }
    int k = localArrayList.size();
    if (k == 0)
    {
      paramList = null;
      return paramList;
    }
    Object localObject = bau.cast(Array.newInstance(bau.getComponentType(), k));
    i = j;
    for (;;)
    {
      paramList = (List<zzape>)localObject;
      if (i >= k) {
        break;
      }
      Array.set(localObject, i, localArrayList.get(i));
      i += 1;
    }
  }
  
  private T zzax(List<zzape> paramList)
  {
    if (paramList.isEmpty()) {
      return null;
    }
    paramList = (zzape)paramList.get(paramList.size() - 1);
    return (T)bau.cast(zzcf(zzaou.zzaz(bil)));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof zzaox)) {
        return false;
      }
      paramObject = (zzaox)paramObject;
    } while ((type == type) && (bau == bau) && (tag == tag) && (bic == bic));
    return false;
  }
  
  public int hashCode()
  {
    int j = type;
    int k = bau.hashCode();
    int m = tag;
    if (bic) {}
    for (int i = 1;; i = 0) {
      return i + (((j + 1147) * 31 + k) * 31 + m) * 31;
    }
  }
  
  protected void zza(zzape paramzzape, List<Object> paramList)
  {
    paramList.add(zzcf(zzaou.zzaz(bil)));
  }
  
  void zza(Object paramObject, zzaov paramzzaov)
    throws IOException
  {
    if (bic)
    {
      zzc(paramObject, paramzzaov);
      return;
    }
    zzb(paramObject, paramzzaov);
  }
  
  final T zzav(List<zzape> paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (bic) {
      return (T)zzaw(paramList);
    }
    return (T)zzax(paramList);
  }
  
  protected void zzb(Object paramObject, zzaov paramzzaov)
  {
    for (;;)
    {
      try
      {
        paramzzaov.zzaes(tag);
        switch (type)
        {
        case 10: 
          i = type;
          throw new IllegalArgumentException(24 + "Unknown type " + i);
        }
      }
      catch (IOException paramObject)
      {
        throw new IllegalStateException((Throwable)paramObject);
      }
      paramObject = (zzapc)paramObject;
      int i = zzapf.zzafa(tag);
      paramzzaov.zzb((zzapc)paramObject);
      paramzzaov.zzai(i, 4);
      return;
      paramzzaov.zzc((zzapc)paramObject);
      return;
    }
  }
  
  protected void zzc(Object paramObject, zzaov paramzzaov)
  {
    int j = Array.getLength(paramObject);
    int i = 0;
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        zzb(localObject, paramzzaov);
      }
      i += 1;
    }
  }
  
  protected Object zzcf(zzaou paramzzaou)
  {
    Object localObject;
    if (bic) {
      localObject = bau.getComponentType();
    }
    for (;;)
    {
      try
      {
        switch (type)
        {
        case 10: 
          int i = type;
          throw new IllegalArgumentException(24 + "Unknown type " + i);
        }
      }
      catch (InstantiationException paramzzaou)
      {
        localObject = String.valueOf(localObject);
        throw new IllegalArgumentException(String.valueOf(localObject).length() + 33 + "Error creating instance of class " + (String)localObject, paramzzaou);
        localObject = bau;
        continue;
        zzapc localzzapc = (zzapc)((Class)localObject).newInstance();
        paramzzaou.zza(localzzapc, zzapf.zzafa(tag));
        return localzzapc;
        localzzapc = (zzapc)((Class)localObject).newInstance();
        paramzzaou.zza(localzzapc);
        return localzzapc;
      }
      catch (IllegalAccessException paramzzaou)
      {
        localObject = String.valueOf(localObject);
        throw new IllegalArgumentException(String.valueOf(localObject).length() + 33 + "Error creating instance of class " + (String)localObject, paramzzaou);
      }
      catch (IOException paramzzaou)
      {
        throw new IllegalArgumentException("Error reading extension field", paramzzaou);
      }
    }
  }
  
  int zzcr(Object paramObject)
  {
    if (bic) {
      return zzcs(paramObject);
    }
    return zzct(paramObject);
  }
  
  protected int zzcs(Object paramObject)
  {
    int j = 0;
    int m = Array.getLength(paramObject);
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (Array.get(paramObject, i) != null) {
        k = j + zzct(Array.get(paramObject, i));
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  protected int zzct(Object paramObject)
  {
    int i = zzapf.zzafa(tag);
    switch (type)
    {
    default: 
      i = type;
      throw new IllegalArgumentException(24 + "Unknown type " + i);
    case 10: 
      return zzaov.zzb(i, (zzapc)paramObject);
    }
    return zzaov.zzc(i, (zzapc)paramObject);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaox
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */