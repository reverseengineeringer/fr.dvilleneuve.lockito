package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BeanSerializerFactory
  extends BasicSerializerFactory
  implements Serializable
{
  public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
  private static final long serialVersionUID = 1L;
  
  protected BeanSerializerFactory(SerializerFactoryConfig paramSerializerFactoryConfig)
  {
    super(paramSerializerFactoryConfig);
  }
  
  protected BeanPropertyWriter _constructWriter(SerializerProvider paramSerializerProvider, BeanPropertyDefinition paramBeanPropertyDefinition, TypeBindings paramTypeBindings, PropertyBuilder paramPropertyBuilder, boolean paramBoolean, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    Object localObject = paramBeanPropertyDefinition.getFullName();
    if (paramSerializerProvider.canOverrideAccessModifiers()) {
      paramAnnotatedMember.fixAccess();
    }
    JavaType localJavaType = paramAnnotatedMember.getType(paramTypeBindings);
    paramTypeBindings = new BeanProperty.Std((PropertyName)localObject, localJavaType, paramBeanPropertyDefinition.getWrapperName(), paramPropertyBuilder.getClassAnnotations(), paramAnnotatedMember, paramBeanPropertyDefinition.getMetadata());
    localObject = findSerializerFromAnnotation(paramSerializerProvider, paramAnnotatedMember);
    if ((localObject instanceof ResolvableSerializer)) {
      ((ResolvableSerializer)localObject).resolve(paramSerializerProvider);
    }
    localObject = paramSerializerProvider.handlePrimaryContextualization((JsonSerializer)localObject, paramTypeBindings);
    paramTypeBindings = null;
    if (ClassUtil.isCollectionMapOrArray(localJavaType.getRawClass())) {
      paramTypeBindings = findPropertyContentTypeSerializer(localJavaType, paramSerializerProvider.getConfig(), paramAnnotatedMember);
    }
    return paramPropertyBuilder.buildWriter(paramSerializerProvider, paramBeanPropertyDefinition, localJavaType, (JsonSerializer)localObject, findPropertyTypeSerializer(localJavaType, paramSerializerProvider.getConfig(), paramAnnotatedMember), paramTypeBindings, paramAnnotatedMember, paramBoolean);
  }
  
  protected JsonSerializer<?> _createSerializer2(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    Object localObject2 = findSerializerByAnnotations(paramSerializerProvider, paramJavaType, paramBeanDescription);
    if (localObject2 != null) {}
    SerializationConfig localSerializationConfig;
    boolean bool;
    Object localObject1;
    do
    {
      return (JsonSerializer<?>)localObject2;
      localSerializationConfig = paramSerializerProvider.getConfig();
      if (!paramJavaType.isContainerType()) {
        break;
      }
      bool = paramBoolean;
      if (!paramBoolean) {
        bool = usesStaticTyping(localSerializationConfig, paramBeanDescription, null);
      }
      localObject1 = buildContainerSerializer(paramSerializerProvider, paramJavaType, paramBeanDescription, bool);
      localObject2 = localObject1;
    } while (localObject1 != null);
    localObject2 = localObject1;
    for (;;)
    {
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = findSerializerByLookup(paramJavaType, localSerializationConfig, paramBeanDescription, bool);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject2 = findSerializerByPrimaryType(paramSerializerProvider, paramJavaType, paramBeanDescription, bool);
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            paramSerializerProvider = findBeanSerializer(paramSerializerProvider, paramJavaType, paramBeanDescription);
            localObject1 = paramSerializerProvider;
            if (paramSerializerProvider == null) {
              localObject1 = findSerializerByAddonType(localSerializationConfig, paramJavaType, paramBeanDescription, bool);
            }
          }
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        break;
      }
      localObject2 = localObject1;
      if (!_factoryConfig.hasSerializerModifiers()) {
        break;
      }
      paramSerializerProvider = _factoryConfig.serializerModifiers().iterator();
      while (paramSerializerProvider.hasNext()) {
        localObject1 = ((BeanSerializerModifier)paramSerializerProvider.next()).modifySerializer(localSerializationConfig, paramBeanDescription, (JsonSerializer)localObject1);
      }
      Iterator localIterator = customSerializers().iterator();
      do
      {
        bool = paramBoolean;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = ((Serializers)localIterator.next()).findSerializer(localSerializationConfig, paramJavaType, paramBeanDescription);
        localObject2 = localObject1;
      } while (localObject1 == null);
      localObject2 = localObject1;
      bool = paramBoolean;
    }
    return (JsonSerializer<?>)localObject1;
  }
  
  protected JsonSerializer<Object> constructBeanSerializer(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (paramBeanDescription.getBeanClass() == Object.class) {
      paramSerializerProvider = paramSerializerProvider.getUnknownTypeSerializer(Object.class);
    }
    Object localObject1;
    do
    {
      Object localObject2;
      do
      {
        return paramSerializerProvider;
        SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
        localObject2 = constructBeanSerializerBuilder(paramBeanDescription);
        ((BeanSerializerBuilder)localObject2).setConfig(localSerializationConfig);
        Object localObject3 = findBeanProperties(paramSerializerProvider, paramBeanDescription, (BeanSerializerBuilder)localObject2);
        localObject1 = localObject3;
        if (localObject3 == null) {
          localObject1 = new ArrayList();
        }
        Iterator localIterator;
        if (_factoryConfig.hasSerializerModifiers())
        {
          localIterator = _factoryConfig.serializerModifiers().iterator();
          for (;;)
          {
            localObject3 = localObject1;
            if (!localIterator.hasNext()) {
              break;
            }
            localObject1 = ((BeanSerializerModifier)localIterator.next()).changeProperties(localSerializationConfig, paramBeanDescription, (List)localObject1);
          }
        }
        localObject3 = localObject1;
        localObject1 = filterBeanProperties(localSerializationConfig, paramBeanDescription, (List)localObject3);
        if (_factoryConfig.hasSerializerModifiers())
        {
          localIterator = _factoryConfig.serializerModifiers().iterator();
          for (;;)
          {
            localObject3 = localObject1;
            if (!localIterator.hasNext()) {
              break;
            }
            localObject1 = ((BeanSerializerModifier)localIterator.next()).orderProperties(localSerializationConfig, paramBeanDescription, (List)localObject1);
          }
        }
        localObject3 = localObject1;
        ((BeanSerializerBuilder)localObject2).setObjectIdWriter(constructObjectIdHandler(paramSerializerProvider, paramBeanDescription, (List)localObject3));
        ((BeanSerializerBuilder)localObject2).setProperties((List)localObject3);
        ((BeanSerializerBuilder)localObject2).setFilterId(findFilterId(localSerializationConfig, paramBeanDescription));
        paramSerializerProvider = paramBeanDescription.findAnyGetter();
        if (paramSerializerProvider != null)
        {
          if (localSerializationConfig.canOverrideAccessModifiers()) {
            paramSerializerProvider.fixAccess();
          }
          localObject3 = paramSerializerProvider.getType(paramBeanDescription.bindingsForBeanType());
          boolean bool = localSerializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING);
          localObject1 = ((JavaType)localObject3).getContentType();
          localObject3 = MapSerializer.construct(null, (JavaType)localObject3, bool, createTypeSerializer(localSerializationConfig, (JavaType)localObject1), null, null, null);
          ((BeanSerializerBuilder)localObject2).setAnyGetter(new AnyGetterWriter(new BeanProperty.Std(new PropertyName(paramSerializerProvider.getName()), (JavaType)localObject1, null, paramBeanDescription.getClassAnnotations(), paramSerializerProvider, PropertyMetadata.STD_OPTIONAL), paramSerializerProvider, (MapSerializer)localObject3));
        }
        processViews(localSerializationConfig, (BeanSerializerBuilder)localObject2);
        if (_factoryConfig.hasSerializerModifiers())
        {
          localObject3 = _factoryConfig.serializerModifiers().iterator();
          for (paramSerializerProvider = (SerializerProvider)localObject2;; paramSerializerProvider = ((BeanSerializerModifier)((Iterator)localObject3).next()).updateBuilder(localSerializationConfig, paramBeanDescription, paramSerializerProvider))
          {
            localObject1 = paramSerializerProvider;
            if (!((Iterator)localObject3).hasNext()) {
              break;
            }
          }
        }
        localObject1 = localObject2;
        localObject2 = ((BeanSerializerBuilder)localObject1).build();
        paramSerializerProvider = (SerializerProvider)localObject2;
      } while (localObject2 != null);
      paramSerializerProvider = (SerializerProvider)localObject2;
    } while (!paramBeanDescription.hasKnownClassAnnotations());
    return ((BeanSerializerBuilder)localObject1).createDummy();
  }
  
  @Deprecated
  protected final JsonSerializer<Object> constructBeanSerializer(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return constructBeanSerializer(paramSerializerProvider, paramBeanDescription);
  }
  
  protected BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription paramBeanDescription)
  {
    return new BeanSerializerBuilder(paramBeanDescription);
  }
  
  protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter paramBeanPropertyWriter, Class<?>[] paramArrayOfClass)
  {
    return FilteredBeanPropertyWriter.constructViewBased(paramBeanPropertyWriter, paramArrayOfClass);
  }
  
  protected ObjectIdWriter constructObjectIdHandler(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription, List<BeanPropertyWriter> paramList)
    throws JsonMappingException
  {
    ObjectIdInfo localObjectIdInfo = paramBeanDescription.getObjectIdInfo();
    if (localObjectIdInfo == null) {
      return null;
    }
    Object localObject = localObjectIdInfo.getGeneratorType();
    if (localObject == ObjectIdGenerators.PropertyGenerator.class)
    {
      localObject = localObjectIdInfo.getPropertyName().getSimpleName();
      int j = paramList.size();
      int i = 0;
      for (;;)
      {
        if (i == j) {
          throw new IllegalArgumentException("Invalid Object Id definition for " + paramBeanDescription.getBeanClass().getName() + ": can not find property with name '" + (String)localObject + "'");
        }
        paramSerializerProvider = (BeanPropertyWriter)paramList.get(i);
        if (((String)localObject).equals(paramSerializerProvider.getName()))
        {
          if (i > 0)
          {
            paramList.remove(i);
            paramList.add(0, paramSerializerProvider);
          }
          paramBeanDescription = paramSerializerProvider.getType();
          paramSerializerProvider = new PropertyBasedObjectIdGenerator(localObjectIdInfo, paramSerializerProvider);
          return ObjectIdWriter.construct(paramBeanDescription, (PropertyName)null, paramSerializerProvider, localObjectIdInfo.getAlwaysAsId());
        }
        i += 1;
      }
    }
    paramList = paramSerializerProvider.constructType((Type)localObject);
    paramList = paramSerializerProvider.getTypeFactory().findTypeParameters(paramList, com.fasterxml.jackson.annotation.ObjectIdGenerator.class)[0];
    paramSerializerProvider = paramSerializerProvider.objectIdGeneratorInstance(paramBeanDescription.getClassInfo(), localObjectIdInfo);
    return ObjectIdWriter.construct(paramList, localObjectIdInfo.getPropertyName(), paramSerializerProvider, localObjectIdInfo.getAlwaysAsId());
  }
  
  protected PropertyBuilder constructPropertyBuilder(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription)
  {
    return new PropertyBuilder(paramSerializationConfig, paramBeanDescription);
  }
  
  public JsonSerializer<Object> createSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType)
    throws JsonMappingException
  {
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    Object localObject1 = localSerializationConfig.introspect(paramJavaType);
    Object localObject2 = findSerializerFromAnnotation(paramSerializerProvider, ((BeanDescription)localObject1).getClassInfo());
    if (localObject2 != null) {
      return (JsonSerializer<Object>)localObject2;
    }
    localObject2 = modifyTypeByAnnotation(localSerializationConfig, ((BeanDescription)localObject1).getClassInfo(), paramJavaType);
    boolean bool;
    if (localObject2 == paramJavaType)
    {
      bool = false;
      paramJavaType = (JavaType)localObject1;
    }
    for (;;)
    {
      localObject1 = paramJavaType.findSerializationConverter();
      if (localObject1 == null)
      {
        return _createSerializer2(paramSerializerProvider, (JavaType)localObject2, paramJavaType, bool);
        if (!((JavaType)localObject2).hasRawClass(paramJavaType.getRawClass()))
        {
          paramJavaType = localSerializationConfig.introspect((JavaType)localObject2);
          bool = true;
        }
      }
      else
      {
        JavaType localJavaType = ((Converter)localObject1).getOutputType(paramSerializerProvider.getTypeFactory());
        if (!localJavaType.hasRawClass(((JavaType)localObject2).getRawClass())) {
          paramJavaType = localSerializationConfig.introspect(localJavaType);
        }
        return new StdDelegatingSerializer((Converter)localObject1, localJavaType, _createSerializer2(paramSerializerProvider, localJavaType, paramJavaType, true));
      }
      bool = true;
      paramJavaType = (JavaType)localObject1;
    }
  }
  
  protected Iterable<Serializers> customSerializers()
  {
    return _factoryConfig.serializers();
  }
  
  protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, List<BeanPropertyWriter> paramList)
  {
    paramSerializationConfig = paramSerializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBeanDescription.getClassInfo());
    if ((paramSerializationConfig != null) && (paramSerializationConfig.length > 0))
    {
      paramSerializationConfig = ArrayBuilders.arrayToSet(paramSerializationConfig);
      paramBeanDescription = paramList.iterator();
      while (paramBeanDescription.hasNext()) {
        if (paramSerializationConfig.contains(((BeanPropertyWriter)paramBeanDescription.next()).getName())) {
          paramBeanDescription.remove();
        }
      }
    }
    return paramList;
  }
  
  protected List<BeanPropertyWriter> findBeanProperties(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription, BeanSerializerBuilder paramBeanSerializerBuilder)
    throws JsonMappingException
  {
    Object localObject = paramBeanDescription.findProperties();
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    removeIgnorableTypes(localSerializationConfig, paramBeanDescription, (List)localObject);
    if (localSerializationConfig.isEnabled(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS)) {
      removeSetterlessGetters(localSerializationConfig, paramBeanDescription, (List)localObject);
    }
    if (((List)localObject).isEmpty()) {
      return null;
    }
    boolean bool = usesStaticTyping(localSerializationConfig, paramBeanDescription, null);
    PropertyBuilder localPropertyBuilder = constructPropertyBuilder(localSerializationConfig, paramBeanDescription);
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    paramBeanDescription = paramBeanDescription.bindingsForBeanType();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)((Iterator)localObject).next();
      AnnotatedMember localAnnotatedMember = localBeanPropertyDefinition.getAccessor();
      if (localBeanPropertyDefinition.isTypeId())
      {
        if (localAnnotatedMember != null)
        {
          if (localSerializationConfig.canOverrideAccessModifiers()) {
            localAnnotatedMember.fixAccess();
          }
          paramBeanSerializerBuilder.setTypeId(localAnnotatedMember);
        }
      }
      else
      {
        AnnotationIntrospector.ReferenceProperty localReferenceProperty = localBeanPropertyDefinition.findReferenceType();
        if ((localReferenceProperty == null) || (!localReferenceProperty.isBackReference())) {
          if ((localAnnotatedMember instanceof AnnotatedMethod)) {
            localArrayList.add(_constructWriter(paramSerializerProvider, localBeanPropertyDefinition, paramBeanDescription, localPropertyBuilder, bool, (AnnotatedMethod)localAnnotatedMember));
          } else {
            localArrayList.add(_constructWriter(paramSerializerProvider, localBeanPropertyDefinition, paramBeanDescription, localPropertyBuilder, bool, (AnnotatedField)localAnnotatedMember));
          }
        }
      }
    }
    return localArrayList;
  }
  
  public JsonSerializer<Object> findBeanSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if ((!isPotentialBeanType(paramJavaType.getRawClass())) && (!paramJavaType.isEnumType())) {
      return null;
    }
    return constructBeanSerializer(paramSerializerProvider, paramBeanDescription);
  }
  
  @Deprecated
  public final JsonSerializer<Object> findBeanSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return findBeanSerializer(paramSerializerProvider, paramJavaType, paramBeanDescription);
  }
  
  public TypeSerializer findPropertyContentTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    JavaType localJavaType = paramJavaType.getContentType();
    AnnotationIntrospector localAnnotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    paramJavaType = localAnnotationIntrospector.findPropertyContentTypeResolver(paramSerializationConfig, paramAnnotatedMember, paramJavaType);
    if (paramJavaType == null) {
      return createTypeSerializer(paramSerializationConfig, localJavaType);
    }
    return paramJavaType.buildTypeSerializer(paramSerializationConfig, localJavaType, paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, paramSerializationConfig, localAnnotationIntrospector, localJavaType));
  }
  
  public TypeSerializer findPropertyTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder localTypeResolverBuilder = localAnnotationIntrospector.findPropertyTypeResolver(paramSerializationConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {
      return createTypeSerializer(paramSerializationConfig, paramJavaType);
    }
    return localTypeResolverBuilder.buildTypeSerializer(paramSerializationConfig, paramJavaType, paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, paramSerializationConfig, localAnnotationIntrospector, paramJavaType));
  }
  
  @Deprecated
  public final TypeSerializer findPropertyTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return findPropertyTypeSerializer(paramJavaType, paramSerializationConfig, paramAnnotatedMember);
  }
  
  protected boolean isPotentialBeanType(Class<?> paramClass)
  {
    return (ClassUtil.canBeABeanType(paramClass) == null) && (!ClassUtil.isProxyType(paramClass));
  }
  
  protected void processViews(SerializationConfig paramSerializationConfig, BeanSerializerBuilder paramBeanSerializerBuilder)
  {
    List localList = paramBeanSerializerBuilder.getProperties();
    boolean bool = paramSerializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
    int m = localList.size();
    paramSerializationConfig = new BeanPropertyWriter[m];
    int k = 0;
    int i = 0;
    if (k < m)
    {
      BeanPropertyWriter localBeanPropertyWriter = (BeanPropertyWriter)localList.get(k);
      Class[] arrayOfClass = localBeanPropertyWriter.getViews();
      int j;
      if (arrayOfClass == null)
      {
        j = i;
        if (!bool) {
          break label108;
        }
        paramSerializationConfig[k] = localBeanPropertyWriter;
      }
      for (;;)
      {
        k += 1;
        break;
        j = i + 1;
        paramSerializationConfig[k] = constructFilteredBeanWriter(localBeanPropertyWriter, arrayOfClass);
        label108:
        i = j;
      }
    }
    if ((bool) && (i == 0)) {
      return;
    }
    paramBeanSerializerBuilder.setFilteredProperties(paramSerializationConfig);
  }
  
  protected void removeIgnorableTypes(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, List<BeanPropertyDefinition> paramList)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      paramBeanDescription = ((BeanPropertyDefinition)localIterator.next()).getAccessor();
      if (paramBeanDescription == null)
      {
        localIterator.remove();
      }
      else
      {
        Class localClass = paramBeanDescription.getRawType();
        paramList = (Boolean)localHashMap.get(localClass);
        paramBeanDescription = paramList;
        if (paramList == null)
        {
          paramList = localAnnotationIntrospector.isIgnorableType(paramSerializationConfig.introspectClassAnnotations(localClass).getClassInfo());
          paramBeanDescription = paramList;
          if (paramList == null) {
            paramBeanDescription = Boolean.FALSE;
          }
          localHashMap.put(localClass, paramBeanDescription);
        }
        if (paramBeanDescription.booleanValue()) {
          localIterator.remove();
        }
      }
    }
  }
  
  protected void removeSetterlessGetters(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, List<BeanPropertyDefinition> paramList)
  {
    paramSerializationConfig = paramList.iterator();
    while (paramSerializationConfig.hasNext())
    {
      paramBeanDescription = (BeanPropertyDefinition)paramSerializationConfig.next();
      if ((!paramBeanDescription.couldDeserialize()) && (!paramBeanDescription.isExplicitlyIncluded())) {
        paramSerializationConfig.remove();
      }
    }
  }
  
  public SerializerFactory withConfig(SerializerFactoryConfig paramSerializerFactoryConfig)
  {
    if (_factoryConfig == paramSerializerFactoryConfig) {
      return this;
    }
    if (getClass() != BeanSerializerFactory.class) {
      throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with " + "additional serializer definitions");
    }
    return new BeanSerializerFactory(paramSerializerFactoryConfig);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.BeanSerializerFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */