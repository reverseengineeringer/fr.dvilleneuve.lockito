package org.joda.convert;

public abstract interface FromStringConverter<T>
{
  public abstract T convertFromString(Class<? extends T> paramClass, String paramString);
}

/* Location:
 * Qualified Name:     org.joda.convert.FromStringConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */