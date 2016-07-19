package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache.TypeKey;
import java.util.HashMap;

public final class ReadOnlyClassToSerializerMap
{
  protected SerializerCache.TypeKey _cacheKey = null;
  protected final JsonSerializerMap _map;
  
  private ReadOnlyClassToSerializerMap(JsonSerializerMap paramJsonSerializerMap)
  {
    _map = paramJsonSerializerMap;
  }
  
  public static ReadOnlyClassToSerializerMap from(HashMap<SerializerCache.TypeKey, JsonSerializer<Object>> paramHashMap)
  {
    return new ReadOnlyClassToSerializerMap(new JsonSerializerMap(paramHashMap));
  }
  
  public ReadOnlyClassToSerializerMap instance()
  {
    return new ReadOnlyClassToSerializerMap(_map);
  }
  
  public JsonSerializer<Object> typedValueSerializer(JavaType paramJavaType)
  {
    if (_cacheKey == null) {
      _cacheKey = new SerializerCache.TypeKey(paramJavaType, true);
    }
    for (;;)
    {
      return _map.find(_cacheKey);
      _cacheKey.resetTyped(paramJavaType);
    }
  }
  
  public JsonSerializer<Object> typedValueSerializer(Class<?> paramClass)
  {
    if (_cacheKey == null) {
      _cacheKey = new SerializerCache.TypeKey(paramClass, true);
    }
    for (;;)
    {
      return _map.find(_cacheKey);
      _cacheKey.resetTyped(paramClass);
    }
  }
  
  public JsonSerializer<Object> untypedValueSerializer(JavaType paramJavaType)
  {
    if (_cacheKey == null) {
      _cacheKey = new SerializerCache.TypeKey(paramJavaType, false);
    }
    for (;;)
    {
      return _map.find(_cacheKey);
      _cacheKey.resetUntyped(paramJavaType);
    }
  }
  
  public JsonSerializer<Object> untypedValueSerializer(Class<?> paramClass)
  {
    if (_cacheKey == null) {
      _cacheKey = new SerializerCache.TypeKey(paramClass, false);
    }
    for (;;)
    {
      return _map.find(_cacheKey);
      _cacheKey.resetUntyped(paramClass);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */