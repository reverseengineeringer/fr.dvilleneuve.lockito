package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class BeanSerializerBase
  extends StdSerializer<Object>
  implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware
{
  protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
  protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
  protected final AnyGetterWriter _anyGetterWriter;
  protected final BeanPropertyWriter[] _filteredProps;
  protected final ObjectIdWriter _objectIdWriter;
  protected final Object _propertyFilterId;
  protected final BeanPropertyWriter[] _props;
  protected final JsonFormat.Shape _serializationShape;
  protected final AnnotatedMember _typeId;
  
  protected BeanSerializerBase(JavaType paramJavaType, BeanSerializerBuilder paramBeanSerializerBuilder, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2)
  {
    super(paramJavaType);
    _props = paramArrayOfBeanPropertyWriter1;
    _filteredProps = paramArrayOfBeanPropertyWriter2;
    if (paramBeanSerializerBuilder == null)
    {
      _typeId = null;
      _anyGetterWriter = null;
      _propertyFilterId = null;
      _objectIdWriter = null;
      _serializationShape = null;
      return;
    }
    _typeId = paramBeanSerializerBuilder.getTypeId();
    _anyGetterWriter = paramBeanSerializerBuilder.getAnyGetter();
    _propertyFilterId = paramBeanSerializerBuilder.getFilterId();
    _objectIdWriter = paramBeanSerializerBuilder.getObjectIdWriter();
    paramJavaType = paramBeanSerializerBuilder.getBeanDescription().findExpectedFormat(null);
    if (paramJavaType == null) {}
    for (paramJavaType = (JavaType)localObject;; paramJavaType = paramJavaType.getShape())
    {
      _serializationShape = paramJavaType;
      return;
    }
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase)
  {
    this(paramBeanSerializerBase, _props, _filteredProps);
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, ObjectIdWriter paramObjectIdWriter)
  {
    this(paramBeanSerializerBase, paramObjectIdWriter, _propertyFilterId);
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, ObjectIdWriter paramObjectIdWriter, Object paramObject)
  {
    super(_handledType);
    _props = _props;
    _filteredProps = _filteredProps;
    _typeId = _typeId;
    _anyGetterWriter = _anyGetterWriter;
    _objectIdWriter = paramObjectIdWriter;
    _propertyFilterId = paramObject;
    _serializationShape = _serializationShape;
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, NameTransformer paramNameTransformer)
  {
    this(paramBeanSerializerBase, rename(_props, paramNameTransformer), rename(_filteredProps, paramNameTransformer));
  }
  
  public BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2)
  {
    super(_handledType);
    _props = paramArrayOfBeanPropertyWriter1;
    _filteredProps = paramArrayOfBeanPropertyWriter2;
    _typeId = _typeId;
    _anyGetterWriter = _anyGetterWriter;
    _objectIdWriter = _objectIdWriter;
    _propertyFilterId = _propertyFilterId;
    _serializationShape = _serializationShape;
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, String[] paramArrayOfString)
  {
    super(_handledType);
    HashSet localHashSet = ArrayBuilders.arrayToSet(paramArrayOfString);
    BeanPropertyWriter[] arrayOfBeanPropertyWriter1 = _props;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter2 = _filteredProps;
    int j = arrayOfBeanPropertyWriter1.length;
    ArrayList localArrayList = new ArrayList(j);
    int i;
    label54:
    BeanPropertyWriter localBeanPropertyWriter;
    if (arrayOfBeanPropertyWriter2 == null)
    {
      paramArrayOfString = null;
      i = 0;
      if (i >= j) {
        break label124;
      }
      localBeanPropertyWriter = arrayOfBeanPropertyWriter1[i];
      if (!localHashSet.contains(localBeanPropertyWriter.getName())) {
        break label99;
      }
    }
    for (;;)
    {
      i += 1;
      break label54;
      paramArrayOfString = new ArrayList(j);
      break;
      label99:
      localArrayList.add(localBeanPropertyWriter);
      if (arrayOfBeanPropertyWriter2 != null) {
        paramArrayOfString.add(arrayOfBeanPropertyWriter2[i]);
      }
    }
    label124:
    _props = ((BeanPropertyWriter[])localArrayList.toArray(new BeanPropertyWriter[localArrayList.size()]));
    if (paramArrayOfString == null) {}
    for (paramArrayOfString = (String[])localObject;; paramArrayOfString = (BeanPropertyWriter[])paramArrayOfString.toArray(new BeanPropertyWriter[paramArrayOfString.size()]))
    {
      _filteredProps = paramArrayOfString;
      _typeId = _typeId;
      _anyGetterWriter = _anyGetterWriter;
      _objectIdWriter = _objectIdWriter;
      _propertyFilterId = _propertyFilterId;
      _serializationShape = _serializationShape;
      return;
    }
  }
  
  private final String _customTypeId(Object paramObject)
  {
    paramObject = _typeId.getValue(paramObject);
    if (paramObject == null) {
      return "";
    }
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    return paramObject.toString();
  }
  
  private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] paramArrayOfBeanPropertyWriter, NameTransformer paramNameTransformer)
  {
    if ((paramArrayOfBeanPropertyWriter == null) || (paramArrayOfBeanPropertyWriter.length == 0) || (paramNameTransformer == null) || (paramNameTransformer == NameTransformer.NOP)) {
      return paramArrayOfBeanPropertyWriter;
    }
    int j = paramArrayOfBeanPropertyWriter.length;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter = new BeanPropertyWriter[j];
    int i = 0;
    while (i < j)
    {
      BeanPropertyWriter localBeanPropertyWriter = paramArrayOfBeanPropertyWriter[i];
      if (localBeanPropertyWriter != null) {
        arrayOfBeanPropertyWriter[i] = localBeanPropertyWriter.rename(paramNameTransformer);
      }
      i += 1;
    }
    return arrayOfBeanPropertyWriter;
  }
  
  protected final void _serializeWithObjectId(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    ObjectIdWriter localObjectIdWriter = _objectIdWriter;
    WritableObjectId localWritableObjectId = paramSerializerProvider.findObjectId(paramObject, generator);
    if (localWritableObjectId.writeAsId(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter)) {
      return;
    }
    Object localObject = localWritableObjectId.generateId(paramObject);
    if (alwaysAsId)
    {
      serializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    if (_typeId == null)
    {
      localObject = null;
      if (localObject != null) {
        break label128;
      }
      paramTypeSerializer.writeTypePrefixForObject(paramObject, paramJsonGenerator);
      label82:
      localWritableObjectId.writeAsField(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter);
      if (_propertyFilterId == null) {
        break label140;
      }
      serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      if (localObject != null) {
        break label150;
      }
      paramTypeSerializer.writeTypeSuffixForObject(paramObject, paramJsonGenerator);
      return;
      localObject = _customTypeId(paramObject);
      break;
      label128:
      paramTypeSerializer.writeCustomTypePrefixForObject(paramObject, paramJsonGenerator, (String)localObject);
      break label82;
      label140:
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    label150:
    paramTypeSerializer.writeCustomTypeSuffixForObject(paramObject, paramJsonGenerator, (String)localObject);
  }
  
  protected final void _serializeWithObjectId(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, boolean paramBoolean)
    throws IOException, JsonGenerationException
  {
    ObjectIdWriter localObjectIdWriter = _objectIdWriter;
    WritableObjectId localWritableObjectId = paramSerializerProvider.findObjectId(paramObject, generator);
    if (localWritableObjectId.writeAsId(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter)) {}
    for (;;)
    {
      return;
      Object localObject = localWritableObjectId.generateId(paramObject);
      if (alwaysAsId)
      {
        serializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        return;
      }
      if (paramBoolean) {
        paramJsonGenerator.writeStartObject();
      }
      localWritableObjectId.writeAsField(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter);
      if (_propertyFilterId != null) {
        serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
      while (paramBoolean)
      {
        paramJsonGenerator.writeEndObject();
        return;
        serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    int i = 0;
    int j = 0;
    if (paramJsonFormatVisitorWrapper == null) {}
    for (;;)
    {
      return;
      paramJavaType = paramJsonFormatVisitorWrapper.expectObjectFormat(paramJavaType);
      if (paramJavaType != null) {
        if (_propertyFilterId != null)
        {
          PropertyFilter localPropertyFilter = findPropertyFilter(paramJsonFormatVisitorWrapper.getProvider(), _propertyFilterId, null);
          i = j;
          while (i < _props.length)
          {
            localPropertyFilter.depositSchemaProperty(_props[i], paramJavaType, paramJsonFormatVisitorWrapper.getProvider());
            i += 1;
          }
        }
        else
        {
          while (i < _props.length)
          {
            _props[i].depositSchemaProperty(paramJavaType);
            i += 1;
          }
        }
      }
    }
  }
  
  protected abstract BeanSerializerBase asArraySerializer();
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = _objectIdWriter;
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Object localObject3;
    String[] arrayOfString;
    Object localObject1;
    label96:
    Object localObject4;
    if ((paramBeanProperty == null) || (localAnnotationIntrospector == null))
    {
      localObject3 = null;
      if (localObject3 == null) {
        break label579;
      }
      arrayOfString = localAnnotationIntrospector.findPropertiesToIgnore((Annotated)localObject3);
      localObject1 = localAnnotationIntrospector.findObjectIdInfo((Annotated)localObject3);
      if (localObject1 != null) {
        break label267;
      }
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localAnnotationIntrospector.findObjectReferenceInfo((Annotated)localObject3, new ObjectIdInfo(NAME_FOR_OBJECT_REF, null, null));
        localObject1 = _objectIdWriter.withAlwaysAsId(((ObjectIdInfo)localObject1).getAlwaysAsId());
      }
      localObject4 = localAnnotationIntrospector.findFilterId((Annotated)localObject3);
      if ((localObject4 == null) || ((_propertyFilterId != null) && (localObject4.equals(_propertyFilterId)))) {
        break label569;
      }
      localObject2 = localObject1;
      localObject1 = localObject4;
    }
    for (;;)
    {
      if (localObject2 != null)
      {
        paramSerializerProvider = ((ObjectIdWriter)localObject2).withSerializer(paramSerializerProvider.findValueSerializer(idType, paramBeanProperty));
        if (paramSerializerProvider == _objectIdWriter) {}
      }
      for (paramBeanProperty = withObjectIdWriter(paramSerializerProvider);; paramBeanProperty = this)
      {
        paramSerializerProvider = paramBeanProperty;
        if (arrayOfString != null)
        {
          paramSerializerProvider = paramBeanProperty;
          if (arrayOfString.length != 0) {
            paramSerializerProvider = paramBeanProperty.withIgnorals(arrayOfString);
          }
        }
        paramBeanProperty = paramSerializerProvider;
        if (localObject1 != null) {
          paramBeanProperty = paramSerializerProvider.withFilterId(localObject1);
        }
        if (localObject3 != null)
        {
          paramSerializerProvider = localAnnotationIntrospector.findFormat((Annotated)localObject3);
          if (paramSerializerProvider == null) {}
        }
        for (paramSerializerProvider = paramSerializerProvider.getShape();; paramSerializerProvider = null)
        {
          localObject1 = paramSerializerProvider;
          if (paramSerializerProvider == null) {
            localObject1 = _serializationShape;
          }
          if (localObject1 == JsonFormat.Shape.ARRAY)
          {
            return paramBeanProperty.asArraySerializer();
            localObject3 = paramBeanProperty.getMember();
            break;
            label267:
            localObject1 = localAnnotationIntrospector.findObjectReferenceInfo((Annotated)localObject3, (ObjectIdInfo)localObject1);
            localObject2 = ((ObjectIdInfo)localObject1).getGeneratorType();
            localObject4 = paramSerializerProvider.constructType((Type)localObject2);
            localObject4 = paramSerializerProvider.getTypeFactory().findTypeParameters(localObject4, ObjectIdGenerator.class)[0];
            if (localObject2 == ObjectIdGenerators.PropertyGenerator.class)
            {
              localObject4 = ((ObjectIdInfo)localObject1).getPropertyName().getSimpleName();
              int j = _props.length;
              int i = 0;
              for (;;)
              {
                if (i == j) {
                  throw new IllegalArgumentException("Invalid Object Id definition for " + _handledType.getName() + ": can not find property with name '" + (String)localObject4 + "'");
                }
                localObject2 = _props[i];
                if (((String)localObject4).equals(((BeanPropertyWriter)localObject2).getName()))
                {
                  if (i > 0)
                  {
                    System.arraycopy(_props, 0, _props, 1, i);
                    _props[0] = localObject2;
                    if (_filteredProps != null)
                    {
                      localObject4 = _filteredProps[i];
                      System.arraycopy(_filteredProps, 0, _filteredProps, 1, i);
                      _filteredProps[0] = localObject4;
                    }
                  }
                  localObject4 = ((BeanPropertyWriter)localObject2).getType();
                  localObject2 = new PropertyBasedObjectIdGenerator((ObjectIdInfo)localObject1, (BeanPropertyWriter)localObject2);
                  localObject1 = ObjectIdWriter.construct((JavaType)localObject4, (PropertyName)null, (ObjectIdGenerator)localObject2, ((ObjectIdInfo)localObject1).getAlwaysAsId());
                  break;
                }
                i += 1;
              }
            }
            localObject2 = paramSerializerProvider.objectIdGeneratorInstance((Annotated)localObject3, (ObjectIdInfo)localObject1);
            localObject1 = ObjectIdWriter.construct((JavaType)localObject4, ((ObjectIdInfo)localObject1).getPropertyName(), (ObjectIdGenerator)localObject2, ((ObjectIdInfo)localObject1).getAlwaysAsId());
            break label96;
          }
          return paramBeanProperty;
        }
      }
      label569:
      localObject2 = localObject1;
      localObject1 = null;
      continue;
      label579:
      arrayOfString = null;
      localObject1 = null;
    }
  }
  
  protected JsonSerializer<Object> findConvertingSerializer(SerializerProvider paramSerializerProvider, BeanPropertyWriter paramBeanPropertyWriter)
    throws JsonMappingException
  {
    Object localObject = paramSerializerProvider.getAnnotationIntrospector();
    if (localObject != null)
    {
      localObject = ((AnnotationIntrospector)localObject).findSerializationConverter(paramBeanPropertyWriter.getMember());
      if (localObject != null)
      {
        localObject = paramSerializerProvider.converterInstance(paramBeanPropertyWriter.getMember(), localObject);
        JavaType localJavaType = ((Converter)localObject).getOutputType(paramSerializerProvider.getTypeFactory());
        return new StdDelegatingSerializer((Converter)localObject, localJavaType, paramSerializerProvider.findValueSerializer(localJavaType, paramBeanPropertyWriter));
      }
    }
    return null;
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode1 = createSchemaNode("object", true);
    paramType = (JsonSerializableSchema)_handledType.getAnnotation(JsonSerializableSchema.class);
    if (paramType != null)
    {
      paramType = paramType.id();
      if ((paramType != null) && (paramType.length() > 0)) {
        localObjectNode1.put("id", paramType);
      }
    }
    ObjectNode localObjectNode2 = localObjectNode1.objectNode();
    int i;
    label83:
    BeanPropertyWriter localBeanPropertyWriter;
    if (_propertyFilterId != null)
    {
      paramType = findPropertyFilter(paramSerializerProvider, _propertyFilterId, null);
      i = 0;
      if (i >= _props.length) {
        break label138;
      }
      localBeanPropertyWriter = _props[i];
      if (paramType != null) {
        break label124;
      }
      localBeanPropertyWriter.depositSchemaProperty(localObjectNode2, paramSerializerProvider);
    }
    for (;;)
    {
      i += 1;
      break label83;
      paramType = null;
      break;
      label124:
      paramType.depositSchemaProperty(localBeanPropertyWriter, localObjectNode2, paramSerializerProvider);
    }
    label138:
    localObjectNode1.put("properties", localObjectNode2);
    return localObjectNode1;
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    int i;
    int j;
    label18:
    BeanPropertyWriter localBeanPropertyWriter;
    Object localObject1;
    Object localObject2;
    if (_filteredProps == null)
    {
      i = 0;
      int k = _props.length;
      j = 0;
      if (j >= k) {
        break label303;
      }
      localBeanPropertyWriter = _props[j];
      if ((!localBeanPropertyWriter.willSuppressNulls()) && (!localBeanPropertyWriter.hasNullSerializer()))
      {
        localObject1 = paramSerializerProvider.findNullValueSerializer(localBeanPropertyWriter);
        if (localObject1 != null)
        {
          localBeanPropertyWriter.assignNullSerializer((JsonSerializer)localObject1);
          if (j < i)
          {
            localObject2 = _filteredProps[j];
            if (localObject2 != null) {
              ((BeanPropertyWriter)localObject2).assignNullSerializer((JsonSerializer)localObject1);
            }
          }
        }
      }
      if (!localBeanPropertyWriter.hasSerializer()) {
        break label117;
      }
    }
    for (;;)
    {
      j += 1;
      break label18;
      i = _filteredProps.length;
      break;
      label117:
      localObject2 = findConvertingSerializer(paramSerializerProvider, localBeanPropertyWriter);
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = localBeanPropertyWriter.getSerializationType();
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject1 = paramSerializerProvider.constructType(localBeanPropertyWriter.getGenericPropertyType());
          localObject2 = localObject1;
          if (!((JavaType)localObject1).isFinal())
          {
            if ((!((JavaType)localObject1).isContainerType()) && (((JavaType)localObject1).containedTypeCount() <= 0)) {
              continue;
            }
            localBeanPropertyWriter.setNonTrivialBaseType((JavaType)localObject1);
            continue;
          }
        }
        JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer((JavaType)localObject2, localBeanPropertyWriter);
        localObject1 = localJsonSerializer;
        if (((JavaType)localObject2).isContainerType())
        {
          localObject2 = (TypeSerializer)((JavaType)localObject2).getContentType().getTypeHandler();
          localObject1 = localJsonSerializer;
          if (localObject2 != null)
          {
            localObject1 = localJsonSerializer;
            if ((localJsonSerializer instanceof ContainerSerializer)) {
              localObject1 = ((ContainerSerializer)localJsonSerializer).withValueTypeSerializer((TypeSerializer)localObject2);
            }
          }
        }
      }
      localBeanPropertyWriter.assignSerializer((JsonSerializer)localObject1);
      if (j < i)
      {
        localObject2 = _filteredProps[j];
        if (localObject2 != null) {
          ((BeanPropertyWriter)localObject2).assignSerializer((JsonSerializer)localObject1);
        }
      }
    }
    label303:
    if (_anyGetterWriter != null) {
      _anyGetterWriter.resolve(paramSerializerProvider);
    }
  }
  
  public abstract void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException;
  
  protected void serializeFields(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if ((_filteredProps != null) && (paramSerializerProvider.getActiveView() != null)) {
      arrayOfBeanPropertyWriter = _filteredProps;
    }
    int k;
    for (;;)
    {
      int j = 0;
      k = 0;
      int i = 0;
      try
      {
        int m = arrayOfBeanPropertyWriter.length;
        for (;;)
        {
          if (i < m)
          {
            BeanPropertyWriter localBeanPropertyWriter = arrayOfBeanPropertyWriter[i];
            if (localBeanPropertyWriter != null)
            {
              j = i;
              k = i;
              localBeanPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
            }
            i += 1;
            continue;
            arrayOfBeanPropertyWriter = _props;
            break;
          }
        }
        j = i;
        k = i;
        if (_anyGetterWriter != null)
        {
          j = i;
          k = i;
          _anyGetterWriter.getAndSerialize(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
        return;
      }
      catch (Exception localException)
      {
        if (j == arrayOfBeanPropertyWriter.length) {}
        for (paramJsonGenerator = "[anySetter]";; paramJsonGenerator = arrayOfBeanPropertyWriter[j].getName())
        {
          wrapAndThrow(paramSerializerProvider, localException, paramObject, paramJsonGenerator);
          return;
        }
      }
      catch (StackOverflowError paramJsonGenerator)
      {
        paramSerializerProvider = new JsonMappingException("Infinite recursion (StackOverflowError)", paramJsonGenerator);
        if (k != arrayOfBeanPropertyWriter.length) {}
      }
    }
    for (paramJsonGenerator = "[anySetter]";; paramJsonGenerator = arrayOfBeanPropertyWriter[k].getName())
    {
      paramSerializerProvider.prependPath(new JsonMappingException.Reference(paramObject, paramJsonGenerator));
      throw paramSerializerProvider;
    }
  }
  
  protected void serializeFieldsFiltered(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    PropertyFilter localPropertyFilter;
    if ((_filteredProps != null) && (paramSerializerProvider.getActiveView() != null))
    {
      arrayOfBeanPropertyWriter = _filteredProps;
      localPropertyFilter = findPropertyFilter(paramSerializerProvider, _propertyFilterId, paramObject);
      if (localPropertyFilter != null) {
        break label54;
      }
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      arrayOfBeanPropertyWriter = _props;
      break;
      label54:
      int j = 0;
      int k = 0;
      int i = 0;
      try
      {
        int m = arrayOfBeanPropertyWriter.length;
        if (i < m)
        {
          BeanPropertyWriter localBeanPropertyWriter = arrayOfBeanPropertyWriter[i];
          if (localBeanPropertyWriter != null)
          {
            j = i;
            k = i;
            localPropertyFilter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider, localBeanPropertyWriter);
          }
        }
        else
        {
          j = i;
          k = i;
          if (_anyGetterWriter == null) {
            continue;
          }
          j = i;
          k = i;
          _anyGetterWriter.getAndFilter(paramObject, paramJsonGenerator, paramSerializerProvider, localPropertyFilter);
          return;
        }
      }
      catch (Exception localException)
      {
        if (j == arrayOfBeanPropertyWriter.length) {}
        for (paramJsonGenerator = "[anySetter]";; paramJsonGenerator = arrayOfBeanPropertyWriter[j].getName())
        {
          wrapAndThrow(paramSerializerProvider, localException, paramObject, paramJsonGenerator);
          return;
        }
      }
      catch (StackOverflowError paramJsonGenerator)
      {
        for (;;)
        {
          paramSerializerProvider = new JsonMappingException("Infinite recursion (StackOverflowError)", paramJsonGenerator);
          if (k == arrayOfBeanPropertyWriter.length) {}
          for (paramJsonGenerator = "[anySetter]";; paramJsonGenerator = arrayOfBeanPropertyWriter[k].getName())
          {
            paramSerializerProvider.prependPath(new JsonMappingException.Reference(paramObject, paramJsonGenerator));
            throw paramSerializerProvider;
          }
          i += 1;
        }
      }
    }
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    if (_objectIdWriter != null)
    {
      _serializeWithObjectId(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
      return;
    }
    String str;
    if (_typeId == null)
    {
      str = null;
      if (str != null) {
        break label76;
      }
      paramTypeSerializer.writeTypePrefixForObject(paramObject, paramJsonGenerator);
      label39:
      if (_propertyFilterId == null) {
        break label88;
      }
      serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      if (str != null) {
        break label98;
      }
      paramTypeSerializer.writeTypeSuffixForObject(paramObject, paramJsonGenerator);
      return;
      str = _customTypeId(paramObject);
      break;
      label76:
      paramTypeSerializer.writeCustomTypePrefixForObject(paramObject, paramJsonGenerator, str);
      break label39;
      label88:
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    label98:
    paramTypeSerializer.writeCustomTypeSuffixForObject(paramObject, paramJsonGenerator, str);
  }
  
  public boolean usesObjectId()
  {
    return _objectIdWriter != null;
  }
  
  protected abstract BeanSerializerBase withFilterId(Object paramObject);
  
  protected abstract BeanSerializerBase withIgnorals(String[] paramArrayOfString);
  
  public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter paramObjectIdWriter);
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */