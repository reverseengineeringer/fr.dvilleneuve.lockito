package com.google.android.gms.internal;

import java.util.Map.Entry;

final class zzanw$zzd<K, V>
  implements Map.Entry<K, V>
{
  final K aQw;
  V aQx;
  zzd<K, V> bfo;
  zzd<K, V> bfr;
  zzd<K, V> bfs;
  zzd<K, V> bft;
  zzd<K, V> bfu;
  int height;
  
  zzanw$zzd()
  {
    aQw = null;
    bfu = this;
    bfo = this;
  }
  
  zzanw$zzd(zzd<K, V> paramzzd1, K paramK, zzd<K, V> paramzzd2, zzd<K, V> paramzzd3)
  {
    bfr = paramzzd1;
    aQw = paramK;
    height = 1;
    bfo = paramzzd2;
    bfu = paramzzd3;
    bfo = this;
    bfu = this;
  }
  
  public zzd<K, V> d()
  {
    Object localObject1 = bfs;
    Object localObject2 = this;
    while (localObject1 != null)
    {
      zzd localzzd = bfs;
      localObject2 = localObject1;
      localObject1 = localzzd;
    }
    return (zzd<K, V>)localObject2;
  }
  
  public zzd<K, V> e()
  {
    Object localObject1 = bft;
    Object localObject2 = this;
    while (localObject1 != null)
    {
      zzd localzzd = bft;
      localObject2 = localObject1;
      localObject1 = localzzd;
    }
    return (zzd<K, V>)localObject2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Map.Entry))
    {
      paramObject = (Map.Entry)paramObject;
      if (aQw != null) {
        break label56;
      }
      bool1 = bool2;
      if (((Map.Entry)paramObject).getKey() == null)
      {
        if (aQx != null) {
          break label77;
        }
        bool1 = bool2;
        if (((Map.Entry)paramObject).getValue() != null) {}
      }
    }
    for (;;)
    {
      bool1 = true;
      label56:
      label77:
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!aQw.equals(((Map.Entry)paramObject).getKey()));
        break;
        bool1 = bool2;
      } while (!aQx.equals(((Map.Entry)paramObject).getValue()));
    }
  }
  
  public K getKey()
  {
    return (K)aQw;
  }
  
  public V getValue()
  {
    return (V)aQx;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (aQw == null)
    {
      i = 0;
      if (aQx != null) {
        break label33;
      }
    }
    for (;;)
    {
      return i ^ j;
      i = aQw.hashCode();
      break;
      label33:
      j = aQx.hashCode();
    }
  }
  
  public V setValue(V paramV)
  {
    Object localObject = aQx;
    aQx = paramV;
    return (V)localObject;
  }
  
  public String toString()
  {
    String str1 = String.valueOf(aQw);
    String str2 = String.valueOf(aQx);
    return String.valueOf(str1).length() + 1 + String.valueOf(str2).length() + str1 + "=" + str2;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanw.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */