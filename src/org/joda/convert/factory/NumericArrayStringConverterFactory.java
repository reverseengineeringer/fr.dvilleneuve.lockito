package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;
import org.joda.convert.StringConverterFactory;

public final class NumericArrayStringConverterFactory
  implements StringConverterFactory
{
  static final Pattern DELIMITER = Pattern.compile("[,]");
  public static final StringConverterFactory INSTANCE = new NumericArrayStringConverterFactory();
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    if ((paramClass.isArray()) && (paramClass.getComponentType().isPrimitive()))
    {
      if (paramClass == long[].class) {
        return LongArrayStringConverter.INSTANCE;
      }
      if (paramClass == int[].class) {
        return IntArrayStringConverter.INSTANCE;
      }
      if (paramClass == short[].class) {
        return ShortArrayStringConverter.INSTANCE;
      }
      if (paramClass == double[].class) {
        return DoubleArrayStringConverter.INSTANCE;
      }
      if (paramClass == float[].class) {
        return FloatArrayStringConverter.INSTANCE;
      }
    }
    return null;
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  static abstract enum DoubleArrayStringConverter
    implements StringConverter<double[]>
  {
    INSTANCE;
    
    private static final double[] EMPTY = new double[0];
    
    private DoubleArrayStringConverter() {}
  }
  
  static abstract enum FloatArrayStringConverter
    implements StringConverter<float[]>
  {
    INSTANCE;
    
    private static final float[] EMPTY = new float[0];
    
    private FloatArrayStringConverter() {}
  }
  
  static abstract enum IntArrayStringConverter
    implements StringConverter<int[]>
  {
    INSTANCE;
    
    private static final int[] EMPTY = new int[0];
    
    private IntArrayStringConverter() {}
  }
  
  static abstract enum LongArrayStringConverter
    implements StringConverter<long[]>
  {
    INSTANCE;
    
    private static final long[] EMPTY = new long[0];
    
    private LongArrayStringConverter() {}
  }
  
  static abstract enum ShortArrayStringConverter
    implements StringConverter<short[]>
  {
    INSTANCE;
    
    private static final short[] EMPTY = new short[0];
    
    private ShortArrayStringConverter() {}
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */