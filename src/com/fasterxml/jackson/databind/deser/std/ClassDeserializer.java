package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

@JacksonStdImpl
public class ClassDeserializer
  extends StdScalarDeserializer<Class<?>>
{
  public static final ClassDeserializer instance = new ClassDeserializer();
  private static final long serialVersionUID = 1L;
  
  public ClassDeserializer()
  {
    super(Class.class);
  }
  
  public Class<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      try
      {
        paramJsonParser = paramDeserializationContext.findClass(paramJsonParser);
        return paramJsonParser;
      }
      catch (Exception paramJsonParser)
      {
        throw paramDeserializationContext.instantiationException(_valueClass, ClassUtil.getRootCause(paramJsonParser));
      }
    }
    throw paramDeserializationContext.mappingException(_valueClass, localJsonToken);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.ClassDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */