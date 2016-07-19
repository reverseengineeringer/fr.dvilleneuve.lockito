package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$ShortArraySerializer
  extends StdArraySerializers.TypedPrimitiveArraySerializer<short[]>
{
  private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);
  
  public StdArraySerializers$ShortArraySerializer()
  {
    super(short[].class);
  }
  
  public StdArraySerializers$ShortArraySerializer(ShortArraySerializer paramShortArraySerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer)
  {
    super(paramShortArraySerializer, paramBeanProperty, paramTypeSerializer);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new ShortArraySerializer(this, _property, paramTypeSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.itemsFormat(JsonFormatTypes.INTEGER);
      }
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return null;
  }
  
  public JavaType getContentType()
  {
    return VALUE_TYPE;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    paramSerializerProvider = createSchemaNode("array", true);
    paramSerializerProvider.put("items", createSchemaNode("integer"));
    return paramSerializerProvider;
  }
  
  public boolean hasSingleElement(short[] paramArrayOfShort)
  {
    return paramArrayOfShort.length == 1;
  }
  
  public boolean isEmpty(short[] paramArrayOfShort)
  {
    return (paramArrayOfShort == null) || (paramArrayOfShort.length == 0);
  }
  
  public void serializeContents(short[] paramArrayOfShort, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int j = 0;
    int i = 0;
    if (_valueTypeSerializer != null)
    {
      j = paramArrayOfShort.length;
      while (i < j)
      {
        _valueTypeSerializer.writeTypePrefixForScalar(null, paramJsonGenerator, Short.TYPE);
        paramJsonGenerator.writeNumber(paramArrayOfShort[i]);
        _valueTypeSerializer.writeTypeSuffixForScalar(null, paramJsonGenerator);
        i += 1;
      }
    }
    int k = paramArrayOfShort.length;
    i = j;
    while (i < k)
    {
      paramJsonGenerator.writeNumber(paramArrayOfShort[i]);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.StdArraySerializers.ShortArraySerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */