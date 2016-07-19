package com.google.android.gms.internal;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

final class zzamn
  implements zzamx<java.util.Date>, zzang<java.util.Date>
{
  private final DateFormat bdO;
  private final DateFormat bdP;
  private final DateFormat bdQ;
  
  zzamn()
  {
    this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
  }
  
  public zzamn(int paramInt1, int paramInt2)
  {
    this(DateFormat.getDateTimeInstance(paramInt1, paramInt2, Locale.US), DateFormat.getDateTimeInstance(paramInt1, paramInt2));
  }
  
  zzamn(String paramString)
  {
    this(new SimpleDateFormat(paramString, Locale.US), new SimpleDateFormat(paramString));
  }
  
  zzamn(DateFormat paramDateFormat1, DateFormat paramDateFormat2)
  {
    bdO = paramDateFormat1;
    bdP = paramDateFormat2;
    bdQ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    bdQ.setTimeZone(TimeZone.getTimeZone("UTC"));
  }
  
  private java.util.Date zza(zzamy paramzzamy)
  {
    java.util.Date localDate2;
    synchronized (bdP)
    {
      try
      {
        java.util.Date localDate1 = bdP.parse(paramzzamy.zzczh());
        return localDate1;
      }
      catch (ParseException localParseException1) {}
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(zzamn.class.getSimpleName());
    localStringBuilder.append('(').append(bdP.getClass().getSimpleName()).append(')');
    return localStringBuilder.toString();
  }
  
  public zzamy zza(java.util.Date paramDate, Type arg2, zzanf paramzzanf)
  {
    synchronized (bdP)
    {
      paramDate = new zzane(bdO.format(paramDate));
      return paramDate;
    }
  }
  
  public java.util.Date zza(zzamy paramzzamy, Type paramType, zzamw paramzzamw)
    throws zzanc
  {
    if (!(paramzzamy instanceof zzane)) {
      throw new zzanc("The date should be a string value");
    }
    paramzzamy = zza(paramzzamy);
    if (paramType == java.util.Date.class) {
      return paramzzamy;
    }
    if (paramType == Timestamp.class) {
      return new Timestamp(paramzzamy.getTime());
    }
    if (paramType == java.sql.Date.class) {
      return new java.sql.Date(paramzzamy.getTime());
    }
    paramzzamy = String.valueOf(getClass());
    paramType = String.valueOf(paramType);
    throw new IllegalArgumentException(String.valueOf(paramzzamy).length() + 23 + String.valueOf(paramType).length() + paramzzamy + " cannot deserialize to " + paramType);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzamn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */