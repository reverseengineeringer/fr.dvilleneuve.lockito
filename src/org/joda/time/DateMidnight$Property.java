package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class DateMidnight$Property
  extends AbstractReadableInstantFieldProperty
{
  private static final long serialVersionUID = 257629620L;
  private DateTimeField iField;
  private DateMidnight iInstant;
  
  DateMidnight$Property(DateMidnight paramDateMidnight, DateTimeField paramDateTimeField)
  {
    iInstant = paramDateMidnight;
    iField = paramDateTimeField;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iInstant = ((DateMidnight)paramObjectInputStream.readObject());
    iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iInstant);
    paramObjectOutputStream.writeObject(iField.getType());
  }
  
  public DateMidnight addToCopy(int paramInt)
  {
    return iInstant.withMillis(iField.add(iInstant.getMillis(), paramInt));
  }
  
  public DateMidnight addToCopy(long paramLong)
  {
    return iInstant.withMillis(iField.add(iInstant.getMillis(), paramLong));
  }
  
  public DateMidnight addWrapFieldToCopy(int paramInt)
  {
    return iInstant.withMillis(iField.addWrapField(iInstant.getMillis(), paramInt));
  }
  
  protected Chronology getChronology()
  {
    return iInstant.getChronology();
  }
  
  public DateMidnight getDateMidnight()
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
  
  public DateMidnight roundCeilingCopy()
  {
    return iInstant.withMillis(iField.roundCeiling(iInstant.getMillis()));
  }
  
  public DateMidnight roundFloorCopy()
  {
    return iInstant.withMillis(iField.roundFloor(iInstant.getMillis()));
  }
  
  public DateMidnight roundHalfCeilingCopy()
  {
    return iInstant.withMillis(iField.roundHalfCeiling(iInstant.getMillis()));
  }
  
  public DateMidnight roundHalfEvenCopy()
  {
    return iInstant.withMillis(iField.roundHalfEven(iInstant.getMillis()));
  }
  
  public DateMidnight roundHalfFloorCopy()
  {
    return iInstant.withMillis(iField.roundHalfFloor(iInstant.getMillis()));
  }
  
  public DateMidnight setCopy(int paramInt)
  {
    return iInstant.withMillis(iField.set(iInstant.getMillis(), paramInt));
  }
  
  public DateMidnight setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public DateMidnight setCopy(String paramString, Locale paramLocale)
  {
    return iInstant.withMillis(iField.set(iInstant.getMillis(), paramString, paramLocale));
  }
  
  public DateMidnight withMaximumValue()
  {
    return setCopy(getMaximumValue());
  }
  
  public DateMidnight withMinimumValue()
  {
    return setCopy(getMinimumValue());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateMidnight.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */