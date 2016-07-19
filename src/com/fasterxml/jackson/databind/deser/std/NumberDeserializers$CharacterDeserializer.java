package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$CharacterDeserializer
  extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Character>
{
  private static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(Character.class, Character.valueOf('\000'));
  private static final long serialVersionUID = 1L;
  private static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.TYPE, null);
  
  public NumberDeserializers$CharacterDeserializer(Class<Character> paramClass, Character paramCharacter)
  {
    super(paramClass, paramCharacter);
  }
  
  public Character deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
    {
      int i = paramJsonParser.getIntValue();
      if ((i >= 0) && (i <= 65535)) {
        return Character.valueOf((char)i);
      }
    }
    else if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText();
      if (paramJsonParser.length() == 1) {
        return Character.valueOf(paramJsonParser.charAt(0));
      }
      if (paramJsonParser.length() == 0) {
        return (Character)getEmptyValue();
      }
    }
    throw paramDeserializationContext.mappingException(_valueClass, localJsonToken);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.NumberDeserializers.CharacterDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */