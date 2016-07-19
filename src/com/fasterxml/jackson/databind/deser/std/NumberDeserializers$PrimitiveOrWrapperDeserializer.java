package com.fasterxml.jackson.databind.deser.std;

public abstract class NumberDeserializers$PrimitiveOrWrapperDeserializer<T>
  extends StdScalarDeserializer<T>
{
  private static final long serialVersionUID = 1L;
  protected final T _nullValue;
  
  protected NumberDeserializers$PrimitiveOrWrapperDeserializer(Class<T> paramClass, T paramT)
  {
    super(paramClass);
    _nullValue = paramT;
  }
  
  public final T getNullValue()
  {
    return (T)_nullValue;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.NumberDeserializers.PrimitiveOrWrapperDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */