package org.joda.convert.factory;

import java.util.regex.Pattern;
import org.joda.convert.StringConverter;

 enum NumericArrayStringConverterFactory$IntArrayStringConverter
  implements StringConverter<int[]>
{
  INSTANCE;
  
  private static final int[] EMPTY = new int[0];
  
  private NumericArrayStringConverterFactory$IntArrayStringConverter() {}
}

/* Location:
 * Qualified Name:     org.joda.convert.factory.NumericArrayStringConverterFactory.IntArrayStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */