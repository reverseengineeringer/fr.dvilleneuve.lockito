package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer
  extends StdDeserializer<String[]>
  implements ContextualDeserializer
{
  public static final StringArrayDeserializer instance = new StringArrayDeserializer();
  private static final long serialVersionUID = -7589512013334920693L;
  protected JsonDeserializer<String> _elementDeserializer;
  
  public StringArrayDeserializer()
  {
    super(String[].class);
    _elementDeserializer = null;
  }
  
  protected StringArrayDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    super(String[].class);
    _elementDeserializer = paramJsonDeserializer;
  }
  
  private final String[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = null;
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return null;
      }
      throw paramDeserializationContext.mappingException(_valueClass);
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {}
    for (paramJsonParser = (JsonParser)localObject;; paramJsonParser = _parseString(paramJsonParser, paramDeserializationContext)) {
      return new String[] { paramJsonParser };
    }
  }
  
  protected final String[] _deserializeCustom(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    JsonDeserializer localJsonDeserializer = _elementDeserializer;
    int i = 0;
    Object localObject = paramJsonParser.nextToken();
    if (localObject != JsonToken.END_ARRAY) {
      if (localObject == JsonToken.VALUE_NULL)
      {
        localObject = null;
        label46:
        if (i < arrayOfObject.length) {
          break label114;
        }
        arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
        i = 0;
      }
    }
    label114:
    for (;;)
    {
      arrayOfObject[i] = localObject;
      i += 1;
      break;
      localObject = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      break label46;
      paramJsonParser = (String[])localObjectBuffer.completeAndClearBuffer(arrayOfObject, i, String.class);
      paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
      return paramJsonParser;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, _elementDeserializer);
    if (localJsonDeserializer == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer(paramDeserializationContext.constructType(String.class), paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer, paramBeanProperty))
    {
      paramBeanProperty = paramDeserializationContext;
      if (paramDeserializationContext != null)
      {
        paramBeanProperty = paramDeserializationContext;
        if (isDefaultDeserializer(paramDeserializationContext)) {
          paramBeanProperty = null;
        }
      }
      paramDeserializationContext = this;
      if (_elementDeserializer != paramBeanProperty) {
        paramDeserializationContext = new StringArrayDeserializer(paramBeanProperty);
      }
      return paramDeserializationContext;
    }
  }
  
  public String[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    if (_elementDeserializer != null) {
      return _deserializeCustom(paramJsonParser, paramDeserializationContext);
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    int i = 0;
    Object localObject = paramJsonParser.nextToken();
    if (localObject != JsonToken.END_ARRAY) {
      if (localObject == JsonToken.VALUE_STRING)
      {
        localObject = paramJsonParser.getText();
        label71:
        if (i < arrayOfObject.length) {
          break label153;
        }
        arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
        i = 0;
      }
    }
    label153:
    for (;;)
    {
      int j = i + 1;
      arrayOfObject[i] = localObject;
      i = j;
      break;
      if (localObject == JsonToken.VALUE_NULL)
      {
        localObject = null;
        break label71;
      }
      localObject = _parseString(paramJsonParser, paramDeserializationContext);
      break label71;
      paramJsonParser = (String[])localObjectBuffer.completeAndClearBuffer(arrayOfObject, i, String.class);
      paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
      return paramJsonParser;
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */