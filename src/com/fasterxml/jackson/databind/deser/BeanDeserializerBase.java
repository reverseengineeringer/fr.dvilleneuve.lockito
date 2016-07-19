package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import com.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BeanDeserializerBase
  extends StdDeserializer<Object>
  implements ContextualDeserializer, ResolvableDeserializer, Serializable
{
  protected static final PropertyName TEMP_PROPERTY_NAME = new PropertyName("#temporary-name");
  private static final long serialVersionUID = 2960120955735322578L;
  protected SettableAnyProperty _anySetter;
  protected final Map<String, SettableBeanProperty> _backRefs;
  protected final BeanPropertyMap _beanProperties;
  protected final JavaType _beanType;
  private final transient Annotations _classAnnotations;
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected ExternalTypeHandler _externalTypeIdHandler;
  protected final HashSet<String> _ignorableProps;
  protected final boolean _ignoreAllUnknown;
  protected final ValueInjector[] _injectables;
  protected final boolean _needViewProcesing;
  protected boolean _nonStandardCreation;
  protected final ObjectIdReader _objectIdReader;
  protected PropertyBasedCreator _propertyBasedCreator;
  protected final JsonFormat.Shape _serializationShape;
  protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
  protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
  protected final ValueInstantiator _valueInstantiator;
  protected boolean _vanillaProcessing;
  
  protected BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase)
  {
    this(paramBeanDeserializerBase, _ignoreAllUnknown);
  }
  
  public BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, ObjectIdReader paramObjectIdReader)
  {
    super(_beanType);
    _classAnnotations = _classAnnotations;
    _beanType = _beanType;
    _valueInstantiator = _valueInstantiator;
    _delegateDeserializer = _delegateDeserializer;
    _propertyBasedCreator = _propertyBasedCreator;
    _backRefs = _backRefs;
    _ignorableProps = _ignorableProps;
    _ignoreAllUnknown = _ignoreAllUnknown;
    _anySetter = _anySetter;
    _injectables = _injectables;
    _nonStandardCreation = _nonStandardCreation;
    _unwrappedPropertyHandler = _unwrappedPropertyHandler;
    _needViewProcesing = _needViewProcesing;
    _serializationShape = _serializationShape;
    _objectIdReader = paramObjectIdReader;
    if (paramObjectIdReader == null)
    {
      _beanProperties = _beanProperties;
      _vanillaProcessing = _vanillaProcessing;
      return;
    }
    paramObjectIdReader = new ObjectIdValueProperty(paramObjectIdReader, PropertyMetadata.STD_REQUIRED);
    _beanProperties = _beanProperties.withProperty(paramObjectIdReader);
    _vanillaProcessing = false;
  }
  
  protected BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, NameTransformer paramNameTransformer)
  {
    super(_beanType);
    _classAnnotations = _classAnnotations;
    _beanType = _beanType;
    _valueInstantiator = _valueInstantiator;
    _delegateDeserializer = _delegateDeserializer;
    _propertyBasedCreator = _propertyBasedCreator;
    _backRefs = _backRefs;
    _ignorableProps = _ignorableProps;
    boolean bool;
    UnwrappedPropertyHandler localUnwrappedPropertyHandler2;
    UnwrappedPropertyHandler localUnwrappedPropertyHandler1;
    if ((paramNameTransformer != null) || (_ignoreAllUnknown))
    {
      bool = true;
      _ignoreAllUnknown = bool;
      _anySetter = _anySetter;
      _injectables = _injectables;
      _objectIdReader = _objectIdReader;
      _nonStandardCreation = _nonStandardCreation;
      localUnwrappedPropertyHandler2 = _unwrappedPropertyHandler;
      if (paramNameTransformer == null) {
        break label186;
      }
      localUnwrappedPropertyHandler1 = localUnwrappedPropertyHandler2;
      if (localUnwrappedPropertyHandler2 != null) {
        localUnwrappedPropertyHandler1 = localUnwrappedPropertyHandler2.renameAll(paramNameTransformer);
      }
      _beanProperties = _beanProperties.renameAll(paramNameTransformer);
    }
    for (;;)
    {
      _unwrappedPropertyHandler = localUnwrappedPropertyHandler1;
      _needViewProcesing = _needViewProcesing;
      _serializationShape = _serializationShape;
      _vanillaProcessing = false;
      return;
      bool = false;
      break;
      label186:
      _beanProperties = _beanProperties;
      localUnwrappedPropertyHandler1 = localUnwrappedPropertyHandler2;
    }
  }
  
  public BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, HashSet<String> paramHashSet)
  {
    super(_beanType);
    _classAnnotations = _classAnnotations;
    _beanType = _beanType;
    _valueInstantiator = _valueInstantiator;
    _delegateDeserializer = _delegateDeserializer;
    _propertyBasedCreator = _propertyBasedCreator;
    _backRefs = _backRefs;
    _ignorableProps = paramHashSet;
    _ignoreAllUnknown = _ignoreAllUnknown;
    _anySetter = _anySetter;
    _injectables = _injectables;
    _nonStandardCreation = _nonStandardCreation;
    _unwrappedPropertyHandler = _unwrappedPropertyHandler;
    _needViewProcesing = _needViewProcesing;
    _serializationShape = _serializationShape;
    _vanillaProcessing = _vanillaProcessing;
    _objectIdReader = _objectIdReader;
    _beanProperties = _beanProperties;
  }
  
  protected BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, boolean paramBoolean)
  {
    super(_beanType);
    _classAnnotations = _classAnnotations;
    _beanType = _beanType;
    _valueInstantiator = _valueInstantiator;
    _delegateDeserializer = _delegateDeserializer;
    _propertyBasedCreator = _propertyBasedCreator;
    _beanProperties = _beanProperties;
    _backRefs = _backRefs;
    _ignorableProps = _ignorableProps;
    _ignoreAllUnknown = paramBoolean;
    _anySetter = _anySetter;
    _injectables = _injectables;
    _objectIdReader = _objectIdReader;
    _nonStandardCreation = _nonStandardCreation;
    _unwrappedPropertyHandler = _unwrappedPropertyHandler;
    _needViewProcesing = _needViewProcesing;
    _serializationShape = _serializationShape;
    _vanillaProcessing = _vanillaProcessing;
  }
  
  protected BeanDeserializerBase(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, BeanPropertyMap paramBeanPropertyMap, Map<String, SettableBeanProperty> paramMap, HashSet<String> paramHashSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramBeanDescription.getType());
    _classAnnotations = paramBeanDescription.getClassInfo().getAnnotations();
    _beanType = paramBeanDescription.getType();
    _valueInstantiator = paramBeanDeserializerBuilder.getValueInstantiator();
    _beanProperties = paramBeanPropertyMap;
    _backRefs = paramMap;
    _ignorableProps = paramHashSet;
    _ignoreAllUnknown = paramBoolean1;
    _anySetter = paramBeanDeserializerBuilder.getAnySetter();
    paramBeanPropertyMap = paramBeanDeserializerBuilder.getInjectables();
    if ((paramBeanPropertyMap == null) || (paramBeanPropertyMap.isEmpty()))
    {
      paramBeanPropertyMap = null;
      _injectables = paramBeanPropertyMap;
      _objectIdReader = paramBeanDeserializerBuilder.getObjectIdReader();
      if ((_unwrappedPropertyHandler == null) && (!_valueInstantiator.canCreateUsingDelegate()) && (!_valueInstantiator.canCreateFromObjectWith()) && (_valueInstantiator.canCreateUsingDefault())) {
        break label236;
      }
      paramBoolean1 = true;
      label145:
      _nonStandardCreation = paramBoolean1;
      paramBeanDeserializerBuilder = paramBeanDescription.findExpectedFormat(null);
      if (paramBeanDeserializerBuilder != null) {
        break label242;
      }
      paramBeanDeserializerBuilder = (BeanDeserializerBuilder)localObject;
      label164:
      _serializationShape = paramBeanDeserializerBuilder;
      _needViewProcesing = paramBoolean2;
      if ((_nonStandardCreation) || (_injectables != null) || (_needViewProcesing) || (_objectIdReader != null)) {
        break label250;
      }
    }
    label236:
    label242:
    label250:
    for (paramBoolean1 = bool;; paramBoolean1 = false)
    {
      _vanillaProcessing = paramBoolean1;
      return;
      paramBeanPropertyMap = (ValueInjector[])paramBeanPropertyMap.toArray(new ValueInjector[paramBeanPropertyMap.size()]);
      break;
      paramBoolean1 = false;
      break label145;
      paramBeanDeserializerBuilder = paramBeanDeserializerBuilder.getShape();
      break label164;
    }
  }
  
  private Throwable throwOrReturnThrowable(Throwable paramThrowable, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonProcessingException))) {
        return paramThrowable;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    return paramThrowable;
  }
  
  protected Object _convertObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, JsonDeserializer<Object> paramJsonDeserializer)
    throws IOException, JsonProcessingException
  {
    paramJsonParser = new TokenBuffer(paramJsonParser);
    if ((paramObject instanceof String)) {
      paramJsonParser.writeString((String)paramObject);
    }
    for (;;)
    {
      paramJsonParser = paramJsonParser.asParser();
      paramJsonParser.nextToken();
      return paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      if ((paramObject instanceof Long)) {
        paramJsonParser.writeNumber(((Long)paramObject).longValue());
      } else if ((paramObject instanceof Integer)) {
        paramJsonParser.writeNumber(((Integer)paramObject).intValue());
      } else {
        paramJsonParser.writeObject(paramObject);
      }
    }
  }
  
  protected abstract Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException;
  
  protected JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    try
    {
      if (_subDeserializers == null) {}
      for (paramTokenBuffer = null;; paramTokenBuffer = (JsonDeserializer)_subDeserializers.get(new ClassKey(paramObject.getClass())))
      {
        if (paramTokenBuffer == null) {
          break;
        }
        return paramTokenBuffer;
      }
      paramDeserializationContext = paramDeserializationContext.findRootValueDeserializer(paramDeserializationContext.constructType(paramObject.getClass()));
    }
    finally {}
    if (paramDeserializationContext != null) {
      try
      {
        if (_subDeserializers == null) {
          _subDeserializers = new HashMap();
        }
        _subDeserializers.put(new ClassKey(paramObject.getClass()), paramDeserializationContext);
        return paramDeserializationContext;
      }
      finally {}
    }
    return paramDeserializationContext;
  }
  
  protected Object _handleTypedObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject1, Object paramObject2)
    throws IOException, JsonProcessingException
  {
    JsonDeserializer localJsonDeserializer = _objectIdReader.getDeserializer();
    if (localJsonDeserializer.handledType() == paramObject2.getClass()) {}
    for (;;)
    {
      paramDeserializationContext.findObjectId(paramObject2, _objectIdReader.generator).bindItem(paramObject1);
      paramDeserializationContext = _objectIdReader.idProperty;
      paramJsonParser = (JsonParser)paramObject1;
      if (paramDeserializationContext != null) {
        paramJsonParser = paramDeserializationContext.setAndReturn(paramObject1, paramObject2);
      }
      return paramJsonParser;
      paramObject2 = _convertObjectId(paramJsonParser, paramDeserializationContext, paramObject2, localJsonDeserializer);
    }
  }
  
  protected SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    Object localObject2 = paramSettableBeanProperty.getValueDeserializer();
    Object localObject1 = paramSettableBeanProperty;
    Object localObject3;
    int j;
    int i;
    if ((localObject2 instanceof BeanDeserializerBase))
    {
      localObject1 = paramSettableBeanProperty;
      if (!((BeanDeserializerBase)localObject2).getValueInstantiator().canCreateUsingDefault())
      {
        localObject3 = paramSettableBeanProperty.getType().getRawClass();
        localObject2 = ClassUtil.getOuterClass((Class)localObject3);
        localObject1 = paramSettableBeanProperty;
        if (localObject2 != null)
        {
          localObject1 = paramSettableBeanProperty;
          if (localObject2 == _beanType.getRawClass())
          {
            localObject3 = ((Class)localObject3).getConstructors();
            j = localObject3.length;
            i = 0;
          }
        }
      }
    }
    for (;;)
    {
      localObject1 = paramSettableBeanProperty;
      if (i < j)
      {
        localObject1 = localObject3[i];
        Class[] arrayOfClass = ((Constructor)localObject1).getParameterTypes();
        if ((arrayOfClass.length == 1) && (arrayOfClass[0] == localObject2))
        {
          if (paramDeserializationContext.getConfig().canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess((Member)localObject1);
          }
          localObject1 = new InnerClassProperty(paramSettableBeanProperty, (Constructor)localObject1);
        }
      }
      else
      {
        return (SettableBeanProperty)localObject1;
      }
      i += 1;
    }
  }
  
  protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    paramDeserializationContext = paramSettableBeanProperty.getManagedReferenceName();
    if (paramDeserializationContext == null) {
      return paramSettableBeanProperty;
    }
    SettableBeanProperty localSettableBeanProperty = paramSettableBeanProperty.getValueDeserializer().findBackReference(paramDeserializationContext);
    if (localSettableBeanProperty == null) {
      throw new IllegalArgumentException("Can not handle managed/back reference '" + paramDeserializationContext + "': no back reference property found from type " + paramSettableBeanProperty.getType());
    }
    JavaType localJavaType1 = _beanType;
    JavaType localJavaType2 = localSettableBeanProperty.getType();
    boolean bool = paramSettableBeanProperty.getType().isContainerType();
    if (!localJavaType2.getRawClass().isAssignableFrom(localJavaType1.getRawClass())) {
      throw new IllegalArgumentException("Can not handle managed/back reference '" + paramDeserializationContext + "': back reference type (" + localJavaType2.getRawClass().getName() + ") not compatible with managed type (" + localJavaType1.getRawClass().getName() + ")");
    }
    return new ManagedReferenceProperty(paramSettableBeanProperty, paramDeserializationContext, localSettableBeanProperty, _classAnnotations, bool);
  }
  
  protected SettableBeanProperty _resolveUnwrappedProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    Object localObject = paramSettableBeanProperty.getMember();
    if (localObject != null)
    {
      localObject = paramDeserializationContext.getAnnotationIntrospector().findUnwrappingNameTransformer((AnnotatedMember)localObject);
      if (localObject != null)
      {
        paramDeserializationContext = paramSettableBeanProperty.getValueDeserializer();
        localObject = paramDeserializationContext.unwrappingDeserializer((NameTransformer)localObject);
        if ((localObject != paramDeserializationContext) && (localObject != null)) {
          return paramSettableBeanProperty.withValueDeserializer((JsonDeserializer)localObject);
        }
      }
    }
    return null;
  }
  
  protected abstract BeanDeserializerBase asArrayDeserializer();
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = _objectIdReader;
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    Object localObject1;
    if ((paramBeanProperty == null) || (localAnnotationIntrospector == null)) {
      localObject1 = null;
    }
    Object localObject3;
    ObjectIdInfo localObjectIdInfo;
    JavaType localJavaType;
    for (;;)
    {
      if ((paramBeanProperty != null) && (localAnnotationIntrospector != null))
      {
        paramBeanProperty = localAnnotationIntrospector.findPropertiesToIgnore((Annotated)localObject1);
        localObject3 = localAnnotationIntrospector.findObjectIdInfo((Annotated)localObject1);
        if (localObject3 != null)
        {
          localObjectIdInfo = localAnnotationIntrospector.findObjectReferenceInfo((Annotated)localObject1, (ObjectIdInfo)localObject3);
          localObject2 = localObjectIdInfo.getGeneratorType();
          if (localObject2 == ObjectIdGenerators.PropertyGenerator.class)
          {
            localObject2 = localObjectIdInfo.getPropertyName();
            localObject3 = findProperty((PropertyName)localObject2);
            if (localObject3 == null)
            {
              throw new IllegalArgumentException("Invalid Object Id definition for " + handledType().getName() + ": can not find property with name '" + localObject2 + "'");
              localObject1 = paramBeanProperty.getMember();
              continue;
            }
            localJavaType = ((SettableBeanProperty)localObject3).getType();
            localObject2 = new PropertyBasedObjectIdGenerator(localObjectIdInfo.getScope());
            paramDeserializationContext = paramDeserializationContext.findRootValueDeserializer(localJavaType);
            localObject2 = ObjectIdReader.construct(localJavaType, localObjectIdInfo.getPropertyName(), (ObjectIdGenerator)localObject2, paramDeserializationContext, (SettableBeanProperty)localObject3);
            paramDeserializationContext = paramBeanProperty;
            paramBeanProperty = (BeanProperty)localObject2;
          }
        }
      }
    }
    for (;;)
    {
      if ((paramBeanProperty != null) && (paramBeanProperty != _objectIdReader)) {}
      for (paramBeanProperty = withObjectIdReader(paramBeanProperty);; paramBeanProperty = this)
      {
        localObject2 = paramBeanProperty;
        if (paramDeserializationContext != null)
        {
          localObject2 = paramBeanProperty;
          if (paramDeserializationContext.length != 0) {
            localObject2 = paramBeanProperty.withIgnorableProperties(ArrayBuilders.setAndArray(_ignorableProps, paramDeserializationContext));
          }
        }
        if (localObject1 != null)
        {
          paramDeserializationContext = localAnnotationIntrospector.findFormat((Annotated)localObject1);
          if (paramDeserializationContext == null) {}
        }
        for (paramDeserializationContext = paramDeserializationContext.getShape();; paramDeserializationContext = null)
        {
          paramBeanProperty = paramDeserializationContext;
          if (paramDeserializationContext == null) {
            paramBeanProperty = _serializationShape;
          }
          if (paramBeanProperty == JsonFormat.Shape.ARRAY)
          {
            return ((BeanDeserializerBase)localObject2).asArrayDeserializer();
            localObject2 = paramDeserializationContext.constructType((Class)localObject2);
            localJavaType = paramDeserializationContext.getTypeFactory().findTypeParameters(localObject2, ObjectIdGenerator.class)[0];
            localObject2 = paramDeserializationContext.objectIdGeneratorInstance((Annotated)localObject1, localObjectIdInfo);
            localObject3 = null;
            break;
          }
          return (JsonDeserializer<?>)localObject2;
        }
      }
      paramDeserializationContext = paramBeanProperty;
      paramBeanProperty = (BeanProperty)localObject2;
      continue;
      paramBeanProperty = (BeanProperty)localObject2;
      paramDeserializationContext = null;
    }
  }
  
  public Iterator<SettableBeanProperty> creatorProperties()
  {
    if (_propertyBasedCreator == null) {
      return Collections.emptyList().iterator();
    }
    return _propertyBasedCreator.properties().iterator();
  }
  
  public Object deserializeFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_delegateDeserializer != null) {
      try
      {
        paramJsonParser = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        if (_injectables != null) {
          injectValues(paramDeserializationContext, paramJsonParser);
        }
        return paramJsonParser;
      }
      catch (Exception paramJsonParser)
      {
        wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
      }
    }
    throw paramDeserializationContext.mappingException(getBeanClass());
  }
  
  public Object deserializeFromBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((_delegateDeserializer != null) && (!_valueInstantiator.canCreateFromBoolean()))
    {
      paramJsonParser = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      if (_injectables != null) {
        injectValues(paramDeserializationContext, paramJsonParser);
      }
      return paramJsonParser;
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_TRUE) {}
    for (boolean bool = true;; bool = false) {
      return _valueInstantiator.createFromBoolean(paramDeserializationContext, bool);
    }
  }
  
  public Object deserializeFromDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    switch (paramJsonParser.getNumberType())
    {
    default: 
      if (_delegateDeserializer != null) {
        paramJsonParser = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      }
      break;
    case ???: 
    case ???: 
      Object localObject;
      do
      {
        return paramJsonParser;
        if ((_delegateDeserializer == null) || (_valueInstantiator.canCreateFromDouble())) {
          break;
        }
        localObject = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        paramJsonParser = (JsonParser)localObject;
      } while (_injectables == null);
      injectValues(paramDeserializationContext, localObject);
      return localObject;
      return _valueInstantiator.createFromDouble(paramDeserializationContext, paramJsonParser.getDoubleValue());
    }
    throw paramDeserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON floating-point number");
  }
  
  public Object deserializeFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_objectIdReader != null) {
      paramJsonParser = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
    }
    Object localObject;
    do
    {
      do
      {
        do
        {
          return paramJsonParser;
          switch (paramJsonParser.getNumberType())
          {
          default: 
            if (_delegateDeserializer == null) {
              break label220;
            }
            localObject = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
            paramJsonParser = (JsonParser)localObject;
          }
        } while (_injectables == null);
        injectValues(paramDeserializationContext, localObject);
        return localObject;
        if ((_delegateDeserializer == null) || (_valueInstantiator.canCreateFromInt())) {
          break;
        }
        localObject = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        paramJsonParser = (JsonParser)localObject;
      } while (_injectables == null);
      injectValues(paramDeserializationContext, localObject);
      return localObject;
      return _valueInstantiator.createFromInt(paramDeserializationContext, paramJsonParser.getIntValue());
      if ((_delegateDeserializer == null) || (_valueInstantiator.canCreateFromInt())) {
        break;
      }
      localObject = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      paramJsonParser = (JsonParser)localObject;
    } while (_injectables == null);
    injectValues(paramDeserializationContext, localObject);
    return localObject;
    return _valueInstantiator.createFromLong(paramDeserializationContext, paramJsonParser.getLongValue());
    label220:
    throw paramDeserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON integer number");
  }
  
  public abstract Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException;
  
  protected Object deserializeFromObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    paramJsonParser = _objectIdReader.readObjectReference(paramJsonParser, paramDeserializationContext);
    paramDeserializationContext = findObjectId_objectIdReader.generator).item;
    if (paramDeserializationContext == null) {
      throw new IllegalStateException("Could not resolve Object Id [" + paramJsonParser + "] (for " + _beanType + ") -- unresolved forward-reference?");
    }
    return paramDeserializationContext;
  }
  
  protected Object deserializeFromObjectUsingNonDefault(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_delegateDeserializer != null) {
      return _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (_propertyBasedCreator != null) {
      return _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext);
    }
    if (_beanType.isAbstract()) {
      throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + _beanType + " (need to add/enable type information?)");
    }
    throw JsonMappingException.from(paramJsonParser, "No suitable constructor found for type " + _beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
  }
  
  public Object deserializeFromString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_objectIdReader != null) {
      paramJsonParser = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
    }
    Object localObject;
    do
    {
      return paramJsonParser;
      if ((_delegateDeserializer == null) || (_valueInstantiator.canCreateFromString())) {
        break;
      }
      localObject = _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      paramJsonParser = (JsonParser)localObject;
    } while (_injectables == null);
    injectValues(paramDeserializationContext, localObject);
    return localObject;
    return _valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
  }
  
  protected Object deserializeWithObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    String str1 = _objectIdReader.propertyName.getSimpleName();
    if ((str1.equals(paramJsonParser.getCurrentName())) || (paramJsonParser.canReadObjectId())) {
      return deserializeFromObject(paramJsonParser, paramDeserializationContext);
    }
    Object localObject = new TokenBuffer(paramJsonParser);
    TokenBuffer localTokenBuffer = null;
    if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
    {
      String str2 = paramJsonParser.getCurrentName();
      if (localTokenBuffer == null) {
        if (str1.equals(str2))
        {
          localTokenBuffer = new TokenBuffer(paramJsonParser);
          localTokenBuffer.writeFieldName(str2);
          paramJsonParser.nextToken();
          localTokenBuffer.copyCurrentStructure(paramJsonParser);
          localTokenBuffer.append((TokenBuffer)localObject);
          localObject = null;
        }
      }
      for (;;)
      {
        paramJsonParser.nextToken();
        break;
        ((TokenBuffer)localObject).writeFieldName(str2);
        paramJsonParser.nextToken();
        ((TokenBuffer)localObject).copyCurrentStructure(paramJsonParser);
        continue;
        localTokenBuffer.writeFieldName(str2);
        paramJsonParser.nextToken();
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
      }
    }
    if (localTokenBuffer == null) {}
    for (;;)
    {
      ((TokenBuffer)localObject).writeEndObject();
      paramJsonParser = ((TokenBuffer)localObject).asParser();
      paramJsonParser.nextToken();
      return deserializeFromObject(paramJsonParser, paramDeserializationContext);
      localObject = localTokenBuffer;
    }
  }
  
  public final Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    if (_objectIdReader != null)
    {
      if (paramJsonParser.canReadObjectId())
      {
        localObject = paramJsonParser.getObjectId();
        if (localObject != null) {
          return _handleTypedObjectId(paramJsonParser, paramDeserializationContext, paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext), localObject);
        }
      }
      Object localObject = paramJsonParser.getCurrentToken();
      if ((localObject != null) && (((JsonToken)localObject).isScalarValue())) {
        return deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
      }
    }
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    if (_backRefs == null) {
      return null;
    }
    return (SettableBeanProperty)_backRefs.get(paramString);
  }
  
  protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector();
    if (localObject != null)
    {
      localObject = ((AnnotationIntrospector)localObject).findDeserializationConverter(paramSettableBeanProperty.getMember());
      if (localObject != null)
      {
        localObject = paramDeserializationContext.converterInstance(paramSettableBeanProperty.getMember(), localObject);
        JavaType localJavaType = ((Converter)localObject).getInputType(paramDeserializationContext.getTypeFactory());
        return new StdDelegatingDeserializer((Converter)localObject, localJavaType, paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramSettableBeanProperty));
      }
    }
    return null;
  }
  
  public SettableBeanProperty findProperty(int paramInt)
  {
    if (_beanProperties == null) {}
    for (SettableBeanProperty localSettableBeanProperty1 = null;; localSettableBeanProperty1 = _beanProperties.find(paramInt))
    {
      SettableBeanProperty localSettableBeanProperty2 = localSettableBeanProperty1;
      if (localSettableBeanProperty1 == null)
      {
        localSettableBeanProperty2 = localSettableBeanProperty1;
        if (_propertyBasedCreator != null) {
          localSettableBeanProperty2 = _propertyBasedCreator.findCreatorProperty(paramInt);
        }
      }
      return localSettableBeanProperty2;
    }
  }
  
  public SettableBeanProperty findProperty(PropertyName paramPropertyName)
  {
    return findProperty(paramPropertyName.getSimpleName());
  }
  
  public SettableBeanProperty findProperty(String paramString)
  {
    if (_beanProperties == null) {}
    for (SettableBeanProperty localSettableBeanProperty1 = null;; localSettableBeanProperty1 = _beanProperties.find(paramString))
    {
      SettableBeanProperty localSettableBeanProperty2 = localSettableBeanProperty1;
      if (localSettableBeanProperty1 == null)
      {
        localSettableBeanProperty2 = localSettableBeanProperty1;
        if (_propertyBasedCreator != null) {
          localSettableBeanProperty2 = _propertyBasedCreator.findCreatorProperty(paramString);
        }
      }
      return localSettableBeanProperty2;
    }
  }
  
  @Deprecated
  public final Class<?> getBeanClass()
  {
    return _beanType.getRawClass();
  }
  
  public Collection<Object> getKnownPropertyNames()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = _beanProperties.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((SettableBeanProperty)localIterator.next()).getName());
    }
    return localArrayList;
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return _objectIdReader;
  }
  
  public int getPropertyCount()
  {
    return _beanProperties.size();
  }
  
  public ValueInstantiator getValueInstantiator()
  {
    return _valueInstantiator;
  }
  
  public JavaType getValueType()
  {
    return _beanType;
  }
  
  protected void handleIgnoredProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)) {
      throw IgnoredPropertyException.from(paramJsonParser, paramObject, paramString, getKnownPropertyNames());
    }
    paramJsonParser.skipChildren();
  }
  
  protected Object handlePolymorphic(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    JsonDeserializer localJsonDeserializer = _findSubclassDeserializer(paramDeserializationContext, paramObject, paramTokenBuffer);
    if (localJsonDeserializer != null)
    {
      if (paramTokenBuffer == null) {
        break label105;
      }
      paramTokenBuffer.writeEndObject();
      paramTokenBuffer = paramTokenBuffer.asParser();
      paramTokenBuffer.nextToken();
    }
    label105:
    for (paramTokenBuffer = localJsonDeserializer.deserialize(paramTokenBuffer, paramDeserializationContext, paramObject);; paramTokenBuffer = (TokenBuffer)paramObject)
    {
      paramObject = paramTokenBuffer;
      if (paramJsonParser != null) {
        paramObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext, paramTokenBuffer);
      }
      return paramObject;
      if (paramTokenBuffer != null) {}
      for (paramTokenBuffer = handleUnknownProperties(paramDeserializationContext, paramObject, paramTokenBuffer);; paramTokenBuffer = (TokenBuffer)paramObject)
      {
        paramObject = paramTokenBuffer;
        if (paramJsonParser == null) {
          break;
        }
        return deserialize(paramJsonParser, paramDeserializationContext, paramTokenBuffer);
      }
    }
  }
  
  protected Object handleUnknownProperties(DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    paramTokenBuffer.writeEndObject();
    paramTokenBuffer = paramTokenBuffer.asParser();
    while (paramTokenBuffer.nextToken() != JsonToken.END_OBJECT)
    {
      String str = paramTokenBuffer.getCurrentName();
      paramTokenBuffer.nextToken();
      handleUnknownProperty(paramTokenBuffer, paramDeserializationContext, paramObject, str);
    }
    return paramObject;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    if (_ignoreAllUnknown)
    {
      paramJsonParser.skipChildren();
      return;
    }
    if ((_ignorableProps != null) && (_ignorableProps.contains(paramString))) {
      handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
    }
    super.handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
  }
  
  protected void handleUnknownVanilla(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    if ((_ignorableProps != null) && (_ignorableProps.contains(paramString)))
    {
      handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
      return;
    }
    if (_anySetter != null) {
      try
      {
        _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, paramString);
        return;
      }
      catch (Exception paramJsonParser)
      {
        wrapAndThrow(paramJsonParser, paramObject, paramString, paramDeserializationContext);
        return;
      }
    }
    handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
  }
  
  public Class<?> handledType()
  {
    return _beanType.getRawClass();
  }
  
  public boolean hasProperty(String paramString)
  {
    return _beanProperties.find(paramString) != null;
  }
  
  public boolean hasViews()
  {
    return _needViewProcesing;
  }
  
  protected void injectValues(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    ValueInjector[] arrayOfValueInjector = _injectables;
    int j = arrayOfValueInjector.length;
    int i = 0;
    while (i < j)
    {
      arrayOfValueInjector[i].inject(paramDeserializationContext, paramObject);
      i += 1;
    }
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  public Iterator<SettableBeanProperty> properties()
  {
    if (_beanProperties == null) {
      throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
    }
    return _beanProperties.iterator();
  }
  
  public void replaceProperty(SettableBeanProperty paramSettableBeanProperty1, SettableBeanProperty paramSettableBeanProperty2)
  {
    _beanProperties.replace(paramSettableBeanProperty2);
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    Object localObject4;
    Object localObject5;
    if (_valueInstantiator.canCreateFromObjectWith())
    {
      localObject1 = _valueInstantiator.getFromObjectArguments(paramDeserializationContext.getConfig());
      _propertyBasedCreator = PropertyBasedCreator.construct(paramDeserializationContext, _valueInstantiator, (SettableBeanProperty[])localObject1);
      localObject3 = _propertyBasedCreator.properties().iterator();
      localObject1 = null;
      for (;;)
      {
        localObject2 = localObject1;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        localObject4 = (SettableBeanProperty)((Iterator)localObject3).next();
        if (((SettableBeanProperty)localObject4).hasValueTypeDeserializer())
        {
          localObject5 = ((SettableBeanProperty)localObject4).getValueTypeDeserializer();
          if (((TypeDeserializer)localObject5).getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY)
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ExternalTypeHandler.Builder();
            }
            ((ExternalTypeHandler.Builder)localObject2).addExternal((SettableBeanProperty)localObject4, (TypeDeserializer)localObject5);
            localObject1 = localObject2;
          }
        }
      }
    }
    Object localObject2 = null;
    Iterator localIterator = _beanProperties.iterator();
    Object localObject3 = null;
    Object localObject1 = localObject2;
    localObject2 = localObject3;
    if (localIterator.hasNext())
    {
      localObject4 = (SettableBeanProperty)localIterator.next();
      if (!((SettableBeanProperty)localObject4).hasValueDeserializer())
      {
        localObject5 = findConvertingDeserializer(paramDeserializationContext, (SettableBeanProperty)localObject4);
        localObject3 = localObject5;
        if (localObject5 == null) {
          localObject3 = findDeserializer(paramDeserializationContext, ((SettableBeanProperty)localObject4).getType(), (BeanProperty)localObject4);
        }
        localObject3 = ((SettableBeanProperty)localObject4).withValueDeserializer((JsonDeserializer)localObject3);
      }
    }
    for (;;)
    {
      label226:
      localObject5 = _resolveManagedReferenceProperty(paramDeserializationContext, (SettableBeanProperty)localObject3);
      localObject3 = _resolveUnwrappedProperty(paramDeserializationContext, (SettableBeanProperty)localObject5);
      if (localObject3 != null)
      {
        if (localObject2 != null) {
          break label628;
        }
        localObject2 = new UnwrappedPropertyHandler();
      }
      label628:
      for (;;)
      {
        ((UnwrappedPropertyHandler)localObject2).addProperty((SettableBeanProperty)localObject3);
        break;
        localObject3 = ((SettableBeanProperty)localObject4).getValueDeserializer();
        localObject5 = paramDeserializationContext.handlePrimaryContextualization((JsonDeserializer)localObject3, (BeanProperty)localObject4);
        if (localObject5 == localObject3) {
          break label631;
        }
        localObject3 = ((SettableBeanProperty)localObject4).withValueDeserializer((JsonDeserializer)localObject5);
        break label226;
        localObject3 = _resolveInnerClassValuedProperty(paramDeserializationContext, (SettableBeanProperty)localObject5);
        if (localObject3 != localObject4) {
          _beanProperties.replace((SettableBeanProperty)localObject3);
        }
        if (!((SettableBeanProperty)localObject3).hasValueTypeDeserializer()) {
          break;
        }
        localObject4 = ((SettableBeanProperty)localObject3).getValueTypeDeserializer();
        if (((TypeDeserializer)localObject4).getTypeInclusion() != JsonTypeInfo.As.EXTERNAL_PROPERTY) {
          break;
        }
        if (localObject1 == null) {
          localObject1 = new ExternalTypeHandler.Builder();
        }
        for (;;)
        {
          ((ExternalTypeHandler.Builder)localObject1).addExternal((SettableBeanProperty)localObject3, (TypeDeserializer)localObject4);
          _beanProperties.remove((SettableBeanProperty)localObject3);
          break;
          if ((_anySetter != null) && (!_anySetter.hasValueDeserializer())) {
            _anySetter = _anySetter.withValueDeserializer(findDeserializer(paramDeserializationContext, _anySetter.getType(), _anySetter.getProperty()));
          }
          if (_valueInstantiator.canCreateUsingDelegate())
          {
            localObject3 = _valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
            if (localObject3 == null) {
              throw new IllegalArgumentException("Invalid delegate-creator definition for " + _beanType + ": value instantiator (" + _valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
            localObject4 = _valueInstantiator.getDelegateCreator();
            _delegateDeserializer = findDeserializer(paramDeserializationContext, (JavaType)localObject3, new BeanProperty.Std(TEMP_PROPERTY_NAME, (JavaType)localObject3, null, _classAnnotations, (AnnotatedMember)localObject4, PropertyMetadata.STD_OPTIONAL));
          }
          if (localObject1 != null)
          {
            _externalTypeIdHandler = ((ExternalTypeHandler.Builder)localObject1).build();
            _nonStandardCreation = true;
          }
          _unwrappedPropertyHandler = ((UnwrappedPropertyHandler)localObject2);
          if (localObject2 != null) {
            _nonStandardCreation = true;
          }
          if ((_vanillaProcessing) && (!_nonStandardCreation)) {}
          for (boolean bool = true;; bool = false)
          {
            _vanillaProcessing = bool;
            return;
          }
        }
      }
      label631:
      localObject3 = localObject4;
    }
  }
  
  public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer);
  
  public abstract BeanDeserializerBase withIgnorableProperties(HashSet<String> paramHashSet);
  
  public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader paramObjectIdReader);
  
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, int paramInt, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(paramThrowable, paramDeserializationContext), paramObject, paramInt);
  }
  
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(paramThrowable, paramDeserializationContext), paramObject, paramString);
  }
  
  protected void wrapInstantiationProblem(Throwable paramThrowable, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0) {
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    throw paramDeserializationContext.instantiationException(_beanType.getRawClass(), paramThrowable);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.BeanDeserializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */