package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.Instant;

public class InstantDeserializer
  extends JodaDeserializerBase<Instant>
{
  private static final long serialVersionUID = 1L;
  
  public InstantDeserializer()
  {
    super(Instant.class);
  }
  
  public Instant deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      return new Instant(paramJsonParser.getLongValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return null;
      }
      return new Instant(paramJsonParser);
    }
    throw paramDeserializationContext.mappingException(Instant.class);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.deser.InstantDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */