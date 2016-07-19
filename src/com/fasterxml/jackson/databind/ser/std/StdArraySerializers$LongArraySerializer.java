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
public final class StdArraySerializers$LongArraySerializer
  extends StdArraySerializers.TypedPrimitiveArraySerializer<long[]>
{
  private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);
  
  public StdArraySerializers$LongArraySerializer()
  {
    super(long[].class);
  }
  
  public StdArraySerializers$LongArraySerializer(LongArraySerializer paramLongArraySerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer)
  {
    super(paramLongArraySerializer, paramBeanProperty, paramTypeSerializer);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new LongArraySerializer(this, _property, paramTypeSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.itemsFormat(JsonFormatTypes.NUMBER);
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
    paramSerializerProvider.put("items", createSchemaNode("number", true));
    return paramSerializerProvider;
  }
  
  public boolean hasSingleElement(long[] paramArrayOfLong)
  {
    return paramArrayOfLong.length == 1;
  }
  
  public boolean isEmpty(long[] paramArrayOfLong)
  {
    return (paramArrayOfLong == null) || (paramArrayOfLong.length == 0);
  }
  
  public void serializeContents(long[] paramArrayOfLong, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int j = 0;
    int i = 0;
    if (_valueTypeSerializer != null)
    {
      j = paramArrayOfLong.length;
      while (i < j)
      {
        _valueTypeSerializer.writeTypePrefixForScalar(null, paramJsonGenerator, Long.TYPE);
        paramJsonGenerator.writeNumber(paramArrayOfLong[i]);
        _valueTypeSerializer.writeTypeSuffixForScalar(null, paramJsonGenerator);
        i += 1;
      }
    }
    int k = paramArrayOfLong.length;
    i = j;
    while (i < k)
    {
      paramJsonGenerator.writeNumber(paramArrayOfLong[i]);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.StdArraySerializers.LongArraySerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */