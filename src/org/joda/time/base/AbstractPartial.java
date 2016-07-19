package org.joda.time.base;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractPartial
  implements ReadablePartial, Comparable<ReadablePartial>
{
  public int compareTo(ReadablePartial paramReadablePartial)
  {
    if (this == paramReadablePartial) {}
    for (;;)
    {
      return 0;
      if (size() != paramReadablePartial.size()) {
        throw new ClassCastException("ReadablePartial objects must have matching field types");
      }
      int i = 0;
      int j = size();
      while (i < j)
      {
        if (getFieldType(i) != paramReadablePartial.getFieldType(i)) {
          throw new ClassCastException("ReadablePartial objects must have matching field types");
        }
        i += 1;
      }
      i = 0;
      j = size();
      while (i < j)
      {
        if (getValue(i) > paramReadablePartial.getValue(i)) {
          return 1;
        }
        if (getValue(i) < paramReadablePartial.getValue(i)) {
          return -1;
        }
        i += 1;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!(paramObject instanceof ReadablePartial));
      paramObject = (ReadablePartial)paramObject;
      bool1 = bool2;
    } while (size() != ((ReadablePartial)paramObject).size());
    int i = 0;
    int j = size();
    for (;;)
    {
      if (i >= j) {
        break label104;
      }
      bool1 = bool2;
      if (getValue(i) != ((ReadablePartial)paramObject).getValue(i)) {
        break;
      }
      bool1 = bool2;
      if (getFieldType(i) != ((ReadablePartial)paramObject).getFieldType(i)) {
        break;
      }
      i += 1;
    }
    label104:
    return FieldUtils.equals(getChronology(), ((ReadablePartial)paramObject).getChronology());
  }
  
  public int get(DateTimeFieldType paramDateTimeFieldType)
  {
    return getValue(indexOfSupported(paramDateTimeFieldType));
  }
  
  public DateTimeField getField(int paramInt)
  {
    return getField(paramInt, getChronology());
  }
  
  protected abstract DateTimeField getField(int paramInt, Chronology paramChronology);
  
  public DateTimeFieldType getFieldType(int paramInt)
  {
    return getField(paramInt, getChronology()).getType();
  }
  
  public DateTimeFieldType[] getFieldTypes()
  {
    DateTimeFieldType[] arrayOfDateTimeFieldType = new DateTimeFieldType[size()];
    int i = 0;
    while (i < arrayOfDateTimeFieldType.length)
    {
      arrayOfDateTimeFieldType[i] = getFieldType(i);
      i += 1;
    }
    return arrayOfDateTimeFieldType;
  }
  
  public DateTimeField[] getFields()
  {
    DateTimeField[] arrayOfDateTimeField = new DateTimeField[size()];
    int i = 0;
    while (i < arrayOfDateTimeField.length)
    {
      arrayOfDateTimeField[i] = getField(i);
      i += 1;
    }
    return arrayOfDateTimeField;
  }
  
  public int[] getValues()
  {
    int[] arrayOfInt = new int[size()];
    int i = 0;
    while (i < arrayOfInt.length)
    {
      arrayOfInt[i] = getValue(i);
      i += 1;
    }
    return arrayOfInt;
  }
  
  public int hashCode()
  {
    int j = 157;
    int i = 0;
    int k = size();
    while (i < k)
    {
      j = (j * 23 + getValue(i)) * 23 + getFieldType(i).hashCode();
      i += 1;
    }
    return j + getChronology().hashCode();
  }
  
  public int indexOf(DateTimeFieldType paramDateTimeFieldType)
  {
    int i = 0;
    int j = size();
    while (i < j)
    {
      if (getFieldType(i) == paramDateTimeFieldType) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  protected int indexOf(DurationFieldType paramDurationFieldType)
  {
    int i = 0;
    int j = size();
    while (i < j)
    {
      if (getFieldType(i).getDurationType() == paramDurationFieldType) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  protected int indexOfSupported(DateTimeFieldType paramDateTimeFieldType)
  {
    int i = indexOf(paramDateTimeFieldType);
    if (i == -1) {
      throw new IllegalArgumentException("Field '" + paramDateTimeFieldType + "' is not supported");
    }
    return i;
  }
  
  protected int indexOfSupported(DurationFieldType paramDurationFieldType)
  {
    int i = indexOf(paramDurationFieldType);
    if (i == -1) {
      throw new IllegalArgumentException("Field '" + paramDurationFieldType + "' is not supported");
    }
    return i;
  }
  
  public boolean isAfter(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("Partial cannot be null");
    }
    return compareTo(paramReadablePartial) > 0;
  }
  
  public boolean isBefore(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("Partial cannot be null");
    }
    return compareTo(paramReadablePartial) < 0;
  }
  
  public boolean isEqual(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("Partial cannot be null");
    }
    return compareTo(paramReadablePartial) == 0;
  }
  
  public boolean isSupported(DateTimeFieldType paramDateTimeFieldType)
  {
    return indexOf(paramDateTimeFieldType) != -1;
  }
  
  public DateTime toDateTime(ReadableInstant paramReadableInstant)
  {
    Chronology localChronology = DateTimeUtils.getInstantChronology(paramReadableInstant);
    return new DateTime(localChronology.set(this, DateTimeUtils.getInstantMillis(paramReadableInstant)), localChronology);
  }
  
  public String toString(DateTimeFormatter paramDateTimeFormatter)
  {
    if (paramDateTimeFormatter == null) {
      return toString();
    }
    return paramDateTimeFormatter.print(this);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.base.AbstractPartial
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */