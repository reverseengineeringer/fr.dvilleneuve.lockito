package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class DateTime$Property
  extends AbstractReadableInstantFieldProperty
{
  private static final long serialVersionUID = -6983323811635733510L;
  private DateTimeField iField;
  private DateTime iInstant;
  
  DateTime$Property(DateTime paramDateTime, DateTimeField paramDateTimeField)
  {
    iInstant = paramDateTime;
    iField = paramDateTimeField;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iInstant = ((DateTime)paramObjectInputStream.readObject());
    iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iInstant);
    paramObjectOutputStream.writeObject(iField.getType());
  }
  
  public DateTime addToCopy(int paramInt)
  {
    return iInstant.withMillis(iField.add(iInstant.getMillis(), paramInt));
  }
  
  public DateTime addToCopy(long paramLong)
  {
    return iInstant.withMillis(iField.add(iInstant.getMillis(), paramLong));
  }
  
  public DateTime addWrapFieldToCopy(int paramInt)
  {
    return iInstant.withMillis(iField.addWrapField(iInstant.getMillis(), paramInt));
  }
  
  protected Chronology getChronology()
  {
    return iInstant.getChronology();
  }
  
  public DateTime getDateTime()
  {
    return iInstant;
  }
  
  public DateTimeField getField()
  {
    return iField;
  }
  
  protected long getMillis()
  {
    return iInstant.getMillis();
  }
  
  public DateTime roundCeilingCopy()
  {
    return iInstant.withMillis(iField.roundCeiling(iInstant.getMillis()));
  }
  
  public DateTime roundFloorCopy()
  {
    return iInstant.withMillis(iField.roundFloor(iInstant.getMillis()));
  }
  
  public DateTime roundHalfCeilingCopy()
  {
    return iInstant.withMillis(iField.roundHalfCeiling(iInstant.getMillis()));
  }
  
  public DateTime roundHalfEvenCopy()
  {
    return iInstant.withMillis(iField.roundHalfEven(iInstant.getMillis()));
  }
  
  public DateTime roundHalfFloorCopy()
  {
    return iInstant.withMillis(iField.roundHalfFloor(iInstant.getMillis()));
  }
  
  public DateTime setCopy(int paramInt)
  {
    return iInstant.withMillis(iField.set(iInstant.getMillis(), paramInt));
  }
  
  public DateTime setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public DateTime setCopy(String paramString, Locale paramLocale)
  {
    return iInstant.withMillis(iField.set(iInstant.getMillis(), paramString, paramLocale));
  }
  
  public DateTime withMaximumValue()
  {
    try
    {
      DateTime localDateTime = setCopy(getMaximumValue());
      return localDateTime;
    }
    catch (RuntimeException localRuntimeException)
    {
      if (IllegalInstantException.isIllegalInstant(localRuntimeException)) {
        return new DateTime(getChronology().getZone().previousTransition(getMillis() + 86400000L), getChronology());
      }
      throw localRuntimeException;
    }
  }
  
  public DateTime withMinimumValue()
  {
    try
    {
      DateTime localDateTime = setCopy(getMinimumValue());
      return localDateTime;
    }
    catch (RuntimeException localRuntimeException)
    {
      if (IllegalInstantException.isIllegalInstant(localRuntimeException)) {
        return new DateTime(getChronology().getZone().nextTransition(getMillis() - 86400000L), getChronology());
      }
      throw localRuntimeException;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTime.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */