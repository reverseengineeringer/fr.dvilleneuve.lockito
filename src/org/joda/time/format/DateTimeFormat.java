package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public class DateTimeFormat
{
  static final int DATE = 0;
  static final int DATETIME = 2;
  static final int FULL = 0;
  static final int LONG = 1;
  static final int MEDIUM = 2;
  static final int NONE = 4;
  private static final Map<String, DateTimeFormatter> PATTERN_CACHE = new LinkedHashMap(7)
  {
    private static final long serialVersionUID = 23L;
    
    protected boolean removeEldestEntry(Map.Entry<String, DateTimeFormatter> paramAnonymousEntry)
    {
      return size() > 500;
    }
  };
  private static final int PATTERN_CACHE_SIZE = 500;
  static final int SHORT = 3;
  private static final DateTimeFormatter[] STYLE_CACHE = new DateTimeFormatter[25];
  static final int TIME = 1;
  
  static void appendPatternTo(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, String paramString)
  {
    parsePatternTo(paramDateTimeFormatterBuilder, paramString);
  }
  
  private static DateTimeFormatter createDateTimeFormatter(int paramInt1, int paramInt2)
  {
    int i = 2;
    if (paramInt1 == 4) {
      i = 1;
    }
    for (;;)
    {
      StyleFormatter localStyleFormatter = new StyleFormatter(paramInt1, paramInt2, i);
      return new DateTimeFormatter(localStyleFormatter, localStyleFormatter);
      if (paramInt2 == 4) {
        i = 0;
      }
    }
  }
  
  private static DateTimeFormatter createFormatterForPattern(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new IllegalArgumentException("Invalid pattern specification");
    }
    synchronized (PATTERN_CACHE)
    {
      DateTimeFormatter localDateTimeFormatter = (DateTimeFormatter)PATTERN_CACHE.get(paramString);
      Object localObject = localDateTimeFormatter;
      if (localDateTimeFormatter == null)
      {
        localObject = new DateTimeFormatterBuilder();
        parsePatternTo((DateTimeFormatterBuilder)localObject, paramString);
        localObject = ((DateTimeFormatterBuilder)localObject).toFormatter();
        PATTERN_CACHE.put(paramString, localObject);
      }
      return (DateTimeFormatter)localObject;
    }
  }
  
  private static DateTimeFormatter createFormatterForStyle(String paramString)
  {
    if ((paramString == null) || (paramString.length() != 2)) {
      throw new IllegalArgumentException("Invalid style specification: " + paramString);
    }
    int i = selectStyle(paramString.charAt(0));
    int j = selectStyle(paramString.charAt(1));
    if ((i == 4) && (j == 4)) {
      throw new IllegalArgumentException("Style '--' is invalid");
    }
    return createFormatterForStyleIndex(i, j);
  }
  
  private static DateTimeFormatter createFormatterForStyleIndex(int paramInt1, int paramInt2)
  {
    int i = (paramInt1 << 2) + paramInt1 + paramInt2;
    if (i >= STYLE_CACHE.length) {
      return createDateTimeFormatter(paramInt1, paramInt2);
    }
    synchronized (STYLE_CACHE)
    {
      DateTimeFormatter localDateTimeFormatter2 = STYLE_CACHE[i];
      DateTimeFormatter localDateTimeFormatter1 = localDateTimeFormatter2;
      if (localDateTimeFormatter2 == null)
      {
        localDateTimeFormatter1 = createDateTimeFormatter(paramInt1, paramInt2);
        STYLE_CACHE[i] = localDateTimeFormatter1;
      }
      return localDateTimeFormatter1;
    }
  }
  
  public static DateTimeFormatter forPattern(String paramString)
  {
    return createFormatterForPattern(paramString);
  }
  
  public static DateTimeFormatter forStyle(String paramString)
  {
    return createFormatterForStyle(paramString);
  }
  
  public static DateTimeFormatter fullDate()
  {
    return createFormatterForStyleIndex(0, 4);
  }
  
  public static DateTimeFormatter fullDateTime()
  {
    return createFormatterForStyleIndex(0, 0);
  }
  
  public static DateTimeFormatter fullTime()
  {
    return createFormatterForStyleIndex(4, 0);
  }
  
  private static boolean isNumericToken(String paramString)
  {
    boolean bool = true;
    int i = paramString.length();
    if (i > 0) {
      switch (paramString.charAt(0))
      {
      }
    }
    do
    {
      bool = false;
      return bool;
    } while (i > 2);
    return true;
  }
  
  public static DateTimeFormatter longDate()
  {
    return createFormatterForStyleIndex(1, 4);
  }
  
  public static DateTimeFormatter longDateTime()
  {
    return createFormatterForStyleIndex(1, 1);
  }
  
  public static DateTimeFormatter longTime()
  {
    return createFormatterForStyleIndex(4, 1);
  }
  
  public static DateTimeFormatter mediumDate()
  {
    return createFormatterForStyleIndex(2, 4);
  }
  
  public static DateTimeFormatter mediumDateTime()
  {
    return createFormatterForStyleIndex(2, 2);
  }
  
  public static DateTimeFormatter mediumTime()
  {
    return createFormatterForStyleIndex(4, 2);
  }
  
  private static void parsePatternTo(DateTimeFormatterBuilder paramDateTimeFormatterBuilder, String paramString)
  {
    int m = paramString.length();
    int[] arrayOfInt = new int[1];
    int i = 0;
    String str;
    int n;
    int k;
    if (i < m)
    {
      arrayOfInt[0] = i;
      str = parseToken(paramString, arrayOfInt);
      n = arrayOfInt[0];
      k = str.length();
      if (k != 0) {}
    }
    else
    {
      return;
    }
    int i1 = str.charAt(0);
    switch (i1)
    {
    default: 
      throw new IllegalArgumentException("Illegal pattern component: " + str);
    case 71: 
      paramDateTimeFormatterBuilder.appendEraText();
    }
    for (;;)
    {
      i = n + 1;
      break;
      paramDateTimeFormatterBuilder.appendCenturyOfEra(k, k);
      continue;
      if (k == 2)
      {
        boolean bool1 = true;
        boolean bool2 = true;
        if (n + 1 < m)
        {
          arrayOfInt[0] += 1;
          bool1 = bool2;
          if (isNumericToken(parseToken(paramString, arrayOfInt))) {
            bool1 = false;
          }
          arrayOfInt[0] -= 1;
        }
        switch (i1)
        {
        default: 
          paramDateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, bool1);
          break;
        case 120: 
          paramDateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, bool1);
          break;
        }
      }
      else
      {
        i = 9;
        int j = i;
        if (n + 1 < m)
        {
          arrayOfInt[0] += 1;
          if (isNumericToken(parseToken(paramString, arrayOfInt))) {
            i = k;
          }
          arrayOfInt[0] -= 1;
          j = i;
        }
        switch (i1)
        {
        default: 
          break;
        case 89: 
          paramDateTimeFormatterBuilder.appendYearOfEra(k, j);
          break;
        case 120: 
          paramDateTimeFormatterBuilder.appendWeekyear(k, j);
          break;
        case 121: 
          paramDateTimeFormatterBuilder.appendYear(k, j);
          continue;
          if (k >= 3)
          {
            if (k >= 4) {
              paramDateTimeFormatterBuilder.appendMonthOfYearText();
            } else {
              paramDateTimeFormatterBuilder.appendMonthOfYearShortText();
            }
          }
          else
          {
            paramDateTimeFormatterBuilder.appendMonthOfYear(k);
            continue;
            paramDateTimeFormatterBuilder.appendDayOfMonth(k);
            continue;
            paramDateTimeFormatterBuilder.appendHalfdayOfDayText();
            continue;
            paramDateTimeFormatterBuilder.appendClockhourOfHalfday(k);
            continue;
            paramDateTimeFormatterBuilder.appendHourOfDay(k);
            continue;
            paramDateTimeFormatterBuilder.appendClockhourOfDay(k);
            continue;
            paramDateTimeFormatterBuilder.appendHourOfHalfday(k);
            continue;
            paramDateTimeFormatterBuilder.appendMinuteOfHour(k);
            continue;
            paramDateTimeFormatterBuilder.appendSecondOfMinute(k);
            continue;
            paramDateTimeFormatterBuilder.appendFractionOfSecond(k, k);
            continue;
            paramDateTimeFormatterBuilder.appendDayOfWeek(k);
            continue;
            if (k >= 4)
            {
              paramDateTimeFormatterBuilder.appendDayOfWeekText();
            }
            else
            {
              paramDateTimeFormatterBuilder.appendDayOfWeekShortText();
              continue;
              paramDateTimeFormatterBuilder.appendDayOfYear(k);
              continue;
              paramDateTimeFormatterBuilder.appendWeekOfWeekyear(k);
              continue;
              if (k >= 4)
              {
                paramDateTimeFormatterBuilder.appendTimeZoneName();
              }
              else
              {
                paramDateTimeFormatterBuilder.appendTimeZoneShortName(null);
                continue;
                if (k == 1)
                {
                  paramDateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", false, 2, 2);
                }
                else if (k == 2)
                {
                  paramDateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", true, 2, 2);
                }
                else
                {
                  paramDateTimeFormatterBuilder.appendTimeZoneId();
                  continue;
                  str = str.substring(1);
                  if (str.length() == 1) {
                    paramDateTimeFormatterBuilder.appendLiteral(str.charAt(0));
                  } else {
                    paramDateTimeFormatterBuilder.appendLiteral(new String(str));
                  }
                }
              }
            }
          }
          break;
        }
      }
    }
  }
  
  private static String parseToken(String paramString, int[] paramArrayOfInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfInt[0];
    int m = paramString.length();
    char c = paramString.charAt(i);
    if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')))
    {
      localStringBuilder.append(c);
      for (;;)
      {
        j = i;
        if (i + 1 >= m) {
          break;
        }
        j = i;
        if (paramString.charAt(i + 1) != c) {
          break;
        }
        localStringBuilder.append(c);
        i += 1;
      }
    }
    localStringBuilder.append('\'');
    int k = 0;
    int j = i;
    if (i < m)
    {
      c = paramString.charAt(i);
      if (c == '\'') {
        if ((i + 1 < m) && (paramString.charAt(i + 1) == '\''))
        {
          i += 1;
          localStringBuilder.append(c);
          j = k;
        }
      }
    }
    for (;;)
    {
      i += 1;
      k = j;
      break;
      if (k == 0) {}
      for (j = 1;; j = 0) {
        break;
      }
      if ((k == 0) && (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))))
      {
        j = i - 1;
        paramArrayOfInt[0] = j;
        return localStringBuilder.toString();
      }
      localStringBuilder.append(c);
      j = k;
    }
  }
  
  public static String patternForStyle(String paramString, Locale paramLocale)
  {
    DateTimeFormatter localDateTimeFormatter = createFormatterForStyle(paramString);
    paramString = paramLocale;
    if (paramLocale == null) {
      paramString = Locale.getDefault();
    }
    return ((StyleFormatter)localDateTimeFormatter.getPrinter()).getPattern(paramString);
  }
  
  private static int selectStyle(char paramChar)
  {
    switch (paramChar)
    {
    default: 
      throw new IllegalArgumentException("Invalid style character: " + paramChar);
    case 'S': 
      return 3;
    case 'M': 
      return 2;
    case 'L': 
      return 1;
    case 'F': 
      return 0;
    }
    return 4;
  }
  
  public static DateTimeFormatter shortDate()
  {
    return createFormatterForStyleIndex(3, 4);
  }
  
  public static DateTimeFormatter shortDateTime()
  {
    return createFormatterForStyleIndex(3, 3);
  }
  
  public static DateTimeFormatter shortTime()
  {
    return createFormatterForStyleIndex(4, 3);
  }
  
  static class StyleFormatter
    implements DateTimePrinter, DateTimeParser
  {
    private static final Map<String, DateTimeFormatter> cCache = new HashMap();
    private final int iDateStyle;
    private final int iTimeStyle;
    private final int iType;
    
    StyleFormatter(int paramInt1, int paramInt2, int paramInt3)
    {
      iDateStyle = paramInt1;
      iTimeStyle = paramInt2;
      iType = paramInt3;
    }
    
    private DateTimeFormatter getFormatter(Locale paramLocale)
    {
      Locale localLocale = paramLocale;
      if (paramLocale == null) {
        localLocale = Locale.getDefault();
      }
      String str = Integer.toString(iType + (iDateStyle << 4) + (iTimeStyle << 8)) + localLocale.toString();
      synchronized (cCache)
      {
        DateTimeFormatter localDateTimeFormatter = (DateTimeFormatter)cCache.get(str);
        paramLocale = localDateTimeFormatter;
        if (localDateTimeFormatter == null)
        {
          paramLocale = DateTimeFormat.forPattern(getPattern(localLocale));
          cCache.put(str, paramLocale);
        }
        return paramLocale;
      }
    }
    
    public int estimateParsedLength()
    {
      return 40;
    }
    
    public int estimatePrintedLength()
    {
      return 40;
    }
    
    String getPattern(Locale paramLocale)
    {
      DateFormat localDateFormat = null;
      switch (iType)
      {
      }
      while (!(localDateFormat instanceof SimpleDateFormat))
      {
        throw new IllegalArgumentException("No datetime pattern for locale: " + paramLocale);
        localDateFormat = DateFormat.getDateInstance(iDateStyle, paramLocale);
        continue;
        localDateFormat = DateFormat.getTimeInstance(iTimeStyle, paramLocale);
        continue;
        localDateFormat = DateFormat.getDateTimeInstance(iDateStyle, iTimeStyle, paramLocale);
      }
      return ((SimpleDateFormat)localDateFormat).toPattern();
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      return getFormatter(paramDateTimeParserBucket.getLocale()).getParser().parseInto(paramDateTimeParserBucket, paramString, paramInt);
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      getFormatter(paramLocale).getPrinter().printTo(paramWriter, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      getFormatter(paramLocale).getPrinter().printTo(paramWriter, paramReadablePartial, paramLocale);
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      getFormatter(paramLocale).getPrinter().printTo(paramStringBuffer, paramLong, paramChronology, paramInt, paramDateTimeZone, paramLocale);
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      getFormatter(paramLocale).getPrinter().printTo(paramStringBuffer, paramReadablePartial, paramLocale);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */