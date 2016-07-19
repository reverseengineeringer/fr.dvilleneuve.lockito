package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalDate$Property
  extends AbstractReadableInstantFieldProperty
{
  private static final long serialVersionUID = -3193829732634L;
  private transient DateTimeField iField;
  private transient LocalDate iInstant;
  
  LocalDate$Property(LocalDate paramLocalDate, DateTimeField paramDateTimeField)
  {
    iInstant = paramLocalDate;
    iField = paramDateTimeField;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iInstant = ((LocalDate)paramObjectInputStream.readObject());
    iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iInstant);
    paramObjectOutputStream.writeObject(iField.getType());
  }
  
  public LocalDate addToCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.add(iInstant.getLocalMillis(), paramInt));
  }
  
  public LocalDate addWrapFieldToCopy(int paramInt)
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
  
  public LocalDate getLocalDate()
  {
    return iInstant;
  }
  
  protected long getMillis()
  {
    return iInstant.getLocalMillis();
  }
  
  public LocalDate roundCeilingCopy()
  {
    return iInstant.withLocalMillis(iField.roundCeiling(iInstant.getLocalMillis()));
  }
  
  public LocalDate roundFloorCopy()
  {
    return iInstant.withLocalMillis(iField.roundFloor(iInstant.getLocalMillis()));
  }
  
  public LocalDate roundHalfCeilingCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfCeiling(iInstant.getLocalMillis()));
  }
  
  public LocalDate roundHalfEvenCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfEven(iInstant.getLocalMillis()));
  }
  
  public LocalDate roundHalfFloorCopy()
  {
    return iInstant.withLocalMillis(iField.roundHalfFloor(iInstant.getLocalMillis()));
  }
  
  public LocalDate setCopy(int paramInt)
  {
    return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramInt));
  }
  
  public LocalDate setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public LocalDate setCopy(String paramString, Locale paramLocale)
  {
    return iInstant.withLocalMillis(iField.set(iInstant.getLocalMillis(), paramString, paramLocale));
  }
  
  public LocalDate withMaximumValue()
  {
    return setCopy(getMaximumValue());
  }
  
  public LocalDate withMinimumValue()
  {
    return setCopy(getMinimumValue());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.LocalDate.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */