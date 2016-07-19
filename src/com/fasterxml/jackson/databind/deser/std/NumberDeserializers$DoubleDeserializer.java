package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$DoubleDeserializer
  extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Double>
{
  private static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(Double.class, Double.valueOf(0.0D));
  private static final long serialVersionUID = 1L;
  private static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.TYPE, null);
  
  public NumberDeserializers$DoubleDeserializer(Class<Double> paramClass, Double paramDouble)
  {
    super(paramClass, paramDouble);
  }
  
  public Double deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _parseDouble(paramJsonParser, paramDeserializationContext);
  }
  
  public Double deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return _parseDouble(paramJsonParser, paramDeserializationContext);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.NumberDeserializers.DoubleDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */