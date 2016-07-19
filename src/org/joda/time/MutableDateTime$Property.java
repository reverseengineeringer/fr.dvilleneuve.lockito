package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class MutableDateTime$Property
  extends AbstractReadableInstantFieldProperty
{
  private static final long serialVersionUID = -4481126543819298617L;
  private DateTimeField iField;
  private MutableDateTime iInstant;
  
  MutableDateTime$Property(MutableDateTime paramMutableDateTime, DateTimeField paramDateTimeField)
  {
    iInstant = paramMutableDateTime;
    iField = paramDateTimeField;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iInstant = ((MutableDateTime)paramObjectInputStream.readObject());
    iField = ((DateTimeFieldType)paramObjectInputStream.readObject()).getField(iInstant.getChronology());
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iInstant);
    paramObjectOutputStream.writeObject(iField.getType());
  }
  
  public MutableDateTime add(int paramInt)
  {
    iInstant.setMillis(getField().add(iInstant.getMillis(), paramInt));
    return iInstant;
  }
  
  public MutableDateTime add(long paramLong)
  {
    iInstant.setMillis(getField().add(iInstant.getMillis(), paramLong));
    return iInstant;
  }
  
  public MutableDateTime addWrapField(int paramInt)
  {
    iInstant.setMillis(getField().addWrapField(iInstant.getMillis(), paramInt));
    return iInstant;
  }
  
  protected Chronology getChronology()
  {
    return iInstant.getChronology();
  }
  
  public DateTimeField getField()
  {
    return iField;
  }
  
  protected long getMillis()
  {
    return iInstant.getMillis();
  }
  
  public MutableDateTime getMutableDateTime()
  {
    return iInstant;
  }
  
  public MutableDateTime roundCeiling()
  {
    iInstant.setMillis(getField().roundCeiling(iInstant.getMillis()));
    return iInstant;
  }
  
  public MutableDateTime roundFloor()
  {
    iInstant.setMillis(getField().roundFloor(iInstant.getMillis()));
    return iInstant;
  }
  
  public MutableDateTime roundHalfCeiling()
  {
    iInstant.setMillis(getField().roundHalfCeiling(iInstant.getMillis()));
    return iInstant;
  }
  
  public MutableDateTime roundHalfEven()
  {
    iInstant.setMillis(getField().roundHalfEven(iInstant.getMillis()));
    return iInstant;
  }
  
  public MutableDateTime roundHalfFloor()
  {
    iInstant.setMillis(getField().roundHalfFloor(iInstant.getMillis()));
    return iInstant;
  }
  
  public MutableDateTime set(int paramInt)
  {
    iInstant.setMillis(getField().set(iInstant.getMillis(), paramInt));
    return iInstant;
  }
  
  public MutableDateTime set(String paramString)
  {
    set(paramString, null);
    return iInstant;
  }
  
  public MutableDateTime set(String paramString, Locale paramLocale)
  {
    iInstant.setMillis(getField().set(iInstant.getMillis(), paramString, paramLocale));
    return iInstant;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.MutableDateTime.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */