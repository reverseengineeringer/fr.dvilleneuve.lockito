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
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer
  extends StdDeserializer<EnumMap<?, ?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1518773374647478964L;
  protected final Class<?> _enumClass;
  protected JsonDeserializer<Enum<?>> _keyDeserializer;
  protected final JavaType _mapType;
  protected JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  @Deprecated
  public EnumMapDeserializer(JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2)
  {
    this(paramJavaType, paramJsonDeserializer1, paramJsonDeserializer2, null);
  }
  
  public EnumMapDeserializer(JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer)
  {
    super(EnumMap.class);
    _mapType = paramJavaType;
    _enumClass = paramJavaType.getKeyType().getRawClass();
    _keyDeserializer = paramJsonDeserializer1;
    _valueDeserializer = paramJsonDeserializer2;
    _valueTypeDeserializer = paramTypeDeserializer;
  }
  
  private EnumMap<?, ?> constructMap()
  {
    return new EnumMap(_enumClass);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = _keyDeserializer;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = paramDeserializationContext.findContextualValueDeserializer(_mapType.getKeyType(), paramBeanProperty);
    }
    localObject2 = _valueDeserializer;
    if (localObject2 == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer(_mapType.getContentType(), paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty))
    {
      TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
      localObject2 = localTypeDeserializer;
      if (localTypeDeserializer != null) {
        localObject2 = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withResolved((JsonDeserializer)localObject1, paramDeserializationContext, (TypeDeserializer)localObject2);
    }
  }
  
  public EnumMap<?, ?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      throw paramDeserializationContext.mappingException(EnumMap.class);
    }
    EnumMap localEnumMap = constructMap();
    JsonDeserializer localJsonDeserializer = _valueDeserializer;
    TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
    Enum localEnum;
    if (paramJsonParser.nextToken() != JsonToken.END_OBJECT)
    {
      localEnum = (Enum)_keyDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      if (localEnum == null) {
        if (paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {}
      }
    }
    for (;;)
    {
      try
      {
        if (!paramJsonParser.hasCurrentToken()) {
          break label173;
        }
        paramJsonParser = paramJsonParser.getText();
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _enumClass, "value not one of declared Enum instance names");
      }
      catch (Exception paramJsonParser)
      {
        paramJsonParser = null;
        continue;
      }
      paramJsonParser.nextToken();
      paramJsonParser.skipChildren();
      break;
      Object localObject;
      if (paramJsonParser.nextToken() == JsonToken.VALUE_NULL) {
        localObject = null;
      }
      for (;;)
      {
        localEnumMap.put(localEnum, localObject);
        break;
        if (localTypeDeserializer == null) {
          localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        } else {
          localObject = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
        }
      }
      return localEnumMap;
      label173:
      paramJsonParser = null;
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  @Deprecated
  public EnumMapDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2)
  {
    return withResolved(paramJsonDeserializer1, paramJsonDeserializer2, null);
  }
  
  public EnumMapDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer)
  {
    if ((paramJsonDeserializer1 == _keyDeserializer) && (paramJsonDeserializer2 == _valueDeserializer) && (paramTypeDeserializer == _valueTypeDeserializer)) {
      return this;
    }
    return new EnumMapDeserializer(_mapType, paramJsonDeserializer1, paramJsonDeserializer2, _valueTypeDeserializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */