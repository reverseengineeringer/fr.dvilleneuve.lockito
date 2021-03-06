package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import java.io.IOException;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$DoubleDeser
  extends PrimitiveArrayDeserializers<double[]>
{
  private static final long serialVersionUID = 1L;
  
  public PrimitiveArrayDeserializers$DoubleDeser()
  {
    super(double[].class);
  }
  
  private final double[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
      return null;
    }
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(_valueClass);
    }
    return new double[] { _parseDoublePrimitive(paramJsonParser, paramDeserializationContext) };
  }
  
  public double[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    ArrayBuilders.DoubleBuilder localDoubleBuilder = paramDeserializationContext.getArrayBuilders().getDoubleBuilder();
    double[] arrayOfDouble = (double[])localDoubleBuilder.resetAndStart();
    int i = 0;
    double d;
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
    {
      d = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
      if (i < arrayOfDouble.length) {
        break label110;
      }
      arrayOfDouble = (double[])localDoubleBuilder.appendCompletedChunk(arrayOfDouble, i);
      i = 0;
    }
    label110:
    for (;;)
    {
      int j = i + 1;
      arrayOfDouble[i] = d;
      i = j;
      break;
      return (double[])localDoubleBuilder.completeAndClearBuffer(arrayOfDouble, i);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers.DoubleDeser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */