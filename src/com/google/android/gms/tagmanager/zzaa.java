package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzaa
  extends zzal
{
  private static final String ID = zzaf.zzhj.toString();
  private final Context mContext;
  
  public zzaa(Context paramContext)
  {
    super(ID, new String[0]);
    mContext = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    paramMap = zzdt(mContext);
    if (paramMap == null) {
      return zzdl.zzcdq();
    }
    return zzdl.zzar(paramMap);
  }
  
  public boolean zzcac()
  {
    return true;
  }
  
  protected String zzdt(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzaa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */