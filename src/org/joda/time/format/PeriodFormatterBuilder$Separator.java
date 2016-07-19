package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.TreeSet;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

class PeriodFormatterBuilder$Separator
  implements PeriodPrinter, PeriodParser
{
  private volatile PeriodParser iAfterParser;
  private volatile PeriodPrinter iAfterPrinter;
  private final PeriodParser iBeforeParser;
  private final PeriodPrinter iBeforePrinter;
  private final String iFinalText;
  private final String[] iParsedForms;
  private final String iText;
  private final boolean iUseAfter;
  private final boolean iUseBefore;
  
  PeriodFormatterBuilder$Separator(String paramString1, String paramString2, String[] paramArrayOfString, PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser, boolean paramBoolean1, boolean paramBoolean2)
  {
    iText = paramString1;
    iFinalText = paramString2;
    if (((paramString2 == null) || (paramString1.equals(paramString2))) && ((paramArrayOfString == null) || (paramArrayOfString.length == 0))) {}
    for (iParsedForms = new String[] { paramString1 };; iParsedForms = ((String[])paramString1.toArray(new String[paramString1.size()])))
    {
      iBeforePrinter = paramPeriodPrinter;
      iBeforeParser = paramPeriodParser;
      iUseBefore = paramBoolean1;
      iUseAfter = paramBoolean2;
      return;
      TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
      localTreeSet.add(paramString1);
      localTreeSet.add(paramString2);
      if (paramArrayOfString != null)
      {
        int i = paramArrayOfString.length;
        for (;;)
        {
          i -= 1;
          if (i < 0) {
            break;
          }
          localTreeSet.add(paramArrayOfString[i]);
        }
      }
      paramString1 = new ArrayList(localTreeSet);
      Collections.reverse(paramString1);
    }
  }
  
  public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    PeriodPrinter localPeriodPrinter1 = iBeforePrinter;
    PeriodPrinter localPeriodPrinter2 = iAfterPrinter;
    int j = localPeriodPrinter1.calculatePrintedLength(paramReadablePeriod, paramLocale) + localPeriodPrinter2.calculatePrintedLength(paramReadablePeriod, paramLocale);
    int i;
    if (iUseBefore)
    {
      i = j;
      if (localPeriodPrinter1.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)
      {
        if (!iUseAfter) {
          break label112;
        }
        int k = localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 2, paramLocale);
        i = j;
        if (k > 0)
        {
          if (k <= 1) {
            break label104;
          }
          paramReadablePeriod = iText;
          i = j + paramReadablePeriod.length();
        }
      }
    }
    label104:
    label112:
    do
    {
      do
      {
        return i;
        paramReadablePeriod = iFinalText;
        break;
        return j + iText.length();
        i = j;
      } while (!iUseAfter);
      i = j;
    } while (localPeriodPrinter2.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) <= 0);
    return j + iText.length();
  }
  
  public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale)
  {
    int j = iBeforePrinter.countFieldsToPrint(paramReadablePeriod, paramInt, paramLocale);
    int i = j;
    if (j < paramInt) {
      i = j + iAfterPrinter.countFieldsToPrint(paramReadablePeriod, paramInt, paramLocale);
    }
    return i;
  }
  
  Separator finish(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser)
  {
    iAfterPrinter = paramPeriodPrinter;
    iAfterParser = paramPeriodParser;
    return this;
  }
  
  public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
  {
    int m = iBeforeParser.parseInto(paramReadWritablePeriod, paramString, paramInt, paramLocale);
    if (m < 0) {
      return m;
    }
    int n = 0;
    int i1 = -1;
    int k = n;
    int j = i1;
    int i = m;
    String[] arrayOfString;
    int i2;
    if (m > paramInt)
    {
      arrayOfString = iParsedForms;
      i2 = arrayOfString.length;
      paramInt = 0;
    }
    for (;;)
    {
      k = n;
      j = i1;
      i = m;
      String str;
      if (paramInt < i2)
      {
        str = arrayOfString[paramInt];
        if ((str != null) && (str.length() != 0) && (!paramString.regionMatches(true, m, str, 0, str.length()))) {
          break label166;
        }
        if (str != null) {
          break label157;
        }
      }
      label157:
      for (paramInt = 0;; paramInt = str.length())
      {
        i = m + paramInt;
        k = 1;
        j = paramInt;
        paramInt = iAfterParser.parseInto(paramReadWritablePeriod, paramString, i, paramLocale);
        if (paramInt >= 0) {
          break;
        }
        return paramInt;
      }
      label166:
      paramInt += 1;
    }
    if ((k != 0) && (paramInt == i) && (j > 0)) {
      return i ^ 0xFFFFFFFF;
    }
    if ((paramInt > i) && (k == 0) && (!iUseBefore)) {
      return i ^ 0xFFFFFFFF;
    }
    return paramInt;
  }
  
  public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale)
    throws IOException
  {
    Object localObject = iBeforePrinter;
    PeriodPrinter localPeriodPrinter = iAfterPrinter;
    ((PeriodPrinter)localObject).printTo(paramWriter, paramReadablePeriod, paramLocale);
    if (iUseBefore) {
      if (((PeriodPrinter)localObject).countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)
      {
        if (!iUseAfter) {
          break label104;
        }
        int i = localPeriodPrinter.countFieldsToPrint(paramReadablePeriod, 2, paramLocale);
        if (i > 0)
        {
          if (i <= 1) {
            break label95;
          }
          localObject = iText;
          paramWriter.write((String)localObject);
        }
      }
    }
    for (;;)
    {
      localPeriodPrinter.printTo(paramWriter, paramReadablePeriod, paramLocale);
      return;
      label95:
      localObject = iFinalText;
      break;
      label104:
      paramWriter.write(iText);
      continue;
      if ((iUseAfter) && (localPeriodPrinter.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)) {
        paramWriter.write(iText);
      }
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    Object localObject = iBeforePrinter;
    PeriodPrinter localPeriodPrinter = iAfterPrinter;
    ((PeriodPrinter)localObject).printTo(paramStringBuffer, paramReadablePeriod, paramLocale);
    if (iUseBefore) {
      if (((PeriodPrinter)localObject).countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)
      {
        if (!iUseAfter) {
          break label105;
        }
        int i = localPeriodPrinter.countFieldsToPrint(paramReadablePeriod, 2, paramLocale);
        if (i > 0)
        {
          if (i <= 1) {
            break label96;
          }
          localObject = iText;
          paramStringBuffer.append((String)localObject);
        }
      }
    }
    for (;;)
    {
      localPeriodPrinter.printTo(paramStringBuffer, paramReadablePeriod, paramLocale);
      return;
      label96:
      localObject = iFinalText;
      break;
      label105:
      paramStringBuffer.append(iText);
      continue;
      if ((iUseAfter) && (localPeriodPrinter.countFieldsToPrint(paramReadablePeriod, 1, paramLocale) > 0)) {
        paramStringBuffer.append(iText);
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.Separator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */