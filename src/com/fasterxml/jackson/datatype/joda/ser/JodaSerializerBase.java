package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

abstract class JodaSerializerBase<T>
  extends StdSerializer<T>
{
  protected JodaSerializerBase(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  public void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException
  {
    paramTypeSerializer.writeTypePrefixForScalar(paramT, paramJsonGenerator);
    serialize(paramT, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramT, paramJsonGenerator);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.ser.JodaSerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */