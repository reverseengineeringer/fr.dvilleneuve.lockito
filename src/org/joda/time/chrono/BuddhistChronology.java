package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationFieldType;
import org.joda.time.field.DelegatedDateTimeField;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.SkipUndoDateTimeField;
import org.joda.time.field.UnsupportedDurationField;

public final class BuddhistChronology
  extends AssembledChronology
{
  public static final int BE = 1;
  private static final int BUDDHIST_OFFSET = 543;
  private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("BE");
  private static final BuddhistChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
  private static final Map<DateTimeZone, BuddhistChronology> cCache = new HashMap();
  private static final long serialVersionUID = -3474595157769370126L;
  
  private BuddhistChronology(Chronology paramChronology, Object paramObject)
  {
    super(paramChronology, paramObject);
  }
  
  public static BuddhistChronology getInstance()
  {
    return getInstance(DateTimeZone.getDefault());
  }
  
  /* Error */
  public static BuddhistChronology getInstance(DateTimeZone paramDateTimeZone)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: astore_1
    //   5: aload_0
    //   6: ifnonnull +7 -> 13
    //   9: invokestatic 58	org/joda/time/DateTimeZone:getDefault	()Lorg/joda/time/DateTimeZone;
    //   12: astore_1
    //   13: getstatic 37	org/joda/time/chrono/BuddhistChronology:cCache	Ljava/util/Map;
    //   16: astore_3
    //   17: aload_3
    //   18: monitorenter
    //   19: getstatic 37	org/joda/time/chrono/BuddhistChronology:cCache	Ljava/util/Map;
    //   22: aload_1
    //   23: invokeinterface 64 2 0
    //   28: checkcast 2	org/joda/time/chrono/BuddhistChronology
    //   31: astore_2
    //   32: aload_2
    //   33: astore_0
    //   34: aload_2
    //   35: ifnonnull +58 -> 93
    //   38: new 2	org/joda/time/chrono/BuddhistChronology
    //   41: dup
    //   42: aload_1
    //   43: aconst_null
    //   44: invokestatic 69	org/joda/time/chrono/GJChronology:getInstance	(Lorg/joda/time/DateTimeZone;Lorg/joda/time/ReadableInstant;)Lorg/joda/time/chrono/GJChronology;
    //   47: aconst_null
    //   48: invokespecial 70	org/joda/time/chrono/BuddhistChronology:<init>	(Lorg/joda/time/Chronology;Ljava/lang/Object;)V
    //   51: astore_0
    //   52: new 2	org/joda/time/chrono/BuddhistChronology
    //   55: dup
    //   56: aload_0
    //   57: new 72	org/joda/time/DateTime
    //   60: dup
    //   61: iconst_1
    //   62: iconst_1
    //   63: iconst_1
    //   64: iconst_0
    //   65: iconst_0
    //   66: iconst_0
    //   67: iconst_0
    //   68: aload_0
    //   69: invokespecial 75	org/joda/time/DateTime:<init>	(IIIIIIILorg/joda/time/Chronology;)V
    //   72: aconst_null
    //   73: invokestatic 80	org/joda/time/chrono/LimitChronology:getInstance	(Lorg/joda/time/Chronology;Lorg/joda/time/ReadableDateTime;Lorg/joda/time/ReadableDateTime;)Lorg/joda/time/chrono/LimitChronology;
    //   76: ldc 82
    //   78: invokespecial 70	org/joda/time/chrono/BuddhistChronology:<init>	(Lorg/joda/time/Chronology;Ljava/lang/Object;)V
    //   81: astore_0
    //   82: getstatic 37	org/joda/time/chrono/BuddhistChronology:cCache	Ljava/util/Map;
    //   85: aload_1
    //   86: aload_0
    //   87: invokeinterface 86 3 0
    //   92: pop
    //   93: aload_3
    //   94: monitorexit
    //   95: ldc 2
    //   97: monitorexit
    //   98: aload_0
    //   99: areturn
    //   100: astore_0
    //   101: aload_3
    //   102: monitorexit
    //   103: aload_0
    //   104: athrow
    //   105: astore_0
    //   106: ldc 2
    //   108: monitorexit
    //   109: aload_0
    //   110: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	paramDateTimeZone	DateTimeZone
    //   4	82	1	localDateTimeZone	DateTimeZone
    //   31	4	2	localBuddhistChronology	BuddhistChronology
    // Exception table:
    //   from	to	target	type
    //   19	32	100	finally
    //   38	93	100	finally
    //   93	95	100	finally
    //   101	103	100	finally
    //   9	13	105	finally
    //   13	19	105	finally
    //   103	105	105	finally
  }
  
  public static BuddhistChronology getInstanceUTC()
  {
    return INSTANCE_UTC;
  }
  
  private Object readResolve()
  {
    Chronology localChronology = getBase();
    if (localChronology == null) {
      return getInstanceUTC();
    }
    return getInstance(localChronology.getZone());
  }
  
  protected void assemble(AssembledChronology.Fields paramFields)
  {
    if (getParam() == null)
    {
      eras = UnsupportedDurationField.getInstance(DurationFieldType.eras());
      year = new OffsetDateTimeField(new SkipUndoDateTimeField(this, year), 543);
      DateTimeField localDateTimeField = yearOfEra;
      yearOfEra = new DelegatedDateTimeField(year, eras, DateTimeFieldType.yearOfEra());
      weekyear = new OffsetDateTimeField(new SkipUndoDateTimeField(this, weekyear), 543);
      centuryOfEra = new DividedDateTimeField(new OffsetDateTimeField(yearOfEra, 99), eras, DateTimeFieldType.centuryOfEra(), 100);
      centuries = centuryOfEra.getDurationField();
      yearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField((DividedDateTimeField)centuryOfEra), DateTimeFieldType.yearOfCentury(), 1);
      weekyearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField(weekyear, centuries, DateTimeFieldType.weekyearOfCentury(), 100), DateTimeFieldType.weekyearOfCentury(), 1);
      era = ERA_FIELD;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof BuddhistChronology))
    {
      paramObject = (BuddhistChronology)paramObject;
      return getZone().equals(((BuddhistChronology)paramObject).getZone());
    }
    return false;
  }
  
  public int hashCode()
  {
    return "Buddhist".hashCode() * 11 + getZone().hashCode();
  }
  
  public String toString()
  {
    String str = "BuddhistChronology";
    DateTimeZone localDateTimeZone = getZone();
    if (localDateTimeZone != null) {
      str = "BuddhistChronology" + '[' + localDateTimeZone.getID() + ']';
    }
    return str;
  }
  
  public Chronology withUTC()
  {
    return INSTANCE_UTC;
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
    return getInstance(localDateTimeZone);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.chrono.BuddhistChronology
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */