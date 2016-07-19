package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ObjectArraySerializer
  extends ArraySerializerBase<Object[]>
  implements ContextualSerializer
{
  protected PropertySerializerMap _dynamicSerializers;
  protected JsonSerializer<Object> _elementSerializer;
  protected final JavaType _elementType;
  protected final boolean _staticTyping;
  protected final TypeSerializer _valueTypeSerializer;
  
  public ObjectArraySerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(Object[].class, null);
    _elementType = paramJavaType;
    _staticTyping = paramBoolean;
    _valueTypeSerializer = paramTypeSerializer;
    _dynamicSerializers = PropertySerializerMap.emptyMap();
    _elementSerializer = paramJsonSerializer;
  }
  
  public ObjectArraySerializer(ObjectArraySerializer paramObjectArraySerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramObjectArraySerializer, paramBeanProperty);
    _elementType = _elementType;
    _valueTypeSerializer = paramTypeSerializer;
    _staticTyping = _staticTyping;
    _dynamicSerializers = _dynamicSerializers;
    _elementSerializer = paramJsonSerializer;
  }
  
  public ObjectArraySerializer(ObjectArraySerializer paramObjectArraySerializer, TypeSerializer paramTypeSerializer)
  {
    super(paramObjectArraySerializer);
    _elementType = _elementType;
    _valueTypeSerializer = paramTypeSerializer;
    _staticTyping = _staticTyping;
    _dynamicSerializers = _dynamicSerializers;
    _elementSerializer = _elementSerializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramJavaType = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, _property);
    if (paramPropertySerializerMap != map) {
      _dynamicSerializers = map;
    }
    return serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    paramClass = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, _property);
    if (paramPropertySerializerMap != map) {
      _dynamicSerializers = map;
    }
    return serializer;
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new ObjectArraySerializer(_elementType, _staticTyping, paramTypeSerializer, _elementSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonArrayFormatVisitor localJsonArrayFormatVisitor = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
    if (localJsonArrayFormatVisitor != null)
    {
      JavaType localJavaType = paramJsonFormatVisitorWrapper.getProvider().getTypeFactory().moreSpecificType(_elementType, paramJavaType.getContentType());
      if (localJavaType == null) {
        throw new JsonMappingException("Could not resolve type");
      }
      JsonSerializer localJsonSerializer = _elementSerializer;
      paramJavaType = localJsonSerializer;
      if (localJsonSerializer == null) {
        paramJavaType = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localJavaType, _property);
      }
      localJsonArrayFormatVisitor.itemsFormat(paramJavaType, localJavaType);
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    if (localTypeSerializer != null) {
      localTypeSerializer = localTypeSerializer.forProperty(paramBeanProperty);
    }
    for (;;)
    {
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (paramBeanProperty != null)
      {
        AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
        localObject1 = localObject2;
        if (localAnnotatedMember != null)
        {
          Object localObject3 = paramSerializerProvider.getAnnotationIntrospector().findContentSerializer(localAnnotatedMember);
          localObject1 = localObject2;
          if (localObject3 != null) {
            localObject1 = paramSerializerProvider.serializerInstance(localAnnotatedMember, localObject3);
          }
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = _elementSerializer;
      }
      localObject2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, (JsonSerializer)localObject2);
      if (localObject2 == null)
      {
        localObject1 = localObject2;
        if (_elementType != null) {
          if (!_staticTyping)
          {
            localObject1 = localObject2;
            if (!hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty)) {
              break label140;
            }
          }
        }
      }
      for (localObject1 = paramSerializerProvider.findValueSerializer(_elementType, paramBeanProperty);; localObject1 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject2, paramBeanProperty)) {
        label140:
        return withResolved(paramBeanProperty, localTypeSerializer, (JsonSerializer)localObject1);
      }
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return _elementSerializer;
  }
  
  public JavaType getContentType()
  {
    return _elementType;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode = createSchemaNode("array", true);
    if (paramType != null)
    {
      paramType = paramSerializerProvider.constructType(paramType);
      if (paramType.isArrayType())
      {
        paramType = ((ArrayType)paramType).getContentType().getRawClass();
        if (paramType != Object.class) {
          break label54;
        }
        localObjectNode.put("items", JsonSchema.getDefaultSchemaNode());
      }
    }
    return localObjectNode;
    label54:
    paramType = paramSerializerProvider.findValueSerializer(paramType, _property);
    if ((paramType instanceof SchemaAware)) {}
    for (paramSerializerProvider = ((SchemaAware)paramType).getSchema(paramSerializerProvider, null);; paramSerializerProvider = JsonSchema.getDefaultSchemaNode())
    {
      localObjectNode.put("items", paramSerializerProvider);
      return localObjectNode;
    }
  }
  
  public boolean hasSingleElement(Object[] paramArrayOfObject)
  {
    return paramArrayOfObject.length == 1;
  }
  
  public boolean isEmpty(Object[] paramArrayOfObject)
  {
    return (paramArrayOfObject == null) || (paramArrayOfObject.length == 0);
  }
  
  public void serializeContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int k = paramArrayOfObject.length;
    if (k == 0) {
      return;
    }
    if (_elementSerializer != null)
    {
      serializeContentsUsing(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider, _elementSerializer);
      return;
    }
    if (_valueTypeSerializer != null)
    {
      serializeTypedContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    int j = 0;
    int i = 0;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localPropertySerializerMap = _dynamicSerializers;
        if (i >= k) {
          break;
        }
        localObject2 = paramArrayOfObject[i];
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          j = i;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          break label271;
        }
        localObject1 = localObject2;
        j = i;
        localClass = localObject2.getClass();
        localObject1 = localObject2;
        j = i;
        JsonSerializer localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null)
        {
          localObject1 = localObject2;
          j = i;
          if (!_elementType.hasGenericTypes()) {
            continue;
          }
          localObject1 = localObject2;
          j = i;
          localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(_elementType, localClass), paramSerializerProvider);
        }
      }
      catch (IOException paramArrayOfObject)
      {
        PropertySerializerMap localPropertySerializerMap;
        Object localObject2;
        Class localClass;
        throw paramArrayOfObject;
        localObject1 = localObject2;
        j = i;
        JsonSerializer localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        continue;
        if ((!(paramArrayOfObject instanceof InvocationTargetException)) || (paramArrayOfObject.getCause() == null)) {
          continue;
        }
        paramArrayOfObject = paramArrayOfObject.getCause();
        continue;
        if (!(paramArrayOfObject instanceof Error)) {
          continue;
        }
        throw ((Error)paramArrayOfObject);
        throw JsonMappingException.wrapWithPath(paramArrayOfObject, localObject1, j);
      }
      catch (Exception paramArrayOfObject)
      {
        continue;
      }
      localObject1 = localObject2;
      j = i;
      localJsonSerializer1.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      label271:
      i += 1;
    }
  }
  
  public void serializeContentsUsing(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    int j = paramArrayOfObject.length;
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    int i = 0;
    for (;;)
    {
      Object localObject;
      if (i < j)
      {
        localObject = paramArrayOfObject[i];
        if (localObject == null) {}
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (IOException paramArrayOfObject)
        {
          throw paramArrayOfObject;
          paramJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
        catch (Exception paramArrayOfObject)
        {
          while (((paramArrayOfObject instanceof InvocationTargetException)) && (paramArrayOfObject.getCause() != null)) {
            paramArrayOfObject = paramArrayOfObject.getCause();
          }
          if (!(paramArrayOfObject instanceof Error)) {
            break label108;
          }
          throw ((Error)paramArrayOfObject);
          throw JsonMappingException.wrapWithPath(paramArrayOfObject, localObject, i);
        }
        if (localTypeSerializer == null)
        {
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
          break label118;
        }
      }
      label108:
      return;
      label118:
      i += 1;
    }
  }
  
  public void serializeTypedContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    int k = paramArrayOfObject.length;
    TypeSerializer localTypeSerializer = _valueTypeSerializer;
    int j = 0;
    int i = 0;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        PropertySerializerMap localPropertySerializerMap = _dynamicSerializers;
        if (i < k)
        {
          Object localObject2 = paramArrayOfObject[i];
          if (localObject2 == null)
          {
            localObject1 = localObject2;
            j = i;
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            localObject1 = localObject2;
            j = i;
            Class localClass = localObject2.getClass();
            localObject1 = localObject2;
            j = i;
            JsonSerializer localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
            JsonSerializer localJsonSerializer1 = localJsonSerializer2;
            if (localJsonSerializer2 == null)
            {
              localObject1 = localObject2;
              j = i;
              localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
            }
            localObject1 = localObject2;
            j = i;
            localJsonSerializer1.serializeWithType(localObject2, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
          }
        }
      }
      catch (IOException paramArrayOfObject)
      {
        throw paramArrayOfObject;
        if (((paramArrayOfObject instanceof InvocationTargetException)) && (paramArrayOfObject.getCause() != null))
        {
          paramArrayOfObject = paramArrayOfObject.getCause();
          continue;
        }
        if ((paramArrayOfObject instanceof Error)) {
          throw ((Error)paramArrayOfObject);
        }
        throw JsonMappingException.wrapWithPath(paramArrayOfObject, localObject1, j);
        return;
      }
      catch (Exception paramArrayOfObject)
      {
        continue;
      }
      i += 1;
    }
  }
  
  public ObjectArraySerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    if ((_property == paramBeanProperty) && (paramJsonSerializer == _elementSerializer) && (_valueTypeSerializer == paramTypeSerializer)) {
      return this;
    }
    return new ObjectArraySerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */