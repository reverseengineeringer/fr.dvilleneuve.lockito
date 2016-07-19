package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class zzaoc
  implements zzanl
{
  private final zzans beb;
  
  public zzaoc(zzans paramzzans)
  {
    beb = paramzzans;
  }
  
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    Type localType = paramzzaoo.t();
    Class localClass = paramzzaoo.s();
    if (!Collection.class.isAssignableFrom(localClass)) {
      return null;
    }
    localType = zzanr.zza(localType, localClass);
    return new zza(paramzzams, localType, paramzzams.zza(zzaoo.zzl(localType)), beb.zzb(paramzzaoo));
  }
  
  private static final class zza<E>
    extends zzank<Collection<E>>
  {
    private final zzank<E> bfH;
    private final zzanx<? extends Collection<E>> bfI;
    
    public zza(zzams paramzzams, Type paramType, zzank<E> paramzzank, zzanx<? extends Collection<E>> paramzzanx)
    {
      bfH = new zzaom(paramzzams, paramzzank, paramType);
      bfI = paramzzanx;
    }
    
    public void zza(zzaor paramzzaor, Collection<E> paramCollection)
      throws IOException
    {
      if (paramCollection == null)
      {
        paramzzaor.r();
        return;
      }
      paramzzaor.n();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Object localObject = paramCollection.next();
        bfH.zza(paramzzaor, localObject);
      }
      paramzzaor.o();
    }
    
    public Collection<E> zzj(zzaop paramzzaop)
      throws IOException
    {
      if (paramzzaop.h() == zzaoq.bhH)
      {
        paramzzaop.nextNull();
        return null;
      }
      Collection localCollection = (Collection)bfI.a();
      paramzzaop.beginArray();
      while (paramzzaop.hasNext()) {
        localCollection.add(bfH.zzb(paramzzaop));
      }
      paramzzaop.endArray();
      return localCollection;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaoc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */