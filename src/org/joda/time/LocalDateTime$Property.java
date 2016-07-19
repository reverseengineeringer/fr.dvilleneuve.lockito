package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalDateTime$Property
  extends AbstractReadableInstantFieldProperty
{
  private static final long serialVersionUID = -358138762846288L;
  private transient DateTimeField iField;
  private transient LocalDateTime iInstant;
  
  LocalDateTime$Property(LocalDateTime paramLocalDateTime, DateTimeField paramDateTimeField)
  {
    iInstant = paramLocalDateTime;
    iField = paramDateTimeField;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iInstant = ((LocalDateTime)paramObjectInputStream.readObject());
    iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iInstant);
    paramObjectOutputStream.writeObject(iField.getType());
  }
  
  public LocalDateTime addToCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), paramInt));
  }
  
  public LocalDateTime addToCopy(long paramLong)
  {
    return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), paramLong));
  }
  
  public LocalDateTime addWrapFieldToCopy(int paramInt)
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
  
  public LocalDateTime getLocalDateTime()
  {
    return iInstant;
  }
  
  protected long getMillis()
  {
    return iInstant.getLocalMillis();
  }
  
  public LocalDateTime roundCeilingCopy()
  {
    return iInstant.withLocalMillis(iField.roundCeiling(iInstant.getLocalMillis()));
  }
  
  public LocalDateTime roundFloorCopy()
  {
    return iInstant.withLocalMillis(iField.roundFloor(iInstant.getLocalMillis()));
  }
  
  public LocalDateTime roundHalfCeilingCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfCeiling(iInstant.getLocalMillis()));
  }
  
  public LocalDateTime roundHalfEvenCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfEven(iInstant.getLocalMillis()));
  }
  
  public LocalDateTime roundHalfFloorCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfFloor(iInstant.getLocalMillis()));
  }
  
  public LocalDateTime setCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramInt));
  }
  
  public LocalDateTime setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public LocalDateTime setCopy(String paramString, Locale paramLocale)
  {
    return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramString, paramLocale));
  }
  
  public LocalDateTime withMaximumValue()
  {
    return setCopy(getMaximumValue());
  }
  
  public LocalDateTime withMinimumValue()
  {
    return setCopy(getMinimumValue());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.LocalDateTime.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */