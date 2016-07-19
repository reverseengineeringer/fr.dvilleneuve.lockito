package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class NumberSerializers
{
  public static void addAll(Map<String, JsonSerializer<?>> paramMap)
  {
    IntegerSerializer localIntegerSerializer = new IntegerSerializer();
    paramMap.put(Integer.class.getName(), localIntegerSerializer);
    paramMap.put(Integer.TYPE.getName(), localIntegerSerializer);
    paramMap.put(Long.class.getName(), LongSerializer.instance);
    paramMap.put(Long.TYPE.getName(), LongSerializer.instance);
    paramMap.put(Byte.class.getName(), IntLikeSerializer.instance);
    paramMap.put(Byte.TYPE.getName(), IntLikeSerializer.instance);
    paramMap.put(Short.class.getName(), ShortSerializer.instance);
    paramMap.put(Short.TYPE.getName(), ShortSerializer.instance);
    paramMap.put(Float.class.getName(), FloatSerializer.instance);
    paramMap.put(Float.TYPE.getName(), FloatSerializer.instance);
    paramMap.put(Double.class.getName(), DoubleSerializer.instance);
    paramMap.put(Double.TYPE.getName(), DoubleSerializer.instance);
  }
  
  @JacksonStdImpl
  public static final class DoubleSerializer
    extends NonTypedScalarSerializerBase<Double>
  {
    static final DoubleSerializer instance = new DoubleSerializer();
    
    public DoubleSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.DOUBLE);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Double paramDouble, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramDouble.doubleValue());
    }
  }
  
  @JacksonStdImpl
  public static final class FloatSerializer
    extends StdScalarSerializer<Float>
  {
    static final FloatSerializer instance = new FloatSerializer();
    
    public FloatSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.FLOAT);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Float paramFloat, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramFloat.floatValue());
    }
  }
  
  @JacksonStdImpl
  public static final class IntLikeSerializer
    extends StdScalarSerializer<Number>
  {
    static final IntLikeSerializer instance = new IntLikeSerializer();
    
    public IntLikeSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.INT);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("integer", true);
    }
    
    public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramNumber.intValue());
    }
  }
  
  @JacksonStdImpl
  public static final class IntegerSerializer
    extends NonTypedScalarSerializerBase<Integer>
  {
    public IntegerSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.INT);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("integer", true);
    }
    
    public void serialize(Integer paramInteger, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramInteger.intValue());
    }
  }
  
  @JacksonStdImpl
  public static final class LongSerializer
    extends StdScalarSerializer<Long>
  {
    static final LongSerializer instance = new LongSerializer();
    
    public LongSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.LONG);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Long paramLong, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramLong.longValue());
    }
  }
  
  @JacksonStdImpl
  public static final class NumberSerializer
    extends StdScalarSerializer<Number>
  {
    public static final NumberSerializer instance = new NumberSerializer();
    
    public NumberSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.BIG_DECIMAL);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      if ((paramNumber instanceof BigDecimal))
      {
        paramJsonGenerator.writeNumber((BigDecimal)paramNumber);
        return;
      }
      if ((paramNumber instanceof BigInteger))
      {
        paramJsonGenerator.writeNumber((BigInteger)paramNumber);
        return;
      }
      if ((paramNumber instanceof Integer))
      {
        paramJsonGenerator.writeNumber(paramNumber.intValue());
        return;
      }
      if ((paramNumber instanceof Long))
      {
        paramJsonGenerator.writeNumber(paramNumber.longValue());
        return;
      }
      if ((paramNumber instanceof Double))
      {
        paramJsonGenerator.writeNumber(paramNumber.doubleValue());
        return;
      }
      if ((paramNumber instanceof Float))
      {
        paramJsonGenerator.writeNumber(paramNumber.floatValue());
        return;
      }
      if (((paramNumber instanceof Byte)) || ((paramNumber instanceof Short)))
      {
        paramJsonGenerator.writeNumber(paramNumber.intValue());
        return;
      }
      paramJsonGenerator.writeNumber(paramNumber.toString());
    }
  }
  
  @JacksonStdImpl
  public static final class ShortSerializer
    extends StdScalarSerializer<Short>
  {
    static final ShortSerializer instance = new ShortSerializer();
    
    public ShortSerializer()
    {
      super();
    }
    
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
      throws JsonMappingException
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null) {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.INT);
      }
    }
    
    public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    {
      return createSchemaNode("number", true);
    }
    
    public void serialize(Short paramShort, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeNumber(paramShort.shortValue());
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.NumberSerializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */