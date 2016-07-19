package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzamv
  extends zzamy
  implements Iterable<zzamy>
{
  private final List<zzamy> aFD = new ArrayList();
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof zzamv)) && (aFD.equals(aFD)));
  }
  
  public int hashCode()
  {
    return aFD.hashCode();
  }
  
  public Iterator<zzamy> iterator()
  {
    return aFD.iterator();
  }
  
  public void zzc(zzamy paramzzamy)
  {
    Object localObject = paramzzamy;
    if (paramzzamy == null) {
      localObject = zzana.bes;
    }
    aFD.add(localObject);
  }
  
  public Number zzczg()
  {
    if (aFD.size() == 1) {
      return ((zzamy)aFD.get(0)).zzczg();
    }
    throw new IllegalStateException();
  }
  
  public String zzczh()
  {
    if (aFD.size() == 1) {
      return ((zzamy)aFD.get(0)).zzczh();
    }
    throw new IllegalStateException();
  }
  
  public double zzczi()
  {
    if (aFD.size() == 1) {
      return ((zzamy)aFD.get(0)).zzczi();
    }
    throw new IllegalStateException();
  }
  
  public long zzczj()
  {
    if (aFD.size() == 1) {
      return ((zzamy)aFD.get(0)).zzczj();
    }
    throw new IllegalStateException();
  }
  
  public int zzczk()
  {
    if (aFD.size() == 1) {
      return ((zzamy)aFD.get(0)).zzczk();
    }
    throw new IllegalStateException();
  }
  
  public boolean zzczl()
  {
    if (aFD.size() == 1) {
      return ((zzamy)aFD.get(0)).zzczl();
    }
    throw new IllegalStateException();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzamv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */