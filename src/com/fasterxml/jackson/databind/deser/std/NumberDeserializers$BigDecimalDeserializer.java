package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigDecimal;

@JacksonStdImpl
public class NumberDeserializers$BigDecimalDeserializer
  extends StdScalarDeserializer<BigDecimal>
{
  public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();
  
  public NumberDeserializers$BigDecimalDeserializer()
  {
    super(BigDecimal.class);
  }
  
  public BigDecimal deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    if ((localObject == JsonToken.VALUE_NUMBER_INT) || (localObject == JsonToken.VALUE_NUMBER_FLOAT)) {
      return paramJsonParser.getDecimalValue();
    }
    if (localObject == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return null;
      }
      try
      {
        localObject = new BigDecimal(paramJsonParser);
        return (BigDecimal)localObject;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid representation");
      }
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */