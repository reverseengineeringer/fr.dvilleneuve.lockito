package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;

 enum NumericObjectArrayStringConverterFactory$IntArrayStringConverter
  implements StringConverter<Integer[]>
{
  INSTANCE;
  
  private static final Integer[] EMPTY = new Integer[0];
  
  private NumericObjectArrayStringConverterFactory$IntArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericObjectArrayStringConverterFactory.IntArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */