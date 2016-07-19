package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.MutableDateTime.Property;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$TextField
  implements DateTimePrinter, DateTimeParser
{
  private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new HashMap();
  private final DateTimeFieldType iFieldType;
  private final boolean iShort;
  
  DateTimeFormatterBuilder$TextField(DateTimeFieldType paramDateTimeFieldType, boolean paramBoolean)
  {
    iFieldType = paramDateTimeFieldType;
    iShort = paramBoolean;
  }
  
  private String print(long paramLong, Chronology paramChronology, Locale paramLocale)
  {
    paramChronology = iFieldType.getField(paramChronology);
    if (iShort) {
      return paramChronology.getAsShortText(paramLong, paramLocale);
    }
    return paramChronology.getAsText(paramLong, paramLocale);
  }
  
  private String print(ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    if (paramReadablePartial.isSupported(iFieldType))
    {
      DateTimeField localDateTimeField = iFieldType.getField(paramReadablePartial.getChronology());
      if (iShort) {
        return localDateTimeField.getAsShortText(paramReadablePartial, paramLocale);
      }
      return localDateTimeField.getAsText(paramReadablePartial, paramLocale);
    }
    return "�";
  }
  
  public int estimateParsedLength()
  {
    return estimatePrintedLength();
  }
  
  public int estimatePrintedLength()
  {
    if (iShort) {
      return 6;
    }
    return 20;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    Locale localLocale = paramDateTimeParserBucket.getLocale();
    synchronized (cParseCache)
    {
      Object localObject2 = (Map)cParseCache.get(localLocale);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new HashMap();
        cParseCache.put(localLocale, localObject1);
      }
      localObject2 = (Object[])((Map)localObject1).get(iFieldType);
      if (localObject2 == null) {
        localObject2 = new HashSet(32);
      }
      for (;;)
      {
        try
        {
          MutableDateTime.Property localProperty = new MutableDateTime(0L, DateTimeZone.UTC).property(iFieldType);
          i = localProperty.getMinimumValueOverall();
          int k = localProperty.getMaximumValueOverall();
          if (k - i > 32) {
            return paramInt ^ 0xFFFFFFFF;
          }
          int j = localProperty.getMaximumTextLength(localLocale);
          if (i <= k)
          {
            localProperty.set(i);
            ((Set)localObject2).add(localProperty.getAsShortText(localLocale));
            ((Set)localObject2).add(localProperty.getAsShortText(localLocale).toLowerCase(localLocale));
            ((Set)localObject2).add(localProperty.getAsShortText(localLocale).toUpperCase(localLocale));
            ((Set)localObject2).add(localProperty.getAsText(localLocale));
            ((Set)localObject2).add(localProperty.getAsText(localLocale).toLowerCase(localLocale));
            ((Set)localObject2).add(localProperty.getAsText(localLocale).toUpperCase(localLocale));
            i += 1;
            continue;
          }
          i = j;
          if ("en".equals(localLocale.getLanguage()))
          {
            i = j;
            if (iFieldType == DateTimeFieldType.era())
            {
              ((Set)localObject2).add("BCE");
              ((Set)localObject2).add("bce");
              ((Set)localObject2).add("CE");
              ((Set)localObject2).add("ce");
              i = 3;
            }
          }
          ((Map)localObject1).put(iFieldType, new Object[] { localObject2, Integer.valueOf(i) });
          localObject1 = localObject2;
          i = Math.min(paramString.length(), paramInt + i);
          if (i <= paramInt) {
            continue;
          }
          localObject2 = paramString.substring(paramInt, i);
          if (!((Set)localObject1).contains(localObject2)) {
            continue;
          }
          paramDateTimeParserBucket.saveField(iFieldType, (String)localObject2, localLocale);
          return i;
        }
        finally
        {
          int i;
          continue;
        }
        localObject1 = (Set)localObject2[0];
        i = ((Integer)localObject2[1]).intValue();
        continue;
        throw paramDateTimeParserBucket;
        i -= 1;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    try
    {
      paramWriter.write(print(paramLong, paramChronology, paramLocale));
      return;
    }
    catch (RuntimeException paramChronology)
    {
      paramWriter.write(65533);
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    try
    {
      paramWriter.write(print(paramReadablePartial, paramLocale));
      return;
    }
    catch (RuntimeException paramReadablePartial)
    {
      paramWriter.write(65533);
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    try
    {
      paramStringBuffer.append(print(paramLong, paramChronology, paramLocale));
      return;
    }
    catch (RuntimeException paramChronology)
    {
      paramStringBuffer.append(65533);
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    try
    {
      paramStringBuffer.append(print(paramReadablePartial, paramLocale));
      return;
    }
    catch (RuntimeException paramReadablePartial)
    {
      paramStringBuffer.append(65533);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.TextField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */