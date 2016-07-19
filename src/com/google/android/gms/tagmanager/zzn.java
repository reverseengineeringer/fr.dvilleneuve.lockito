package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzn
  extends zzal
{
  private static final String ID = zzaf.zzgh.toString();
  private static final String VALUE = zzag.zzue.toString();
  
  public zzn()
  {
    super(ID, new String[] { VALUE });
  }
  
  public static String zzcaf()
  {
    return ID;
  }
  
  public static String zzcag()
  {
    return VALUE;
  }
  
  public zzai.zza zzav(Map<String, zzai.zza> paramMap)
  {
    return (zzai.zza)paramMap.get(VALUE);
  }
  
  public boolean zzcac()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */