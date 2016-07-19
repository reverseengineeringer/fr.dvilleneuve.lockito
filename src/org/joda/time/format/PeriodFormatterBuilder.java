package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatterBuilder
{
  private static final int DAYS = 3;
  private static final int HOURS = 4;
  private static final int MAX_FIELD = 9;
  private static final int MILLIS = 7;
  private static final int MINUTES = 5;
  private static final int MONTHS = 1;
  private static final int PRINT_ZERO_ALWAYS = 4;
  private static final int PRINT_ZERO_IF_SUPPORTED = 3;
  private static final int PRINT_ZERO_NEVER = 5;
  private static final int PRINT_ZERO_RARELY_FIRST = 1;
  private static final int PRINT_ZERO_RARELY_LAST = 2;
  private static final int SECONDS = 6;
  private static final int SECONDS_MILLIS = 8;
  private static final int SECONDS_OPTIONAL_MILLIS = 9;
  private static final int WEEKS = 2;
  private static final int YEARS = 0;
  private List<Object> iElementPairs;
  private FieldFormatter[] iFieldFormatters;
  private int iMaxParsedDigits;
  private int iMinPrintedDigits;
  private boolean iNotParser;
  private boolean iNotPrinter;
  private PeriodFieldAffix iPrefix;
  private int iPrintZeroSetting;
  private boolean iRejectSignedValues;
  
  public PeriodFormatterBuilder()
  {
    clear();
  }
  
  private PeriodFormatterBuilder append0(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser)
  {
    boolean bool2 = true;
    iElementPairs.add(paramPeriodPrinter);
    iElementPairs.add(paramPeriodParser);
    boolean bool3 = iNotPrinter;
    if (paramPeriodPrinter == null)
    {
      bool1 = true;
      iNotPrinter = (bool1 | bool3);
      bool3 = iNotParser;
      if (paramPeriodParser != null) {
        break label73;
      }
    }
    label73:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      iNotParser = (bool3 | bool1);
      return this;
      bool1 = false;
      break;
    }
  }
  
  private void appendField(int paramInt)
  {
    appendField(paramInt, iMinPrintedDigits);
  }
  
  private void appendField(int paramInt1, int paramInt2)
  {
    FieldFormatter localFieldFormatter = new FieldFormatter(paramInt2, iPrintZeroSetting, iMaxParsedDigits, iRejectSignedValues, paramInt1, iFieldFormatters, iPrefix, null);
    append0(localFieldFormatter, localFieldFormatter);
    iFieldFormatters[paramInt1] = localFieldFormatter;
    iPrefix = null;
  }
  
  private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix paramPeriodFieldAffix)
  {
    if (paramPeriodFieldAffix == null) {
      throw new IllegalArgumentException();
    }
    Object localObject = paramPeriodFieldAffix;
    if (iPrefix != null) {
      localObject = new CompositeAffix(iPrefix, paramPeriodFieldAffix);
    }
    iPrefix = ((PeriodFieldAffix)localObject);
    return this;
  }
  
  private PeriodFormatterBuilder appendSeparator(String paramString1, String paramString2, String[] paramArrayOfString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException();
    }
    clearPrefix();
    List localList2 = iElementPairs;
    if (localList2.size() == 0)
    {
      if ((paramBoolean2) && (!paramBoolean1))
      {
        paramString1 = new Separator(paramString1, paramString2, paramArrayOfString, Literal.EMPTY, Literal.EMPTY, paramBoolean1, paramBoolean2);
        append0(paramString1, paramString1);
      }
      return this;
    }
    Object localObject2 = null;
    int i = localList2.size();
    List localList1;
    for (;;)
    {
      i -= 1;
      localObject1 = localObject2;
      localList1 = localList2;
      if (i >= 0)
      {
        if ((localList2.get(i) instanceof Separator))
        {
          localObject1 = (Separator)localList2.get(i);
          localList1 = localList2.subList(i + 1, localList2.size());
        }
      }
      else
      {
        if ((localObject1 == null) || (localList1.size() != 0)) {
          break;
        }
        throw new IllegalStateException("Cannot have two adjacent separators");
      }
      i -= 1;
    }
    Object localObject1 = createComposite(localList1);
    localList1.clear();
    paramString1 = new Separator(paramString1, paramString2, paramArrayOfString, (PeriodPrinter)localObject1[0], (PeriodParser)localObject1[1], paramBoolean1, paramBoolean2);
    localList1.add(paramString1);
    localList1.add(paramString1);
    return this;
  }
  
  private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix paramPeriodFieldAffix)
  {
    Object localObject2;
    if (iElementPairs.size() > 0) {
      localObject2 = iElementPairs.get(iElementPairs.size() - 2);
    }
    for (Object localObject1 = iElementPairs.get(iElementPairs.size() - 1); (localObject2 == null) || (localObject1 == null) || (localObject2 != localObject1) || (!(localObject2 instanceof FieldFormatter)); localObject1 = null)
    {
      throw new IllegalStateException("No field to apply suffix to");
      localObject2 = null;
    }
    clearPrefix();
    paramPeriodFieldAffix = new FieldFormatter((FieldFormatter)localObject2, paramPeriodFieldAffix);
    iElementPairs.set(iElementPairs.size() - 2, paramPeriodFieldAffix);
    iElementPairs.set(iElementPairs.size() - 1, paramPeriodFieldAffix);
    iFieldFormatters[paramPeriodFieldAffix.getFieldType()] = paramPeriodFieldAffix;
    return this;
  }
  
  private void clearPrefix()
    throws IllegalStateException
  {
    if (iPrefix != null) {
      throw new IllegalStateException("Prefix not followed by field");
    }
    iPrefix = null;
  }
  
  private static Object[] createComposite(List<Object> paramList)
  {
    switch (paramList.size())
    {
    default: 
      paramList = new Composite(paramList);
      return new Object[] { paramList, paramList };
    case 0: 
      return new Object[] { Literal.EMPTY, Literal.EMPTY };
    }
    return new Object[] { paramList.get(0), paramList.get(1) };
  }
  
  private static PeriodFormatter toFormatter(List<Object> paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) && (paramBoolean2)) {
      throw new IllegalStateException("Builder has created neither a printer nor a parser");
    }
    int i = paramList.size();
    if ((i >= 2) && ((paramList.get(0) instanceof Separator)))
    {
      Separator localSeparator = (Separator)paramList.get(0);
      if ((iAfterParser == null) && (iAfterPrinter == null))
      {
        paramList = toFormatter(paramList.subList(2, i), paramBoolean1, paramBoolean2);
        paramList = localSeparator.finish(paramList.getPrinter(), paramList.getParser());
        return new PeriodFormatter(paramList, paramList);
      }
    }
    paramList = createComposite(paramList);
    if (paramBoolean1) {
      return new PeriodFormatter(null, (PeriodParser)paramList[1]);
    }
    if (paramBoolean2) {
      return new PeriodFormatter((PeriodPrinter)paramList[0], null);
    }
    return new PeriodFormatter((PeriodPrinter)paramList[0], (PeriodParser)paramList[1]);
  }
  
  public PeriodFormatterBuilder append(PeriodFormatter paramPeriodFormatter)
  {
    if (paramPeriodFormatter == null) {
      throw new IllegalArgumentException("No formatter supplied");
    }
    clearPrefix();
    append0(paramPeriodFormatter.getPrinter(), paramPeriodFormatter.getParser());
    return this;
  }
  
  public PeriodFormatterBuilder append(PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser)
  {
    if ((paramPeriodPrinter == null) && (paramPeriodParser == null)) {
      throw new IllegalArgumentException("No printer or parser supplied");
    }
    clearPrefix();
    append0(paramPeriodPrinter, paramPeriodParser);
    return this;
  }
  
  public PeriodFormatterBuilder appendDays()
  {
    appendField(3);
    return this;
  }
  
  public PeriodFormatterBuilder appendHours()
  {
    appendField(4);
    return this;
  }
  
  public PeriodFormatterBuilder appendLiteral(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Literal must not be null");
    }
    clearPrefix();
    paramString = new Literal(paramString);
    append0(paramString, paramString);
    return this;
  }
  
  public PeriodFormatterBuilder appendMillis()
  {
    appendField(7);
    return this;
  }
  
  public PeriodFormatterBuilder appendMillis3Digit()
  {
    appendField(7, 3);
    return this;
  }
  
  public PeriodFormatterBuilder appendMinutes()
  {
    appendField(5);
    return this;
  }
  
  public PeriodFormatterBuilder appendMonths()
  {
    appendField(1);
    return this;
  }
  
  public PeriodFormatterBuilder appendPrefix(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    return appendPrefix(new SimpleAffix(paramString));
  }
  
  public PeriodFormatterBuilder appendPrefix(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException();
    }
    return appendPrefix(new PluralAffix(paramString1, paramString2));
  }
  
  public PeriodFormatterBuilder appendSeconds()
  {
    appendField(6);
    return this;
  }
  
  public PeriodFormatterBuilder appendSecondsWithMillis()
  {
    appendField(8);
    return this;
  }
  
  public PeriodFormatterBuilder appendSecondsWithOptionalMillis()
  {
    appendField(9);
    return this;
  }
  
  public PeriodFormatterBuilder appendSeparator(String paramString)
  {
    return appendSeparator(paramString, paramString, null, true, true);
  }
  
  public PeriodFormatterBuilder appendSeparator(String paramString1, String paramString2)
  {
    return appendSeparator(paramString1, paramString2, null, true, true);
  }
  
  public PeriodFormatterBuilder appendSeparator(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    return appendSeparator(paramString1, paramString2, paramArrayOfString, true, true);
  }
  
  public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String paramString)
  {
    return appendSeparator(paramString, paramString, null, false, true);
  }
  
  public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String paramString)
  {
    return appendSeparator(paramString, paramString, null, true, false);
  }
  
  public PeriodFormatterBuilder appendSuffix(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    return appendSuffix(new SimpleAffix(paramString));
  }
  
  public PeriodFormatterBuilder appendSuffix(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException();
    }
    return appendSuffix(new PluralAffix(paramString1, paramString2));
  }
  
  public PeriodFormatterBuilder appendWeeks()
  {
    appendField(2);
    return this;
  }
  
  public PeriodFormatterBuilder appendYears()
  {
    appendField(0);
    return this;
  }
  
  public void clear()
  {
    iMinPrintedDigits = 1;
    iPrintZeroSetting = 2;
    iMaxParsedDigits = 10;
    iRejectSignedValues = false;
    iPrefix = null;
    if (iElementPairs == null) {
      iElementPairs = new ArrayList();
    }
    for (;;)
    {
      iNotPrinter = false;
      iNotParser = false;
      iFieldFormatters = new FieldFormatter[10];
      return;
      iElementPairs.clear();
    }
  }
  
  public PeriodFormatterBuilder maximumParsedDigits(int paramInt)
  {
    iMaxParsedDigits = paramInt;
    return this;
  }
  
  public PeriodFormatterBuilder minimumPrintedDigits(int paramInt)
  {
    iMinPrintedDigits = paramInt;
    return this;
  }
  
  public PeriodFormatterBuilder printZeroAlways()
  {
    iPrintZeroSetting = 4;
    return this;
  }
  
  public PeriodFormatterBuilder printZeroIfSupported()
  {
    iPrintZeroSetting = 3;
    return this;
  }
  
  public PeriodFormatterBuilder printZeroNever()
  {
    iPrintZeroSetting = 5;
    return this;
  }
  
  public PeriodFormatterBuilder printZeroRarelyFirst()
  {
    iPrintZeroSetting = 1;
    return this;
  }
  
  public PeriodFormatterBuilder printZeroRarelyLast()
  {
    iPrintZeroSetting = 2;
    return this;
  }
  
  public PeriodFormatterBuilder rejectSignedValues(boolean paramBoolean)
  {
    iRejectSignedValues = paramBoolean;
    return this;
  }
  
  public PeriodFormatter toFormatter()
  {
    PeriodFormatter localPeriodFormatter = toFormatter(iElementPairs, iNotPrinter, iNotParser);
    iFieldFormatters = ((FieldFormatter[])iFieldFormatters.clone());
    return localPeriodFormatter;
  }
  
  public PeriodParser toParser()
  {
    if (iNotParser) {
      return null;
    }
    return toFormatter().getParser();
  }
  
  public PeriodPrinter toPrinter()
  {
    if (iNotPrinter) {
      return null;
    }
    return toFormatter().getPrinter();
  }
  
  static class Composite
    implements PeriodPrinter, PeriodParser
  {
    private final PeriodParser[] iParsers;
    private final PeriodPrinter[] iPrinters;
    
    Composite(List<Object> paramList)
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
  
  static class CompositeAffix
    implements PeriodFormatterBuilder.PeriodFieldAffix
  {
    private final PeriodFormatterBuilder.PeriodFieldAffix iLeft;
    private final PeriodFormatterBuilder.PeriodFieldAffix iRight;
    
    CompositeAffix(PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix1, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix2)
    {
      iLeft = paramPeriodFieldAffix1;
      iRight = paramPeriodFieldAffix2;
    }
    
    public int calculatePrintedLength(int paramInt)
    {
      return iLeft.calculatePrintedLength(paramInt) + iRight.calculatePrintedLength(paramInt);
    }
    
    public int parse(String paramString, int paramInt)
    {
      int i = iLeft.parse(paramString, paramInt);
      paramInt = i;
      if (i >= 0) {
        paramInt = iRight.parse(paramString, i);
      }
      return paramInt;
    }
    
    public void printTo(Writer paramWriter, int paramInt)
      throws IOException
    {
      iLeft.printTo(paramWriter, paramInt);
      iRight.printTo(paramWriter, paramInt);
    }
    
    public void printTo(StringBuffer paramStringBuffer, int paramInt)
    {
      iLeft.printTo(paramStringBuffer, paramInt);
      iRight.printTo(paramStringBuffer, paramInt);
    }
    
    public int scan(String paramString, int paramInt)
    {
      int i = iLeft.scan(paramString, paramInt);
      if (i >= 0) {
        return iRight.scan(paramString, i);
      }
      return paramInt ^ 0xFFFFFFFF;
    }
  }
  
  static class FieldFormatter
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
    
    FieldFormatter(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, FieldFormatter[] paramArrayOfFieldFormatter, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix1, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix2)
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
    
    FieldFormatter(FieldFormatter paramFieldFormatter, PeriodFormatterBuilder.PeriodFieldAffix paramPeriodFieldAffix)
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
  
  static class Literal
    implements PeriodPrinter, PeriodParser
  {
    static final Literal EMPTY = new Literal("");
    private final String iText;
    
    Literal(String paramString)
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
  
  static abstract interface PeriodFieldAffix
  {
    public abstract int calculatePrintedLength(int paramInt);
    
    public abstract int parse(String paramString, int paramInt);
    
    public abstract void printTo(Writer paramWriter, int paramInt)
      throws IOException;
    
    public abstract void printTo(StringBuffer paramStringBuffer, int paramInt);
    
    public abstract int scan(String paramString, int paramInt);
  }
  
  static class PluralAffix
    implements PeriodFormatterBuilder.PeriodFieldAffix
  {
    private final String iPluralText;
    private final String iSingularText;
    
    PluralAffix(String paramString1, String paramString2)
    {
      iSingularText = paramString1;
      iPluralText = paramString2;
    }
    
    public int calculatePrintedLength(int paramInt)
    {
      if (paramInt == 1) {}
      for (String str = iSingularText;; str = iPluralText) {
        return str.length();
      }
    }
    
    public int parse(String paramString, int paramInt)
    {
      String str1 = iPluralText;
      String str3 = iSingularText;
      String str4 = str1;
      String str2 = str3;
      if (str1.length() < str3.length())
      {
        str2 = str1;
        str4 = str3;
      }
      if (paramString.regionMatches(true, paramInt, str4, 0, str4.length())) {
        return str4.length() + paramInt;
      }
      if (paramString.regionMatches(true, paramInt, str2, 0, str2.length())) {
        return str2.length() + paramInt;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
    
    public void printTo(Writer paramWriter, int paramInt)
      throws IOException
    {
      if (paramInt == 1) {}
      for (String str = iSingularText;; str = iPluralText)
      {
        paramWriter.write(str);
        return;
      }
    }
    
    public void printTo(StringBuffer paramStringBuffer, int paramInt)
    {
      if (paramInt == 1) {}
      for (String str = iSingularText;; str = iPluralText)
      {
        paramStringBuffer.append(str);
        return;
      }
    }
    
    public int scan(String paramString, int paramInt)
    {
      String str1 = iPluralText;
      String str3 = iSingularText;
      String str4 = str1;
      String str2 = str3;
      if (str1.length() < str3.length())
      {
        str2 = str1;
        str4 = str3;
      }
      int j = str4.length();
      int k = str2.length();
      int m = paramString.length();
      int i = paramInt;
      while (i < m)
      {
        if (paramString.regionMatches(true, i, str4, 0, j)) {}
        while (paramString.regionMatches(true, i, str2, 0, k)) {
          return i;
        }
        i += 1;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
  }
  
  static class Separator
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
    
    Separator(String paramString1, String paramString2, String[] paramArrayOfString, PeriodPrinter paramPeriodPrinter, PeriodParser paramPeriodParser, boolean paramBoolean1, boolean paramBoolean2)
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
  
  static class SimpleAffix
    implements PeriodFormatterBuilder.PeriodFieldAffix
  {
    private final String iText;
    
    SimpleAffix(String paramString)
    {
      iText = paramString;
    }
    
    public int calculatePrintedLength(int paramInt)
    {
      return iText.length();
    }
    
    public int parse(String paramString, int paramInt)
    {
      String str = iText;
      int i = str.length();
      if (paramString.regionMatches(true, paramInt, str, 0, i)) {
        return paramInt + i;
      }
      return paramInt ^ 0xFFFFFFFF;
    }
    
    public void printTo(Writer paramWriter, int paramInt)
      throws IOException
    {
      paramWriter.write(iText);
    }
    
    public void printTo(StringBuffer paramStringBuffer, int paramInt)
    {
      paramStringBuffer.append(iText);
    }
    
    public int scan(String paramString, int paramInt)
    {
      String str = iText;
      int j = str.length();
      int k = paramString.length();
      int i = paramInt;
      for (;;)
      {
        if (i < k) {
          if (paramString.regionMatches(true, i, str, 0, j)) {
            return i;
          }
        }
        switch (paramString.charAt(i))
        {
        case '/': 
        default: 
          return paramInt ^ 0xFFFFFFFF;
        }
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.format.PeriodFormatterBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */