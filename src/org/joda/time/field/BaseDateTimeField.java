package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;

public abstract class BaseDateTimeField
  extends DateTimeField
{
  private final DateTimeFieldType iType;
  
  protected BaseDateTimeField(DateTimeFieldType paramDateTimeFieldType)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("The type must not be null");
    }
    iType = paramDateTimeFieldType;
  }
  
  public long add(long paramLong, int paramInt)
  {
    return getDurationField().add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return getDurationField().add(paramLong1, paramLong2);
  }
  
  public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 == 0) {
      return paramArrayOfInt;
    }
    Object localObject1 = null;
    Object localObject2 = localObject1;
    int[] arrayOfInt = paramArrayOfInt;
    int i = paramInt2;
    long l;
    if (paramInt2 > 0)
    {
      i = getMaximumValue(paramReadablePartial, paramArrayOfInt);
      l = paramArrayOfInt[paramInt1] + paramInt2;
      if (l > i) {
        break label122;
      }
      paramArrayOfInt[paramInt1] = ((int)l);
      i = paramInt2;
      arrayOfInt = paramArrayOfInt;
    }
    for (localObject2 = localObject1;; localObject2 = paramArrayOfInt)
    {
      if (i < 0)
      {
        paramInt2 = getMinimumValue(paramReadablePartial, arrayOfInt);
        l = arrayOfInt[paramInt1] + i;
        if (l >= paramInt2) {
          arrayOfInt[paramInt1] = ((int)l);
        }
      }
      else
      {
        return set(paramReadablePartial, paramInt1, arrayOfInt, arrayOfInt[paramInt1]);
        label122:
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          if (paramInt1 == 0) {
            throw new IllegalArgumentException("Maximum value exceeded for add");
          }
          localObject1 = paramReadablePartial.getField(paramInt1 - 1);
          localObject2 = localObject1;
          if (getRangeDurationField().getType() != ((DateTimeField)localObject1).getDurationField().getType()) {
            throw new IllegalArgumentException("Fields invalid for add");
          }
        }
        paramInt2 -= i + 1 - paramArrayOfInt[paramInt1];
        paramArrayOfInt = ((DateTimeField)localObject2).add(paramReadablePartial, paramInt1 - 1, paramArrayOfInt, 1);
        paramArrayOfInt[paramInt1] = getMinimumValue(paramReadablePartial, paramArrayOfInt);
        localObject1 = localObject2;
        break;
      }
      paramArrayOfInt = (int[])localObject2;
      if (localObject2 == null)
      {
        if (paramInt1 == 0) {
          throw new IllegalArgumentException("Maximum value exceeded for add");
        }
        localObject1 = paramReadablePartial.getField(paramInt1 - 1);
        paramArrayOfInt = (int[])localObject1;
        if (getRangeDurationField().getType() != ((DateTimeField)localObject1).getDurationField().getType()) {
          throw new IllegalArgumentException("Fields invalid for add");
        }
      }
      i -= paramInt2 - 1 - arrayOfInt[paramInt1];
      arrayOfInt = paramArrayOfInt.add(paramReadablePartial, paramInt1 - 1, arrayOfInt, -1);
      arrayOfInt[paramInt1] = getMaximumValue(paramReadablePartial, arrayOfInt);
    }
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    return set(paramLong, FieldUtils.getWrappedValue(get(paramLong), paramInt, getMinimumValue(paramLong), getMaximumValue(paramLong)));
  }
  
  public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    return set(paramReadablePartial, paramInt1, paramArrayOfInt, FieldUtils.getWrappedValue(paramArrayOfInt[paramInt1], paramInt2, getMinimumValue(paramReadablePartial), getMaximumValue(paramReadablePartial)));
  }
  
  public int[] addWrapPartial(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 == 0) {
      return paramArrayOfInt;
    }
    Object localObject1 = null;
    Object localObject2 = localObject1;
    int[] arrayOfInt = paramArrayOfInt;
    int i = paramInt2;
    long l;
    if (paramInt2 > 0)
    {
      i = getMaximumValue(paramReadablePartial, paramArrayOfInt);
      l = paramArrayOfInt[paramInt1] + paramInt2;
      if (l > i) {
        break label122;
      }
      paramArrayOfInt[paramInt1] = ((int)l);
      i = paramInt2;
      arrayOfInt = paramArrayOfInt;
      localObject2 = localObject1;
    }
    for (;;)
    {
      if (i < 0)
      {
        paramInt2 = getMinimumValue(paramReadablePartial, arrayOfInt);
        l = arrayOfInt[paramInt1] + i;
        if (l >= paramInt2) {
          arrayOfInt[paramInt1] = ((int)l);
        }
      }
      else
      {
        return set(paramReadablePartial, paramInt1, arrayOfInt, arrayOfInt[paramInt1]);
        label122:
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          if (paramInt1 == 0)
          {
            paramInt2 -= i + 1 - paramArrayOfInt[paramInt1];
            paramArrayOfInt[paramInt1] = getMinimumValue(paramReadablePartial, paramArrayOfInt);
            break;
          }
          localObject1 = paramReadablePartial.getField(paramInt1 - 1);
          localObject2 = localObject1;
          if (getRangeDurationField().getType() != ((DateTimeField)localObject1).getDurationField().getType()) {
            throw new IllegalArgumentException("Fields invalid for add");
          }
        }
        paramInt2 -= i + 1 - paramArrayOfInt[paramInt1];
        paramArrayOfInt = ((DateTimeField)localObject2).addWrapPartial(paramReadablePartial, paramInt1 - 1, paramArrayOfInt, 1);
        paramArrayOfInt[paramInt1] = getMinimumValue(paramReadablePartial, paramArrayOfInt);
        localObject1 = localObject2;
        break;
      }
      paramArrayOfInt = (int[])localObject2;
      if (localObject2 == null)
      {
        if (paramInt1 == 0)
        {
          i -= paramInt2 - 1 - arrayOfInt[paramInt1];
          arrayOfInt[paramInt1] = getMaximumValue(paramReadablePartial, arrayOfInt);
          continue;
        }
        localObject1 = paramReadablePartial.getField(paramInt1 - 1);
        paramArrayOfInt = (int[])localObject1;
        if (getRangeDurationField().getType() != ((DateTimeField)localObject1).getDurationField().getType()) {
          throw new IllegalArgumentException("Fields invalid for add");
        }
      }
      i -= paramInt2 - 1 - arrayOfInt[paramInt1];
      arrayOfInt = paramArrayOfInt.addWrapPartial(paramReadablePartial, paramInt1 - 1, arrayOfInt, -1);
      arrayOfInt[paramInt1] = getMaximumValue(paramReadablePartial, arrayOfInt);
      localObject2 = paramArrayOfInt;
    }
  }
  
  protected int convertText(String paramString, Locale paramLocale)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramLocale)
    {
      throw new IllegalFieldValueException(getType(), paramString);
    }
  }
  
  public abstract int get(long paramLong);
  
  public String getAsShortText(int paramInt, Locale paramLocale)
  {
    return getAsText(paramInt, paramLocale);
  }
  
  public final String getAsShortText(long paramLong)
  {
    return getAsShortText(paramLong, null);
  }
  
  public String getAsShortText(long paramLong, Locale paramLocale)
  {
    return getAsShortText(get(paramLong), paramLocale);
  }
  
  public String getAsShortText(ReadablePartial paramReadablePartial, int paramInt, Locale paramLocale)
  {
    return getAsShortText(paramInt, paramLocale);
  }
  
  public final String getAsShortText(ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    return getAsShortText(paramReadablePartial, paramReadablePartial.get(getType()), paramLocale);
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return Integer.toString(paramInt);
  }
  
  public final String getAsText(long paramLong)
  {
    return getAsText(paramLong, null);
  }
  
  public String getAsText(long paramLong, Locale paramLocale)
  {
    return getAsText(get(paramLong), paramLocale);
  }
  
  public String getAsText(ReadablePartial paramReadablePartial, int paramInt, Locale paramLocale)
  {
    return getAsText(paramInt, paramLocale);
  }
  
  public final String getAsText(ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    return getAsText(paramReadablePartial, paramReadablePartial.get(getType()), paramLocale);
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return getDurationField().getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return getDurationField().getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public abstract DurationField getDurationField();
  
  public int getLeapAmount(long paramLong)
  {
    return 0;
  }
  
  public DurationField getLeapDurationField()
  {
    return null;
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    return getMaximumTextLength(paramLocale);
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    int i = getMaximumValue();
    if (i >= 0)
    {
      if (i < 10) {
        return 1;
      }
      if (i < 100) {
        return 2;
      }
      if (i < 1000) {
        return 3;
      }
    }
    return Integer.toString(i).length();
  }
  
  public abstract int getMaximumValue();
  
  public int getMaximumValue(long paramLong)
  {
    return getMaximumValue();
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    return getMaximumValue();
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    return getMaximumValue(paramReadablePartial);
  }
  
  public abstract int getMinimumValue();
  
  public int getMinimumValue(long paramLong)
  {
    return getMinimumValue();
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial)
  {
    return getMinimumValue();
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    return getMinimumValue(paramReadablePartial);
  }
  
  public final String getName()
  {
    return iType.getName();
  }
  
  public abstract DurationField getRangeDurationField();
  
  public final DateTimeFieldType getType()
  {
    return iType;
  }
  
  public boolean isLeap(long paramLong)
  {
    return false;
  }
  
  public final boolean isSupported()
  {
    return true;
  }
  
  public long remainder(long paramLong)
  {
    return paramLong - roundFloor(paramLong);
  }
  
  public long roundCeiling(long paramLong)
  {
    long l2 = roundFloor(paramLong);
    long l1 = paramLong;
    if (l2 != paramLong) {
      l1 = add(l2, 1);
    }
    return l1;
  }
  
  public abstract long roundFloor(long paramLong);
  
  public long roundHalfCeiling(long paramLong)
  {
    long l1 = roundFloor(paramLong);
    long l2 = roundCeiling(paramLong);
    if (l2 - paramLong <= paramLong - l1) {
      return l2;
    }
    return l1;
  }
  
  public long roundHalfEven(long paramLong)
  {
    long l1 = roundFloor(paramLong);
    long l2 = roundCeiling(paramLong);
    long l3 = paramLong - l1;
    paramLong = l2 - paramLong;
    if (l3 < paramLong) {}
    do
    {
      return l1;
      if (paramLong < l3) {
        return l2;
      }
    } while ((get(l2) & 0x1) != 0);
    return l2;
  }
  
  public long roundHalfFloor(long paramLong)
  {
    long l1 = roundFloor(paramLong);
    long l2 = roundCeiling(paramLong);
    if (paramLong - l1 <= l2 - paramLong) {
      return l1;
    }
    return l2;
  }
  
  public abstract long set(long paramLong, int paramInt);
  
  public final long set(long paramLong, String paramString)
  {
    return set(paramLong, paramString, null);
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    return set(paramLong, convertText(paramString, paramLocale));
  }
  
  public int[] set(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    FieldUtils.verifyValueBounds(this, paramInt2, getMinimumValue(paramReadablePartial, paramArrayOfInt), getMaximumValue(paramReadablePartial, paramArrayOfInt));
    paramArrayOfInt[paramInt1] = paramInt2;
    paramInt1 += 1;
    while (paramInt1 < paramReadablePartial.size())
    {
      DateTimeField localDateTimeField = paramReadablePartial.getField(paramInt1);
      if (paramArrayOfInt[paramInt1] > localDateTimeField.getMaximumValue(paramReadablePartial, paramArrayOfInt)) {
        paramArrayOfInt[paramInt1] = localDateTimeField.getMaximumValue(paramReadablePartial, paramArrayOfInt);
      }
      if (paramArrayOfInt[paramInt1] < localDateTimeField.getMinimumValue(paramReadablePartial, paramArrayOfInt)) {
        paramArrayOfInt[paramInt1] = localDateTimeField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
      }
      paramInt1 += 1;
    }
    return paramArrayOfInt;
  }
  
  public int[] set(ReadablePartial paramReadablePartial, int paramInt, int[] paramArrayOfInt, String paramString, Locale paramLocale)
  {
    return set(paramReadablePartial, paramInt, paramArrayOfInt, convertText(paramString, paramLocale));
  }
  
  public String toString()
  {
    return "DateTimeField[" + getName() + ']';
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.BaseDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */