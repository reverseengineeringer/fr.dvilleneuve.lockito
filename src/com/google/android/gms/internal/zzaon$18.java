package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

final class zzaon$18
  extends zzank<zzamy>
{
  public void zza(zzaor paramzzaor, zzamy paramzzamy)
    throws IOException
  {
    if ((paramzzamy == null) || (paramzzamy.zzczp()))
    {
      paramzzaor.r();
      return;
    }
    if (paramzzamy.zzczo())
    {
      paramzzamy = paramzzamy.zzczs();
      if (paramzzamy.zzczv())
      {
        paramzzaor.zza(paramzzamy.zzczg());
        return;
      }
      if (paramzzamy.zzczu())
      {
        paramzzaor.zzcz(paramzzamy.zzczl());
        return;
      }
      paramzzaor.zztb(paramzzamy.zzczh());
      return;
    }
    if (paramzzamy.zzczm())
    {
      paramzzaor.n();
      paramzzamy = paramzzamy.zzczr().iterator();
      while (paramzzamy.hasNext()) {
        zza(paramzzaor, (zzamy)paramzzamy.next());
      }
      paramzzaor.o();
      return;
    }
    if (paramzzamy.zzczn())
    {
      paramzzaor.p();
      paramzzamy = paramzzamy.zzczq().entrySet().iterator();
      while (paramzzamy.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramzzamy.next();
        paramzzaor.zzta((String)localEntry.getKey());
        zza(paramzzaor, (zzamy)localEntry.getValue());
      }
      paramzzaor.q();
      return;
    }
    paramzzaor = String.valueOf(paramzzamy.getClass());
    throw new IllegalArgumentException(String.valueOf(paramzzaor).length() + 15 + "Couldn't write " + paramzzaor);
  }
  
  public zzamy zzad(zzaop paramzzaop)
    throws IOException
  {
    switch (zzaon.26.bfU[paramzzaop.h().ordinal()])
    {
    default: 
      throw new IllegalArgumentException();
    case 3: 
      return new zzane(paramzzaop.nextString());
    case 1: 
      return new zzane(new zzanv(paramzzaop.nextString()));
    case 2: 
      return new zzane(Boolean.valueOf(paramzzaop.nextBoolean()));
    case 4: 
      paramzzaop.nextNull();
      return zzana.bes;
    case 5: 
      localObject = new zzamv();
      paramzzaop.beginArray();
      while (paramzzaop.hasNext()) {
        ((zzamv)localObject).zzc((zzamy)zzb(paramzzaop));
      }
      paramzzaop.endArray();
      return (zzamy)localObject;
    }
    Object localObject = new zzanb();
    paramzzaop.beginObject();
    while (paramzzaop.hasNext()) {
      ((zzanb)localObject).zza(paramzzaop.nextName(), (zzamy)zzb(paramzzaop));
    }
    paramzzaop.endObject();
    return (zzamy)localObject;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */