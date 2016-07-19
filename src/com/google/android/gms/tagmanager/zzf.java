package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzf
  extends zzal
{
  private static final String ID = zzaf.zzgd.toString();
  private final Context mContext;
  
  public zzf(Context paramContext)
  {
    super(ID, new String[0]);
    mContext = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    return zzdl.zzar(mContext.getPackageName());
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */