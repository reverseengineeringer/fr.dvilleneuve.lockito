package org.joda.time.field;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.Interval;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public abstract class AbstractReadableInstantFieldProperty
  implements Serializable
{
  private static final long serialVersionUID = 1971226328211649661L;
  
  public int compareTo(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      throw new IllegalArgumentException("The instant must not be null");
    }
    int i = get();
    int j = paramReadableInstant.get(getFieldType());
    if (i < j) {
      return -1;
    }
    if (i > j) {
      return 1;
    }
    return 0;
  }
  
  public int compareTo(ReadablePartial paramReadablePartial)
  {
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("The partial must not be null");
    }
    int i = get();
    int j = paramReadablePartial.get(getFieldType());
    if (i < j) {
      return -1;
    }
    if (i > j) {
      return 1;
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof AbstractReadableInstantFieldProperty)) {
        return false;
      }
      paramObject = (AbstractReadableInstantFieldProperty)paramObject;
    } while ((get() == ((AbstractReadableInstantFieldProperty)paramObject).get()) && (getFieldType().equals(((AbstractReadableInstantFieldProperty)paramObject).getFieldType())) && (FieldUtils.equals(getChronology(), ((AbstractReadableInstantFieldProperty)paramObject).getChronology())));
    return false;
  }
  
  public int get()
  {
    return getField().get(getMillis());
  }
  
  public String getAsShortText()
  {
    return getAsShortText(null);
  }
  
  public String getAsShortText(Locale paramLocale)
  {
    return getField().getAsShortText(getMillis(), paramLocale);
  }
  
  public String getAsString()
  {
    return Integer.toString(get());
  }
  
  public String getAsText()
  {
    return getAsText(null);
  }
  
  public String getAsText(Locale paramLocale)
  {
    return getField().getAsText(getMillis(), paramLocale);
  }
  
  protected Chronology getChronology()
  {
    throw new UnsupportedOperationException("The method getChronology() was added in v1.4 and needs to be implemented by subclasses of AbstractReadableInstantFieldProperty");
  }
  
  public int getDifference(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      return getField().getDifference(getMillis(), DateTimeUtils.currentTimeMillis());
    }
    return getField().getDifference(getMillis(), paramReadableInstant.getMillis());
  }
  
  public long getDifferenceAsLong(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      return getField().getDifferenceAsLong(getMillis(), DateTimeUtils.currentTimeMillis());
    }
    return getField().getDifferenceAsLong(getMillis(), paramReadableInstant.getMillis());
  }
  
  public DurationField getDurationField()
  {
    return getField().getDurationField();
  }
  
  public abstract DateTimeField getField();
  
  public DateTimeFieldType getFieldType()
  {
    return getField().getType();
  }
  
  public int getLeapAmount()
  {
    return getField().getLeapAmount(getMillis());
  }
  
  public DurationField getLeapDurationField()
  {
    return getField().getLeapDurationField();
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    return getField().getMaximumShortTextLength(paramLocale);
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return getField().getMaximumTextLength(paramLocale);
  }
  
  public int getMaximumValue()
  {
    return getField().getMaximumValue(getMillis());
  }
  
  public int getMaximumValueOverall()
  {
    return getField().getMaximumValue();
  }
  
  protected abstract long getMillis();
  
  public int getMinimumValue()
  {
    return getField().getMinimumValue(getMillis());
  }
  
  public int getMinimumValueOverall()
  {
    return getField().getMinimumValue();
  }
  
  public String getName()
  {
    return getField().getName();
  }
  
  public DurationField getRangeDurationField()
  {
    return getField().getRangeDurationField();
  }
  
  public int hashCode()
  {
    return get() * 17 + getFieldType().hashCode() + getChronology().hashCode();
  }
  
  public boolean isLeap()
  {
    return getField().isLeap(getMillis());
  }
  
  public long remainder()
  {
    return getField().remainder(getMillis());
  }
  
  public Interval toInterval()
  {
    DateTimeField localDateTimeField = getField();
    long l = localDateTimeField.roundFloor(getMillis());
    return new Interval(l, localDateTimeField.add(l, 1));
  }
  
  public String toString()
  {
    return "Property[" + getName() + "]";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.AbstractReadableInstantFieldProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */