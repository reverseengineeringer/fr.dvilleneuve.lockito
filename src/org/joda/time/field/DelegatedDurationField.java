package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class DelegatedDurationField
  extends DurationField
  implements Serializable
{
  private static final long serialVersionUID = -5576443481242007829L;
  private final DurationField iField;
  private final DurationFieldType iType;
  
  protected DelegatedDurationField(DurationField paramDurationField)
  {
    this(paramDurationField, null);
  }
  
  protected DelegatedDurationField(DurationField paramDurationField, DurationFieldType paramDurationFieldType)
  {
    if (paramDurationField == null) {
      throw new IllegalArgumentException("The field must not be null");
    }
    iField = paramDurationField;
    DurationFieldType localDurationFieldType = paramDurationFieldType;
    if (paramDurationFieldType == null) {
      localDurationFieldType = paramDurationField.getType();
    }
    iType = localDurationFieldType;
  }
  
  public long add(long paramLong, int paramInt)
  {
    return iField.add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return iField.add(paramLong1, paramLong2);
  }
  
  public int compareTo(DurationField paramDurationField)
  {
    return iField.compareTo(paramDurationField);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof DelegatedDurationField)) {
      return iField.equals(iField);
    }
    return false;
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return iField.getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return iField.getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public long getMillis(int paramInt)
  {
    return iField.getMillis(paramInt);
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    return iField.getMillis(paramInt, paramLong);
  }
  
  public long getMillis(long paramLong)
  {
    return iField.getMillis(paramLong);
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    return iField.getMillis(paramLong1, paramLong2);
  }
  
  public String getName()
  {
    return iType.getName();
  }
  
  public DurationFieldType getType()
  {
    return iType;
  }
  
  public long getUnitMillis()
  {
    return iField.getUnitMillis();
  }
  
  public int getValue(long paramLong)
  {
    return iField.getValue(paramLong);
  }
  
  public int getValue(long paramLong1, long paramLong2)
  {
    return iField.getValue(paramLong1, paramLong2);
  }
  
  public long getValueAsLong(long paramLong)
  {
    return iField.getValueAsLong(paramLong);
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    return iField.getValueAsLong(paramLong1, paramLong2);
  }
  
  public final DurationField getWrappedField()
  {
    return iField;
  }
  
  public int hashCode()
  {
    return iField.hashCode() ^ iType.hashCode();
  }
  
  public boolean isPrecise()
  {
    return iField.isPrecise();
  }
  
  public boolean isSupported()
  {
    return iField.isSupported();
  }
  
  public String toString()
  {
    if (iType == null) {
      return iField.toString();
    }
    return "DurationField[" + iType + ']';
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.DelegatedDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */