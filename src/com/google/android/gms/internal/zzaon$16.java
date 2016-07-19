package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

final class zzaon$16
  extends zzank<Calendar>
{
  public void zza(zzaor paramzzaor, Calendar paramCalendar)
    throws IOException
  {
    if (paramCalendar == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor.p();
    paramzzaor.zzta("year");
    paramzzaor.zzcp(paramCalendar.get(1));
    paramzzaor.zzta("month");
    paramzzaor.zzcp(paramCalendar.get(2));
    paramzzaor.zzta("dayOfMonth");
    paramzzaor.zzcp(paramCalendar.get(5));
    paramzzaor.zzta("hourOfDay");
    paramzzaor.zzcp(paramCalendar.get(11));
    paramzzaor.zzta("minute");
    paramzzaor.zzcp(paramCalendar.get(12));
    paramzzaor.zzta("second");
    paramzzaor.zzcp(paramCalendar.get(13));
    paramzzaor.q();
  }
  
  public Calendar zzab(zzaop paramzzaop)
    throws IOException
  {
    int j = 0;
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    paramzzaop.beginObject();
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    while (paramzzaop.h() != zzaoq.bhC)
    {
      String str = paramzzaop.nextName();
      int i = paramzzaop.nextInt();
      if ("year".equals(str)) {
        i2 = i;
      } else if ("month".equals(str)) {
        i1 = i;
      } else if ("dayOfMonth".equals(str)) {
        n = i;
      } else if ("hourOfDay".equals(str)) {
        m = i;
      } else if ("minute".equals(str)) {
        k = i;
      } else if ("second".equals(str)) {
        j = i;
      }
    }
    paramzzaop.endObject();
    return new GregorianCalendar(i2, i1, n, m, k, j);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */