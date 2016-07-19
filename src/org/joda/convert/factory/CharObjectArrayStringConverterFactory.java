package org.joda.convert.factory;

import java.util.Arrays;
import java.util.regex.Pattern;
import org.joda.convert.StringConverter;
import org.joda.convert.StringConverterFactory;

public final class CharObjectArrayStringConverterFactory
  implements StringConverterFactory
{
  static final Pattern DELIMITER = Pattern.compile("[,]");
  public static final StringConverterFactory INSTANCE = new CharObjectArrayStringConverterFactory();
  
  public StringConverter<?> findConverter(Class<?> paramClass)
  {
    if (paramClass == Character[].class) {
      return CharecterArrayStringConverter.INSTANCE;
    }
    return null;
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  static abstract enum CharecterArrayStringConverter
    implements StringConverter<Character[]>
  {
    INSTANCE;
    
    private static final Character[] EMPTY = new Character[0];
    
    private CharecterArrayStringConverter() {}
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.CharObjectArrayStringConverterFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */