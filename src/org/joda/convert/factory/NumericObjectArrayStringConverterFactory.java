package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;
import org.joda.convert.StringConverterFactory;

public final class NumericObjectArrayStringConverterFactory
  implements StringConverterFactory
{
  static final Pattern DELIMITER = Pattern.compile("[,]");
  public static final StringConverterFactory INSTANCE = new NumericObjectArrayStringConverterFactory();
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    if (paramClass.isArray())
    {
      if (paramClass == Long[].class) {
        return LongArrayStringConverter.INSTANCE;
      }
      if (paramClass == Integer[].class) {
        return IntArrayStringConverter.INSTANCE;
      }
      if (paramClass == Short[].class) {
        return ShortArrayStringConverter.INSTANCE;
      }
      if (paramClass == Double[].class) {
        return DoubleArrayStringConverter.INSTANCE;
      }
      if (paramClass == Float[].class) {
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
    implements StringConverter<Double[]>
  {
    INSTANCE;
    
    private static final Double[] EMPTY = new Double[0];
    
    private DoubleArrayStringConverter() {}
  }
  
  static abstract enum FloatArrayStringConverter
    implements StringConverter<Float[]>
  {
    INSTANCE;
    
    private static final Float[] EMPTY = new Float[0];
    
    private FloatArrayStringConverter() {}
  }
  
  static abstract enum IntArrayStringConverter
    implements StringConverter<Integer[]>
  {
    INSTANCE;
    
    private static final Integer[] EMPTY = new Integer[0];
    
    private IntArrayStringConverter() {}
  }
  
  static abstract enum LongArrayStringConverter
    implements StringConverter<Long[]>
  {
    INSTANCE;
    
    private static final Long[] EMPTY = new Long[0];
    
    private LongArrayStringConverter() {}
  }
  
  static abstract enum ShortArrayStringConverter
    implements StringConverter<Short[]>
  {
    INSTANCE;
    
    private static final Short[] EMPTY = new Short[0];
    
    private ShortArrayStringConverter() {}
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */