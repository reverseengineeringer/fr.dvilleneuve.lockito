package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class PropertyWriter
{
  public abstract void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException;
  
  @Deprecated
  public abstract void depositSchemaProperty(ObjectNode paramObjectNode, SerializerProvider paramSerializerProvider)
    throws JsonMappingException;
  
  public abstract PropertyName getFullName();
  
  public abstract String getName();
  
  public abstract void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception;
  
  public abstract void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception;
  
  public abstract void serializeAsOmittedField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception;
  
  public abstract void serializeAsPlaceholder(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception;
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.PropertyWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */