package org.joda.time.chrono;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.RemainderDateTimeField;

public final class ISOChronology
  extends AssembledChronology
{
  private static final int FAST_CACHE_SIZE = 64;
  private static final ISOChronology INSTANCE_UTC;
  private static final Map<DateTimeZone, ISOChronology> cCache = new HashMap();
  private static final ISOChronology[] cFastCache = new ISOChronology[64];
  private static final long serialVersionUID = -6212696554273812441L;
  
  static
  {
    INSTANCE_UTC = new ISOChronology(GregorianChronology.getInstanceUTC());
    cCache.put(DateTimeZone.UTC, INSTANCE_UTC);
  }
  
  private ISOChronology(Chronology paramChronology)
  {
    super(paramChronology, null);
  }
  
  public static ISOChronology getInstance()
  {
    return getInstance(DateTimeZone.getDefault());
  }
  
  public static ISOChronology getInstance(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    int i = System.identityHashCode(localDateTimeZone) & 0x3F;
    paramDateTimeZone = cFastCache[i];
    if ((paramDateTimeZone != null) && (paramDateTimeZone.getZone() == localDateTimeZone)) {
      return paramDateTimeZone;
    }
    synchronized (cCache)
    {
      ISOChronology localISOChronology = (ISOChronology)cCache.get(localDateTimeZone);
      paramDateTimeZone = localISOChronology;
      if (localISOChronology == null) {
        paramDateTimeZone = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, localDateTimeZone));
      }
    }
    throw paramDateTimeZone;
  }
  
  public static ISOChronology getInstanceUTC()
  {
    return INSTANCE_UTC;
  }
  
  private Object writeReplace()
  {
    return new Stub(getZone());
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    if (getBase().getZone() == DateTimeZone.UTC)
    {
      centuryOfEra = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
      centuries = centuryOfEra.getDurationField();
      yearOfCentury = new RemainderDateTimeField((DividedDateTimeField)centuryOfEra, DateTimeFieldType.yearOfCentury());
      weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField)centuryOfEra, weekyears, DateTimeFieldType.weekyearOfCentury());
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof ISOChronology))
    {
      paramObject = (ISOChronology)paramObject;
      return getZone().equals(((ISOChronology)paramObject).getZone());
    }
    return false;
  }
  
  public int hashCode()
  {
    return "ISO".hashCode() * 11 + getZone().hashCode();
  }
  
  public String toString()
  {
    String str = "ISOChronology";
    DateTimeZone localDateTimeZone = getZone();
    if (localDateTimeZone != null) {
      str = "ISOChronology" + '[' + localDateTimeZone.getID() + ']';
    }
    return str;
  }
  
  public Chronology withUTC()
  {
    return INSTANCE_UTC;
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    if (localDateTimeZone == getZone()) {
      return this;
    }
    return getInstance(localDateTimeZone);
  }
  
  private static final class Stub
    implements Serializable
  {
    private static final long serialVersionUID = -6212696554273812441L;
    private transient DateTimeZone iZone;
    
    Stub(DateTimeZone paramDateTimeZone)
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
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.ISOChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */