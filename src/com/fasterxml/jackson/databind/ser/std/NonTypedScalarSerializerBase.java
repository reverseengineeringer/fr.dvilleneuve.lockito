package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class NonTypedScalarSerializerBase<T>
  extends StdScalarSerializer<T>
{
  protected NonTypedScalarSerializerBase(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  public final void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    serialize(paramT, paramJsonGenerator, paramSerializerProvider);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */