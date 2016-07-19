package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JavaTypeDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class BasicDeserializerFactory
  extends DeserializerFactory
  implements Serializable
{
  private static final Class<?> CLASS_CHAR_BUFFER;
  private static final Class<?> CLASS_ITERABLE;
  private static final Class<?> CLASS_OBJECT = Object.class;
  private static final Class<?> CLASS_STRING = String.class;
  protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME;
  static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
  static final HashMap<String, Class<? extends Map>> _mapFallbacks;
  protected final DeserializerFactoryConfig _factoryConfig;
  
  static
  {
    CLASS_CHAR_BUFFER = CharSequence.class;
    CLASS_ITERABLE = Iterable.class;
    UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    _mapFallbacks = new HashMap();
    _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
    _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
    _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
    _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
    try
    {
      _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
      _collectionFallbacks = new HashMap();
      _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
      _collectionFallbacks.put(List.class.getName(), ArrayList.class);
      _collectionFallbacks.put(Set.class.getName(), HashSet.class);
      _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
      _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
      _collectionFallbacks.put("java.util.Deque", LinkedList.class);
      _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        System.err.println("Problems with (optional) types: " + localThrowable);
      }
    }
  }
  
  protected BasicDeserializerFactory(DeserializerFactoryConfig paramDeserializerFactoryConfig)
  {
    _factoryConfig = paramDeserializerFactoryConfig;
  }
  
  private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = localDeserializationConfig.introspect(paramJavaType);
    Object localObject2 = findDeserializerFromAnnotation(paramDeserializationContext, ((BeanDescription)localObject1).getClassInfo());
    if (localObject2 != null) {
      return StdKeyDeserializers.constructDelegatingKeyDeserializer(localDeserializationConfig, paramJavaType, (JsonDeserializer)localObject2);
    }
    paramDeserializationContext = paramJavaType.getRawClass();
    if (_findCustomEnumDeserializer(paramDeserializationContext, localDeserializationConfig, (BeanDescription)localObject1) != null) {
      return StdKeyDeserializers.constructDelegatingKeyDeserializer(localDeserializationConfig, paramJavaType, (JsonDeserializer)localObject2);
    }
    paramJavaType = constructEnumResolver(paramDeserializationContext, localDeserializationConfig, ((BeanDescription)localObject1).findJsonValueMethod());
    localObject1 = ((BeanDescription)localObject1).getFactoryMethods().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (AnnotatedMethod)((Iterator)localObject1).next();
      if (localDeserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation((Annotated)localObject2))
      {
        if ((((AnnotatedMethod)localObject2).getParameterCount() == 1) && (((AnnotatedMethod)localObject2).getRawReturnType().isAssignableFrom(paramDeserializationContext)))
        {
          if (((AnnotatedMethod)localObject2).getGenericParameterType(0) != String.class) {
            throw new IllegalArgumentException("Parameter #0 type for factory method (" + localObject2 + ") not suitable, must be java.lang.String");
          }
          if (localDeserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(((AnnotatedMethod)localObject2).getMember());
          }
          return StdKeyDeserializers.constructEnumKeyDeserializer(paramJavaType, (AnnotatedMethod)localObject2);
        }
        throw new IllegalArgumentException("Unsuitable method (" + localObject2 + ") decorated with @JsonCreator (for Enum type " + paramDeserializationContext.getName() + ")");
      }
    }
    return StdKeyDeserializers.constructEnumKeyDeserializer(paramJavaType);
  }
  
  private ValueInstantiator _findStdValueInstantiator(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (paramBeanDescription.getBeanClass() == JsonLocation.class) {
      return JsonLocationInstantiator.instance;
    }
    return null;
  }
  
  private JavaType _mapAbstractType2(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    if (_factoryConfig.hasAbstractTypeResolvers())
    {
      Iterator localIterator = _factoryConfig.abstractTypeResolvers().iterator();
      while (localIterator.hasNext())
      {
        JavaType localJavaType = ((AbstractTypeResolver)localIterator.next()).findTypeMapping(paramDeserializationConfig, paramJavaType);
        if ((localJavaType != null) && (localJavaType.getRawClass() != localClass)) {
          return localJavaType;
        }
      }
    }
    return null;
  }
  
  protected void _addDeserializerConstructors(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector)
    throws JsonMappingException
  {
    Object localObject1 = paramBeanDescription.findDefaultConstructor();
    if ((localObject1 != null) && ((!paramCreatorCollector.hasDefaultCreator()) || (paramAnnotationIntrospector.hasCreatorAnnotation((Annotated)localObject1)))) {
      paramCreatorCollector.setDefaultCreator((AnnotatedWithParams)localObject1);
    }
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject5 = paramBeanDescription.findProperties().iterator();
    Object localObject6;
    Object localObject7;
    Object localObject2;
    while (((Iterator)localObject5).hasNext())
    {
      localObject6 = (BeanPropertyDefinition)((Iterator)localObject5).next();
      if (((BeanPropertyDefinition)localObject6).getConstructorParameter() != null)
      {
        localObject7 = ((BeanPropertyDefinition)localObject6).getConstructorParameter();
        localObject1 = ((AnnotatedParameter)localObject7).getOwner();
        if ((localObject1 instanceof AnnotatedConstructor))
        {
          if (localObject4 != null) {
            break label639;
          }
          localObject2 = (AnnotatedConstructor)localObject1;
        }
      }
    }
    for (localObject1 = new PropertyName[((AnnotatedConstructor)localObject2).getParameterCount()];; localObject1 = localObject3)
    {
      localObject1[localObject7.getIndex()] = ((BeanPropertyDefinition)localObject6).getFullName();
      localObject4 = localObject2;
      localObject3 = localObject1;
      break;
      localObject7 = paramBeanDescription.getConstructors().iterator();
      while (((Iterator)localObject7).hasNext())
      {
        AnnotatedConstructor localAnnotatedConstructor = (AnnotatedConstructor)((Iterator)localObject7).next();
        int i1 = localAnnotatedConstructor.getParameterCount();
        boolean bool1;
        label211:
        boolean bool2;
        if ((paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedConstructor)) || (localAnnotatedConstructor == localObject4))
        {
          bool1 = true;
          bool2 = paramVisibilityChecker.isCreatorVisible(localAnnotatedConstructor);
          if (i1 != 1) {
            break label275;
          }
          if (localAnnotatedConstructor != localObject4) {
            break label269;
          }
        }
        label269:
        for (localObject1 = localObject3[0];; localObject1 = null)
        {
          _handleSingleArgumentConstructor(paramDeserializationContext, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localAnnotatedConstructor, bool1, bool2, (PropertyName)localObject1);
          break;
          bool1 = false;
          break label211;
        }
        label275:
        if ((bool1) || (bool2))
        {
          localObject1 = null;
          int m = 0;
          int k = 0;
          CreatorProperty[] arrayOfCreatorProperty = new CreatorProperty[i1];
          int j = 0;
          if (j < i1)
          {
            localObject6 = localAnnotatedConstructor.getParameter(j);
            localObject2 = null;
            if (localAnnotatedConstructor == localObject4) {
              localObject2 = localObject3[j];
            }
            localObject5 = localObject2;
            label354:
            int i;
            int n;
            if (localObject2 == null)
            {
              if (localObject6 == null)
              {
                localObject2 = null;
                localObject5 = localObject2;
              }
            }
            else
            {
              localObject2 = paramAnnotationIntrospector.findInjectableValueId((AnnotatedMember)localObject6);
              if ((localObject5 == null) || (!((PropertyName)localObject5).hasSimpleName())) {
                break label446;
              }
              i = m + 1;
              arrayOfCreatorProperty[j] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, (PropertyName)localObject5, j, (AnnotatedParameter)localObject6, localObject2);
              localObject2 = localObject1;
              n = k;
            }
            for (;;)
            {
              j += 1;
              localObject1 = localObject2;
              k = n;
              m = i;
              break;
              localObject2 = paramAnnotationIntrospector.findNameForDeserialization((Annotated)localObject6);
              break label354;
              label446:
              if (localObject2 != null)
              {
                n = k + 1;
                arrayOfCreatorProperty[j] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, (PropertyName)localObject5, j, (AnnotatedParameter)localObject6, localObject2);
                i = m;
                localObject2 = localObject1;
              }
              else if (paramAnnotationIntrospector.findUnwrappingNameTransformer((AnnotatedMember)localObject6) != null)
              {
                arrayOfCreatorProperty[j] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, UNWRAPPED_CREATOR_PARAM_NAME, j, (AnnotatedParameter)localObject6, null);
                i = m + 1;
                n = k;
                localObject2 = localObject1;
              }
              else
              {
                n = k;
                i = m;
                localObject2 = localObject6;
                if (localObject1 != null)
                {
                  n = k;
                  i = m;
                  localObject2 = localObject1;
                }
              }
            }
          }
          if ((bool1) || (m > 0) || (k > 0)) {
            if (m + k == i1) {
              paramCreatorCollector.addPropertyCreator(localAnnotatedConstructor, arrayOfCreatorProperty);
            } else if ((m == 0) && (k + 1 == i1)) {
              paramCreatorCollector.addDelegatingCreator(localAnnotatedConstructor, arrayOfCreatorProperty);
            } else {
              paramCreatorCollector.addIncompeteParameter((AnnotatedParameter)localObject1);
            }
          }
        }
      }
      return;
      label639:
      localObject2 = localObject4;
    }
  }
  
  protected void _addDeserializerFactoryMethods(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Iterator localIterator = paramBeanDescription.getFactoryMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      boolean bool = paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedMethod);
      int i1 = localAnnotatedMethod.getParameterCount();
      if (i1 == 0)
      {
        if (bool) {
          paramCreatorCollector.setDefaultCreator(localAnnotatedMethod);
        }
      }
      else
      {
        Object localObject2;
        Object localObject1;
        if (i1 == 1)
        {
          localObject2 = localAnnotatedMethod.getParameter(0);
          if (localObject2 == null)
          {
            localObject1 = null;
            label97:
            if (localObject1 != null) {
              break label160;
            }
          }
          label160:
          for (localObject1 = null;; localObject1 = ((PropertyName)localObject1).getSimpleName())
          {
            if ((paramAnnotationIntrospector.findInjectableValueId((AnnotatedMember)localObject2) != null) || ((localObject1 != null) && (((String)localObject1).length() != 0))) {
              break label180;
            }
            _handleSingleArgumentFactory(localDeserializationConfig, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localAnnotatedMethod, bool);
            break;
            localObject1 = paramAnnotationIntrospector.findNameForDeserialization((Annotated)localObject2);
            break label97;
          }
        }
        if (paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedMethod))
        {
          label180:
          localObject1 = null;
          CreatorProperty[] arrayOfCreatorProperty = new CreatorProperty[i1];
          int m = 0;
          int k = 0;
          int j = 0;
          if (j < i1)
          {
            AnnotatedParameter localAnnotatedParameter = localAnnotatedMethod.getParameter(j);
            label223:
            Object localObject3;
            int i;
            int n;
            if (localAnnotatedParameter == null)
            {
              localObject2 = null;
              localObject3 = paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter);
              if ((localObject2 == null) || (!((PropertyName)localObject2).hasSimpleName())) {
                break label311;
              }
              i = m + 1;
              arrayOfCreatorProperty[j] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, (PropertyName)localObject2, j, localAnnotatedParameter, localObject3);
              localObject2 = localObject1;
              n = k;
            }
            for (;;)
            {
              j += 1;
              localObject1 = localObject2;
              k = n;
              m = i;
              break;
              localObject2 = paramAnnotationIntrospector.findNameForDeserialization(localAnnotatedParameter);
              break label223;
              label311:
              if (localObject3 != null)
              {
                n = k + 1;
                arrayOfCreatorProperty[j] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, (PropertyName)localObject2, j, localAnnotatedParameter, localObject3);
                i = m;
                localObject2 = localObject1;
              }
              else if (paramAnnotationIntrospector.findUnwrappingNameTransformer(localAnnotatedParameter) != null)
              {
                arrayOfCreatorProperty[j] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, UNWRAPPED_CREATOR_PARAM_NAME, j, localAnnotatedParameter, null);
                i = m + 1;
                n = k;
                localObject2 = localObject1;
              }
              else
              {
                n = k;
                i = m;
                localObject2 = localAnnotatedParameter;
                if (localObject1 != null)
                {
                  n = k;
                  i = m;
                  localObject2 = localObject1;
                }
              }
            }
          }
          if ((bool) || (m > 0) || (k > 0)) {
            if (m + k == i1) {
              paramCreatorCollector.addPropertyCreator(localAnnotatedMethod, arrayOfCreatorProperty);
            } else if ((m == 0) && (k + 1 == i1)) {
              paramCreatorCollector.addDelegatingCreator(localAnnotatedMethod, arrayOfCreatorProperty);
            } else {
              throw new IllegalArgumentException("Argument #" + ((AnnotatedParameter)localObject1).getIndex() + " of factory method " + localAnnotatedMethod + " has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
            }
          }
        }
      }
    }
  }
  
  protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    CreatorCollector localCreatorCollector = new CreatorCollector(paramBeanDescription, paramDeserializationContext.canOverrideAccessModifiers());
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    VisibilityChecker localVisibilityChecker = localDeserializationConfig.getDefaultVisibilityChecker();
    localVisibilityChecker = localAnnotationIntrospector.findAutoDetectVisibility(paramBeanDescription.getClassInfo(), localVisibilityChecker);
    _addDeserializerFactoryMethods(paramDeserializationContext, paramBeanDescription, localVisibilityChecker, localAnnotationIntrospector, localCreatorCollector);
    if (paramBeanDescription.getType().isConcrete()) {
      _addDeserializerConstructors(paramDeserializationContext, paramBeanDescription, localVisibilityChecker, localAnnotationIntrospector, localCreatorCollector);
    }
    return localCreatorCollector.constructValueInstantiator(localDeserializationConfig);
  }
  
  protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findArrayDeserializer(paramArrayType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findCollectionDeserializer(paramCollectionType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findCollectionLikeDeserializer(paramCollectionLikeType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findEnumDeserializer(paramClass, paramDeserializationConfig, paramBeanDescription);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findMapDeserializer(paramMapType, paramDeserializationConfig, paramBeanDescription, paramKeyDeserializer, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findMapLikeDeserializer(paramMapLikeType, paramDeserializationConfig, paramBeanDescription, paramKeyDeserializer, paramTypeDeserializer, paramJsonDeserializer);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = _factoryConfig.deserializers().iterator();
    while (localIterator.hasNext())
    {
      JsonDeserializer localJsonDeserializer = ((Deserializers)localIterator.next()).findTreeNodeDeserializer(paramClass, paramDeserializationConfig, paramBeanDescription);
      if (localJsonDeserializer != null) {
        return localJsonDeserializer;
      }
    }
    return null;
  }
  
  protected AnnotatedMethod _findJsonValueFor(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    if (paramJavaType == null) {
      return null;
    }
    return paramDeserializationConfig.introspect(paramJavaType).findJsonValueMethod();
  }
  
  protected boolean _handleSingleArgumentConstructor(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedConstructor paramAnnotatedConstructor, boolean paramBoolean1, boolean paramBoolean2, PropertyName paramPropertyName)
    throws JsonMappingException
  {
    AnnotatedParameter localAnnotatedParameter = paramAnnotatedConstructor.getParameter(0);
    if (paramPropertyName == null) {
      if (localAnnotatedParameter == null) {
        paramVisibilityChecker = null;
      }
    }
    for (;;)
    {
      paramAnnotationIntrospector = paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter);
      if ((paramAnnotationIntrospector != null) || ((paramVisibilityChecker != null) && (paramVisibilityChecker.hasSimpleName())))
      {
        paramCreatorCollector.addPropertyCreator(paramAnnotatedConstructor, new CreatorProperty[] { constructCreatorProperty(paramDeserializationContext, paramBeanDescription, paramVisibilityChecker, 0, localAnnotatedParameter, paramAnnotationIntrospector) });
        return true;
        paramVisibilityChecker = paramAnnotationIntrospector.findNameForDeserialization(localAnnotatedParameter);
      }
      else
      {
        paramDeserializationContext = paramAnnotatedConstructor.getRawParameterType(0);
        if (paramDeserializationContext == String.class)
        {
          if ((paramBoolean1) || (paramBoolean2)) {
            paramCreatorCollector.addStringCreator(paramAnnotatedConstructor);
          }
          return true;
        }
        if ((paramDeserializationContext == Integer.TYPE) || (paramDeserializationContext == Integer.class))
        {
          if ((paramBoolean1) || (paramBoolean2)) {
            paramCreatorCollector.addIntCreator(paramAnnotatedConstructor);
          }
          return true;
        }
        if ((paramDeserializationContext == Long.TYPE) || (paramDeserializationContext == Long.class))
        {
          if ((paramBoolean1) || (paramBoolean2)) {
            paramCreatorCollector.addLongCreator(paramAnnotatedConstructor);
          }
          return true;
        }
        if ((paramDeserializationContext == Double.TYPE) || (paramDeserializationContext == Double.class))
        {
          if ((paramBoolean1) || (paramBoolean2)) {
            paramCreatorCollector.addDoubleCreator(paramAnnotatedConstructor);
          }
          return true;
        }
        if (paramBoolean1)
        {
          paramCreatorCollector.addDelegatingCreator(paramAnnotatedConstructor, null);
          return true;
        }
        return false;
        paramVisibilityChecker = paramPropertyName;
      }
    }
  }
  
  protected boolean _handleSingleArgumentFactory(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
    throws JsonMappingException
  {
    paramDeserializationConfig = paramAnnotatedMethod.getRawParameterType(0);
    if (paramDeserializationConfig == String.class) {
      if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
        paramCreatorCollector.addStringCreator(paramAnnotatedMethod);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return true;
            if ((paramDeserializationConfig != Integer.TYPE) && (paramDeserializationConfig != Integer.class)) {
              break;
            }
          } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
          paramCreatorCollector.addIntCreator(paramAnnotatedMethod);
          return true;
          if ((paramDeserializationConfig != Long.TYPE) && (paramDeserializationConfig != Long.class)) {
            break;
          }
        } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
        paramCreatorCollector.addLongCreator(paramAnnotatedMethod);
        return true;
        if ((paramDeserializationConfig != Double.TYPE) && (paramDeserializationConfig != Double.class)) {
          break;
        }
      } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
      paramCreatorCollector.addDoubleCreator(paramAnnotatedMethod);
      return true;
      if ((paramDeserializationConfig != Boolean.TYPE) && (paramDeserializationConfig != Boolean.class)) {
        break;
      }
    } while ((!paramBoolean) && (!paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod)));
    paramCreatorCollector.addBooleanCreator(paramAnnotatedMethod);
    return true;
    if (paramAnnotationIntrospector.hasCreatorAnnotation(paramAnnotatedMethod))
    {
      paramCreatorCollector.addDelegatingCreator(paramAnnotatedMethod, null);
      return true;
    }
    return false;
  }
  
  protected CollectionType _mapAbstractCollectionType(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig)
  {
    Class localClass = paramJavaType.getRawClass();
    localClass = (Class)_collectionFallbacks.get(localClass.getName());
    if (localClass == null) {
      return null;
    }
    return (CollectionType)paramDeserializationConfig.constructSpecializedType(paramJavaType, localClass);
  }
  
  public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof ValueInstantiator)) {
      return (ValueInstantiator)paramObject;
    }
    if (!(paramObject instanceof Class)) {
      throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + paramObject.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
    }
    paramObject = (Class)paramObject;
    if (paramObject == NoClass.class) {
      return null;
    }
    if (!ValueInstantiator.class.isAssignableFrom((Class)paramObject)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + ((Class)paramObject).getName() + "; expected Class<ValueInstantiator>");
    }
    HandlerInstantiator localHandlerInstantiator = paramDeserializationConfig.getHandlerInstantiator();
    if (localHandlerInstantiator != null)
    {
      paramAnnotated = localHandlerInstantiator.valueInstantiatorInstance(paramDeserializationConfig, paramAnnotated, (Class)paramObject);
      if (paramAnnotated != null) {
        return paramAnnotated;
      }
    }
    return (ValueInstantiator)ClassUtil.createInstance((Class)paramObject, paramDeserializationConfig.canOverrideAccessModifiers());
  }
  
  protected CreatorProperty constructCreatorProperty(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, PropertyName paramPropertyName, int paramInt, AnnotatedParameter paramAnnotatedParameter, Object paramObject)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject2 = paramDeserializationContext.getAnnotationIntrospector();
    Object localObject1;
    boolean bool;
    label36:
    label44:
    PropertyMetadata localPropertyMetadata;
    JavaType localJavaType1;
    if (localObject2 == null)
    {
      localObject1 = null;
      if ((localObject1 == null) || (!((Boolean)localObject1).booleanValue())) {
        break label226;
      }
      bool = true;
      if (localObject2 != null) {
        break label232;
      }
      localObject1 = null;
      localPropertyMetadata = PropertyMetadata.construct(bool, (String)localObject1);
      JavaType localJavaType2 = localDeserializationConfig.getTypeFactory().constructType(paramAnnotatedParameter.getParameterType(), paramBeanDescription.bindingsForBeanType());
      localObject1 = new BeanProperty.Std(paramPropertyName, localJavaType2, ((AnnotationIntrospector)localObject2).findWrapperName(paramAnnotatedParameter), paramBeanDescription.getClassAnnotations(), paramAnnotatedParameter, localPropertyMetadata);
      localJavaType1 = resolveType(paramDeserializationContext, paramBeanDescription, localJavaType2, paramAnnotatedParameter);
      if (localJavaType1 == localJavaType2) {
        break label247;
      }
      localObject1 = ((BeanProperty.Std)localObject1).withType(localJavaType1);
    }
    label226:
    label232:
    label247:
    for (;;)
    {
      localObject2 = findDeserializerFromAnnotation(paramDeserializationContext, paramAnnotatedParameter);
      localJavaType1 = modifyTypeByAnnotation(paramDeserializationContext, paramAnnotatedParameter, localJavaType1);
      paramDeserializationContext = (TypeDeserializer)localJavaType1.getTypeHandler();
      if (paramDeserializationContext == null) {
        paramDeserializationContext = findTypeDeserializer(localDeserializationConfig, localJavaType1);
      }
      for (;;)
      {
        paramBeanDescription = new CreatorProperty(paramPropertyName, localJavaType1, ((BeanProperty.Std)localObject1).getWrapperName(), paramDeserializationContext, paramBeanDescription.getClassAnnotations(), paramAnnotatedParameter, paramInt, paramObject, localPropertyMetadata);
        paramDeserializationContext = paramBeanDescription;
        if (localObject2 != null) {
          paramDeserializationContext = paramBeanDescription.withValueDeserializer((JsonDeserializer)localObject2);
        }
        return paramDeserializationContext;
        localObject1 = ((AnnotationIntrospector)localObject2).hasRequiredMarker(paramAnnotatedParameter);
        break;
        bool = false;
        break label36;
        localObject1 = ((AnnotationIntrospector)localObject2).findPropertyDescription(paramAnnotatedParameter);
        break label44;
      }
    }
  }
  
  protected EnumResolver<?> constructEnumResolver(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, AnnotatedMethod paramAnnotatedMethod)
  {
    if (paramAnnotatedMethod != null)
    {
      paramAnnotatedMethod = paramAnnotatedMethod.getAnnotated();
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(paramAnnotatedMethod);
      }
      return EnumResolver.constructUnsafeUsingMethod(paramClass, paramAnnotatedMethod);
    }
    if (paramDeserializationConfig.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {
      return EnumResolver.constructUnsafeUsingToString(paramClass);
    }
    return EnumResolver.constructUnsafe(paramClass, paramDeserializationConfig.getAnnotationIntrospector());
  }
  
  public JsonDeserializer<?> createArrayDeserializer(DeserializationContext paramDeserializationContext, ArrayType paramArrayType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JavaType localJavaType = paramArrayType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType.getValueHandler();
    Object localObject1 = (TypeDeserializer)localJavaType.getTypeHandler();
    if (localObject1 == null) {
      localObject1 = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    for (;;)
    {
      Object localObject2 = _findCustomArrayDeserializer(paramArrayType, localDeserializationConfig, paramBeanDescription, (TypeDeserializer)localObject1, localJsonDeserializer);
      paramDeserializationContext = (DeserializationContext)localObject2;
      if (localObject2 == null)
      {
        if (localJsonDeserializer == null)
        {
          paramDeserializationContext = localJavaType.getRawClass();
          if (localJavaType.isPrimitive())
          {
            localObject1 = PrimitiveArrayDeserializers.forType(paramDeserializationContext);
            return (JsonDeserializer<?>)localObject1;
          }
          if (paramDeserializationContext == String.class) {
            return StringArrayDeserializer.instance;
          }
        }
        paramDeserializationContext = new ObjectArrayDeserializer(paramArrayType, localJsonDeserializer, (TypeDeserializer)localObject1);
      }
      if (_factoryConfig.hasDeserializerModifiers())
      {
        localObject2 = _factoryConfig.deserializerModifiers().iterator();
        for (;;)
        {
          localObject1 = paramDeserializationContext;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject2).next()).modifyArrayDeserializer(localDeserializationConfig, paramArrayType, paramBeanDescription, paramDeserializationContext);
        }
      }
      return paramDeserializationContext;
    }
  }
  
  public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext paramDeserializationContext, CollectionType paramCollectionType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType = paramCollectionType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType.getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType.getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    for (;;)
    {
      Object localObject2 = _findCustomCollectionDeserializer(paramCollectionType, localDeserializationConfig, paramBeanDescription, localTypeDeserializer, localJsonDeserializer);
      Object localObject1 = localObject2;
      Object localObject3;
      if (localObject2 == null)
      {
        localObject3 = paramCollectionType.getRawClass();
        localObject1 = localObject2;
        if (localJsonDeserializer == null)
        {
          localObject1 = localObject2;
          if (EnumSet.class.isAssignableFrom((Class)localObject3)) {
            localObject1 = new EnumSetDeserializer(localJavaType, null);
          }
        }
      }
      BeanDescription localBeanDescription;
      if (localObject1 == null) {
        if ((paramCollectionType.isInterface()) || (paramCollectionType.isAbstract()))
        {
          localObject3 = _mapAbstractCollectionType(paramCollectionType, localDeserializationConfig);
          if (localObject3 == null)
          {
            if (paramCollectionType.getTypeHandler() == null) {
              throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + paramCollectionType);
            }
            localObject2 = AbstractDeserializer.constructForNonPOJO(paramBeanDescription);
          }
          for (localObject1 = paramCollectionType;; localObject1 = localObject3)
          {
            paramCollectionType = (CollectionType)localObject2;
            localObject3 = localObject1;
            localBeanDescription = paramBeanDescription;
            if (localObject2 != null) {
              break label294;
            }
            paramDeserializationContext = findValueInstantiator(paramDeserializationContext, paramBeanDescription);
            if ((paramDeserializationContext.canCreateUsingDefault()) || (((CollectionType)localObject1).getRawClass() != ArrayBlockingQueue.class)) {
              break;
            }
            return new ArrayBlockingQueueDeserializer((JavaType)localObject1, localJsonDeserializer, localTypeDeserializer, paramDeserializationContext, null);
            paramBeanDescription = localDeserializationConfig.introspectForCreation((JavaType)localObject3);
            localObject2 = localObject1;
          }
          if (localJavaType.getRawClass() == String.class)
          {
            paramCollectionType = new StringCollectionDeserializer((JavaType)localObject1, localJsonDeserializer, paramDeserializationContext);
            localBeanDescription = paramBeanDescription;
            localObject3 = localObject1;
          }
        }
      }
      for (;;)
      {
        label294:
        if (_factoryConfig.hasDeserializerModifiers())
        {
          paramBeanDescription = _factoryConfig.deserializerModifiers().iterator();
          for (paramDeserializationContext = paramCollectionType;; paramDeserializationContext = ((BeanDeserializerModifier)paramBeanDescription.next()).modifyCollectionDeserializer(localDeserializationConfig, (CollectionType)localObject3, localBeanDescription, paramDeserializationContext))
          {
            paramCollectionType = paramDeserializationContext;
            if (!paramBeanDescription.hasNext()) {
              break;
            }
          }
          paramCollectionType = new CollectionDeserializer((JavaType)localObject1, localJsonDeserializer, localTypeDeserializer, paramDeserializationContext);
          localObject3 = localObject1;
          localBeanDescription = paramBeanDescription;
        }
        else
        {
          return paramCollectionType;
          localObject2 = localObject1;
          localObject1 = paramCollectionType;
          break;
          localObject3 = paramCollectionType;
          paramCollectionType = (CollectionType)localObject1;
          localBeanDescription = paramBeanDescription;
        }
      }
    }
  }
  
  public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext paramDeserializationContext, CollectionLikeType paramCollectionLikeType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType = paramCollectionLikeType.getContentType();
    Object localObject = (JsonDeserializer)localJavaType.getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    paramDeserializationContext = (TypeDeserializer)localJavaType.getTypeHandler();
    if (paramDeserializationContext == null) {
      paramDeserializationContext = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    for (;;)
    {
      paramDeserializationContext = _findCustomCollectionLikeDeserializer(paramCollectionLikeType, localDeserializationConfig, paramBeanDescription, paramDeserializationContext, (JsonDeserializer)localObject);
      localObject = paramDeserializationContext;
      if (paramDeserializationContext != null)
      {
        localObject = paramDeserializationContext;
        if (_factoryConfig.hasDeserializerModifiers())
        {
          localObject = _factoryConfig.deserializerModifiers().iterator();
          while (((Iterator)localObject).hasNext()) {
            paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject).next()).modifyCollectionLikeDeserializer(localDeserializationConfig, paramCollectionLikeType, paramBeanDescription, paramDeserializationContext);
          }
          localObject = paramDeserializationContext;
        }
      }
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> createEnumDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject2 = paramJavaType.getRawClass();
    Object localObject1 = _findCustomEnumDeserializer((Class)localObject2, localDeserializationConfig, paramBeanDescription);
    AnnotatedMethod localAnnotatedMethod;
    if (localObject1 == null)
    {
      Iterator localIterator = paramBeanDescription.getFactoryMethods().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
          if (paramDeserializationContext.getAnnotationIntrospector().hasCreatorAnnotation(localAnnotatedMethod)) {
            if ((localAnnotatedMethod.getParameterCount() == 1) && (localAnnotatedMethod.getRawReturnType().isAssignableFrom((Class)localObject2)))
            {
              localObject1 = EnumDeserializer.deserializerForCreator(localDeserializationConfig, (Class)localObject2, localAnnotatedMethod);
              paramDeserializationContext = (DeserializationContext)localObject1;
              if (localObject1 != null) {
                break;
              }
            }
          }
        }
      }
    }
    for (paramDeserializationContext = new EnumDeserializer(constructEnumResolver((Class)localObject2, localDeserializationConfig, paramBeanDescription.findJsonValueMethod()));; paramDeserializationContext = (DeserializationContext)localObject1)
    {
      if (_factoryConfig.hasDeserializerModifiers())
      {
        localObject2 = _factoryConfig.deserializerModifiers().iterator();
        for (;;)
        {
          localObject1 = paramDeserializationContext;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject2).next()).modifyEnumDeserializer(localDeserializationConfig, paramJavaType, paramBeanDescription, paramDeserializationContext);
        }
        throw new IllegalArgumentException("Unsuitable method (" + localAnnotatedMethod + ") decorated with @JsonCreator (for Enum type " + ((Class)localObject2).getName() + ")");
      }
      localObject1 = paramDeserializationContext;
      return (JsonDeserializer<?>)localObject1;
      break;
    }
  }
  
  public KeyDeserializer createKeyDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = null;
    Object localObject2 = null;
    if (_factoryConfig.hasKeyDeserializers())
    {
      BeanDescription localBeanDescription = localDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass());
      Iterator localIterator = _factoryConfig.keyDeserializers().iterator();
      localObject1 = localObject2;
      while (localIterator.hasNext())
      {
        localObject2 = ((KeyDeserializers)localIterator.next()).findKeyDeserializer(paramJavaType, localDeserializationConfig, localBeanDescription);
        localObject1 = localObject2;
        if (localObject2 != null) {
          localObject1 = localObject2;
        }
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      if (paramJavaType.isEnumType()) {
        paramDeserializationContext = _createEnumKeyDeserializer(paramDeserializationContext, paramJavaType);
      }
    }
    do
    {
      do
      {
        return paramDeserializationContext;
        localObject2 = StdKeyDeserializers.findStringBasedKeyDeserializer(localDeserializationConfig, paramJavaType);
        paramDeserializationContext = (DeserializationContext)localObject2;
      } while (localObject2 == null);
      paramDeserializationContext = (DeserializationContext)localObject2;
    } while (!_factoryConfig.hasDeserializerModifiers());
    paramDeserializationContext = _factoryConfig.deserializerModifiers().iterator();
    while (paramDeserializationContext.hasNext()) {
      localObject2 = ((BeanDeserializerModifier)paramDeserializationContext.next()).modifyKeyDeserializer(localDeserializationConfig, paramJavaType, (KeyDeserializer)localObject2);
    }
    return (KeyDeserializer)localObject2;
  }
  
  public JsonDeserializer<?> createMapDeserializer(DeserializationContext paramDeserializationContext, MapType paramMapType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject3 = paramMapType.getKeyType();
    Object localObject1 = paramMapType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)((JavaType)localObject1).getValueHandler();
    KeyDeserializer localKeyDeserializer = (KeyDeserializer)((JavaType)localObject3).getValueHandler();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)((JavaType)localObject1).getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, (JavaType)localObject1);
    }
    for (;;)
    {
      localObject1 = _findCustomMapDeserializer(paramMapType, localDeserializationConfig, paramBeanDescription, localKeyDeserializer, localTypeDeserializer, localJsonDeserializer);
      Object localObject2 = localObject1;
      Object localObject4;
      if (localObject1 == null)
      {
        localObject4 = paramMapType.getRawClass();
        if (EnumMap.class.isAssignableFrom((Class)localObject4))
        {
          localObject1 = ((JavaType)localObject3).getRawClass();
          if ((localObject1 == null) || (!((Class)localObject1).isEnum())) {
            throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
          }
          localObject1 = new EnumMapDeserializer(paramMapType, null, localJsonDeserializer, localTypeDeserializer);
        }
        localObject2 = localObject1;
        if (localObject1 == null) {
          if ((paramMapType.isInterface()) || (paramMapType.isAbstract()))
          {
            localObject2 = (Class)_mapFallbacks.get(((Class)localObject4).getName());
            if (localObject2 != null)
            {
              localObject2 = (MapType)localDeserializationConfig.constructSpecializedType(paramMapType, (Class)localObject2);
              paramBeanDescription = localDeserializationConfig.introspectForCreation((JavaType)localObject2);
              paramMapType = (MapType)localObject1;
              localObject3 = localObject2;
              localObject4 = paramBeanDescription;
              if (localObject1 == null)
              {
                paramMapType = new MapDeserializer((JavaType)localObject2, findValueInstantiator(paramDeserializationContext, paramBeanDescription), localKeyDeserializer, localJsonDeserializer, localTypeDeserializer);
                paramMapType.setIgnorableProperties(localDeserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBeanDescription.getClassInfo()));
                localObject4 = paramBeanDescription;
                localObject3 = localObject2;
              }
            }
          }
        }
      }
      for (;;)
      {
        paramDeserializationContext = paramMapType;
        if (_factoryConfig.hasDeserializerModifiers())
        {
          paramBeanDescription = _factoryConfig.deserializerModifiers().iterator();
          for (;;)
          {
            paramDeserializationContext = paramMapType;
            if (!paramBeanDescription.hasNext()) {
              break;
            }
            paramMapType = ((BeanDeserializerModifier)paramBeanDescription.next()).modifyMapDeserializer(localDeserializationConfig, (MapType)localObject3, (BeanDescription)localObject4, paramMapType);
          }
          if (paramMapType.getTypeHandler() == null) {
            throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + paramMapType);
          }
          localObject1 = AbstractDeserializer.constructForNonPOJO(paramBeanDescription);
          localObject2 = paramMapType;
          break;
        }
        return paramDeserializationContext;
        localObject2 = paramMapType;
        break;
        localObject3 = paramMapType;
        paramMapType = (MapType)localObject2;
        localObject4 = paramBeanDescription;
      }
    }
  }
  
  public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext paramDeserializationContext, MapLikeType paramMapLikeType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject2 = paramMapLikeType.getKeyType();
    JavaType localJavaType = paramMapLikeType.getContentType();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject1 = (JsonDeserializer)localJavaType.getValueHandler();
    localObject2 = (KeyDeserializer)((JavaType)localObject2).getValueHandler();
    paramDeserializationContext = (TypeDeserializer)localJavaType.getTypeHandler();
    if (paramDeserializationContext == null) {
      paramDeserializationContext = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    for (;;)
    {
      paramDeserializationContext = _findCustomMapLikeDeserializer(paramMapLikeType, localDeserializationConfig, paramBeanDescription, (KeyDeserializer)localObject2, paramDeserializationContext, (JsonDeserializer)localObject1);
      localObject1 = paramDeserializationContext;
      if (paramDeserializationContext != null)
      {
        localObject1 = paramDeserializationContext;
        if (_factoryConfig.hasDeserializerModifiers())
        {
          localObject1 = _factoryConfig.deserializerModifiers().iterator();
          while (((Iterator)localObject1).hasNext()) {
            paramDeserializationContext = ((BeanDeserializerModifier)((Iterator)localObject1).next()).modifyMapLikeDeserializer(localDeserializationConfig, paramMapLikeType, paramBeanDescription, paramDeserializationContext);
          }
          localObject1 = paramDeserializationContext;
        }
      }
      return (JsonDeserializer<?>)localObject1;
    }
  }
  
  public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    paramJavaType = paramJavaType.getRawClass();
    paramDeserializationConfig = _findCustomTreeNodeDeserializer(paramJavaType, paramDeserializationConfig, paramBeanDescription);
    if (paramDeserializationConfig != null) {
      return paramDeserializationConfig;
    }
    return JsonNodeDeserializer.getDeserializer(paramJavaType);
  }
  
  public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject = paramJavaType.getRawClass();
    String str = ((Class)localObject).getName();
    if ((((Class)localObject).isPrimitive()) || (str.startsWith("java.")))
    {
      if (localObject == CLASS_OBJECT) {
        paramDeserializationContext = new UntypedObjectDeserializer();
      }
      do
      {
        do
        {
          return paramDeserializationContext;
          if ((localObject == CLASS_STRING) || (localObject == CLASS_CHAR_BUFFER)) {
            return StringDeserializer.instance;
          }
          if (localObject == CLASS_ITERABLE)
          {
            localObject = paramDeserializationContext.getTypeFactory();
            if (paramJavaType.containedTypeCount() > 0) {}
            for (paramJavaType = paramJavaType.containedType(0);; paramJavaType = TypeFactory.unknownType()) {
              return createCollectionDeserializer(paramDeserializationContext, ((TypeFactory)localObject).constructCollectionType(Collection.class, paramJavaType), paramBeanDescription);
            }
          }
          paramJavaType = NumberDeserializers.find((Class)localObject, str);
          paramDeserializationContext = paramJavaType;
        } while (paramJavaType != null);
        paramJavaType = DateDeserializers.find((Class)localObject, str);
        paramDeserializationContext = paramJavaType;
      } while (paramJavaType != null);
      return JdkDeserializers.find((Class)localObject, str);
    }
    if (str.startsWith("com.fasterxml."))
    {
      if (localObject == TokenBuffer.class) {
        return TokenBufferDeserializer.instance;
      }
      if (JavaType.class.isAssignableFrom((Class)localObject)) {
        return JavaTypeDeserializer.instance;
      }
    }
    return null;
  }
  
  protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findDeserializer(paramAnnotated);
    if (localObject == null) {
      return null;
    }
    return paramDeserializationContext.deserializerInstance(paramAnnotated, localObject);
  }
  
  public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder localTypeResolverBuilder = localAnnotationIntrospector.findPropertyContentTypeResolver(paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    paramJavaType = paramJavaType.getContentType();
    if (localTypeResolverBuilder == null) {
      return findTypeDeserializer(paramDeserializationConfig, paramJavaType);
    }
    return localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, paramDeserializationConfig, localAnnotationIntrospector, paramJavaType));
  }
  
  public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder localTypeResolverBuilder = localAnnotationIntrospector.findPropertyTypeResolver(paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {
      return findTypeDeserializer(paramDeserializationConfig, paramJavaType);
    }
    return localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, paramDeserializationConfig, localAnnotationIntrospector, paramJavaType));
  }
  
  public TypeDeserializer findTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    Collection localCollection = null;
    Object localObject2 = paramDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass()).getClassInfo();
    Object localObject3 = paramDeserializationConfig.getAnnotationIntrospector();
    Object localObject1 = ((AnnotationIntrospector)localObject3).findTypeResolver(paramDeserializationConfig, (AnnotatedClass)localObject2, paramJavaType);
    if (localObject1 == null)
    {
      localObject2 = paramDeserializationConfig.getDefaultTyper(paramJavaType);
      localObject1 = localObject2;
      if (localObject2 == null) {
        return null;
      }
    }
    else
    {
      localCollection = paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypes((AnnotatedClass)localObject2, paramDeserializationConfig, (AnnotationIntrospector)localObject3);
    }
    localObject2 = localObject1;
    if (((TypeResolverBuilder)localObject1).getDefaultImpl() == null)
    {
      localObject2 = localObject1;
      if (paramJavaType.isAbstract())
      {
        localObject3 = mapAbstractType(paramDeserializationConfig, paramJavaType);
        localObject2 = localObject1;
        if (localObject3 != null)
        {
          localObject2 = localObject1;
          if (((JavaType)localObject3).getRawClass() != paramJavaType.getRawClass()) {
            localObject2 = ((TypeResolverBuilder)localObject1).defaultImpl(((JavaType)localObject3).getRawClass());
          }
        }
      }
    }
    return ((TypeResolverBuilder)localObject2).buildTypeDeserializer(paramDeserializationConfig, paramJavaType, localCollection);
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject2 = null;
    Object localObject1 = paramBeanDescription.getClassInfo();
    Object localObject3 = paramDeserializationContext.getAnnotationIntrospector().findValueInstantiator((AnnotatedClass)localObject1);
    if (localObject3 != null) {
      localObject2 = _valueInstantiatorInstance(localDeserializationConfig, (Annotated)localObject1, localObject3);
    }
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = _findStdValueInstantiator(localDeserializationConfig, paramBeanDescription);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = _constructDefaultValueInstantiator(paramDeserializationContext, paramBeanDescription);
      }
    }
    if (_factoryConfig.hasValueInstantiators())
    {
      localObject2 = _factoryConfig.valueInstantiators().iterator();
      paramDeserializationContext = (DeserializationContext)localObject1;
      do
      {
        localObject1 = paramDeserializationContext;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject3 = (ValueInstantiators)((Iterator)localObject2).next();
        localObject1 = ((ValueInstantiators)localObject3).findValueInstantiator(localDeserializationConfig, paramBeanDescription, paramDeserializationContext);
        paramDeserializationContext = (DeserializationContext)localObject1;
      } while (localObject1 != null);
      throw new JsonMappingException("Broken registered ValueInstantiators (of type " + localObject3.getClass().getName() + "): returned null ValueInstantiator");
    }
    if (((ValueInstantiator)localObject1).getIncompleteParameter() != null)
    {
      paramDeserializationContext = ((ValueInstantiator)localObject1).getIncompleteParameter();
      paramBeanDescription = paramDeserializationContext.getOwner();
      throw new IllegalArgumentException("Argument #" + paramDeserializationContext.getIndex() + " of constructor " + paramBeanDescription + " has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
    }
    return (ValueInstantiator)localObject1;
  }
  
  public DeserializerFactoryConfig getFactoryConfig()
  {
    return _factoryConfig;
  }
  
  public JavaType mapAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    for (;;)
    {
      JavaType localJavaType = _mapAbstractType2(paramDeserializationConfig, paramJavaType);
      if (localJavaType == null) {
        return paramJavaType;
      }
      Class localClass1 = paramJavaType.getRawClass();
      Class localClass2 = localJavaType.getRawClass();
      if ((localClass1 == localClass2) || (!localClass1.isAssignableFrom(localClass2))) {
        throw new IllegalArgumentException("Invalid abstract type resolution from " + paramJavaType + " to " + localJavaType + ": latter is not a subtype of former");
      }
      paramJavaType = localJavaType;
    }
  }
  
  protected <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, T paramT)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    Object localObject2 = localAnnotationIntrospector.findDeserializationType(paramAnnotated, paramT);
    if (localObject2 != null) {}
    for (;;)
    {
      Object localObject1;
      try
      {
        localObject1 = paramT.narrowBy((Class)localObject2);
        paramT = (T)localObject1;
        if (!paramT.isContainerType()) {
          break label433;
        }
        localObject2 = localAnnotationIntrospector.findDeserializationKeyType(paramAnnotated, paramT.getKeyType());
        if (localObject2 != null) {
          if (!(paramT instanceof MapLikeType)) {
            throw new JsonMappingException("Illegal key-type annotation: type " + paramT + " is not a Map(-like) type");
          }
        }
      }
      catch (IllegalArgumentException paramDeserializationContext)
      {
        throw new JsonMappingException("Failed to narrow type " + paramT + " with concrete-type annotation (value " + ((Class)localObject2).getName() + "), method '" + paramAnnotated.getName() + "': " + paramDeserializationContext.getMessage(), null, paramDeserializationContext);
      }
      for (;;)
      {
        try
        {
          localObject1 = ((MapLikeType)paramT).narrowKey((Class)localObject2);
          localObject2 = ((JavaType)localObject1).getKeyType();
          paramT = (T)localObject1;
          if (localObject2 != null)
          {
            paramT = (T)localObject1;
            if (((JavaType)localObject2).getValueHandler() == null)
            {
              localObject2 = paramDeserializationContext.keyDeserializerInstance(paramAnnotated, localAnnotationIntrospector.findKeyDeserializer(paramAnnotated));
              paramT = (T)localObject1;
              if (localObject2 != null)
              {
                paramT = ((MapLikeType)localObject1).withKeyValueHandler(localObject2);
                paramT.getKeyType();
              }
            }
          }
          localObject2 = localAnnotationIntrospector.findDeserializationContentType(paramAnnotated, paramT.getContentType());
          localObject1 = paramT;
          if (localObject2 != null) {}
          localObject1 = paramT;
        }
        catch (IllegalArgumentException paramDeserializationContext)
        {
          try
          {
            localObject1 = paramT.narrowContentsBy((Class)localObject2);
            paramT = (T)localObject1;
            if (((JavaType)localObject1).getContentType().getValueHandler() == null)
            {
              paramDeserializationContext = paramDeserializationContext.deserializerInstance(paramAnnotated, localAnnotationIntrospector.findContentDeserializer(paramAnnotated));
              paramT = (T)localObject1;
              if (paramDeserializationContext != null) {
                paramT = ((JavaType)localObject1).withContentValueHandler(paramDeserializationContext);
              }
            }
            return paramT;
          }
          catch (IllegalArgumentException paramDeserializationContext)
          {
            throw new JsonMappingException("Failed to narrow content type " + paramT + " with content-type annotation (" + ((Class)localObject2).getName() + "): " + paramDeserializationContext.getMessage(), null, paramDeserializationContext);
          }
          paramDeserializationContext = paramDeserializationContext;
          throw new JsonMappingException("Failed to narrow key type " + paramT + " with key-type annotation (" + ((Class)localObject2).getName() + "): " + paramDeserializationContext.getMessage(), null, paramDeserializationContext);
        }
      }
      label433:
      return paramT;
    }
  }
  
  protected JavaType resolveType(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    paramBeanDescription = paramJavaType;
    if (paramJavaType.isContainerType())
    {
      Object localObject = paramDeserializationContext.getAnnotationIntrospector();
      paramBeanDescription = paramJavaType;
      if (paramJavaType.getKeyType() != null)
      {
        KeyDeserializer localKeyDeserializer = paramDeserializationContext.keyDeserializerInstance(paramAnnotatedMember, ((AnnotationIntrospector)localObject).findKeyDeserializer(paramAnnotatedMember));
        paramBeanDescription = paramJavaType;
        if (localKeyDeserializer != null)
        {
          paramBeanDescription = ((MapLikeType)paramJavaType).withKeyValueHandler(localKeyDeserializer);
          paramBeanDescription.getKeyType();
        }
      }
      localObject = paramDeserializationContext.deserializerInstance(paramAnnotatedMember, ((AnnotationIntrospector)localObject).findContentDeserializer(paramAnnotatedMember));
      paramJavaType = paramBeanDescription;
      if (localObject != null) {
        paramJavaType = paramBeanDescription.withContentValueHandler(localObject);
      }
      paramBeanDescription = paramJavaType;
      if ((paramAnnotatedMember instanceof AnnotatedMember))
      {
        localObject = findPropertyContentTypeDeserializer(paramDeserializationContext.getConfig(), paramJavaType, paramAnnotatedMember);
        paramBeanDescription = paramJavaType;
        if (localObject != null) {
          paramBeanDescription = paramJavaType.withContentTypeHandler(localObject);
        }
      }
    }
    if ((paramAnnotatedMember instanceof AnnotatedMember)) {}
    for (paramDeserializationContext = findPropertyTypeDeserializer(paramDeserializationContext.getConfig(), paramBeanDescription, paramAnnotatedMember);; paramDeserializationContext = findTypeDeserializer(paramDeserializationContext.getConfig(), paramBeanDescription))
    {
      paramJavaType = paramBeanDescription;
      if (paramDeserializationContext != null) {
        paramJavaType = paramBeanDescription.withTypeHandler(paramDeserializationContext);
      }
      return paramJavaType;
    }
  }
  
  public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver)
  {
    return withConfig(_factoryConfig.withAbstractTypeResolver(paramAbstractTypeResolver));
  }
  
  public final DeserializerFactory withAdditionalDeserializers(Deserializers paramDeserializers)
  {
    return withConfig(_factoryConfig.withAdditionalDeserializers(paramDeserializers));
  }
  
  public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers)
  {
    return withConfig(_factoryConfig.withAdditionalKeyDeserializers(paramKeyDeserializers));
  }
  
  protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig paramDeserializerFactoryConfig);
  
  public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier)
  {
    return withConfig(_factoryConfig.withDeserializerModifier(paramBeanDeserializerModifier));
  }
  
  public final DeserializerFactory withValueInstantiators(ValueInstantiators paramValueInstantiators)
  {
    return withConfig(_factoryConfig.withValueInstantiators(paramValueInstantiators));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.BasicDeserializerFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */