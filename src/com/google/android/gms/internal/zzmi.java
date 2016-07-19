package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class zzmi
  extends zzg<zzmi>
{
  private String zzcvs;
  private int zzcvt;
  private int zzcvu;
  private String zzcvv;
  private String zzcvw;
  private boolean zzcvx;
  private boolean zzcvy;
  
  public zzmi()
  {
    this(false);
  }
  
  public zzmi(boolean paramBoolean)
  {
    this(paramBoolean, zzyd());
  }
  
  public zzmi(boolean paramBoolean, int paramInt)
  {
    zzab.zzgd(paramInt);
    zzcvt = paramInt;
    zzcvy = paramBoolean;
  }
  
  static int zzyd()
  {
    UUID localUUID = UUID.randomUUID();
    int i = (int)(localUUID.getLeastSignificantBits() & 0x7FFFFFFF);
    if (i != 0) {}
    int j;
    do
    {
      return i;
      j = (int)(localUUID.getMostSignificantBits() & 0x7FFFFFFF);
      i = j;
    } while (j != 0);
    Log.e("GAv4", "UUID.randomUUID() returned 0.");
    return Integer.MAX_VALUE;
  }
  
  private void zzyh() {}
  
  public void setScreenName(String paramString)
  {
    zzyh();
    zzcvs = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("screenName", zzcvs);
    localHashMap.put("interstitial", Boolean.valueOf(zzcvx));
    localHashMap.put("automatic", Boolean.valueOf(zzcvy));
    localHashMap.put("screenId", Integer.valueOf(zzcvt));
    localHashMap.put("referrerScreenId", Integer.valueOf(zzcvu));
    localHashMap.put("referrerScreenName", zzcvv);
    localHashMap.put("referrerUri", zzcvw);
    return zzk(localHashMap);
  }
  
  public void zza(zzmi paramzzmi)
  {
    if (!TextUtils.isEmpty(zzcvs)) {
      paramzzmi.setScreenName(zzcvs);
    }
    if (zzcvt != 0) {
      paramzzmi.zzbu(zzcvt);
    }
    if (zzcvu != 0) {
      paramzzmi.zzbv(zzcvu);
    }
    if (!TextUtils.isEmpty(zzcvv)) {
      paramzzmi.zzea(zzcvv);
    }
    if (!TextUtils.isEmpty(zzcvw)) {
      paramzzmi.zzeb(zzcvw);
    }
    if (zzcvx) {
      paramzzmi.zzar(zzcvx);
    }
    if (zzcvy) {
      paramzzmi.zzaq(zzcvy);
    }
  }
  
  public void zzaq(boolean paramBoolean)
  {
    zzyh();
    zzcvy = paramBoolean;
  }
  
  public void zzar(boolean paramBoolean)
  {
    zzyh();
    zzcvx = paramBoolean;
  }
  
  public void zzbu(int paramInt)
  {
    zzyh();
    zzcvt = paramInt;
  }
  
  public void zzbv(int paramInt)
  {
    zzyh();
    zzcvu = paramInt;
  }
  
  public void zzea(String paramString)
  {
    zzyh();
    zzcvv = paramString;
  }
  
  public void zzeb(String paramString)
  {
    zzyh();
    if (TextUtils.isEmpty(paramString))
    {
      zzcvw = null;
      return;
    }
    zzcvw = paramString;
  }
  
  public String zzye()
  {
    return zzcvs;
  }
  
  public int zzyf()
  {
    return zzcvt;
  }
  
  public String zzyg()
  {
    return zzcvw;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzmi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */