package org.joda.convert.factory;

import org.joda.convert.StringConverter;
import org.joda.convert.StringConverterFactory;

public final class ByteObjectArrayStringConverterFactory
  implements StringConverterFactory
{
  public static final StringConverterFactory INSTANCE = new ByteObjectArrayStringConverterFactory();
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    if (paramClass == Byte[].class) {
      return ByteArrayStringConverter.INSTANCE;
    }
    return null;
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  static abstract enum ByteArrayStringConverter
    implements StringConverter<Byte[]>
  {
    INSTANCE;
    
    private static final Byte[] EMPTY = new Byte[0];
    private static final String HEX = "0123456789ABCDEF";
    
    private ByteArrayStringConverter() {}
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.ByteObjectArrayStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */