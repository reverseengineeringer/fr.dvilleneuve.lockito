package org.joda.time.tz;

import java.util.StringTokenizer;
import org.joda.time.LocalDate;

class ZoneInfoCompiler$DateTimeOfYear
{
  public final boolean iAdvanceDayOfWeek;
  public final int iDayOfMonth;
  public final int iDayOfWeek;
  public final int iMillisOfDay;
  public final int iMonthOfYear;
  public final char iZoneChar;
  
  ZoneInfoCompiler$DateTimeOfYear()
  {
    iMonthOfYear = 1;
    iDayOfMonth = 1;
    iDayOfWeek = 0;
    iAdvanceDayOfWeek = false;
    iMillisOfDay = 0;
    iZoneChar = 'w';
  }
  
  ZoneInfoCompiler$DateTimeOfYear(StringTokenizer paramStringTokenizer)
  {
    int n = 1;
    int i = 1;
    int j = 0;
    int i2 = 0;
    boolean bool1 = false;
    char c2 = 'w';
    boolean bool2 = bool1;
    int m = i;
    int k = j;
    int i1 = i2;
    char c1 = c2;
    int i3;
    String str;
    if (paramStringTokenizer.hasMoreTokens())
    {
      i3 = ZoneInfoCompiler.parseMonth(paramStringTokenizer.nextToken());
      bool2 = bool1;
      m = i;
      k = j;
      i1 = i2;
      n = i3;
      c1 = c2;
      if (paramStringTokenizer.hasMoreTokens())
      {
        str = paramStringTokenizer.nextToken();
        if (!str.startsWith("last")) {
          break label273;
        }
        i = -1;
        j = ZoneInfoCompiler.parseDayOfWeek(str.substring(4));
        bool1 = false;
        bool2 = bool1;
        m = i;
        k = j;
        i1 = i2;
        n = i3;
        c1 = c2;
        if (paramStringTokenizer.hasMoreTokens())
        {
          paramStringTokenizer = paramStringTokenizer.nextToken();
          c1 = ZoneInfoCompiler.parseZoneChar(paramStringTokenizer.charAt(paramStringTokenizer.length() - 1));
          if (!paramStringTokenizer.equals("24:00")) {
            break label423;
          }
          if (i != -1) {
            break label395;
          }
          paramStringTokenizer = new LocalDate(2001, i3, 1).plusMonths(1);
          label199:
          if (i == -1) {
            break label417;
          }
          bool2 = true;
          label208:
          n = paramStringTokenizer.getMonthOfYear();
          m = paramStringTokenizer.getDayOfMonth();
          k = (j - 1 + 1) % 7 + 1;
          i1 = i2;
        }
      }
    }
    for (;;)
    {
      iMonthOfYear = n;
      iDayOfMonth = m;
      iDayOfWeek = k;
      iAdvanceDayOfWeek = bool2;
      iMillisOfDay = i1;
      iZoneChar = c1;
      return;
      try
      {
        label273:
        i = Integer.parseInt(str);
        j = 0;
        bool1 = false;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        j = str.indexOf(">=");
        if (j > 0)
        {
          i = Integer.parseInt(str.substring(j + 2));
          j = ZoneInfoCompiler.parseDayOfWeek(str.substring(0, j));
          bool1 = true;
          break;
        }
        j = str.indexOf("<=");
        if (j > 0)
        {
          i = Integer.parseInt(str.substring(j + 2));
          j = ZoneInfoCompiler.parseDayOfWeek(str.substring(0, j));
          bool1 = false;
          break;
        }
        throw new IllegalArgumentException(str);
      }
      label395:
      paramStringTokenizer = new LocalDate(2001, i3, i).plusDays(1);
      break label199;
      label417:
      bool2 = false;
      break label208;
      label423:
      i1 = ZoneInfoCompiler.parseTime(paramStringTokenizer);
      bool2 = bool1;
      m = i;
      k = j;
      n = i3;
    }
  }
  
  public void addCutover(DateTimeZoneBuilder paramDateTimeZoneBuilder, int paramInt)
  {
    paramDateTimeZoneBuilder.addCutover(paramInt, iZoneChar, iMonthOfYear, iDayOfMonth, iDayOfWeek, iAdvanceDayOfWeek, iMillisOfDay);
  }
  
  public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDateTimeZoneBuilder.addRecurringSavings(paramString, paramInt1, paramInt2, paramInt3, iZoneChar, iMonthOfYear, iDayOfMonth, iDayOfWeek, iAdvanceDayOfWeek, iMillisOfDay);
  }
  
  public String toString()
  {
    return "MonthOfYear: " + iMonthOfYear + "\n" + "DayOfMonth: " + iDayOfMonth + "\n" + "DayOfWeek: " + iDayOfWeek + "\n" + "AdvanceDayOfWeek: " + iAdvanceDayOfWeek + "\n" + "MillisOfDay: " + iMillisOfDay + "\n" + "ZoneChar: " + iZoneChar + "\n";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.ZoneInfoCompiler.DateTimeOfYear
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */