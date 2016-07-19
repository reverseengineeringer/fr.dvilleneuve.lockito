package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$IntegerDeserializer
  extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Integer>
{
  private static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(Integer.class, Integer.valueOf(0));
  private static final long serialVersionUID = 1L;
  private static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.TYPE, null);
  
  public NumberDeserializers$IntegerDeserializer(Class<Integer> paramClass, Integer paramInteger)
  {
    super(paramClass, paramInteger);
  }
  
  public Integer deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _parseInteger(paramJsonParser, paramDeserializationContext);
  }
  
  public Integer deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return _parseInteger(paramJsonParser, paramDeserializationContext);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.NumberDeserializers.IntegerDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */