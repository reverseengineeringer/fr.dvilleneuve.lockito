package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class zzaoj
  implements zzanl
{
  private final zzans beb;
  private final zzant bek;
  private final zzamr bem;
  
  public zzaoj(zzans paramzzans, zzamr paramzzamr, zzant paramzzant)
  {
    beb = paramzzans;
    bem = paramzzamr;
    bek = paramzzant;
  }
  
  private zzank<?> zza(zzams paramzzams, Field paramField, zzaoo<?> paramzzaoo)
  {
    paramField = (zzanm)paramField.getAnnotation(zzanm.class);
    if (paramField != null)
    {
      paramField = zzaoe.zza(beb, paramzzams, paramzzaoo, paramField);
      if (paramField != null) {
        return paramField;
      }
    }
    return paramzzams.zza(paramzzaoo);
  }
  
  private zzb zza(final zzams paramzzams, final Field paramField, String paramString, final zzaoo<?> paramzzaoo, boolean paramBoolean1, boolean paramBoolean2)
  {
    new zzb(paramString, paramBoolean1, paramBoolean2)
    {
      final zzank<?> bfV = zzaoj.zza(zzaoj.this, paramzzams, paramField, paramzzaoo);
      
      void zza(zzaop paramAnonymouszzaop, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymouszzaop = bfV.zzb(paramAnonymouszzaop);
        if ((paramAnonymouszzaop != null) || (!bfZ)) {
          paramField.set(paramAnonymousObject, paramAnonymouszzaop);
        }
      }
      
      void zza(zzaor paramAnonymouszzaor, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousObject = paramField.get(paramAnonymousObject);
        new zzaom(paramzzams, bfV, paramzzaoo.t()).zza(paramAnonymouszzaor, paramAnonymousObject);
      }
      
      public boolean zzcq(Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        if (!bgc) {}
        while (paramField.get(paramAnonymousObject) == paramAnonymousObject) {
          return false;
        }
        return true;
      }
    };
  }
  
  static List<String> zza(zzamr paramzzamr, Field paramField)
  {
    zzann localzzann = (zzann)paramField.getAnnotation(zzann.class);
    LinkedList localLinkedList = new LinkedList();
    if (localzzann == null) {
      localLinkedList.add(paramzzamr.zzc(paramField));
    }
    for (;;)
    {
      return localLinkedList;
      localLinkedList.add(localzzann.value());
      paramzzamr = localzzann.zzczy();
      int j = paramzzamr.length;
      int i = 0;
      while (i < j)
      {
        localLinkedList.add(paramzzamr[i]);
        i += 1;
      }
    }
  }
  
  private Map<String, zzb> zza(zzams paramzzams, zzaoo<?> paramzzaoo, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface()) {
      return localLinkedHashMap;
    }
    Type localType1 = paramzzaoo.t();
    Object localObject1 = paramClass;
    paramClass = paramzzaoo;
    label94:
    int j;
    if (localObject1 != Object.class)
    {
      Field[] arrayOfField = ((Class)localObject1).getDeclaredFields();
      int k = arrayOfField.length;
      int i = 0;
      for (;;)
      {
        if (i < k)
        {
          Field localField = arrayOfField[i];
          boolean bool1 = zza(localField, true);
          boolean bool2 = zza(localField, false);
          if ((!bool1) && (!bool2))
          {
            i += 1;
          }
          else
          {
            localField.setAccessible(true);
            Type localType2 = zzanr.zza(paramClass.t(), (Class)localObject1, localField.getGenericType());
            List localList = zzd(localField);
            paramzzaoo = null;
            j = 0;
            label138:
            if (j < localList.size())
            {
              Object localObject2 = (String)localList.get(j);
              if (j != 0) {
                bool1 = false;
              }
              localObject2 = (zzb)localLinkedHashMap.put(localObject2, zza(paramzzams, localField, (String)localObject2, zzaoo.zzl(localType2), bool1, bool2));
              if (paramzzaoo != null) {
                break label314;
              }
              paramzzaoo = (zzaoo<?>)localObject2;
            }
          }
        }
      }
    }
    label314:
    for (;;)
    {
      j += 1;
      break label138;
      if (paramzzaoo == null) {
        break label94;
      }
      paramzzams = String.valueOf(localType1);
      paramzzaoo = name;
      throw new IllegalArgumentException(String.valueOf(paramzzams).length() + 37 + String.valueOf(paramzzaoo).length() + paramzzams + " declares multiple JSON fields named " + paramzzaoo);
      paramClass = zzaoo.zzl(zzanr.zza(paramClass.t(), (Class)localObject1, ((Class)localObject1).getGenericSuperclass()));
      localObject1 = paramClass.s();
      break;
      return localLinkedHashMap;
    }
  }
  
  static boolean zza(Field paramField, boolean paramBoolean, zzant paramzzant)
  {
    return (!paramzzant.zza(paramField.getType(), paramBoolean)) && (!paramzzant.zza(paramField, paramBoolean));
  }
  
  private List<String> zzd(Field paramField)
  {
    return zza(bem, paramField);
  }
  
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    Class localClass = paramzzaoo.s();
    if (!Object.class.isAssignableFrom(localClass)) {
      return null;
    }
    return new zza(beb.zzb(paramzzaoo), zza(paramzzams, paramzzaoo, localClass), null);
  }
  
  public boolean zza(Field paramField, boolean paramBoolean)
  {
    return zza(paramField, paramBoolean, bek);
  }
  
  public static final class zza<T>
    extends zzank<T>
  {
    private final zzanx<T> bfI;
    private final Map<String, zzaoj.zzb> bgb;
    
    private zza(zzanx<T> paramzzanx, Map<String, zzaoj.zzb> paramMap)
    {
      bfI = paramzzanx;
      bgb = paramMap;
    }
    
    public void zza(zzaor paramzzaor, T paramT)
      throws IOException
    {
      if (paramT == null)
      {
        paramzzaor.r();
        return;
      }
      paramzzaor.p();
      try
      {
        Iterator localIterator = bgb.values().iterator();
        while (localIterator.hasNext())
        {
          zzaoj.zzb localzzb = (zzaoj.zzb)localIterator.next();
          if (localzzb.zzcq(paramT))
          {
            paramzzaor.zzta(name);
            localzzb.zza(paramzzaor, paramT);
          }
        }
        paramzzaor.q();
      }
      catch (IllegalAccessException paramzzaor)
      {
        throw new AssertionError();
      }
    }
    
    public T zzb(zzaop paramzzaop)
      throws IOException
    {
      if (paramzzaop.h() == zzaoq.bhH)
      {
        paramzzaop.nextNull();
        return null;
      }
      Object localObject1 = bfI.a();
      try
      {
        paramzzaop.beginObject();
        for (;;)
        {
          if (!paramzzaop.hasNext()) {
            break label103;
          }
          localObject2 = paramzzaop.nextName();
          localObject2 = (zzaoj.zzb)bgb.get(localObject2);
          if ((localObject2 != null) && (bgd)) {
            break;
          }
          paramzzaop.skipValue();
        }
      }
      catch (IllegalStateException paramzzaop)
      {
        for (;;)
        {
          Object localObject2;
          throw new zzanh(paramzzaop);
          ((zzaoj.zzb)localObject2).zza(paramzzaop, localObject1);
        }
      }
      catch (IllegalAccessException paramzzaop)
      {
        throw new AssertionError(paramzzaop);
      }
      label103:
      paramzzaop.endObject();
      return (T)localObject1;
    }
  }
  
  static abstract class zzb
  {
    final boolean bgc;
    final boolean bgd;
    final String name;
    
    protected zzb(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      name = paramString;
      bgc = paramBoolean1;
      bgd = paramBoolean2;
    }
    
    abstract void zza(zzaop paramzzaop, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract void zza(zzaor paramzzaor, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract boolean zzcq(Object paramObject)
      throws IOException, IllegalAccessException;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */