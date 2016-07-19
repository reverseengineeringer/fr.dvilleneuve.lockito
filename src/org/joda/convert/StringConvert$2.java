package org.joda.convert;

class StringConvert$2
  implements StringConverter<T>
{
  StringConvert$2(StringConvert paramStringConvert, ToStringConverter paramToStringConverter, FromStringConverter paramFromStringConverter) {}
  
  public T convertFromString(Class<? extends T> paramClass, String paramString)
  {
    return (T)val$fromString.convertFromString(paramClass, paramString);
  }
  
  public String convertToString(T paramT)
  {
    return val$toString.convertToString(paramT);
  }
}

/* Location:
 * Qualified Name:     org.joda.convert.StringConvert.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */