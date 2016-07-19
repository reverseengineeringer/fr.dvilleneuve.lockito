package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public abstract class AbstractPartialFieldProperty
{
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
      throw new IllegalArgumentException("The instant must not be null");
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
      if (!(paramObject instanceof AbstractPartialFieldProperty)) {
        return false;
      }
      paramObject = (AbstractPartialFieldProperty)paramObject;
    } while ((get() == ((AbstractPartialFieldProperty)paramObject).get()) && (getFieldType() == ((AbstractPartialFieldProperty)paramObject).getFieldType()) && (FieldUtils.equals(getReadablePartial().getChronology(), ((AbstractPartialFieldProperty)paramObject).getReadablePartial().getChronology())));
    return false;
  }
  
  public abstract int get();
  
  public String getAsShortText()
  {
    return getAsShortText(null);
  }
  
  public String getAsShortText(Locale paramLocale)
  {
    return getField().getAsShortText(getReadablePartial(), get(), paramLocale);
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
    return getField().getAsText(getReadablePartial(), get(), paramLocale);
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
    return getField().getMaximumValue(getReadablePartial());
  }
  
  public int getMaximumValueOverall()
  {
    return getField().getMaximumValue();
  }
  
  public int getMinimumValue()
  {
    return getField().getMinimumValue(getReadablePartial());
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
  
  protected abstract ReadablePartial getReadablePartial();
  
  public int hashCode()
  {
    return ((get() + 247) * 13 + getFieldType().hashCode()) * 13 + getReadablePartial().getChronology().hashCode();
  }
  
  public String toString()
  {
    return "Property[" + getName() + "]";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.AbstractPartialFieldProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */