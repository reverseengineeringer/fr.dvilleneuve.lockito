package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DurationField;

final class GJChronology$ImpreciseCutoverField
  extends GJChronology.CutoverField
{
  private static final long serialVersionUID = 3410248757173576441L;
  
  GJChronology$ImpreciseCutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong)
  {
    this(paramGJChronology, paramDateTimeField1, paramDateTimeField2, null, paramLong, false);
  }
  
  GJChronology$ImpreciseCutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong)
  {
    this(paramGJChronology, paramDateTimeField1, paramDateTimeField2, paramDurationField, paramLong, false);
  }
  
  GJChronology$ImpreciseCutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong, boolean paramBoolean)
  {
    super(paramGJChronology, paramDateTimeField1, paramDateTimeField2, paramLong, paramBoolean);
    paramGJChronology = paramDurationField;
    if (paramDurationField == null) {
      paramGJChronology = new GJChronology.LinkedDurationField(iDurationField, this);
    }
    iDurationField = paramGJChronology;
  }
  
  GJChronology$ImpreciseCutoverField(GJChronology paramGJChronology, DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField1, DurationField paramDurationField2, long paramLong)
  {
    this(paramGJChronology, paramDateTimeField1, paramDateTimeField2, paramDurationField1, paramLong, false);
    iRangeDurationField = paramDurationField2;
  }
  
  public long add(long paramLong, int paramInt)
  {
    long l;
    if (paramLong >= iCutover)
    {
      l = iGregorianField.add(paramLong, paramInt);
      paramLong = l;
      if (l < iCutover)
      {
        paramLong = l;
        if (GJChronology.access$000(this$0) + l < iCutover)
        {
          if (!iConvertByWeekyear) {
            break label107;
          }
          paramLong = l;
          if (GJChronology.access$100(this$0).weekyear().get(l) <= 0) {
            paramLong = GJChronology.access$100(this$0).weekyear().add(l, -1);
          }
          paramLong = gregorianToJulian(paramLong);
        }
      }
    }
    label107:
    do
    {
      do
      {
        return paramLong;
        paramLong = l;
        if (GJChronology.access$100(this$0).year().get(l) > 0) {
          break;
        }
        paramLong = GJChronology.access$100(this$0).year().add(l, -1);
        break;
        l = iJulianField.add(paramLong, paramInt);
        paramLong = l;
      } while (l < iCutover);
      paramLong = l;
    } while (l - GJChronology.access$000(this$0) < iCutover);
    return julianToGregorian(l);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    if (paramLong1 >= iCutover)
    {
      paramLong2 = iGregorianField.add(paramLong1, paramLong2);
      paramLong1 = paramLong2;
      if (paramLong2 < iCutover)
      {
        paramLong1 = paramLong2;
        if (GJChronology.access$000(this$0) + paramLong2 < iCutover)
        {
          if (!iConvertByWeekyear) {
            break label99;
          }
          paramLong1 = paramLong2;
          if (GJChronology.access$100(this$0).weekyear().get(paramLong2) <= 0) {
            paramLong1 = GJChronology.access$100(this$0).weekyear().add(paramLong2, -1);
          }
          paramLong1 = gregorianToJulian(paramLong1);
        }
      }
    }
    label99:
    do
    {
      do
      {
        return paramLong1;
        paramLong1 = paramLong2;
        if (GJChronology.access$100(this$0).year().get(paramLong2) > 0) {
          break;
        }
        paramLong1 = GJChronology.access$100(this$0).year().add(paramLong2, -1);
        break;
        paramLong2 = iJulianField.add(paramLong1, paramLong2);
        paramLong1 = paramLong2;
      } while (paramLong2 < iCutover);
      paramLong1 = paramLong2;
    } while (paramLong2 - GJChronology.access$000(this$0) < iCutover);
    return julianToGregorian(paramLong2);
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    if (paramLong1 >= iCutover)
    {
      if (paramLong2 >= iCutover) {
        return iGregorianField.getDifference(paramLong1, paramLong2);
      }
      paramLong1 = gregorianToJulian(paramLong1);
      return iJulianField.getDifference(paramLong1, paramLong2);
    }
    if (paramLong2 < iCutover) {
      return iJulianField.getDifference(paramLong1, paramLong2);
    }
    paramLong1 = julianToGregorian(paramLong1);
    return iGregorianField.getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    if (paramLong1 >= iCutover)
    {
      if (paramLong2 >= iCutover) {
        return iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
      }
      paramLong1 = gregorianToJulian(paramLong1);
      return iJulianField.getDifferenceAsLong(paramLong1, paramLong2);
    }
    if (paramLong2 < iCutover) {
      return iJulianField.getDifferenceAsLong(paramLong1, paramLong2);
    }
    paramLong1 = julianToGregorian(paramLong1);
    return iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public int getMaximumValue(long paramLong)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.getMaximumValue(paramLong);
    }
    return iJulianField.getMaximumValue(paramLong);
  }
  
  public int getMinimumValue(long paramLong)
  {
    if (paramLong >= iCutover) {
      return iGregorianField.getMinimumValue(paramLong);
    }
    return iJulianField.getMinimumValue(paramLong);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJChronology.ImpreciseCutoverField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */