package org.joda.time.tz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import net.danlew.android.joda.ResUtils;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.LenientChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class ZoneInfoCompiler
{
  static Chronology cLenientISO;
  static DateTimeOfYear cStartOfYear;
  static ThreadLocal<Boolean> cVerbose = new ThreadLocal()
  {
    protected Boolean initialValue()
    {
      return Boolean.FALSE;
    }
  };
  private List<String> iLinks = new ArrayList();
  private Map<String, RuleSet> iRuleSets = new HashMap();
  private List<Zone> iZones = new ArrayList();
  
  static Chronology getLenientISOChronology()
  {
    if (cLenientISO == null) {
      cLenientISO = LenientChronology.getInstance(ISOChronology.getInstanceUTC());
    }
    return cLenientISO;
  }
  
  static DateTimeOfYear getStartOfYear()
  {
    if (cStartOfYear == null) {
      cStartOfYear = new DateTimeOfYear();
    }
    return cStartOfYear;
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    if (paramArrayOfString.length == 0)
    {
      printUsage();
      return;
    }
    File localFile2 = null;
    File localFile1 = null;
    boolean bool = false;
    int i = 0;
    for (;;)
    {
      if (i < paramArrayOfString.length) {
        try
        {
          if ("-src".equals(paramArrayOfString[i]))
          {
            i += 1;
            localFile2 = new File(paramArrayOfString[i]);
            break label225;
          }
          if ("-dst".equals(paramArrayOfString[i]))
          {
            i += 1;
            localFile1 = new File(paramArrayOfString[i]);
            break label225;
          }
          if ("-verbose".equals(paramArrayOfString[i]))
          {
            bool = true;
            break label225;
          }
          if ("-?".equals(paramArrayOfString[i]))
          {
            printUsage();
            return;
          }
        }
        catch (IndexOutOfBoundsException paramArrayOfString)
        {
          printUsage();
          return;
        }
      }
      if (i >= paramArrayOfString.length)
      {
        printUsage();
        return;
      }
      File[] arrayOfFile = new File[paramArrayOfString.length - i];
      int j = 0;
      if (i < paramArrayOfString.length)
      {
        if (localFile2 == null) {}
        for (File localFile3 = new File(paramArrayOfString[i]);; localFile3 = new File(localFile2, paramArrayOfString[i]))
        {
          arrayOfFile[j] = localFile3;
          i += 1;
          j += 1;
          break;
        }
      }
      cVerbose.set(Boolean.valueOf(bool));
      new ZoneInfoCompiler().compile(localFile1, arrayOfFile);
      return;
      label225:
      i += 1;
    }
  }
  
  static int parseDayOfWeek(String paramString)
  {
    DateTimeField localDateTimeField = ISOChronology.getInstanceUTC().dayOfWeek();
    return localDateTimeField.get(localDateTimeField.set(0L, paramString, Locale.ENGLISH));
  }
  
  static int parseMonth(String paramString)
  {
    DateTimeField localDateTimeField = ISOChronology.getInstanceUTC().monthOfYear();
    return localDateTimeField.get(localDateTimeField.set(0L, paramString, Locale.ENGLISH));
  }
  
  static String parseOptional(String paramString)
  {
    String str = paramString;
    if (paramString.equals("-")) {
      str = null;
    }
    return str;
  }
  
  static int parseTime(String paramString)
  {
    DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.hourMinuteSecondFraction();
    MutableDateTime localMutableDateTime = new MutableDateTime(0L, getLenientISOChronology());
    int i = 0;
    if (paramString.startsWith("-")) {
      i = 1;
    }
    if (localDateTimeFormatter.parseInto(localMutableDateTime, paramString, i) == (i ^ 0xFFFFFFFF)) {
      throw new IllegalArgumentException(paramString);
    }
    int k = (int)localMutableDateTime.getMillis();
    int j = k;
    if (i == 1) {
      j = -k;
    }
    return j;
  }
  
  static int parseYear(String paramString, int paramInt)
  {
    paramString = paramString.toLowerCase();
    if ((paramString.equals("minimum")) || (paramString.equals("min"))) {
      paramInt = Integer.MIN_VALUE;
    }
    do
    {
      return paramInt;
      if ((paramString.equals("maximum")) || (paramString.equals("max"))) {
        return Integer.MAX_VALUE;
      }
    } while (paramString.equals("only"));
    return Integer.parseInt(paramString);
  }
  
  static char parseZoneChar(char paramChar)
  {
    switch (paramChar)
    {
    default: 
      return 'w';
    case 'S': 
    case 's': 
      return 's';
    }
    return 'u';
  }
  
  private static void printUsage()
  {
    System.out.println("Usage: java org.joda.time.tz.ZoneInfoCompiler <options> <source files>");
    System.out.println("where possible options include:");
    System.out.println("  -src <directory>    Specify where to read source files");
    System.out.println("  -dst <directory>    Specify where to write generated files");
    System.out.println("  -verbose            Output verbosely (default false)");
  }
  
  static boolean test(String paramString, DateTimeZone paramDateTimeZone)
  {
    if (!paramString.equals(paramDateTimeZone.getID())) {
      return true;
    }
    long l1 = ISOChronology.getInstanceUTC().year().set(0L, 1850);
    long l3 = ISOChronology.getInstanceUTC().year().set(0L, 2050);
    int i = paramDateTimeZone.getOffset(l1);
    paramString = paramDateTimeZone.getNameKey(l1);
    ArrayList localArrayList = new ArrayList();
    long l2 = paramDateTimeZone.nextTransition(l1);
    if ((l2 == l1) || (l2 > l3))
    {
      l1 = ISOChronology.getInstanceUTC().year().set(0L, 2050);
      l3 = ISOChronology.getInstanceUTC().year().set(0L, 1850);
      i = localArrayList.size();
    }
    long l4;
    do
    {
      i -= 1;
      if (i >= 0)
      {
        l2 = paramDateTimeZone.previousTransition(l1);
        if ((l2 != l1) && (l2 >= l3)) {}
      }
      else
      {
        return true;
        l1 = l2;
        int j = paramDateTimeZone.getOffset(l1);
        String str = paramDateTimeZone.getNameKey(l1);
        if ((i == j) && (paramString.equals(str)))
        {
          System.out.println("*d* Error in " + paramDateTimeZone.getID() + " " + new DateTime(l1, ISOChronology.getInstanceUTC()));
          return false;
        }
        if ((str == null) || ((str.length() < 3) && (!"??".equals(str))))
        {
          System.out.println("*s* Error in " + paramDateTimeZone.getID() + " " + new DateTime(l1, ISOChronology.getInstanceUTC()) + ", nameKey=" + str);
          return false;
        }
        localArrayList.add(Long.valueOf(l1));
        i = j;
        paramString = str;
        break;
      }
      l4 = ((Long)localArrayList.get(i)).longValue();
      l1 = l2;
    } while (l4 - 1L == l2);
    System.out.println("*r* Error in " + paramDateTimeZone.getID() + " " + new DateTime(l2, ISOChronology.getInstanceUTC()) + " != " + new DateTime(l4 - 1L, ISOChronology.getInstanceUTC()));
    return false;
  }
  
  public static boolean verbose()
  {
    return ((Boolean)cVerbose.get()).booleanValue();
  }
  
  static void writeZoneInfoMap(DataOutputStream paramDataOutputStream, Map<String, DateTimeZone> paramMap)
    throws IOException
  {
    HashMap localHashMap = new HashMap(paramMap.size());
    Object localObject1 = new TreeMap();
    short s1 = 0;
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Map.Entry)localIterator.next();
      Object localObject3 = (String)((Map.Entry)localObject2).getKey();
      short s2 = s1;
      if (!localHashMap.containsKey(localObject3))
      {
        Short localShort = Short.valueOf(s1);
        localHashMap.put(localObject3, localShort);
        ((TreeMap)localObject1).put(localShort, localObject3);
        s1 = (short)(s1 + 1);
        s2 = s1;
        if (s1 == 0) {
          throw new InternalError("Too many time zone ids");
        }
      }
      localObject2 = ((DateTimeZone)((Map.Entry)localObject2).getValue()).getID();
      s1 = s2;
      if (!localHashMap.containsKey(localObject2))
      {
        localObject3 = Short.valueOf(s2);
        localHashMap.put(localObject2, localObject3);
        ((TreeMap)localObject1).put(localObject3, localObject2);
        s2 = (short)(s2 + 1);
        s1 = s2;
        if (s2 == 0) {
          throw new InternalError("Too many time zone ids");
        }
      }
    }
    paramDataOutputStream.writeShort(((TreeMap)localObject1).size());
    localObject1 = ((TreeMap)localObject1).values().iterator();
    while (((Iterator)localObject1).hasNext()) {
      paramDataOutputStream.writeUTF((String)((Iterator)localObject1).next());
    }
    paramDataOutputStream.writeShort(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      localObject1 = (Map.Entry)paramMap.next();
      paramDataOutputStream.writeShort(((Short)localHashMap.get((String)((Map.Entry)localObject1).getKey())).shortValue());
      paramDataOutputStream.writeShort(((Short)localHashMap.get(((DateTimeZone)((Map.Entry)localObject1).getValue()).getID())).shortValue());
    }
  }
  
  public Map<String, DateTimeZone> compile(File paramFile, File[] paramArrayOfFile)
    throws IOException
  {
    Object localObject1;
    if (paramArrayOfFile != null)
    {
      i = 0;
      while (i < paramArrayOfFile.length)
      {
        localObject1 = new BufferedReader(new FileReader(paramArrayOfFile[i]));
        parseDataFile((BufferedReader)localObject1);
        ((BufferedReader)localObject1).close();
        i += 1;
      }
    }
    if (paramFile != null)
    {
      if ((!paramFile.exists()) && (!paramFile.mkdirs())) {
        throw new IOException("Destination directory doesn't exist and cannot be created: " + paramFile);
      }
      if (!paramFile.isDirectory()) {
        throw new IOException("Destination is not a directory: " + paramFile);
      }
    }
    paramArrayOfFile = new TreeMap();
    System.out.println("Writing zoneinfo files");
    int i = 0;
    Object localObject2;
    Object localObject3;
    for (;;)
    {
      Object localObject4;
      File localFile;
      if (i < iZones.size())
      {
        localObject2 = (Zone)iZones.get(i);
        localObject3 = new DateTimeZoneBuilder();
        ((Zone)localObject2).addToBuilder((DateTimeZoneBuilder)localObject3, iRuleSets);
        localObject1 = ((DateTimeZoneBuilder)localObject3).toDateTimeZone(iName, true);
        if (test(((DateTimeZone)localObject1).getID(), (DateTimeZone)localObject1))
        {
          paramArrayOfFile.put(((DateTimeZone)localObject1).getID(), localObject1);
          if (paramFile != null)
          {
            localObject4 = ResUtils.getTzResource(((DateTimeZone)localObject1).getID());
            if (verbose()) {
              System.out.println("Writing " + (String)localObject4);
            }
            localFile = new File(paramFile, (String)localObject4);
            if (!localFile.getParentFile().exists()) {
              localFile.getParentFile().mkdirs();
            }
            localObject4 = new FileOutputStream(localFile);
          }
        }
      }
      try
      {
        ((DateTimeZoneBuilder)localObject3).writeTo(iName, (OutputStream)localObject4);
        ((OutputStream)localObject4).close();
        localObject2 = new FileInputStream(localFile);
        localObject3 = DateTimeZoneBuilder.readFrom((InputStream)localObject2, ((DateTimeZone)localObject1).getID());
        ((InputStream)localObject2).close();
        if (!((DateTimeZone)localObject1).equals(localObject3)) {
          System.out.println("*e* Error in " + ((DateTimeZone)localObject1).getID() + ": Didn't read properly from file");
        }
        i += 1;
      }
      finally
      {
        ((OutputStream)localObject4).close();
      }
    }
    while (i < 2)
    {
      int j = 0;
      if (j < iLinks.size())
      {
        localObject1 = (String)iLinks.get(j);
        localObject2 = (String)iLinks.get(j + 1);
        localObject3 = (DateTimeZone)paramArrayOfFile.get(localObject1);
        if (localObject3 == null) {
          if (i > 0) {
            System.out.println("Cannot find time zone '" + (String)localObject1 + "' to link alias '" + (String)localObject2 + "' to");
          }
        }
        for (;;)
        {
          j += 2;
          break;
          paramArrayOfFile.put(localObject2, localObject3);
        }
      }
      i += 1;
    }
    if (paramFile != null)
    {
      System.out.println("Writing " + ResUtils.getZoneInfoMapResource());
      paramFile = new File(paramFile, ResUtils.getZoneInfoMapResource());
      if (!paramFile.getParentFile().exists()) {
        paramFile.getParentFile().mkdirs();
      }
      paramFile = new DataOutputStream(new FileOutputStream(paramFile));
    }
    try
    {
      localObject1 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
      ((Map)localObject1).putAll(paramArrayOfFile);
      writeZoneInfoMap(paramFile, (Map)localObject1);
      return paramArrayOfFile;
    }
    finally
    {
      paramFile.close();
    }
  }
  
  public void parseDataFile(BufferedReader paramBufferedReader)
    throws IOException
  {
    Object localObject1 = null;
    for (;;)
    {
      String str = paramBufferedReader.readLine();
      if (str == null) {
        break;
      }
      Object localObject2 = str.trim();
      if ((((String)localObject2).length() != 0) && (((String)localObject2).charAt(0) != '#'))
      {
        int i = str.indexOf('#');
        localObject2 = str;
        if (i >= 0) {
          localObject2 = str.substring(0, i);
        }
        StringTokenizer localStringTokenizer = new StringTokenizer((String)localObject2, " \t");
        if ((Character.isWhitespace(((String)localObject2).charAt(0))) && (localStringTokenizer.hasMoreTokens()))
        {
          if (localObject1 != null) {
            ((Zone)localObject1).chain(localStringTokenizer);
          }
        }
        else
        {
          if (localObject1 != null) {
            iZones.add(localObject1);
          }
          str = null;
          localObject1 = str;
          if (localStringTokenizer.hasMoreTokens())
          {
            localObject1 = localStringTokenizer.nextToken();
            if (((String)localObject1).equalsIgnoreCase("Rule"))
            {
              localObject1 = new Rule(localStringTokenizer);
              localObject2 = (RuleSet)iRuleSets.get(iName);
              if (localObject2 == null)
              {
                localObject2 = new RuleSet((Rule)localObject1);
                iRuleSets.put(iName, localObject2);
                localObject1 = str;
              }
              else
              {
                ((RuleSet)localObject2).addRule((Rule)localObject1);
                localObject1 = str;
              }
            }
            else if (((String)localObject1).equalsIgnoreCase("Zone"))
            {
              localObject1 = new Zone(localStringTokenizer);
            }
            else if (((String)localObject1).equalsIgnoreCase("Link"))
            {
              iLinks.add(localStringTokenizer.nextToken());
              iLinks.add(localStringTokenizer.nextToken());
              localObject1 = str;
            }
            else
            {
              System.out.println("Unknown line: " + (String)localObject2);
              localObject1 = str;
            }
          }
        }
      }
    }
    if (localObject1 != null) {
      iZones.add(localObject1);
    }
  }
  
  static class DateTimeOfYear
  {
    public final boolean iAdvanceDayOfWeek;
    public final int iDayOfMonth;
    public final int iDayOfWeek;
    public final int iMillisOfDay;
    public final int iMonthOfYear;
    public final char iZoneChar;
    
    DateTimeOfYear()
    {
      iMonthOfYear = 1;
      iDayOfMonth = 1;
      iDayOfWeek = 0;
      iAdvanceDayOfWeek = false;
      iMillisOfDay = 0;
      iZoneChar = 'w';
    }
    
    DateTimeOfYear(StringTokenizer paramStringTokenizer)
    {
      int n = 1;
      int i = 1;
      int j = 0;
      int i2 = 0;
      boolean bool1 = false;
      char c2 = 'w';
      boolean bool2 = bool1;
      int m = i;
      int k = j;
      int i1 = i2;
      char c1 = c2;
      int i3;
      String str;
      if (paramStringTokenizer.hasMoreTokens())
      {
        i3 = ZoneInfoCompiler.parseMonth(paramStringTokenizer.nextToken());
        bool2 = bool1;
        m = i;
        k = j;
        i1 = i2;
        n = i3;
        c1 = c2;
        if (paramStringTokenizer.hasMoreTokens())
        {
          str = paramStringTokenizer.nextToken();
          if (!str.startsWith("last")) {
            break label273;
          }
          i = -1;
          j = ZoneInfoCompiler.parseDayOfWeek(str.substring(4));
          bool1 = false;
          bool2 = bool1;
          m = i;
          k = j;
          i1 = i2;
          n = i3;
          c1 = c2;
          if (paramStringTokenizer.hasMoreTokens())
          {
            paramStringTokenizer = paramStringTokenizer.nextToken();
            c1 = ZoneInfoCompiler.parseZoneChar(paramStringTokenizer.charAt(paramStringTokenizer.length() - 1));
            if (!paramStringTokenizer.equals("24:00")) {
              break label423;
            }
            if (i != -1) {
              break label395;
            }
            paramStringTokenizer = new LocalDate(2001, i3, 1).plusMonths(1);
            label199:
            if (i == -1) {
              break label417;
            }
            bool2 = true;
            label208:
            n = paramStringTokenizer.getMonthOfYear();
            m = paramStringTokenizer.getDayOfMonth();
            k = (j - 1 + 1) % 7 + 1;
            i1 = i2;
          }
        }
      }
      for (;;)
      {
        iMonthOfYear = n;
        iDayOfMonth = m;
        iDayOfWeek = k;
        iAdvanceDayOfWeek = bool2;
        iMillisOfDay = i1;
        iZoneChar = c1;
        return;
        try
        {
          label273:
          i = Integer.parseInt(str);
          j = 0;
          bool1 = false;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          j = str.indexOf(">=");
          if (j > 0)
          {
            i = Integer.parseInt(str.substring(j + 2));
            j = ZoneInfoCompiler.parseDayOfWeek(str.substring(0, j));
            bool1 = true;
            break;
          }
          j = str.indexOf("<=");
          if (j > 0)
          {
            i = Integer.parseInt(str.substring(j + 2));
            j = ZoneInfoCompiler.parseDayOfWeek(str.substring(0, j));
            bool1 = false;
            break;
          }
          throw new IllegalArgumentException(str);
        }
        label395:
        paramStringTokenizer = new LocalDate(2001, i3, i).plusDays(1);
        break label199;
        label417:
        bool2 = false;
        break label208;
        label423:
        i1 = ZoneInfoCompiler.parseTime(paramStringTokenizer);
        bool2 = bool1;
        m = i;
        k = j;
        n = i3;
      }
    }
    
    public void addCutover(DateTimeZoneBuilder paramDateTimeZoneBuilder, int paramInt)
    {
      paramDateTimeZoneBuilder.addCutover(paramInt, iZoneChar, iMonthOfYear, iDayOfMonth, iDayOfWeek, iAdvanceDayOfWeek, iMillisOfDay);
    }
    
    public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString, int paramInt1, int paramInt2, int paramInt3)
    {
      paramDateTimeZoneBuilder.addRecurringSavings(paramString, paramInt1, paramInt2, paramInt3, iZoneChar, iMonthOfYear, iDayOfMonth, iDayOfWeek, iAdvanceDayOfWeek, iMillisOfDay);
    }
    
    public String toString()
    {
      return "MonthOfYear: " + iMonthOfYear + "\n" + "DayOfMonth: " + iDayOfMonth + "\n" + "DayOfWeek: " + iDayOfWeek + "\n" + "AdvanceDayOfWeek: " + iAdvanceDayOfWeek + "\n" + "MillisOfDay: " + iMillisOfDay + "\n" + "ZoneChar: " + iZoneChar + "\n";
    }
  }
  
  private static class Rule
  {
    public final ZoneInfoCompiler.DateTimeOfYear iDateTimeOfYear;
    public final int iFromYear;
    public final String iLetterS;
    public final String iName;
    public final int iSaveMillis;
    public final int iToYear;
    public final String iType;
    
    Rule(StringTokenizer paramStringTokenizer)
    {
      iName = paramStringTokenizer.nextToken().intern();
      iFromYear = ZoneInfoCompiler.parseYear(paramStringTokenizer.nextToken(), 0);
      iToYear = ZoneInfoCompiler.parseYear(paramStringTokenizer.nextToken(), iFromYear);
      if (iToYear < iFromYear) {
        throw new IllegalArgumentException();
      }
      iType = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
      iDateTimeOfYear = new ZoneInfoCompiler.DateTimeOfYear(paramStringTokenizer);
      iSaveMillis = ZoneInfoCompiler.parseTime(paramStringTokenizer.nextToken());
      iLetterS = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
    }
    
    private String formatName(String paramString)
    {
      int i = paramString.indexOf('/');
      if (i > 0) {
        if (iSaveMillis == 0) {
          str = paramString.substring(0, i).intern();
        }
      }
      do
      {
        return str;
        return paramString.substring(i + 1).intern();
        i = paramString.indexOf("%s");
        str = paramString;
      } while (i < 0);
      String str = paramString.substring(0, i);
      paramString = paramString.substring(i + 2);
      if (iLetterS == null) {}
      for (paramString = str.concat(paramString);; paramString = str + iLetterS + paramString) {
        return paramString.intern();
      }
    }
    
    public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString)
    {
      paramString = formatName(paramString);
      iDateTimeOfYear.addRecurring(paramDateTimeZoneBuilder, paramString, iSaveMillis, iFromYear, iToYear);
    }
    
    public String toString()
    {
      return "[Rule]\nName: " + iName + "\n" + "FromYear: " + iFromYear + "\n" + "ToYear: " + iToYear + "\n" + "Type: " + iType + "\n" + iDateTimeOfYear + "SaveMillis: " + iSaveMillis + "\n" + "LetterS: " + iLetterS + "\n";
    }
  }
  
  private static class RuleSet
  {
    private List<ZoneInfoCompiler.Rule> iRules = new ArrayList();
    
    RuleSet(ZoneInfoCompiler.Rule paramRule)
    {
      iRules.add(paramRule);
    }
    
    public void addRecurring(DateTimeZoneBuilder paramDateTimeZoneBuilder, String paramString)
    {
      int i = 0;
      while (i < iRules.size())
      {
        ((ZoneInfoCompiler.Rule)iRules.get(i)).addRecurring(paramDateTimeZoneBuilder, paramString);
        i += 1;
      }
    }
    
    void addRule(ZoneInfoCompiler.Rule paramRule)
    {
      if (!iName.equals(iRules.get(0)).iName)) {
        throw new IllegalArgumentException("Rule name mismatch");
      }
      iRules.add(paramRule);
    }
  }
  
  private static class Zone
  {
    public final String iFormat;
    public final String iName;
    private Zone iNext;
    public final int iOffsetMillis;
    public final String iRules;
    public final ZoneInfoCompiler.DateTimeOfYear iUntilDateTimeOfYear;
    public final int iUntilYear;
    
    private Zone(String paramString, StringTokenizer paramStringTokenizer)
    {
      iName = paramString.intern();
      iOffsetMillis = ZoneInfoCompiler.parseTime(paramStringTokenizer.nextToken());
      iRules = ZoneInfoCompiler.parseOptional(paramStringTokenizer.nextToken());
      iFormat = paramStringTokenizer.nextToken().intern();
      int i = Integer.MAX_VALUE;
      ZoneInfoCompiler.DateTimeOfYear localDateTimeOfYear = ZoneInfoCompiler.getStartOfYear();
      paramString = localDateTimeOfYear;
      if (paramStringTokenizer.hasMoreTokens())
      {
        int j = Integer.parseInt(paramStringTokenizer.nextToken());
        paramString = localDateTimeOfYear;
        i = j;
        if (paramStringTokenizer.hasMoreTokens())
        {
          paramString = new ZoneInfoCompiler.DateTimeOfYear(paramStringTokenizer);
          i = j;
        }
      }
      iUntilYear = i;
      iUntilDateTimeOfYear = paramString;
    }
    
    Zone(StringTokenizer paramStringTokenizer)
    {
      this(paramStringTokenizer.nextToken(), paramStringTokenizer);
    }
    
    private static void addToBuilder(Zone paramZone, DateTimeZoneBuilder paramDateTimeZoneBuilder, Map<String, ZoneInfoCompiler.RuleSet> paramMap)
    {
      for (;;)
      {
        if (paramZone != null)
        {
          paramDateTimeZoneBuilder.setStandardOffset(iOffsetMillis);
          if (iRules != null) {
            break label40;
          }
          paramDateTimeZoneBuilder.setFixedSavings(iFormat, 0);
        }
        while (iUntilYear == Integer.MAX_VALUE)
        {
          return;
          try
          {
            label40:
            int i = ZoneInfoCompiler.parseTime(iRules);
            paramDateTimeZoneBuilder.setFixedSavings(iFormat, i);
          }
          catch (Exception localException)
          {
            ZoneInfoCompiler.RuleSet localRuleSet = (ZoneInfoCompiler.RuleSet)paramMap.get(iRules);
            if (localRuleSet == null) {
              throw new IllegalArgumentException("Rules not found: " + iRules);
            }
            localRuleSet.addRecurring(paramDateTimeZoneBuilder, iFormat);
          }
        }
        iUntilDateTimeOfYear.addCutover(paramDateTimeZoneBuilder, iUntilYear);
        paramZone = iNext;
      }
    }
    
    public void addToBuilder(DateTimeZoneBuilder paramDateTimeZoneBuilder, Map<String, ZoneInfoCompiler.RuleSet> paramMap)
    {
      addToBuilder(this, paramDateTimeZoneBuilder, paramMap);
    }
    
    void chain(StringTokenizer paramStringTokenizer)
    {
      if (iNext != null)
      {
        iNext.chain(paramStringTokenizer);
        return;
      }
      iNext = new Zone(iName, paramStringTokenizer);
    }
    
    public String toString()
    {
      String str = "[Zone]\nName: " + iName + "\n" + "OffsetMillis: " + iOffsetMillis + "\n" + "Rules: " + iRules + "\n" + "Format: " + iFormat + "\n" + "UntilYear: " + iUntilYear + "\n" + iUntilDateTimeOfYear;
      if (iNext == null) {
        return str;
      }
      return str + "...\n" + iNext.toString();
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.ZoneInfoCompiler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */