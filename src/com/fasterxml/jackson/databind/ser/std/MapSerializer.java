package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer
  extends ContainerSerializer<Map<?, ?>>
  implements ContextualSerializer
{
  protected static final JavaType UNSPECIFIED_TYPE = ;
  protected PropertySerializerMap _dynamicValueSerializers;
  protected final Object _filterId;
  protected final HashSet<String> _ignoredEntries;
  protected JsonSerializer<Object> _keySerializer;
  protected final JavaType _keyType;
  protected final BeanProperty _property;
  protected JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final boolean _valueTypeIsStatic;
  protected final TypeSerializer _valueTypeSerializer;
  
  protected MapSerializer(MapSerializer paramMapSerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2, HashSet<String> paramHashSet)
  {
    super(Map.class, false);
    _ignoredEntries = paramHashSet;
    _keyType = _keyType;
    _valueType = _valueType;
    _valueTypeIsStatic = _valueTypeIsStatic;
    _valueTypeSerializer = _valueTypeSerializer;
    _keySerializer = paramJsonSerializer1;
    _valueSerializer = paramJsonSerializer2;
    _dynamicValueSerializers = _dynamicValueSerializers;
    _property = paramBeanProperty;
    _filterId = _filterId;
  }
  
  protected MapSerializer(MapSerializer paramMapSerializer, TypeSerializer paramTypeSerializer)
  {
    super(Map.class, false);
    _ignoredEntries = _ignoredEntries;
    _keyType = _keyType;
    _valueType = _valueType;
    _valueTypeIsStatic = _valueTypeIsStatic;
    _valueTypeSerializer = paramTypeSerializer;
    _keySerializer = _keySerializer;
    _valueSerializer = _valueSerializer;
    _dynamicValueSerializers = _dynamicValueSerializers;
    _property = _property;
    _filterId = _filterId;
  }
  
  protected MapSerializer(MapSerializer paramMapSerializer, Object paramObject)
  {
    super(Map.class, false);
    _ignoredEntries = _ignoredEntries;
    _keyType = _keyType;
    _valueType = _valueType;
    _valueTypeIsStatic = _valueTypeIsStatic;
    _valueTypeSerializer = _valueTypeSerializer;
    _keySerializer = _keySerializer;
    _valueSerializer = _valueSerializer;
    _dynamicValueSerializers = _dynamicValueSerializers;
    _property = _property;
    _filterId = paramObject;
  }
  
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2)
  {
    super(Map.class, false);
    _ignoredEntries = paramHashSet;
    _keyType = paramJavaType1;
    _valueType = paramJavaType2;
    _valueTypeIsStatic = paramBoolean;
    _valueTypeSerializer = paramTypeSerializer;
    _keySerializer = paramJsonSerializer1;
    _valueSerializer = paramJsonSerializer2;
    _dynamicValueSerializers = PropertySerializerMap.emptyMap();
    _property = null;
    _filterId = null;
  }
  
  @Deprecated
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2)
  {
    return construct(paramArrayOfString, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, null);
  }
  
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2, Object paramObject)
  {
    boolean bool = false;
    HashSet localHashSet = toSet(paramArrayOfString);
    if (paramJavaType == null)
    {
      paramJavaType = UNSPECIFIED_TYPE;
      paramArrayOfString = paramJavaType;
      if (paramBoolean) {
        break label89;
      }
      paramBoolean = bool;
      if (paramJavaType != null)
      {
        paramBoolean = bool;
        if (paramJavaType.isFinal()) {
          paramBoolean = true;
        }
      }
    }
    for (;;)
    {
      paramJavaType = new MapSerializer(localHashSet, paramArrayOfString, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2);
      paramArrayOfString = paramJavaType;
      if (paramObject != null) {
        paramArrayOfString = paramJavaType.withFilterId(paramObject);
      }
      return paramArrayOfString;
      paramArrayOfString = paramJavaType.getKeyType();
      paramJavaType = paramJavaType.getContentType();
      break;
      label89:
      if (paramJavaType.getRawClass() == Object.class) {
        paramBoolean = false;
      }
    }
  }
  
  private static HashSet<String> toSet(String[] paramArrayOfString)
  {
    Object localObject;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
    {
      localObject = null;
      return (HashSet<String>)localObject;
    }
    HashSet localHashSet = new HashSet(paramArrayOfString.length);
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      localObject = localHashSet;
      if (i >= j) {
        break;
      }
      localHashSet.add(paramArrayOfString[i]);
      i += 1;
    }
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramJavaType = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, _property);
    if (paramPropertySerializerMap != map) {
      _dynamicValueSerializers = map;
    }
    return serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramClass = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, _property);
    if (paramPropertySerializerMap != map) {
      _dynamicValueSerializers = map;
    }
    return serializer;
  }
  
  protected Map<?, ?> _orderEntries(Map<?, ?> paramMap)
  {
    if ((paramMap instanceof SortedMap)) {
      return paramMap;
    }
    return new TreeMap(paramMap);
  }
  
  public MapSerializer _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new MapSerializer(this, paramTypeSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (paramJavaType = null;; paramJavaType = paramJsonFormatVisitorWrapper.expectMapFormat(paramJavaType))
    {
      if (paramJavaType != null)
      {
        paramJavaType.keyFormat(_keySerializer, _keyType);
        JsonSerializer localJsonSerializer2 = _valueSerializer;
        JsonSerializer localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null) {
          localJsonSerializer1 = _findAndAddDynamic(_dynamicValueSerializers, _valueType, paramJsonFormatVisitorWrapper.getProvider());
        }
        paramJavaType.valueFormat(localJsonSerializer1, _valueType);
      }
      return;
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject1 = null;
    Object localObject2;
    Object localObject3;
    if (paramBeanProperty != null)
    {
      localObject2 = paramBeanProperty.getMember();
      if (localObject2 != null)
      {
        localObject3 = paramSerializerProvider.getAnnotationIntrospector();
        localObject1 = ((AnnotationIntrospector)localObject3).findKeySerializer((Annotated)localObject2);
        if (localObject1 != null)
        {
          localObject1 = paramSerializerProvider.serializerInstance((Annotated)localObject2, localObject1);
          localObject3 = ((AnnotationIntrospector)localObject3).findContentSerializer((Annotated)localObject2);
          if (localObject3 != null) {
            localObject2 = paramSerializerProvider.serializerInstance((Annotated)localObject2, localObject3);
          }
        }
      }
    }
    for (;;)
    {
      localObject3 = localObject2;
      if (localObject2 == null) {
        localObject3 = _valueSerializer;
      }
      localObject2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, (JsonSerializer)localObject3);
      if (localObject2 == null)
      {
        if (((!_valueTypeIsStatic) || (_valueType.getRawClass() == Object.class)) && (!hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty))) {
          break label342;
        }
        localObject2 = paramSerializerProvider.findValueSerializer(_valueType, paramBeanProperty);
      }
      label170:
      label278:
      label291:
      label342:
      for (;;)
      {
        if (localObject1 == null) {
          localObject1 = _keySerializer;
        }
        for (;;)
        {
          AnnotationIntrospector localAnnotationIntrospector;
          String[] arrayOfString;
          if (localObject1 == null)
          {
            localObject1 = paramSerializerProvider.findKeySerializer(_keyType, paramBeanProperty);
            localObject3 = _ignoredEntries;
            localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
            if ((localAnnotationIntrospector == null) || (paramBeanProperty == null)) {
              break label291;
            }
            arrayOfString = localAnnotationIntrospector.findPropertiesToIgnore(paramBeanProperty.getMember());
            if (arrayOfString == null) {
              break label291;
            }
            if (localObject3 != null) {
              break label278;
            }
          }
          for (paramSerializerProvider = new HashSet();; paramSerializerProvider = new HashSet((Collection)localObject3))
          {
            int j = arrayOfString.length;
            int i = 0;
            for (;;)
            {
              localObject3 = paramSerializerProvider;
              if (i >= j) {
                break;
              }
              paramSerializerProvider.add(arrayOfString[i]);
              i += 1;
            }
            localObject2 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject2, paramBeanProperty);
            break;
            localObject1 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject1, paramBeanProperty);
            break label170;
          }
          localObject1 = withResolved(paramBeanProperty, (JsonSerializer)localObject1, (JsonSerializer)localObject2, (HashSet)localObject3);
          paramSerializerProvider = (SerializerProvider)localObject1;
          if (paramBeanProperty != null)
          {
            paramBeanProperty = localAnnotationIntrospector.findFilterId(paramBeanProperty.getMember());
            paramSerializerProvider = (SerializerProvider)localObject1;
            if (paramBeanProperty != null) {
              paramSerializerProvider = ((MapSerializer)localObject1).withFilterId(paramBeanProperty);
            }
          }
          return paramSerializerProvider;
        }
      }
      localObject2 = null;
      continue;
      localObject1 = null;
      break;
      localObject2 = null;
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return _valueSerializer;
  }
  
  public JavaType getContentType()
  {
    return _valueType;
  }
  
  public JsonSerializer<?> getKeySerializer()
  {
    return _keySerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("object", true);
  }
  
  public boolean hasSingleElement(Map<?, ?> paramMap)
  {
    return paramMap.size() == 1;
  }
  
  public boolean isEmpty(Map<?, ?> paramMap)
  {
    return (paramMap == null) || (paramMap.isEmpty());
  }
  
  public void serialize(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeStartObject();
    Object localObject;
    if (!paramMap.isEmpty())
    {
      if (_filterId != null)
      {
        serializeFilteredFields(paramMap, paramJsonGenerator, paramSerializerProvider, findPropertyFilter(paramSerializerProvider, _filterId, paramMap));
        return;
      }
      localObject = paramMap;
      if (paramSerializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
        localObject = _orderEntries(paramMap);
      }
      if (_valueSerializer == null) {
        break label82;
      }
      serializeFieldsUsing((Map)localObject, paramJsonGenerator, paramSerializerProvider, _valueSerializer);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndObject();
      return;
      label82:
      serializeFields((Map)localObject, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public void serializeFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    label14:
    JsonSerializer localJsonSerializer;
    HashSet localHashSet;
    int i;
    label40:
    Object localObject1;
    Iterator localIterator;
    if (_valueTypeSerializer != null)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    else
    {
      localJsonSerializer = _keySerializer;
      localHashSet = _ignoredEntries;
      if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
        break label135;
      }
      i = 1;
      localObject1 = _dynamicValueSerializers;
      localIterator = paramMap.entrySet().iterator();
    }
    label59:
    Object localObject4;
    Object localObject5;
    if (localIterator.hasNext())
    {
      localObject2 = (Map.Entry)localIterator.next();
      localObject4 = ((Map.Entry)localObject2).getValue();
      localObject5 = ((Map.Entry)localObject2).getKey();
      if (localObject5 != null) {
        break label141;
      }
      paramSerializerProvider.findNullKeySerializer(_keyType, _property).serialize(null, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      if (localObject4 != null) {
        break label178;
      }
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      break label59;
      break label14;
      label135:
      i = 0;
      break label40;
      label141:
      if (((i != 0) && (localObject4 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject5)))) {
        break;
      }
      localJsonSerializer.serialize(localObject5, paramJsonGenerator, paramSerializerProvider);
    }
    label178:
    Object localObject3 = localObject4.getClass();
    Object localObject2 = ((PropertySerializerMap)localObject1).serializerFor((Class)localObject3);
    if (localObject2 == null) {
      if (_valueType.hasGenericTypes())
      {
        localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(_valueType, (Class)localObject3), paramSerializerProvider);
        label228:
        localObject3 = _dynamicValueSerializers;
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
    }
    for (;;)
    {
      try
      {
        ((JsonSerializer)localObject2).serialize(localObject4, paramJsonGenerator, paramSerializerProvider);
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject5);
      }
      break;
      localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, (Class)localObject3, paramSerializerProvider);
      break label228;
    }
  }
  
  protected void serializeFieldsUsing(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    JsonSerializer localJsonSerializer = _keySerializer;
    HashSet localHashSet = _ignoredEntries;
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    int i;
    Iterator localIterator;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      localIterator = paramMap.entrySet().iterator();
    }
    for (;;)
    {
      label44:
      if (!localIterator.hasNext()) {
        return;
      }
      Object localObject2 = (Map.Entry)localIterator.next();
      Object localObject1 = ((Map.Entry)localObject2).getValue();
      localObject2 = ((Map.Entry)localObject2).getKey();
      if (localObject2 == null) {
        paramSerializerProvider.findNullKeySerializer(_keyType, _property).serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        if (localObject1 != null) {
          break label163;
        }
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        break label44;
        i = 0;
        break;
        if (((i != 0) && (localObject1 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject2)))) {
          break label44;
        }
        localJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      }
      label163:
      if (localTypeSerializer == null) {
        try
        {
          paramJsonSerializer.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject2);
        }
      } else {
        paramJsonSerializer.serializeWithType(localException, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      }
    }
  }
  
  public void serializeFilteredFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyFilter paramPropertyFilter)
    throws IOException, JsonGenerationException
  {
    HashSet localHashSet = _ignoredEntries;
    int i;
    Object localObject1;
    MapProperty localMapProperty;
    label51:
    Object localObject2;
    Object localObject4;
    Object localObject5;
    JsonSerializer localJsonSerializer;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      localObject1 = _dynamicValueSerializers;
      localMapProperty = new MapProperty(_valueTypeSerializer);
      Iterator localIterator = paramMap.entrySet().iterator();
      if (!localIterator.hasNext()) {
        break label304;
      }
      localObject2 = (Map.Entry)localIterator.next();
      localObject4 = ((Map.Entry)localObject2).getKey();
      localObject5 = ((Map.Entry)localObject2).getValue();
      if (localObject4 != null) {
        break label155;
      }
      localJsonSerializer = paramSerializerProvider.findNullKeySerializer(_keyType, _property);
      label110:
      if (localObject5 != null) {
        break label189;
      }
      localObject2 = paramSerializerProvider.getDefaultNullValueSerializer();
    }
    label155:
    label189:
    label304:
    label305:
    for (;;)
    {
      localMapProperty.reset(localObject4, localObject5, localJsonSerializer, (JsonSerializer)localObject2);
      try
      {
        paramPropertyFilter.serializeAsField(paramMap, paramJsonGenerator, paramSerializerProvider, localMapProperty);
        break label51;
        i = 0;
        break;
        if (((i != 0) && (localObject5 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject4)))) {
          break label51;
        }
        localJsonSerializer = _keySerializer;
        break label110;
        Object localObject3 = localObject5.getClass();
        localObject2 = ((PropertySerializerMap)localObject1).serializerFor((Class)localObject3);
        if (localObject2 != null) {
          break label305;
        }
        if (_valueType.hasGenericTypes()) {}
        for (localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, paramSerializerProvider.constructSpecializedType(_valueType, (Class)localObject3), paramSerializerProvider);; localObject1 = _findAndAddDynamic((PropertySerializerMap)localObject1, (Class)localObject3, paramSerializerProvider))
        {
          localObject3 = _dynamicValueSerializers;
          localObject2 = localObject1;
          localObject1 = localObject3;
          break;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject4);
        }
      }
      return;
    }
  }
  
  protected void serializeTypedFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    JsonSerializer localJsonSerializer = _keySerializer;
    HashSet localHashSet = _ignoredEntries;
    int i;
    Object localObject2;
    label44:
    Object localObject3;
    Object localObject6;
    Object localObject7;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      Iterator localIterator = paramMap.entrySet().iterator();
      localObject2 = null;
      localObject1 = null;
      if (!localIterator.hasNext()) {
        return;
      }
      localObject3 = (Map.Entry)localIterator.next();
      localObject6 = ((Map.Entry)localObject3).getValue();
      localObject7 = ((Map.Entry)localObject3).getKey();
      if (localObject7 != null) {
        break label126;
      }
      paramSerializerProvider.findNullKeySerializer(_keyType, _property).serialize(null, paramJsonGenerator, paramSerializerProvider);
      label107:
      if (localObject6 != null) {
        break label163;
      }
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    }
    label126:
    label163:
    Object localObject5;
    for (;;)
    {
      break label44;
      i = 0;
      break;
      if (((i != 0) && (localObject6 == null)) || ((localHashSet != null) && (localHashSet.contains(localObject7)))) {
        break label44;
      }
      localJsonSerializer.serialize(localObject7, paramJsonGenerator, paramSerializerProvider);
      break label107;
      localObject5 = localObject6.getClass();
      if (localObject5 != localObject2) {
        break label239;
      }
      localObject5 = localObject1;
      localObject3 = localObject1;
      localObject1 = localObject5;
      try
      {
        ((JsonSerializer)localObject3).serializeWithType(localObject6, paramJsonGenerator, paramSerializerProvider, _valueTypeSerializer);
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject7);
      }
    }
    label239:
    if (_valueType.hasGenericTypes()) {}
    for (Object localObject1 = paramSerializerProvider.findValueSerializer(paramSerializerProvider.constructSpecializedType(_valueType, (Class)localObject5), _property);; localObject1 = paramSerializerProvider.findValueSerializer((Class)localObject5, _property))
    {
      Object localObject4 = localObject1;
      localObject2 = localObject5;
      break;
    }
  }
  
  public void serializeWithType(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramMap, paramJsonGenerator);
    Object localObject = paramMap;
    if (!paramMap.isEmpty())
    {
      localObject = paramMap;
      if (paramSerializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
        localObject = _orderEntries(paramMap);
      }
      if (_valueSerializer == null) {
        break label67;
      }
      serializeFieldsUsing((Map)localObject, paramJsonGenerator, paramSerializerProvider, _valueSerializer);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForObject(localObject, paramJsonGenerator);
      return;
      label67:
      serializeFields((Map)localObject, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public MapSerializer withFilterId(Object paramObject)
  {
    if (_filterId == paramObject) {
      return this;
    }
    return new MapSerializer(this, paramObject);
  }
  
  public MapSerializer withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2, HashSet<String> paramHashSet)
  {
    return new MapSerializer(this, paramBeanProperty, paramJsonSerializer1, paramJsonSerializer2, paramHashSet);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.MapSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */