package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DefaultSerializerProvider
  extends SerializerProvider
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
  protected transient Map<Object, WritableObjectId> _seenObjectIds;
  
  protected DefaultSerializerProvider() {}
  
  protected DefaultSerializerProvider(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    super(paramSerializerProvider, paramSerializationConfig, paramSerializerFactory);
  }
  
  protected Map<Object, WritableObjectId> _createObjectIdMap()
  {
    if (isEnabled(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)) {
      return new HashMap();
    }
    return new IdentityHashMap();
  }
  
  protected void _serializeNull(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    Object localObject = getDefaultNullValueSerializer();
    try
    {
      ((JsonSerializer)localObject).serialize(null, paramJsonGenerator, this);
      return;
    }
    catch (IOException paramJsonGenerator)
    {
      throw paramJsonGenerator;
    }
    catch (Exception localException)
    {
      localObject = localException.getMessage();
      paramJsonGenerator = (JsonGenerator)localObject;
      if (localObject == null) {
        paramJsonGenerator = "[no message for " + localException.getClass().getName() + "]";
      }
      throw new JsonMappingException(paramJsonGenerator, localException);
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("A class must be provided");
    }
    paramJsonFormatVisitorWrapper.setProvider(this);
    findValueSerializer(paramJavaType, null).acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType);
  }
  
  public int cachedSerializersCount()
  {
    return _serializerCache.size();
  }
  
  public abstract DefaultSerializerProvider createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory);
  
  public WritableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    Object localObject1;
    if (_seenObjectIds == null)
    {
      _seenObjectIds = _createObjectIdMap();
      if (_objectIdGenerators != null) {
        break label111;
      }
      _objectIdGenerators = new ArrayList(8);
      localObject1 = null;
    }
    for (;;)
    {
      Object localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = paramObjectIdGenerator.newForSerialization(this);
        _objectIdGenerators.add(localObject2);
      }
      paramObjectIdGenerator = new WritableObjectId((ObjectIdGenerator)localObject2);
      _seenObjectIds.put(paramObject, paramObjectIdGenerator);
      return paramObjectIdGenerator;
      localObject1 = (WritableObjectId)_seenObjectIds.get(paramObject);
      if (localObject1 == null) {
        break;
      }
      return (WritableObjectId)localObject1;
      label111:
      int j = _objectIdGenerators.size();
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label161;
        }
        localObject2 = (ObjectIdGenerator)_objectIdGenerators.get(i);
        localObject1 = localObject2;
        if (((ObjectIdGenerator)localObject2).canUseFor(paramObjectIdGenerator)) {
          break;
        }
        i += 1;
      }
      label161:
      localObject1 = null;
    }
  }
  
  public void flushCachedSerializers()
  {
    _serializerCache.flush();
  }
  
  public JsonSchema generateJsonSchema(Class<?> paramClass)
    throws JsonMappingException
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("A class must be provided");
    }
    Object localObject = findValueSerializer(paramClass, null);
    if ((localObject instanceof SchemaAware)) {}
    for (localObject = ((SchemaAware)localObject).getSchema(this, null); !(localObject instanceof ObjectNode); localObject = JsonSchema.getDefaultSchemaNode()) {
      throw new IllegalArgumentException("Class " + paramClass.getName() + " would not be serialized as a JSON object and therefore has no schema");
    }
    return new JsonSchema((ObjectNode)localObject);
  }
  
  @Deprecated
  public boolean hasSerializerFor(Class<?> paramClass)
  {
    return hasSerializerFor(paramClass, null);
  }
  
  public boolean hasSerializerFor(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    boolean bool = false;
    try
    {
      paramClass = _findExplicitUntypedSerializer(paramClass);
      if (paramClass != null) {
        bool = true;
      }
    }
    catch (JsonMappingException paramClass)
    {
      while (paramAtomicReference == null) {}
      paramAtomicReference.set(paramClass);
      return false;
    }
    catch (RuntimeException paramClass)
    {
      if (paramAtomicReference != null) {
        break label35;
      }
      throw paramClass;
      label35:
      paramAtomicReference.set(paramClass);
    }
    return bool;
    return false;
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonGenerationException
  {
    int i = 1;
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      JsonSerializer localJsonSerializer = findTypedValueSerializer(paramObject.getClass(), true, null);
      String str = _config.getRootName();
      if (str == null)
      {
        boolean bool = _config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        i = bool;
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(_rootNames.findRootName(paramObject.getClass(), _config));
          i = bool;
        }
      }
      try
      {
        localJsonSerializer.serialize(paramObject, paramJsonGenerator, this);
        if (i == 0) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          if (str.length() == 0)
          {
            i = 0;
          }
          else
          {
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(str);
          }
        }
      }
      catch (Exception localException)
      {
        paramObject = localException.getMessage();
        paramJsonGenerator = (JsonGenerator)paramObject;
        if (paramObject == null) {
          paramJsonGenerator = "[no message for " + localException.getClass().getName() + "]";
        }
        throw new JsonMappingException(paramJsonGenerator, localException);
      }
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType)
    throws IOException, JsonGenerationException
  {
    int i = 1;
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass())) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      paramJavaType = findTypedValueSerializer(paramJavaType, true, null);
      String str = _config.getRootName();
      if (str == null)
      {
        boolean bool = _config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        i = bool;
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(_rootNames.findRootName(paramObject.getClass(), _config));
          i = bool;
        }
      }
      try
      {
        paramJavaType.serialize(paramObject, paramJsonGenerator, this);
        if (i == 0) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          if (str.length() == 0)
          {
            i = 0;
          }
          else
          {
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(str);
          }
        }
      }
      catch (Exception paramJavaType)
      {
        paramObject = paramJavaType.getMessage();
        paramJsonGenerator = (JsonGenerator)paramObject;
        if (paramObject == null) {
          paramJsonGenerator = "[no message for " + paramJavaType.getClass().getName() + "]";
        }
        throw new JsonMappingException(paramJsonGenerator, paramJavaType);
      }
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    int i = 1;
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if ((paramJavaType != null) && (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass()))) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      Object localObject = paramJsonSerializer;
      if (paramJsonSerializer == null) {
        localObject = findTypedValueSerializer(paramJavaType, true, null);
      }
      paramJavaType = _config.getRootName();
      if (paramJavaType == null)
      {
        boolean bool = _config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        i = bool;
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(_rootNames.findRootName(paramObject.getClass(), _config));
          i = bool;
        }
      }
      try
      {
        ((JsonSerializer)localObject).serialize(paramObject, paramJsonGenerator, this);
        if (i == 0) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
        return;
      }
      catch (IOException paramJsonGenerator)
      {
        for (;;)
        {
          throw paramJsonGenerator;
          if (paramJavaType.length() == 0)
          {
            i = 0;
          }
          else
          {
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(paramJavaType);
          }
        }
      }
      catch (Exception paramJavaType)
      {
        paramObject = paramJavaType.getMessage();
        paramJsonGenerator = (JsonGenerator)paramObject;
        if (paramObject == null) {
          paramJsonGenerator = "[no message for " + paramJavaType.getClass().getName() + "]";
        }
        throw new JsonMappingException(paramJsonGenerator, paramJavaType);
      }
    }
  }
  
  public JsonSerializer<Object> serializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject = null;
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof JsonSerializer)) {
      paramAnnotated = (JsonSerializer)paramObject;
    }
    label191:
    for (;;)
    {
      return _handleResolvable(paramAnnotated);
      if (!(paramObject instanceof Class)) {
        throw new IllegalStateException("AnnotationIntrospector returned serializer definition of type " + paramObject.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
      }
      paramObject = (Class)paramObject;
      if ((paramObject == JsonSerializer.None.class) || (paramObject == NoClass.class)) {
        break;
      }
      if (!JsonSerializer.class.isAssignableFrom((Class)paramObject)) {
        throw new IllegalStateException("AnnotationIntrospector returned Class " + ((Class)paramObject).getName() + "; expected Class<JsonSerializer>");
      }
      HandlerInstantiator localHandlerInstantiator = _config.getHandlerInstantiator();
      if (localHandlerInstantiator == null) {}
      for (paramAnnotated = (Annotated)localObject;; paramAnnotated = localHandlerInstantiator.serializerInstance(_config, paramAnnotated, (Class)paramObject))
      {
        if (paramAnnotated != null) {
          break label191;
        }
        paramAnnotated = (JsonSerializer)ClassUtil.createInstance((Class)paramObject, _config.canOverrideAccessModifiers());
        break;
      }
    }
  }
  
  public static final class Impl
    extends DefaultSerializerProvider
  {
    private static final long serialVersionUID = 1L;
    
    public Impl() {}
    
    protected Impl(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
    {
      super(paramSerializationConfig, paramSerializerFactory);
    }
    
    public Impl createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
    {
      return new Impl(this, paramSerializationConfig, paramSerializerFactory);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.DefaultSerializerProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */