package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer
  extends ContainerDeserializerBase<Object[]>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final ArrayType _arrayType;
  protected final Class<?> _elementClass;
  protected JsonDeserializer<Object> _elementDeserializer;
  protected final TypeDeserializer _elementTypeDeserializer;
  protected final boolean _untyped;
  
  public ObjectArrayDeserializer(ArrayType paramArrayType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramArrayType);
    _arrayType = paramArrayType;
    _elementClass = paramArrayType.getContentType().getRawClass();
    if (_elementClass == Object.class) {}
    for (boolean bool = true;; bool = false)
    {
      _untyped = bool;
      _elementDeserializer = paramJsonDeserializer;
      _elementTypeDeserializer = paramTypeDeserializer;
      return;
    }
  }
  
  private final Object[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
      return null;
    }
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (_elementClass == Byte.class)) {
        return deserializeFromBase64(paramJsonParser, paramDeserializationContext);
      }
      throw paramDeserializationContext.mappingException(_arrayType.getRawClass());
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL)
    {
      paramJsonParser = null;
      if (!_untyped) {
        break label147;
      }
    }
    label147:
    for (paramDeserializationContext = new Object[1];; paramDeserializationContext = (Object[])Array.newInstance(_elementClass, 1))
    {
      paramDeserializationContext[0] = paramJsonParser;
      return paramDeserializationContext;
      if (_elementTypeDeserializer == null)
      {
        paramJsonParser = _elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        break;
      }
      paramJsonParser = _elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, _elementTypeDeserializer);
      break;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, _elementDeserializer);
    if (localObject == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer(_arrayType.getContentType(), paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject, paramBeanProperty))
    {
      TypeDeserializer localTypeDeserializer = _elementTypeDeserializer;
      localObject = localTypeDeserializer;
      if (localTypeDeserializer != null) {
        localObject = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withDeserializer((TypeDeserializer)localObject, paramDeserializationContext);
    }
  }
  
  public Object[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      return handleNonArray(paramJsonParser, paramDeserializationContext);
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    TypeDeserializer localTypeDeserializer = _elementTypeDeserializer;
    int i = 0;
    Object localObject = paramJsonParser.nextToken();
    if (localObject != JsonToken.END_ARRAY) {
      if (localObject == JsonToken.VALUE_NULL)
      {
        localObject = null;
        label60:
        if (i < arrayOfObject.length) {
          break label170;
        }
        arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
        i = 0;
      }
    }
    label170:
    for (;;)
    {
      int j = i + 1;
      arrayOfObject[i] = localObject;
      i = j;
      break;
      if (localTypeDeserializer == null)
      {
        localObject = _elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        break label60;
      }
      localObject = _elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      break label60;
      if (_untyped) {}
      for (paramJsonParser = localObjectBuffer.completeAndClearBuffer(arrayOfObject, i);; paramJsonParser = localObjectBuffer.completeAndClearBuffer(arrayOfObject, i, _elementClass))
      {
        paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
        return paramJsonParser;
      }
    }
  }
  
  protected Byte[] deserializeFromBase64(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    paramJsonParser = paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant());
    paramDeserializationContext = new Byte[paramJsonParser.length];
    int i = 0;
    int j = paramJsonParser.length;
    while (i < j)
    {
      paramDeserializationContext[i] = Byte.valueOf(paramJsonParser[i]);
      i += 1;
    }
    return paramDeserializationContext;
  }
  
  public Object[] deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return (Object[])paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return _elementDeserializer;
  }
  
  public JavaType getContentType()
  {
    return _arrayType.getContentType();
  }
  
  public ObjectArrayDeserializer withDeserializer(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    if ((paramJsonDeserializer == _elementDeserializer) && (paramTypeDeserializer == _elementTypeDeserializer)) {
      return this;
    }
    return new ObjectArrayDeserializer(_arrayType, paramJsonDeserializer, paramTypeDeserializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */