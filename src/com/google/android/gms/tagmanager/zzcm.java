package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcm
  extends zzdg
{
  private static final String ID = zzaf.zzik.toString();
  private static final String axv = zzag.zzpf.toString();
  
  public zzcm()
  {
    super(ID);
  }
  
  protected boolean zza(String paramString1, String paramString2, Map<String, zzai.zza> paramMap)
  {
    if (zzdl.zzk((zzai.zza)paramMap.get(axv)).booleanValue()) {}
    for (int i = 66;; i = 64) {
      try
      {
        boolean bool = Pattern.compile(paramString2, i).matcher(paramString1).find();
        return bool;
      }
      catch (PatternSyntaxException paramString1)
      {
        return false;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */