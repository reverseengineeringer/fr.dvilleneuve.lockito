package com.google.android.gms.analytics.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import java.util.UUID;

public final class zzai$zza
{
  private final long ap;
  private final String mName;
  
  private zzai$zza(zzai paramzzai, String paramString, long paramLong)
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
    long l = aq.zzyw().currentTimeMillis();
    SharedPreferences.Editor localEditor = zzai.zza(aq).edit();
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
    return Math.abs(l - aq.zzyw().currentTimeMillis());
  }
  
  private long zzadw()
  {
    return zzai.zza(aq).getLong(zzadx(), 0L);
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
      str = zzai.zza(aq).getString(zzadz(), null);
      l = zzai.zza(aq).getLong(zzady(), 0L);
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
        long l = zzai.zza(aq).getLong(zzady(), 0L);
        if (l <= 0L)
        {
          paramString = zzai.zza(aq).edit();
          paramString.putString(zzadz(), str);
          paramString.putLong(zzady(), 1L);
          paramString.apply();
          return;
        }
        if ((UUID.randomUUID().getLeastSignificantBits() & 0x7FFFFFFFFFFFFFFF) < Long.MAX_VALUE / (l + 1L))
        {
          i = 1;
          paramString = zzai.zza(aq).edit();
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

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzai.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */