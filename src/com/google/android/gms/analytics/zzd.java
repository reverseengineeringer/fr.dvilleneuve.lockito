package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class zzd
  implements zzk
{
  private static final Uri zzcsv;
  private final LogPrinter zzcsw = new LogPrinter(4, "GA/LogCatTransport");
  
  static
  {
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("uri");
    localBuilder.authority("local");
    zzcsv = localBuilder.build();
  }
  
  public void zzb(zze paramzze)
  {
    Object localObject = new ArrayList(paramzze.zzwg());
    Collections.sort((List)localObject, new Comparator()
    {
      public int zza(zzg paramAnonymouszzg1, zzg paramAnonymouszzg2)
      {
        return paramAnonymouszzg1.getClass().getCanonicalName().compareTo(paramAnonymouszzg2.getClass().getCanonicalName());
      }
    });
    paramzze = new StringBuilder();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = ((zzg)((Iterator)localObject).next()).toString();
      if (!TextUtils.isEmpty(str))
      {
        if (paramzze.length() != 0) {
          paramzze.append(", ");
        }
        paramzze.append(str);
      }
    }
    zzcsw.println(paramzze.toString());
  }
  
  public Uri zzvu()
  {
    return zzcsv;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */