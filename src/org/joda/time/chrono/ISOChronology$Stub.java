package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.joda.time.DateTimeZone;

final class ISOChronology$Stub
  implements Serializable
{
  private static final long serialVersionUID = -6212696554273812441L;
  private transient DateTimeZone iZone;
  
  ISOChronology$Stub(DateTimeZone paramDateTimeZone)
  {
    iZone = paramDateTimeZone;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    iZone = ((DateTimeZone)paramObjectInputStream.readObject());
  }
  
  private Object readResolve()
  {
    return ISOChronology.getInstance(iZone);
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(iZone);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.ISOChronology.Stub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */