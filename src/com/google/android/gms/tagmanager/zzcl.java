package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcl
  extends zzal
{
  private static final String ID = zzaf.zzhq.toString();
  private static final String axt = zzag.zzlk.toString();
  private static final String axu = zzag.zzll.toString();
  private static final String axv = zzag.zzpf.toString();
  private static final String axw = zzag.zzoy.toString();
  
  public zzcl()
  {
    super(ID, new String[] { axt, axu });
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    Object localObject = (zzai.zza)paramMap.get(axt);
    zzai.zza localzza = (zzai.zza)paramMap.get(axu);
    if ((localObject == null) || (localObject == zzdl.zzcdq()) || (localzza == null) || (localzza == zzdl.zzcdq())) {
      return zzdl.zzcdq();
    }
    int i = 64;
    if (zzdl.zzk((zzai.zza)paramMap.get(axv)).booleanValue()) {
      i = 66;
    }
    paramMap = (zzai.zza)paramMap.get(axw);
    int j;
    if (paramMap != null)
    {
      paramMap = zzdl.zzi(paramMap);
      if (paramMap == zzdl.zzcdl()) {
        return zzdl.zzcdq();
      }
      int k = paramMap.intValue();
      j = k;
      if (k < 0) {
        return zzdl.zzcdq();
      }
    }
    else
    {
      j = 1;
    }
    try
    {
      paramMap = zzdl.zzg((zzai.zza)localObject);
      localObject = zzdl.zzg(localzza);
      localzza = null;
      localObject = Pattern.compile((String)localObject, i).matcher(paramMap);
      paramMap = localzza;
      if (((Matcher)localObject).find())
      {
        paramMap = localzza;
        if (((Matcher)localObject).groupCount() >= j) {
          paramMap = ((Matcher)localObject).group(j);
        }
      }
      if (paramMap == null) {
        return zzdl.zzcdq();
      }
      paramMap = zzdl.zzar(paramMap);
      return paramMap;
    }
    catch (PatternSyntaxException paramMap) {}
    return zzdl.zzcdq();
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */