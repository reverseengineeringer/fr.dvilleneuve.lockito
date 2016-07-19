package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public final class UnsupportedDurationField
  extends DurationField
  implements Serializable
{
  private static HashMap<DurationFieldType, UnsupportedDurationField> cCache;
  private static final long serialVersionUID = -6390301302770925357L;
  private final DurationFieldType iType;
  
  private UnsupportedDurationField(DurationFieldType paramDurationFieldType)
  {
    iType = paramDurationFieldType;
  }
  
  /* Error */
  public static UnsupportedDurationField getInstance(DurationFieldType paramDurationFieldType)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 27	org/joda/time/field/UnsupportedDurationField:cCache	Ljava/util/HashMap;
    //   6: ifnonnull +46 -> 52
    //   9: new 29	java/util/HashMap
    //   12: dup
    //   13: bipush 7
    //   15: invokespecial 32	java/util/HashMap:<init>	(I)V
    //   18: putstatic 27	org/joda/time/field/UnsupportedDurationField:cCache	Ljava/util/HashMap;
    //   21: aconst_null
    //   22: astore_1
    //   23: aload_1
    //   24: astore_2
    //   25: aload_1
    //   26: ifnonnull +21 -> 47
    //   29: new 2	org/joda/time/field/UnsupportedDurationField
    //   32: dup
    //   33: aload_0
    //   34: invokespecial 34	org/joda/time/field/UnsupportedDurationField:<init>	(Lorg/joda/time/DurationFieldType;)V
    //   37: astore_2
    //   38: getstatic 27	org/joda/time/field/UnsupportedDurationField:cCache	Ljava/util/HashMap;
    //   41: aload_0
    //   42: aload_2
    //   43: invokevirtual 38	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   46: pop
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_2
    //   51: areturn
    //   52: getstatic 27	org/joda/time/field/UnsupportedDurationField:cCache	Ljava/util/HashMap;
    //   55: aload_0
    //   56: invokevirtual 42	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   59: checkcast 2	org/joda/time/field/UnsupportedDurationField
    //   62: astore_1
    //   63: goto -40 -> 23
    //   66: astore_0
    //   67: ldc 2
    //   69: monitorexit
    //   70: aload_0
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	paramDurationFieldType	DurationFieldType
    //   22	41	1	localUnsupportedDurationField1	UnsupportedDurationField
    //   24	27	2	localUnsupportedDurationField2	UnsupportedDurationField
    // Exception table:
    //   from	to	target	type
    //   3	21	66	finally
    //   29	47	66	finally
    //   52	63	66	finally
  }
  
  private Object readResolve()
  {
    return getInstance(iType);
  }
  
  private UnsupportedOperationException unsupported()
  {
    return new UnsupportedOperationException(iType + " field is unsupported");
  }
  
  public long add(long paramLong, int paramInt)
  {
    throw unsupported();
  }
  
  public long add(long paramLong1, long paramLong2)
  {
    throw unsupported();
  }
  
  public int compareTo(DurationField paramDurationField)
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof UnsupportedDurationField)) {
        break label47;
      }
      paramObject = (UnsupportedDurationField)paramObject;
      if (((UnsupportedDurationField)paramObject).getName() != null) {
        break;
      }
    } while (getName() == null);
    return false;
    return ((UnsupportedDurationField)paramObject).getName().equals(getName());
    label47:
    return false;
  }
  
  public int getDifference(long paramLong1, long paramLong2)
  {
    throw unsupported();
  }
  
  public long getDifferenceAsLong(long paramLong1, long paramLong2)
  {
    throw unsupported();
  }
  
  public long getMillis(int paramInt)
  {
    throw unsupported();
  }
  
  public long getMillis(int paramInt, long paramLong)
  {
    throw unsupported();
  }
  
  public long getMillis(long paramLong)
  {
    throw unsupported();
  }
  
  public long getMillis(long paramLong1, long paramLong2)
  {
    throw unsupported();
  }
  
  public String getName()
  {
    return iType.getName();
  }
  
  public final DurationFieldType getType()
  {
    return iType;
  }
  
  public long getUnitMillis()
  {
    return 0L;
  }
  
  public int getValue(long paramLong)
  {
    throw unsupported();
  }
  
  public int getValue(long paramLong1, long paramLong2)
  {
    throw unsupported();
  }
  
  public long getValueAsLong(long paramLong)
  {
    throw unsupported();
  }
  
  public long getValueAsLong(long paramLong1, long paramLong2)
  {
    throw unsupported();
  }
  
  public int hashCode()
  {
    return getName().hashCode();
  }
  
  public boolean isPrecise()
  {
    return true;
  }
  
  public boolean isSupported()
  {
    return false;
  }
  
  public String toString()
  {
    return "UnsupportedDurationField[" + getName() + ']';
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.UnsupportedDurationField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */