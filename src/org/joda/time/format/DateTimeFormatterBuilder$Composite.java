package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimeFormatterBuilder$Composite
  implements DateTimePrinter, DateTimeParser
{
  private final int iParsedLengthEstimate;
  private final DateTimeParser[] iParsers;
  private final int iPrintedLengthEstimate;
  private final DateTimePrinter[] iPrinters;
  
  DateTimeFormatterBuilder$Composite(List<Object> paramList)
  {
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    decompose(paramList, localArrayList2, localArrayList1);
    if ((localArrayList2.contains(null)) || (localArrayList2.isEmpty())) {
      iPrinters = null;
    }
    for (iPrintedLengthEstimate = 0; (localArrayList1.contains(null)) || (localArrayList1.isEmpty()); iPrintedLengthEstimate = j)
    {
      iParsers = null;
      iParsedLengthEstimate = 0;
      return;
      k = localArrayList2.size();
      iPrinters = new DateTimePrinter[k];
      j = 0;
      i = 0;
      while (i < k)
      {
        paramList = (DateTimePrinter)localArrayList2.get(i);
        j += paramList.estimatePrintedLength();
        iPrinters[i] = paramList;
        i += 1;
      }
    }
    int k = localArrayList1.size();
    iParsers = new DateTimeParser[k];
    int j = 0;
    int i = 0;
    while (i < k)
    {
      paramList = (DateTimeParser)localArrayList1.get(i);
      j += paramList.estimateParsedLength();
      iParsers[i] = paramList;
      i += 1;
    }
    iParsedLengthEstimate = j;
  }
  
  private void addArrayToList(List<Object> paramList, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      int i = 0;
      while (i < paramArrayOfObject.length)
      {
        paramList.add(paramArrayOfObject[i]);
        i += 1;
      }
    }
  }
  
  private void decompose(List<Object> paramList1, List<Object> paramList2, List<Object> paramList3)
  {
    int j = paramList1.size();
    int i = 0;
    if (i < j)
    {
      Object localObject = paramList1.get(i);
      if ((localObject instanceof Composite))
      {
        addArrayToList(paramList2, iPrinters);
        label49:
        localObject = paramList1.get(i + 1);
        if (!(localObject instanceof Composite)) {
          break label103;
        }
        addArrayToList(paramList3, iParsers);
      }
      for (;;)
      {
        i += 2;
        break;
        paramList2.add(localObject);
        break label49;
        label103:
        paramList3.add(localObject);
      }
    }
  }
  
  public int estimateParsedLength()
  {
    return iParsedLengthEstimate;
  }
  
  public int estimatePrintedLength()
  {
    return iPrintedLengthEstimate;
  }
  
  boolean isParser()
  {
    return iParsers != null;
  }
  
  boolean isPrinter()
  {
    return iPrinters != null;
  }
  
  public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
  {
    DateTimeParser[] arrayOfDateTimeParser = iParsers;
    if (arrayOfDateTimeParser == null) {
      throw new UnsupportedOperationException();
    }
    int k = arrayOfDateTimeParser.length;
    int j = 0;
    int i = paramInt;
    paramInt = j;
    while ((paramInt < k) && (i >= 0))
    {
      i = arrayOfDateTimeParser[paramInt].parseInto(paramDateTimeParserBucket, paramString, i);
      paramInt += 1;
    }
    return i;
  }
  
  public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    throws IOException
  {
    DateTimePrinter[] arrayOfDateTimePrinter = iPrinters;
    if (arrayOfDateTimePrinter == null) {
      throw new UnsupportedOperationException();
    }
    Locale localLocale = paramLocale;
    if (paramLocale == null) {
      localLocale = Locale.getDefault();
    }
    int j = arrayOfDateTimePrinter.length;
    int i = 0;
    while (i < j)
    {
      arrayOfDateTimePrinter[i].printTo(paramWriter, paramLong, paramChronology, paramInt, paramDateTimeZone, localLocale);
      i += 1;
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
    throws IOException
  {
    DateTimePrinter[] arrayOfDateTimePrinter = iPrinters;
    if (arrayOfDateTimePrinter == null) {
      throw new UnsupportedOperationException();
    }
    Locale localLocale = paramLocale;
    if (paramLocale == null) {
      localLocale = Locale.getDefault();
    }
    int j = arrayOfDateTimePrinter.length;
    int i = 0;
    while (i < j)
    {
      arrayOfDateTimePrinter[i].printTo(paramWriter, paramReadablePartial, localLocale);
      i += 1;
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
  {
    DateTimePrinter[] arrayOfDateTimePrinter = iPrinters;
    if (arrayOfDateTimePrinter == null) {
      throw new UnsupportedOperationException();
    }
    Locale localLocale = paramLocale;
    if (paramLocale == null) {
      localLocale = Locale.getDefault();
    }
    int j = arrayOfDateTimePrinter.length;
    int i = 0;
    while (i < j)
    {
      arrayOfDateTimePrinter[i].printTo(paramStringBuffer, paramLong, paramChronology, paramInt, paramDateTimeZone, localLocale);
      i += 1;
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    DateTimePrinter[] arrayOfDateTimePrinter = iPrinters;
    if (arrayOfDateTimePrinter == null) {
      throw new UnsupportedOperationException();
    }
    Locale localLocale = paramLocale;
    if (paramLocale == null) {
      localLocale = Locale.getDefault();
    }
    int j = arrayOfDateTimePrinter.length;
    int i = 0;
    while (i < j)
    {
      arrayOfDateTimePrinter[i].printTo(paramStringBuffer, paramReadablePartial, localLocale);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder.Composite
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */