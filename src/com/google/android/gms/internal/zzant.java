package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class zzant
  implements zzanl, Cloneable
{
  public static final zzant beU = new zzant();
  private double beV = -1.0D;
  private int beW = 136;
  private boolean beX = true;
  private List<zzamo> beY = Collections.emptyList();
  private List<zzamo> beZ = Collections.emptyList();
  
  private boolean zza(zzano paramzzano)
  {
    return (paramzzano == null) || (paramzzano.zzczz() <= beV);
  }
  
  private boolean zza(zzano paramzzano, zzanp paramzzanp)
  {
    return (zza(paramzzano)) && (zza(paramzzanp));
  }
  
  private boolean zza(zzanp paramzzanp)
  {
    return (paramzzanp == null) || (paramzzanp.zzczz() > beV);
  }
  
  private boolean zzm(Class<?> paramClass)
  {
    return (!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()));
  }
  
  private boolean zzn(Class<?> paramClass)
  {
    return (paramClass.isMemberClass()) && (!zzo(paramClass));
  }
  
  private boolean zzo(Class<?> paramClass)
  {
    return (paramClass.getModifiers() & 0x8) != 0;
  }
  
  protected zzant b()
  {
    try
    {
      zzant localzzant = (zzant)super.clone();
      return localzzant;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  public <T> zzank<T> zza(final zzams paramzzams, final zzaoo<T> paramzzaoo)
  {
    Class localClass = paramzzaoo.s();
    final boolean bool1 = zza(localClass, true);
    final boolean bool2 = zza(localClass, false);
    if ((!bool1) && (!bool2)) {
      return null;
    }
    new zzank()
    {
      private zzank<T> bej;
      
      private zzank<T> zzczx()
      {
        zzank localzzank = bej;
        if (localzzank != null) {
          return localzzank;
        }
        localzzank = paramzzams.zza(zzant.this, paramzzaoo);
        bej = localzzank;
        return localzzank;
      }
      
      public void zza(zzaor paramAnonymouszzaor, T paramAnonymousT)
        throws IOException
      {
        if (bool1)
        {
          paramAnonymouszzaor.r();
          return;
        }
        zzczx().zza(paramAnonymouszzaor, paramAnonymousT);
      }
      
      public T zzb(zzaop paramAnonymouszzaop)
        throws IOException
      {
        if (bool2)
        {
          paramAnonymouszzaop.skipValue();
          return null;
        }
        return (T)zzczx().zzb(paramAnonymouszzaop);
      }
    };
  }
  
  public zzant zza(zzamo paramzzamo, boolean paramBoolean1, boolean paramBoolean2)
  {
    zzant localzzant = b();
    if (paramBoolean1)
    {
      beY = new ArrayList(beY);
      beY.add(paramzzamo);
    }
    if (paramBoolean2)
    {
      beZ = new ArrayList(beZ);
      beZ.add(paramzzamo);
    }
    return localzzant;
  }
  
  public boolean zza(Class<?> paramClass, boolean paramBoolean)
  {
    if ((beV != -1.0D) && (!zza((zzano)paramClass.getAnnotation(zzano.class), (zzanp)paramClass.getAnnotation(zzanp.class)))) {
      return true;
    }
    if ((!beX) && (zzn(paramClass))) {
      return true;
    }
    if (zzm(paramClass)) {
      return true;
    }
    if (paramBoolean) {}
    for (Object localObject = beY;; localObject = beZ)
    {
      localObject = ((List)localObject).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((zzamo)((Iterator)localObject).next()).zzh(paramClass));
      return true;
    }
    return false;
  }
  
  public boolean zza(Field paramField, boolean paramBoolean)
  {
    if ((beW & paramField.getModifiers()) != 0) {
      return true;
    }
    if ((beV != -1.0D) && (!zza((zzano)paramField.getAnnotation(zzano.class), (zzanp)paramField.getAnnotation(zzanp.class)))) {
      return true;
    }
    if (paramField.isSynthetic()) {
      return true;
    }
    if ((!beX) && (zzn(paramField.getType()))) {
      return true;
    }
    if (zzm(paramField.getType())) {
      return true;
    }
    if (paramBoolean) {}
    for (Object localObject = beY; !((List)localObject).isEmpty(); localObject = beZ)
    {
      paramField = new zzamp(paramField);
      localObject = ((List)localObject).iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((zzamo)((Iterator)localObject).next()).zza(paramField));
      return true;
    }
    return false;
  }
  
  public zzant zze(int... paramVarArgs)
  {
    int i = 0;
    zzant localzzant = b();
    beW = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      beW = (paramVarArgs[i] | beW);
      i += 1;
    }
    return localzzant;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzant
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */