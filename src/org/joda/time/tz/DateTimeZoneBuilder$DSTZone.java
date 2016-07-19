package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.joda.time.DateTimeZone;

final class DateTimeZoneBuilder$DSTZone
  extends DateTimeZone
{
  private static final long serialVersionUID = 6941492635554961361L;
  final DateTimeZoneBuilder.Recurrence iEndRecurrence;
  final int iStandardOffset;
  final DateTimeZoneBuilder.Recurrence iStartRecurrence;
  
  DateTimeZoneBuilder$DSTZone(String paramString, int paramInt, DateTimeZoneBuilder.Recurrence paramRecurrence1, DateTimeZoneBuilder.Recurrence paramRecurrence2)
  {
    super(paramString);
    iStandardOffset = paramInt;
    iStartRecurrence = paramRecurrence1;
    iEndRecurrence = paramRecurrence2;
  }
  
  private DateTimeZoneBuilder.Recurrence findMatchingRecurrence(long paramLong)
  {
    int i = iStandardOffset;
    DateTimeZoneBuilder.Recurrence localRecurrence1 = iStartRecurrence;
    DateTimeZoneBuilder.Recurrence localRecurrence2 = iEndRecurrence;
    try
    {
      l1 = localRecurrence1.next(paramLong, i, localRecurrence2.getSaveMillis());
      try
      {
        long l2 = localRecurrence2.next(paramLong, i, localRecurrence1.getSaveMillis());
        paramLong = l2;
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        for (;;) {}
      }
      catch (ArithmeticException localArithmeticException2)
      {
        for (;;) {}
      }
      if (l1 > paramLong) {
        return localRecurrence1;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      for (;;)
      {
        l1 = paramLong;
      }
    }
    catch (ArithmeticException localArithmeticException1)
    {
      for (;;)
      {
        long l1 = paramLong;
      }
    }
    return localRecurrence2;
  }
  
  static DSTZone readFrom(DataInput paramDataInput, String paramString)
    throws IOException
  {
    return new DSTZone(paramString, (int)DateTimeZoneBuilder.readMillis(paramDataInput), DateTimeZoneBuilder.Recurrence.readFrom(paramDataInput), DateTimeZoneBuilder.Recurrence.readFrom(paramDataInput));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof DSTZone)) {
        break;
      }
      paramObject = (DSTZone)paramObject;
    } while ((getID().equals(((DSTZone)paramObject).getID())) && (iStandardOffset == iStandardOffset) && (iStartRecurrence.equals(iStartRecurrence)) && (iEndRecurrence.equals(iEndRecurrence)));
    return false;
    return false;
  }
  
  public String getNameKey(long paramLong)
  {
    return findMatchingRecurrence(paramLong).getNameKey();
  }
  
  public int getOffset(long paramLong)
  {
    return iStandardOffset + findMatchingRecurrence(paramLong).getSaveMillis();
  }
  
  public int getStandardOffset(long paramLong)
  {
    return iStandardOffset;
  }
  
  public boolean isFixed()
  {
    return false;
  }
  
  public long nextTransition(long paramLong)
  {
    int i = iStandardOffset;
    DateTimeZoneBuilder.Recurrence localRecurrence1 = iStartRecurrence;
    DateTimeZoneBuilder.Recurrence localRecurrence2 = iEndRecurrence;
    long l1;
    for (;;)
    {
      long l2;
      try
      {
        l2 = localRecurrence1.next(paramLong, i, localRecurrence2.getSaveMillis());
        l1 = l2;
        if (paramLong > 0L)
        {
          l1 = l2;
          if (l2 < 0L) {
            l1 = paramLong;
          }
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        long l3;
        l1 = paramLong;
        continue;
      }
      catch (ArithmeticException localArithmeticException2)
      {
        l1 = paramLong;
        continue;
      }
      try
      {
        l3 = localRecurrence2.next(paramLong, i, localRecurrence1.getSaveMillis());
        l2 = l3;
        if (paramLong > 0L)
        {
          l2 = l3;
          if (l3 < 0L) {
            l2 = paramLong;
          }
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        l2 = paramLong;
      }
      catch (ArithmeticException localArithmeticException1)
      {
        l2 = paramLong;
      }
    }
    if (l1 > l2) {
      return l2;
    }
    return l1;
  }
  
  public long previousTransition(long paramLong)
  {
    paramLong += 1L;
    int i = iStandardOffset;
    DateTimeZoneBuilder.Recurrence localRecurrence1 = iStartRecurrence;
    DateTimeZoneBuilder.Recurrence localRecurrence2 = iEndRecurrence;
    for (;;)
    {
      long l2;
      long l1;
      try
      {
        l2 = localRecurrence1.previous(paramLong, i, localRecurrence2.getSaveMillis());
        l1 = l2;
        if (paramLong < 0L)
        {
          l1 = l2;
          if (l2 > 0L) {
            l1 = paramLong;
          }
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        long l3;
        l1 = paramLong;
        continue;
      }
      catch (ArithmeticException localArithmeticException2)
      {
        l1 = paramLong;
        continue;
      }
      try
      {
        l3 = localRecurrence2.previous(paramLong, i, localRecurrence1.getSaveMillis());
        l2 = l3;
        if (paramLong < 0L)
        {
          l2 = l3;
          if (l3 > 0L) {
            l2 = paramLong;
          }
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        l2 = paramLong;
      }
      catch (ArithmeticException localArithmeticException1)
      {
        l2 = paramLong;
        continue;
        l1 = l2;
      }
    }
    if (l1 > l2) {
      return l1 - 1L;
    }
  }
  
  public void writeTo(DataOutput paramDataOutput)
    throws IOException
  {
    DateTimeZoneBuilder.writeMillis(paramDataOutput, iStandardOffset);
    iStartRecurrence.writeTo(paramDataOutput);
    iEndRecurrence.writeTo(paramDataOutput);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.DSTZone
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */