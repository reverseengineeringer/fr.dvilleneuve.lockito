package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzce
  extends zzal
{
  private static final String ID = zzaf.zzgy.toString();
  
  public zzce()
  {
    super(ID, new String[0]);
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    return zzdl.zzar(Build.VERSION.RELEASE);
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzce
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */