package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class DefaultDeserializationContext
  extends DeserializationContext
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> _objectIds;
  
  protected DefaultDeserializationContext(DefaultDeserializationContext paramDefaultDeserializationContext, DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues)
  {
    super(paramDefaultDeserializationContext, paramDeserializationConfig, paramJsonParser, paramInjectableValues);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext paramDefaultDeserializationContext, DeserializerFactory paramDeserializerFactory)
  {
    super(paramDefaultDeserializationContext, paramDeserializerFactory);
  }
  
  protected DefaultDeserializationContext(DeserializerFactory paramDeserializerFactory, DeserializerCache paramDeserializerCache)
  {
    super(paramDeserializerFactory, paramDeserializerCache);
  }
  
  public abstract DefaultDeserializationContext createInstance(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues);
  
  public JsonDeserializer<Object> deserializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (paramObject == null) {
      paramObject = localObject2;
    }
    Class localClass;
    do
    {
      do
      {
        do
        {
          return (JsonDeserializer<Object>)paramObject;
          if (!(paramObject instanceof JsonDeserializer)) {
            break;
          }
          paramAnnotated = (JsonDeserializer)paramObject;
          paramObject = paramAnnotated;
        } while (!(paramAnnotated instanceof ResolvableDeserializer));
        ((ResolvableDeserializer)paramAnnotated).resolve(this);
        return paramAnnotated;
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + paramObject.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
        }
        localClass = (Class)paramObject;
        paramObject = localObject2;
      } while (localClass == JsonDeserializer.None.class);
      paramObject = localObject2;
    } while (localClass == NoClass.class);
    if (!JsonDeserializer.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<JsonDeserializer>");
    }
    paramObject = _config.getHandlerInstantiator();
    if (paramObject == null) {}
    for (paramObject = localObject1;; paramObject = ((HandlerInstantiator)paramObject).deserializerInstance(_config, paramAnnotated, localClass))
    {
      paramAnnotated = (Annotated)paramObject;
      if (paramObject != null) {
        break;
      }
      paramAnnotated = (JsonDeserializer)ClassUtil.createInstance(localClass, _config.canOverrideAccessModifiers());
      break;
    }
  }
  
  public ReadableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    paramObjectIdGenerator = paramObjectIdGenerator.key(paramObject);
    if (_objectIds == null) {
      _objectIds = new LinkedHashMap();
    }
    ReadableObjectId localReadableObjectId;
    do
    {
      paramObject = new ReadableObjectId(paramObject);
      _objectIds.put(paramObjectIdGenerator, paramObject);
      return (ReadableObjectId)paramObject;
      localReadableObjectId = (ReadableObjectId)_objectIds.get(paramObjectIdGenerator);
    } while (localReadableObjectId == null);
    return localReadableObjectId;
  }
  
  public final KeyDeserializer keyDeserializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (paramObject == null) {
      paramObject = localObject2;
    }
    Class localClass;
    do
    {
      do
      {
        do
        {
          return (KeyDeserializer)paramObject;
          if (!(paramObject instanceof KeyDeserializer)) {
            break;
          }
          paramAnnotated = (KeyDeserializer)paramObject;
          paramObject = paramAnnotated;
        } while (!(paramAnnotated instanceof ResolvableDeserializer));
        ((ResolvableDeserializer)paramAnnotated).resolve(this);
        return paramAnnotated;
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + paramObject.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        localClass = (Class)paramObject;
        paramObject = localObject2;
      } while (localClass == KeyDeserializer.None.class);
      paramObject = localObject2;
    } while (localClass == NoClass.class);
    if (!KeyDeserializer.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<KeyDeserializer>");
    }
    paramObject = _config.getHandlerInstantiator();
    if (paramObject == null) {}
    for (paramObject = localObject1;; paramObject = ((HandlerInstantiator)paramObject).keyDeserializerInstance(_config, paramAnnotated, localClass))
    {
      paramAnnotated = (Annotated)paramObject;
      if (paramObject != null) {
        break;
      }
      paramAnnotated = (KeyDeserializer)ClassUtil.createInstance(localClass, _config.canOverrideAccessModifiers());
      break;
    }
  }
  
  public abstract DefaultDeserializationContext with(DeserializerFactory paramDeserializerFactory);
  
  public static final class Impl
    extends DefaultDeserializationContext
  {
    private static final long serialVersionUID = 1L;
    
    protected Impl(Impl paramImpl, DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues)
    {
      super(paramDeserializationConfig, paramJsonParser, paramInjectableValues);
    }
    
    protected Impl(Impl paramImpl, DeserializerFactory paramDeserializerFactory)
    {
      super(paramDeserializerFactory);
    }
    
    public Impl(DeserializerFactory paramDeserializerFactory)
    {
      super(null);
    }
    
    public DefaultDeserializationContext createInstance(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues)
    {
      return new Impl(this, paramDeserializationConfig, paramJsonParser, paramInjectableValues);
    }
    
    public DefaultDeserializationContext with(DeserializerFactory paramDeserializerFactory)
    {
      return new Impl(this, paramDeserializerFactory);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */