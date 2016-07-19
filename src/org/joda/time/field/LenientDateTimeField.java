package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;

public class LenientDateTimeField
  extends DelegatedDateTimeField
{
  private static final long serialVersionUID = 8714085824173290599L;
  private final Chronology iBase;
  
  protected LenientDateTimeField(DateTimeField paramDateTimeField, Chronology paramChronology)
  {
    super(paramDateTimeField);
    iBase = paramChronology;
  }
  
  public static DateTimeField getInstance(DateTimeField paramDateTimeField, Chronology paramChronology)
  {
    if (paramDateTimeField == null) {
      paramDateTimeField = null;
    }
    DateTimeField localDateTimeField;
    do
    {
      return paramDateTimeField;
      localDateTimeField = paramDateTimeField;
      if ((paramDateTimeField instanceof StrictDateTimeField)) {
        localDateTimeField = ((StrictDateTimeField)paramDateTimeField).getWrappedField();
      }
      paramDateTimeField = localDateTimeField;
    } while (localDateTimeField.isLenient());
    return new LenientDateTimeField(localDateTimeField, paramChronology);
  }
  
  public final boolean isLenient()
  {
    return true;
  }
  
  public long set(long paramLong, int paramInt)
  {
    long l1 = iBase.getZone().convertUTCToLocal(paramLong);
    long l2 = FieldUtils.safeSubtract(paramInt, get(paramLong));
    l1 = getType().getField(iBase.withUTC()).add(l1, l2);
    return iBase.getZone().convertLocalToUTC(l1, false, paramLong);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.LenientDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */