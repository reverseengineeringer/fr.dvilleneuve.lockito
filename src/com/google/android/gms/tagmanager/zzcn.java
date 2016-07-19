package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzcn
  extends zzal
{
  private static final String ID = zzaf.zzhc.toString();
  private final Context mContext;
  
  public zzcn(Context paramContext)
  {
    super(ID, new String[0]);
    mContext = paramContext;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    paramMap = new DisplayMetrics();
    ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay().getMetrics(paramMap);
    int i = widthPixels;
    int j = heightPixels;
    return zzdl.zzar(23 + i + "x" + j);
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */