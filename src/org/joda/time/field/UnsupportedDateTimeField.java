package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

public final class UnsupportedDateTimeField
  extends DateTimeField
  implements Serializable
{
  private static HashMap<DateTimeFieldType, UnsupportedDateTimeField> cCache;
  private static final long serialVersionUID = -1934618396111902255L;
  private final DurationField iDurationField;
  private final DateTimeFieldType iType;
  
  private UnsupportedDateTimeField(DateTimeFieldType paramDateTimeFieldType, DurationField paramDurationField)
  {
    if ((paramDateTimeFieldType == null) || (paramDurationField == null)) {
      throw new IllegalArgumentException();
    }
    iType = paramDateTimeFieldType;
    iDurationField = paramDurationField;
  }
  
  /* Error */
  public static UnsupportedDateTimeField getInstance(DateTimeFieldType paramDateTimeFieldType, DurationField paramDurationField)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 34	org/joda/time/field/UnsupportedDateTimeField:cCache	Ljava/util/HashMap;
    //   6: ifnonnull +47 -> 53
    //   9: new 36	java/util/HashMap
    //   12: dup
    //   13: bipush 7
    //   15: invokespecial 39	java/util/HashMap:<init>	(I)V
    //   18: putstatic 34	org/joda/time/field/UnsupportedDateTimeField:cCache	Ljava/util/HashMap;
    //   21: aconst_null
    //   22: astore_2
    //   23: aload_2
    //   24: astore_3
    //   25: aload_2
    //   26: ifnonnull +22 -> 48
    //   29: new 2	org/joda/time/field/UnsupportedDateTimeField
    //   32: dup
    //   33: aload_0
    //   34: aload_1
    //   35: invokespecial 41	org/joda/time/field/UnsupportedDateTimeField:<init>	(Lorg/joda/time/DateTimeFieldType;Lorg/joda/time/DurationField;)V
    //   38: astore_3
    //   39: getstatic 34	org/joda/time/field/UnsupportedDateTimeField:cCache	Ljava/util/HashMap;
    //   42: aload_0
    //   43: aload_3
    //   44: invokevirtual 45	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: ldc 2
    //   50: monitorexit
    //   51: aload_3
    //   52: areturn
    //   53: getstatic 34	org/joda/time/field/UnsupportedDateTimeField:cCache	Ljava/util/HashMap;
    //   56: aload_0
    //   57: invokevirtual 49	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   60: checkcast 2	org/joda/time/field/UnsupportedDateTimeField
    //   63: astore_3
    //   64: aload_3
    //   65: astore_2
    //   66: aload_3
    //   67: ifnull -44 -> 23
    //   70: aload_3
    //   71: invokevirtual 53	org/joda/time/field/UnsupportedDateTimeField:getDurationField	()Lorg/joda/time/DurationField;
    //   74: astore 4
    //   76: aload_3
    //   77: astore_2
    //   78: aload 4
    //   80: aload_1
    //   81: if_acmpeq -58 -> 23
    //   84: aconst_null
    //   85: astore_2
    //   86: goto -63 -> 23
    //   89: astore_0
    //   90: ldc 2
    //   92: monitorexit
    //   93: aload_0
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	paramDateTimeFieldType	DateTimeFieldType
    //   0	95	1	paramDurationField	DurationField
    //   22	64	2	localObject1	Object
    //   24	53	3	localObject2	Object
    //   74	5	4	localDurationField	DurationField
    // Exception table:
    //   from	to	target	type
    //   3	21	89	finally
    //   29	48	89	finally
    //   53	64	89	finally
    //   70	76	89	finally
  }
  
  private Object readResolve()
  {
    return getInstance(iType, iDurationField);
  }
  
  private UnsupportedOperationException unsupported()
  {
    return new UnsupportedOperationException(iType + " field is unsupported");
  }
  
  public long add(long paramLong, int paramInt)
  {
    return getDurationField().add(paramLong, paramInt);
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    return getDurationField().add(paramLong1, paramLong2);
  }
  
  public int[] add(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    throw unsupported();
  }
  
  public long addWrapField(long paramLong, int paramInt)
  {
    throw unsupported();
  }
  
  public int[] addWrapField(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    throw unsupported();
  }
  
  public int[] addWrapPartial(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    throw unsupported();
  }
  
  public int get(long paramLong)
  {
    throw unsupported();
  }
  
  public String getAsShortText(int paramInt, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsShortText(long paramLong)
  {
    throw unsupported();
  }
  
  public String getAsShortText(long paramLong, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsShortText(ReadablePartial paramReadablePartial, int paramInt, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsShortText(ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsText(int paramInt, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsText(long paramLong)
  {
    throw unsupported();
  }
  
  public String getAsText(long paramLong, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsText(ReadablePartial paramReadablePartial, int paramInt, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String getAsText(ReadablePartial paramReadablePartial, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    return getDurationField().getDifference(paramLong1, paramLong2);
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    return getDurationField().getDifferenceAsLong(paramLong1, paramLong2);
  }
  
  public DurationField getDurationField()
  {
    return iDurationField;
  }
  
  public int getLeapAmount(long paramLong)
  {
    throw unsupported();
  }
  
  public DurationField getLeapDurationField()
  {
    return null;
  }
  
  public int getMaximumShortTextLength(Locale paramLocale)
  {
    throw unsupported();
  }
  
  public int getMaximumTextLength(Locale paramLocale)
  {
    throw unsupported();
  }
  
  public int getMaximumValue()
  {
    throw unsupported();
  }
  
  public int getMaximumValue(long paramLong)
  {
    throw unsupported();
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial)
  {
    throw unsupported();
  }
  
  public int getMaximumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    throw unsupported();
  }
  
  public int getMinimumValue()
  {
    throw unsupported();
  }
  
  public int getMinimumValue(long paramLong)
  {
    throw unsupported();
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial)
  {
    throw unsupported();
  }
  
  public int getMinimumValue(ReadablePartial paramReadablePartial, int[] paramArrayOfInt)
  {
    throw unsupported();
  }
  
  public String getName()
  {
    return iType.getName();
  }
  
  public DurationField getRangeDurationField()
  {
    return null;
  }
  
  public DateTimeFieldType getType()
  {
    return iType;
  }
  
  public boolean isLeap(long paramLong)
  {
    throw unsupported();
  }
  
  public boolean isLenient()
  {
    return false;
  }
  
  public boolean isSupported()
  {
    return false;
  }
  
  public long remainder(long paramLong)
  {
    throw unsupported();
  }
  
  public long roundCeiling(long paramLong)
  {
    throw unsupported();
  }
  
  public long roundFloor(long paramLong)
  {
    throw unsupported();
  }
  
  public long roundHalfCeiling(long paramLong)
  {
    throw unsupported();
  }
  
  public long roundHalfEven(long paramLong)
  {
    throw unsupported();
  }
  
  public long roundHalfFloor(long paramLong)
  {
    throw unsupported();
  }
  
  public long set(long paramLong, int paramInt)
  {
    throw unsupported();
  }
  
  public long set(long paramLong, String paramString)
  {
    throw unsupported();
  }
  
  public long set(long paramLong, String paramString, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public int[] set(ReadablePartial paramReadablePartial, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    throw unsupported();
  }
  
  public int[] set(ReadablePartial paramReadablePartial, int paramInt, int[] paramArrayOfInt, String paramString, Locale paramLocale)
  {
    throw unsupported();
  }
  
  public String toString()
  {
    return "UnsupportedDateTimeField";
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.UnsupportedDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */