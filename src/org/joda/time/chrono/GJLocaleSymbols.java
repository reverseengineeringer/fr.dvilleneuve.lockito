package org.joda.time.chrono;

import java.lang.ref.WeakReference;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.TreeMap;
import java.util.WeakHashMap;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.IllegalFieldValueException;

class GJLocaleSymbols
{
  private static final int FAST_CACHE_SIZE = 64;
  private static WeakHashMap<Locale, GJLocaleSymbols> cCache = new WeakHashMap();
  private static final GJLocaleSymbols[] cFastCache = new GJLocaleSymbols[64];
  private final String[] iDaysOfWeek;
  private final String[] iEras;
  private final String[] iHalfday;
  private final WeakReference<Locale> iLocale;
  private final int iMaxDayOfWeekLength;
  private final int iMaxEraLength;
  private final int iMaxHalfdayLength;
  private final int iMaxMonthLength;
  private final int iMaxShortDayOfWeekLength;
  private final int iMaxShortMonthLength;
  private final String[] iMonths;
  private final TreeMap<String, Integer> iParseDaysOfWeek;
  private final TreeMap<String, Integer> iParseEras;
  private final TreeMap<String, Integer> iParseMonths;
  private final String[] iShortDaysOfWeek;
  private final String[] iShortMonths;
  
  private GJLocaleSymbols(Locale paramLocale)
  {
    iLocale = new WeakReference(paramLocale);
    Object localObject = DateTimeUtils.getDateFormatSymbols(paramLocale);
    iEras = ((DateFormatSymbols)localObject).getEras();
    iDaysOfWeek = realignDaysOfWeek(((DateFormatSymbols)localObject).getWeekdays());
    iShortDaysOfWeek = realignDaysOfWeek(((DateFormatSymbols)localObject).getShortWeekdays());
    iMonths = realignMonths(((DateFormatSymbols)localObject).getMonths());
    iShortMonths = realignMonths(((DateFormatSymbols)localObject).getShortMonths());
    iHalfday = ((DateFormatSymbols)localObject).getAmPmStrings();
    localObject = new Integer[13];
    int i = 0;
    while (i < 13)
    {
      localObject[i] = Integer.valueOf(i);
      i += 1;
    }
    iParseEras = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    addSymbols(iParseEras, iEras, (Integer[])localObject);
    if ("en".equals(paramLocale.getLanguage()))
    {
      iParseEras.put("BCE", localObject[0]);
      iParseEras.put("CE", localObject[1]);
    }
    iParseDaysOfWeek = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    addSymbols(iParseDaysOfWeek, iDaysOfWeek, (Integer[])localObject);
    addSymbols(iParseDaysOfWeek, iShortDaysOfWeek, (Integer[])localObject);
    addNumerals(iParseDaysOfWeek, 1, 7, (Integer[])localObject);
    iParseMonths = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    addSymbols(iParseMonths, iMonths, (Integer[])localObject);
    addSymbols(iParseMonths, iShortMonths, (Integer[])localObject);
    addNumerals(iParseMonths, 1, 12, (Integer[])localObject);
    iMaxEraLength = maxLength(iEras);
    iMaxDayOfWeekLength = maxLength(iDaysOfWeek);
    iMaxShortDayOfWeekLength = maxLength(iShortDaysOfWeek);
    iMaxMonthLength = maxLength(iMonths);
    iMaxShortMonthLength = maxLength(iShortMonths);
    iMaxHalfdayLength = maxLength(iHalfday);
  }
  
  private static void addNumerals(TreeMap<String, Integer> paramTreeMap, int paramInt1, int paramInt2, Integer[] paramArrayOfInteger)
  {
    while (paramInt1 <= paramInt2)
    {
      paramTreeMap.put(String.valueOf(paramInt1).intern(), paramArrayOfInteger[paramInt1]);
      paramInt1 += 1;
    }
  }
  
  private static void addSymbols(TreeMap<String, Integer> paramTreeMap, String[] paramArrayOfString, Integer[] paramArrayOfInteger)
  {
    int i = paramArrayOfString.length;
    for (;;)
    {
      int j = i - 1;
      if (j < 0) {
        break;
      }
      String str = paramArrayOfString[j];
      i = j;
      if (str != null)
      {
        paramTreeMap.put(str, paramArrayOfInteger[j]);
        i = j;
      }
    }
  }
  
  public static GJLocaleSymbols forLocale(Locale paramLocale)
  {
    Locale localLocale = paramLocale;
    if (paramLocale == null) {
      localLocale = Locale.getDefault();
    }
    int i = System.identityHashCode(localLocale) & 0x3F;
    paramLocale = cFastCache[i];
    if ((paramLocale != null) && (iLocale.get() == localLocale)) {
      return paramLocale;
    }
    synchronized (cCache)
    {
      GJLocaleSymbols localGJLocaleSymbols = (GJLocaleSymbols)cCache.get(localLocale);
      paramLocale = localGJLocaleSymbols;
      if (localGJLocaleSymbols == null) {
        paramLocale = new GJLocaleSymbols(localLocale);
      }
    }
    throw paramLocale;
  }
  
  private static int maxLength(String[] paramArrayOfString)
  {
    int j = 0;
    int i = paramArrayOfString.length;
    for (;;)
    {
      int k = i - 1;
      if (k < 0) {
        break;
      }
      String str = paramArrayOfString[k];
      i = k;
      if (str != null)
      {
        int m = str.length();
        i = k;
        if (m > j)
        {
          j = m;
          i = k;
        }
      }
    }
    return j;
  }
  
  private static String[] realignDaysOfWeek(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[8];
    int i = 1;
    if (i < 8)
    {
      if (i < 7) {}
      for (int j = i + 1;; j = 1)
      {
        arrayOfString[i] = paramArrayOfString[j];
        i += 1;
        break;
      }
    }
    return arrayOfString;
  }
  
  private static String[] realignMonths(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[13];
    int i = 1;
    while (i < 13)
    {
      arrayOfString[i] = paramArrayOfString[(i - 1)];
      i += 1;
    }
    return arrayOfString;
  }
  
  public int dayOfWeekTextToValue(String paramString)
  {
    Integer localInteger = (Integer)iParseDaysOfWeek.get(paramString);
    if (localInteger != null) {
      return localInteger.intValue();
    }
    throw new IllegalFieldValueException(DateTimeFieldType.dayOfWeek(), paramString);
  }
  
  public String dayOfWeekValueToShortText(int paramInt)
  {
    return iShortDaysOfWeek[paramInt];
  }
  
  public String dayOfWeekValueToText(int paramInt)
  {
    return iDaysOfWeek[paramInt];
  }
  
  public int eraTextToValue(String paramString)
  {
    Integer localInteger = (Integer)iParseEras.get(paramString);
    if (localInteger != null) {
      return localInteger.intValue();
    }
    throw new IllegalFieldValueException(DateTimeFieldType.era(), paramString);
  }
  
  public String eraValueToText(int paramInt)
  {
    return iEras[paramInt];
  }
  
  public int getDayOfWeekMaxShortTextLength()
  {
    return iMaxShortDayOfWeekLength;
  }
  
  public int getDayOfWeekMaxTextLength()
  {
    return iMaxDayOfWeekLength;
  }
  
  public int getEraMaxTextLength()
  {
    return iMaxEraLength;
  }
  
  public int getHalfdayMaxTextLength()
  {
    return iMaxHalfdayLength;
  }
  
  public int getMonthMaxShortTextLength()
  {
    return iMaxShortMonthLength;
  }
  
  public int getMonthMaxTextLength()
  {
    return iMaxMonthLength;
  }
  
  public int halfdayTextToValue(String paramString)
  {
    String[] arrayOfString = iHalfday;
    int i = arrayOfString.length;
    int j;
    do
    {
      j = i - 1;
      if (j < 0) {
        break;
      }
      i = j;
    } while (!arrayOfString[j].equalsIgnoreCase(paramString));
    return j;
    throw new IllegalFieldValueException(DateTimeFieldType.halfdayOfDay(), paramString);
  }
  
  public String halfdayValueToText(int paramInt)
  {
    return iHalfday[paramInt];
  }
  
  public int monthOfYearTextToValue(String paramString)
  {
    Integer localInteger = (Integer)iParseMonths.get(paramString);
    if (localInteger != null) {
      return localInteger.intValue();
    }
    throw new IllegalFieldValueException(DateTimeFieldType.monthOfYear(), paramString);
  }
  
  public String monthOfYearValueToShortText(int paramInt)
  {
    return iShortMonths[paramInt];
  }
  
  public String monthOfYearValueToText(int paramInt)
  {
    return iMonths[paramInt];
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJLocaleSymbols
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */