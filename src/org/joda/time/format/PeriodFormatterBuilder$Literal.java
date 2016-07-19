package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

class PeriodFormatterBuilder$Literal
  implements PeriodPrinter, PeriodParser
{
  static final Literal EMPTY = new Literal("");
  private final String iText;
  
  PeriodFormatterBuilder$Literal(String paramString)
  {
    iText = paramString;
  }
  
  public int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    return iText.length();
  }
  
  public int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale)
  {
    return 0;
  }
  
  public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale)
  {
    if (paramString.regionMatches(true, paramInt, iText, 0, iText.length())) {
      return iText.length() + paramInt;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale)
    throws IOException
  {
    paramWriter.write(iText);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale)
  {
    paramStringBuffer.append(iText);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder.Literal
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */