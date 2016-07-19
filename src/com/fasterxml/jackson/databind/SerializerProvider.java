package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider
  extends DatabindContext
{
  protected static final boolean CACHE_UNKNOWN_MAPPINGS = false;
  public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
  protected static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();
  @Deprecated
  protected static final JavaType TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(Object.class);
  protected transient ContextAttributes _attributes;
  protected final SerializationConfig _config;
  protected DateFormat _dateFormat;
  protected JsonSerializer<Object> _keySerializer;
  protected final ReadOnlyClassToSerializerMap _knownSerializers;
  protected JsonSerializer<Object> _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
  protected JsonSerializer<Object> _nullValueSerializer = NullSerializer.instance;
  protected final RootNameLookup _rootNames;
  protected final Class<?> _serializationView;
  protected final SerializerCache _serializerCache;
  protected final SerializerFactory _serializerFactory;
  protected final boolean _stdNullValueSerializer;
  protected JsonSerializer<Object> _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
  
  public SerializerProvider()
  {
    _config = null;
    _serializerFactory = null;
    _serializerCache = new SerializerCache();
    _knownSerializers = null;
    _rootNames = new RootNameLookup();
    _serializationView = null;
    _attributes = null;
    _stdNullValueSerializer = true;
  }
  
  protected SerializerProvider(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    if (paramSerializationConfig == null) {
      throw new NullPointerException();
    }
    _serializerFactory = paramSerializerFactory;
    _config = paramSerializationConfig;
    _serializerCache = _serializerCache;
    _unknownTypeSerializer = _unknownTypeSerializer;
    _keySerializer = _keySerializer;
    _nullValueSerializer = _nullValueSerializer;
    if (_nullValueSerializer == DEFAULT_NULL_KEY_SERIALIZER) {}
    for (boolean bool = true;; bool = false)
    {
      _stdNullValueSerializer = bool;
      _nullKeySerializer = _nullKeySerializer;
      _rootNames = _rootNames;
      _knownSerializers = _serializerCache.getReadOnlyLookupMap();
      _serializationView = paramSerializationConfig.getActiveView();
      _attributes = paramSerializationConfig.getAttributes();
      return;
    }
  }
  
  protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    try
    {
      JsonSerializer localJsonSerializer = _createUntypedSerializer(paramJavaType);
      if (localJsonSerializer != null) {
        _serializerCache.addAndResolveNonTypedSerializer(paramJavaType, localJsonSerializer, this);
      }
      return localJsonSerializer;
    }
    catch (IllegalArgumentException paramJavaType)
    {
      throw new JsonMappingException(paramJavaType.getMessage(), null, paramJavaType);
    }
  }
  
  protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> paramClass)
    throws JsonMappingException
  {
    try
    {
      JsonSerializer localJsonSerializer = _createUntypedSerializer(_config.constructType(paramClass));
      if (localJsonSerializer != null) {
        _serializerCache.addAndResolveNonTypedSerializer(paramClass, localJsonSerializer, this);
      }
      return localJsonSerializer;
    }
    catch (IllegalArgumentException paramClass)
    {
      throw new JsonMappingException(paramClass.getMessage(), null, paramClass);
    }
  }
  
  protected JsonSerializer<Object> _createUntypedSerializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    return _serializerFactory.createSerializer(this, paramJavaType);
  }
  
  protected final DateFormat _dateFormat()
  {
    if (_dateFormat != null) {
      return _dateFormat;
    }
    DateFormat localDateFormat = (DateFormat)_config.getDateFormat().clone();
    _dateFormat = localDateFormat;
    return localDateFormat;
  }
  
  protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> paramClass)
    throws JsonMappingException
  {
    Object localObject = _knownSerializers.untypedValueSerializer(paramClass);
    if (localObject != null) {}
    JsonSerializer localJsonSerializer;
    do
    {
      return (JsonSerializer<Object>)localObject;
      localJsonSerializer = _serializerCache.untypedValueSerializer(paramClass);
      localObject = localJsonSerializer;
    } while (localJsonSerializer != null);
    return _createAndCacheUntypedSerializer(paramClass);
  }
  
  protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if ((paramJsonSerializer instanceof ResolvableSerializer)) {
      ((ResolvableSerializer)paramJsonSerializer).resolve(this);
    }
    return handleSecondaryContextualization(paramJsonSerializer, paramBeanProperty);
  }
  
  protected JsonSerializer<Object> _handleResolvable(JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    if ((paramJsonSerializer instanceof ResolvableSerializer)) {
      ((ResolvableSerializer)paramJsonSerializer).resolve(this);
    }
    return paramJsonSerializer;
  }
  
  protected void _reportIncompatibleRootType(Object paramObject, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    if ((paramJavaType.isPrimitive()) && (ClassUtil.wrapperType(paramJavaType.getRawClass()).isAssignableFrom(paramObject.getClass()))) {
      return;
    }
    throw new JsonMappingException("Incompatible types: declared root type (" + paramJavaType + ") vs " + paramObject.getClass().getName());
  }
  
  public void defaultSerializeDateKey(long paramLong, JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeFieldName(String.valueOf(paramLong));
      return;
    }
    paramJsonGenerator.writeFieldName(_dateFormat().format(new Date(paramLong)));
  }
  
  public void defaultSerializeDateKey(Date paramDate, JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeFieldName(String.valueOf(paramDate.getTime()));
      return;
    }
    paramJsonGenerator.writeFieldName(_dateFormat().format(paramDate));
  }
  
  public final void defaultSerializeDateValue(long paramLong, JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeNumber(paramLong);
      return;
    }
    paramJsonGenerator.writeString(_dateFormat().format(new Date(paramLong)));
  }
  
  public final void defaultSerializeDateValue(Date paramDate, JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
    {
      paramJsonGenerator.writeNumber(paramDate.getTime());
      return;
    }
    paramJsonGenerator.writeString(_dateFormat().format(paramDate));
  }
  
  public final void defaultSerializeField(String paramString, Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeFieldName(paramString);
    if (paramObject == null)
    {
      if (_stdNullValueSerializer)
      {
        paramJsonGenerator.writeNull();
        return;
      }
      _nullValueSerializer.serialize(null, paramJsonGenerator, this);
      return;
    }
    findTypedValueSerializer(paramObject.getClass(), true, null).serialize(paramObject, paramJsonGenerator, this);
  }
  
  public final void defaultSerializeNull(JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    if (_stdNullValueSerializer)
    {
      paramJsonGenerator.writeNull();
      return;
    }
    _nullValueSerializer.serialize(null, paramJsonGenerator, this);
  }
  
  public final void defaultSerializeValue(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException, JsonProcessingException
  {
    if (paramObject == null)
    {
      if (_stdNullValueSerializer)
      {
        paramJsonGenerator.writeNull();
        return;
      }
      _nullValueSerializer.serialize(null, paramJsonGenerator, this);
      return;
    }
    findTypedValueSerializer(paramObject.getClass(), true, null).serialize(paramObject, paramJsonGenerator, this);
  }
  
  public JsonSerializer<Object> findKeySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return _handleContextualResolvable(_serializerFactory.createKeySerializer(_config, paramJavaType, _keySerializer), paramBeanProperty);
  }
  
  public JsonSerializer<Object> findNullKeySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return _nullKeySerializer;
  }
  
  public JsonSerializer<Object> findNullValueSerializer(BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return _nullValueSerializer;
  }
  
  public abstract WritableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator);
  
  public JsonSerializer<Object> findPrimaryPropertySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer2 = _knownSerializers.untypedValueSerializer(paramJavaType);
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null)
    {
      localJsonSerializer2 = _serializerCache.untypedValueSerializer(paramJavaType);
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null)
      {
        localJsonSerializer2 = _createAndCacheUntypedSerializer(paramJavaType);
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null) {
          return getUnknownTypeSerializer(paramJavaType.getRawClass());
        }
      }
    }
    return handlePrimaryContextualization(localJsonSerializer1, paramBeanProperty);
  }
  
  public JsonSerializer<Object> findPrimaryPropertySerializer(Class<?> paramClass, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer2 = _knownSerializers.untypedValueSerializer(paramClass);
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null)
    {
      localJsonSerializer2 = _serializerCache.untypedValueSerializer(paramClass);
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null)
      {
        localJsonSerializer2 = _serializerCache.untypedValueSerializer(_config.constructType(paramClass));
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null)
        {
          localJsonSerializer2 = _createAndCacheUntypedSerializer(paramClass);
          localJsonSerializer1 = localJsonSerializer2;
          if (localJsonSerializer2 == null) {
            return getUnknownTypeSerializer(paramClass);
          }
        }
      }
    }
    return handlePrimaryContextualization(localJsonSerializer1, paramBeanProperty);
  }
  
  public JsonSerializer<Object> findTypedValueSerializer(JavaType paramJavaType, boolean paramBoolean, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = _knownSerializers.typedValueSerializer(paramJavaType);
    if (localObject1 != null) {}
    do
    {
      return (JsonSerializer<Object>)localObject1;
      localObject2 = _serializerCache.typedValueSerializer(paramJavaType);
      localObject1 = localObject2;
    } while (localObject2 != null);
    localObject1 = findValueSerializer(paramJavaType, paramBeanProperty);
    Object localObject2 = _serializerFactory.createTypeSerializer(_config, paramJavaType);
    if (localObject2 != null) {}
    for (paramBeanProperty = new TypeWrappedSerializer(((TypeSerializer)localObject2).forProperty(paramBeanProperty), (JsonSerializer)localObject1);; paramBeanProperty = (BeanProperty)localObject1)
    {
      localObject1 = paramBeanProperty;
      if (!paramBoolean) {
        break;
      }
      _serializerCache.addTypedSerializer(paramJavaType, paramBeanProperty);
      return paramBeanProperty;
    }
  }
  
  public JsonSerializer<Object> findTypedValueSerializer(Class<?> paramClass, boolean paramBoolean, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = _knownSerializers.typedValueSerializer(paramClass);
    if (localObject1 != null) {}
    do
    {
      return (JsonSerializer<Object>)localObject1;
      localObject2 = _serializerCache.typedValueSerializer(paramClass);
      localObject1 = localObject2;
    } while (localObject2 != null);
    localObject1 = findValueSerializer(paramClass, paramBeanProperty);
    Object localObject2 = _serializerFactory.createTypeSerializer(_config, _config.constructType(paramClass));
    if (localObject2 != null) {}
    for (paramBeanProperty = new TypeWrappedSerializer(((TypeSerializer)localObject2).forProperty(paramBeanProperty), (JsonSerializer)localObject1);; paramBeanProperty = (BeanProperty)localObject1)
    {
      localObject1 = paramBeanProperty;
      if (!paramBoolean) {
        break;
      }
      _serializerCache.addTypedSerializer(paramClass, paramBeanProperty);
      return paramBeanProperty;
    }
  }
  
  public JsonSerializer<Object> findValueSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer2 = _knownSerializers.untypedValueSerializer(paramJavaType);
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null)
    {
      localJsonSerializer2 = _serializerCache.untypedValueSerializer(paramJavaType);
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null)
      {
        localJsonSerializer2 = _createAndCacheUntypedSerializer(paramJavaType);
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null) {
          return getUnknownTypeSerializer(paramJavaType.getRawClass());
        }
      }
    }
    return handleSecondaryContextualization(localJsonSerializer1, paramBeanProperty);
  }
  
  public JsonSerializer<Object> findValueSerializer(Class<?> paramClass, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer2 = _knownSerializers.untypedValueSerializer(paramClass);
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null)
    {
      localJsonSerializer2 = _serializerCache.untypedValueSerializer(paramClass);
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null)
      {
        localJsonSerializer2 = _serializerCache.untypedValueSerializer(_config.constructType(paramClass));
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null)
        {
          localJsonSerializer2 = _createAndCacheUntypedSerializer(paramClass);
          localJsonSerializer1 = localJsonSerializer2;
          if (localJsonSerializer2 == null) {
            return getUnknownTypeSerializer(paramClass);
          }
        }
      }
    }
    return handleSecondaryContextualization(localJsonSerializer1, paramBeanProperty);
  }
  
  public final Class<?> getActiveView()
  {
    return _serializationView;
  }
  
  public final AnnotationIntrospector getAnnotationIntrospector()
  {
    return _config.getAnnotationIntrospector();
  }
  
  public Object getAttribute(Object paramObject)
  {
    return _attributes.getAttribute(paramObject);
  }
  
  public final SerializationConfig getConfig()
  {
    return _config;
  }
  
  public JsonSerializer<Object> getDefaultNullKeySerializer()
  {
    return _nullKeySerializer;
  }
  
  public JsonSerializer<Object> getDefaultNullValueSerializer()
  {
    return _nullValueSerializer;
  }
  
  public final FilterProvider getFilterProvider()
  {
    return _config.getFilterProvider();
  }
  
  public Locale getLocale()
  {
    return _config.getLocale();
  }
  
  @Deprecated
  public final Class<?> getSerializationView()
  {
    return _serializationView;
  }
  
  public TimeZone getTimeZone()
  {
    return _config.getTimeZone();
  }
  
  public final TypeFactory getTypeFactory()
  {
    return _config.getTypeFactory();
  }
  
  public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> paramClass)
  {
    return _unknownTypeSerializer;
  }
  
  @Deprecated
  public JsonSerializer<?> handleContextualization(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return handleSecondaryContextualization(paramJsonSerializer, paramBeanProperty);
  }
  
  public JsonSerializer<?> handlePrimaryContextualization(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject = paramJsonSerializer;
    if (paramJsonSerializer != null)
    {
      localObject = paramJsonSerializer;
      if ((paramJsonSerializer instanceof ContextualSerializer)) {
        localObject = ((ContextualSerializer)paramJsonSerializer).createContextual(this, paramBeanProperty);
      }
    }
    return (JsonSerializer<?>)localObject;
  }
  
  public JsonSerializer<?> handleSecondaryContextualization(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject = paramJsonSerializer;
    if (paramJsonSerializer != null)
    {
      localObject = paramJsonSerializer;
      if ((paramJsonSerializer instanceof ContextualSerializer)) {
        localObject = ((ContextualSerializer)paramJsonSerializer).createContextual(this, paramBeanProperty);
      }
    }
    return (JsonSerializer<?>)localObject;
  }
  
  public final boolean hasSerializationFeatures(int paramInt)
  {
    return _config.hasSerializationFeatures(paramInt);
  }
  
  public final boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return _config.isEnabled(paramSerializationFeature);
  }
  
  public abstract JsonSerializer<Object> serializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException;
  
  public SerializerProvider setAttribute(Object paramObject1, Object paramObject2)
  {
    _attributes = _attributes.withPerCallAttribute(paramObject1, paramObject2);
    return this;
  }
  
  public void setDefaultKeySerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if (paramJsonSerializer == null) {
      throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }
    _keySerializer = paramJsonSerializer;
  }
  
  public void setNullKeySerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if (paramJsonSerializer == null) {
      throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }
    _nullKeySerializer = paramJsonSerializer;
  }
  
  public void setNullValueSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if (paramJsonSerializer == null) {
      throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }
    _nullValueSerializer = paramJsonSerializer;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.SerializerProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */