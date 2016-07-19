package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class SerializerFactory
{
  @Deprecated
  public JsonSerializer<Object> createKeySerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    return createKeySerializer(paramSerializationConfig, paramJavaType, null);
  }
  
  public abstract JsonSerializer<Object> createKeySerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
    throws JsonMappingException;
  
  public abstract JsonSerializer<Object> createSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType)
    throws JsonMappingException;
  
  @Deprecated
  public JsonSerializer<Object> createSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return createSerializer(paramSerializerProvider, paramJavaType);
  }
  
  public abstract TypeSerializer createTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType)
    throws JsonMappingException;
  
  public abstract SerializerFactory withAdditionalKeySerializers(Serializers paramSerializers);
  
  public abstract SerializerFactory withAdditionalSerializers(Serializers paramSerializers);
  
  public abstract SerializerFactory withSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier);
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.SerializerFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */