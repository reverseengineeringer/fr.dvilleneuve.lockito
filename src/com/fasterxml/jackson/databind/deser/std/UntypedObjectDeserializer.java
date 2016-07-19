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
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class UntypedObjectDeserializer
  extends StdDeserializer<Object>
  implements ResolvableDeserializer, ContextualDeserializer
{
  private static final Object[] NO_OBJECTS = new Object[0];
  @Deprecated
  public static final UntypedObjectDeserializer instance = new UntypedObjectDeserializer();
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<Object> _listDeserializer;
  protected JsonDeserializer<Object> _mapDeserializer;
  protected JsonDeserializer<Object> _numberDeserializer;
  protected JsonDeserializer<Object> _stringDeserializer;
  
  public UntypedObjectDeserializer()
  {
    super(Object.class);
  }
  
  public UntypedObjectDeserializer(UntypedObjectDeserializer paramUntypedObjectDeserializer, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, JsonDeserializer<?> paramJsonDeserializer3, JsonDeserializer<?> paramJsonDeserializer4)
  {
    super(Object.class);
    _mapDeserializer = paramJsonDeserializer1;
    _listDeserializer = paramJsonDeserializer2;
    _stringDeserializer = paramJsonDeserializer3;
    _numberDeserializer = paramJsonDeserializer4;
  }
  
  protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJavaType = paramDeserializationContext.findRootValueDeserializer(paramJavaType);
    paramDeserializationContext = paramJavaType;
    if (ClassUtil.isJacksonStdImpl(paramJavaType)) {
      paramDeserializationContext = null;
    }
    return paramDeserializationContext;
  }
  
  protected JsonDeserializer<?> _withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, JsonDeserializer<?> paramJsonDeserializer3, JsonDeserializer<?> paramJsonDeserializer4)
  {
    return new UntypedObjectDeserializer(this, paramJsonDeserializer1, paramJsonDeserializer2, paramJsonDeserializer3, paramJsonDeserializer4);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = _mapDeserializer;
    if ((localJsonDeserializer1 instanceof ContextualDeserializer)) {
      localJsonDeserializer1 = ((ContextualDeserializer)localJsonDeserializer1).createContextual(paramDeserializationContext, paramBeanProperty);
    }
    for (;;)
    {
      JsonDeserializer localJsonDeserializer2 = _listDeserializer;
      if ((localJsonDeserializer2 instanceof ContextualDeserializer)) {
        localJsonDeserializer2 = ((ContextualDeserializer)localJsonDeserializer2).createContextual(paramDeserializationContext, paramBeanProperty);
      }
      for (;;)
      {
        JsonDeserializer localJsonDeserializer3 = _stringDeserializer;
        if ((localJsonDeserializer3 instanceof ContextualDeserializer)) {
          localJsonDeserializer3 = ((ContextualDeserializer)localJsonDeserializer3).createContextual(paramDeserializationContext, paramBeanProperty);
        }
        for (;;)
        {
          JsonDeserializer localJsonDeserializer5 = _numberDeserializer;
          JsonDeserializer localJsonDeserializer4 = localJsonDeserializer5;
          if ((localJsonDeserializer5 instanceof ContextualDeserializer)) {
            localJsonDeserializer4 = ((ContextualDeserializer)localJsonDeserializer5).createContextual(paramDeserializationContext, paramBeanProperty);
          }
          if ((localJsonDeserializer1 == _mapDeserializer) && (localJsonDeserializer2 == _listDeserializer) && (localJsonDeserializer3 == _stringDeserializer))
          {
            paramDeserializationContext = this;
            if (localJsonDeserializer4 == _numberDeserializer) {}
          }
          else
          {
            paramDeserializationContext = _withResolved(localJsonDeserializer1, localJsonDeserializer2, localJsonDeserializer3, localJsonDeserializer4);
          }
          return paramDeserializationContext;
        }
      }
    }
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    switch (paramJsonParser.getCurrentToken())
    {
    default: 
      throw paramDeserializationContext.mappingException(Object.class);
    case ???: 
    case ???: 
      if (_mapDeserializer != null) {
        return _mapDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return mapObject(paramJsonParser, paramDeserializationContext);
    case ???: 
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
        return mapArrayToArray(paramJsonParser, paramDeserializationContext);
      }
      if (_listDeserializer != null) {
        return _listDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return mapArray(paramJsonParser, paramDeserializationContext);
    case ???: 
      return paramJsonParser.getEmbeddedObject();
    case ???: 
      if (_stringDeserializer != null) {
        return _stringDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return paramJsonParser.getText();
    case ???: 
      if (_numberDeserializer != null) {
        return _numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
        return paramJsonParser.getBigIntegerValue();
      }
      return paramJsonParser.getNumberValue();
    case ???: 
      if (_numberDeserializer != null) {
        return _numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
        return paramJsonParser.getDecimalValue();
      }
      return Double.valueOf(paramJsonParser.getDoubleValue());
    case ???: 
      return Boolean.TRUE;
    case ???: 
      return Boolean.FALSE;
    }
    return null;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    switch (localJsonToken)
    {
    default: 
      throw paramDeserializationContext.mappingException(Object.class);
    case ???: 
    case ???: 
    case ???: 
      return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
    case ???: 
      if (_stringDeserializer != null) {
        return _stringDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      return paramJsonParser.getText();
    case ???: 
      if (_numberDeserializer != null) {
        return _numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
        return paramJsonParser.getBigIntegerValue();
      }
      return paramJsonParser.getNumberValue();
    case ???: 
      if (_numberDeserializer != null) {
        return _numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
        return paramJsonParser.getDecimalValue();
      }
      return Double.valueOf(paramJsonParser.getDoubleValue());
    case ???: 
      return Boolean.TRUE;
    case ???: 
      return Boolean.FALSE;
    case ???: 
      return paramJsonParser.getEmbeddedObject();
    }
    return null;
  }
  
  protected Object mapArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return new ArrayList(4);
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object localObject2 = localObjectBuffer.resetAndStart();
    int i = 0;
    int j = 0;
    Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
    int k = j + 1;
    Object localObject1;
    if (i >= localObject2.length)
    {
      localObject1 = localObjectBuffer.appendCompletedChunk((Object[])localObject2);
      i = 0;
    }
    for (;;)
    {
      int m = i + 1;
      localObject1[i] = localObject3;
      j = k;
      i = m;
      localObject2 = localObject1;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        break;
      }
      paramJsonParser = new ArrayList(k + (k >> 3) + 1);
      localObjectBuffer.completeAndClearBuffer((Object[])localObject1, m, paramJsonParser);
      return paramJsonParser;
      localObject1 = localObject2;
    }
  }
  
  protected Object[] mapArrayToArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return NO_OBJECTS;
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object localObject1 = localObjectBuffer.resetAndStart();
    int i = 0;
    Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
    Object localObject2;
    if (i >= localObject1.length)
    {
      localObject2 = localObjectBuffer.appendCompletedChunk((Object[])localObject1);
      i = 0;
    }
    for (;;)
    {
      int j = i + 1;
      localObject2[i] = localObject3;
      i = j;
      localObject1 = localObject2;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        break;
      }
      return localObjectBuffer.completeAndClearBuffer((Object[])localObject2, j);
      localObject2 = localObject1;
    }
  }
  
  protected Object mapObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    if (localObject1 != JsonToken.FIELD_NAME) {
      return new LinkedHashMap(4);
    }
    localObject1 = paramJsonParser.getText();
    paramJsonParser.nextToken();
    localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME)
    {
      paramJsonParser = new LinkedHashMap(4);
      paramJsonParser.put(localObject1, localObject2);
      return paramJsonParser;
    }
    String str = paramJsonParser.getText();
    paramJsonParser.nextToken();
    Object localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME)
    {
      paramJsonParser = new LinkedHashMap(4);
      paramJsonParser.put(localObject1, localObject2);
      paramJsonParser.put(str, localObject3);
      return paramJsonParser;
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put(localObject1, localObject2);
    localLinkedHashMap.put(str, localObject3);
    do
    {
      localObject1 = paramJsonParser.getText();
      paramJsonParser.nextToken();
      localLinkedHashMap.put(localObject1, deserialize(paramJsonParser, paramDeserializationContext));
    } while (paramJsonParser.nextToken() != JsonToken.END_OBJECT);
    return localLinkedHashMap;
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    JavaType localJavaType1 = paramDeserializationContext.constructType(Object.class);
    JavaType localJavaType2 = paramDeserializationContext.constructType(String.class);
    TypeFactory localTypeFactory = paramDeserializationContext.getTypeFactory();
    _mapDeserializer = _findCustomDeser(paramDeserializationContext, localTypeFactory.constructMapType(Map.class, localJavaType2, localJavaType1));
    _listDeserializer = _findCustomDeser(paramDeserializationContext, localTypeFactory.constructCollectionType(List.class, localJavaType1));
    _stringDeserializer = _findCustomDeser(paramDeserializationContext, localJavaType2);
    _numberDeserializer = _findCustomDeser(paramDeserializationContext, localTypeFactory.constructType(Number.class));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */