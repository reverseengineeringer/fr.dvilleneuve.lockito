package org.joda.time.chrono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Instant;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class GJChronology
  extends AssembledChronology
{
  static final Instant DEFAULT_CUTOVER = new Instant(-12219292800000L);
  private static final Map<DateTimeZone, ArrayList<GJChronology>> cCache = new HashMap();
  private static final long serialVersionUID = -2545574827706931671L;
  private Instant iCutoverInstant;
  private long iCutoverMillis;
  private long iGapDuration;
  private GregorianChronology iGregorianChronology;
  private JulianChronology iJulianChronology;
  
  private GJChronology(Chronology paramChronology, JulianChronology paramJulianChronology, GregorianChronology paramGregorianChronology, Instant paramInstant)
  {
    super(paramChronology, new Object[] { paramJulianChronology, paramGregorianChronology, paramInstant });
  }
  
  private GJChronology(JulianChronology paramJulianChronology, GregorianChronology paramGregorianChronology, Instant paramInstant)
  {
    super(null, new Object[] { paramJulianChronology, paramGregorianChronology, paramInstant });
  }
  
  private static long convertByWeekyear(long paramLong, Chronology paramChronology1, Chronology paramChronology2)
  {
    long l = paramChronology2.weekyear().set(0L, paramChronology1.weekyear().get(paramLong));
    l = paramChronology2.weekOfWeekyear().set(l, paramChronology1.weekOfWeekyear().get(paramLong));
    l = paramChronology2.dayOfWeek().set(l, paramChronology1.dayOfWeek().get(paramLong));
    return paramChronology2.millisOfDay().set(l, paramChronology1.millisOfDay().get(paramLong));
  }
  
  private static long convertByYear(long paramLong, Chronology paramChronology1, Chronology paramChronology2)
  {
    return paramChronology2.getDateTimeMillis(paramChronology1.year().get(paramLong), paramChronology1.monthOfYear().get(paramLong), paramChronology1.dayOfMonth().get(paramLong), paramChronology1.millisOfDay().get(paramLong));
  }
  
  public static GJChronology getInstance()
  {
    return getInstance(DateTimeZone.getDefault(), DEFAULT_CUTOVER, 4);
  }
  
  public static GJChronology getInstance(DateTimeZone paramDateTimeZone)
  {
    return getInstance(paramDateTimeZone, DEFAULT_CUTOVER, 4);
  }
  
  public static GJChronology getInstance(DateTimeZone paramDateTimeZone, long paramLong, int paramInt)
  {
    if (paramLong == DEFAULT_CUTOVER.getMillis()) {}
    for (Object localObject = null;; localObject = new Instant(paramLong)) {
      return getInstance(paramDateTimeZone, (ReadableInstant)localObject, paramInt);
    }
  }
  
  public static GJChronology getInstance(DateTimeZone paramDateTimeZone, ReadableInstant paramReadableInstant)
  {
    return getInstance(paramDateTimeZone, paramReadableInstant, 4);
  }
  
  /* Error */
  public static GJChronology getInstance(DateTimeZone paramDateTimeZone, ReadableInstant paramReadableInstant, int paramInt)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 128	org/joda/time/DateTimeUtils:getZone	(Lorg/joda/time/DateTimeZone;)Lorg/joda/time/DateTimeZone;
    //   7: astore 7
    //   9: aload_1
    //   10: ifnonnull +99 -> 109
    //   13: getstatic 41	org/joda/time/chrono/GJChronology:DEFAULT_CUTOVER	Lorg/joda/time/Instant;
    //   16: astore_0
    //   17: getstatic 47	org/joda/time/chrono/GJChronology:cCache	Ljava/util/Map;
    //   20: astore 6
    //   22: aload 6
    //   24: monitorenter
    //   25: getstatic 47	org/joda/time/chrono/GJChronology:cCache	Ljava/util/Map;
    //   28: aload 7
    //   30: invokeinterface 133 2 0
    //   35: checkcast 135	java/util/ArrayList
    //   38: astore 5
    //   40: aload 5
    //   42: ifnonnull +114 -> 156
    //   45: new 135	java/util/ArrayList
    //   48: dup
    //   49: iconst_2
    //   50: invokespecial 138	java/util/ArrayList:<init>	(I)V
    //   53: astore_1
    //   54: getstatic 47	org/joda/time/chrono/GJChronology:cCache	Ljava/util/Map;
    //   57: aload 7
    //   59: aload_1
    //   60: invokeinterface 142 3 0
    //   65: pop
    //   66: aload 7
    //   68: getstatic 146	org/joda/time/DateTimeZone:UTC	Lorg/joda/time/DateTimeZone;
    //   71: if_acmpne +148 -> 219
    //   74: new 2	org/joda/time/chrono/GJChronology
    //   77: dup
    //   78: aload 7
    //   80: iload_2
    //   81: invokestatic 151	org/joda/time/chrono/JulianChronology:getInstance	(Lorg/joda/time/DateTimeZone;I)Lorg/joda/time/chrono/JulianChronology;
    //   84: aload 7
    //   86: iload_2
    //   87: invokestatic 156	org/joda/time/chrono/GregorianChronology:getInstance	(Lorg/joda/time/DateTimeZone;I)Lorg/joda/time/chrono/GregorianChronology;
    //   90: aload_0
    //   91: invokespecial 158	org/joda/time/chrono/GJChronology:<init>	(Lorg/joda/time/chrono/JulianChronology;Lorg/joda/time/chrono/GregorianChronology;Lorg/joda/time/Instant;)V
    //   94: astore_0
    //   95: aload_1
    //   96: aload_0
    //   97: invokevirtual 162	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   100: pop
    //   101: aload 6
    //   103: monitorexit
    //   104: ldc 2
    //   106: monitorexit
    //   107: aload_0
    //   108: areturn
    //   109: aload_1
    //   110: invokeinterface 168 1 0
    //   115: astore_1
    //   116: aload_1
    //   117: astore_0
    //   118: new 170	org/joda/time/LocalDate
    //   121: dup
    //   122: aload_1
    //   123: invokevirtual 121	org/joda/time/Instant:getMillis	()J
    //   126: aload 7
    //   128: invokestatic 173	org/joda/time/chrono/GregorianChronology:getInstance	(Lorg/joda/time/DateTimeZone;)Lorg/joda/time/chrono/GregorianChronology;
    //   131: invokespecial 176	org/joda/time/LocalDate:<init>	(JLorg/joda/time/Chronology;)V
    //   134: invokevirtual 180	org/joda/time/LocalDate:getYear	()I
    //   137: ifgt -120 -> 17
    //   140: new 182	java/lang/IllegalArgumentException
    //   143: dup
    //   144: ldc -72
    //   146: invokespecial 187	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   149: athrow
    //   150: astore_0
    //   151: ldc 2
    //   153: monitorexit
    //   154: aload_0
    //   155: athrow
    //   156: aload 5
    //   158: invokevirtual 190	java/util/ArrayList:size	()I
    //   161: istore_3
    //   162: iload_3
    //   163: iconst_1
    //   164: isub
    //   165: istore 4
    //   167: aload 5
    //   169: astore_1
    //   170: iload 4
    //   172: iflt -106 -> 66
    //   175: aload 5
    //   177: iload 4
    //   179: invokevirtual 193	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   182: checkcast 2	org/joda/time/chrono/GJChronology
    //   185: astore_1
    //   186: iload 4
    //   188: istore_3
    //   189: iload_2
    //   190: aload_1
    //   191: invokevirtual 196	org/joda/time/chrono/GJChronology:getMinimumDaysInFirstWeek	()I
    //   194: if_icmpne -32 -> 162
    //   197: iload 4
    //   199: istore_3
    //   200: aload_0
    //   201: aload_1
    //   202: invokevirtual 199	org/joda/time/chrono/GJChronology:getGregorianCutover	()Lorg/joda/time/Instant;
    //   205: invokevirtual 202	org/joda/time/Instant:equals	(Ljava/lang/Object;)Z
    //   208: ifeq -46 -> 162
    //   211: aload 6
    //   213: monitorexit
    //   214: aload_1
    //   215: astore_0
    //   216: goto -112 -> 104
    //   219: getstatic 146	org/joda/time/DateTimeZone:UTC	Lorg/joda/time/DateTimeZone;
    //   222: aload_0
    //   223: iload_2
    //   224: invokestatic 115	org/joda/time/chrono/GJChronology:getInstance	(Lorg/joda/time/DateTimeZone;Lorg/joda/time/ReadableInstant;I)Lorg/joda/time/chrono/GJChronology;
    //   227: astore_0
    //   228: new 2	org/joda/time/chrono/GJChronology
    //   231: dup
    //   232: aload_0
    //   233: aload 7
    //   235: invokestatic 207	org/joda/time/chrono/ZonedChronology:getInstance	(Lorg/joda/time/Chronology;Lorg/joda/time/DateTimeZone;)Lorg/joda/time/chrono/ZonedChronology;
    //   238: aload_0
    //   239: getfield 209	org/joda/time/chrono/GJChronology:iJulianChronology	Lorg/joda/time/chrono/JulianChronology;
    //   242: aload_0
    //   243: getfield 63	org/joda/time/chrono/GJChronology:iGregorianChronology	Lorg/joda/time/chrono/GregorianChronology;
    //   246: aload_0
    //   247: getfield 211	org/joda/time/chrono/GJChronology:iCutoverInstant	Lorg/joda/time/Instant;
    //   250: invokespecial 213	org/joda/time/chrono/GJChronology:<init>	(Lorg/joda/time/Chronology;Lorg/joda/time/chrono/JulianChronology;Lorg/joda/time/chrono/GregorianChronology;Lorg/joda/time/Instant;)V
    //   253: astore_0
    //   254: goto -159 -> 95
    //   257: astore_0
    //   258: aload 6
    //   260: monitorexit
    //   261: aload_0
    //   262: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	263	0	paramDateTimeZone	DateTimeZone
    //   0	263	1	paramReadableInstant	ReadableInstant
    //   0	263	2	paramInt	int
    //   161	39	3	i	int
    //   165	33	4	j	int
    //   38	138	5	localArrayList	ArrayList
    //   7	227	7	localDateTimeZone	DateTimeZone
    // Exception table:
    //   from	to	target	type
    //   3	9	150	finally
    //   13	17	150	finally
    //   17	25	150	finally
    //   109	116	150	finally
    //   118	150	150	finally
    //   261	263	150	finally
    //   25	40	257	finally
    //   45	66	257	finally
    //   66	95	257	finally
    //   95	104	257	finally
    //   156	162	257	finally
    //   175	186	257	finally
    //   189	197	257	finally
    //   200	214	257	finally
    //   219	254	257	finally
    //   258	261	257	finally
  }
  
  public static GJChronology getInstanceUTC()
  {
    return getInstance(DateTimeZone.UTC, DEFAULT_CUTOVER, 4);
  }
  
  private Object readResolve()
  {
    return getInstance(getZone(), iCutoverInstant, getMinimumDaysInFirstWeek());
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    Object localObject2 = (Object[])getParam();
    Object localObject1 = (JulianChronology)localObject2[0];
    GregorianChronology localGregorianChronology = (GregorianChronology)localObject2[1];
    localObject2 = (Instant)localObject2[2];
    iCutoverMillis = ((Instant)localObject2).getMillis();
    iJulianChronology = ((JulianChronology)localObject1);
    iGregorianChronology = localGregorianChronology;
    iCutoverInstant = ((Instant)localObject2);
    if (getBase() != null) {
      return;
    }
    if (((JulianChronology)localObject1).getMinimumDaysInFirstWeek() != localGregorianChronology.getMinimumDaysInFirstWeek()) {
      throw new IllegalArgumentException();
    }
    iGapDuration = (iCutoverMillis - julianToGregorianByYear(iCutoverMillis));
    paramFields.copyFieldsFrom(localGregorianChronology);
    if (localGregorianChronology.millisOfDay().get(iCutoverMillis) == 0)
    {
      millisOfSecond = new CutoverField(((JulianChronology)localObject1).millisOfSecond(), millisOfSecond, iCutoverMillis);
      millisOfDay = new CutoverField(((JulianChronology)localObject1).millisOfDay(), millisOfDay, iCutoverMillis);
      secondOfMinute = new CutoverField(((JulianChronology)localObject1).secondOfMinute(), secondOfMinute, iCutoverMillis);
      secondOfDay = new CutoverField(((JulianChronology)localObject1).secondOfDay(), secondOfDay, iCutoverMillis);
      minuteOfHour = new CutoverField(((JulianChronology)localObject1).minuteOfHour(), minuteOfHour, iCutoverMillis);
      minuteOfDay = new CutoverField(((JulianChronology)localObject1).minuteOfDay(), minuteOfDay, iCutoverMillis);
      hourOfDay = new CutoverField(((JulianChronology)localObject1).hourOfDay(), hourOfDay, iCutoverMillis);
      hourOfHalfday = new CutoverField(((JulianChronology)localObject1).hourOfHalfday(), hourOfHalfday, iCutoverMillis);
      clockhourOfDay = new CutoverField(((JulianChronology)localObject1).clockhourOfDay(), clockhourOfDay, iCutoverMillis);
      clockhourOfHalfday = new CutoverField(((JulianChronology)localObject1).clockhourOfHalfday(), clockhourOfHalfday, iCutoverMillis);
      halfdayOfDay = new CutoverField(((JulianChronology)localObject1).halfdayOfDay(), halfdayOfDay, iCutoverMillis);
    }
    era = new CutoverField(((JulianChronology)localObject1).era(), era, iCutoverMillis);
    year = new ImpreciseCutoverField(((JulianChronology)localObject1).year(), year, iCutoverMillis);
    years = year.getDurationField();
    yearOfEra = new ImpreciseCutoverField(((JulianChronology)localObject1).yearOfEra(), yearOfEra, years, iCutoverMillis);
    centuryOfEra = new ImpreciseCutoverField(((JulianChronology)localObject1).centuryOfEra(), centuryOfEra, iCutoverMillis);
    centuries = centuryOfEra.getDurationField();
    yearOfCentury = new ImpreciseCutoverField(((JulianChronology)localObject1).yearOfCentury(), yearOfCentury, years, centuries, iCutoverMillis);
    monthOfYear = new ImpreciseCutoverField(((JulianChronology)localObject1).monthOfYear(), monthOfYear, null, years, iCutoverMillis);
    months = monthOfYear.getDurationField();
    weekyear = new ImpreciseCutoverField(((JulianChronology)localObject1).weekyear(), weekyear, null, iCutoverMillis, true);
    weekyears = weekyear.getDurationField();
    weekyearOfCentury = new ImpreciseCutoverField(((JulianChronology)localObject1).weekyearOfCentury(), weekyearOfCentury, weekyears, centuries, iCutoverMillis);
    long l = localGregorianChronology.year().roundCeiling(iCutoverMillis);
    dayOfYear = new CutoverField(((JulianChronology)localObject1).dayOfYear(), dayOfYear, years, l, false);
    l = localGregorianChronology.weekyear().roundCeiling(iCutoverMillis);
    weekOfWeekyear = new CutoverField(((JulianChronology)localObject1).weekOfWeekyear(), weekOfWeekyear, weekyears, l, true);
    localObject1 = new CutoverField(((JulianChronology)localObject1).dayOfMonth(), dayOfMonth, iCutoverMillis);
    iRangeDurationField = months;
    dayOfMonth = ((DateTimeField)localObject1);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof GJChronology)) {
        break;
      }
      paramObject = (GJChronology)paramObject;
    } while ((iCutoverMillis == iCutoverMillis) && (getMinimumDaysInFirstWeek() == ((GJChronology)paramObject).getMinimumDaysInFirstWeek()) && (getZone().equals(((GJChronology)paramObject).getZone())));
    return false;
    return false;
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    Chronology localChronology = getBase();
    long l1;
    if (localChronology != null) {
      l1 = localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    long l2;
    do
    {
      do
      {
        return l1;
        l2 = iGregorianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
        l1 = l2;
      } while (l2 >= iCutoverMillis);
      l2 = iJulianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4);
      l1 = l2;
    } while (l2 < iCutoverMillis);
    throw new IllegalArgumentException("Specified date does not exist");
  }
  
  public long getDateTimeMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    throws IllegalArgumentException
  {
    Chronology localChronology = getBase();
    long l1;
    if (localChronology != null) {
      l1 = localChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
    }
    for (;;)
    {
      return l1;
      try
      {
        l2 = iGregorianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
        l1 = l2;
        if (l2 >= iCutoverMillis) {
          continue;
        }
        l2 = iJulianChronology.getDateTimeMillis(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
        l1 = l2;
        if (l2 < iCutoverMillis) {
          continue;
        }
        throw new IllegalArgumentException("Specified date does not exist");
      }
      catch (IllegalFieldValueException localIllegalFieldValueException)
      {
        do
        {
          if ((paramInt2 != 2) || (paramInt3 != 29)) {
            throw localIllegalFieldValueException;
          }
          l1 = iGregorianChronology.getDateTimeMillis(paramInt1, paramInt2, 28, paramInt4, paramInt5, paramInt6, paramInt7);
          long l2 = l1;
        } while (l1 < iCutoverMillis);
        throw localIllegalFieldValueException;
      }
    }
  }
  
  public Instant getGregorianCutover()
  {
    return iCutoverInstant;
  }
  
  public int getMinimumDaysInFirstWeek()
  {
    return iGregorianChronology.getMinimumDaysInFirstWeek();
  }
  
  public DateTimeZone getZone()
  {
    Chronology localChronology = getBase();
    if (localChronology != null) {
      return localChronology.getZone();
    }
    return DateTimeZone.UTC;
  }
  
  long gregorianToJulianByWeekyear(long paramLong)
  {
    return convertByWeekyear(paramLong, iGregorianChronology, iJulianChronology);
  }
  
  long gregorianToJulianByYear(long paramLong)
  {
    return convertByYear(paramLong, iGregorianChronology, iJulianChronology);
  }
  
  public int hashCode()
  {
    return "GJ".hashCode() * 11 + getZone().hashCode() + getMinimumDaysInFirstWeek() + iCutoverInstant.hashCode();
  }
  
  long julianToGregorianByWeekyear(long paramLong)
  {
    return convertByWeekyear(paramLong, iJulianChronology, iGregorianChronology);
  }
  
  long julianToGregorianByYear(long paramLong)
  {
    return convertByYear(paramLong, iJulianChronology, iGregorianChronology);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(60);
    localStringBuffer.append("GJChronology");
    localStringBuffer.append('[');
    localStringBuffer.append(getZone().getID());
    if (iCutoverMillis != DEFAULT_CUTOVER.getMillis())
    {
      localStringBuffer.append(",cutover=");
      if (withUTC().dayOfYear().remainder(iCutoverMillis) != 0L) {
        break label135;
      }
    }
    label135:
    for (DateTimeFormatter localDateTimeFormatter = ISODateTimeFormat.date();; localDateTimeFormatter = ISODateTimeFormat.dateTime())
    {
      localDateTimeFormatter.withChronology(withUTC()).printTo(localStringBuffer, iCutoverMillis);
      if (getMinimumDaysInFirstWeek() != 4)
      {
        localStringBuffer.append(",mdfw=");
        localStringBuffer.append(getMinimumDaysInFirstWeek());
      }
      localStringBuffer.append(']');
      return localStringBuffer.toString();
    }
  }
  
  public Chronology withUTC()
  {
    return withZone(DateTimeZone.UTC);
  }
  
  public Chronology withZone(DateTimeZone paramDateTimeZone)
  {
    DateTimeZone localDateTimeZone = paramDateTimeZone;
    if (paramDateTimeZone == null) {
      localDateTimeZone = DateTimeZone.getDefault();
    }
    if (localDateTimeZone == getZone()) {
      return this;
    }
    return getInstance(localDateTimeZone, iCutoverInstant, getMinimumDaysInFirstWeek());
  }
  
  private class CutoverField
    extends BaseDateTimeField
  {
    private static final long serialVersionUID = 3528501219481026402L;
    final boolean iConvertByWeekyear;
    final long iCutover;
    protected DurationField iDurationField;
    final DateTimeField iGregorianField;
    final DateTimeField iJulianField;
    protected DurationField iRangeDurationField;
    
    CutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong)
    {
      this(paramDateTimeField1, paramDateTimeField2, paramLong, false);
    }
    
    CutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong, boolean paramBoolean)
    {
      this(paramDateTimeField1, paramDateTimeField2, null, paramLong, paramBoolean);
    }
    
    CutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong, boolean paramBoolean)
    {
      super();
      iJulianField = paramDateTimeField1;
      iGregorianField = paramDateTimeField2;
      iCutover = paramLong;
      iConvertByWeekyear = paramBoolean;
      iDurationField = paramDateTimeField2.getDurationField();
      this$1 = paramDurationField;
      if (paramDurationField == null)
      {
        paramDateTimeField2 = paramDateTimeField2.getRangeDurationField();
        this$1 = paramDateTimeField2;
        if (paramDateTimeField2 == null) {
          this$1 = paramDateTimeField1.getRangeDurationField();
        }
      }
      iRangeDurationField = GJChronology.this;
    }
    
    public long add(long paramLong, int paramInt)
    {
      return iGregorianField.add(paramLong, paramInt);
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      return iGregorianField.add(paramLong1, paramLong2);
    }
    
    public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
    {
      if (paramInt2 == 0) {
        return paramArrayOfInt;
      }
      if (DateTimeUtils.isContiguous(paramReadablePartial))
      {
        long l = 0L;
        paramInt1 = 0;
        int i = paramReadablePartial.size();
        while (paramInt1 < i)
        {
          l = paramReadablePartial.getFieldType(paramInt1).getField(GJChronology.this).set(l, paramArrayOfInt[paramInt1]);
          paramInt1 += 1;
        }
        l = add(l, paramInt2);
        return get(paramReadablePartial, l);
      }
      return super.add(paramReadablePartial, paramInt1, paramArrayOfInt, paramInt2);
    }
    
    public int get(long paramLong)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.get(paramLong);
      }
      return iJulianField.get(paramLong);
    }
    
    public String getAsShortText(int paramInt, Locale paramLocale)
    {
      return iGregorianField.getAsShortText(paramInt, paramLocale);
    }
    
    public String getAsShortText(long paramLong, Locale paramLocale)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.getAsShortText(paramLong, paramLocale);
      }
      return iJulianField.getAsShortText(paramLong, paramLocale);
    }
    
    public String getAsText(int paramInt, Locale paramLocale)
    {
      return iGregorianField.getAsText(paramInt, paramLocale);
    }
    
    public String getAsText(long paramLong, Locale paramLocale)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.getAsText(paramLong, paramLocale);
      }
      return iJulianField.getAsText(paramLong, paramLocale);
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      return iGregorianField.getDifference(paramLong1, paramLong2);
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      return iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
    }
    
    public DurationField getDurationField()
    {
      return iDurationField;
    }
    
    public int getLeapAmount(long paramLong)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.getLeapAmount(paramLong);
      }
      return iJulianField.getLeapAmount(paramLong);
    }
    
    public DurationField getLeapDurationField()
    {
      return iGregorianField.getLeapDurationField();
    }
    
    public int getMaximumShortTextLength(Locale paramLocale)
    {
      return Math.max(iJulianField.getMaximumShortTextLength(paramLocale), iGregorianField.getMaximumShortTextLength(paramLocale));
    }
    
    public int getMaximumTextLength(Locale paramLocale)
    {
      return Math.max(iJulianField.getMaximumTextLength(paramLocale), iGregorianField.getMaximumTextLength(paramLocale));
    }
    
    public int getMaximumValue()
    {
      return iGregorianField.getMaximumValue();
    }
    
    public int getMaximumValue(long paramLong)
    {
      int i;
      if (paramLong >= iCutover) {
        i = iGregorianField.getMaximumValue(paramLong);
      }
      int j;
      do
      {
        return i;
        j = iJulianField.getMaximumValue(paramLong);
        i = j;
      } while (iJulianField.set(paramLong, j) < iCutover);
      return iJulianField.get(iJulianField.add(iCutover, -1));
    }
    
    public int getMaximumValue(ReadablePartial paramReadablePartial)
    {
      return getMaximumValue(GJChronology.getInstanceUTC().set(paramReadablePartial, 0L));
    }
    
    public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
    {
      GJChronology localGJChronology = GJChronology.getInstanceUTC();
      long l1 = 0L;
      int i = 0;
      int j = paramReadablePartial.size();
      while (i < j)
      {
        DateTimeField localDateTimeField = paramReadablePartial.getFieldType(i).getField(localGJChronology);
        long l2 = l1;
        if (paramArrayOfInt[i] <= localDateTimeField.getMaximumValue(l1)) {
          l2 = localDateTimeField.set(l1, paramArrayOfInt[i]);
        }
        i += 1;
        l1 = l2;
      }
      return getMaximumValue(l1);
    }
    
    public int getMinimumValue()
    {
      return iJulianField.getMinimumValue();
    }
    
    public int getMinimumValue(long paramLong)
    {
      int i;
      if (paramLong < iCutover) {
        i = iJulianField.getMinimumValue(paramLong);
      }
      int j;
      do
      {
        return i;
        j = iGregorianField.getMinimumValue(paramLong);
        i = j;
      } while (iGregorianField.set(paramLong, j) >= iCutover);
      return iGregorianField.get(iCutover);
    }
    
    public int getMinimumValue(ReadablePartial paramReadablePartial)
    {
      return iJulianField.getMinimumValue(paramReadablePartial);
    }
    
    public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
    {
      return iJulianField.getMinimumValue(paramReadablePartial, paramArrayOfInt);
    }
    
    public DurationField getRangeDurationField()
    {
      return iRangeDurationField;
    }
    
    protected long gregorianToJulian(long paramLong)
    {
      if (iConvertByWeekyear) {
        return gregorianToJulianByWeekyear(paramLong);
      }
      return gregorianToJulianByYear(paramLong);
    }
    
    public boolean isLeap(long paramLong)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.isLeap(paramLong);
      }
      return iJulianField.isLeap(paramLong);
    }
    
    public boolean isLenient()
    {
      return false;
    }
    
    protected long julianToGregorian(long paramLong)
    {
      if (iConvertByWeekyear) {
        return julianToGregorianByWeekyear(paramLong);
      }
      return julianToGregorianByYear(paramLong);
    }
    
    public long roundCeiling(long paramLong)
    {
      if (paramLong >= iCutover) {
        paramLong = iGregorianField.roundCeiling(paramLong);
      }
      long l;
      do
      {
        do
        {
          return paramLong;
          l = iJulianField.roundCeiling(paramLong);
          paramLong = l;
        } while (l < iCutover);
        paramLong = l;
      } while (l - iGapDuration < iCutover);
      return julianToGregorian(l);
    }
    
    public long roundFloor(long paramLong)
    {
      if (paramLong >= iCutover)
      {
        long l = iGregorianField.roundFloor(paramLong);
        paramLong = l;
        if (l < iCutover)
        {
          paramLong = l;
          if (iGapDuration + l < iCutover) {
            paramLong = gregorianToJulian(l);
          }
        }
        return paramLong;
      }
      return iJulianField.roundFloor(paramLong);
    }
    
    public long set(long paramLong, int paramInt)
    {
      long l2;
      long l1;
      if (paramLong >= iCutover)
      {
        l2 = iGregorianField.set(paramLong, paramInt);
        paramLong = l2;
        if (l2 < iCutover)
        {
          l1 = l2;
          if (iGapDuration + l2 < iCutover) {
            l1 = gregorianToJulian(l2);
          }
          paramLong = l1;
          if (get(l1) != paramInt) {
            throw new IllegalFieldValueException(iGregorianField.getType(), Integer.valueOf(paramInt), null, null);
          }
        }
      }
      else
      {
        l2 = iJulianField.set(paramLong, paramInt);
        paramLong = l2;
        if (l2 >= iCutover)
        {
          l1 = l2;
          if (l2 - iGapDuration >= iCutover) {
            l1 = julianToGregorian(l2);
          }
          paramLong = l1;
          if (get(l1) != paramInt) {
            throw new IllegalFieldValueException(iJulianField.getType(), Integer.valueOf(paramInt), null, null);
          }
        }
      }
      return paramLong;
    }
    
    public long set(long paramLong, String paramString, Locale paramLocale)
    {
      long l;
      if (paramLong >= iCutover)
      {
        l = iGregorianField.set(paramLong, paramString, paramLocale);
        paramLong = l;
        if (l < iCutover)
        {
          paramLong = l;
          if (iGapDuration + l < iCutover) {
            paramLong = gregorianToJulian(l);
          }
        }
      }
      do
      {
        do
        {
          return paramLong;
          l = iJulianField.set(paramLong, paramString, paramLocale);
          paramLong = l;
        } while (l < iCutover);
        paramLong = l;
      } while (l - iGapDuration < iCutover);
      return julianToGregorian(l);
    }
  }
  
  private final class ImpreciseCutoverField
    extends GJChronology.CutoverField
  {
    private static final long serialVersionUID = 3410248757173576441L;
    
    ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, long paramLong)
    {
      this(paramDateTimeField1, paramDateTimeField2, null, paramLong, false);
    }
    
    ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong)
    {
      this(paramDateTimeField1, paramDateTimeField2, paramDurationField, paramLong, false);
    }
    
    ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField, long paramLong, boolean paramBoolean)
    {
      super(paramDateTimeField1, paramDateTimeField2, paramLong, paramBoolean);
      this$1 = paramDurationField;
      if (paramDurationField == null) {
        this$1 = new GJChronology.LinkedDurationField(iDurationField, this);
      }
      iDurationField = GJChronology.this;
    }
    
    ImpreciseCutoverField(DateTimeField paramDateTimeField1, DateTimeField paramDateTimeField2, DurationField paramDurationField1, DurationField paramDurationField2, long paramLong)
    {
      this(paramDateTimeField1, paramDateTimeField2, paramDurationField1, paramLong, false);
      iRangeDurationField = paramDurationField2;
    }
    
    public long add(long paramLong, int paramInt)
    {
      long l;
      if (paramLong >= iCutover)
      {
        l = iGregorianField.add(paramLong, paramInt);
        paramLong = l;
        if (l < iCutover)
        {
          paramLong = l;
          if (iGapDuration + l < iCutover)
          {
            if (!iConvertByWeekyear) {
              break label107;
            }
            paramLong = l;
            if (iGregorianChronology.weekyear().get(l) <= 0) {
              paramLong = iGregorianChronology.weekyear().add(l, -1);
            }
            paramLong = gregorianToJulian(paramLong);
          }
        }
      }
      label107:
      do
      {
        do
        {
          return paramLong;
          paramLong = l;
          if (iGregorianChronology.year().get(l) > 0) {
            break;
          }
          paramLong = iGregorianChronology.year().add(l, -1);
          break;
          l = iJulianField.add(paramLong, paramInt);
          paramLong = l;
        } while (l < iCutover);
        paramLong = l;
      } while (l - iGapDuration < iCutover);
      return julianToGregorian(l);
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      if (paramLong1 >= iCutover)
      {
        paramLong2 = iGregorianField.add(paramLong1, paramLong2);
        paramLong1 = paramLong2;
        if (paramLong2 < iCutover)
        {
          paramLong1 = paramLong2;
          if (iGapDuration + paramLong2 < iCutover)
          {
            if (!iConvertByWeekyear) {
              break label99;
            }
            paramLong1 = paramLong2;
            if (iGregorianChronology.weekyear().get(paramLong2) <= 0) {
              paramLong1 = iGregorianChronology.weekyear().add(paramLong2, -1);
            }
            paramLong1 = gregorianToJulian(paramLong1);
          }
        }
      }
      label99:
      do
      {
        do
        {
          return paramLong1;
          paramLong1 = paramLong2;
          if (iGregorianChronology.year().get(paramLong2) > 0) {
            break;
          }
          paramLong1 = iGregorianChronology.year().add(paramLong2, -1);
          break;
          paramLong2 = iJulianField.add(paramLong1, paramLong2);
          paramLong1 = paramLong2;
        } while (paramLong2 < iCutover);
        paramLong1 = paramLong2;
      } while (paramLong2 - iGapDuration < iCutover);
      return julianToGregorian(paramLong2);
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      if (paramLong1 >= iCutover)
      {
        if (paramLong2 >= iCutover) {
          return iGregorianField.getDifference(paramLong1, paramLong2);
        }
        paramLong1 = gregorianToJulian(paramLong1);
        return iJulianField.getDifference(paramLong1, paramLong2);
      }
      if (paramLong2 < iCutover) {
        return iJulianField.getDifference(paramLong1, paramLong2);
      }
      paramLong1 = julianToGregorian(paramLong1);
      return iGregorianField.getDifference(paramLong1, paramLong2);
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      if (paramLong1 >= iCutover)
      {
        if (paramLong2 >= iCutover) {
          return iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
        }
        paramLong1 = gregorianToJulian(paramLong1);
        return iJulianField.getDifferenceAsLong(paramLong1, paramLong2);
      }
      if (paramLong2 < iCutover) {
        return iJulianField.getDifferenceAsLong(paramLong1, paramLong2);
      }
      paramLong1 = julianToGregorian(paramLong1);
      return iGregorianField.getDifferenceAsLong(paramLong1, paramLong2);
    }
    
    public int getMaximumValue(long paramLong)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.getMaximumValue(paramLong);
      }
      return iJulianField.getMaximumValue(paramLong);
    }
    
    public int getMinimumValue(long paramLong)
    {
      if (paramLong >= iCutover) {
        return iGregorianField.getMinimumValue(paramLong);
      }
      return iJulianField.getMinimumValue(paramLong);
    }
  }
  
  private static class LinkedDurationField
    extends DecoratedDurationField
  {
    private static final long serialVersionUID = 4097975388007713084L;
    private final GJChronology.ImpreciseCutoverField iField;
    
    LinkedDurationField(DurationField paramDurationField, GJChronology.ImpreciseCutoverField paramImpreciseCutoverField)
    {
      super(paramDurationField.getType());
      iField = paramImpreciseCutoverField;
    }
    
    public long add(long paramLong, int paramInt)
    {
      return iField.add(paramLong, paramInt);
    }
    
    public long add(long paramLong1, long paramLong2)
    {
      return iField.add(paramLong1, paramLong2);
    }
    
    public int getDifference(long paramLong1, long paramLong2)
    {
      return iField.getDifference(paramLong1, paramLong2);
    }
    
    public long getDifferenceAsLong(long paramLong1, long paramLong2)
    {
      return iField.getDifferenceAsLong(paramLong1, paramLong2);
    }
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.GJChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */