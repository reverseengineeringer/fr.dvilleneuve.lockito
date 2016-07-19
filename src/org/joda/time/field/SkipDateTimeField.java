package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

public final class SkipDateTimeField
  extends DelegatedDateTimeField
{
  private static final long serialVersionUID = -8869148464118507846L;
  private final Chronology iChronology;
  private transient int iMinValue;
  private final int iSkip;
  
  public SkipDateTimeField(Chronology paramChronology, DateTimeField paramDateTimeField)
  {
    this(paramChronology, paramDateTimeField, 0);
  }
  
  public SkipDateTimeField(Chronology paramChronology, DateTimeField paramDateTimeField, int paramInt)
  {
    super(paramDateTimeField);
    iChronology = paramChronology;
    int i = super.getMinimumValue();
    if (i < paramInt) {
      iMinValue = (i - 1);
    }
    for (;;)
    {
      iSkip = paramInt;
      return;
      if (i == paramInt) {
        iMinValue = (paramInt + 1);
      } else {
        iMinValue = i;
      }
    }
  }
  
  private Object readResolve()
  {
    return getType().getField(iChronology);
  }
  
  public int get(long paramLong)
  {
    int j = super.get(paramLong);
    int i = j;
    if (j <= iSkip) {
      i = j - 1;
    }
    return i;
  }
  
  public int getMinimumValue()
  {
    return iMinValue;
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, iMinValue, getMaximumValue());
    int i = paramInt;
    if (paramInt <= iSkip)
    {
      if (paramInt == iSkip) {
        throw new IllegalFieldValueException(DateTimeFieldType.year(), Integer.valueOf(paramInt), null, null);
      }
      i = paramInt + 1;
    }
    return super.set(paramLong, i);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.SkipDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */