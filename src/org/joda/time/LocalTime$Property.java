package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalTime$Property
  extends AbstractReadableInstantFieldProperty
{
  private static final long serialVersionUID = -325842547277223L;
  private transient DateTimeField iField;
  private transient LocalTime iInstant;
  
  LocalTime$Property(LocalTime paramLocalTime, DateTimeField paramDateTimeField)
  {
    iInstant = paramLocalTime;
    iField = paramDateTimeField;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iInstant = ((LocalTime)paramObjectInputStream.readObject());
    iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iInstant);
    paramObjectOutputStream.writeObject(iField.getType());
  }
  
  public LocalTime addCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), paramInt));
  }
  
  public LocalTime addCopy(long paramLong)
  {
    return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), paramLong));
  }
  
  public LocalTime addNoWrapToCopy(int paramInt)
  {
    long l = iField.add(iInstant.getLocalMillis(), paramInt);
    if (iInstant.getChronology().millisOfDay().get(l) != l) {
      throw new IllegalArgumentException("The addition exceeded the boundaries of LocalTime");
    }
    return iInstant.withLocalMillis(l);
  }
  
  public LocalTime addWrapFieldToCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.addWrapField(iInstant.getLocalMillis(), paramInt));
  }
  
  protected Chronology getChronology()
  {
    return iInstant.getChronology();
  }
  
  public DateTimeField getField()
  {
    return iField;
  }
  
  public LocalTime getLocalTime()
  {
    return iInstant;
  }
  
  protected long getMillis()
  {
    return iInstant.getLocalMillis();
  }
  
  public LocalTime roundCeilingCopy()
  {
    return iInstant.withLocalMillis(iField.roundCeiling(iInstant.getLocalMillis()));
  }
  
  public LocalTime roundFloorCopy()
  {
    return iInstant.withLocalMillis(iField.roundFloor(iInstant.getLocalMillis()));
  }
  
  public LocalTime roundHalfCeilingCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfCeiling(iInstant.getLocalMillis()));
  }
  
  public LocalTime roundHalfEvenCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfEven(iInstant.getLocalMillis()));
  }
  
  public LocalTime roundHalfFloorCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfFloor(iInstant.getLocalMillis()));
  }
  
  public LocalTime setCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramInt));
  }
  
  public LocalTime setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public LocalTime setCopy(String paramString, Locale paramLocale)
  {
    return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramString, paramLocale));
  }
  
  public LocalTime withMaximumValue()
  {
    return setCopy(getMaximumValue());
  }
  
  public LocalTime withMinimumValue()
  {
    return setCopy(getMinimumValue());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.LocalTime.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */