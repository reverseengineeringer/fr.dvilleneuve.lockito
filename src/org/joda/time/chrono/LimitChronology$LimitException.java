package org.joda.time.chrono;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

class LimitChronology$LimitException
  extends IllegalArgumentException
{
  private static final long serialVersionUID = -5924689995607498581L;
  private final boolean iIsLow;
  
  LimitChronology$LimitException(LimitChronology paramLimitChronology, String paramString, boolean paramBoolean)
  {
    super(paramString);
    iIsLow = paramBoolean;
  }
  
  public String getMessage()
  {
    StringBuffer localStringBuffer = new StringBuffer(85);
    localStringBuffer.append("The");
    Object localObject = super.getMessage();
    if (localObject != null)
    {
      localStringBuffer.append(' ');
      localStringBuffer.append((String)localObject);
    }
    localStringBuffer.append(" instant is ");
    localObject = ISODateTimeFormat.dateTime().withChronology(this$0.getBase());
    if (iIsLow)
    {
      localStringBuffer.append("below the supported minimum of ");
      ((DateTimeFormatter)localObject).printTo(localStringBuffer, this$0.getLowerLimit().getMillis());
    }
    for (;;)
    {
      localStringBuffer.append(" (");
      localStringBuffer.append(this$0.getBase());
      localStringBuffer.append(')');
      return localStringBuffer.toString();
      localStringBuffer.append("above the supported maximum of ");
      ((DateTimeFormatter)localObject).printTo(localStringBuffer, this$0.getUpperLimit().getMillis());
    }
  }
  
  public String toString()
  {
    return "IllegalArgumentException: " + getMessage();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.LimitChronology.LimitException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */