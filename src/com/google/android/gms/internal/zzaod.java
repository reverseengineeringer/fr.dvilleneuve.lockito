package com.google.android.gms.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class zzaod
  extends zzank<Date>
{
  public static final zzanl bfE = new zzanl()
  {
    public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
    {
      if (paramAnonymouszzaoo.s() == Date.class) {
        return new zzaod();
      }
      return null;
    }
  };
  private final DateFormat bdO = DateFormat.getDateTimeInstance(2, 2, Locale.US);
  private final DateFormat bdP = DateFormat.getDateTimeInstance(2, 2);
  private final DateFormat bdQ = g();
  
  private static DateFormat g()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat;
  }
  
  private Date zzsz(String paramString)
  {
    try
    {
      Date localDate1 = bdP.parse(paramString);
      paramString = localDate1;
    }
    catch (ParseException localParseException1)
    {
      try
      {
        Date localDate2 = bdO.parse(paramString);
        paramString = localDate2;
      }
      catch (ParseException localParseException2)
      {
        try
        {
          Date localDate3 = bdQ.parse(paramString);
          paramString = localDate3;
        }
        catch (ParseException localParseException3)
        {
          throw new zzanh(paramString, localParseException3);
        }
      }
    }
    finally {}
    return paramString;
  }
  
  public void zza(zzaor paramzzaor, Date paramDate)
    throws IOException
  {
    if (paramDate == null) {}
    for (;;)
    {
      try
      {
        paramzzaor.r();
        return;
      }
      finally {}
      paramzzaor.zztb(bdO.format(paramDate));
    }
  }
  
  public Date zzk(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    return zzsz(paramzzaop.nextString());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */