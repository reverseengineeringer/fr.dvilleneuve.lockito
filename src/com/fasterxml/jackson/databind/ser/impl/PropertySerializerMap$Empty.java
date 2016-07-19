package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JsonSerializer;

final class PropertySerializerMap$Empty
  extends PropertySerializerMap
{
  protected static final Empty instance = new Empty();
  
  public PropertySerializerMap newWith(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
  {
    return new PropertySerializerMap.Single(paramClass, paramJsonSerializer);
  }
  
  public JsonSerializer<Object> serializerFor(Class<?> paramClass)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.Empty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */