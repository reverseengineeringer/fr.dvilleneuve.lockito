package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.chrono.ISOChronology;

final class DateTimeZoneBuilder$PrecalculatedZone
  extends DateTimeZone
{
  private static final long serialVersionUID = 7811976468055766265L;
  private final String[] iNameKeys;
  private final int[] iStandardOffsets;
  private final DateTimeZoneBuilder.DSTZone iTailZone;
  private final long[] iTransitions;
  private final int[] iWallOffsets;
  
  private DateTimeZoneBuilder$PrecalculatedZone(String paramString, long[] paramArrayOfLong, int[] paramArrayOfInt1, int[] paramArrayOfInt2, String[] paramArrayOfString, DateTimeZoneBuilder.DSTZone paramDSTZone)
  {
    super(paramString);
    iTransitions = paramArrayOfLong;
    iWallOffsets = paramArrayOfInt1;
    iStandardOffsets = paramArrayOfInt2;
    iNameKeys = paramArrayOfString;
    iTailZone = paramDSTZone;
  }
  
  static PrecalculatedZone create(String paramString, boolean paramBoolean, ArrayList<DateTimeZoneBuilder.Transition> paramArrayList, DateTimeZoneBuilder.DSTZone paramDSTZone)
  {
    int j = paramArrayList.size();
    if (j == 0) {
      throw new IllegalArgumentException();
    }
    long[] arrayOfLong = new long[j];
    int[] arrayOfInt1 = new int[j];
    int[] arrayOfInt2 = new int[j];
    String[] arrayOfString = new String[j];
    Object localObject1 = null;
    int i = 0;
    Object localObject2;
    while (i < j)
    {
      localObject2 = (DateTimeZoneBuilder.Transition)paramArrayList.get(i);
      if (!((DateTimeZoneBuilder.Transition)localObject2).isTransitionFrom((DateTimeZoneBuilder.Transition)localObject1)) {
        throw new IllegalArgumentException(paramString);
      }
      arrayOfLong[i] = ((DateTimeZoneBuilder.Transition)localObject2).getMillis();
      arrayOfInt1[i] = ((DateTimeZoneBuilder.Transition)localObject2).getWallOffset();
      arrayOfInt2[i] = ((DateTimeZoneBuilder.Transition)localObject2).getStandardOffset();
      arrayOfString[i] = ((DateTimeZoneBuilder.Transition)localObject2).getNameKey();
      localObject1 = localObject2;
      i += 1;
    }
    paramArrayList = new String[5];
    Object localObject3 = new DateFormatSymbols(Locale.ENGLISH).getZoneStrings();
    i = 0;
    while (i < localObject3.length)
    {
      localObject2 = localObject3[i];
      localObject1 = paramArrayList;
      if (localObject2 != null)
      {
        localObject1 = paramArrayList;
        if (localObject2.length == 5)
        {
          localObject1 = paramArrayList;
          if (paramString.equals(localObject2[0])) {
            localObject1 = localObject2;
          }
        }
      }
      i += 1;
      paramArrayList = (ArrayList<DateTimeZoneBuilder.Transition>)localObject1;
    }
    localObject1 = ISOChronology.getInstanceUTC();
    i = 0;
    if (i < arrayOfString.length - 1)
    {
      localObject2 = arrayOfString[i];
      localObject3 = arrayOfString[(i + 1)];
      long l1 = arrayOfInt1[i];
      long l2 = arrayOfInt1[(i + 1)];
      long l3 = arrayOfInt2[i];
      long l4 = arrayOfInt2[(i + 1)];
      Period localPeriod = new Period(arrayOfLong[i], arrayOfLong[(i + 1)], PeriodType.yearMonthDay(), (Chronology)localObject1);
      j = i;
      if (l1 != l2)
      {
        j = i;
        if (l3 == l4)
        {
          j = i;
          if (((String)localObject2).equals(localObject3))
          {
            j = i;
            if (localPeriod.getYears() == 0)
            {
              j = i;
              if (localPeriod.getMonths() > 4)
              {
                j = i;
                if (localPeriod.getMonths() < 8)
                {
                  j = i;
                  if (((String)localObject2).equals(paramArrayList[2]))
                  {
                    j = i;
                    if (((String)localObject2).equals(paramArrayList[4]))
                    {
                      if (ZoneInfoCompiler.verbose())
                      {
                        System.out.println("Fixing duplicate name key - " + (String)localObject3);
                        System.out.println("     - " + new DateTime(arrayOfLong[i], (Chronology)localObject1) + " - " + new DateTime(arrayOfLong[(i + 1)], (Chronology)localObject1));
                      }
                      if (l1 <= l2) {
                        break label572;
                      }
                      arrayOfString[i] = ((String)localObject2 + "-Summer").intern();
                      j = i;
                    }
                  }
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        i = j + 1;
        break;
        label572:
        j = i;
        if (l1 < l2)
        {
          arrayOfString[(i + 1)] = ((String)localObject3 + "-Summer").intern();
          j = i + 1;
        }
      }
    }
    paramArrayList = paramDSTZone;
    if (paramDSTZone != null)
    {
      paramArrayList = paramDSTZone;
      if (iStartRecurrence.getNameKey().equals(iEndRecurrence.getNameKey()))
      {
        if (ZoneInfoCompiler.verbose()) {
          System.out.println("Fixing duplicate recurrent name key - " + iStartRecurrence.getNameKey());
        }
        if (iStartRecurrence.getSaveMillis() <= 0) {
          break label749;
        }
        paramArrayList = new DateTimeZoneBuilder.DSTZone(paramDSTZone.getID(), iStandardOffset, iStartRecurrence.renameAppend("-Summer"), iEndRecurrence);
      }
    }
    if (paramBoolean) {}
    for (;;)
    {
      return new PrecalculatedZone(paramString, arrayOfLong, arrayOfInt1, arrayOfInt2, arrayOfString, paramArrayList);
      label749:
      paramArrayList = new DateTimeZoneBuilder.DSTZone(paramDSTZone.getID(), iStandardOffset, iStartRecurrence, iEndRecurrence.renameAppend("-Summer"));
      break;
      paramString = "";
    }
  }
  
  static PrecalculatedZone readFrom(DataInput paramDataInput, String paramString)
    throws IOException
  {
    int k = paramDataInput.readUnsignedShort();
    Object localObject = new String[k];
    int i = 0;
    while (i < k)
    {
      localObject[i] = paramDataInput.readUTF();
      i += 1;
    }
    int m = paramDataInput.readInt();
    long[] arrayOfLong = new long[m];
    int[] arrayOfInt1 = new int[m];
    int[] arrayOfInt2 = new int[m];
    String[] arrayOfString = new String[m];
    i = 0;
    for (;;)
    {
      int j;
      if (i < m)
      {
        arrayOfLong[i] = DateTimeZoneBuilder.readMillis(paramDataInput);
        arrayOfInt1[i] = ((int)DateTimeZoneBuilder.readMillis(paramDataInput));
        arrayOfInt2[i] = ((int)DateTimeZoneBuilder.readMillis(paramDataInput));
        if (k < 256) {}
        try
        {
          j = paramDataInput.readUnsignedByte();
        }
        catch (ArrayIndexOutOfBoundsException paramDataInput)
        {
          throw new IOException("Invalid encoding");
        }
        j = paramDataInput.readUnsignedShort();
      }
      else
      {
        localObject = null;
        if (paramDataInput.readBoolean()) {
          localObject = DateTimeZoneBuilder.DSTZone.readFrom(paramDataInput, paramString);
        }
        return new PrecalculatedZone(paramString, arrayOfLong, arrayOfInt1, arrayOfInt2, arrayOfString, (DateTimeZoneBuilder.DSTZone)localObject);
      }
      arrayOfString[i] = localObject[j];
      i += 1;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PrecalculatedZone)) {
        break label121;
      }
      paramObject = (PrecalculatedZone)paramObject;
      if ((!getID().equals(((PrecalculatedZone)paramObject).getID())) || (!Arrays.equals(iTransitions, iTransitions)) || (!Arrays.equals(iNameKeys, iNameKeys)) || (!Arrays.equals(iWallOffsets, iWallOffsets)) || (!Arrays.equals(iStandardOffsets, iStandardOffsets))) {
        break;
      }
      if (iTailZone != null) {
        break label105;
      }
    } while (iTailZone == null);
    label105:
    while (!iTailZone.equals(iTailZone)) {
      return false;
    }
    return true;
    label121:
    return false;
  }
  
  public String getNameKey(long paramLong)
  {
    long[] arrayOfLong = iTransitions;
    int i = Arrays.binarySearch(arrayOfLong, paramLong);
    if (i >= 0) {
      return iNameKeys[i];
    }
    i ^= 0xFFFFFFFF;
    if (i < arrayOfLong.length)
    {
      if (i > 0) {
        return iNameKeys[(i - 1)];
      }
      return "UTC";
    }
    if (iTailZone == null) {
      return iNameKeys[(i - 1)];
    }
    return iTailZone.getNameKey(paramLong);
  }
  
  public int getOffset(long paramLong)
  {
    long[] arrayOfLong = iTransitions;
    int i = Arrays.binarySearch(arrayOfLong, paramLong);
    if (i >= 0) {
      return iWallOffsets[i];
    }
    i ^= 0xFFFFFFFF;
    if (i < arrayOfLong.length)
    {
      if (i > 0) {
        return iWallOffsets[(i - 1)];
      }
      return 0;
    }
    if (iTailZone == null) {
      return iWallOffsets[(i - 1)];
    }
    return iTailZone.getOffset(paramLong);
  }
  
  public int getStandardOffset(long paramLong)
  {
    long[] arrayOfLong = iTransitions;
    int i = Arrays.binarySearch(arrayOfLong, paramLong);
    if (i >= 0) {
      return iStandardOffsets[i];
    }
    i ^= 0xFFFFFFFF;
    if (i < arrayOfLong.length)
    {
      if (i > 0) {
        return iStandardOffsets[(i - 1)];
      }
      return 0;
    }
    if (iTailZone == null) {
      return iStandardOffsets[(i - 1)];
    }
    return iTailZone.getStandardOffset(paramLong);
  }
  
  public boolean isCachable()
  {
    if (iTailZone != null) {
      return true;
    }
    long[] arrayOfLong = iTransitions;
    if (arrayOfLong.length <= 1) {
      return false;
    }
    double d1 = 0.0D;
    int j = 0;
    int i = 1;
    while (i < arrayOfLong.length)
    {
      long l = arrayOfLong[i] - arrayOfLong[(i - 1)];
      int k = j;
      double d2 = d1;
      if (l < 63158400000L)
      {
        d2 = d1 + l;
        k = j + 1;
      }
      i += 1;
      j = k;
      d1 = d2;
    }
    return (j > 0) && (d1 / j / 8.64E7D >= 25.0D);
  }
  
  public boolean isFixed()
  {
    return false;
  }
  
  public long nextTransition(long paramLong)
  {
    long[] arrayOfLong = iTransitions;
    int i = Arrays.binarySearch(arrayOfLong, paramLong);
    if (i >= 0) {
      i += 1;
    }
    while (i < arrayOfLong.length)
    {
      return arrayOfLong[i];
      i ^= 0xFFFFFFFF;
    }
    if (iTailZone == null) {
      return paramLong;
    }
    long l2 = arrayOfLong[(arrayOfLong.length - 1)];
    long l1 = paramLong;
    if (paramLong < l2) {
      l1 = l2;
    }
    return iTailZone.nextTransition(l1);
  }
  
  public long previousTransition(long paramLong)
  {
    long[] arrayOfLong = iTransitions;
    int i = Arrays.binarySearch(arrayOfLong, paramLong);
    long l1;
    if (i >= 0)
    {
      l1 = paramLong;
      if (paramLong > Long.MIN_VALUE) {
        l1 = paramLong - 1L;
      }
    }
    long l2;
    do
    {
      do
      {
        do
        {
          return l1;
          i ^= 0xFFFFFFFF;
          if (i >= arrayOfLong.length) {
            break;
          }
          l1 = paramLong;
        } while (i <= 0);
        l2 = arrayOfLong[(i - 1)];
        l1 = paramLong;
      } while (l2 <= Long.MIN_VALUE);
      return l2 - 1L;
      if (iTailZone != null)
      {
        l1 = iTailZone.previousTransition(paramLong);
        if (l1 < paramLong) {
          return l1;
        }
      }
      l2 = arrayOfLong[(i - 1)];
      l1 = paramLong;
    } while (l2 <= Long.MIN_VALUE);
    return l2 - 1L;
  }
  
  public void writeTo(DataOutput paramDataOutput)
    throws IOException
  {
    int k = iTransitions.length;
    Object localObject = new HashSet();
    int i = 0;
    while (i < k)
    {
      ((Set)localObject).add(iNameKeys[i]);
      i += 1;
    }
    int m = ((Set)localObject).size();
    if (m > 65535) {
      throw new UnsupportedOperationException("String pool is too large");
    }
    String[] arrayOfString = new String[m];
    localObject = ((Set)localObject).iterator();
    i = 0;
    while (((Iterator)localObject).hasNext())
    {
      arrayOfString[i] = ((String)((Iterator)localObject).next());
      i += 1;
    }
    paramDataOutput.writeShort(m);
    i = 0;
    while (i < m)
    {
      paramDataOutput.writeUTF(arrayOfString[i]);
      i += 1;
    }
    paramDataOutput.writeInt(k);
    i = 0;
    if (i < k)
    {
      DateTimeZoneBuilder.writeMillis(paramDataOutput, iTransitions[i]);
      DateTimeZoneBuilder.writeMillis(paramDataOutput, iWallOffsets[i]);
      DateTimeZoneBuilder.writeMillis(paramDataOutput, iStandardOffsets[i]);
      localObject = iNameKeys[i];
      int j = 0;
      for (;;)
      {
        if (j < m)
        {
          if (!arrayOfString[j].equals(localObject)) {
            break label263;
          }
          if (m >= 256) {
            break label253;
          }
          paramDataOutput.writeByte(j);
        }
        for (;;)
        {
          i += 1;
          break;
          label253:
          paramDataOutput.writeShort(j);
        }
        label263:
        j += 1;
      }
    }
    if (iTailZone != null) {}
    for (boolean bool = true;; bool = false)
    {
      paramDataOutput.writeBoolean(bool);
      if (iTailZone != null) {
        iTailZone.writeTo(paramDataOutput);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.PrecalculatedZone
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */