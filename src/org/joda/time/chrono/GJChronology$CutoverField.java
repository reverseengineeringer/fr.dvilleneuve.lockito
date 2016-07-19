package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;

class GJChronology$CutoverField
  extends BaseDateTimeField
{
  private static final long serialVersionUID = 3528501219481026402L;
  final boolean iConvertByWeekyear;
  final long iCutover;
  protected DurationField iDurationField;
  final DateTimeField iGregorianField;
  final DateTimeField iJulianField;
  protected DurationField iRangeDurationField;
  
  GJChronology$CutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong)
  {
    this(paramGJChronology, paramDateTimeField1, paramDateTimeField2, paramLong, false);
  }
  
  GJChronology$CutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong, boolean paramBoolean)
  {
    this(paramGJChronology, paramDateTimeField1, paramDateTimeField2, null, paramLong, paramBoolean);
  }
  
  GJChronology$CutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong, boolean paramBoolean)
  {
    super(paramDateTimeField2.getType());
    iJulianField = paramDateTimeField1;
    iGregorianField = paramDateTimeField2;
    iCutover = paramLong;
    iConvertByWeekyear = paramBoolean;
    iDurationField = paramDateTimeField2.getDurationField();
    paramGJChronology = paramDurationField;
    if (paramDurationField == null)
    {
      paramDateTimeField2 = paramDateTimeField2.getRangeDurationField();
      paramGJChronology = paramDateTimeField2;
      if (paramDateTimeField2 == null) {
        paramGJChronology = paramDateTimeField1.getRangeDurationField();
      }
    }
    iRangeDurationField = paramGJChronology;
  }
  
  public long add(long paramLong, int paramInt)
  {
    return iGregorianField.add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return iGregorianField.add(paramLong1, paramLong2);
  }
  
  public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    if (paramInt2 == 0) {
      return paramArrayOfInt;
    }
    if (DateTimeUtils.isContiguous(paramReadablePartial))
    {
      long l = 0L;
      paramInt1 = 0;
      int i = paramReadablePartial.size();
      while (paramInt1 < i)
      {
        l = paramReadablePartial.getFieldType(paramInt1).getField(this$0).set(l, paramArrayOfInt[paramInt1]);
        paramInt1 += 1;
      }
      l = add(l, paramInt2);
      return this$0.get(paramReadablePartial, l);
    }
    return super.add(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
  }
  
  public int get(long paramLong)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.get(paramLong);
    }
    return iJulianField.get(paramLong);
  }
  
  public String getAsShortText(int paramInt, Locale paramLocale)
  {
    return iGregorianField.getAsShortText(paramInt, paramLocale);
  }
  
  public String getAsShortText(long paramLong, Locale paramLocale)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.getAsShortText(paramLong, paramLocale);
    }
    return iJulianField.getAsShortText(paramLong, paramLocale);
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    return iGregorianField.getAsText(paramInt, paramLocale);
  }
  
  public String getAsText(long paramLong, Locale paramLocale)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.getAsText(paramLong, paramLocale);
    }
    return iJulianField.getAsText(paramLong, paramLocale);
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return iGregorianField.getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public DurationField getDurationField()
  {
    return iDurationField;
  }
  
  public int getLeapAmount(long paramLong)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.getLeapAmount(paramLong);
    }
    return iJulianField.getLeapAmount(paramLong);
  }
  
  public DurationField getLeapDurationField()
  {
    return iGregorianField.getLeapDurationField();
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    return Math.max(iJulianField.getMaximumShortTextLength(paramLocale), iGregorianField.getMaximumShortTextLength(paramLocale));
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    return Math.max(iJulianField.getMaximumTextLength(paramLocale), iGregorianField.getMaximumTextLength(paramLocale));
  }
  
  public int getMaximumValue()
  {
    return iGregorianField.getMaximumValue();
  }
  
  public int getMaximumValue(long paramLong)
  {
    int i;
    if (paramLong >= iCutover) {
      i = iGregorianField.getMaximumValue(paramLong);
    }
    int j;
    do
    {
      return i;
      j = iJulianField.getMaximumValue(paramLong);
      i = j;
    } while (iJulianField.set(paramLong, j) < iCutover);
    return iJulianField.get(iJulianField.add(iCutover, -1));
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    return getMaximumValue(GJChronology.getInstanceUTC().set(paramReadablePartial, 0L));
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    GJChronology localGJChronology = GJChronology.getInstanceUTC();
    long l1 = 0L;
    int i = 0;
    int j = paramReadablePartial.size();
    while (i < j)
    {
      DateTimeField localDateTimeField = paramReadablePartial.getFieldType(i).getField(localGJChronology);
      long l2 = l1;
      if (paramArrayOfInt[i] <= localDateTimeField.getMaximumValue(l1)) {
        l2 = localDateTimeField.set(l1, paramArrayOfInt[i]);
      }
      i += 1;
      l1 = l2;
    }
    return getMaximumValue(l1);
  }
  
  public int getMinimumValue()
  {
    return iJulianField.getMinimumValue();
  }
  
  public int getMinimumValue(long paramLong)
  {
    int i;
    if (paramLong < iCutover) {
      i = iJulianField.getMinimumValue(paramLong);
    }
    int j;
    do
    {
      return i;
      j = iGregorianField.getMinimumValue(paramLong);
      i = j;
    } while (iGregorianField.set(paramLong, j) >= iCutover);
    return iGregorianField.get(iCutover);
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial)
  {
    return iJulianField.getMinimumValue(paramReadablePartial);
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    return iJulianField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
  }
  
  public DurationField getRangeDurationField()
  {
    return iRangeDurationField;
  }
  
  protected long gregorianToJulian(long paramLong)
  {
    if (iConvertByWeekyear) {
      return this$0.gregorianToJulianByWeekyear(paramLong);
    }
    return this$0.gregorianToJulianByYear(paramLong);
  }
  
  public boolean isLeap(long paramLong)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.isLeap(paramLong);
    }
    return iJulianField.isLeap(paramLong);
  }
  
  public boolean isLenient()
  {
    return false;
  }
  
  protected long julianToGregorian(long paramLong)
  {
    if (iConvertByWeekyear) {
      return this$0.julianToGregorianByWeekyear(paramLong);
    }
    return this$0.julianToGregorianByYear(paramLong);
  }
  
  public long roundCeiling(long paramLong)
  {
    if (paramLong >= iCutover) {
      paramLong = iGregorianField.roundCeiling(paramLong);
    }
    long l;
    do
    {
      do
      {
        return paramLong;
        l = iJulianField.roundCeiling(paramLong);
        paramLong = l;
      } while (l < iCutover);
      paramLong = l;
    } while (l - GJChronology.access$000(this$0) < iCutover);
    return julianToGregorian(l);
  }
  
  public long roundFloor(long paramLong)
  {
    if (paramLong >= iCutover)
    {
      long l = iGregorianField.roundFloor(paramLong);
      paramLong = l;
      if (l < iCutover)
      {
        paramLong = l;
        if (GJChronology.access$000(this$0) + l < iCutover) {
          paramLong = gregorianToJulian(l);
        }
      }
      return paramLong;
    }
    return iJulianField.roundFloor(paramLong);
  }
  
  public long set(long paramLong, int paramInt)
  {
    long l2;
    long l1;
    if (paramLong >= iCutover)
    {
      l2 = iGregorianField.set(paramLong, paramInt);
      paramLong = l2;
      if (l2 < iCutover)
      {
        l1 = l2;
        if (GJChronology.access$000(this$0) + l2 < iCutover) {
          l1 = gregorianToJulian(l2);
        }
        paramLong = l1;
        if (get(l1) != paramInt) {
          throw new IllegalFieldValueException(iGregorianField.getType(), Integer.valueOf(paramInt), null, null);
        }
      }
    }
    else
    {
      l2 = iJulianField.set(paramLong, paramInt);
      paramLong = l2;
      if (l2 >= iCutover)
      {
        l1 = l2;
        if (l2 - GJChronology.access$000(this$0) >= iCutover) {
          l1 = julianToGregorian(l2);
        }
        paramLong = l1;
        if (get(l1) != paramInt) {
          throw new IllegalFieldValueException(iJulianField.getType(), Integer.valueOf(paramInt), null, null);
        }
      }
    }
    return paramLong;
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    long l;
    if (paramLong >= iCutover)
    {
      l = iGregorianField.set(paramLong, paramString, paramLocale);
      paramLong = l;
      if (l < iCutover)
      {
        paramLong = l;
        if (GJChronology.access$000(this$0) + l < iCutover) {
          paramLong = gregorianToJulian(l);
        }
      }
    }
    do
    {
      do
      {
        return paramLong;
        l = iJulianField.set(paramLong, paramString, paramLocale);
        paramLong = l;
      } while (l < iCutover);
      paramLong = l;
    } while (l - GJChronology.access$000(this$0) < iCutover);
    return julianToGregorian(l);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJChronology.CutoverField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */