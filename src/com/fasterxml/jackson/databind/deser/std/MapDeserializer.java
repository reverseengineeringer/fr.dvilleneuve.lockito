package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer
  extends ContainerDeserializerBase<Map<Object, Object>>
  implements ContextualDeserializer, ResolvableDeserializer
{
  private static final long serialVersionUID = -3378654289961736240L;
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected final boolean _hasDefaultCreator;
  protected HashSet<String> _ignorableProperties;
  protected final KeyDeserializer _keyDeserializer;
  protected final JavaType _mapType;
  protected PropertyBasedCreator _propertyBasedCreator;
  protected boolean _standardStringKey;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    _mapType = paramJavaType;
    _keyDeserializer = paramKeyDeserializer;
    _valueDeserializer = paramJsonDeserializer;
    _valueTypeDeserializer = paramTypeDeserializer;
    _valueInstantiator = paramValueInstantiator;
    _hasDefaultCreator = paramValueInstantiator.canCreateUsingDefault();
    _delegateDeserializer = null;
    _propertyBasedCreator = null;
    _standardStringKey = _isStdKeyDeser(paramJavaType, paramKeyDeserializer);
  }
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer)
  {
    super(_mapType);
    _mapType = _mapType;
    _keyDeserializer = _keyDeserializer;
    _valueDeserializer = _valueDeserializer;
    _valueTypeDeserializer = _valueTypeDeserializer;
    _valueInstantiator = _valueInstantiator;
    _propertyBasedCreator = _propertyBasedCreator;
    _delegateDeserializer = _delegateDeserializer;
    _hasDefaultCreator = _hasDefaultCreator;
    _ignorableProperties = _ignorableProperties;
    _standardStringKey = _standardStringKey;
  }
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, HashSet<String> paramHashSet)
  {
    super(_mapType);
    _mapType = _mapType;
    _keyDeserializer = paramKeyDeserializer;
    _valueDeserializer = paramJsonDeserializer;
    _valueTypeDeserializer = paramTypeDeserializer;
    _valueInstantiator = _valueInstantiator;
    _propertyBasedCreator = _propertyBasedCreator;
    _delegateDeserializer = _delegateDeserializer;
    _hasDefaultCreator = _hasDefaultCreator;
    _ignorableProperties = paramHashSet;
    _standardStringKey = _isStdKeyDeser(_mapType, paramKeyDeserializer);
  }
  
  public Map<Object, Object> _deserializeUsingCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    PropertyBasedCreator localPropertyBasedCreator = _propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, null);
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    localObject2 = _valueDeserializer;
    TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
    if (localObject1 == JsonToken.FIELD_NAME)
    {
      Object localObject3 = paramJsonParser.getCurrentName();
      localObject1 = paramJsonParser.nextToken();
      if ((_ignorableProperties != null) && (_ignorableProperties.contains(localObject3))) {
        paramJsonParser.skipChildren();
      }
      do
      {
        localObject1 = paramJsonParser.nextToken();
        break;
        localObject3 = localPropertyBasedCreator.findCreatorProperty((String)localObject3);
        if (localObject3 == null) {
          break label177;
        }
        localObject1 = ((SettableBeanProperty)localObject3).deserialize(paramJsonParser, paramDeserializationContext);
      } while (!localPropertyValueBuffer.assignParameter(((SettableBeanProperty)localObject3).getCreatorIndex(), localObject1));
      paramJsonParser.nextToken();
      try
      {
        localObject1 = (Map)localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject1);
        return (Map<Object, Object>)localObject1;
      }
      catch (Exception paramJsonParser)
      {
        wrapAndThrow(paramJsonParser, _mapType.getRawClass());
        return null;
      }
      label177:
      localObject3 = paramJsonParser.getCurrentName();
      localObject3 = _keyDeserializer.deserializeKey((String)localObject3, paramDeserializationContext);
      if (localObject1 == JsonToken.VALUE_NULL) {
        localObject1 = null;
      }
      for (;;)
      {
        localPropertyValueBuffer.bufferMapProperty(localObject3, localObject1);
        break;
        if (localTypeDeserializer == null) {
          localObject1 = ((JsonDeserializer)localObject2).deserialize(paramJsonParser, paramDeserializationContext);
        } else {
          localObject1 = ((JsonDeserializer)localObject2).deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
        }
      }
    }
    try
    {
      paramJsonParser = (Map)localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
      return paramJsonParser;
    }
    catch (Exception paramJsonParser)
    {
      wrapAndThrow(paramJsonParser, _mapType.getRawClass());
    }
    return null;
  }
  
  protected final boolean _isStdKeyDeser(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer)
  {
    if (paramKeyDeserializer == null) {}
    do
    {
      do
      {
        return true;
        paramJavaType = paramJavaType.getKeyType();
      } while (paramJavaType == null);
      paramJavaType = paramJavaType.getRawClass();
    } while (((paramJavaType == String.class) || (paramJavaType == Object.class)) && (isDefaultKeyDeserializer(paramKeyDeserializer)));
    return false;
  }
  
  protected final void _readAndBind(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException, JsonProcessingException
  {
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    localObject2 = _keyDeserializer;
    JsonDeserializer localJsonDeserializer = _valueDeserializer;
    TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
    while (localObject1 == JsonToken.FIELD_NAME)
    {
      localObject1 = paramJsonParser.getCurrentName();
      Object localObject3 = ((KeyDeserializer)localObject2).deserializeKey((String)localObject1, paramDeserializationContext);
      JsonToken localJsonToken = paramJsonParser.nextToken();
      if ((_ignorableProperties != null) && (_ignorableProperties.contains(localObject1)))
      {
        paramJsonParser.skipChildren();
        localObject1 = paramJsonParser.nextToken();
      }
      else
      {
        if (localJsonToken == JsonToken.VALUE_NULL) {
          localObject1 = null;
        }
        for (;;)
        {
          paramMap.put(localObject3, localObject1);
          break;
          if (localTypeDeserializer == null) {
            localObject1 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject1 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
      }
    }
  }
  
  protected final void _readAndBindStringMap(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException, JsonProcessingException
  {
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    localObject2 = _valueDeserializer;
    TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
    while (localObject1 == JsonToken.FIELD_NAME)
    {
      String str = paramJsonParser.getCurrentName();
      localObject1 = paramJsonParser.nextToken();
      if ((_ignorableProperties != null) && (_ignorableProperties.contains(str)))
      {
        paramJsonParser.skipChildren();
        localObject1 = paramJsonParser.nextToken();
      }
      else
      {
        if (localObject1 == JsonToken.VALUE_NULL) {
          localObject1 = null;
        }
        for (;;)
        {
          paramMap.put(str, localObject1);
          break;
          if (localTypeDeserializer == null) {
            localObject1 = ((JsonDeserializer)localObject2).deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject1 = ((JsonDeserializer)localObject2).deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
      }
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = _keyDeserializer;
    Object localObject1;
    label56:
    Object localObject4;
    Object localObject3;
    String[] arrayOfString;
    if (localObject2 == null)
    {
      localObject1 = paramDeserializationContext.findKeyDeserializer(_mapType.getKeyType(), paramBeanProperty);
      localObject2 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, _valueDeserializer);
      if (localObject2 != null) {
        break label188;
      }
      localObject2 = paramDeserializationContext.findContextualValueDeserializer(_mapType.getContentType(), paramBeanProperty);
      localObject4 = _valueTypeDeserializer;
      localObject3 = localObject4;
      if (localObject4 != null) {
        localObject3 = ((TypeDeserializer)localObject4).forProperty(paramBeanProperty);
      }
      localObject4 = _ignorableProperties;
      paramDeserializationContext = paramDeserializationContext.getAnnotationIntrospector();
      if ((paramDeserializationContext == null) || (paramBeanProperty == null)) {
        break label213;
      }
      arrayOfString = paramDeserializationContext.findPropertiesToIgnore(paramBeanProperty.getMember());
      if (arrayOfString == null) {
        break label213;
      }
      if (localObject4 != null) {
        break label200;
      }
    }
    label188:
    label200:
    for (paramDeserializationContext = new HashSet();; paramDeserializationContext = new HashSet((Collection)localObject4))
    {
      int j = arrayOfString.length;
      int i = 0;
      for (;;)
      {
        paramBeanProperty = paramDeserializationContext;
        if (i >= j) {
          break;
        }
        paramDeserializationContext.add(arrayOfString[i]);
        i += 1;
      }
      localObject1 = localObject2;
      if (!(localObject2 instanceof ContextualKeyDeserializer)) {
        break;
      }
      localObject1 = ((ContextualKeyDeserializer)localObject2).createContextual(paramDeserializationContext, paramBeanProperty);
      break;
      localObject2 = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty);
      break label56;
    }
    label213:
    paramBeanProperty = (BeanProperty)localObject4;
    return withResolved((KeyDeserializer)localObject1, (TypeDeserializer)localObject3, (JsonDeserializer)localObject2, paramBeanProperty);
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_propertyBasedCreator != null) {
      return _deserializeUsingCreator(paramJsonParser, paramDeserializationContext);
    }
    if (_delegateDeserializer != null) {
      return (Map)_valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (!_hasDefaultCreator) {
      throw paramDeserializationContext.instantiationException(getMapClass(), "No default constructor found");
    }
    Object localObject = paramJsonParser.getCurrentToken();
    if ((localObject != JsonToken.START_OBJECT) && (localObject != JsonToken.FIELD_NAME) && (localObject != JsonToken.END_OBJECT))
    {
      if (localObject == JsonToken.VALUE_STRING) {
        return (Map)_valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
      }
      throw paramDeserializationContext.mappingException(getMapClass());
    }
    localObject = (Map)_valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (_standardStringKey)
    {
      _readAndBindStringMap(paramJsonParser, paramDeserializationContext, (Map)localObject);
      return (Map<Object, Object>)localObject;
    }
    _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject);
    return (Map<Object, Object>)localObject;
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken != JsonToken.START_OBJECT) && (localJsonToken != JsonToken.FIELD_NAME)) {
      throw paramDeserializationContext.mappingException(getMapClass());
    }
    if (_standardStringKey)
    {
      _readAndBindStringMap(paramJsonParser, paramDeserializationContext, paramMap);
      return paramMap;
    }
    _readAndBind(paramJsonParser, paramDeserializationContext, paramMap);
    return paramMap;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return _valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return _mapType.getContentType();
  }
  
  public final Class<?> getMapClass()
  {
    return _mapType.getRawClass();
  }
  
  public JavaType getValueType()
  {
    return _mapType;
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    Object localObject;
    if (_valueInstantiator.canCreateUsingDelegate())
    {
      localObject = _valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
      if (localObject == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + _mapType + ": value instantiator (" + _valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      _delegateDeserializer = findDeserializer(paramDeserializationContext, (JavaType)localObject, null);
    }
    if (_valueInstantiator.canCreateFromObjectWith())
    {
      localObject = _valueInstantiator.getFromObjectArguments(paramDeserializationContext.getConfig());
      _propertyBasedCreator = PropertyBasedCreator.construct(paramDeserializationContext, _valueInstantiator, (SettableBeanProperty[])localObject);
    }
    _standardStringKey = _isStdKeyDeser(_mapType, _keyDeserializer);
  }
  
  public void setIgnorableProperties(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    for (paramArrayOfString = null;; paramArrayOfString = ArrayBuilders.arrayToSet(paramArrayOfString))
    {
      _ignorableProperties = paramArrayOfString;
      return;
    }
  }
  
  protected MapDeserializer withResolved(KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer, HashSet<String> paramHashSet)
  {
    if ((_keyDeserializer == paramKeyDeserializer) && (_valueDeserializer == paramJsonDeserializer) && (_valueTypeDeserializer == paramTypeDeserializer) && (_ignorableProperties == paramHashSet)) {
      return this;
    }
    return new MapDeserializer(this, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer, paramHashSet);
  }
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if (((paramThrowable instanceof IOException)) && (!(paramThrowable instanceof JsonMappingException))) {
      throw ((IOException)paramThrowable);
    }
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, null);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.MapDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */