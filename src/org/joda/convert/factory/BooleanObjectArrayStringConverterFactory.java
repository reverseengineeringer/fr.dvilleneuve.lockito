package org.joda.convert.factory;

import org.joda.convert.StringConverter;
import org.joda.convert.StringConverterFactory;

public final class BooleanObjectArrayStringConverterFactory
  implements StringConverterFactory
{
  public static final StringConverterFactory INSTANCE = new BooleanObjectArrayStringConverterFactory();
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    if (paramClass == Boolean[].class) {
      return BooleanArrayStringConverter.INSTANCE;
    }
    return null;
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  static abstract enum BooleanArrayStringConverter
    implements StringConverter<Boolean[]>
  {
    INSTANCE;
    
    private static final Boolean[] EMPTY = new Boolean[0];
    
    private BooleanArrayStringConverter() {}
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.BooleanObjectArrayStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */