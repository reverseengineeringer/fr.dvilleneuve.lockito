package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;

public final class DefaultSerializerProvider$Impl
  extends DefaultSerializerProvider
{
  private static final long serialVersionUID = 1L;
  
  public DefaultSerializerProvider$Impl() {}
  
  protected DefaultSerializerProvider$Impl(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    super(paramSerializerProvider, paramSerializationConfig, paramSerializerFactory);
  }
  
  public Impl createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    return new Impl(this, paramSerializationConfig, paramSerializerFactory);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */