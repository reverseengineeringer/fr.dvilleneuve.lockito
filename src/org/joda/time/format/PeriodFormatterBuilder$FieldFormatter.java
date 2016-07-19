package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

class PeriodFormatterBuilder$FieldFormatter
  implements PeriodPrinter, PeriodParser
{
  private final FieldFormatter[] iFieldFormatters;
  private final int iFieldType;
  private final int iMaxParsedDigits;
  private final int iMinPrintedDigits;
  private final PeriodFormatterBuilder.PeriodFieldAffix iPrefix;
  private final int iPrintZeroSetting;
  private final boolean iRejectSignedValues;
  private final PeriodFormatterBuilder.PeriodFieldAffix iSuffix;
  
  PeriodFormatterBuilder$FieldFormatter(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, FieldFormatter[] paramArrayOfFieldFormatter, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix1, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix2)
  {
    iMinPrintedDigits = paramInt1;
    iPrintZeroSetting = paramInt2;
    iMaxParsedDigits = paramInt3;
    iRejectSignedValues = paramBoolean;
    iFieldType = paramInt4;
    iFieldFormatters = paramArrayOfFieldFormatter;
    iPrefix = paramPeriodFieldAffix1;
    iSuffix = paramPeriodFieldAffix2;
  }
  
  PeriodFormatterBuilder$FieldFormatter(FieldFormatter paramFieldFormatter, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix)
  {
    iMinPrintedDigits = iMinPrintedDigits;
    iPrintZeroSetting = iPrintZeroSetting;
    iMaxParsedDigits = iMaxParsedDigits;
    iRejectSignedValues = iRejectSignedValues;
    iFieldType = iFieldType;
    iFieldFormatters = iFieldFormatters;
    iPrefix = iPrefix;
    Object localObject = paramPeriodFieldAffix;
    if (iSuffix != null) {
      localObject = new PeriodFormatterBuilder.CompositeAffix(iSuffix, paramPeriodFieldAffix);
    }
    iSuffix = ((PeriodFormatterBuilder.PeriodFieldAffix)localObject);
  }
  
  private int parseInt(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 10) {
      return Integer.parseInt(paramString.substring(paramInt1, paramInt1 + paramInt2));
    }
    if (paramInt2 <= 0) {
      return 0;
    }
    int i = paramInt1 + 1;
    int j = paramString.charAt(paramInt1);
    paramInt2 -= 1;
    int k;
    if (j == 45)
    {
      paramInt2 -= 1;
      if (paramInt2 < 0) {
        return 0;
      }
      k = 1;
      paramInt1 = i + 1;
      j = paramString.charAt(i);
    }
    for (i = k;; i = k)
    {
      k = j - 48;
      j = paramInt1;
      paramInt1 = k;
      while (paramInt2 > 0)
      {
        paramInt1 = (paramInt1 << 3) + (paramInt1 << 1) + paramString.charAt(j) - 48;
        paramInt2 -= 1;
        j += 1;
      }
      k = 0;
      paramInt1 = i;
    }
    paramInt2 = paramInt1;
    if (i != 0) {
      paramInt2 = -paramInt1;
    }
    return paramInt2;
  }
  
  public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    long l2 = getFieldValue(paramReadablePeriod);
    if (l2 == Long.MAX_VALUE)
    {
      i = 0;
      return i;
    }
    int j = Math.max(FormatUtils.calculateDigitCount(l2), iMinPrintedDigits);
    int i = j;
    long l1 = l2;
    if (iFieldType >= 8) {
      if (l2 >= 0L) {
        break label166;
      }
    }
    label166:
    for (i = Math.max(j, 5);; i = Math.max(j, 4))
    {
      j = i + 1;
      i = j;
      if (iFieldType == 9)
      {
        i = j;
        if (Math.abs(l2) % 1000L == 0L) {
          i = j - 4;
        }
      }
      l1 = l2 / 1000L;
      int k = (int)l1;
      j = i;
      if (iPrefix != null) {
        j = i + iPrefix.calculatePrintedLength(k);
      }
      i = j;
      if (iSuffix == null) {
        break;
      }
      return j + iSuffix.calculatePrintedLength(k);
    }
  }
  
  public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale)
  {
    if (paramInt <= 0) {}
    while ((iPrintZeroSetting != 4) && (getFieldValue(paramReadablePeriod) == Long.MAX_VALUE)) {
      return 0;
    }
    return 1;
  }
  
  int getFieldType()
  {
    return iFieldType;
  }
  
  long getFieldValue(ReadablePeriod paramReadablePeriod)
  {
    PeriodType localPeriodType;
    long l2;
    if (iPrintZeroSetting == 4)
    {
      localPeriodType = null;
      if ((localPeriodType == null) || (isSupported(localPeriodType, iFieldType))) {
        break label48;
      }
      l2 = Long.MAX_VALUE;
    }
    label48:
    long l1;
    label124:
    do
    {
      return l2;
      localPeriodType = paramReadablePeriod.getPeriodType();
      break;
      switch (iFieldType)
      {
      default: 
        return Long.MAX_VALUE;
      case 0: 
        l1 = paramReadablePeriod.get(DurationFieldType.years());
        l2 = l1;
      }
    } while (l1 != 0L);
    int i;
    switch (iPrintZeroSetting)
    {
    case 3: 
    case 4: 
    default: 
      return l1;
    case 1: 
      if ((isZero(paramReadablePeriod)) && (iFieldFormatters[iFieldType] == this)) {
        i = Math.min(iFieldType, 8) - 1;
      }
      break;
    case 5: 
    case 2: 
      for (;;)
      {
        l2 = l1;
        if (i < 0) {
          break;
        }
        l2 = l1;
        if (i > 9) {
          break;
        }
        if ((isSupported(localPeriodType, i)) && (iFieldFormatters[i] != null))
        {
          return Long.MAX_VALUE;
          l1 = paramReadablePeriod.get(DurationFieldType.months());
          break label124;
          l1 = paramReadablePeriod.get(DurationFieldType.weeks());
          break label124;
          l1 = paramReadablePeriod.get(DurationFieldType.days());
          break label124;
          l1 = paramReadablePeriod.get(DurationFieldType.hours());
          break label124;
          l1 = paramReadablePeriod.get(DurationFieldType.minutes());
          break label124;
          l1 = paramReadablePeriod.get(DurationFieldType.seconds());
          break label124;
          l1 = paramReadablePeriod.get(DurationFieldType.millis());
          break label124;
          i = paramReadablePeriod.get(DurationFieldType.seconds());
          int j = paramReadablePeriod.get(DurationFieldType.millis());
          l1 = i * 1000L + j;
          break label124;
          return Long.MAX_VALUE;
          if ((isZero(paramReadablePeriod)) && (iFieldFormatters[iFieldType] == this))
          {
            i = iFieldType + 1;
            for (;;)
            {
              l2 = l1;
              if (i > 9) {
                break;
              }
              if ((isSupported(localPeriodType, i)) && (iFieldFormatters[i] != null)) {
                return Long.MAX_VALUE;
              }
              i += 1;
            }
          }
          return Long.MAX_VALUE;
        }
        i -= 1;
      }
    }
    return Long.MAX_VALUE;
  }
  
  boolean isSupported(PeriodType paramPeriodType, int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      return false;
      return paramPeriodType.isSupported(DurationFieldType.years());
      return paramPeriodType.isSupported(DurationFieldType.months());
      return paramPeriodType.isSupported(DurationFieldType.weeks());
      return paramPeriodType.isSupported(DurationFieldType.days());
      return paramPeriodType.isSupported(DurationFieldType.hours());
      return paramPeriodType.isSupported(DurationFieldType.minutes());
      return paramPeriodType.isSupported(DurationFieldType.seconds());
      return paramPeriodType.isSupported(DurationFieldType.millis());
    } while ((!paramPeriodType.isSupported(DurationFieldType.seconds())) && (!paramPeriodType.isSupported(DurationFieldType.millis())));
    return true;
  }
  
  boolean isZero(ReadablePeriod paramReadablePeriod)
  {
    int i = 0;
    int j = paramReadablePeriod.size();
    while (i < j)
    {
      if (paramReadablePeriod.getValue(i) != 0) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
  {
    int k;
    if (iPrintZeroSetting == 4) {
      k = 1;
    }
    while (paramInt >= paramString.length()) {
      if (k != 0)
      {
        return paramInt ^ 0xFFFFFFFF;
        k = 0;
      }
      else
      {
        return paramInt;
      }
    }
    int j = k;
    int i = paramInt;
    int m;
    if (iPrefix != null)
    {
      i = iPrefix.parse(paramString, paramInt);
      if (i >= 0) {
        j = 1;
      }
    }
    else
    {
      m = -1;
      paramInt = j;
      k = m;
      if (iSuffix != null)
      {
        paramInt = j;
        k = m;
        if (j == 0)
        {
          k = iSuffix.scan(paramString, i);
          if (k < 0) {
            break label158;
          }
          paramInt = 1;
        }
      }
      if ((paramInt != 0) || (isSupported(paramReadWritablePeriod.getPeriodType(), iFieldType))) {
        break label171;
      }
      return i;
    }
    if (k == 0) {
      return i ^ 0xFFFFFFFF;
    }
    return i;
    label158:
    if (j == 0) {
      return k ^ 0xFFFFFFFF;
    }
    return k;
    label171:
    int n;
    if (k > 0)
    {
      paramInt = Math.min(iMaxParsedDigits, k - i);
      i1 = 0;
      j = -1;
      n = 0;
      m = i;
      i = i1;
      if (i < paramInt)
      {
        i1 = paramString.charAt(m + i);
        if ((i != 0) || ((i1 != 45) && (i1 != 43)) || (iRejectSignedValues)) {
          break label365;
        }
        if (i1 != 45) {
          break label322;
        }
      }
    }
    label294:
    label322:
    for (int i1 = 1;; i1 = 0)
    {
      if (i + 1 < paramInt)
      {
        int i2 = paramString.charAt(m + i + 1);
        if ((i2 >= 48) && (i2 <= 57)) {
          break label328;
        }
      }
      if (n != 0) {
        break label454;
      }
      return m ^ 0xFFFFFFFF;
      paramInt = Math.min(iMaxParsedDigits, paramString.length() - i);
      break;
    }
    label328:
    if (i1 != 0) {
      i += 1;
    }
    for (;;)
    {
      paramInt = Math.min(paramInt + 1, paramString.length() - m);
      break;
      m += 1;
    }
    label365:
    if ((i1 >= 48) && (i1 <= 57)) {
      n = 1;
    }
    for (;;)
    {
      i += 1;
      break;
      if (((i1 != 46) && (i1 != 44)) || ((iFieldType != 8) && (iFieldType != 9)) || (j >= 0)) {
        break label294;
      }
      j = m + i + 1;
      paramInt = Math.min(paramInt + 1, paramString.length() - m);
    }
    label454:
    if ((k >= 0) && (m + i != k)) {
      return m;
    }
    if ((iFieldType != 8) && (iFieldType != 9)) {
      setFieldValue(paramReadWritablePeriod, iFieldType, parseInt(paramString, m, i));
    }
    for (;;)
    {
      i = m + i;
      paramInt = i;
      if (i >= 0)
      {
        paramInt = i;
        if (iSuffix != null) {
          paramInt = iSuffix.parse(paramString, i);
        }
      }
      return paramInt;
      if (j < 0)
      {
        setFieldValue(paramReadWritablePeriod, 6, parseInt(paramString, m, i));
        setFieldValue(paramReadWritablePeriod, 7, 0);
      }
      else
      {
        k = parseInt(paramString, m, j - m - 1);
        setFieldValue(paramReadWritablePeriod, 6, k);
        paramInt = m + i - j;
        if (paramInt > 0) {
          break;
        }
        j = 0;
        setFieldValue(paramReadWritablePeriod, 7, j);
      }
    }
    if (paramInt >= 3) {
      paramInt = parseInt(paramString, j, 3);
    }
    for (;;)
    {
      j = paramInt;
      if (k >= 0) {
        break;
      }
      j = -paramInt;
      break;
      j = parseInt(paramString, j, paramInt);
      if (paramInt == 1) {
        paramInt = j * 100;
      } else {
        paramInt = j * 10;
      }
    }
  }
  
  public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale)
    throws IOException
  {
    long l = getFieldValue(paramReadablePeriod);
    if (l == Long.MAX_VALUE) {
      return;
    }
    int i = (int)l;
    if (iFieldType >= 8) {
      i = (int)(l / 1000L);
    }
    if (iPrefix != null) {
      iPrefix.printTo(paramWriter, i);
    }
    int j = iMinPrintedDigits;
    if (j <= 1) {
      FormatUtils.writeUnpaddedInteger(paramWriter, i);
    }
    for (;;)
    {
      if (iFieldType >= 8)
      {
        j = (int)(Math.abs(l) % 1000L);
        if ((iFieldType == 8) || (j > 0))
        {
          paramWriter.write(46);
          FormatUtils.writePaddedInteger(paramWriter, j, 3);
        }
      }
      if (iSuffix == null) {
        break;
      }
      iSuffix.printTo(paramWriter, i);
      return;
      FormatUtils.writePaddedInteger(paramWriter, i, j);
    }
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    long l = getFieldValue(paramReadablePeriod);
    if (l == Long.MAX_VALUE) {
      return;
    }
    int i = (int)l;
    if (iFieldType >= 8) {
      i = (int)(l / 1000L);
    }
    if (iPrefix != null) {
      iPrefix.printTo(paramStringBuffer, i);
    }
    int j = paramStringBuffer.length();
    int k = iMinPrintedDigits;
    if (k <= 1) {
      FormatUtils.appendUnpaddedInteger(paramStringBuffer, i);
    }
    for (;;)
    {
      if (iFieldType >= 8)
      {
        k = (int)(Math.abs(l) % 1000L);
        if ((iFieldType == 8) || (k > 0))
        {
          if ((l < 0L) && (l > -1000L)) {
            paramStringBuffer.insert(j, '-');
          }
          paramStringBuffer.append('.');
          FormatUtils.appendPaddedInteger(paramStringBuffer, k, 3);
        }
      }
      if (iSuffix == null) {
        break;
      }
      iSuffix.printTo(paramStringBuffer, i);
      return;
      FormatUtils.appendPaddedInteger(paramStringBuffer, i, k);
    }
  }
  
  void setFieldValue(ReadWritablePeriod paramReadWritablePeriod, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return;
    case 0: 
      paramReadWritablePeriod.setYears(paramInt2);
      return;
    case 1: 
      paramReadWritablePeriod.setMonths(paramInt2);
      return;
    case 2: 
      paramReadWritablePeriod.setWeeks(paramInt2);
      return;
    case 3: 
      paramReadWritablePeriod.setDays(paramInt2);
      return;
    case 4: 
      paramReadWritablePeriod.setHours(paramInt2);
      return;
    case 5: 
      paramReadWritablePeriod.setMinutes(paramInt2);
      return;
    case 6: 
      paramReadWritablePeriod.setSeconds(paramInt2);
      return;
    }
    paramReadWritablePeriod.setMillis(paramInt2);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.FieldFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */