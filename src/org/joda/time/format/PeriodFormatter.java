package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatter
{
  private final Locale iLocale;
  private final PeriodType iParseType;
  private final PeriodParser iParser;
  private final PeriodPrinter iPrinter;
  
  public PeriodFormatter(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser)
  {
    iPrinter = paramPeriodPrinter;
    iParser = paramPeriodParser;
    iLocale = null;
    iParseType = null;
  }
  
  private PeriodFormatter(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser, Locale paramLocale, PeriodType paramPeriodType)
  {
    iPrinter = paramPeriodPrinter;
    iParser = paramPeriodParser;
    iLocale = paramLocale;
    iParseType = paramPeriodType;
  }
  
  private void checkParser()
  {
    if (iParser == null) {
      throw new UnsupportedOperationException("Parsing not supported");
    }
  }
  
  private void checkPeriod(ReadablePeriod paramReadablePeriod)
  {
    if (paramReadablePeriod == null) {
      throw new IllegalArgumentException("Period must not be null");
    }
  }
  
  private void checkPrinter()
  {
    if (iPrinter == null) {
      throw new UnsupportedOperationException("Printing not supported");
    }
  }
  
  public Locale getLocale()
  {
    return iLocale;
  }
  
  public PeriodType getParseType()
  {
    return iParseType;
  }
  
  public PeriodParser getParser()
  {
    return iParser;
  }
  
  public PeriodPrinter getPrinter()
  {
    return iPrinter;
  }
  
  public boolean isParser()
  {
    return iParser != null;
  }
  
  public boolean isPrinter()
  {
    return iPrinter != null;
  }
  
  public int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt)
  {
    checkParser();
    checkPeriod(paramReadWritablePeriod);
    return getParser().parseInto(paramReadWritablePeriod, paramString, paramInt, iLocale);
  }
  
  public MutablePeriod parseMutablePeriod(String paramString)
  {
    checkParser();
    MutablePeriod localMutablePeriod = new MutablePeriod(0L, iParseType);
    int j = getParser().parseInto(localMutablePeriod, paramString, 0, iLocale);
    int i;
    if (j >= 0)
    {
      i = j;
      if (j >= paramString.length()) {
        return localMutablePeriod;
      }
    }
    else
    {
      i = j ^ 0xFFFFFFFF;
    }
    throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
  }
  
  public Period parsePeriod(String paramString)
  {
    checkParser();
    return parseMutablePeriod(paramString).toPeriod();
  }
  
  public String print(ReadablePeriod paramReadablePeriod)
  {
    checkPrinter();
    checkPeriod(paramReadablePeriod);
    PeriodPrinter localPeriodPrinter = getPrinter();
    StringBuffer localStringBuffer = new StringBuffer(localPeriodPrinter.calculatePrintedLength(paramReadablePeriod, iLocale));
    localPeriodPrinter.printTo(localStringBuffer, paramReadablePeriod, iLocale);
    return localStringBuffer.toString();
  }
  
  public void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod)
    throws IOException
  {
    checkPrinter();
    checkPeriod(paramReadablePeriod);
    getPrinter().printTo(paramWriter, paramReadablePeriod, iLocale);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod)
  {
    checkPrinter();
    checkPeriod(paramReadablePeriod);
    getPrinter().printTo(paramStringBuffer, paramReadablePeriod, iLocale);
  }
  
  public PeriodFormatter withLocale(Locale paramLocale)
  {
    if ((paramLocale == getLocale()) || ((paramLocale != null) && (paramLocale.equals(getLocale())))) {
      return this;
    }
    return new PeriodFormatter(iPrinter, iParser, paramLocale, iParseType);
  }
  
  public PeriodFormatter withParseType(PeriodType paramPeriodType)
  {
    if (paramPeriodType == iParseType) {
      return this;
    }
    return new PeriodFormatter(iPrinter, iParser, iLocale, paramPeriodType);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */