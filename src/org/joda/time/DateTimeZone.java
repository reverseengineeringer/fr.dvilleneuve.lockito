package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;

public abstract class DateTimeZone
  implements Serializable
{
  private static final int MAX_MILLIS = 86399999;
  public static final DateTimeZone UTC = new FixedDateTimeZone("UTC", "UTC", 0, 0);
  private static Set<String> cAvailableIDs;
  private static volatile DateTimeZone cDefault;
  private static NameProvider cNameProvider;
  private static DateTimeFormatter cOffsetFormatter;
  private static Provider cProvider;
  private static Map<String, String> cZoneIdConversion;
  private static Map<String, SoftReference<DateTimeZone>> iFixedOffsetCache;
  private static final long serialVersionUID = 5546345482340108586L;
  private final String iID;
  
  static
  {
    setProvider0(null);
    setNameProvider0(null);
  }
  
  protected DateTimeZone(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Id must not be null");
    }
    iID = paramString;
  }
  
  private static DateTimeZone fixedOffsetZone(String paramString, int paramInt)
  {
    if (paramInt == 0) {}
    for (;;)
    {
      try
      {
        localObject = UTC;
        return (DateTimeZone)localObject;
      }
      finally {}
      if (iFixedOffsetCache == null) {
        iFixedOffsetCache = new HashMap();
      }
      Object localObject = (Reference)iFixedOffsetCache.get(paramString);
      if (localObject != null)
      {
        DateTimeZone localDateTimeZone = (DateTimeZone)((Reference)localObject).get();
        localObject = localDateTimeZone;
        if (localDateTimeZone != null) {}
      }
      else
      {
        localObject = new FixedDateTimeZone(paramString, null, paramInt, paramInt);
        iFixedOffsetCache.put(paramString, new SoftReference(localObject));
      }
    }
  }
  
  @FromString
  public static DateTimeZone forID(String paramString)
  {
    Object localObject;
    if (paramString == null) {
      localObject = getDefault();
    }
    DateTimeZone localDateTimeZone;
    do
    {
      return (DateTimeZone)localObject;
      if (paramString.equals("UTC")) {
        return UTC;
      }
      localDateTimeZone = cProvider.getZone(paramString);
      localObject = localDateTimeZone;
    } while (localDateTimeZone != null);
    if ((paramString.startsWith("+")) || (paramString.startsWith("-")))
    {
      int i = parseOffset(paramString);
      if (i == 0L) {
        return UTC;
      }
      return fixedOffsetZone(printOffset(i), i);
    }
    throw new IllegalArgumentException("The datetime zone id '" + paramString + "' is not recognised");
  }
  
  public static DateTimeZone forOffsetHours(int paramInt)
    throws IllegalArgumentException
  {
    return forOffsetHoursMinutes(paramInt, 0);
  }
  
  public static DateTimeZone forOffsetHoursMinutes(int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    if ((paramInt1 == 0) && (paramInt2 == 0)) {
      return UTC;
    }
    if ((paramInt1 < -23) || (paramInt1 > 23)) {
      throw new IllegalArgumentException("Hours out of range: " + paramInt1);
    }
    if ((paramInt2 < -59) || (paramInt2 > 59)) {
      throw new IllegalArgumentException("Minutes out of range: " + paramInt2);
    }
    if ((paramInt1 > 0) && (paramInt2 < 0)) {
      throw new IllegalArgumentException("Positive hours must not have negative minutes: " + paramInt2);
    }
    paramInt1 *= 60;
    if (paramInt1 < 0) {}
    for (;;)
    {
      try
      {
        paramInt1 -= Math.abs(paramInt2);
        paramInt1 = FieldUtils.safeMultiply(paramInt1, 60000);
        return forOffsetMillis(paramInt1);
      }
      catch (ArithmeticException localArithmeticException)
      {
        throw new IllegalArgumentException("Offset is too large");
      }
      paramInt1 = paramInt2 + paramInt1;
    }
  }
  
  public static DateTimeZone forOffsetMillis(int paramInt)
  {
    if ((paramInt < -86399999) || (paramInt > 86399999)) {
      throw new IllegalArgumentException("Millis out of range: " + paramInt);
    }
    return fixedOffsetZone(printOffset(paramInt), paramInt);
  }
  
  public static DateTimeZone forTimeZone(TimeZone paramTimeZone)
  {
    Object localObject2;
    if (paramTimeZone == null) {
      localObject2 = getDefault();
    }
    String str1;
    String str2;
    Object localObject1;
    do
    {
      return (DateTimeZone)localObject2;
      str1 = paramTimeZone.getID();
      if (str1.equals("UTC")) {
        return UTC;
      }
      localObject2 = null;
      str2 = getConvertedId(str1);
      if (str2 != null) {
        localObject2 = cProvider.getZone(str2);
      }
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = cProvider.getZone(str1);
      }
      localObject2 = localObject1;
    } while (localObject1 != null);
    if (str2 == null)
    {
      paramTimeZone = paramTimeZone.getID();
      if ((paramTimeZone.startsWith("GMT+")) || (paramTimeZone.startsWith("GMT-")))
      {
        int i = parseOffset(paramTimeZone.substring(3));
        if (i == 0L) {
          return UTC;
        }
        return fixedOffsetZone(printOffset(i), i);
      }
    }
    throw new IllegalArgumentException("The datetime zone id '" + str1 + "' is not recognised");
  }
  
  public static Set<String> getAvailableIDs()
  {
    return cAvailableIDs;
  }
  
  private static String getConvertedId(String paramString)
  {
    try
    {
      Map localMap = cZoneIdConversion;
      Object localObject = localMap;
      if (localMap == null)
      {
        localObject = new HashMap();
        ((Map)localObject).put("GMT", "UTC");
        ((Map)localObject).put("WET", "WET");
        ((Map)localObject).put("CET", "CET");
        ((Map)localObject).put("MET", "CET");
        ((Map)localObject).put("ECT", "CET");
        ((Map)localObject).put("EET", "EET");
        ((Map)localObject).put("MIT", "Pacific/Apia");
        ((Map)localObject).put("HST", "Pacific/Honolulu");
        ((Map)localObject).put("AST", "America/Anchorage");
        ((Map)localObject).put("PST", "America/Los_Angeles");
        ((Map)localObject).put("MST", "America/Denver");
        ((Map)localObject).put("PNT", "America/Phoenix");
        ((Map)localObject).put("CST", "America/Chicago");
        ((Map)localObject).put("EST", "America/New_York");
        ((Map)localObject).put("IET", "America/Indiana/Indianapolis");
        ((Map)localObject).put("PRT", "America/Puerto_Rico");
        ((Map)localObject).put("CNT", "America/St_Johns");
        ((Map)localObject).put("AGT", "America/Argentina/Buenos_Aires");
        ((Map)localObject).put("BET", "America/Sao_Paulo");
        ((Map)localObject).put("ART", "Africa/Cairo");
        ((Map)localObject).put("CAT", "Africa/Harare");
        ((Map)localObject).put("EAT", "Africa/Addis_Ababa");
        ((Map)localObject).put("NET", "Asia/Yerevan");
        ((Map)localObject).put("PLT", "Asia/Karachi");
        ((Map)localObject).put("IST", "Asia/Kolkata");
        ((Map)localObject).put("BST", "Asia/Dhaka");
        ((Map)localObject).put("VST", "Asia/Ho_Chi_Minh");
        ((Map)localObject).put("CTT", "Asia/Shanghai");
        ((Map)localObject).put("JST", "Asia/Tokyo");
        ((Map)localObject).put("ACT", "Australia/Darwin");
        ((Map)localObject).put("AET", "Australia/Sydney");
        ((Map)localObject).put("SST", "Pacific/Guadalcanal");
        ((Map)localObject).put("NST", "Pacific/Auckland");
        cZoneIdConversion = (Map)localObject;
      }
      paramString = (String)((Map)localObject).get(paramString);
      return paramString;
    }
    finally {}
  }
  
  public static DateTimeZone getDefault()
  {
    Object localObject1 = cDefault;
    if (localObject1 == null)
    {
      for (;;)
      {
        Object localObject4;
        DateTimeZone localDateTimeZone;
        String str;
        try
        {
          localObject1 = cDefault;
          localObject4 = localObject1;
          if (localObject1 == null)
          {
            localDateTimeZone = null;
            localObject4 = null;
            localObject1 = localDateTimeZone;
          }
        }
        finally {}
        try
        {
          str = System.getProperty("user.timezone");
          localObject1 = localObject4;
          if (str != null)
          {
            localObject1 = localDateTimeZone;
            localDateTimeZone = forID(str);
            localObject1 = localDateTimeZone;
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          localObject5 = localObject2;
        }
        catch (RuntimeException localRuntimeException)
        {
          Object localObject5;
          localObject3 = localObject5;
        }
      }
      localObject4 = localObject1;
      if (localObject1 == null) {
        localObject4 = forTimeZone(TimeZone.getDefault());
      }
      localObject1 = localObject4;
      if (localObject4 == null) {
        localObject1 = UTC;
      }
      localObject4 = localObject1;
      cDefault = (DateTimeZone)localObject1;
      return (DateTimeZone)localObject4;
    }
    Object localObject3;
    return (DateTimeZone)localObject3;
  }
  
  private static NameProvider getDefaultNameProvider()
  {
    localObject3 = null;
    for (;;)
    {
      try
      {
        str = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
        localObject1 = localObject3;
        if (str == null) {}
      }
      catch (SecurityException localSecurityException)
      {
        String str;
        Object localObject1;
        Object localObject2 = localObject3;
        continue;
      }
      try
      {
        localObject1 = (NameProvider)Class.forName(str).newInstance();
        localObject3 = localObject1;
        if (localObject1 == null) {
          localObject3 = new DefaultNameProvider();
        }
        return (NameProvider)localObject3;
      }
      catch (Exception localException)
      {
        throw new RuntimeException(localException);
      }
    }
  }
  
  /* Error */
  private static Provider getDefaultProvider()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: ldc_w 381
    //   5: invokestatic 345	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_1
    //   9: aload_1
    //   10: ifnull +14 -> 24
    //   13: aload_1
    //   14: invokestatic 366	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   17: invokevirtual 369	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   20: checkcast 112	org/joda/time/tz/Provider
    //   23: astore_0
    //   24: aload_0
    //   25: ifnonnull +50 -> 75
    //   28: new 383	net/danlew/android/joda/ResourceZoneInfoProvider
    //   31: dup
    //   32: invokespecial 384	net/danlew/android/joda/ResourceZoneInfoProvider:<init>	()V
    //   35: astore_1
    //   36: aload_1
    //   37: astore_0
    //   38: aload_0
    //   39: astore_1
    //   40: aload_0
    //   41: ifnonnull +11 -> 52
    //   44: new 386	org/joda/time/tz/UTCProvider
    //   47: dup
    //   48: invokespecial 387	org/joda/time/tz/UTCProvider:<init>	()V
    //   51: astore_1
    //   52: aload_1
    //   53: areturn
    //   54: astore_0
    //   55: new 336	java/lang/RuntimeException
    //   58: dup
    //   59: aload_0
    //   60: invokespecial 377	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   63: athrow
    //   64: astore_0
    //   65: aconst_null
    //   66: astore_0
    //   67: goto -43 -> 24
    //   70: astore_1
    //   71: aload_1
    //   72: invokevirtual 390	java/lang/Exception:printStackTrace	()V
    //   75: goto -37 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	40	0	localObject1	Object
    //   54	6	0	localException1	Exception
    //   64	1	0	localSecurityException	SecurityException
    //   66	1	0	localObject2	Object
    //   8	45	1	localObject3	Object
    //   70	2	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   13	24	54	java/lang/Exception
    //   2	9	64	java/lang/SecurityException
    //   13	24	64	java/lang/SecurityException
    //   55	64	64	java/lang/SecurityException
    //   28	36	70	java/lang/Exception
  }
  
  public static NameProvider getNameProvider()
  {
    return cNameProvider;
  }
  
  public static Provider getProvider()
  {
    return cProvider;
  }
  
  private static DateTimeFormatter offsetFormatter()
  {
    try
    {
      if (cOffsetFormatter == null) {
        cOffsetFormatter = new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter();
      }
      DateTimeFormatter localDateTimeFormatter = cOffsetFormatter;
      return localDateTimeFormatter;
    }
    finally {}
  }
  
  private static int parseOffset(String paramString)
  {
    BaseChronology local1 = new BaseChronology()
    {
      private static final long serialVersionUID = -3128740902654445468L;
      
      public DateTimeZone getZone()
      {
        return null;
      }
      
      public String toString()
      {
        return getClass().getName();
      }
      
      public Chronology withUTC()
      {
        return this;
      }
      
      public Chronology withZone(DateTimeZone paramAnonymousDateTimeZone)
      {
        return this;
      }
    };
    return -(int)offsetFormatter().withChronology(local1).parseMillis(paramString);
  }
  
  private static String printOffset(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramInt >= 0) {
      localStringBuffer.append('+');
    }
    for (;;)
    {
      i = paramInt / 3600000;
      FormatUtils.appendPaddedInteger(localStringBuffer, i, 2);
      paramInt -= i * 3600000;
      i = paramInt / 60000;
      localStringBuffer.append(':');
      FormatUtils.appendPaddedInteger(localStringBuffer, i, 2);
      paramInt -= i * 60000;
      if (paramInt != 0) {
        break;
      }
      return localStringBuffer.toString();
      localStringBuffer.append('-');
      paramInt = -paramInt;
    }
    int i = paramInt / 1000;
    localStringBuffer.append(':');
    FormatUtils.appendPaddedInteger(localStringBuffer, i, 2);
    paramInt -= i * 1000;
    if (paramInt == 0) {
      return localStringBuffer.toString();
    }
    localStringBuffer.append('.');
    FormatUtils.appendPaddedInteger(localStringBuffer, paramInt, 3);
    return localStringBuffer.toString();
  }
  
  public static void setDefault(DateTimeZone paramDateTimeZone)
    throws SecurityException
  {
    SecurityManager localSecurityManager = System.getSecurityManager();
    if (localSecurityManager != null) {
      localSecurityManager.checkPermission(new JodaTimePermission("DateTimeZone.setDefault"));
    }
    if (paramDateTimeZone == null) {
      throw new IllegalArgumentException("The datetime zone must not be null");
    }
    try
    {
      cDefault = paramDateTimeZone;
      return;
    }
    finally {}
  }
  
  public static void setNameProvider(NameProvider paramNameProvider)
    throws SecurityException
  {
    SecurityManager localSecurityManager = System.getSecurityManager();
    if (localSecurityManager != null) {
      localSecurityManager.checkPermission(new JodaTimePermission("DateTimeZone.setNameProvider"));
    }
    setNameProvider0(paramNameProvider);
  }
  
  private static void setNameProvider0(NameProvider paramNameProvider)
  {
    NameProvider localNameProvider = paramNameProvider;
    if (paramNameProvider == null) {
      localNameProvider = getDefaultNameProvider();
    }
    cNameProvider = localNameProvider;
  }
  
  public static void setProvider(Provider paramProvider)
    throws SecurityException
  {
    SecurityManager localSecurityManager = System.getSecurityManager();
    if (localSecurityManager != null) {
      localSecurityManager.checkPermission(new JodaTimePermission("DateTimeZone.setProvider"));
    }
    setProvider0(paramProvider);
  }
  
  private static void setProvider0(Provider paramProvider)
  {
    Provider localProvider = paramProvider;
    if (paramProvider == null) {
      localProvider = getDefaultProvider();
    }
    paramProvider = localProvider.getAvailableIDs();
    if ((paramProvider == null) || (paramProvider.size() == 0)) {
      throw new IllegalArgumentException("The provider doesn't have any available ids");
    }
    if (!paramProvider.contains("UTC")) {
      throw new IllegalArgumentException("The provider doesn't support UTC");
    }
    if (!UTC.equals(localProvider.getZone("UTC"))) {
      throw new IllegalArgumentException("Invalid UTC zone provided");
    }
    cProvider = localProvider;
    cAvailableIDs = paramProvider;
  }
  
  public long adjustOffset(long paramLong, boolean paramBoolean)
  {
    long l1 = paramLong - 10800000L;
    long l2 = getOffset(l1);
    long l3 = getOffset(paramLong + 10800000L);
    if (l2 <= l3) {}
    do
    {
      do
      {
        do
        {
          return paramLong;
          l2 -= l3;
          l1 = nextTransition(l1);
          l3 = l1 - l2;
        } while ((paramLong < l3) || (paramLong >= l1 + l2));
        if (paramLong - l3 < l2) {
          break;
        }
      } while (paramBoolean);
      return paramLong - l2;
    } while (!paramBoolean);
    return paramLong + l2;
  }
  
  public long convertLocalToUTC(long paramLong, boolean paramBoolean)
  {
    int j = getOffset(paramLong);
    int k = getOffset(paramLong - j);
    int i = k;
    if (j != k) {
      if (!paramBoolean)
      {
        i = k;
        if (j >= 0) {}
      }
      else
      {
        long l2 = nextTransition(paramLong - j);
        l1 = l2;
        if (l2 == paramLong - j) {
          l1 = Long.MAX_VALUE;
        }
        long l3 = nextTransition(paramLong - k);
        l2 = l3;
        if (l3 == paramLong - k) {
          l2 = Long.MAX_VALUE;
        }
        i = k;
        if (l1 != l2)
        {
          if (paramBoolean) {
            throw new IllegalInstantException(paramLong, getID());
          }
          i = j;
        }
      }
    }
    long l1 = paramLong - i;
    if (((paramLong ^ l1) < 0L) && ((i ^ paramLong) < 0L)) {
      throw new ArithmeticException("Subtracting time zone offset caused overflow");
    }
    return l1;
  }
  
  public long convertLocalToUTC(long paramLong1, boolean paramBoolean, long paramLong2)
  {
    int i = getOffset(paramLong2);
    paramLong2 = paramLong1 - i;
    if (getOffset(paramLong2) == i) {
      return paramLong2;
    }
    return convertLocalToUTC(paramLong1, paramBoolean);
  }
  
  public long convertUTCToLocal(long paramLong)
  {
    int i = getOffset(paramLong);
    long l = paramLong + i;
    if (((paramLong ^ l) < 0L) && ((i ^ paramLong) >= 0L)) {
      throw new ArithmeticException("Adding time zone offset caused overflow");
    }
    return l;
  }
  
  public abstract boolean equals(Object paramObject);
  
  @ToString
  public final String getID()
  {
    return iID;
  }
  
  public long getMillisKeepLocal(DateTimeZone paramDateTimeZone, long paramLong)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = getDefault();
    }
    if (localDateTimeZone == this) {
      return paramLong;
    }
    return localDateTimeZone.convertLocalToUTC(convertUTCToLocal(paramLong), false, paramLong);
  }
  
  public final String getName(long paramLong)
  {
    return getName(paramLong, null);
  }
  
  public String getName(long paramLong, Locale paramLocale)
  {
    Object localObject = paramLocale;
    if (paramLocale == null) {
      localObject = Locale.getDefault();
    }
    paramLocale = getNameKey(paramLong);
    if (paramLocale == null) {
      paramLocale = iID;
    }
    do
    {
      return paramLocale;
      localObject = cNameProvider.getName((Locale)localObject, iID, paramLocale);
      paramLocale = (Locale)localObject;
    } while (localObject != null);
    return printOffset(getOffset(paramLong));
  }
  
  public abstract String getNameKey(long paramLong);
  
  public abstract int getOffset(long paramLong);
  
  public final int getOffset(ReadableInstant paramReadableInstant)
  {
    if (paramReadableInstant == null) {
      return getOffset(DateTimeUtils.currentTimeMillis());
    }
    return getOffset(paramReadableInstant.getMillis());
  }
  
  public int getOffsetFromLocal(long paramLong)
  {
    int i = getOffset(paramLong);
    long l = paramLong - i;
    int j = getOffset(l);
    if (i != j)
    {
      if ((i - j < 0) && (nextTransition(l) != nextTransition(paramLong - j))) {
        return i;
      }
    }
    else if (i >= 0)
    {
      paramLong = previousTransition(l);
      if (paramLong < l)
      {
        int k = getOffset(paramLong);
        if (l - paramLong <= k - i) {
          return k;
        }
      }
    }
    return j;
  }
  
  public final String getShortName(long paramLong)
  {
    return getShortName(paramLong, null);
  }
  
  public String getShortName(long paramLong, Locale paramLocale)
  {
    Object localObject = paramLocale;
    if (paramLocale == null) {
      localObject = Locale.getDefault();
    }
    paramLocale = getNameKey(paramLong);
    if (paramLocale == null) {
      paramLocale = iID;
    }
    do
    {
      return paramLocale;
      localObject = cNameProvider.getShortName((Locale)localObject, iID, paramLocale);
      paramLocale = (Locale)localObject;
    } while (localObject != null);
    return printOffset(getOffset(paramLong));
  }
  
  public abstract int getStandardOffset(long paramLong);
  
  public int hashCode()
  {
    return getID().hashCode() + 57;
  }
  
  public abstract boolean isFixed();
  
  public boolean isLocalDateTimeGap(LocalDateTime paramLocalDateTime)
  {
    if (isFixed()) {
      return false;
    }
    try
    {
      paramLocalDateTime.toDateTime(this);
      return false;
    }
    catch (IllegalInstantException paramLocalDateTime) {}
    return true;
  }
  
  public boolean isStandardOffset(long paramLong)
  {
    return getOffset(paramLong) == getStandardOffset(paramLong);
  }
  
  public abstract long nextTransition(long paramLong);
  
  public abstract long previousTransition(long paramLong);
  
  public String toString()
  {
    return getID();
  }
  
  public TimeZone toTimeZone()
  {
    return TimeZone.getTimeZone(iID);
  }
  
  protected Object writeReplace()
    throws ObjectStreamException
  {
    return new Stub(iID);
  }
  
  private static final class Stub
    implements Serializable
  {
    private static final long serialVersionUID = -6471952376487863581L;
    private transient String iID;
    
    Stub(String paramString)
    {
      iID = paramString;
    }
    
    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException
    {
      iID = paramObjectInputStream.readUTF();
    }
    
    private Object readResolve()
      throws ObjectStreamException
    {
      return DateTimeZone.forID(iID);
    }
    
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeUTF(iID);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.DateTimeZone
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */