package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzcy
  extends zzal
{
  private static final String ID = zzaf.zzhe.toString();
  
  public zzcy()
  {
    super(ID, new String[0]);
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    return zzdl.zzar(Integer.valueOf(Build.VERSION.SDK_INT));
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */