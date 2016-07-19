package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzdf
  extends zzdg
{
  private static final String ID = zzaf.zzil.toString();
  
  public zzdf()
  {
    super(ID);
  }
  
  protected boolean zza(String paramString1, String paramString2, Map<String, zzai.zza> paramMap)
  {
    return paramString1.startsWith(paramString2);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */