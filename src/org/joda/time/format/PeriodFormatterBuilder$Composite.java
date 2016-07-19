package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

class PeriodFormatterBuilder$Composite
  implements PeriodPrinter, PeriodParser
{
  private final PeriodParser[] iParsers;
  private final PeriodPrinter[] iPrinters;
  
  PeriodFormatterBuilder$Composite(List<Object> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    decompose(paramList, localArrayList1, localArrayList2);
    if (localArrayList1.size() <= 0) {}
    for (iPrinters = null; localArrayList2.size() <= 0; iPrinters = ((PeriodPrinter[])localArrayList1.toArray(new PeriodPrinter[localArrayList1.size()])))
    {
      iParsers = null;
      return;
    }
    iParsers = ((PeriodParser[])localArrayList2.toArray(new PeriodParser[localArrayList2.size()]));
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
      if ((localObject instanceof PeriodPrinter))
      {
        if ((localObject instanceof Composite)) {
          addArrayToList(paramList2, iPrinters);
        }
      }
      else
      {
        label57:
        localObject = paramList1.get(i + 1);
        if ((localObject instanceof PeriodParser))
        {
          if (!(localObject instanceof Composite)) {
            break label119;
          }
          addArrayToList(paramList3, iParsers);
        }
      }
      for (;;)
      {
        i += 2;
        break;
        paramList2.add(localObject);
        break label57;
        label119:
        paramList3.add(localObject);
      }
    }
  }
  
  public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    int i = 0;
    PeriodPrinter[] arrayOfPeriodPrinter = iPrinters;
    int j = arrayOfPeriodPrinter.length;
    for (;;)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      i += arrayOfPeriodPrinter[j].calculatePrintedLength(paramReadablePeriod, paramLocale);
    }
    return i;
  }
  
  public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale)
  {
    int i = 0;
    PeriodPrinter[] arrayOfPeriodPrinter = iPrinters;
    int j = arrayOfPeriodPrinter.length;
    while (i < paramInt)
    {
      j -= 1;
      if (j < 0) {
        break;
      }
      i += arrayOfPeriodPrinter[j].countFieldsToPrint(paramReadablePeriod, Integer.MAX_VALUE, paramLocale);
    }
    return i;
  }
  
  public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
  {
    PeriodParser[] arrayOfPeriodParser = iParsers;
    if (arrayOfPeriodParser == null) {
      throw new UnsupportedOperationException();
    }
    int k = arrayOfPeriodParser.length;
    int j = 0;
    int i = paramInt;
    paramInt = j;
    while ((paramInt < k) && (i >= 0))
    {
      i = arrayOfPeriodParser[paramInt].parseInto(paramReadWritablePeriod, paramString, i, paramLocale);
      paramInt += 1;
    }
    return i;
  }
  
  public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale)
    throws IOException
  {
    PeriodPrinter[] arrayOfPeriodPrinter = iPrinters;
    int j = arrayOfPeriodPrinter.length;
    int i = 0;
    while (i < j)
    {
      arrayOfPeriodPrinter[i].printTo(paramWriter, paramReadablePeriod, paramLocale);
      i += 1;
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    PeriodPrinter[] arrayOfPeriodPrinter = iPrinters;
    int j = arrayOfPeriodPrinter.length;
    int i = 0;
    while (i < j)
    {
      arrayOfPeriodPrinter[i].printTo(paramStringBuffer, paramReadablePeriod, paramLocale);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.Composite
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */