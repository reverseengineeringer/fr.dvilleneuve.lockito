package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class DeserializerCache
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap(64, 0.75F, 2);
  protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap(8);
  
  private Class<?> _verifyAsClass(Object paramObject, String paramString, Class<?> paramClass)
  {
    if (paramObject == null) {
      paramObject = null;
    }
    do
    {
      return (Class<?>)paramObject;
      if (!(paramObject instanceof Class)) {
        throw new IllegalStateException("AnnotationIntrospector." + paramString + "() returned value of type " + paramObject.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
      }
      paramString = (Class)paramObject;
      if (paramString == paramClass) {
        break;
      }
      paramObject = paramString;
    } while (paramString != NoClass.class);
    return null;
  }
  
  private JavaType modifyTypeByAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, JavaType paramJavaType)
    throws JsonMappingException
  {
    Object localObject2 = paramDeserializationContext.getAnnotationIntrospector();
    Object localObject3 = ((AnnotationIntrospector)localObject2).findDeserializationType(paramAnnotated, paramJavaType);
    if (localObject3 != null) {}
    for (;;)
    {
      Object localObject1;
      try
      {
        localObject1 = paramJavaType.narrowBy((Class)localObject3);
        paramJavaType = (JavaType)localObject1;
        localObject1 = paramJavaType;
        if (paramJavaType.isContainerType())
        {
          localObject3 = ((AnnotationIntrospector)localObject2).findDeserializationKeyType(paramAnnotated, paramJavaType.getKeyType());
          if (localObject3 == null) {
            break label486;
          }
          if (!(paramJavaType instanceof MapLikeType)) {
            throw new JsonMappingException("Illegal key-type annotation: type " + paramJavaType + " is not a Map(-like) type");
          }
        }
      }
      catch (IllegalArgumentException paramDeserializationContext)
      {
        throw new JsonMappingException("Failed to narrow type " + paramJavaType + " with concrete-type annotation (value " + ((Class)localObject3).getName() + "), method '" + paramAnnotated.getName() + "': " + paramDeserializationContext.getMessage(), null, paramDeserializationContext);
      }
      for (;;)
      {
        try
        {
          localObject1 = ((MapLikeType)paramJavaType).narrowKey((Class)localObject3);
          localObject3 = ((JavaType)localObject1).getKeyType();
          paramJavaType = (JavaType)localObject1;
          if (localObject3 != null)
          {
            paramJavaType = (JavaType)localObject1;
            if (((JavaType)localObject3).getValueHandler() == null)
            {
              localObject3 = ((AnnotationIntrospector)localObject2).findKeyDeserializer(paramAnnotated);
              paramJavaType = (JavaType)localObject1;
              if (localObject3 != null)
              {
                localObject3 = paramDeserializationContext.keyDeserializerInstance(paramAnnotated, localObject3);
                paramJavaType = (JavaType)localObject1;
                if (localObject3 != null)
                {
                  paramJavaType = ((MapLikeType)localObject1).withKeyValueHandler(localObject3);
                  paramJavaType.getKeyType();
                }
              }
            }
          }
          localObject3 = ((AnnotationIntrospector)localObject2).findDeserializationContentType(paramAnnotated, paramJavaType.getContentType());
          if (localObject3 == null) {
            break label483;
          }
          localObject1 = _verifyAsClass(localObject2, "findContentDeserializer", JsonDeserializer.None.class);
        }
        catch (IllegalArgumentException paramDeserializationContext)
        {
          try
          {
            localObject1 = paramJavaType.narrowContentsBy((Class)localObject3);
            paramJavaType = (JavaType)localObject1;
            localObject1 = paramJavaType;
            if (paramJavaType.getContentType().getValueHandler() == null)
            {
              localObject2 = ((AnnotationIntrospector)localObject2).findContentDeserializer(paramAnnotated);
              localObject1 = paramJavaType;
              if (localObject2 != null)
              {
                if (!(localObject2 instanceof JsonDeserializer)) {
                  break label450;
                }
                paramDeserializationContext = (JsonDeserializer)localObject2;
                paramDeserializationContext = null;
                localObject1 = paramJavaType;
                if (paramDeserializationContext != null) {
                  localObject1 = paramJavaType.withContentValueHandler(paramDeserializationContext);
                }
              }
            }
            return (JavaType)localObject1;
          }
          catch (IllegalArgumentException paramDeserializationContext)
          {
            throw new JsonMappingException("Failed to narrow content type " + paramJavaType + " with content-type annotation (" + ((Class)localObject3).getName() + "): " + paramDeserializationContext.getMessage(), null, paramDeserializationContext);
          }
          paramDeserializationContext = paramDeserializationContext;
          throw new JsonMappingException("Failed to narrow key type " + paramJavaType + " with key-type annotation (" + ((Class)localObject3).getName() + "): " + paramDeserializationContext.getMessage(), null, paramDeserializationContext);
        }
        label450:
        if (localObject1 != null)
        {
          paramDeserializationContext = paramDeserializationContext.deserializerInstance(paramAnnotated, localObject1);
        }
        else
        {
          paramDeserializationContext = null;
          continue;
          label483:
          continue;
          label486:
          localObject1 = paramJavaType;
        }
      }
    }
  }
  
  protected JsonDeserializer<Object> _createAndCache2(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    boolean bool2;
    do
    {
      try
      {
        paramDeserializerFactory = _createDeserializer(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
        if (paramDeserializerFactory == null)
        {
          paramDeserializationContext = null;
          return paramDeserializationContext;
        }
      }
      catch (IllegalArgumentException paramDeserializationContext)
      {
        throw new JsonMappingException(paramDeserializationContext.getMessage(), null, paramDeserializationContext);
      }
      boolean bool1 = paramDeserializerFactory instanceof ResolvableDeserializer;
      bool2 = paramDeserializerFactory.isCachable();
      if (bool1)
      {
        _incompleteDeserializers.put(paramJavaType, paramDeserializerFactory);
        ((ResolvableDeserializer)paramDeserializerFactory).resolve(paramDeserializationContext);
        _incompleteDeserializers.remove(paramJavaType);
      }
      paramDeserializationContext = paramDeserializerFactory;
    } while (!bool2);
    _cachedDeserializers.put(paramJavaType, paramDeserializerFactory);
    return paramDeserializerFactory;
  }
  
  protected JsonDeserializer<Object> _createAndCacheValueDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    int i;
    synchronized (_incompleteDeserializers)
    {
      JsonDeserializer localJsonDeserializer = _findCachedDeserializer(paramJavaType);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
      i = _incompleteDeserializers.size();
      if (i > 0)
      {
        localJsonDeserializer = (JsonDeserializer)_incompleteDeserializers.get(paramJavaType);
        if (localJsonDeserializer != null) {
          return localJsonDeserializer;
        }
      }
    }
    try
    {
      paramDeserializationContext = _createAndCache2(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
      if ((i == 0) && (_incompleteDeserializers.size() > 0)) {
        _incompleteDeserializers.clear();
      }
      return paramDeserializationContext;
    }
    finally
    {
      paramDeserializationContext = finally;
      if ((i == 0) && (_incompleteDeserializers.size() > 0)) {
        _incompleteDeserializers.clear();
      }
      throw paramDeserializationContext;
    }
  }
  
  protected JsonDeserializer<Object> _createDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    if ((!paramJavaType.isAbstract()) && (!paramJavaType.isMapLikeType()))
    {
      localObject1 = paramJavaType;
      if (!paramJavaType.isCollectionLikeType()) {}
    }
    else
    {
      localObject1 = paramDeserializerFactory.mapAbstractType(localDeserializationConfig, paramJavaType);
    }
    paramJavaType = localDeserializationConfig.introspect((JavaType)localObject1);
    Object localObject2 = findDeserializerFromAnnotation(paramDeserializationContext, paramJavaType.getClassInfo());
    if (localObject2 != null) {
      return (JsonDeserializer<Object>)localObject2;
    }
    JavaType localJavaType = modifyTypeByAnnotation(paramDeserializationContext, paramJavaType.getClassInfo(), (JavaType)localObject1);
    localObject2 = localObject1;
    if (localJavaType != localObject1)
    {
      paramJavaType = localDeserializationConfig.introspect(localJavaType);
      localObject2 = localJavaType;
    }
    Object localObject1 = paramJavaType.findPOJOBuilder();
    if (localObject1 != null) {
      return paramDeserializerFactory.createBuilderBasedDeserializer(paramDeserializationContext, (JavaType)localObject2, paramJavaType, (Class)localObject1);
    }
    localObject1 = paramJavaType.findDeserializationConverter();
    if (localObject1 == null) {
      return _createDeserializer2(paramDeserializationContext, paramDeserializerFactory, (JavaType)localObject2, paramJavaType);
    }
    localJavaType = ((Converter)localObject1).getInputType(paramDeserializationContext.getTypeFactory());
    if (!localJavaType.hasRawClass(((JavaType)localObject2).getRawClass())) {
      paramJavaType = localDeserializationConfig.introspect(localJavaType);
    }
    return new StdDelegatingDeserializer((Converter)localObject1, localJavaType, _createDeserializer2(paramDeserializationContext, paramDeserializerFactory, localJavaType, paramJavaType));
  }
  
  protected JsonDeserializer<?> _createDeserializer2(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    if (paramJavaType.isEnumType()) {
      return paramDeserializerFactory.createEnumDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
    }
    if (paramJavaType.isContainerType())
    {
      if (paramJavaType.isArrayType()) {
        return paramDeserializerFactory.createArrayDeserializer(paramDeserializationContext, (ArrayType)paramJavaType, paramBeanDescription);
      }
      if (paramJavaType.isMapLikeType())
      {
        paramJavaType = (MapLikeType)paramJavaType;
        if (paramJavaType.isTrueMapType()) {
          return paramDeserializerFactory.createMapDeserializer(paramDeserializationContext, (MapType)paramJavaType, paramBeanDescription);
        }
        return paramDeserializerFactory.createMapLikeDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
      }
      if (paramJavaType.isCollectionLikeType())
      {
        JsonFormat.Value localValue = paramBeanDescription.findExpectedFormat(null);
        if ((localValue == null) || (localValue.getShape() != JsonFormat.Shape.OBJECT))
        {
          paramJavaType = (CollectionLikeType)paramJavaType;
          if (paramJavaType.isTrueCollectionType()) {
            return paramDeserializerFactory.createCollectionDeserializer(paramDeserializationContext, (CollectionType)paramJavaType, paramBeanDescription);
          }
          return paramDeserializerFactory.createCollectionLikeDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
        }
      }
    }
    if (JsonNode.class.isAssignableFrom(paramJavaType.getRawClass())) {
      return paramDeserializerFactory.createTreeDeserializer(localDeserializationConfig, paramJavaType, paramBeanDescription);
    }
    return paramDeserializerFactory.createBeanDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
  }
  
  protected JsonDeserializer<Object> _findCachedDeserializer(JavaType paramJavaType)
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("Null JavaType passed");
    }
    return (JsonDeserializer)_cachedDeserializers.get(paramJavaType);
  }
  
  protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + paramJavaType);
  }
  
  protected JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    if (!ClassUtil.isConcrete(paramJavaType.getRawClass())) {
      throw new JsonMappingException("Can not find a Value deserializer for abstract type " + paramJavaType);
    }
    throw new JsonMappingException("Can not find a Value deserializer for type " + paramJavaType);
  }
  
  public int cachedDeserializersCount()
  {
    return _cachedDeserializers.size();
  }
  
  protected Converter<Object, Object> findConverter(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findDeserializationConverter(paramAnnotated);
    if (localObject == null) {
      return null;
    }
    return paramDeserializationContext.converterInstance(paramAnnotated, localObject);
  }
  
  protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, JsonDeserializer<Object> paramJsonDeserializer)
    throws JsonMappingException
  {
    paramAnnotated = findConverter(paramDeserializationContext, paramAnnotated);
    if (paramAnnotated == null) {
      return paramJsonDeserializer;
    }
    return new StdDelegatingDeserializer(paramAnnotated, paramAnnotated.getInputType(paramDeserializationContext.getTypeFactory()), paramJsonDeserializer);
  }
  
  protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findDeserializer(paramAnnotated);
    if (localObject == null) {
      return null;
    }
    return findConvertingDeserializer(paramDeserializationContext, paramAnnotated, paramDeserializationContext.deserializerInstance(paramAnnotated, localObject));
  }
  
  public KeyDeserializer findKeyDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    KeyDeserializer localKeyDeserializer = paramDeserializerFactory.createKeyDeserializer(paramDeserializationContext, paramJavaType);
    if (localKeyDeserializer == null) {
      paramDeserializerFactory = _handleUnknownKeyDeserializer(paramJavaType);
    }
    do
    {
      return paramDeserializerFactory;
      paramDeserializerFactory = localKeyDeserializer;
    } while (!(localKeyDeserializer instanceof ResolvableDeserializer));
    ((ResolvableDeserializer)localKeyDeserializer).resolve(paramDeserializationContext);
    return localKeyDeserializer;
  }
  
  public JsonDeserializer<Object> findValueDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = _findCachedDeserializer(paramJavaType);
    Object localObject = localJsonDeserializer;
    if (localJsonDeserializer == null)
    {
      paramDeserializationContext = _createAndCacheValueDeserializer(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
      localObject = paramDeserializationContext;
      if (paramDeserializationContext == null) {
        localObject = _handleUnknownValueDeserializer(paramJavaType);
      }
    }
    return (JsonDeserializer<Object>)localObject;
  }
  
  public void flushCachedDeserializers()
  {
    _cachedDeserializers.clear();
  }
  
  public boolean hasValueDeserializerFor(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer2 = _findCachedDeserializer(paramJavaType);
    JsonDeserializer localJsonDeserializer1 = localJsonDeserializer2;
    if (localJsonDeserializer2 == null) {
      localJsonDeserializer1 = _createAndCacheValueDeserializer(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
    }
    return localJsonDeserializer1 != null;
  }
  
  Object writeReplace()
  {
    _incompleteDeserializers.clear();
    return this;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.DeserializerCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */