package com.google.android.gms.analytics.internal;

import android.util.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzaf
  extends zzd
{
  private static String aa = "3";
  private static String ab = "01VDIWEA?";
  private static zzaf ac;
  
  public zzaf(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public static zzaf zzadf()
  {
    return ac;
  }
  
  public void zza(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    String str = (String)zzy.zzczr.get();
    if (Log.isLoggable(str, paramInt)) {
      Log.println(paramInt, str, zzc(paramString, paramObject1, paramObject2, paramObject3));
    }
    if (paramInt >= 5) {
      zzb(paramInt, paramString, paramObject1, paramObject2, paramObject3);
    }
  }
  
  public void zza(zzab paramzzab, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "no reason provided";
    }
    if (paramzzab != null)
    {
      paramzzab = paramzzab.toString();
      paramString = String.valueOf(str);
      if (paramString.length() == 0) {
        break label50;
      }
    }
    label50:
    for (paramString = "Discarding hit. ".concat(paramString);; paramString = new String("Discarding hit. "))
    {
      zzd(paramString, paramzzab);
      return;
      paramzzab = "no hit data";
      break;
    }
  }
  
  public void zzb(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    int i = 0;
    for (;;)
    {
      try
      {
        com.google.android.gms.common.internal.zzab.zzaa(paramString);
        if (paramInt < 0)
        {
          paramInt = i;
          if (paramInt >= ab.length())
          {
            paramInt = ab.length() - 1;
            if (zzyy().zzabd())
            {
              if (zzyy().zzabc())
              {
                c1 = 'P';
                String str1 = aa;
                char c2 = ab.charAt(paramInt);
                String str2 = zze.VERSION;
                paramString = String.valueOf(zzc(paramString, zzn(paramObject1), zzn(paramObject2), zzn(paramObject3)));
                paramObject1 = String.valueOf(str1).length() + 3 + String.valueOf(str2).length() + String.valueOf(paramString).length() + str1 + c2 + c1 + str2 + ":" + paramString;
                paramString = (String)paramObject1;
                if (((String)paramObject1).length() > 1024) {
                  paramString = ((String)paramObject1).substring(0, 1024);
                }
                paramObject1 = zzyu().zzzl();
                if (paramObject1 != null) {
                  ((zzai)paramObject1).zzads().zzew(paramString);
                }
                return;
              }
              c1 = 'C';
              continue;
            }
            boolean bool = zzyy().zzabc();
            if (bool)
            {
              c1 = 'p';
              continue;
            }
            char c1 = 'c';
            continue;
          }
        }
      }
      finally {}
    }
  }
  
  public void zzh(Map<String, String> paramMap, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "no reason provided";
    }
    if (paramMap != null)
    {
      paramString = new StringBuilder();
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        if (paramString.length() > 0) {
          paramString.append(',');
        }
        paramString.append((String)localEntry.getKey());
        paramString.append('=');
        paramString.append((String)localEntry.getValue());
      }
      paramMap = paramString.toString();
      paramString = String.valueOf(str);
      if (paramString.length() == 0) {
        break label144;
      }
    }
    label144:
    for (paramString = "Discarding hit. ".concat(paramString);; paramString = new String("Discarding hit. "))
    {
      zzd(paramString, paramMap);
      return;
      paramMap = "no hit data";
      break;
    }
  }
  
  protected String zzn(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof Integer)) {
      paramObject = new Long(((Integer)paramObject).intValue());
    }
    for (;;)
    {
      if ((paramObject instanceof Long))
      {
        if (Math.abs(((Long)paramObject).longValue()) < 100L) {
          return String.valueOf(paramObject);
        }
        if (String.valueOf(paramObject).charAt(0) == '-') {}
        for (String str = "-";; str = "")
        {
          paramObject = String.valueOf(Math.abs(((Long)paramObject).longValue()));
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str);
          localStringBuilder.append(Math.round(Math.pow(10.0D, ((String)paramObject).length() - 1)));
          localStringBuilder.append("...");
          localStringBuilder.append(str);
          localStringBuilder.append(Math.round(Math.pow(10.0D, ((String)paramObject).length()) - 1.0D));
          return localStringBuilder.toString();
        }
      }
      if ((paramObject instanceof Boolean)) {
        return String.valueOf(paramObject);
      }
      if ((paramObject instanceof Throwable)) {
        return paramObject.getClass().getCanonicalName();
      }
      return "-";
    }
  }
  
  protected void zzwv()
  {
    try
    {
      ac = this;
      return;
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzaf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */