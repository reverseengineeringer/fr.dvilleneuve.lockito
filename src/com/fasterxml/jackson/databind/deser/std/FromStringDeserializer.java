package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class FromStringDeserializer<T>
  extends StdScalarDeserializer<T>
{
  private static final long serialVersionUID = 1L;
  
  protected FromStringDeserializer(Class<?> paramClass)
  {
    super(paramClass);
  }
  
  protected abstract T _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException;
  
  protected T _deserializeEmbedded(Object paramObject, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    throw paramDeserializationContext.mappingException("Don't know how to convert embedded Object of type " + paramObject.getClass().getName() + " into " + _valueClass.getName());
  }
  
  public final T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject1 = null;
    Object localObject2 = paramJsonParser.getValueAsString();
    if (localObject2 != null)
    {
      paramJsonParser = (JsonParser)localObject1;
      if (((String)localObject2).length() != 0)
      {
        localObject2 = ((String)localObject2).trim();
        if (((String)localObject2).length() != 0) {
          break label42;
        }
        paramJsonParser = (JsonParser)localObject1;
      }
    }
    label42:
    do
    {
      for (;;)
      {
        return paramJsonParser;
        try
        {
          localObject1 = _deserialize((String)localObject2, paramDeserializationContext);
          paramJsonParser = (JsonParser)localObject1;
          if (localObject1 != null) {}
        }
        catch (IllegalArgumentException paramJsonParser)
        {
          for (;;) {}
        }
      }
      throw paramDeserializationContext.weirdStringException((String)localObject2, _valueClass, "not a valid textual representation");
      if (paramJsonParser.getCurrentToken() != JsonToken.VALUE_EMBEDDED_OBJECT) {
        break;
      }
      localObject2 = paramJsonParser.getEmbeddedObject();
      paramJsonParser = (JsonParser)localObject1;
    } while (localObject2 == null);
    if (_valueClass.isAssignableFrom(localObject2.getClass())) {
      return (T)localObject2;
    }
    return (T)_deserializeEmbedded(localObject2, paramDeserializationContext);
    throw paramDeserializationContext.mappingException(_valueClass);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */