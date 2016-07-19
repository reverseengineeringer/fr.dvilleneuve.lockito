package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;

 enum NumericArrayStringConverterFactory$ShortArrayStringConverter
  implements StringConverter<short[]>
{
  INSTANCE;
  
  private static final short[] EMPTY = new short[0];
  
  private NumericArrayStringConverterFactory$ShortArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.ShortArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */