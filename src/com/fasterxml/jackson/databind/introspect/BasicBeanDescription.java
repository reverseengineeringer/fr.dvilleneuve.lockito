package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription
  extends BeanDescription
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected AnnotatedMember _anyGetter;
  protected AnnotatedMethod _anySetterMethod;
  protected TypeBindings _bindings;
  protected final AnnotatedClass _classInfo;
  protected final MapperConfig<?> _config;
  protected Set<String> _ignoredPropertyNames;
  protected Map<Object, AnnotatedMember> _injectables;
  protected AnnotatedMethod _jsonValueMethod;
  protected ObjectIdInfo _objectIdInfo;
  protected final List<BeanPropertyDefinition> _properties;
  
  protected BasicBeanDescription(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, List<BeanPropertyDefinition> paramList)
  {
    super(paramJavaType);
    _config = paramMapperConfig;
    if (paramMapperConfig == null) {}
    for (paramMapperConfig = null;; paramMapperConfig = paramMapperConfig.getAnnotationIntrospector())
    {
      _annotationIntrospector = paramMapperConfig;
      _classInfo = paramAnnotatedClass;
      _properties = paramList;
      return;
    }
  }
  
  protected BasicBeanDescription(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    this(paramPOJOPropertiesCollector.getConfig(), paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef(), paramPOJOPropertiesCollector.getProperties());
    _objectIdInfo = paramPOJOPropertiesCollector.getObjectIdInfo();
  }
  
  public static BasicBeanDescription forDeserialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    BasicBeanDescription localBasicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector);
    _anySetterMethod = paramPOJOPropertiesCollector.getAnySetterMethod();
    _ignoredPropertyNames = paramPOJOPropertiesCollector.getIgnoredPropertyNames();
    _injectables = paramPOJOPropertiesCollector.getInjectables();
    _jsonValueMethod = paramPOJOPropertiesCollector.getJsonValueMethod();
    return localBasicBeanDescription;
  }
  
  public static BasicBeanDescription forOtherUse(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass)
  {
    return new BasicBeanDescription(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  public static BasicBeanDescription forSerialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    BasicBeanDescription localBasicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector);
    _jsonValueMethod = paramPOJOPropertiesCollector.getJsonValueMethod();
    _anyGetter = paramPOJOPropertiesCollector.getAnyGetter();
    return localBasicBeanDescription;
  }
  
  public Converter<Object, Object> _createConverter(Object paramObject)
  {
    Object localObject = null;
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof Converter)) {
      return (Converter)paramObject;
    }
    if (!(paramObject instanceof Class)) {
      throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + paramObject.getClass().getName() + "; expected type Converter or Class<Converter> instead");
    }
    Class localClass = (Class)paramObject;
    if ((localClass == Converter.None.class) || (localClass == NoClass.class)) {
      return null;
    }
    if (!Converter.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<Converter>");
    }
    paramObject = _config.getHandlerInstantiator();
    if (paramObject == null) {}
    for (paramObject = localObject;; paramObject = ((HandlerInstantiator)paramObject).converterInstance(_config, _classInfo, localClass))
    {
      localObject = paramObject;
      if (paramObject == null) {
        localObject = (Converter)ClassUtil.createInstance(localClass, _config.canOverrideAccessModifiers());
      }
      return (Converter<Object, Object>)localObject;
    }
  }
  
  public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> paramCollection, boolean paramBoolean)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = _properties.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (BeanPropertyDefinition)localIterator.next();
      AnnotatedField localAnnotatedField = ((BeanPropertyDefinition)localObject).getField();
      if (localAnnotatedField != null)
      {
        localObject = ((BeanPropertyDefinition)localObject).getName();
        if ((paramCollection == null) || (!paramCollection.contains(localObject))) {
          localLinkedHashMap.put(localObject, localAnnotatedField);
        }
      }
    }
    return localLinkedHashMap;
  }
  
  public TypeBindings bindingsForBeanType()
  {
    if (_bindings == null) {
      _bindings = new TypeBindings(_config.getTypeFactory(), _type);
    }
    return _bindings;
  }
  
  public AnnotatedMember findAnyGetter()
    throws IllegalArgumentException
  {
    if ((_anyGetter != null) && (!Map.class.isAssignableFrom(_anyGetter.getRawType()))) {
      throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + _anyGetter.getName() + "(): return type is not instance of java.util.Map");
    }
    return _anyGetter;
  }
  
  public AnnotatedMethod findAnySetter()
    throws IllegalArgumentException
  {
    if (_anySetterMethod != null)
    {
      Class localClass = _anySetterMethod.getRawParameterType(0);
      if ((localClass != String.class) && (localClass != Object.class)) {
        throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + _anySetterMethod.getName() + "(): first argument not of type String or Object, but " + localClass.getName());
      }
    }
    return _anySetterMethod;
  }
  
  public Map<String, AnnotatedMember> findBackReferenceProperties()
  {
    HashMap localHashMap = null;
    Iterator localIterator = _properties.iterator();
    for (;;)
    {
      AnnotatedMember localAnnotatedMember;
      Object localObject;
      if (localIterator.hasNext())
      {
        localAnnotatedMember = ((BeanPropertyDefinition)localIterator.next()).getMutator();
        if (localAnnotatedMember == null) {
          continue;
        }
        localObject = _annotationIntrospector.findReferenceType(localAnnotatedMember);
        if ((localObject == null) || (!((AnnotationIntrospector.ReferenceProperty)localObject).isBackReference())) {
          continue;
        }
        if (localHashMap != null) {
          break label127;
        }
        localHashMap = new HashMap();
      }
      label127:
      for (;;)
      {
        localObject = ((AnnotationIntrospector.ReferenceProperty)localObject).getName();
        if (localHashMap.put(localObject, localAnnotatedMember) == null) {
          break;
        }
        throw new IllegalArgumentException("Multiple back-reference properties with name '" + (String)localObject + "'");
        return localHashMap;
      }
    }
  }
  
  public List<String> findCreatorPropertyNames()
  {
    int i = 0;
    for (Object localObject1 = null; i < 2; localObject1 = localObject2)
    {
      int j;
      if (i == 0)
      {
        localObject2 = getConstructors();
        Iterator localIterator = ((List)localObject2).iterator();
        localObject2 = localObject1;
        AnnotatedWithParams localAnnotatedWithParams;
        int k;
        label119:
        do
        {
          PropertyName localPropertyName;
          do
          {
            do
            {
              if (!localIterator.hasNext()) {
                break;
              }
              localAnnotatedWithParams = (AnnotatedWithParams)localIterator.next();
              k = localAnnotatedWithParams.getParameterCount();
            } while (k < 1);
            localPropertyName = _annotationIntrospector.findNameForDeserialization(localAnnotatedWithParams.getParameter(0));
          } while (localPropertyName == null);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = new ArrayList();
          }
          ((List)localObject1).add(localPropertyName.getSimpleName());
          j = 1;
          localObject2 = localObject1;
        } while (j >= k);
        localObject2 = _annotationIntrospector.findNameForDeserialization(localAnnotatedWithParams.getParameter(j));
        if (localObject2 != null) {
          break label177;
        }
      }
      label177:
      for (localObject2 = null;; localObject2 = ((PropertyName)localObject2).getSimpleName())
      {
        ((List)localObject1).add(localObject2);
        j += 1;
        break label119;
        localObject2 = getFactoryMethods();
        break;
      }
      i += 1;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = Collections.emptyList();
    }
    return (List<String>)localObject2;
  }
  
  public AnnotatedConstructor findDefaultConstructor()
  {
    return _classInfo.getDefaultConstructor();
  }
  
  public Converter<Object, Object> findDeserializationConverter()
  {
    if (_annotationIntrospector == null) {
      return null;
    }
    return _createConverter(_annotationIntrospector.findDeserializationConverter(_classInfo));
  }
  
  public JsonFormat.Value findExpectedFormat(JsonFormat.Value paramValue)
  {
    Object localObject = paramValue;
    if (_annotationIntrospector != null)
    {
      JsonFormat.Value localValue = _annotationIntrospector.findFormat(_classInfo);
      localObject = paramValue;
      if (localValue != null) {
        localObject = localValue;
      }
    }
    return (JsonFormat.Value)localObject;
  }
  
  public Method findFactoryMethod(Class<?>... paramVarArgs)
  {
    Iterator localIterator = _classInfo.getStaticMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      if (isFactoryMethod(localAnnotatedMethod))
      {
        Class localClass = localAnnotatedMethod.getRawParameterType(0);
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
          if (localClass.isAssignableFrom(paramVarArgs[i])) {
            return localAnnotatedMethod.getAnnotated();
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public Map<Object, AnnotatedMember> findInjectables()
  {
    return _injectables;
  }
  
  public AnnotatedMethod findJsonValueMethod()
  {
    return _jsonValueMethod;
  }
  
  public AnnotatedMethod findMethod(String paramString, Class<?>[] paramArrayOfClass)
  {
    return _classInfo.findMethod(paramString, paramArrayOfClass);
  }
  
  public Class<?> findPOJOBuilder()
  {
    if (_annotationIntrospector == null) {
      return null;
    }
    return _annotationIntrospector.findPOJOBuilder(_classInfo);
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig()
  {
    if (_annotationIntrospector == null) {
      return null;
    }
    return _annotationIntrospector.findPOJOBuilderConfig(_classInfo);
  }
  
  public List<BeanPropertyDefinition> findProperties()
  {
    return _properties;
  }
  
  public Converter<Object, Object> findSerializationConverter()
  {
    if (_annotationIntrospector == null) {
      return null;
    }
    return _createConverter(_annotationIntrospector.findSerializationConverter(_classInfo));
  }
  
  public JsonInclude.Include findSerializationInclusion(JsonInclude.Include paramInclude)
  {
    if (_annotationIntrospector == null) {
      return paramInclude;
    }
    return _annotationIntrospector.findSerializationInclusion(_classInfo, paramInclude);
  }
  
  public Constructor<?> findSingleArgConstructor(Class<?>... paramVarArgs)
  {
    Iterator localIterator = _classInfo.getConstructors().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedConstructor localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
      if (localAnnotatedConstructor.getParameterCount() == 1)
      {
        Class localClass = localAnnotatedConstructor.getRawParameterType(0);
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
          if (paramVarArgs[i] == localClass) {
            return localAnnotatedConstructor.getAnnotated();
          }
          i += 1;
        }
      }
    }
    return null;
  }
  
  public Annotations getClassAnnotations()
  {
    return _classInfo.getAnnotations();
  }
  
  public AnnotatedClass getClassInfo()
  {
    return _classInfo;
  }
  
  public List<AnnotatedConstructor> getConstructors()
  {
    return _classInfo.getConstructors();
  }
  
  public List<AnnotatedMethod> getFactoryMethods()
  {
    Object localObject = _classInfo.getStaticMethods();
    if (((List)localObject).isEmpty()) {
      return (List<AnnotatedMethod>)localObject;
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)((Iterator)localObject).next();
      if (isFactoryMethod(localAnnotatedMethod)) {
        localArrayList.add(localAnnotatedMethod);
      }
    }
    return localArrayList;
  }
  
  public Set<String> getIgnoredPropertyNames()
  {
    if (_ignoredPropertyNames == null) {
      return Collections.emptySet();
    }
    return _ignoredPropertyNames;
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    return _objectIdInfo;
  }
  
  public boolean hasKnownClassAnnotations()
  {
    return _classInfo.hasAnnotations();
  }
  
  public Object instantiateBean(boolean paramBoolean)
  {
    Object localObject = _classInfo.getDefaultConstructor();
    if (localObject == null) {
      return null;
    }
    if (paramBoolean) {
      ((AnnotatedConstructor)localObject).fixAccess();
    }
    for (;;)
    {
      try
      {
        localObject = ((AnnotatedConstructor)localObject).getAnnotated().newInstance(new Object[0]);
        return localObject;
      }
      catch (Exception localException)
      {
        continue;
      }
      if (((Throwable)localObject).getCause() == null) {
        continue;
      }
      localObject = ((Throwable)localObject).getCause();
    }
    if ((localObject instanceof Error)) {
      throw ((Error)localObject);
    }
    if ((localObject instanceof RuntimeException)) {
      throw ((RuntimeException)localObject);
    }
    throw new IllegalArgumentException("Failed to instantiate bean of type " + _classInfo.getAnnotated().getName() + ": (" + localObject.getClass().getName() + ") " + ((Throwable)localObject).getMessage(), (Throwable)localObject);
  }
  
  protected boolean isFactoryMethod(AnnotatedMethod paramAnnotatedMethod)
  {
    Object localObject = paramAnnotatedMethod.getRawReturnType();
    if (!getBeanClass().isAssignableFrom((Class)localObject)) {}
    do
    {
      do
      {
        return false;
        if (_annotationIntrospector.hasCreatorAnnotation(paramAnnotatedMethod)) {
          return true;
        }
        localObject = paramAnnotatedMethod.getName();
        if ("valueOf".equals(localObject)) {
          return true;
        }
      } while ((!"fromString".equals(localObject)) || (1 != paramAnnotatedMethod.getParameterCount()));
      paramAnnotatedMethod = paramAnnotatedMethod.getRawParameterType(0);
    } while ((paramAnnotatedMethod != String.class) && (!CharSequence.class.isAssignableFrom(paramAnnotatedMethod)));
    return true;
  }
  
  public boolean removeProperty(String paramString)
  {
    Iterator localIterator = _properties.iterator();
    while (localIterator.hasNext()) {
      if (((BeanPropertyDefinition)localIterator.next()).getName().equals(paramString))
      {
        localIterator.remove();
        return true;
      }
    }
    return false;
  }
  
  public JavaType resolveType(Type paramType)
  {
    if (paramType == null) {
      return null;
    }
    return bindingsForBeanType().resolveType(paramType);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.BasicBeanDescription
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */