package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>>
  extends StdSerializer<T>
{
  protected StaticListSerializerBase(Class<?> paramClass)
  {
    super(paramClass, false);
  }
  
  protected abstract void acceptContentVisitor(JsonArrayFormatVisitor paramJsonArrayFormatVisitor)
    throws JsonMappingException;
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    acceptContentVisitor(paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType));
  }
  
  protected abstract JsonNode contentSchema();
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    paramSerializerProvider = createSchemaNode("array", true);
    paramSerializerProvider.put("items", contentSchema());
    return paramSerializerProvider;
  }
  
  public boolean isEmpty(T paramT)
  {
    return (paramT == null) || (paramT.size() == 0);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */