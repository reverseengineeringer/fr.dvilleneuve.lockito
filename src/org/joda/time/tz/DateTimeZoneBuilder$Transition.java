package org.joda.time.tz;

final class DateTimeZoneBuilder$Transition
{
  private final long iMillis;
  private final String iNameKey;
  private final int iStandardOffset;
  private final int iWallOffset;
  
  DateTimeZoneBuilder$Transition(long paramLong, String paramString, int paramInt1, int paramInt2)
  {
    iMillis = paramLong;
    iNameKey = paramString;
    iWallOffset = paramInt1;
    iStandardOffset = paramInt2;
  }
  
  DateTimeZoneBuilder$Transition(long paramLong, DateTimeZoneBuilder.Rule paramRule, int paramInt)
  {
    iMillis = paramLong;
    iNameKey = paramRule.getNameKey();
    iWallOffset = (paramRule.getSaveMillis() + paramInt);
    iStandardOffset = paramInt;
  }
  
  DateTimeZoneBuilder$Transition(long paramLong, Transition paramTransition)
  {
    iMillis = paramLong;
    iNameKey = iNameKey;
    iWallOffset = iWallOffset;
    iStandardOffset = iStandardOffset;
  }
  
  public long getMillis()
  {
    return iMillis;
  }
  
  public String getNameKey()
  {
    return iNameKey;
  }
  
  public int getSaveMillis()
  {
    return iWallOffset - iStandardOffset;
  }
  
  public int getStandardOffset()
  {
    return iStandardOffset;
  }
  
  public int getWallOffset()
  {
    return iWallOffset;
  }
  
  public boolean isTransitionFrom(Transition paramTransition)
  {
    if (paramTransition == null) {}
    while ((iMillis > iMillis) && ((iWallOffset != iWallOffset) || (!iNameKey.equals(iNameKey)))) {
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.Transition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */