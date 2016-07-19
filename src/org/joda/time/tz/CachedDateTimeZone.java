package org.joda.time.tz;

import org.joda.time.DateTimeZone;

public class CachedDateTimeZone
  extends DateTimeZone
{
  private static final int cInfoCacheMask;
  private static final long serialVersionUID = 5472298452022250685L;
  private final transient Info[] iInfoCache = new Info[cInfoCacheMask + 1];
  private final DateTimeZone iZone;
  
  static
  {
    try
    {
      Integer localInteger = Integer.getInteger("org.joda.time.tz.CachedDateTimeZone.size");
      if (localInteger == null)
      {
        i = 512;
        cInfoCacheMask = i - 1;
        return;
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        Object localObject = null;
        continue;
        int i = ((Integer)localObject).intValue() - 1;
        int j = 0;
        while (i > 0)
        {
          j += 1;
          i >>= 1;
        }
        i = 1 << j;
      }
    }
  }
  
  private CachedDateTimeZone(DateTimeZone paramDateTimeZone)
  {
    super(paramDateTimeZone.getID());
    iZone = paramDateTimeZone;
  }
  
  private Info createInfo(long paramLong)
  {
    long l1 = paramLong & 0xFFFFFFFF00000000;
    Info localInfo1 = new Info(iZone, l1);
    Object localObject = localInfo1;
    paramLong = l1;
    for (;;)
    {
      long l2 = iZone.nextTransition(paramLong);
      if ((l2 == paramLong) || (l2 > (l1 | 0xFFFFFFFF))) {
        return localInfo1;
      }
      paramLong = l2;
      Info localInfo2 = new Info(iZone, paramLong);
      iNextInfo = localInfo2;
      localObject = localInfo2;
    }
  }
  
  public static CachedDateTimeZone forZone(DateTimeZone paramDateTimeZone)
  {
    if ((paramDateTimeZone instanceof CachedDateTimeZone)) {
      return (CachedDateTimeZone)paramDateTimeZone;
    }
    return new CachedDateTimeZone(paramDateTimeZone);
  }
  
  private Info getInfo(long paramLong)
  {
    int i = (int)(paramLong >> 32);
    Info[] arrayOfInfo = iInfoCache;
    int j = i & cInfoCacheMask;
    Info localInfo2 = arrayOfInfo[j];
    Info localInfo1;
    if (localInfo2 != null)
    {
      localInfo1 = localInfo2;
      if ((int)(iPeriodStart >> 32) == i) {}
    }
    else
    {
      localInfo1 = createInfo(paramLong);
      arrayOfInfo[j] = localInfo1;
    }
    return localInfo1;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof CachedDateTimeZone)) {
      return iZone.equals(iZone);
    }
    return false;
  }
  
  public String getNameKey(long paramLong)
  {
    return getInfo(paramLong).getNameKey(paramLong);
  }
  
  public int getOffset(long paramLong)
  {
    return getInfo(paramLong).getOffset(paramLong);
  }
  
  public int getStandardOffset(long paramLong)
  {
    return getInfo(paramLong).getStandardOffset(paramLong);
  }
  
  public DateTimeZone getUncachedZone()
  {
    return iZone;
  }
  
  public int hashCode()
  {
    return iZone.hashCode();
  }
  
  public boolean isFixed()
  {
    return iZone.isFixed();
  }
  
  public long nextTransition(long paramLong)
  {
    return iZone.nextTransition(paramLong);
  }
  
  public long previousTransition(long paramLong)
  {
    return iZone.previousTransition(paramLong);
  }
  
  private static final class Info
  {
    private String iNameKey;
    Info iNextInfo;
    private int iOffset = Integer.MIN_VALUE;
    public final long iPeriodStart;
    private int iStandardOffset = Integer.MIN_VALUE;
    public final DateTimeZone iZoneRef;
    
    Info(DateTimeZone paramDateTimeZone, long paramLong)
    {
      iPeriodStart = paramLong;
      iZoneRef = paramDateTimeZone;
    }
    
    public String getNameKey(long paramLong)
    {
      if ((iNextInfo == null) || (paramLong < iNextInfo.iPeriodStart))
      {
        if (iNameKey == null) {
          iNameKey = iZoneRef.getNameKey(iPeriodStart);
        }
        return iNameKey;
      }
      return iNextInfo.getNameKey(paramLong);
    }
    
    public int getOffset(long paramLong)
    {
      if ((iNextInfo == null) || (paramLong < iNextInfo.iPeriodStart))
      {
        if (iOffset == Integer.MIN_VALUE) {
          iOffset = iZoneRef.getOffset(iPeriodStart);
        }
        return iOffset;
      }
      return iNextInfo.getOffset(paramLong);
    }
    
    public int getStandardOffset(long paramLong)
    {
      if ((iNextInfo == null) || (paramLong < iNextInfo.iPeriodStart))
      {
        if (iStandardOffset == Integer.MIN_VALUE) {
          iStandardOffset = iZoneRef.getStandardOffset(iPeriodStart);
        }
        return iStandardOffset;
      }
      return iNextInfo.getStandardOffset(paramLong);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.CachedDateTimeZone
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */