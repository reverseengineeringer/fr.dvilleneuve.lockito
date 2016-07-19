package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableInstant;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

public class DateTimeFormatter
{
  private final Chronology iChrono;
  private final int iDefaultYear;
  private final Locale iLocale;
  private final boolean iOffsetParsed;
  private final DateTimeParser iParser;
  private final Integer iPivotYear;
  private final DateTimePrinter iPrinter;
  private final DateTimeZone iZone;
  
  public DateTimeFormatter(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser)
  {
    iPrinter = paramDateTimePrinter;
    iParser = paramDateTimeParser;
    iLocale = null;
    iOffsetParsed = false;
    iChrono = null;
    iZone = null;
    iPivotYear = null;
    iDefaultYear = 2000;
  }
  
  private DateTimeFormatter(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser, Locale paramLocale, boolean paramBoolean, Chronology paramChronology, DateTimeZone paramDateTimeZone, Integer paramInteger, int paramInt)
  {
    iPrinter = paramDateTimePrinter;
    iParser = paramDateTimeParser;
    iLocale = paramLocale;
    iOffsetParsed = paramBoolean;
    iChrono = paramChronology;
    iZone = paramDateTimeZone;
    iPivotYear = paramInteger;
    iDefaultYear = paramInt;
  }
  
  private void printTo(Writer paramWriter, long paramLong, Chronology paramChronology)
    throws IOException
  {
    DateTimePrinter localDateTimePrinter = requirePrinter();
    Chronology localChronology = selectChronology(paramChronology);
    DateTimeZone localDateTimeZone = localChronology.getZone();
    int j = localDateTimeZone.getOffset(paramLong);
    long l2 = paramLong + j;
    long l1 = l2;
    int i = j;
    paramChronology = localDateTimeZone;
    if ((paramLong ^ l2) < 0L)
    {
      l1 = l2;
      i = j;
      paramChronology = localDateTimeZone;
      if ((j ^ paramLong) >= 0L)
      {
        paramChronology = DateTimeZone.UTC;
        i = 0;
        l1 = paramLong;
      }
    }
    localDateTimePrinter.printTo(paramWriter, l1, localChronology.withUTC(), i, paramChronology, iLocale);
  }
  
  private void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology)
  {
    DateTimePrinter localDateTimePrinter = requirePrinter();
    Chronology localChronology = selectChronology(paramChronology);
    DateTimeZone localDateTimeZone = localChronology.getZone();
    int j = localDateTimeZone.getOffset(paramLong);
    long l2 = paramLong + j;
    long l1 = l2;
    int i = j;
    paramChronology = localDateTimeZone;
    if ((paramLong ^ l2) < 0L)
    {
      l1 = l2;
      i = j;
      paramChronology = localDateTimeZone;
      if ((j ^ paramLong) >= 0L)
      {
        paramChronology = DateTimeZone.UTC;
        i = 0;
        l1 = paramLong;
      }
    }
    localDateTimePrinter.printTo(paramStringBuffer, l1, localChronology.withUTC(), i, paramChronology, iLocale);
  }
  
  private DateTimeParser requireParser()
  {
    DateTimeParser localDateTimeParser = iParser;
    if (localDateTimeParser == null) {
      throw new UnsupportedOperationException("Parsing not supported");
    }
    return localDateTimeParser;
  }
  
  private DateTimePrinter requirePrinter()
  {
    DateTimePrinter localDateTimePrinter = iPrinter;
    if (localDateTimePrinter == null) {
      throw new UnsupportedOperationException("Printing not supported");
    }
    return localDateTimePrinter;
  }
  
  private Chronology selectChronology(Chronology paramChronology)
  {
    paramChronology = DateTimeUtils.getChronology(paramChronology);
    if (iChrono != null) {
      paramChronology = iChrono;
    }
    Chronology localChronology = paramChronology;
    if (iZone != null) {
      localChronology = paramChronology.withZone(iZone);
    }
    return localChronology;
  }
  
  @Deprecated
  public Chronology getChronolgy()
  {
    return iChrono;
  }
  
  public Chronology getChronology()
  {
    return iChrono;
  }
  
  public int getDefaultYear()
  {
    return iDefaultYear;
  }
  
  public Locale getLocale()
  {
    return iLocale;
  }
  
  public DateTimeParser getParser()
  {
    return iParser;
  }
  
  public Integer getPivotYear()
  {
    return iPivotYear;
  }
  
  public DateTimePrinter getPrinter()
  {
    return iPrinter;
  }
  
  public DateTimeZone getZone()
  {
    return iZone;
  }
  
  public boolean isOffsetParsed()
  {
    return iOffsetParsed;
  }
  
  public boolean isParser()
  {
    return iParser != null;
  }
  
  public boolean isPrinter()
  {
    return iPrinter != null;
  }
  
  public DateTime parseDateTime(String paramString)
  {
    DateTimeParser localDateTimeParser = requireParser();
    Object localObject = selectChronology(null);
    DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, (Chronology)localObject, iLocale, iPivotYear, iDefaultYear);
    int j = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
    int i;
    if (j >= 0)
    {
      i = j;
      if (j >= paramString.length())
      {
        long l = localDateTimeParserBucket.computeMillis(true, paramString);
        if ((iOffsetParsed) && (localDateTimeParserBucket.getOffsetInteger() != null)) {
          paramString = ((Chronology)localObject).withZone(DateTimeZone.forOffsetMillis(localDateTimeParserBucket.getOffsetInteger().intValue()));
        }
        for (;;)
        {
          localObject = new DateTime(l, paramString);
          paramString = (String)localObject;
          if (iZone != null) {
            paramString = ((DateTime)localObject).withZone(iZone);
          }
          return paramString;
          paramString = (String)localObject;
          if (localDateTimeParserBucket.getZone() != null) {
            paramString = ((Chronology)localObject).withZone(localDateTimeParserBucket.getZone());
          }
        }
      }
    }
    else
    {
      i = j ^ 0xFFFFFFFF;
    }
    throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
  }
  
  public int parseInto(ReadWritableInstant paramReadWritableInstant, String paramString, int paramInt)
  {
    DateTimeParser localDateTimeParser = requireParser();
    if (paramReadWritableInstant == null) {
      throw new IllegalArgumentException("Instant must not be null");
    }
    long l1 = paramReadWritableInstant.getMillis();
    Chronology localChronology = paramReadWritableInstant.getChronology();
    int i = DateTimeUtils.getChronology(localChronology).year().get(l1);
    long l2 = localChronology.getZone().getOffset(l1);
    localChronology = selectChronology(localChronology);
    DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(l1 + l2, localChronology, iLocale, iPivotYear, i);
    paramInt = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, paramInt);
    paramReadWritableInstant.setMillis(localDateTimeParserBucket.computeMillis(false, paramString));
    if ((iOffsetParsed) && (localDateTimeParserBucket.getOffsetInteger() != null)) {
      paramString = localChronology.withZone(DateTimeZone.forOffsetMillis(localDateTimeParserBucket.getOffsetInteger().intValue()));
    }
    for (;;)
    {
      paramReadWritableInstant.setChronology(paramString);
      if (iZone != null) {
        paramReadWritableInstant.setZone(iZone);
      }
      return paramInt;
      paramString = localChronology;
      if (localDateTimeParserBucket.getZone() != null) {
        paramString = localChronology.withZone(localDateTimeParserBucket.getZone());
      }
    }
  }
  
  public LocalDate parseLocalDate(String paramString)
  {
    return parseLocalDateTime(paramString).toLocalDate();
  }
  
  public LocalDateTime parseLocalDateTime(String paramString)
  {
    DateTimeParser localDateTimeParser = requireParser();
    Chronology localChronology = selectChronology(null).withUTC();
    DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, localChronology, iLocale, iPivotYear, iDefaultYear);
    int j = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
    int i;
    if (j >= 0)
    {
      i = j;
      if (j >= paramString.length())
      {
        long l = localDateTimeParserBucket.computeMillis(true, paramString);
        if (localDateTimeParserBucket.getOffsetInteger() != null) {
          paramString = localChronology.withZone(DateTimeZone.forOffsetMillis(localDateTimeParserBucket.getOffsetInteger().intValue()));
        }
        for (;;)
        {
          return new LocalDateTime(l, paramString);
          paramString = localChronology;
          if (localDateTimeParserBucket.getZone() != null) {
            paramString = localChronology.withZone(localDateTimeParserBucket.getZone());
          }
        }
      }
    }
    else
    {
      i = j ^ 0xFFFFFFFF;
    }
    throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
  }
  
  public LocalTime parseLocalTime(String paramString)
  {
    return parseLocalDateTime(paramString).toLocalTime();
  }
  
  public long parseMillis(String paramString)
  {
    DateTimeParser localDateTimeParser = requireParser();
    DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, selectChronology(iChrono), iLocale, iPivotYear, iDefaultYear);
    int j = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
    int i;
    if (j >= 0)
    {
      i = j;
      if (j >= paramString.length()) {
        return localDateTimeParserBucket.computeMillis(true, paramString);
      }
    }
    else
    {
      i = j ^ 0xFFFFFFFF;
    }
    throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
  }
  
  public MutableDateTime parseMutableDateTime(String paramString)
  {
    DateTimeParser localDateTimeParser = requireParser();
    Chronology localChronology = selectChronology(null);
    DateTimeParserBucket localDateTimeParserBucket = new DateTimeParserBucket(0L, localChronology, iLocale, iPivotYear, iDefaultYear);
    int j = localDateTimeParser.parseInto(localDateTimeParserBucket, paramString, 0);
    int i;
    if (j >= 0)
    {
      i = j;
      if (j >= paramString.length())
      {
        long l = localDateTimeParserBucket.computeMillis(true, paramString);
        if ((iOffsetParsed) && (localDateTimeParserBucket.getOffsetInteger() != null)) {
          paramString = localChronology.withZone(DateTimeZone.forOffsetMillis(localDateTimeParserBucket.getOffsetInteger().intValue()));
        }
        for (;;)
        {
          paramString = new MutableDateTime(l, paramString);
          if (iZone != null) {
            paramString.setZone(iZone);
          }
          return paramString;
          paramString = localChronology;
          if (localDateTimeParserBucket.getZone() != null) {
            paramString = localChronology.withZone(localDateTimeParserBucket.getZone());
          }
        }
      }
    }
    else
    {
      i = j ^ 0xFFFFFFFF;
    }
    throw new IllegalArgumentException(FormatUtils.createErrorMessage(paramString, i));
  }
  
  public String print(long paramLong)
  {
    StringBuffer localStringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
    printTo(localStringBuffer, paramLong);
    return localStringBuffer.toString();
  }
  
  public String print(ReadableInstant paramReadableInstant)
  {
    StringBuffer localStringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
    printTo(localStringBuffer, paramReadableInstant);
    return localStringBuffer.toString();
  }
  
  public String print(ReadablePartial paramReadablePartial)
  {
    StringBuffer localStringBuffer = new StringBuffer(requirePrinter().estimatePrintedLength());
    printTo(localStringBuffer, paramReadablePartial);
    return localStringBuffer.toString();
  }
  
  public void printTo(Writer paramWriter, long paramLong)
    throws IOException
  {
    printTo(paramWriter, paramLong, null);
  }
  
  public void printTo(Writer paramWriter, ReadableInstant paramReadableInstant)
    throws IOException
  {
    printTo(paramWriter, DateTimeUtils.getInstantMillis(paramReadableInstant), DateTimeUtils.getInstantChronology(paramReadableInstant));
  }
  
  public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial)
    throws IOException
  {
    DateTimePrinter localDateTimePrinter = requirePrinter();
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("The partial must not be null");
    }
    localDateTimePrinter.printTo(paramWriter, paramReadablePartial, iLocale);
  }
  
  public void printTo(Appendable paramAppendable, long paramLong)
    throws IOException
  {
    paramAppendable.append(print(paramLong));
  }
  
  public void printTo(Appendable paramAppendable, ReadableInstant paramReadableInstant)
    throws IOException
  {
    paramAppendable.append(print(paramReadableInstant));
  }
  
  public void printTo(Appendable paramAppendable, ReadablePartial paramReadablePartial)
    throws IOException
  {
    paramAppendable.append(print(paramReadablePartial));
  }
  
  public void printTo(StringBuffer paramStringBuffer, long paramLong)
  {
    printTo(paramStringBuffer, paramLong, null);
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadableInstant paramReadableInstant)
  {
    printTo(paramStringBuffer, DateTimeUtils.getInstantMillis(paramReadableInstant), DateTimeUtils.getInstantChronology(paramReadableInstant));
  }
  
  public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial)
  {
    DateTimePrinter localDateTimePrinter = requirePrinter();
    if (paramReadablePartial == null) {
      throw new IllegalArgumentException("The partial must not be null");
    }
    localDateTimePrinter.printTo(paramStringBuffer, paramReadablePartial, iLocale);
  }
  
  public DateTimeFormatter withChronology(Chronology paramChronology)
  {
    if (iChrono == paramChronology) {
      return this;
    }
    return new DateTimeFormatter(iPrinter, iParser, iLocale, iOffsetParsed, paramChronology, iZone, iPivotYear, iDefaultYear);
  }
  
  public DateTimeFormatter withDefaultYear(int paramInt)
  {
    return new DateTimeFormatter(iPrinter, iParser, iLocale, iOffsetParsed, iChrono, iZone, iPivotYear, paramInt);
  }
  
  public DateTimeFormatter withLocale(Locale paramLocale)
  {
    if ((paramLocale == getLocale()) || ((paramLocale != null) && (paramLocale.equals(getLocale())))) {
      return this;
    }
    return new DateTimeFormatter(iPrinter, iParser, paramLocale, iOffsetParsed, iChrono, iZone, iPivotYear, iDefaultYear);
  }
  
  public DateTimeFormatter withOffsetParsed()
  {
    if (iOffsetParsed == true) {
      return this;
    }
    return new DateTimeFormatter(iPrinter, iParser, iLocale, true, iChrono, null, iPivotYear, iDefaultYear);
  }
  
  public DateTimeFormatter withPivotYear(int paramInt)
  {
    return withPivotYear(Integer.valueOf(paramInt));
  }
  
  public DateTimeFormatter withPivotYear(Integer paramInteger)
  {
    if ((iPivotYear == paramInteger) || ((iPivotYear != null) && (iPivotYear.equals(paramInteger)))) {
      return this;
    }
    return new DateTimeFormatter(iPrinter, iParser, iLocale, iOffsetParsed, iChrono, iZone, paramInteger, iDefaultYear);
  }
  
  public DateTimeFormatter withZone(DateTimeZone paramDateTimeZone)
  {
    if (iZone == paramDateTimeZone) {
      return this;
    }
    return new DateTimeFormatter(iPrinter, iParser, iLocale, false, iChrono, paramDateTimeZone, iPivotYear, iDefaultYear);
  }
  
  public DateTimeFormatter withZoneUTC()
  {
    return withZone(DateTimeZone.UTC);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */