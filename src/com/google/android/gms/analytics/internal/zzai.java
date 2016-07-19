package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import java.util.UUID;

public class zzai
  extends zzd
{
  private SharedPreferences al;
  private long am;
  private long an = -1L;
  private final zza ao = new zza("monitoring", zzyy().zzaci(), null);
  
  protected zzai(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public long zzadn()
  {
    zzwu();
    zzzg();
    long l;
    if (am == 0L)
    {
      l = al.getLong("first_run", 0L);
      if (l == 0L) {
        break label46;
      }
    }
    for (am = l;; am = l)
    {
      return am;
      label46:
      l = zzyw().currentTimeMillis();
      SharedPreferences.Editor localEditor = al.edit();
      localEditor.putLong("first_run", l);
      if (!localEditor.commit()) {
        zzel("Failed to commit first run time");
      }
    }
  }
  
  public zzal zzado()
  {
    return new zzal(zzyw(), zzadn());
  }
  
  public long zzadp()
  {
    zzwu();
    zzzg();
    if (an == -1L) {
      an = al.getLong("last_dispatch", 0L);
    }
    return an;
  }
  
  public void zzadq()
  {
    zzwu();
    zzzg();
    long l = zzyw().currentTimeMillis();
    SharedPreferences.Editor localEditor = al.edit();
    localEditor.putLong("last_dispatch", l);
    localEditor.apply();
    an = l;
  }
  
  public String zzadr()
  {
    zzwu();
    zzzg();
    String str = al.getString("installation_campaign", null);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return str;
  }
  
  public zza zzads()
  {
    return ao;
  }
  
  public void zzev(String paramString)
  {
    zzwu();
    zzzg();
    SharedPreferences.Editor localEditor = al.edit();
    if (TextUtils.isEmpty(paramString)) {
      localEditor.remove("installation_campaign");
    }
    for (;;)
    {
      if (!localEditor.commit()) {
        zzel("Failed to commit campaign data");
      }
      return;
      localEditor.putString("installation_campaign", paramString);
    }
  }
  
  protected void zzwv()
  {
    al = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
  }
  
  public final class zza
  {
    private final long ap;
    private final String mName;
    
    private zza(String paramString, long paramLong)
    {
      zzab.zzhs(paramString);
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzab.zzbn(bool);
        mName = paramString;
        ap = paramLong;
        return;
      }
    }
    
    private void zzadt()
    {
      long l = zzyw().currentTimeMillis();
      SharedPreferences.Editor localEditor = zzai.zza(zzai.this).edit();
      localEditor.remove(zzady());
      localEditor.remove(zzadz());
      localEditor.putLong(zzadx(), l);
      localEditor.commit();
    }
    
    private long zzadu()
    {
      long l = zzadw();
      if (l == 0L) {
        return 0L;
      }
      return Math.abs(l - zzyw().currentTimeMillis());
    }
    
    private long zzadw()
    {
      return zzai.zza(zzai.this).getLong(zzadx(), 0L);
    }
    
    private String zzadx()
    {
      return String.valueOf(mName).concat(":start");
    }
    
    private String zzady()
    {
      return String.valueOf(mName).concat(":count");
    }
    
    public Pair<String, Long> zzadv()
    {
      long l = zzadu();
      if (l < ap) {}
      String str;
      do
      {
        return null;
        if (l > ap * 2L)
        {
          zzadt();
          return null;
        }
        str = zzai.zza(zzai.this).getString(zzadz(), null);
        l = zzai.zza(zzai.this).getLong(zzady(), 0L);
        zzadt();
      } while ((str == null) || (l <= 0L));
      return new Pair(str, Long.valueOf(l));
    }
    
    protected String zzadz()
    {
      return String.valueOf(mName).concat(":value");
    }
    
    public void zzew(String paramString)
    {
      if (zzadw() == 0L) {
        zzadt();
      }
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      for (;;)
      {
        try
        {
          long l = zzai.zza(zzai.this).getLong(zzady(), 0L);
          if (l <= 0L)
          {
            paramString = zzai.zza(zzai.this).edit();
            paramString.putString(zzadz(), str);
            paramString.putLong(zzady(), 1L);
            paramString.apply();
            return;
          }
          if ((UUID.randomUUID().getLeastSignificantBits() & 0x7FFFFFFFFFFFFFFF) < Long.MAX_VALUE / (l + 1L))
          {
            i = 1;
            paramString = zzai.zza(zzai.this).edit();
            if (i != 0) {
              paramString.putString(zzadz(), str);
            }
            paramString.putLong(zzady(), l + 1L);
            paramString.apply();
            return;
          }
        }
        finally {}
        int i = 0;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */