package org.joda.time.tz;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

final class DateTimeZoneBuilder$Recurrence
{
  final String iNameKey;
  final DateTimeZoneBuilder.OfYear iOfYear;
  final int iSaveMillis;
  
  DateTimeZoneBuilder$Recurrence(DateTimeZoneBuilder.OfYear paramOfYear, String paramString, int paramInt)
  {
    iOfYear = paramOfYear;
    iNameKey = paramString;
    iSaveMillis = paramInt;
  }
  
  static Recurrence readFrom(DataInput paramDataInput)
    throws IOException
  {
    return new Recurrence(DateTimeZoneBuilder.OfYear.readFrom(paramDataInput), paramDataInput.readUTF(), (int)DateTimeZoneBuilder.readMillis(paramDataInput));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof Recurrence)) {
        break;
      }
      paramObject = (Recurrence)paramObject;
    } while ((iSaveMillis == iSaveMillis) && (iNameKey.equals(iNameKey)) && (iOfYear.equals(iOfYear)));
    return false;
    return false;
  }
  
  public String getNameKey()
  {
    return iNameKey;
  }
  
  public DateTimeZoneBuilder.OfYear getOfYear()
  {
    return iOfYear;
  }
  
  public int getSaveMillis()
  {
    return iSaveMillis;
  }
  
  public long next(long paramLong, int paramInt1, int paramInt2)
  {
    return iOfYear.next(paramLong, paramInt1, paramInt2);
  }
  
  public long previous(long paramLong, int paramInt1, int paramInt2)
  {
    return iOfYear.previous(paramLong, paramInt1, paramInt2);
  }
  
  Recurrence rename(String paramString)
  {
    return new Recurrence(iOfYear, paramString, iSaveMillis);
  }
  
  Recurrence renameAppend(String paramString)
  {
    return rename((iNameKey + paramString).intern());
  }
  
  public void writeTo(DataOutput paramDataOutput)
    throws IOException
  {
    iOfYear.writeTo(paramDataOutput);
    paramDataOutput.writeUTF(iNameKey);
    DateTimeZoneBuilder.writeMillis(paramDataOutput, iSaveMillis);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.DateTimeZoneBuilder.Recurrence
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */