package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public final class StringDeserializer
  extends StdScalarDeserializer<String>
{
  public static final StringDeserializer instance = new StringDeserializer();
  private static final long serialVersionUID = 1L;
  
  public StringDeserializer()
  {
    super(String.class);
  }
  
  public String deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramJsonParser.getValueAsString();
    if (localObject != null) {
      return (String)localObject;
    }
    localObject = paramJsonParser.getCurrentToken();
    if (localObject == JsonToken.VALUE_EMBEDDED_OBJECT)
    {
      paramJsonParser = paramJsonParser.getEmbeddedObject();
      if (paramJsonParser == null) {
        return null;
      }
      if ((paramJsonParser instanceof byte[])) {
        return Base64Variants.getDefaultVariant().encode((byte[])paramJsonParser, false);
      }
      return paramJsonParser.toString();
    }
    throw paramDeserializationContext.mappingException(_valueClass, (JsonToken)localObject);
  }
  
  public String deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return deserialize(paramJsonParser, paramDeserializationContext);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StringDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */