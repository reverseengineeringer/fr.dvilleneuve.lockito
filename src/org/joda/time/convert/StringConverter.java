package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.ReadWritableInterval;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

class StringConverter
  extends AbstractConverter
  implements InstantConverter, PartialConverter, DurationConverter, PeriodConverter, IntervalConverter
{
  static final StringConverter INSTANCE = new StringConverter();
  
  public long getDurationMillis(Object paramObject)
  {
    paramObject = (String)paramObject;
    int i = ((String)paramObject).length();
    String str;
    int k;
    int j;
    if ((i >= 4) && ((((String)paramObject).charAt(0) == 'P') || (((String)paramObject).charAt(0) == 'p')) && ((((String)paramObject).charAt(1) == 'T') || (((String)paramObject).charAt(1) == 't')) && ((((String)paramObject).charAt(i - 1) == 'S') || (((String)paramObject).charAt(i - 1) == 's')))
    {
      str = ((String)paramObject).substring(2, i - 1);
      k = -1;
      j = 0;
      i = 0;
      if (i >= str.length()) {
        break label260;
      }
      if ((str.charAt(i) < '0') || (str.charAt(i) > '9')) {
        break label166;
      }
    }
    for (;;)
    {
      i += 1;
      break;
      throw new IllegalArgumentException("Invalid format: \"" + (String)paramObject + '"');
      label166:
      if ((i != 0) || (str.charAt(0) != '-')) {
        break label186;
      }
      j = 1;
    }
    label186:
    if (j != 0) {}
    for (int m = 1;; m = 0)
    {
      if ((i <= m) || (str.charAt(i) != '.') || (k != -1)) {
        break label228;
      }
      k = i;
      break;
    }
    label228:
    throw new IllegalArgumentException("Invalid format: \"" + (String)paramObject + '"');
    label260:
    long l2 = 0L;
    long l1;
    if (j != 0)
    {
      i = 1;
      if (k <= 0) {
        break label368;
      }
      l1 = Long.parseLong(str.substring(i, k));
      str = str.substring(k + 1);
      paramObject = str;
      if (str.length() != 3) {
        paramObject = (str + "000").substring(0, 3);
      }
      l2 = Integer.parseInt((String)paramObject);
    }
    for (;;)
    {
      if (j == 0) {
        break label401;
      }
      return FieldUtils.safeAdd(FieldUtils.safeMultiply(-l1, 1000), -l2);
      i = 0;
      break;
      label368:
      if (j != 0) {
        l1 = Long.parseLong(str.substring(i, str.length()));
      } else {
        l1 = Long.parseLong(str);
      }
    }
    label401:
    return FieldUtils.safeAdd(FieldUtils.safeMultiply(l1, 1000), l2);
  }
  
  public long getInstantMillis(Object paramObject, Chronology paramChronology)
  {
    paramObject = (String)paramObject;
    return ISODateTimeFormat.dateTimeParser().withChronology(paramChronology).parseMillis((String)paramObject);
  }
  
  public int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter)
  {
    Chronology localChronology = paramChronology;
    if (paramDateTimeFormatter.getZone() != null) {
      localChronology = paramChronology.withZone(paramDateTimeFormatter.getZone());
    }
    return localChronology.get(paramReadablePartial, paramDateTimeFormatter.withChronology(localChronology).parseMillis((String)paramObject));
  }
  
  public Class<?> getSupportedType()
  {
    return String.class;
  }
  
  public void setInto(ReadWritableInterval paramReadWritableInterval, Object paramObject, Chronology paramChronology)
  {
    Object localObject = (String)paramObject;
    int i = ((String)localObject).indexOf('/');
    if (i < 0) {
      throw new IllegalArgumentException("Format requires a '/' separator: " + (String)localObject);
    }
    String str2 = ((String)localObject).substring(0, i);
    if (str2.length() <= 0) {
      throw new IllegalArgumentException("Format invalid: " + (String)localObject);
    }
    String str1 = ((String)localObject).substring(i + 1);
    if (str1.length() <= 0) {
      throw new IllegalArgumentException("Format invalid: " + (String)localObject);
    }
    DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.dateTimeParser().withChronology(paramChronology);
    PeriodFormatter localPeriodFormatter = ISOPeriodFormat.standard();
    long l1 = 0L;
    Period localPeriod = null;
    paramObject = null;
    i = str2.charAt(0);
    if ((i == 80) || (i == 112)) {
      localPeriod = localPeriodFormatter.withParseType(getPeriodType(str2)).parsePeriod(str2);
    }
    for (;;)
    {
      i = str1.charAt(0);
      if ((i != 80) && (i != 112)) {
        break label338;
      }
      if (localPeriod == null) {
        break;
      }
      throw new IllegalArgumentException("Interval composed of two durations: " + (String)localObject);
      paramObject = localDateTimeFormatter.parseDateTime(str2);
      l1 = ((DateTime)paramObject).getMillis();
      paramObject = ((DateTime)paramObject).getChronology();
    }
    localPeriod = localPeriodFormatter.withParseType(getPeriodType(str1)).parsePeriod(str1);
    if (paramChronology != null) {}
    long l2;
    for (;;)
    {
      l2 = paramChronology.add(localPeriod, l1, 1);
      paramObject = paramChronology;
      paramReadWritableInterval.setInterval(l1, l2);
      paramReadWritableInterval.setChronology((Chronology)paramObject);
      return;
      paramChronology = (Chronology)paramObject;
    }
    label338:
    localObject = localDateTimeFormatter.parseDateTime(str1);
    long l3 = ((DateTime)localObject).getMillis();
    if (paramObject != null) {
      label358:
      if (paramChronology == null) {
        break label402;
      }
    }
    for (;;)
    {
      l2 = l3;
      paramObject = paramChronology;
      if (localPeriod == null) {
        break;
      }
      l1 = paramChronology.add(localPeriod, l3, -1);
      l2 = l3;
      paramObject = paramChronology;
      break;
      paramObject = ((DateTime)localObject).getChronology();
      break label358;
      label402:
      paramChronology = (Chronology)paramObject;
    }
  }
  
  public void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology)
  {
    paramObject = (String)paramObject;
    paramChronology = ISOPeriodFormat.standard();
    paramReadWritablePeriod.clear();
    int i = paramChronology.parseInto(paramReadWritablePeriod, (String)paramObject, 0);
    if (i < ((String)paramObject).length())
    {
      if (i < 0) {
        paramChronology.withParseType(paramReadWritablePeriod.getPeriodType()).parseMutablePeriod((String)paramObject);
      }
      throw new IllegalArgumentException("Invalid format: \"" + (String)paramObject + '"');
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.StringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */