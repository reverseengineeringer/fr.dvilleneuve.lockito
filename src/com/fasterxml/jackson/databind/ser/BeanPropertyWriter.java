package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BeanPropertyWriter
  extends PropertyWriter
  implements BeanProperty
{
  public static final Object MARKER_FOR_EMPTY = new Object();
  protected final Method _accessorMethod;
  protected final JavaType _cfgSerializationType;
  protected final Annotations _contextAnnotations;
  protected final JavaType _declaredType;
  protected transient PropertySerializerMap _dynamicSerializers;
  protected final Field _field;
  protected final Class<?>[] _includeInViews;
  protected HashMap<Object, Object> _internalSettings;
  protected final AnnotatedMember _member;
  protected final PropertyMetadata _metadata;
  protected final SerializedString _name;
  protected JavaType _nonTrivialBaseType;
  protected JsonSerializer<Object> _nullSerializer;
  protected JsonSerializer<Object> _serializer;
  protected final boolean _suppressNulls;
  protected final Object _suppressableValue;
  protected TypeSerializer _typeSerializer;
  protected final PropertyName _wrapperName;
  
  public BeanPropertyWriter(BeanPropertyDefinition paramBeanPropertyDefinition, AnnotatedMember paramAnnotatedMember, Annotations paramAnnotations, JavaType paramJavaType1, JsonSerializer<?> paramJsonSerializer, TypeSerializer paramTypeSerializer, JavaType paramJavaType2, boolean paramBoolean, Object paramObject)
  {
    _member = paramAnnotatedMember;
    _contextAnnotations = paramAnnotations;
    _name = new SerializedString(paramBeanPropertyDefinition.getName());
    _wrapperName = paramBeanPropertyDefinition.getWrapperName();
    _declaredType = paramJavaType1;
    _serializer = paramJsonSerializer;
    if (paramJsonSerializer == null)
    {
      paramAnnotations = PropertySerializerMap.emptyMap();
      _dynamicSerializers = paramAnnotations;
      _typeSerializer = paramTypeSerializer;
      _cfgSerializationType = paramJavaType2;
      _metadata = paramBeanPropertyDefinition.getMetadata();
      if (!(paramAnnotatedMember instanceof AnnotatedField)) {
        break label137;
      }
      _accessorMethod = null;
    }
    for (_field = ((Field)paramAnnotatedMember.getMember());; _field = null)
    {
      _suppressNulls = paramBoolean;
      _suppressableValue = paramObject;
      _includeInViews = paramBeanPropertyDefinition.findViews();
      _nullSerializer = null;
      return;
      paramAnnotations = null;
      break;
      label137:
      if (!(paramAnnotatedMember instanceof AnnotatedMethod)) {
        break label163;
      }
      _accessorMethod = ((Method)paramAnnotatedMember.getMember());
    }
    label163:
    throw new IllegalArgumentException("Can not pass member of type " + paramAnnotatedMember.getClass().getName());
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter)
  {
    this(paramBeanPropertyWriter, _name);
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter, SerializedString paramSerializedString)
  {
    _name = paramSerializedString;
    _wrapperName = _wrapperName;
    _member = _member;
    _contextAnnotations = _contextAnnotations;
    _declaredType = _declaredType;
    _accessorMethod = _accessorMethod;
    _field = _field;
    _serializer = _serializer;
    _nullSerializer = _nullSerializer;
    if (_internalSettings != null) {
      _internalSettings = new HashMap(_internalSettings);
    }
    _cfgSerializationType = _cfgSerializationType;
    _dynamicSerializers = _dynamicSerializers;
    _suppressNulls = _suppressNulls;
    _suppressableValue = _suppressableValue;
    _includeInViews = _includeInViews;
    _typeSerializer = _typeSerializer;
    _nonTrivialBaseType = _nonTrivialBaseType;
    _metadata = _metadata;
  }
  
  protected void _depositSchemaProperty(ObjectNode paramObjectNode, JsonNode paramJsonNode)
  {
    paramObjectNode.put(getName(), paramJsonNode);
  }
  
  protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if (_nonTrivialBaseType != null) {}
    for (paramClass = paramPropertySerializerMap.findAndAddPrimarySerializer(paramSerializerProvider.constructSpecializedType(_nonTrivialBaseType, paramClass), paramSerializerProvider, this);; paramClass = paramPropertySerializerMap.findAndAddPrimarySerializer(paramClass, paramSerializerProvider, this))
    {
      if (paramPropertySerializerMap != map) {
        _dynamicSerializers = map;
      }
      return serializer;
    }
  }
  
  protected void _handleSelfReference(Object paramObject, JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    if (paramJsonSerializer.usesObjectId()) {
      return;
    }
    throw new JsonMappingException("Direct self-reference leading to cycle");
  }
  
  public void assignNullSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if ((_nullSerializer != null) && (_nullSerializer != paramJsonSerializer)) {
      throw new IllegalStateException("Can not override null serializer");
    }
    _nullSerializer = paramJsonSerializer;
  }
  
  public void assignSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if ((_serializer != null) && (_serializer != paramJsonSerializer)) {
      throw new IllegalStateException("Can not override serializer");
    }
    _serializer = paramJsonSerializer;
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException
  {
    if (paramJsonObjectFormatVisitor != null)
    {
      if (isRequired()) {
        paramJsonObjectFormatVisitor.property(this);
      }
    }
    else {
      return;
    }
    paramJsonObjectFormatVisitor.optionalProperty(this);
  }
  
  @Deprecated
  public void depositSchemaProperty(ObjectNode paramObjectNode, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    Object localObject1 = getSerializationType();
    Object localObject2;
    boolean bool;
    if (localObject1 == null)
    {
      localObject1 = getGenericPropertyType();
      Object localObject3 = getSerializer();
      localObject2 = localObject3;
      if (localObject3 == null)
      {
        localObject3 = getRawSerializationType();
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = getPropertyType();
        }
        localObject2 = paramSerializerProvider.findValueSerializer((Class)localObject2, this);
      }
      if (isRequired()) {
        break label111;
      }
      bool = true;
      label71:
      if (!(localObject2 instanceof SchemaAware)) {
        break label116;
      }
    }
    label111:
    label116:
    for (paramSerializerProvider = ((SchemaAware)localObject2).getSchema(paramSerializerProvider, (Type)localObject1, bool);; paramSerializerProvider = JsonSchema.getDefaultSchemaNode())
    {
      _depositSchemaProperty(paramObjectNode, paramSerializerProvider);
      return;
      localObject1 = ((JavaType)localObject1).getRawClass();
      break;
      bool = false;
      break label71;
    }
  }
  
  public final Object get(Object paramObject)
    throws Exception
  {
    if (_accessorMethod != null) {
      return _accessorMethod.invoke(paramObject, new Object[0]);
    }
    return _field.get(paramObject);
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    return _member.getAnnotation(paramClass);
  }
  
  public <A extends Annotation> A getContextAnnotation(Class<A> paramClass)
  {
    return _contextAnnotations.get(paramClass);
  }
  
  public PropertyName getFullName()
  {
    return new PropertyName(_name.getValue());
  }
  
  public Type getGenericPropertyType()
  {
    if (_accessorMethod != null) {
      return _accessorMethod.getGenericReturnType();
    }
    return _field.getGenericType();
  }
  
  public Object getInternalSetting(Object paramObject)
  {
    if (_internalSettings == null) {
      return null;
    }
    return _internalSettings.get(paramObject);
  }
  
  public AnnotatedMember getMember()
  {
    return _member;
  }
  
  public PropertyMetadata getMetadata()
  {
    return _metadata;
  }
  
  public String getName()
  {
    return _name.getValue();
  }
  
  public Class<?> getPropertyType()
  {
    if (_accessorMethod != null) {
      return _accessorMethod.getReturnType();
    }
    return _field.getType();
  }
  
  public Class<?> getRawSerializationType()
  {
    if (_cfgSerializationType == null) {
      return null;
    }
    return _cfgSerializationType.getRawClass();
  }
  
  public JavaType getSerializationType()
  {
    return _cfgSerializationType;
  }
  
  public SerializedString getSerializedName()
  {
    return _name;
  }
  
  public JsonSerializer<Object> getSerializer()
  {
    return _serializer;
  }
  
  public JavaType getType()
  {
    return _declaredType;
  }
  
  public Class<?>[] getViews()
  {
    return _includeInViews;
  }
  
  public PropertyName getWrapperName()
  {
    return _wrapperName;
  }
  
  public boolean hasNullSerializer()
  {
    return _nullSerializer != null;
  }
  
  public boolean hasSerializer()
  {
    return _serializer != null;
  }
  
  public boolean isRequired()
  {
    return _metadata.isRequired();
  }
  
  @Deprecated
  protected boolean isRequired(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _metadata.isRequired();
  }
  
  public boolean isUnwrapping()
  {
    return false;
  }
  
  public Object removeInternalSetting(Object paramObject)
  {
    if (_internalSettings != null)
    {
      paramObject = _internalSettings.remove(paramObject);
      if (_internalSettings.size() == 0) {
        _internalSettings = null;
      }
      return paramObject;
    }
    return null;
  }
  
  public BeanPropertyWriter rename(NameTransformer paramNameTransformer)
  {
    paramNameTransformer = paramNameTransformer.transform(_name.getValue());
    if (paramNameTransformer.equals(_name.toString())) {
      return this;
    }
    return new BeanPropertyWriter(this, new SerializedString(paramNameTransformer));
  }
  
  public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = get(paramObject);
    if (localObject == null)
    {
      if (_nullSerializer != null)
      {
        _nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
        return;
      }
      paramJsonGenerator.writeNull();
      return;
    }
    JsonSerializer localJsonSerializer2 = _serializer;
    JsonSerializer localJsonSerializer1 = localJsonSerializer2;
    if (localJsonSerializer2 == null)
    {
      Class localClass = localObject.getClass();
      PropertySerializerMap localPropertySerializerMap = _dynamicSerializers;
      localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null) {
        localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
      }
    }
    if (_suppressableValue != null) {
      if (MARKER_FOR_EMPTY == _suppressableValue)
      {
        if (localJsonSerializer1.isEmpty(localObject)) {
          serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
      else if (_suppressableValue.equals(localObject))
      {
        serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
        return;
      }
    }
    if (localObject == paramObject) {
      _handleSelfReference(paramObject, localJsonSerializer1);
    }
    if (_typeSerializer == null)
    {
      localJsonSerializer1.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    localJsonSerializer1.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, _typeSerializer);
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = get(paramObject);
    if (localObject == null) {
      if (_nullSerializer != null)
      {
        paramJsonGenerator.writeFieldName(_name);
        _nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
    }
    JsonSerializer localJsonSerializer1;
    do
    {
      return;
      JsonSerializer localJsonSerializer2 = _serializer;
      localJsonSerializer1 = localJsonSerializer2;
      if (localJsonSerializer2 == null)
      {
        Class localClass = localObject.getClass();
        PropertySerializerMap localPropertySerializerMap = _dynamicSerializers;
        localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
        localJsonSerializer1 = localJsonSerializer2;
        if (localJsonSerializer2 == null) {
          localJsonSerializer1 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        }
      }
      if (_suppressableValue == null) {
        break;
      }
      if (MARKER_FOR_EMPTY != _suppressableValue) {
        break label160;
      }
    } while (localJsonSerializer1.isEmpty(localObject));
    label160:
    while (!_suppressableValue.equals(localObject))
    {
      if (localObject == paramObject) {
        _handleSelfReference(paramObject, localJsonSerializer1);
      }
      paramJsonGenerator.writeFieldName(_name);
      if (_typeSerializer != null) {
        break;
      }
      localJsonSerializer1.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    return;
    localJsonSerializer1.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, _typeSerializer);
  }
  
  public void serializeAsOmittedField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (!paramJsonGenerator.canOmitFields()) {
      paramJsonGenerator.writeOmittedField(_name.getValue());
    }
  }
  
  public void serializeAsPlaceholder(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (_nullSerializer != null)
    {
      _nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeNull();
  }
  
  public Object setInternalSetting(Object paramObject1, Object paramObject2)
  {
    if (_internalSettings == null) {
      _internalSettings = new HashMap();
    }
    return _internalSettings.put(paramObject1, paramObject2);
  }
  
  public void setNonTrivialBaseType(JavaType paramJavaType)
  {
    _nonTrivialBaseType = paramJavaType;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(40);
    localStringBuilder.append("property '").append(getName()).append("' (");
    if (_accessorMethod != null)
    {
      localStringBuilder.append("via method ").append(_accessorMethod.getDeclaringClass().getName()).append("#").append(_accessorMethod.getName());
      if (_serializer != null) {
        break label142;
      }
      localStringBuilder.append(", no static serializer");
    }
    for (;;)
    {
      localStringBuilder.append(')');
      return localStringBuilder.toString();
      localStringBuilder.append("field \"").append(_field.getDeclaringClass().getName()).append("#").append(_field.getName());
      break;
      label142:
      localStringBuilder.append(", static serializer of type " + _serializer.getClass().getName());
    }
  }
  
  public BeanPropertyWriter unwrappingWriter(NameTransformer paramNameTransformer)
  {
    return new UnwrappingBeanPropertyWriter(this, paramNameTransformer);
  }
  
  public boolean willSuppressNulls()
  {
    return _suppressNulls;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.BeanPropertyWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */