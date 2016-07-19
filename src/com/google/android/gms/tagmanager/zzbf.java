package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class zzbf
  extends zzal
{
  private static final String ID = zzaf.zzho.toString();
  private static final String awB = zzag.zzpm.toString();
  private static final String awC = zzag.zzpq.toString();
  private static final String awD = zzag.zzog.toString();
  private static final String awj = zzag.zzlk.toString();
  
  public zzbf()
  {
    super(ID, new String[] { awj });
  }
  
  private String zza(String paramString, zza paramzza, Set<Character> paramSet)
  {
    switch (1.awE[paramzza.ordinal()])
    {
    default: 
      return paramString;
    case 1: 
      try
      {
        paramzza = zzdp.zzoz(paramString);
        return paramzza;
      }
      catch (UnsupportedEncodingException paramzza)
      {
        zzbn.zzb("Joiner: unsupported encoding", paramzza);
        return paramString;
      }
    }
    paramString = paramString.replace("\\", "\\\\");
    paramSet = paramSet.iterator();
    if (paramSet.hasNext())
    {
      String str = ((Character)paramSet.next()).toString();
      paramzza = String.valueOf(str);
      if (paramzza.length() != 0) {}
      for (paramzza = "\\".concat(paramzza);; paramzza = new String("\\"))
      {
        paramString = paramString.replace(str, paramzza);
        break;
      }
    }
    return paramString;
  }
  
  private void zza(StringBuilder paramStringBuilder, String paramString, zza paramzza, Set<Character> paramSet)
  {
    paramStringBuilder.append(zza(paramString, paramzza, paramSet));
  }
  
  private void zza(Set<Character> paramSet, String paramString)
  {
    int i = 0;
    while (i < paramString.length())
    {
      paramSet.add(Character.valueOf(paramString.charAt(i)));
      i += 1;
    }
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    zzai.zza localzza = (zzai.zza)paramMap.get(awj);
    if (localzza == null) {
      return zzdl.zzcdq();
    }
    Object localObject1 = (zzai.zza)paramMap.get(awB);
    String str1;
    Object localObject2;
    if (localObject1 != null)
    {
      str1 = zzdl.zzg((zzai.zza)localObject1);
      localObject1 = (zzai.zza)paramMap.get(awC);
      if (localObject1 == null) {
        break label186;
      }
      localObject2 = zzdl.zzg((zzai.zza)localObject1);
      label75:
      localObject1 = zza.awF;
      paramMap = (zzai.zza)paramMap.get(awD);
      if (paramMap == null) {
        break label432;
      }
      paramMap = zzdl.zzg(paramMap);
      if (!"url".equals(paramMap)) {
        break label193;
      }
      localObject1 = zza.awG;
      paramMap = null;
    }
    for (;;)
    {
      label118:
      StringBuilder localStringBuilder = new StringBuilder();
      switch (type)
      {
      default: 
        zza(localStringBuilder, zzdl.zzg(localzza), (zza)localObject1, paramMap);
      }
      for (;;)
      {
        return zzdl.zzar(localStringBuilder.toString());
        str1 = "";
        break;
        label186:
        localObject2 = "=";
        break label75;
        label193:
        if ("backslash".equals(paramMap))
        {
          localObject1 = zza.awH;
          paramMap = new HashSet();
          zza(paramMap, str1);
          zza(paramMap, (String)localObject2);
          paramMap.remove(Character.valueOf('\\'));
          break label118;
        }
        paramMap = String.valueOf(paramMap);
        if (paramMap.length() != 0) {}
        for (paramMap = "Joiner: unsupported escape type: ".concat(paramMap);; paramMap = new String("Joiner: unsupported escape type: "))
        {
          zzbn.e(paramMap);
          return zzdl.zzcdq();
        }
        int j = 1;
        localObject2 = zzwu;
        int k = localObject2.length;
        int i = 0;
        while (i < k)
        {
          localzza = localObject2[i];
          if (j == 0) {
            localStringBuilder.append(str1);
          }
          zza(localStringBuilder, zzdl.zzg(localzza), (zza)localObject1, paramMap);
          i += 1;
          j = 0;
        }
        i = 0;
        while (i < zzwv.length)
        {
          if (i > 0) {
            localStringBuilder.append(str1);
          }
          String str2 = zzdl.zzg(zzwv[i]);
          String str3 = zzdl.zzg(zzww[i]);
          zza(localStringBuilder, str2, (zza)localObject1, paramMap);
          localStringBuilder.append((String)localObject2);
          zza(localStringBuilder, str3, (zza)localObject1, paramMap);
          i += 1;
        }
      }
      label432:
      paramMap = null;
    }
  }
  
  public boolean zzcac()
  {
    return true;
  }
  
  private static enum zza
  {
    private zza() {}
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzbf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */