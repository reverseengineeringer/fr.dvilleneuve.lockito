package com.google.android.gms.internal;

import java.util.Map.Entry;
import java.util.Set;

public final class zzanb
  extends zzamy
{
  private final zzanw<String, zzamy> bet = new zzanw();
  
  public Set<Map.Entry<String, zzamy>> entrySet()
  {
    return bet.entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof zzanb)) && (bet.equals(bet)));
  }
  
  public boolean has(String paramString)
  {
    return bet.containsKey(paramString);
  }
  
  public int hashCode()
  {
    return bet.hashCode();
  }
  
  public void zza(String paramString, zzamy paramzzamy)
  {
    Object localObject = paramzzamy;
    if (paramzzamy == null) {
      localObject = zzana.bes;
    }
    bet.put(paramString, localObject);
  }
  
  public zzamy zzsx(String paramString)
  {
    return (zzamy)bet.get(paramString);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */