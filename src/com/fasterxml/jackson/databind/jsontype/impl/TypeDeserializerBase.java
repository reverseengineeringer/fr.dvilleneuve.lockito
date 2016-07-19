package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public abstract class TypeDeserializerBase
  extends TypeDeserializer
  implements Serializable
{
  private static final long serialVersionUID = 278445030337366675L;
  protected final JavaType _baseType;
  protected final JavaType _defaultImpl;
  protected JsonDeserializer<Object> _defaultImplDeserializer;
  protected final HashMap<String, JsonDeserializer<Object>> _deserializers;
  protected final TypeIdResolver _idResolver;
  protected final BeanProperty _property;
  protected final boolean _typeIdVisible;
  protected final String _typePropertyName;
  
  protected TypeDeserializerBase(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, String paramString, boolean paramBoolean, Class<?> paramClass)
  {
    _baseType = paramJavaType;
    _idResolver = paramTypeIdResolver;
    _typePropertyName = paramString;
    _typeIdVisible = paramBoolean;
    _deserializers = new HashMap();
    if (paramClass == null) {}
    for (_defaultImpl = null;; _defaultImpl = paramJavaType.forcedNarrowBy(paramClass))
    {
      _property = null;
      return;
    }
  }
  
  protected TypeDeserializerBase(TypeDeserializerBase paramTypeDeserializerBase, BeanProperty paramBeanProperty)
  {
    _baseType = _baseType;
    _idResolver = _idResolver;
    _typePropertyName = _typePropertyName;
    _typeIdVisible = _typeIdVisible;
    _deserializers = _deserializers;
    _defaultImpl = _defaultImpl;
    _defaultImplDeserializer = _defaultImplDeserializer;
    _property = paramBeanProperty;
  }
  
  protected Object _deserializeWithNativeTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramJsonParser.getTypeId();
    if (localObject == null)
    {
      if (_defaultImpl != null)
      {
        localObject = _findDefaultImplDeserializer(paramDeserializationContext);
        return ((JsonDeserializer)localObject).deserialize(paramJsonParser, paramDeserializationContext);
      }
      throw paramDeserializationContext.mappingException("No (native) type id found when one was expected for polymorphic type handling");
    }
    if ((localObject instanceof String)) {}
    for (localObject = (String)localObject;; localObject = String.valueOf(localObject))
    {
      localObject = _findDeserializer(paramDeserializationContext, (String)localObject);
      break;
    }
  }
  
  protected final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_defaultImpl == null)
    {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
        return NullifyingDeserializer.instance;
      }
      return null;
    }
    if (_defaultImpl.getRawClass() == NoClass.class) {
      return NullifyingDeserializer.instance;
    }
    synchronized (_defaultImpl)
    {
      if (_defaultImplDeserializer == null) {
        _defaultImplDeserializer = paramDeserializationContext.findContextualValueDeserializer(_defaultImpl, _property);
      }
      paramDeserializationContext = _defaultImplDeserializer;
      return paramDeserializationContext;
    }
  }
  
  protected final JsonDeserializer<Object> _findDeserializer(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException, JsonProcessingException
  {
    Object localObject2;
    Object localObject1;
    for (;;)
    {
      synchronized (_deserializers)
      {
        localObject2 = (JsonDeserializer)_deserializers.get(paramString);
        localObject1 = localObject2;
        if (localObject2 != null) {
          break label112;
        }
        if ((_idResolver instanceof TypeIdResolverBase))
        {
          localObject1 = ((TypeIdResolverBase)_idResolver).typeFromId(paramDeserializationContext, paramString);
          if (localObject1 != null) {
            break label117;
          }
          if (_defaultImpl != null) {
            break;
          }
          throw paramDeserializationContext.unknownTypeException(_baseType, paramString);
        }
      }
      localObject1 = _idResolver.typeFromId(paramString);
    }
    for (paramDeserializationContext = _findDefaultImplDeserializer(paramDeserializationContext);; paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer((JavaType)localObject2, _property))
    {
      _deserializers.put(paramString, paramDeserializationContext);
      localObject1 = paramDeserializationContext;
      label112:
      return (JsonDeserializer<Object>)localObject1;
      label117:
      localObject2 = localObject1;
      if (_baseType != null)
      {
        localObject2 = localObject1;
        if (_baseType.getClass() == localObject1.getClass()) {
          localObject2 = _baseType.narrowBy(((JavaType)localObject1).getRawClass());
        }
      }
    }
  }
  
  public String baseTypeName()
  {
    return _baseType.getRawClass().getName();
  }
  
  public abstract TypeDeserializer forProperty(BeanProperty paramBeanProperty);
  
  public Class<?> getDefaultImpl()
  {
    if (_defaultImpl == null) {
      return null;
    }
    return _defaultImpl.getRawClass();
  }
  
  public final String getPropertyName()
  {
    return _typePropertyName;
  }
  
  public TypeIdResolver getTypeIdResolver()
  {
    return _idResolver;
  }
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[').append(getClass().getName());
    localStringBuilder.append("; base-type:").append(_baseType);
    localStringBuilder.append("; id-resolver: ").append(_idResolver);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */