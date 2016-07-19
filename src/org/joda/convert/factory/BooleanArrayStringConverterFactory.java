package org.joda.convert.factory;

import org.joda.convert.StringConverter;
import org.joda.convert.StringConverterFactory;

public final class BooleanArrayStringConverterFactory
  implements StringConverterFactory
{
  public static final StringConverterFactory INSTANCE = new BooleanArrayStringConverterFactory();
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    if (paramClass == boolean[].class) {
      return BooleanArrayStringConverter.INSTANCE;
    }
    return null;
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  static abstract enum BooleanArrayStringConverter
    implements StringConverter<boolean[]>
  {
    INSTANCE;
    
    private static final boolean[] EMPTY = new boolean[0];
    
    private BooleanArrayStringConverter() {}
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.BooleanArrayStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */