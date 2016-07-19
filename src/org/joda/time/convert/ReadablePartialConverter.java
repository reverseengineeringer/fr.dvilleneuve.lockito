package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class ReadablePartialConverter
  extends AbstractConverter
  implements PartialConverter
{
  static final ReadablePartialConverter INSTANCE = new ReadablePartialConverter();
  
  public Chronology getChronology(Object paramObject, Chronology paramChronology)
  {
    Chronology localChronology = paramChronology;
    if (paramChronology == null) {
      localChronology = DateTimeUtils.getChronology(((ReadablePartial)paramObject).getChronology());
    }
    return localChronology;
  }
  
  public Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone)
  {
    return getChronology(paramObject, (Chronology)null).withZone(paramDateTimeZone);
  }
  
  public int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology)
  {
    paramObject = (ReadablePartial)paramObject;
    int j = paramReadablePartial.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = ((ReadablePartial)paramObject).get(paramReadablePartial.getFieldType(i));
      i += 1;
    }
    paramChronology.validate(paramReadablePartial, arrayOfInt);
    return arrayOfInt;
  }
  
  public Class<?> getSupportedType()
  {
    return ReadablePartial.class;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.convert.ReadablePartialConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */