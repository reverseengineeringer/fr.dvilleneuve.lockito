package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.MutableDateTime;
import org.joda.time.MutableDateTime.Property;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

public class DateTimeFormatterBuilder
{
  private ArrayList<Object> iElementPairs = new ArrayList();
  private Object iFormatter;
  
  private DateTimeFormatterBuilder append0(Object paramObject)
  {
    iFormatter = null;
    iElementPairs.add(paramObject);
    iElementPairs.add(paramObject);
    return this;
  }
  
  private DateTimeFormatterBuilder append0(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser)
  {
    iFormatter = null;
    iElementPairs.add(paramDateTimePrinter);
    iElementPairs.add(paramDateTimeParser);
    return this;
  }
  
  static void appendUnknownString(StringBuffer paramStringBuffer, int paramInt)
  {
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      paramStringBuffer.append(65533);
    }
  }
  
  private void checkParser(DateTimeParser paramDateTimeParser)
  {
    if (paramDateTimeParser == null) {
      throw new IllegalArgumentException("No parser supplied");
    }
  }
  
  private void checkPrinter(DateTimePrinter paramDateTimePrinter)
  {
    if (paramDateTimePrinter == null) {
      throw new IllegalArgumentException("No printer supplied");
    }
  }
  
  private Object getFormatter()
  {
    Object localObject2 = iFormatter;
    Object localObject1 = localObject2;
    Object localObject3;
    Object localObject4;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (iElementPairs.size() == 2)
      {
        localObject3 = iElementPairs.get(0);
        localObject4 = iElementPairs.get(1);
        if (localObject3 == null) {
          break label89;
        }
        if (localObject3 != localObject4)
        {
          localObject1 = localObject2;
          if (localObject4 != null) {
            break label62;
          }
        }
      }
    }
    label62:
    label89:
    for (localObject1 = localObject3;; localObject1 = localObject4)
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new Composite(iElementPairs);
      }
      iFormatter = localObject2;
      localObject1 = localObject2;
      return localObject1;
    }
  }
  
  private boolean isFormatter(Object paramObject)
  {
    return (isPrinter(paramObject)) || (isParser(paramObject));
  }
  
  private boolean isParser(Object paramObject)
  {
    if ((paramObject instanceof DateTimeParser))
    {
      if ((paramObject instanceof Composite)) {
        return ((Composite)paramObject).isParser();
      }
      return true;
    }
    return false;
  }
  
  private boolean isPrinter(Object paramObject)
  {
    if ((paramObject instanceof DateTimePrinter))
    {
      if ((paramObject instanceof Composite)) {
        return ((Composite)paramObject).isPrinter();
      }
      return true;
    }
    return false;
  }
  
  static void printUnknownString(Writer paramWriter, int paramInt)
    throws IOException
  {
    for (;;)
    {
      paramInt -= 1;
      if (paramInt < 0) {
        break;
      }
      paramWriter.write(65533);
    }
  }
  
  public DateTimeFormatterBuilder append(DateTimeFormatter paramDateTimeFormatter)
  {
    if (paramDateTimeFormatter == null) {
      throw new IllegalArgumentException("No formatter supplied");
    }
    return append0(paramDateTimeFormatter.getPrinter(), paramDateTimeFormatter.getParser());
  }
  
  public DateTimeFormatterBuilder append(DateTimeParser paramDateTimeParser)
  {
    checkParser(paramDateTimeParser);
    return append0(null, paramDateTimeParser);
  }
  
  public DateTimeFormatterBuilder append(DateTimePrinter paramDateTimePrinter)
  {
    checkPrinter(paramDateTimePrinter);
    return append0(paramDateTimePrinter, null);
  }
  
  public DateTimeFormatterBuilder append(DateTimePrinter paramDateTimePrinter, DateTimeParser paramDateTimeParser)
  {
    checkPrinter(paramDateTimePrinter);
    checkParser(paramDateTimeParser);
    return append0(paramDateTimePrinter, paramDateTimeParser);
  }
  
  public DateTimeFormatterBuilder append(DateTimePrinter paramDateTimePrinter, DateTimeParser[] paramArrayOfDateTimeParser)
  {
    if (paramDateTimePrinter != null) {
      checkPrinter(paramDateTimePrinter);
    }
    if (paramArrayOfDateTimeParser == null) {
      throw new IllegalArgumentException("No parsers supplied");
    }
    int j = paramArrayOfDateTimeParser.length;
    if (j == 1)
    {
      if (paramArrayOfDateTimeParser[0] == null) {
        throw new IllegalArgumentException("No parser supplied");
      }
      return append0(paramDateTimePrinter, paramArrayOfDateTimeParser[0]);
    }
    DateTimeParser[] arrayOfDateTimeParser = new DateTimeParser[j];
    int i = 0;
    while (i < j - 1)
    {
      DateTimeParser localDateTimeParser = paramArrayOfDateTimeParser[i];
      arrayOfDateTimeParser[i] = localDateTimeParser;
      if (localDateTimeParser == null) {
        throw new IllegalArgumentException("Incomplete parser array");
      }
      i += 1;
    }
    arrayOfDateTimeParser[i] = paramArrayOfDateTimeParser[i];
    return append0(paramDateTimePrinter, new MatchingParser(arrayOfDateTimeParser));
  }
  
  public DateTimeFormatterBuilder appendCenturyOfEra(int paramInt1, int paramInt2)
  {
    return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendClockhourOfDay(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.clockhourOfDay(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendClockhourOfHalfday(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendDayOfMonth(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.dayOfMonth(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendDayOfWeek(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.dayOfWeek(), paramInt, 1);
  }
  
  public DateTimeFormatterBuilder appendDayOfWeekShortText()
  {
    return appendShortText(DateTimeFieldType.dayOfWeek());
  }
  
  public DateTimeFormatterBuilder appendDayOfWeekText()
  {
    return appendText(DateTimeFieldType.dayOfWeek());
  }
  
  public DateTimeFormatterBuilder appendDayOfYear(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.dayOfYear(), paramInt, 3);
  }
  
  public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    int i = paramInt2;
    if (paramInt2 < paramInt1) {
      i = paramInt1;
    }
    if ((paramInt1 < 0) || (i <= 0)) {
      throw new IllegalArgumentException();
    }
    if (paramInt1 <= 1) {
      return append0(new UnpaddedNumber(paramDateTimeFieldType, i, false));
    }
    return append0(new PaddedNumber(paramDateTimeFieldType, i, false, paramInt1));
  }
  
  public DateTimeFormatterBuilder appendEraText()
  {
    return appendText(DateTimeFieldType.era());
  }
  
  public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    if (paramInt <= 0) {
      throw new IllegalArgumentException("Illegal number of digits: " + paramInt);
    }
    return append0(new FixedNumber(paramDateTimeFieldType, paramInt, false));
  }
  
  public DateTimeFormatterBuilder appendFixedSignedDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    if (paramInt <= 0) {
      throw new IllegalArgumentException("Illegal number of digits: " + paramInt);
    }
    return append0(new FixedNumber(paramDateTimeFieldType, paramInt, true));
  }
  
  public DateTimeFormatterBuilder appendFraction(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    int i = paramInt2;
    if (paramInt2 < paramInt1) {
      i = paramInt1;
    }
    if ((paramInt1 < 0) || (i <= 0)) {
      throw new IllegalArgumentException();
    }
    return append0(new Fraction(paramDateTimeFieldType, paramInt1, i));
  }
  
  public DateTimeFormatterBuilder appendFractionOfDay(int paramInt1, int paramInt2)
  {
    return appendFraction(DateTimeFieldType.dayOfYear(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendFractionOfHour(int paramInt1, int paramInt2)
  {
    return appendFraction(DateTimeFieldType.hourOfDay(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendFractionOfMinute(int paramInt1, int paramInt2)
  {
    return appendFraction(DateTimeFieldType.minuteOfDay(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendFractionOfSecond(int paramInt1, int paramInt2)
  {
    return appendFraction(DateTimeFieldType.secondOfDay(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendHalfdayOfDayText()
  {
    return appendText(DateTimeFieldType.halfdayOfDay());
  }
  
  public DateTimeFormatterBuilder appendHourOfDay(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.hourOfDay(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendHourOfHalfday(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.hourOfHalfday(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendLiteral(char paramChar)
  {
    return append0(new CharacterLiteral(paramChar));
  }
  
  public DateTimeFormatterBuilder appendLiteral(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Literal must not be null");
    }
    DateTimeFormatterBuilder localDateTimeFormatterBuilder = this;
    switch (paramString.length())
    {
    default: 
      localDateTimeFormatterBuilder = append0(new StringLiteral(paramString));
    case 0: 
      return localDateTimeFormatterBuilder;
    }
    return append0(new CharacterLiteral(paramString.charAt(0)));
  }
  
  public DateTimeFormatterBuilder appendMillisOfDay(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.millisOfDay(), paramInt, 8);
  }
  
  public DateTimeFormatterBuilder appendMillisOfSecond(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.millisOfSecond(), paramInt, 3);
  }
  
  public DateTimeFormatterBuilder appendMinuteOfDay(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.minuteOfDay(), paramInt, 4);
  }
  
  public DateTimeFormatterBuilder appendMinuteOfHour(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.minuteOfHour(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendMonthOfYear(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.monthOfYear(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendMonthOfYearShortText()
  {
    return appendShortText(DateTimeFieldType.monthOfYear());
  }
  
  public DateTimeFormatterBuilder appendMonthOfYearText()
  {
    return appendText(DateTimeFieldType.monthOfYear());
  }
  
  public DateTimeFormatterBuilder appendOptional(DateTimeParser paramDateTimeParser)
  {
    checkParser(paramDateTimeParser);
    return append0(null, new MatchingParser(new DateTimeParser[] { paramDateTimeParser, null }));
  }
  
  public DateTimeFormatterBuilder appendPattern(String paramString)
  {
    DateTimeFormat.appendPatternTo(this, paramString);
    return this;
  }
  
  public DateTimeFormatterBuilder appendSecondOfDay(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.secondOfDay(), paramInt, 5);
  }
  
  public DateTimeFormatterBuilder appendSecondOfMinute(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.secondOfMinute(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendShortText(DateTimeFieldType paramDateTimeFieldType)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    return append0(new TextField(paramDateTimeFieldType, true));
  }
  
  public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    int i = paramInt2;
    if (paramInt2 < paramInt1) {
      i = paramInt1;
    }
    if ((paramInt1 < 0) || (i <= 0)) {
      throw new IllegalArgumentException();
    }
    if (paramInt1 <= 1) {
      return append0(new UnpaddedNumber(paramDateTimeFieldType, i, true));
    }
    return append0(new PaddedNumber(paramDateTimeFieldType, i, true, paramInt1));
  }
  
  public DateTimeFormatterBuilder appendText(DateTimeFieldType paramDateTimeFieldType)
  {
    if (paramDateTimeFieldType == null) {
      throw new IllegalArgumentException("Field type must not be null");
    }
    return append0(new TextField(paramDateTimeFieldType, false));
  }
  
  public DateTimeFormatterBuilder appendTimeZoneId()
  {
    return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
  }
  
  public DateTimeFormatterBuilder appendTimeZoneName()
  {
    return append0(new TimeZoneName(0, null), null);
  }
  
  public DateTimeFormatterBuilder appendTimeZoneName(Map<String, DateTimeZone> paramMap)
  {
    paramMap = new TimeZoneName(0, paramMap);
    return append0(paramMap, paramMap);
  }
  
  public DateTimeFormatterBuilder appendTimeZoneOffset(String paramString1, String paramString2, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    return append0(new TimeZoneOffset(paramString1, paramString2, paramBoolean, paramInt1, paramInt2));
  }
  
  public DateTimeFormatterBuilder appendTimeZoneOffset(String paramString, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    return append0(new TimeZoneOffset(paramString, paramString, paramBoolean, paramInt1, paramInt2));
  }
  
  public DateTimeFormatterBuilder appendTimeZoneShortName()
  {
    return append0(new TimeZoneName(1, null), null);
  }
  
  public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> paramMap)
  {
    paramMap = new TimeZoneName(1, paramMap);
    return append0(paramMap, paramMap);
  }
  
  public DateTimeFormatterBuilder appendTwoDigitWeekyear(int paramInt)
  {
    return appendTwoDigitWeekyear(paramInt, false);
  }
  
  public DateTimeFormatterBuilder appendTwoDigitWeekyear(int paramInt, boolean paramBoolean)
  {
    return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), paramInt, paramBoolean));
  }
  
  public DateTimeFormatterBuilder appendTwoDigitYear(int paramInt)
  {
    return appendTwoDigitYear(paramInt, false);
  }
  
  public DateTimeFormatterBuilder appendTwoDigitYear(int paramInt, boolean paramBoolean)
  {
    return append0(new TwoDigitYear(DateTimeFieldType.year(), paramInt, paramBoolean));
  }
  
  public DateTimeFormatterBuilder appendWeekOfWeekyear(int paramInt)
  {
    return appendDecimal(DateTimeFieldType.weekOfWeekyear(), paramInt, 2);
  }
  
  public DateTimeFormatterBuilder appendWeekyear(int paramInt1, int paramInt2)
  {
    return appendSignedDecimal(DateTimeFieldType.weekyear(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendYear(int paramInt1, int paramInt2)
  {
    return appendSignedDecimal(DateTimeFieldType.year(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendYearOfCentury(int paramInt1, int paramInt2)
  {
    return appendDecimal(DateTimeFieldType.yearOfCentury(), paramInt1, paramInt2);
  }
  
  public DateTimeFormatterBuilder appendYearOfEra(int paramInt1, int paramInt2)
  {
    return appendDecimal(DateTimeFieldType.yearOfEra(), paramInt1, paramInt2);
  }
  
  public boolean canBuildFormatter()
  {
    return isFormatter(getFormatter());
  }
  
  public boolean canBuildParser()
  {
    return isParser(getFormatter());
  }
  
  public boolean canBuildPrinter()
  {
    return isPrinter(getFormatter());
  }
  
  public void clear()
  {
    iFormatter = null;
    iElementPairs.clear();
  }
  
  public DateTimeFormatter toFormatter()
  {
    Object localObject = getFormatter();
    DateTimePrinter localDateTimePrinter = null;
    if (isPrinter(localObject)) {
      localDateTimePrinter = (DateTimePrinter)localObject;
    }
    DateTimeParser localDateTimeParser = null;
    if (isParser(localObject)) {
      localDateTimeParser = (DateTimeParser)localObject;
    }
    if ((localDateTimePrinter != null) || (localDateTimeParser != null)) {
      return new DateTimeFormatter(localDateTimePrinter, localDateTimeParser);
    }
    throw new UnsupportedOperationException("Both printing and parsing not supported");
  }
  
  public DateTimeParser toParser()
  {
    Object localObject = getFormatter();
    if (isParser(localObject)) {
      return (DateTimeParser)localObject;
    }
    throw new UnsupportedOperationException("Parsing is not supported");
  }
  
  public DateTimePrinter toPrinter()
  {
    Object localObject = getFormatter();
    if (isPrinter(localObject)) {
      return (DateTimePrinter)localObject;
    }
    throw new UnsupportedOperationException("Printing is not supported");
  }
  
  static class CharacterLiteral
    implements DateTimePrinter, DateTimeParser
  {
    private final char iValue;
    
    CharacterLiteral(char paramChar)
    {
      iValue = paramChar;
    }
    
    public int estimateParsedLength()
    {
      return 1;
    }
    
    public int estimatePrintedLength()
    {
      return 1;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      if (paramInt >= paramString.length()) {
        return paramInt ^ 0xFFFFFFFF;
      }
      char c2 = paramString.charAt(paramInt);
      char c1 = iValue;
      if (c2 != c1)
      {
        c2 = Character.toUpperCase(c2);
        c1 = Character.toUpperCase(c1);
        if ((c2 != c1) && (Character.toLowerCase(c2) != Character.toLowerCase(c1))) {
          return paramInt ^ 0xFFFFFFFF;
        }
      }
      return paramInt + 1;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      paramWriter.write(iValue);
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      paramWriter.write(iValue);
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      paramStringBuffer.append(iValue);
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      paramStringBuffer.append(iValue);
    }
  }
  
  static class Composite
    implements DateTimePrinter, DateTimeParser
  {
    private final int iParsedLengthEstimate;
    private final DateTimeParser[] iParsers;
    private final int iPrintedLengthEstimate;
    private final DateTimePrinter[] iPrinters;
    
    Composite(List<Object> paramList)
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
  
  static class FixedNumber
    extends DateTimeFormatterBuilder.PaddedNumber
  {
    protected FixedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
    {
      super(paramInt, paramBoolean, paramInt);
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      int k = super.parseInto(paramDateTimeParserBucket, paramString, paramInt);
      if (k < 0) {}
      int i;
      do
      {
        int j;
        do
        {
          return k;
          j = paramInt + iMaxParsedDigits;
        } while (k == j);
        i = j;
        if (iSigned)
        {
          paramInt = paramString.charAt(paramInt);
          if (paramInt != 45)
          {
            i = j;
            if (paramInt != 43) {}
          }
          else
          {
            i = j + 1;
          }
        }
        if (k > i) {
          return i + 1 ^ 0xFFFFFFFF;
        }
      } while (k >= i);
      return k ^ 0xFFFFFFFF;
    }
  }
  
  static class Fraction
    implements DateTimePrinter, DateTimeParser
  {
    private final DateTimeFieldType iFieldType;
    protected int iMaxDigits;
    protected int iMinDigits;
    
    protected Fraction(DateTimeFieldType paramDateTimeFieldType, int paramInt1, int paramInt2)
    {
      iFieldType = paramDateTimeFieldType;
      int i = paramInt2;
      if (paramInt2 > 18) {
        i = 18;
      }
      iMinDigits = paramInt1;
      iMaxDigits = i;
    }
    
    private long[] getFractionData(long paramLong, DateTimeField paramDateTimeField)
    {
      long l2 = paramDateTimeField.getDurationField().getUnitMillis();
      int i = iMaxDigits;
      for (;;)
      {
        long l1;
        switch (i)
        {
        default: 
          l1 = 1L;
        }
        while (l2 * l1 / l1 == l2)
        {
          return new long[] { paramLong * l1 / l2, i };
          l1 = 10L;
          continue;
          l1 = 100L;
          continue;
          l1 = 1000L;
          continue;
          l1 = 10000L;
          continue;
          l1 = 100000L;
          continue;
          l1 = 1000000L;
          continue;
          l1 = 10000000L;
          continue;
          l1 = 100000000L;
          continue;
          l1 = 1000000000L;
          continue;
          l1 = 10000000000L;
          continue;
          l1 = 100000000000L;
          continue;
          l1 = 1000000000000L;
          continue;
          l1 = 10000000000000L;
          continue;
          l1 = 100000000000000L;
          continue;
          l1 = 1000000000000000L;
          continue;
          l1 = 10000000000000000L;
          continue;
          l1 = 100000000000000000L;
          continue;
          l1 = 1000000000000000000L;
        }
        i -= 1;
      }
    }
    
    public int estimateParsedLength()
    {
      return iMaxDigits;
    }
    
    public int estimatePrintedLength()
    {
      return iMaxDigits;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      DateTimeField localDateTimeField = iFieldType.getField(paramDateTimeParserBucket.getChronology());
      int j = Math.min(iMaxDigits, paramString.length() - paramInt);
      long l1 = 0L;
      long l2 = localDateTimeField.getDurationField().getUnitMillis() * 10L;
      int i = 0;
      for (;;)
      {
        int k;
        if (i < j)
        {
          k = paramString.charAt(paramInt + i);
          if ((k >= 48) && (k <= 57)) {}
        }
        else
        {
          l1 /= 10L;
          if (i != 0) {
            break;
          }
          return paramInt ^ 0xFFFFFFFF;
        }
        i += 1;
        l2 /= 10L;
        l1 += (k - 48) * l2;
      }
      if (l1 > 2147483647L) {
        return paramInt ^ 0xFFFFFFFF;
      }
      paramDateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, localDateTimeField.getDurationField()), (int)l1);
      return paramInt + i;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      printTo(null, paramWriter, paramLong, paramChronology);
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      printTo(null, paramWriter, paramReadablePartial.getChronology().set(paramReadablePartial, 0L), paramReadablePartial.getChronology());
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      try
      {
        printTo(paramStringBuffer, null, paramLong, paramChronology);
        return;
      }
      catch (IOException paramStringBuffer) {}
    }
    
    protected void printTo(StringBuffer paramStringBuffer, Writer paramWriter, long paramLong, Chronology paramChronology)
      throws IOException
    {
      paramChronology = iFieldType.getField(paramChronology);
      int i = iMinDigits;
      for (;;)
      {
        try
        {
          paramLong = paramChronology.remainder(paramLong);
          if (paramLong != 0L) {
            break label99;
          }
          j = i;
          if (paramStringBuffer != null)
          {
            i -= 1;
            if (i >= 0)
            {
              paramStringBuffer.append('0');
              continue;
            }
          }
          j -= 1;
        }
        catch (RuntimeException paramChronology)
        {
          if (paramStringBuffer != null)
          {
            DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, i);
            return;
          }
          DateTimeFormatterBuilder.printUnknownString(paramWriter, i);
          return;
        }
        while (j >= 0) {
          paramWriter.write(48);
        }
        continue;
        label99:
        paramChronology = getFractionData(paramLong, paramChronology);
        paramLong = paramChronology[0];
        int j = (int)paramChronology[1];
        int m;
        int k;
        if ((0x7FFFFFFF & paramLong) == paramLong)
        {
          paramChronology = Integer.toString((int)paramLong);
          m = paramChronology.length();
          k = i;
          i = j;
          label152:
          if (m >= i) {
            break label203;
          }
          if (paramStringBuffer == null) {
            break label194;
          }
          paramStringBuffer.append('0');
        }
        for (;;)
        {
          k -= 1;
          i -= 1;
          break label152;
          paramChronology = Long.toString(paramLong);
          break;
          label194:
          paramWriter.write(48);
        }
        label203:
        if (k >= i) {
          break;
        }
        j = m;
        for (;;)
        {
          if ((k >= i) || (j <= 1) || (paramChronology.charAt(j - 1) != '0'))
          {
            if (j >= paramChronology.length()) {
              break label331;
            }
            if (paramStringBuffer == null) {
              break label301;
            }
            i = 0;
            while (i < j)
            {
              paramStringBuffer.append(paramChronology.charAt(i));
              i += 1;
            }
            break;
          }
          i -= 1;
          j -= 1;
        }
        label301:
        i = 0;
        while (i < j)
        {
          paramWriter.write(paramChronology.charAt(i));
          i += 1;
        }
      }
      label331:
      if (paramStringBuffer != null)
      {
        paramStringBuffer.append(paramChronology);
        return;
      }
      paramWriter.write(paramChronology);
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      long l = paramReadablePartial.getChronology().set(paramReadablePartial, 0L);
      try
      {
        printTo(paramStringBuffer, null, l, paramReadablePartial.getChronology());
        return;
      }
      catch (IOException paramStringBuffer) {}
    }
  }
  
  static class MatchingParser
    implements DateTimeParser
  {
    private final int iParsedLengthEstimate;
    private final DateTimeParser[] iParsers;
    
    MatchingParser(DateTimeParser[] paramArrayOfDateTimeParser)
    {
      iParsers = paramArrayOfDateTimeParser;
      int j = 0;
      int i = paramArrayOfDateTimeParser.length;
      for (;;)
      {
        int k = i - 1;
        if (k < 0) {
          break;
        }
        DateTimeParser localDateTimeParser = paramArrayOfDateTimeParser[k];
        i = k;
        if (localDateTimeParser != null)
        {
          int m = localDateTimeParser.estimateParsedLength();
          i = k;
          if (m > j)
          {
            j = m;
            i = k;
          }
        }
      }
      iParsedLengthEstimate = j;
    }
    
    public int estimateParsedLength()
    {
      return iParsedLengthEstimate;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      DateTimeParser[] arrayOfDateTimeParser = iParsers;
      int i3 = arrayOfDateTimeParser.length;
      Object localObject3 = paramDateTimeParserBucket.saveState();
      int i1 = 0;
      int i = paramInt;
      Object localObject1 = null;
      int k = paramInt;
      int j = 0;
      int m = i1;
      Object localObject2;
      if (j < i3)
      {
        localObject2 = arrayOfDateTimeParser[j];
        if (localObject2 == null)
        {
          if (i <= paramInt) {
            return paramInt;
          }
          m = 1;
        }
      }
      else
      {
        if ((i <= paramInt) && ((i != paramInt) || (m == 0))) {
          break label265;
        }
        if (localObject1 != null) {
          paramDateTimeParserBucket.restoreState(localObject1);
        }
        return i;
      }
      int i2 = ((DateTimeParser)localObject2).parseInto(paramDateTimeParserBucket, paramString, paramInt);
      int n;
      if (i2 >= paramInt)
      {
        m = k;
        n = i;
        localObject2 = localObject1;
        if (i2 > i)
        {
          if ((i2 >= paramString.length()) || (j + 1 >= i3) || (arrayOfDateTimeParser[(j + 1)] == null)) {
            return i2;
          }
          n = i2;
          localObject2 = paramDateTimeParserBucket.saveState();
          m = k;
        }
      }
      for (;;)
      {
        paramDateTimeParserBucket.restoreState(localObject3);
        j += 1;
        k = m;
        i = n;
        localObject1 = localObject2;
        break;
        m = k;
        n = i;
        localObject2 = localObject1;
        if (i2 < 0)
        {
          i2 ^= 0xFFFFFFFF;
          m = k;
          n = i;
          localObject2 = localObject1;
          if (i2 > k)
          {
            m = i2;
            n = i;
            localObject2 = localObject1;
          }
        }
      }
      label265:
      return k ^ 0xFFFFFFFF;
    }
  }
  
  static abstract class NumberFormatter
    implements DateTimePrinter, DateTimeParser
  {
    protected final DateTimeFieldType iFieldType;
    protected final int iMaxParsedDigits;
    protected final boolean iSigned;
    
    NumberFormatter(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
    {
      iFieldType = paramDateTimeFieldType;
      iMaxParsedDigits = paramInt;
      iSigned = paramBoolean;
    }
    
    public int estimateParsedLength()
    {
      return iMaxParsedDigits;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      int m = Math.min(iMaxParsedDigits, paramString.length() - paramInt);
      int i = 0;
      int j = 0;
      int k;
      int n;
      for (;;)
      {
        k = i;
        if (j < m)
        {
          n = paramString.charAt(paramInt + j);
          if ((j != 0) || ((n != 45) && (n != 43)) || (!iSigned)) {
            break label176;
          }
          if (n != 45) {
            break label134;
          }
          i = 1;
          label78:
          k = i;
          if (j + 1 < m)
          {
            n = paramString.charAt(paramInt + j + 1);
            k = i;
            if (n >= 48)
            {
              if (n <= 57) {
                break label140;
              }
              k = i;
            }
          }
        }
        label134:
        label140:
        label176:
        do
        {
          do
          {
            if (j != 0) {
              break label207;
            }
            return paramInt ^ 0xFFFFFFFF;
            i = 0;
            break label78;
            if (i != 0) {
              j += 1;
            }
            for (;;)
            {
              m = Math.min(m + 1, paramString.length() - paramInt);
              break;
              paramInt += 1;
            }
            k = i;
          } while (n < 48);
          k = i;
        } while (n > 57);
        j += 1;
      }
      label207:
      if (j >= 9)
      {
        j = paramInt + j;
        i = Integer.parseInt(paramString.substring(paramInt, j));
        paramDateTimeParserBucket.saveField(iFieldType, i);
        return j;
      }
      if (k != 0) {}
      for (i = paramInt + 1;; i = paramInt)
      {
        try
        {
          m = paramString.charAt(i);
          n = m - 48;
          m = paramInt + j;
          i += 1;
          paramInt = n;
          while (i < m)
          {
            paramInt = (paramInt << 3) + (paramInt << 1) + paramString.charAt(i) - 48;
            i += 1;
          }
          i = paramInt;
        }
        catch (StringIndexOutOfBoundsException paramDateTimeParserBucket)
        {
          return paramInt ^ 0xFFFFFFFF;
        }
        j = m;
        if (k == 0) {
          break;
        }
        i = -paramInt;
        j = m;
        break;
      }
    }
  }
  
  static class PaddedNumber
    extends DateTimeFormatterBuilder.NumberFormatter
  {
    protected final int iMinPrintedDigits;
    
    protected PaddedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt1, boolean paramBoolean, int paramInt2)
    {
      super(paramInt1, paramBoolean);
      iMinPrintedDigits = paramInt2;
    }
    
    public int estimatePrintedLength()
    {
      return iMaxParsedDigits;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      try
      {
        FormatUtils.writePaddedInteger(paramWriter, iFieldType.getField(paramChronology).get(paramLong), iMinPrintedDigits);
        return;
      }
      catch (RuntimeException paramChronology)
      {
        DateTimeFormatterBuilder.printUnknownString(paramWriter, iMinPrintedDigits);
      }
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      if (paramReadablePartial.isSupported(iFieldType)) {
        try
        {
          FormatUtils.writePaddedInteger(paramWriter, paramReadablePartial.get(iFieldType), iMinPrintedDigits);
          return;
        }
        catch (RuntimeException paramReadablePartial)
        {
          DateTimeFormatterBuilder.printUnknownString(paramWriter, iMinPrintedDigits);
          return;
        }
      }
      DateTimeFormatterBuilder.printUnknownString(paramWriter, iMinPrintedDigits);
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      try
      {
        FormatUtils.appendPaddedInteger(paramStringBuffer, iFieldType.getField(paramChronology).get(paramLong), iMinPrintedDigits);
        return;
      }
      catch (RuntimeException paramChronology)
      {
        DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, iMinPrintedDigits);
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      if (paramReadablePartial.isSupported(iFieldType)) {
        try
        {
          FormatUtils.appendPaddedInteger(paramStringBuffer, paramReadablePartial.get(iFieldType), iMinPrintedDigits);
          return;
        }
        catch (RuntimeException paramReadablePartial)
        {
          DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, iMinPrintedDigits);
          return;
        }
      }
      DateTimeFormatterBuilder.appendUnknownString(paramStringBuffer, iMinPrintedDigits);
    }
  }
  
  static class StringLiteral
    implements DateTimePrinter, DateTimeParser
  {
    private final String iValue;
    
    StringLiteral(String paramString)
    {
      iValue = paramString;
    }
    
    public int estimateParsedLength()
    {
      return iValue.length();
    }
    
    public int estimatePrintedLength()
    {
      return iValue.length();
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      if (paramString.regionMatches(true, paramInt, iValue, 0, iValue.length())) {
        return iValue.length() + paramInt;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      paramWriter.write(iValue);
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      paramWriter.write(iValue);
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      paramStringBuffer.append(iValue);
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      paramStringBuffer.append(iValue);
    }
  }
  
  static class TextField
    implements DateTimePrinter, DateTimeParser
  {
    private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new HashMap();
    private final DateTimeFieldType iFieldType;
    private final boolean iShort;
    
    TextField(DateTimeFieldType paramDateTimeFieldType, boolean paramBoolean)
    {
      iFieldType = paramDateTimeFieldType;
      iShort = paramBoolean;
    }
    
    private String print(long paramLong, Chronology paramChronology, Locale paramLocale)
    {
      paramChronology = iFieldType.getField(paramChronology);
      if (iShort) {
        return paramChronology.getAsShortText(paramLong, paramLocale);
      }
      return paramChronology.getAsText(paramLong, paramLocale);
    }
    
    private String print(ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      if (paramReadablePartial.isSupported(iFieldType))
      {
        DateTimeField localDateTimeField = iFieldType.getField(paramReadablePartial.getChronology());
        if (iShort) {
          return localDateTimeField.getAsShortText(paramReadablePartial, paramLocale);
        }
        return localDateTimeField.getAsText(paramReadablePartial, paramLocale);
      }
      return "�";
    }
    
    public int estimateParsedLength()
    {
      return estimatePrintedLength();
    }
    
    public int estimatePrintedLength()
    {
      if (iShort) {
        return 6;
      }
      return 20;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      Locale localLocale = paramDateTimeParserBucket.getLocale();
      synchronized (cParseCache)
      {
        Object localObject2 = (Map)cParseCache.get(localLocale);
        Object localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = new HashMap();
          cParseCache.put(localLocale, localObject1);
        }
        localObject2 = (Object[])((Map)localObject1).get(iFieldType);
        if (localObject2 == null) {
          localObject2 = new HashSet(32);
        }
        for (;;)
        {
          try
          {
            MutableDateTime.Property localProperty = new MutableDateTime(0L, DateTimeZone.UTC).property(iFieldType);
            i = localProperty.getMinimumValueOverall();
            int k = localProperty.getMaximumValueOverall();
            if (k - i > 32) {
              return paramInt ^ 0xFFFFFFFF;
            }
            int j = localProperty.getMaximumTextLength(localLocale);
            if (i <= k)
            {
              localProperty.set(i);
              ((Set)localObject2).add(localProperty.getAsShortText(localLocale));
              ((Set)localObject2).add(localProperty.getAsShortText(localLocale).toLowerCase(localLocale));
              ((Set)localObject2).add(localProperty.getAsShortText(localLocale).toUpperCase(localLocale));
              ((Set)localObject2).add(localProperty.getAsText(localLocale));
              ((Set)localObject2).add(localProperty.getAsText(localLocale).toLowerCase(localLocale));
              ((Set)localObject2).add(localProperty.getAsText(localLocale).toUpperCase(localLocale));
              i += 1;
              continue;
            }
            i = j;
            if ("en".equals(localLocale.getLanguage()))
            {
              i = j;
              if (iFieldType == DateTimeFieldType.era())
              {
                ((Set)localObject2).add("BCE");
                ((Set)localObject2).add("bce");
                ((Set)localObject2).add("CE");
                ((Set)localObject2).add("ce");
                i = 3;
              }
            }
            ((Map)localObject1).put(iFieldType, new Object[] { localObject2, Integer.valueOf(i) });
            localObject1 = localObject2;
            i = Math.min(paramString.length(), paramInt + i);
            if (i <= paramInt) {
              continue;
            }
            localObject2 = paramString.substring(paramInt, i);
            if (!((Set)localObject1).contains(localObject2)) {
              continue;
            }
            paramDateTimeParserBucket.saveField(iFieldType, (String)localObject2, localLocale);
            return i;
          }
          finally
          {
            int i;
            continue;
          }
          localObject1 = (Set)localObject2[0];
          i = ((Integer)localObject2[1]).intValue();
          continue;
          throw paramDateTimeParserBucket;
          i -= 1;
        }
        return paramInt ^ 0xFFFFFFFF;
      }
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      try
      {
        paramWriter.write(print(paramLong, paramChronology, paramLocale));
        return;
      }
      catch (RuntimeException paramChronology)
      {
        paramWriter.write(65533);
      }
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      try
      {
        paramWriter.write(print(paramReadablePartial, paramLocale));
        return;
      }
      catch (RuntimeException paramReadablePartial)
      {
        paramWriter.write(65533);
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      try
      {
        paramStringBuffer.append(print(paramLong, paramChronology, paramLocale));
        return;
      }
      catch (RuntimeException paramChronology)
      {
        paramStringBuffer.append(65533);
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      try
      {
        paramStringBuffer.append(print(paramReadablePartial, paramLocale));
        return;
      }
      catch (RuntimeException paramReadablePartial)
      {
        paramStringBuffer.append(65533);
      }
    }
  }
  
  static enum TimeZoneId
    implements DateTimePrinter, DateTimeParser
  {
    static final Set<String> ALL_IDS;
    static final int MAX_LENGTH;
    
    static
    {
      $VALUES = new TimeZoneId[] { INSTANCE };
      ALL_IDS = DateTimeZone.getAvailableIDs();
      int i = 0;
      Iterator localIterator = ALL_IDS.iterator();
      while (localIterator.hasNext()) {
        i = Math.max(i, ((String)localIterator.next()).length());
      }
      MAX_LENGTH = i;
    }
    
    private TimeZoneId() {}
    
    public int estimateParsedLength()
    {
      return MAX_LENGTH;
    }
    
    public int estimatePrintedLength()
    {
      return MAX_LENGTH;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      String str2 = paramString.substring(paramInt);
      paramString = null;
      Iterator localIterator = ALL_IDS.iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        if ((str2.startsWith(str1)) && ((paramString == null) || (str1.length() > paramString.length()))) {
          paramString = str1;
        }
      }
      if (paramString != null)
      {
        paramDateTimeParserBucket.setZone(DateTimeZone.forID(paramString));
        return paramString.length() + paramInt;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      if (paramDateTimeZone != null) {}
      for (paramChronology = paramDateTimeZone.getID();; paramChronology = "")
      {
        paramWriter.write(paramChronology);
        return;
      }
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {}
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      if (paramDateTimeZone != null) {}
      for (paramChronology = paramDateTimeZone.getID();; paramChronology = "")
      {
        paramStringBuffer.append(paramChronology);
        return;
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
  }
  
  static class TimeZoneName
    implements DateTimePrinter, DateTimeParser
  {
    static final int LONG_NAME = 0;
    static final int SHORT_NAME = 1;
    private final Map<String, DateTimeZone> iParseLookup;
    private final int iType;
    
    TimeZoneName(int paramInt, Map<String, DateTimeZone> paramMap)
    {
      iType = paramInt;
      iParseLookup = paramMap;
    }
    
    private String print(long paramLong, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      if (paramDateTimeZone == null) {
        return "";
      }
      switch (iType)
      {
      default: 
        return "";
      case 0: 
        return paramDateTimeZone.getName(paramLong, paramLocale);
      }
      return paramDateTimeZone.getShortName(paramLong, paramLocale);
    }
    
    public int estimateParsedLength()
    {
      if (iType == 1) {
        return 4;
      }
      return 20;
    }
    
    public int estimatePrintedLength()
    {
      if (iType == 1) {
        return 4;
      }
      return 20;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      Map localMap = iParseLookup;
      if (localMap != null) {}
      for (;;)
      {
        String str2 = paramString.substring(paramInt);
        paramString = null;
        Iterator localIterator = localMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if ((str2.startsWith(str1)) && ((paramString == null) || (str1.length() > paramString.length()))) {
            paramString = str1;
          }
        }
        localMap = DateTimeUtils.getDefaultTimeZoneNames();
      }
      if (paramString != null)
      {
        paramDateTimeParserBucket.setZone((DateTimeZone)localMap.get(paramString));
        return paramString.length() + paramInt;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      paramWriter.write(print(paramLong - paramInt, paramDateTimeZone, paramLocale));
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {}
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      paramStringBuffer.append(print(paramLong - paramInt, paramDateTimeZone, paramLocale));
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
  }
  
  static class TimeZoneOffset
    implements DateTimePrinter, DateTimeParser
  {
    private final int iMaxFields;
    private final int iMinFields;
    private final boolean iShowSeparators;
    private final String iZeroOffsetParseText;
    private final String iZeroOffsetPrintText;
    
    TimeZoneOffset(String paramString1, String paramString2, boolean paramBoolean, int paramInt1, int paramInt2)
    {
      iZeroOffsetPrintText = paramString1;
      iZeroOffsetParseText = paramString2;
      iShowSeparators = paramBoolean;
      if ((paramInt1 <= 0) || (paramInt2 < paramInt1)) {
        throw new IllegalArgumentException();
      }
      int i = paramInt1;
      if (paramInt1 > 4)
      {
        i = 4;
        paramInt2 = 4;
      }
      iMinFields = i;
      iMaxFields = paramInt2;
    }
    
    private int digitCount(String paramString, int paramInt1, int paramInt2)
    {
      paramInt2 = Math.min(paramString.length() - paramInt1, paramInt2);
      int i = 0;
      for (;;)
      {
        if (paramInt2 > 0)
        {
          int j = paramString.charAt(paramInt1 + i);
          if ((j >= 48) && (j <= 57)) {}
        }
        else
        {
          return i;
        }
        i += 1;
        paramInt2 -= 1;
      }
    }
    
    public int estimateParsedLength()
    {
      return estimatePrintedLength();
    }
    
    public int estimatePrintedLength()
    {
      int j = iMinFields + 1 << 1;
      int i = j;
      if (iShowSeparators) {
        i = j + (iMinFields - 1);
      }
      j = i;
      if (iZeroOffsetPrintText != null)
      {
        j = i;
        if (iZeroOffsetPrintText.length() > i) {
          j = iZeroOffsetPrintText.length();
        }
      }
      return j;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      int i = paramString.length() - paramInt;
      if (iZeroOffsetParseText != null)
      {
        if (iZeroOffsetParseText.length() != 0) {
          break label71;
        }
        if (i <= 0) {
          break label61;
        }
        j = paramString.charAt(paramInt);
        if ((j != 45) && (j != 43)) {
          break label61;
        }
      }
      while (i <= 1)
      {
        return paramInt ^ 0xFFFFFFFF;
        label61:
        paramDateTimeParserBucket.setOffset(Integer.valueOf(0));
        return paramInt;
        label71:
        if (paramString.regionMatches(true, paramInt, iZeroOffsetParseText, 0, iZeroOffsetParseText.length()))
        {
          paramDateTimeParserBucket.setOffset(Integer.valueOf(0));
          return iZeroOffsetParseText.length() + paramInt;
        }
      }
      int j = paramString.charAt(paramInt);
      if (j == 45) {}
      for (int k = 1;; k = 0)
      {
        paramInt += 1;
        if (digitCount(paramString, paramInt, 2) >= 2) {
          break label163;
        }
        return paramInt ^ 0xFFFFFFFF;
        if (j != 43) {
          break;
        }
      }
      return paramInt ^ 0xFFFFFFFF;
      label163:
      j = FormatUtils.parseTwoDigits(paramString, paramInt);
      if (j > 23) {
        return paramInt ^ 0xFFFFFFFF;
      }
      int i1 = j * 3600000;
      int n = i - 1 - 2;
      j = paramInt + 2;
      if (n <= 0)
      {
        paramInt = j;
        i = i1;
      }
      for (;;)
      {
        j = i;
        if (k != 0) {
          j = -i;
        }
        paramDateTimeParserBucket.setOffset(Integer.valueOf(j));
        return paramInt;
        int m = paramString.charAt(j);
        if (m == 58)
        {
          m = 1;
          n -= 1;
          j += 1;
        }
        for (;;)
        {
          i2 = digitCount(paramString, j, 2);
          if (i2 == 0)
          {
            i = i1;
            paramInt = j;
            if (m == 0) {
              break;
            }
          }
          if (i2 >= 2) {
            break label340;
          }
          return j ^ 0xFFFFFFFF;
          i = i1;
          paramInt = j;
          if (m < 48) {
            break;
          }
          i = i1;
          paramInt = j;
          if (m > 57) {
            break;
          }
          m = 0;
        }
        label340:
        paramInt = FormatUtils.parseTwoDigits(paramString, j);
        if (paramInt > 59) {
          return j ^ 0xFFFFFFFF;
        }
        int i2 = i1 + 60000 * paramInt;
        int i3 = n - 2;
        i1 = j + 2;
        i = i2;
        paramInt = i1;
        if (i3 > 0)
        {
          n = i3;
          j = i1;
          if (m != 0)
          {
            i = i2;
            paramInt = i1;
            if (paramString.charAt(i1) == ':')
            {
              n = i3 - 1;
              j = i1 + 1;
            }
          }
          else
          {
            i1 = digitCount(paramString, j, 2);
            if (i1 == 0)
            {
              i = i2;
              paramInt = j;
              if (m == 0) {}
            }
            else
            {
              if (i1 < 2) {
                return j ^ 0xFFFFFFFF;
              }
              paramInt = FormatUtils.parseTwoDigits(paramString, j);
              if (paramInt > 59) {
                return j ^ 0xFFFFFFFF;
              }
              i1 = i2 + paramInt * 1000;
              i2 = n - 2;
              n = j + 2;
              i = i1;
              paramInt = n;
              if (i2 > 0)
              {
                j = n;
                if (m != 0)
                {
                  if (paramString.charAt(n) != '.')
                  {
                    i = i1;
                    paramInt = n;
                    if (paramString.charAt(n) != ',') {}
                  }
                  else
                  {
                    j = n + 1;
                  }
                }
                else
                {
                  n = digitCount(paramString, j, 3);
                  if (n == 0)
                  {
                    i = i1;
                    paramInt = j;
                    if (m == 0) {}
                  }
                  else
                  {
                    if (n < 1) {
                      return j ^ 0xFFFFFFFF;
                    }
                    paramInt = j + 1;
                    i = i1 + (paramString.charAt(j) - '0') * 100;
                    if (n > 1)
                    {
                      j = paramInt + 1;
                      m = i + (paramString.charAt(paramInt) - '0') * 10;
                      i = m;
                      paramInt = j;
                      if (n > 2)
                      {
                        i = m + (paramString.charAt(j) - '0');
                        paramInt = j + 1;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      if (paramDateTimeZone == null) {
        return;
      }
      if ((paramInt == 0) && (iZeroOffsetPrintText != null))
      {
        paramWriter.write(iZeroOffsetPrintText);
        return;
      }
      if (paramInt >= 0) {
        paramWriter.write(43);
      }
      for (;;)
      {
        int i = paramInt / 3600000;
        FormatUtils.writePaddedInteger(paramWriter, i, 2);
        if (iMaxFields == 1) {
          break;
        }
        paramInt -= 3600000 * i;
        if ((paramInt == 0) && (iMinFields == 1)) {
          break;
        }
        i = paramInt / 60000;
        if (iShowSeparators) {
          paramWriter.write(58);
        }
        FormatUtils.writePaddedInteger(paramWriter, i, 2);
        if (iMaxFields == 2) {
          break;
        }
        paramInt -= 60000 * i;
        if ((paramInt == 0) && (iMinFields == 2)) {
          break;
        }
        i = paramInt / 1000;
        if (iShowSeparators) {
          paramWriter.write(58);
        }
        FormatUtils.writePaddedInteger(paramWriter, i, 2);
        if (iMaxFields == 3) {
          break;
        }
        paramInt -= i * 1000;
        if ((paramInt == 0) && (iMinFields == 3)) {
          break;
        }
        if (iShowSeparators) {
          paramWriter.write(46);
        }
        FormatUtils.writePaddedInteger(paramWriter, paramInt, 3);
        return;
        paramWriter.write(45);
        paramInt = -paramInt;
      }
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {}
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      if (paramDateTimeZone == null) {
        return;
      }
      if ((paramInt == 0) && (iZeroOffsetPrintText != null))
      {
        paramStringBuffer.append(iZeroOffsetPrintText);
        return;
      }
      if (paramInt >= 0) {
        paramStringBuffer.append('+');
      }
      for (;;)
      {
        int i = paramInt / 3600000;
        FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
        if (iMaxFields == 1) {
          break;
        }
        paramInt -= 3600000 * i;
        if ((paramInt == 0) && (iMinFields <= 1)) {
          break;
        }
        i = paramInt / 60000;
        if (iShowSeparators) {
          paramStringBuffer.append(':');
        }
        FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
        if (iMaxFields == 2) {
          break;
        }
        paramInt -= 60000 * i;
        if ((paramInt == 0) && (iMinFields <= 2)) {
          break;
        }
        i = paramInt / 1000;
        if (iShowSeparators) {
          paramStringBuffer.append(':');
        }
        FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
        if (iMaxFields == 3) {
          break;
        }
        paramInt -= i * 1000;
        if ((paramInt == 0) && (iMinFields <= 3)) {
          break;
        }
        if (iShowSeparators) {
          paramStringBuffer.append('.');
        }
        FormatUtils.appendPaddedInteger(paramStringBuffer, paramInt, 3);
        return;
        paramStringBuffer.append('-');
        paramInt = -paramInt;
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale) {}
  }
  
  static class TwoDigitYear
    implements DateTimePrinter, DateTimeParser
  {
    private final boolean iLenientParse;
    private final int iPivot;
    private final DateTimeFieldType iType;
    
    TwoDigitYear(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
    {
      iType = paramDateTimeFieldType;
      iPivot = paramInt;
      iLenientParse = paramBoolean;
    }
    
    private int getTwoDigitYear(long paramLong, Chronology paramChronology)
    {
      try
      {
        int j = iType.getField(paramChronology).get(paramLong);
        int i = j;
        if (j < 0) {
          i = -j;
        }
        return i % 100;
      }
      catch (RuntimeException paramChronology) {}
      return -1;
    }
    
    private int getTwoDigitYear(ReadablePartial paramReadablePartial)
    {
      if (paramReadablePartial.isSupported(iType)) {
        try
        {
          int j = paramReadablePartial.get(iType);
          int i = j;
          if (j < 0) {
            i = -j;
          }
          return i % 100;
        }
        catch (RuntimeException paramReadablePartial) {}
      }
      return -1;
    }
    
    public int estimateParsedLength()
    {
      if (iLenientParse) {
        return 4;
      }
      return 2;
    }
    
    public int estimatePrintedLength()
    {
      return 2;
    }
    
    public int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt)
    {
      int k = paramString.length() - paramInt;
      int i;
      int m;
      int j;
      int n;
      if (!iLenientParse)
      {
        i = paramInt;
        if (Math.min(2, k) < 2) {
          return paramInt ^ 0xFFFFFFFF;
        }
      }
      else
      {
        m = 0;
        j = 0;
        i = 0;
        for (;;)
        {
          if (i < k)
          {
            n = paramString.charAt(paramInt + i);
            if ((i == 0) && ((n == 45) || (n == 43)))
            {
              m = 1;
              if (n == 45) {}
              for (j = 1;; j = 0)
              {
                if (j == 0) {
                  break label110;
                }
                i += 1;
                break;
              }
              label110:
              k -= 1;
              paramInt += 1;
              continue;
            }
            if ((n >= 48) && (n <= 57)) {}
          }
          else
          {
            if (i != 0) {
              break;
            }
            return paramInt ^ 0xFFFFFFFF;
          }
          i += 1;
        }
        if ((m != 0) || (i != 2))
        {
          if (i >= 9)
          {
            k = paramInt + i;
            i = Integer.parseInt(paramString.substring(paramInt, k));
            paramDateTimeParserBucket.saveField(iType, i);
            return k;
          }
          if (j == 0) {
            break label472;
          }
        }
      }
      label466:
      label472:
      for (k = paramInt + 1;; k = paramInt)
      {
        try
        {
          m = paramString.charAt(k);
          n = m - 48;
          m = paramInt + i;
          i = k + 1;
          paramInt = n;
          while (i < m)
          {
            paramInt = (paramInt << 3) + (paramInt << 1) + paramString.charAt(i) - 48;
            i += 1;
          }
          i = paramInt;
        }
        catch (StringIndexOutOfBoundsException paramDateTimeParserBucket)
        {
          return paramInt ^ 0xFFFFFFFF;
        }
        k = m;
        if (j == 0) {
          break;
        }
        i = -paramInt;
        k = m;
        break;
        i = paramInt;
        paramInt = paramString.charAt(i);
        if ((paramInt < 48) || (paramInt > 57)) {
          return i ^ 0xFFFFFFFF;
        }
        paramInt -= 48;
        j = paramString.charAt(i + 1);
        if ((j < 48) || (j > 57)) {
          return i ^ 0xFFFFFFFF;
        }
        k = (paramInt << 3) + (paramInt << 1) + j - 48;
        paramInt = iPivot;
        if (paramDateTimeParserBucket.getPivotYear() != null) {
          paramInt = paramDateTimeParserBucket.getPivotYear().intValue();
        }
        m = paramInt - 50;
        if (m >= 0)
        {
          paramInt = m % 100;
          if (k >= paramInt) {
            break label466;
          }
        }
        for (j = 100;; j = 0)
        {
          paramDateTimeParserBucket.saveField(iType, k + (j + m - paramInt));
          return i + 2;
          paramInt = (m + 1) % 100 + 99;
          break;
        }
      }
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      paramInt = getTwoDigitYear(paramLong, paramChronology);
      if (paramInt < 0)
      {
        paramWriter.write(65533);
        paramWriter.write(65533);
        return;
      }
      FormatUtils.writePaddedInteger(paramWriter, paramInt, 2);
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      int i = getTwoDigitYear(paramReadablePartial);
      if (i < 0)
      {
        paramWriter.write(65533);
        paramWriter.write(65533);
        return;
      }
      FormatUtils.writePaddedInteger(paramWriter, i, 2);
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      paramInt = getTwoDigitYear(paramLong, paramChronology);
      if (paramInt < 0)
      {
        paramStringBuffer.append(65533);
        paramStringBuffer.append(65533);
        return;
      }
      FormatUtils.appendPaddedInteger(paramStringBuffer, paramInt, 2);
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      int i = getTwoDigitYear(paramReadablePartial);
      if (i < 0)
      {
        paramStringBuffer.append(65533);
        paramStringBuffer.append(65533);
        return;
      }
      FormatUtils.appendPaddedInteger(paramStringBuffer, i, 2);
    }
  }
  
  static class UnpaddedNumber
    extends DateTimeFormatterBuilder.NumberFormatter
  {
    protected UnpaddedNumber(DateTimeFieldType paramDateTimeFieldType, int paramInt, boolean paramBoolean)
    {
      super(paramInt, paramBoolean);
    }
    
    public int estimatePrintedLength()
    {
      return iMaxParsedDigits;
    }
    
    public void printTo(Writer paramWriter, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
      throws IOException
    {
      try
      {
        FormatUtils.writeUnpaddedInteger(paramWriter, iFieldType.getField(paramChronology).get(paramLong));
        return;
      }
      catch (RuntimeException paramChronology)
      {
        paramWriter.write(65533);
      }
    }
    
    public void printTo(Writer paramWriter, ReadablePartial paramReadablePartial, Locale paramLocale)
      throws IOException
    {
      if (paramReadablePartial.isSupported(iFieldType)) {
        try
        {
          FormatUtils.writeUnpaddedInteger(paramWriter, paramReadablePartial.get(iFieldType));
          return;
        }
        catch (RuntimeException paramReadablePartial)
        {
          paramWriter.write(65533);
          return;
        }
      }
      paramWriter.write(65533);
    }
    
    public void printTo(StringBuffer paramStringBuffer, long paramLong, Chronology paramChronology, int paramInt, DateTimeZone paramDateTimeZone, Locale paramLocale)
    {
      try
      {
        FormatUtils.appendUnpaddedInteger(paramStringBuffer, iFieldType.getField(paramChronology).get(paramLong));
        return;
      }
      catch (RuntimeException paramChronology)
      {
        paramStringBuffer.append(65533);
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, ReadablePartial paramReadablePartial, Locale paramLocale)
    {
      if (paramReadablePartial.isSupported(iFieldType)) {
        try
        {
          FormatUtils.appendUnpaddedInteger(paramStringBuffer, paramReadablePartial.get(iFieldType));
          return;
        }
        catch (RuntimeException paramReadablePartial)
        {
          paramStringBuffer.append(65533);
          return;
        }
      }
      paramStringBuffer.append(65533);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.DateTimeFormatterBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */