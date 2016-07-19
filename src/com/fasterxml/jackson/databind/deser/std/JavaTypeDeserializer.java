package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;

public class JavaTypeDeserializer
  extends StdScalarDeserializer<JavaType>
{
  public static final JavaTypeDeserializer instance = new JavaTypeDeserializer();
  private static final long serialVersionUID = 1L;
  
  public JavaTypeDeserializer()
  {
    super(JavaType.class);
  }
  
  public JavaType deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (JavaType)getEmptyValue();
      }
      return paramDeserializationContext.getTypeFactory().constructFromCanonical(paramJsonParser);
    }
    if (localJsonToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
      return (JavaType)paramJsonParser.getEmbeddedObject();
    }
    throw paramDeserializationContext.mappingException(_valueClass);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JavaTypeDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */