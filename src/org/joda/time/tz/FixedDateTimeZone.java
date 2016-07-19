package org.joda.time.tz;

import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;

public final class FixedDateTimeZone
  extends DateTimeZone
{
  private static final long serialVersionUID = -3513011772763289092L;
  private final String iNameKey;
  private final int iStandardOffset;
  private final int iWallOffset;
  
  public FixedDateTimeZone(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    super(paramString1);
    iNameKey = paramString2;
    iWallOffset = paramInt1;
    iStandardOffset = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof FixedDateTimeZone)) {
        break;
      }
      paramObject = (FixedDateTimeZone)paramObject;
    } while ((getID().equals(((FixedDateTimeZone)paramObject).getID())) && (iStandardOffset == iStandardOffset) && (iWallOffset == iWallOffset));
    return false;
    return false;
  }
  
  public String getNameKey(long paramLong)
  {
    return iNameKey;
  }
  
  public int getOffset(long paramLong)
  {
    return iWallOffset;
  }
  
  public int getOffsetFromLocal(long paramLong)
  {
    return iWallOffset;
  }
  
  public int getStandardOffset(long paramLong)
  {
    return iStandardOffset;
  }
  
  public int hashCode()
  {
    return getID().hashCode() + iStandardOffset * 37 + iWallOffset * 31;
  }
  
  public boolean isFixed()
  {
    return true;
  }
  
  public long nextTransition(long paramLong)
  {
    return paramLong;
  }
  
  public long previousTransition(long paramLong)
  {
    return paramLong;
  }
  
  public TimeZone toTimeZone()
  {
    String str = getID();
    if ((str.length() == 6) && ((str.startsWith("+")) || (str.startsWith("-")))) {
      return TimeZone.getTimeZone("GMT" + getID());
    }
    return new SimpleTimeZone(iWallOffset, getID());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.FixedDateTimeZone
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */