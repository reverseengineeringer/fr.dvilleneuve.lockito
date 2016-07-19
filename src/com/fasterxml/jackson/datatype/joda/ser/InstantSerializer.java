package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.Instant;

public final class InstantSerializer
  extends JodaSerializerBase<Instant>
{
  public InstantSerializer()
  {
    super(Instant.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {}
    for (paramSerializerProvider = "number";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public void serialize(Instant paramInstant, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeNumber(paramInstant.getMillis());
      return;
    }
    paramJsonGenerator.writeString(paramInstant.toString());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.InstantSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */