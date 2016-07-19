package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

final class DateTimeZone$Stub
  implements Serializable
{
  private static final long serialVersionUID = -6471952376487863581L;
  private transient String iID;
  
  DateTimeZone$Stub(String paramString)
  {
    iID = paramString;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException
  {
    iID = paramObjectInputStream.readUTF();
  }
  
  private Object readResolve()
    throws ObjectStreamException
  {
    return DateTimeZone.forID(iID);
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeUTF(iID);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeZone.Stub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */