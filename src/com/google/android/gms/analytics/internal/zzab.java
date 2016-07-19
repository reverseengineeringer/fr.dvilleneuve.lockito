package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzab
{
  private final List<Command> O;
  private final long P;
  private final long Q;
  private final int R;
  private final boolean S;
  private final String T;
  private final Map<String, String> zzbee;
  
  public zzab(zzc paramzzc, Map<String, String> paramMap, long paramLong, boolean paramBoolean)
  {
    this(paramzzc, paramMap, paramLong, paramBoolean, 0L, 0, null);
  }
  
  public zzab(zzc paramzzc, Map<String, String> paramMap, long paramLong1, boolean paramBoolean, long paramLong2, int paramInt)
  {
    this(paramzzc, paramMap, paramLong1, paramBoolean, paramLong2, paramInt, null);
  }
  
  public zzab(zzc paramzzc, Map<String, String> paramMap, long paramLong1, boolean paramBoolean, long paramLong2, int paramInt, List<Command> paramList)
  {
    com.google.android.gms.common.internal.zzab.zzaa(paramzzc);
    com.google.android.gms.common.internal.zzab.zzaa(paramMap);
    Q = paramLong1;
    S = paramBoolean;
    P = paramLong2;
    R = paramInt;
    if (paramList != null) {}
    Object localObject2;
    for (Object localObject1 = paramList;; localObject1 = Collections.emptyList())
    {
      O = ((List)localObject1);
      T = zzr(paramList);
      paramList = new HashMap();
      localObject1 = paramMap.entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        if (zzm(((Map.Entry)localObject2).getKey()))
        {
          String str = zza(paramzzc, ((Map.Entry)localObject2).getKey());
          if (str != null) {
            paramList.put(str, zzb(paramzzc, ((Map.Entry)localObject2).getValue()));
          }
        }
      }
    }
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      localObject1 = (Map.Entry)paramMap.next();
      if (!zzm(((Map.Entry)localObject1).getKey()))
      {
        localObject2 = zza(paramzzc, ((Map.Entry)localObject1).getKey());
        if (localObject2 != null) {
          paramList.put(localObject2, zzb(paramzzc, ((Map.Entry)localObject1).getValue()));
        }
      }
    }
    if (!TextUtils.isEmpty(T))
    {
      zzao.zzc(paramList, "_v", T);
      if ((T.equals("ma4.0.0")) || (T.equals("ma4.0.1"))) {
        paramList.remove("adid");
      }
    }
    zzbee = Collections.unmodifiableMap(paramList);
  }
  
  public static zzab zza(zzc paramzzc, zzab paramzzab, Map<String, String> paramMap)
  {
    return new zzab(paramzzc, paramMap, paramzzab.zzacz(), paramzzab.zzadb(), paramzzab.zzacy(), paramzzab.zzacx(), paramzzab.zzada());
  }
  
  private static String zza(zzc paramzzc, Object paramObject)
  {
    if (paramObject == null) {
      paramzzc = null;
    }
    Object localObject;
    do
    {
      return paramzzc;
      localObject = paramObject.toString();
      paramObject = localObject;
      if (((String)localObject).startsWith("&")) {
        paramObject = ((String)localObject).substring(1);
      }
      int i = ((String)paramObject).length();
      localObject = paramObject;
      if (i > 256)
      {
        localObject = ((String)paramObject).substring(0, 256);
        paramzzc.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(i), localObject);
      }
      paramzzc = (zzc)localObject;
    } while (!TextUtils.isEmpty((CharSequence)localObject));
    return null;
  }
  
  private static String zzb(zzc paramzzc, Object paramObject)
  {
    if (paramObject == null) {}
    for (paramObject = "";; paramObject = paramObject.toString())
    {
      int i = ((String)paramObject).length();
      Object localObject = paramObject;
      if (i > 8192)
      {
        localObject = ((String)paramObject).substring(0, 8192);
        paramzzc.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(i), localObject);
      }
      return (String)localObject;
    }
  }
  
  private static boolean zzm(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    return paramObject.toString().startsWith("&");
  }
  
  private static String zzr(List<Command> paramList)
  {
    Command localCommand;
    if (paramList != null)
    {
      paramList = paramList.iterator();
      do
      {
        if (!paramList.hasNext()) {
          break;
        }
        localCommand = (Command)paramList.next();
      } while (!"appendVersion".equals(localCommand.getId()));
    }
    for (paramList = localCommand.getValue();; paramList = null)
    {
      if (TextUtils.isEmpty(paramList)) {
        return null;
      }
      return paramList;
    }
  }
  
  private String zzs(String paramString1, String paramString2)
  {
    com.google.android.gms.common.internal.zzab.zzhs(paramString1);
    if (!paramString1.startsWith("&")) {}
    for (boolean bool = true;; bool = false)
    {
      com.google.android.gms.common.internal.zzab.zzb(bool, "Short param name required");
      paramString1 = (String)zzbee.get(paramString1);
      if (paramString1 == null) {
        break;
      }
      return paramString1;
    }
    return paramString2;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ht=").append(Q);
    if (P != 0L) {
      localStringBuffer.append(", dbId=").append(P);
    }
    if (R != 0) {
      localStringBuffer.append(", appUID=").append(R);
    }
    Object localObject = new ArrayList(zzbee.keySet());
    Collections.sort((List)localObject);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      localStringBuffer.append(", ");
      localStringBuffer.append(str);
      localStringBuffer.append("=");
      localStringBuffer.append((String)zzbee.get(str));
    }
    return localStringBuffer.toString();
  }
  
  public int zzacx()
  {
    return R;
  }
  
  public long zzacy()
  {
    return P;
  }
  
  public long zzacz()
  {
    return Q;
  }
  
  public List<Command> zzada()
  {
    return O;
  }
  
  public boolean zzadb()
  {
    return S;
  }
  
  public long zzadc()
  {
    return zzao.zzez(zzs("_s", "0"));
  }
  
  public String zzadd()
  {
    return zzs("_m", "");
  }
  
  public Map<String, String> zzm()
  {
    return zzbee;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzab
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */