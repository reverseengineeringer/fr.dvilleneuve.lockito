package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder;
import java.io.IOException;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$ShortDeser
  extends PrimitiveArrayDeserializers<short[]>
{
  private static final long serialVersionUID = 1L;
  
  public PrimitiveArrayDeserializers$ShortDeser()
  {
    super(short[].class);
  }
  
  private final short[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
      return null;
    }
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(_valueClass);
    }
    return new short[] { _parseShortPrimitive(paramJsonParser, paramDeserializationContext) };
  }
  
  public short[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    ArrayBuilders.ShortBuilder localShortBuilder = paramDeserializationContext.getArrayBuilders().getShortBuilder();
    short[] arrayOfShort = (short[])localShortBuilder.resetAndStart();
    int j = 0;
    int i;
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
    {
      i = _parseShortPrimitive(paramJsonParser, paramDeserializationContext);
      if (j < arrayOfShort.length) {
        break label110;
      }
      arrayOfShort = (short[])localShortBuilder.appendCompletedChunk(arrayOfShort, j);
      j = 0;
    }
    label110:
    for (;;)
    {
      int k = j + 1;
      arrayOfShort[j] = i;
      j = k;
      break;
      return (short[])localShortBuilder.completeAndClearBuffer(arrayOfShort, j);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers.ShortDeser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */