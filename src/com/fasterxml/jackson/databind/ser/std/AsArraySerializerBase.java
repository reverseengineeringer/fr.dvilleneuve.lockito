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
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AsArraySerializerBase<T>
  extends ContainerSerializer<T>
  implements ContextualSerializer
{
  protected PropertySerializerMap _dynamicSerializers;
  protected final JsonSerializer<Object> _elementSerializer;
  protected final JavaType _elementType;
  protected final BeanProperty _property;
  protected final boolean _staticTyping;
  protected final TypeSerializer _valueTypeSerializer;
  
  protected AsArraySerializerBase(AsArraySerializerBase<?> paramAsArraySerializerBase, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramAsArraySerializerBase);
    _elementType = _elementType;
    _staticTyping = _staticTyping;
    _valueTypeSerializer = paramTypeSerializer;
    _property = paramBeanProperty;
    _elementSerializer = paramJsonSerializer;
    _dynamicSerializers = _dynamicSerializers;
  }
  
  protected AsArraySerializerBase(Class<?> paramClass, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    super(paramClass, false);
    _elementType = paramJavaType;
    if (!paramBoolean)
    {
      paramBoolean = bool;
      if (paramJavaType != null)
      {
        paramBoolean = bool;
        if (!paramJavaType.isFinal()) {}
      }
    }
    else
    {
      paramBoolean = true;
    }
    _staticTyping = paramBoolean;
    _valueTypeSerializer = paramTypeSerializer;
    _property = paramBeanProperty;
    _elementSerializer = paramJsonSerializer;
    _dynamicSerializers = PropertySerializerMap.emptyMap();
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
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonArrayFormatVisitor localJsonArrayFormatVisitor;
    if (paramJsonFormatVisitorWrapper == null) {
      localJsonArrayFormatVisitor = null;
    }
    while (localJsonArrayFormatVisitor != null)
    {
      JavaType localJavaType = paramJsonFormatVisitorWrapper.getProvider().getTypeFactory().moreSpecificType(_elementType, paramJavaType.getContentType());
      if (localJavaType == null)
      {
        throw new JsonMappingException("Could not resolve type");
        localJsonArrayFormatVisitor = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
      }
      else
      {
        JsonSerializer localJsonSerializer = _elementSerializer;
        paramJavaType = localJsonSerializer;
        if (localJsonSerializer == null) {
          paramJavaType = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localJavaType, _property);
        }
        localJsonArrayFormatVisitor.itemsFormat(paramJavaType, localJavaType);
      }
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
          if ((!_staticTyping) || (_elementType.getRawClass() == Object.class))
          {
            localObject1 = localObject2;
            if (!hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty)) {
              break label152;
            }
          }
        }
      }
      for (localObject1 = paramSerializerProvider.findValueSerializer(_elementType, paramBeanProperty);; localObject1 = paramSerializerProvider.handleSecondaryContextualization((JsonSerializer)localObject2, paramBeanProperty))
      {
        label152:
        if ((localObject1 == _elementSerializer) && (paramBeanProperty == _property))
        {
          paramSerializerProvider = this;
          if (_valueTypeSerializer == localTypeSerializer) {}
        }
        else
        {
          paramSerializerProvider = withResolved(paramBeanProperty, localTypeSerializer, (JsonSerializer)localObject1);
        }
        return paramSerializerProvider;
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
      JavaType localJavaType2 = paramSerializerProvider.constructType(paramType).getContentType();
      localJavaType1 = localJavaType2;
      if (localJavaType2 == null)
      {
        localJavaType1 = localJavaType2;
        if ((paramType instanceof ParameterizedType))
        {
          paramType = ((ParameterizedType)paramType).getActualTypeArguments();
          localJavaType1 = localJavaType2;
          if (paramType.length != 1) {}
        }
      }
    }
    for (JavaType localJavaType1 = paramSerializerProvider.constructType(paramType[0]);; localJavaType1 = null)
    {
      paramType = localJavaType1;
      if (localJavaType1 == null)
      {
        paramType = localJavaType1;
        if (_elementType != null) {
          paramType = _elementType;
        }
      }
      if (paramType != null)
      {
        if (paramType.getRawClass() == Object.class) {
          break label152;
        }
        paramType = paramSerializerProvider.findValueSerializer(paramType, _property);
        if (!(paramType instanceof SchemaAware)) {
          break label152;
        }
      }
      label152:
      for (paramSerializerProvider = ((SchemaAware)paramType).getSchema(paramSerializerProvider, null);; paramSerializerProvider = null)
      {
        paramType = paramSerializerProvider;
        if (paramSerializerProvider == null) {
          paramType = JsonSchema.getDefaultSchemaNode();
        }
        localObjectNode.put("items", paramType);
        return localObjectNode;
      }
    }
  }
  
  public final void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if ((paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && (hasSingleElement(paramT)))
    {
      serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray();
    serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  protected abstract void serializeContents(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException;
  
  public void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForArray(paramT, paramJsonGenerator);
    serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForArray(paramT, paramJsonGenerator);
  }
  
  public abstract AsArraySerializerBase<T> withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer);
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */