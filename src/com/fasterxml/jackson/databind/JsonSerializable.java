package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract interface JsonSerializable
{
  public abstract void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException;
  
  public abstract void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException;
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.JsonSerializable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */