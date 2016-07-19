package org.joda.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class IllegalInstantException
  extends IllegalArgumentException
{
  private static final long serialVersionUID = 2858712538216L;
  
  public IllegalInstantException(long paramLong, String paramString)
  {
    super(createMessage(paramLong, paramString));
  }
  
  public IllegalInstantException(String paramString)
  {
    super(paramString);
  }
  
  private static String createMessage(long paramLong, String paramString)
  {
    String str = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(paramLong));
    if (paramString != null) {}
    for (paramString = " (" + paramString + ")";; paramString = "") {
      return "Illegal instant due to time zone offset transition (daylight savings time 'gap'): " + str + paramString;
    }
  }
  
  public static boolean isIllegalInstant(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof IllegalInstantException)) {
      return true;
    }
    if ((paramThrowable.getCause() != null) && (paramThrowable.getCause() != paramThrowable)) {
      return isIllegalInstant(paramThrowable.getCause());
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.IllegalInstantException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */