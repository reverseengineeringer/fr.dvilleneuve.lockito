package org.joda.time.format;

import java.util.Arrays;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

public class DateTimeParserBucket
{
  private final Chronology iChrono;
  private int iDefaultYear;
  private Locale iLocale;
  private final long iMillis;
  private Integer iOffset;
  private Integer iPivotYear;
  private SavedField[] iSavedFields = new SavedField[8];
  private int iSavedFieldsCount;
  private boolean iSavedFieldsShared;
  private Object iSavedState;
  private DateTimeZone iZone;
  
  @Deprecated
  public DateTimeParserBucket(long paramLong, Chronology paramChronology, Locale paramLocale)
  {
    this(paramLong, paramChronology, paramLocale, null, 2000);
  }
  
  @Deprecated
  public DateTimeParserBucket(long paramLong, Chronology paramChronology, Locale paramLocale, Integer paramInteger)
  {
    this(paramLong, paramChronology, paramLocale, paramInteger, 2000);
  }
  
  public DateTimeParserBucket(long paramLong, Chronology paramChronology, Locale paramLocale, Integer paramInteger, int paramInt)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    iMillis = paramLong;
    iZone = paramChronology.getZone();
    iChrono = paramChronology.withUTC();
    paramChronology = paramLocale;
    if (paramLocale == null) {
      paramChronology = Locale.getDefault();
    }
    iLocale = paramChronology;
    iPivotYear = paramInteger;
    iDefaultYear = paramInt;
  }
  
  static int compareReverse(DurationField paramDurationField1, DurationField paramDurationField2)
  {
    if ((paramDurationField1 == null) || (!paramDurationField1.isSupported()))
    {
      if ((paramDurationField2 == null) || (!paramDurationField2.isSupported())) {
        return 0;
      }
      return -1;
    }
    if ((paramDurationField2 == null) || (!paramDurationField2.isSupported())) {
      return 1;
    }
    return -paramDurationField1.compareTo(paramDurationField2);
  }
  
  private void saveField(SavedField paramSavedField)
  {
    SavedField[] arrayOfSavedField1 = iSavedFields;
    int j = iSavedFieldsCount;
    Object localObject;
    if (j != arrayOfSavedField1.length)
    {
      localObject = arrayOfSavedField1;
      if (!iSavedFieldsShared) {}
    }
    else
    {
      if (j != arrayOfSavedField1.length) {
        break label89;
      }
    }
    label89:
    for (int i = j * 2;; i = arrayOfSavedField1.length)
    {
      SavedField[] arrayOfSavedField2 = new SavedField[i];
      System.arraycopy(arrayOfSavedField1, 0, arrayOfSavedField2, 0, j);
      localObject = arrayOfSavedField2;
      iSavedFields = arrayOfSavedField2;
      iSavedFieldsShared = false;
      iSavedState = null;
      localObject[j] = paramSavedField;
      iSavedFieldsCount = (j + 1);
      return;
    }
  }
  
  private static void sort(SavedField[] paramArrayOfSavedField, int paramInt)
  {
    if (paramInt > 10) {
      Arrays.sort(paramArrayOfSavedField, 0, paramInt);
    }
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramInt)
      {
        int j = i;
        while ((j > 0) && (paramArrayOfSavedField[(j - 1)].compareTo(paramArrayOfSavedField[j]) > 0))
        {
          SavedField localSavedField = paramArrayOfSavedField[j];
          paramArrayOfSavedField[j] = paramArrayOfSavedField[(j - 1)];
          paramArrayOfSavedField[(j - 1)] = localSavedField;
          j -= 1;
        }
        i += 1;
      }
    }
  }
  
  public long computeMillis()
  {
    return computeMillis(false, null);
  }
  
  public long computeMillis(boolean paramBoolean)
  {
    return computeMillis(paramBoolean, null);
  }
  
  public long computeMillis(boolean paramBoolean, String paramString)
  {
    SavedField[] arrayOfSavedField = iSavedFields;
    int j = iSavedFieldsCount;
    if (iSavedFieldsShared)
    {
      arrayOfSavedField = (SavedField[])iSavedFields.clone();
      iSavedFields = arrayOfSavedField;
      iSavedFieldsShared = false;
    }
    sort(arrayOfSavedField, j);
    Object localObject3;
    if (j > 0)
    {
      localObject3 = DurationFieldType.months().getField(iChrono);
      DurationField localDurationField1 = DurationFieldType.days().getField(iChrono);
      DurationField localDurationField2 = 0iField.getDurationField();
      if ((compareReverse(localDurationField2, (DurationField)localObject3) >= 0) && (compareReverse(localDurationField2, localDurationField1) <= 0))
      {
        saveField(DateTimeFieldType.year(), iDefaultYear);
        l1 = computeMillis(paramBoolean, paramString);
        return l1;
      }
    }
    long l1 = iMillis;
    int i = 0;
    if (i < j) {}
    for (;;)
    {
      try
      {
        l1 = arrayOfSavedField[i].set(l1, paramBoolean);
        i += 1;
      }
      catch (IllegalFieldValueException localIllegalFieldValueException)
      {
        if (paramString == null) {
          continue;
        }
        localIllegalFieldValueException.prependMessage("Cannot parse \"" + paramString + '"');
        throw localIllegalFieldValueException;
      }
      l1 = ((SavedField)localObject3).set(l1, paramBoolean);
      i += 1;
      break label380;
      paramBoolean = false;
      continue;
      Object localObject2;
      label380:
      do
      {
        do
        {
          Object localObject1;
          if (iOffset != null) {
            return localObject1 - iOffset.intValue();
          }
          l1 = localObject1;
          if (iZone == null) {
            break;
          }
          i = iZone.getOffsetFromLocal(localObject1);
          localObject1 -= i;
          l1 = l2;
          if (i == iZone.getOffset(l2)) {
            break;
          }
          localObject3 = "Illegal instant due to time zone offset transition (" + iZone + ')';
          localObject2 = localObject3;
          if (paramString != null) {
            localObject2 = "Cannot parse \"" + paramString + "\": " + (String)localObject3;
          }
          throw new IllegalInstantException((String)localObject2);
          l2 = l1;
        } while (!paramBoolean);
        i = 0;
        long l2 = l1;
      } while (i >= j);
      localObject3 = localObject2[i];
      if (i == j - 1) {
        paramBoolean = true;
      }
    }
  }
  
  public Chronology getChronology()
  {
    return iChrono;
  }
  
  public Locale getLocale()
  {
    return iLocale;
  }
  
  @Deprecated
  public int getOffset()
  {
    if (iOffset != null) {
      return iOffset.intValue();
    }
    return 0;
  }
  
  public Integer getOffsetInteger()
  {
    return iOffset;
  }
  
  public Integer getPivotYear()
  {
    return iPivotYear;
  }
  
  public DateTimeZone getZone()
  {
    return iZone;
  }
  
  public boolean restoreState(Object paramObject)
  {
    if (((paramObject instanceof SavedState)) && (((SavedState)paramObject).restoreState(this)))
    {
      iSavedState = paramObject;
      return true;
    }
    return false;
  }
  
  public void saveField(DateTimeField paramDateTimeField, int paramInt)
  {
    saveField(new SavedField(paramDateTimeField, paramInt));
  }
  
  public void saveField(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    saveField(new SavedField(paramDateTimeFieldType.getField(iChrono), paramInt));
  }
  
  public void saveField(DateTimeFieldType paramDateTimeFieldType, String paramString, Locale paramLocale)
  {
    saveField(new SavedField(paramDateTimeFieldType.getField(iChrono), paramString, paramLocale));
  }
  
  public Object saveState()
  {
    if (iSavedState == null) {
      iSavedState = new SavedState();
    }
    return iSavedState;
  }
  
  @Deprecated
  public void setOffset(int paramInt)
  {
    iSavedState = null;
    iOffset = Integer.valueOf(paramInt);
  }
  
  public void setOffset(Integer paramInteger)
  {
    iSavedState = null;
    iOffset = paramInteger;
  }
  
  public void setPivotYear(Integer paramInteger)
  {
    iPivotYear = paramInteger;
  }
  
  public void setZone(DateTimeZone paramDateTimeZone)
  {
    iSavedState = null;
    iZone = paramDateTimeZone;
  }
  
  static class SavedField
    implements Comparable<SavedField>
  {
    final DateTimeField iField;
    final Locale iLocale;
    final String iText;
    final int iValue;
    
    SavedField(DateTimeField paramDateTimeField, int paramInt)
    {
      iField = paramDateTimeField;
      iValue = paramInt;
      iText = null;
      iLocale = null;
    }
    
    SavedField(DateTimeField paramDateTimeField, String paramString, Locale paramLocale)
    {
      iField = paramDateTimeField;
      iValue = 0;
      iText = paramString;
      iLocale = paramLocale;
    }
    
    public int compareTo(SavedField paramSavedField)
    {
      paramSavedField = iField;
      int i = DateTimeParserBucket.compareReverse(iField.getRangeDurationField(), paramSavedField.getRangeDurationField());
      if (i != 0) {
        return i;
      }
      return DateTimeParserBucket.compareReverse(iField.getDurationField(), paramSavedField.getDurationField());
    }
    
    long set(long paramLong, boolean paramBoolean)
    {
      if (iText == null) {}
      for (paramLong = iField.set(paramLong, iValue);; paramLong = iField.set(paramLong, iText, iLocale))
      {
        long l = paramLong;
        if (paramBoolean) {
          l = iField.roundFloor(paramLong);
        }
        return l;
      }
    }
  }
  
  class SavedState
  {
    final Integer iOffset = iOffset;
    final DateTimeParserBucket.SavedField[] iSavedFields = iSavedFields;
    final int iSavedFieldsCount = iSavedFieldsCount;
    final DateTimeZone iZone = iZone;
    
    SavedState() {}
    
    boolean restoreState(DateTimeParserBucket paramDateTimeParserBucket)
    {
      if (paramDateTimeParserBucket != DateTimeParserBucket.this) {
        return false;
      }
      DateTimeParserBucket.access$002(paramDateTimeParserBucket, iZone);
      DateTimeParserBucket.access$102(paramDateTimeParserBucket, iOffset);
      DateTimeParserBucket.access$202(paramDateTimeParserBucket, iSavedFields);
      if (iSavedFieldsCount < iSavedFieldsCount) {
        DateTimeParserBucket.access$402(paramDateTimeParserBucket, true);
      }
      DateTimeParserBucket.access$302(paramDateTimeParserBucket, iSavedFieldsCount);
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeParserBucket
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */