package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder;
import java.io.IOException;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$FloatDeser
  extends PrimitiveArrayDeserializers<float[]>
{
  private static final long serialVersionUID = 1L;
  
  public PrimitiveArrayDeserializers$FloatDeser()
  {
    super(float[].class);
  }
  
  private final float[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
      return null;
    }
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(_valueClass);
    }
    return new float[] { _parseFloatPrimitive(paramJsonParser, paramDeserializationContext) };
  }
  
  public float[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    ArrayBuilders.FloatBuilder localFloatBuilder = paramDeserializationContext.getArrayBuilders().getFloatBuilder();
    float[] arrayOfFloat = (float[])localFloatBuilder.resetAndStart();
    int i = 0;
    float f;
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
    {
      f = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
      if (i < arrayOfFloat.length) {
        break label110;
      }
      arrayOfFloat = (float[])localFloatBuilder.appendCompletedChunk(arrayOfFloat, i);
      i = 0;
    }
    label110:
    for (;;)
    {
      int j = i + 1;
      arrayOfFloat[i] = f;
      i = j;
      break;
      return (float[])localFloatBuilder.completeAndClearBuffer(arrayOfFloat, i);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers.FloatDeser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */