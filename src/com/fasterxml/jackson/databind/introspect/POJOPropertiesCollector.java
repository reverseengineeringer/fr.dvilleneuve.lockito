package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected LinkedList<AnnotatedMember> _anyGetters = null;
  protected LinkedList<AnnotatedMethod> _anySetters = null;
  protected final AnnotatedClass _classDef;
  protected final MapperConfig<?> _config;
  protected LinkedList<POJOPropertyBuilder> _creatorProperties = null;
  protected final boolean _forSerialization;
  protected HashSet<String> _ignoredPropertyNames;
  protected LinkedHashMap<Object, AnnotatedMember> _injectables;
  protected LinkedList<AnnotatedMethod> _jsonValueGetters = null;
  protected final String _mutatorPrefix;
  protected final LinkedHashMap<String, POJOPropertyBuilder> _properties = new LinkedHashMap();
  protected final JavaType _type;
  protected final VisibilityChecker<?> _visibilityChecker;
  
  protected POJOPropertiesCollector(MapperConfig<?> paramMapperConfig, boolean paramBoolean, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, String paramString)
  {
    _config = paramMapperConfig;
    _forSerialization = paramBoolean;
    _type = paramJavaType;
    _classDef = paramAnnotatedClass;
    paramJavaType = paramString;
    if (paramString == null) {
      paramJavaType = "set";
    }
    _mutatorPrefix = paramJavaType;
    paramJavaType = (JavaType)localObject;
    if (paramMapperConfig.isAnnotationProcessingEnabled()) {
      paramJavaType = _config.getAnnotationIntrospector();
    }
    _annotationIntrospector = paramJavaType;
    if (_annotationIntrospector == null)
    {
      _visibilityChecker = _config.getDefaultVisibilityChecker();
      return;
    }
    _visibilityChecker = _annotationIntrospector.findAutoDetectVisibility(paramAnnotatedClass, _config.getDefaultVisibilityChecker());
  }
  
  private void _addIgnored(String paramString)
  {
    if (!_forSerialization)
    {
      if (_ignoredPropertyNames == null) {
        _ignoredPropertyNames = new HashSet();
      }
      _ignoredPropertyNames.add(paramString);
    }
  }
  
  private PropertyNamingStrategy _findNamingStrategy()
  {
    if (_annotationIntrospector == null) {}
    for (Object localObject1 = null; localObject1 == null; localObject1 = _annotationIntrospector.findNamingStrategy(_classDef)) {
      return _config.getPropertyNamingStrategy();
    }
    if ((localObject1 instanceof PropertyNamingStrategy)) {
      return (PropertyNamingStrategy)localObject1;
    }
    if (!(localObject1 instanceof Class)) {
      throw new IllegalStateException("AnnotationIntrospector returned PropertyNamingStrategy definition of type " + localObject1.getClass().getName() + "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
    }
    localObject1 = (Class)localObject1;
    if (!PropertyNamingStrategy.class.isAssignableFrom((Class)localObject1)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + ((Class)localObject1).getName() + "; expected Class<PropertyNamingStrategy>");
    }
    Object localObject2 = _config.getHandlerInstantiator();
    if (localObject2 != null)
    {
      localObject2 = ((HandlerInstantiator)localObject2).namingStrategyInstance(_config, _classDef, (Class)localObject1);
      if (localObject2 != null) {
        return (PropertyNamingStrategy)localObject2;
      }
    }
    return (PropertyNamingStrategy)ClassUtil.createInstance((Class)localObject1, _config.canOverrideAccessModifiers());
  }
  
  protected void _addCreators()
  {
    AnnotationIntrospector localAnnotationIntrospector = _annotationIntrospector;
    if (localAnnotationIntrospector == null) {}
    Iterator localIterator;
    label74:
    AnnotatedParameter localAnnotatedParameter;
    POJOPropertyBuilder localPOJOPropertyBuilder;
    label139:
    do
    {
      return;
      localIterator = _classDef.getConstructors().iterator();
      if (localIterator.hasNext())
      {
        localObject2 = (AnnotatedConstructor)localIterator.next();
        if (_creatorProperties == null) {
          _creatorProperties = new LinkedList();
        }
        j = ((AnnotatedConstructor)localObject2).getParameterCount();
        i = 0;
        if (i < j)
        {
          localAnnotatedParameter = ((AnnotatedConstructor)localObject2).getParameter(i);
          localObject1 = localAnnotationIntrospector.findNameForDeserialization(localAnnotatedParameter);
          if (localObject1 != null) {
            break label139;
          }
        }
        for (localObject1 = null;; localObject1 = ((PropertyName)localObject1).getSimpleName())
        {
          if (localObject1 != null)
          {
            localPOJOPropertyBuilder = _property((String)localObject1);
            localPOJOPropertyBuilder.addCtor(localAnnotatedParameter, (String)localObject1, true, false);
            _creatorProperties.add(localPOJOPropertyBuilder);
          }
          i += 1;
          break label74;
          break;
        }
      }
      localIterator = _classDef.getStaticMethods().iterator();
    } while (!localIterator.hasNext());
    Object localObject2 = (AnnotatedMethod)localIterator.next();
    if (_creatorProperties == null) {
      _creatorProperties = new LinkedList();
    }
    int j = ((AnnotatedMethod)localObject2).getParameterCount();
    int i = 0;
    label209:
    if (i < j)
    {
      localAnnotatedParameter = ((AnnotatedMethod)localObject2).getParameter(i);
      localObject1 = localAnnotationIntrospector.findNameForDeserialization(localAnnotatedParameter);
      if (localObject1 != null) {
        break label274;
      }
    }
    label274:
    for (Object localObject1 = null;; localObject1 = ((PropertyName)localObject1).getSimpleName())
    {
      if (localObject1 != null)
      {
        localPOJOPropertyBuilder = _property((String)localObject1);
        localPOJOPropertyBuilder.addCtor(localAnnotatedParameter, (String)localObject1, true, false);
        _creatorProperties.add(localPOJOPropertyBuilder);
      }
      i += 1;
      break label209;
      break;
    }
  }
  
  protected void _addFields()
  {
    AnnotationIntrospector localAnnotationIntrospector = _annotationIntrospector;
    int i;
    label42:
    AnnotatedField localAnnotatedField;
    String str;
    Object localObject;
    if ((!_forSerialization) && (!_config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS)))
    {
      i = 1;
      Iterator localIterator = _classDef.fields().iterator();
      if (!localIterator.hasNext()) {
        break label257;
      }
      localAnnotatedField = (AnnotatedField)localIterator.next();
      str = localAnnotatedField.getName();
      if (localAnnotationIntrospector != null) {
        break label180;
      }
      localObject = null;
      label79:
      if (!"".equals(localObject)) {
        break label261;
      }
      localObject = str;
    }
    label100:
    label180:
    label255:
    label257:
    label258:
    label261:
    for (;;)
    {
      boolean bool1;
      if (localObject != null)
      {
        bool1 = true;
        if (bool1) {
          break label258;
        }
        bool1 = _visibilityChecker.isFieldVisible(localAnnotatedField);
      }
      for (;;)
      {
        if ((localAnnotationIntrospector != null) && (localAnnotationIntrospector.hasIgnoreMarker(localAnnotatedField))) {}
        for (boolean bool2 = true;; bool2 = false)
        {
          if ((i != 0) && (localObject == null) && (!bool2) && (Modifier.isFinal(localAnnotatedField.getModifiers()))) {
            break label255;
          }
          _property(str).addField(localAnnotatedField, (String)localObject, bool1, bool2);
          break label42;
          i = 0;
          break;
          if (_forSerialization)
          {
            localObject = localAnnotationIntrospector.findNameForSerialization(localAnnotatedField);
            if (localObject == null)
            {
              localObject = null;
              break label79;
            }
            localObject = ((PropertyName)localObject).getSimpleName();
            break label79;
          }
          localObject = localAnnotationIntrospector.findNameForDeserialization(localAnnotatedField);
          if (localObject == null)
          {
            localObject = null;
            break label79;
          }
          localObject = ((PropertyName)localObject).getSimpleName();
          break label79;
          bool1 = false;
          break label100;
        }
        break label42;
        return;
      }
    }
  }
  
  protected void _addGetterMethod(AnnotatedMethod paramAnnotatedMethod, AnnotationIntrospector paramAnnotationIntrospector)
  {
    Object localObject2 = null;
    if (paramAnnotationIntrospector != null) {
      if (paramAnnotationIntrospector.hasAnyGetterAnnotation(paramAnnotatedMethod))
      {
        if (_anyGetters == null) {
          _anyGetters = new LinkedList();
        }
        _anyGetters.add(paramAnnotatedMethod);
      }
    }
    Object localObject1;
    label95:
    do
    {
      return;
      if (paramAnnotationIntrospector.hasAsValueAnnotation(paramAnnotatedMethod))
      {
        if (_jsonValueGetters == null) {
          _jsonValueGetters = new LinkedList();
        }
        _jsonValueGetters.add(paramAnnotatedMethod);
        return;
      }
      if (paramAnnotationIntrospector != null) {
        break;
      }
      localObject1 = null;
      if (localObject1 != null) {
        break label174;
      }
      localObject1 = localObject2;
      if (localObject1 != null) {
        break label198;
      }
      localObject2 = BeanUtil.okNameForRegularGetter(paramAnnotatedMethod, paramAnnotatedMethod.getName());
      if (localObject2 != null) {
        break label184;
      }
      localObject2 = BeanUtil.okNameForIsGetter(paramAnnotatedMethod, paramAnnotatedMethod.getName());
    } while (localObject2 == null);
    boolean bool1 = _visibilityChecker.isIsGetterVisible(paramAnnotatedMethod);
    label141:
    if (paramAnnotationIntrospector == null) {}
    for (boolean bool2 = false;; bool2 = paramAnnotationIntrospector.hasIgnoreMarker(paramAnnotatedMethod))
    {
      _property((String)localObject2).addGetter(paramAnnotatedMethod, (String)localObject1, bool1, bool2);
      return;
      localObject1 = paramAnnotationIntrospector.findNameForSerialization(paramAnnotatedMethod);
      break;
      label174:
      localObject1 = ((PropertyName)localObject1).getSimpleName();
      break label95;
      label184:
      bool1 = _visibilityChecker.isGetterVisible(paramAnnotatedMethod);
      break label141;
      label198:
      Object localObject3 = BeanUtil.okNameForGetter(paramAnnotatedMethod);
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = paramAnnotatedMethod.getName();
      }
      localObject3 = localObject1;
      if (((String)localObject1).length() == 0) {
        localObject3 = localObject2;
      }
      bool1 = true;
      localObject1 = localObject3;
      break label141;
    }
  }
  
  protected void _addInjectables()
  {
    AnnotationIntrospector localAnnotationIntrospector = _annotationIntrospector;
    if (localAnnotationIntrospector == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = _classDef.fields().iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (AnnotatedField)localIterator.next();
        _doAddInjectable(localAnnotationIntrospector.findInjectableValueId((AnnotatedMember)localObject), (AnnotatedMember)localObject);
      }
      localIterator = _classDef.memberMethods().iterator();
      while (localIterator.hasNext())
      {
        localObject = (AnnotatedMethod)localIterator.next();
        if (((AnnotatedMethod)localObject).getParameterCount() == 1) {
          _doAddInjectable(localAnnotationIntrospector.findInjectableValueId((AnnotatedMember)localObject), (AnnotatedMember)localObject);
        }
      }
    }
  }
  
  protected void _addMethods()
  {
    AnnotationIntrospector localAnnotationIntrospector = _annotationIntrospector;
    Iterator localIterator = _classDef.memberMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      int i = localAnnotatedMethod.getParameterCount();
      if (i == 0)
      {
        _addGetterMethod(localAnnotatedMethod, localAnnotationIntrospector);
      }
      else if (i == 1)
      {
        _addSetterMethod(localAnnotatedMethod, localAnnotationIntrospector);
      }
      else if ((i == 2) && (localAnnotationIntrospector != null) && (localAnnotationIntrospector.hasAnySetterAnnotation(localAnnotatedMethod)))
      {
        if (_anySetters == null) {
          _anySetters = new LinkedList();
        }
        _anySetters.add(localAnnotatedMethod);
      }
    }
  }
  
  protected void _addSetterMethod(AnnotatedMethod paramAnnotatedMethod, AnnotationIntrospector paramAnnotationIntrospector)
  {
    Object localObject2 = null;
    Object localObject1;
    if (paramAnnotationIntrospector == null)
    {
      localObject1 = null;
      if (localObject1 != null) {
        break label50;
      }
      localObject1 = localObject2;
    }
    label50:
    boolean bool1;
    for (;;)
    {
      if (localObject1 == null)
      {
        localObject2 = BeanUtil.okNameForMutator(paramAnnotatedMethod, _mutatorPrefix);
        if (localObject2 == null)
        {
          return;
          localObject1 = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedMethod);
          break;
          localObject1 = ((PropertyName)localObject1).getSimpleName();
          continue;
        }
        bool1 = _visibilityChecker.isSetterVisible(paramAnnotatedMethod);
        if (paramAnnotationIntrospector != null) {
          break label144;
        }
      }
    }
    label144:
    for (boolean bool2 = false;; bool2 = paramAnnotationIntrospector.hasIgnoreMarker(paramAnnotatedMethod))
    {
      _property((String)localObject2).addSetter(paramAnnotatedMethod, (String)localObject1, bool1, bool2);
      return;
      Object localObject3 = BeanUtil.okNameForMutator(paramAnnotatedMethod, _mutatorPrefix);
      localObject2 = localObject3;
      if (localObject3 == null) {
        localObject2 = paramAnnotatedMethod.getName();
      }
      localObject3 = localObject1;
      if (((String)localObject1).length() == 0) {
        localObject3 = localObject2;
      }
      bool1 = true;
      localObject1 = localObject3;
      break;
    }
  }
  
  protected void _doAddInjectable(Object paramObject, AnnotatedMember paramAnnotatedMember)
  {
    if (paramObject == null) {}
    do
    {
      return;
      if (_injectables == null) {
        _injectables = new LinkedHashMap();
      }
    } while ((AnnotatedMember)_injectables.put(paramObject, paramAnnotatedMember) == null);
    paramAnnotatedMember = paramObject.getClass().getName();
    throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(paramObject) + "' (of type " + paramAnnotatedMember + ")");
  }
  
  protected POJOPropertyBuilder _property(String paramString)
  {
    POJOPropertyBuilder localPOJOPropertyBuilder2 = (POJOPropertyBuilder)_properties.get(paramString);
    POJOPropertyBuilder localPOJOPropertyBuilder1 = localPOJOPropertyBuilder2;
    if (localPOJOPropertyBuilder2 == null)
    {
      localPOJOPropertyBuilder1 = new POJOPropertyBuilder(new PropertyName(paramString), _annotationIntrospector, _forSerialization);
      _properties.put(paramString, localPOJOPropertyBuilder1);
    }
    return localPOJOPropertyBuilder1;
  }
  
  protected void _removeUnwantedProperties()
  {
    Iterator localIterator = _properties.entrySet().iterator();
    boolean bool;
    if (!_config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS)) {
      bool = true;
    }
    while (localIterator.hasNext())
    {
      POJOPropertyBuilder localPOJOPropertyBuilder = (POJOPropertyBuilder)((Map.Entry)localIterator.next()).getValue();
      if (!localPOJOPropertyBuilder.anyVisible())
      {
        localIterator.remove();
        continue;
        bool = false;
      }
      else
      {
        if (localPOJOPropertyBuilder.anyIgnorals())
        {
          if (!localPOJOPropertyBuilder.isExplicitlyIncluded())
          {
            localIterator.remove();
            _addIgnored(localPOJOPropertyBuilder.getName());
            continue;
          }
          localPOJOPropertyBuilder.removeIgnored();
          if ((!_forSerialization) && (!localPOJOPropertyBuilder.couldDeserialize())) {
            _addIgnored(localPOJOPropertyBuilder.getName());
          }
        }
        localPOJOPropertyBuilder.removeNonVisible(bool);
      }
    }
  }
  
  protected void _renameProperties()
  {
    Object localObject3 = _properties.entrySet().iterator();
    Object localObject1 = null;
    POJOPropertyBuilder localPOJOPropertyBuilder;
    Object localObject2;
    while (((Iterator)localObject3).hasNext())
    {
      localPOJOPropertyBuilder = (POJOPropertyBuilder)((Map.Entry)((Iterator)localObject3).next()).getValue();
      String str = localPOJOPropertyBuilder.findNewName();
      if (str != null)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new LinkedList();
        }
        ((LinkedList)localObject2).add(localPOJOPropertyBuilder.withSimpleName(str));
        ((Iterator)localObject3).remove();
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null)
    {
      localObject1 = ((LinkedList)localObject1).iterator();
      label160:
      label224:
      label231:
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (POJOPropertyBuilder)((Iterator)localObject1).next();
        localObject3 = ((POJOPropertyBuilder)localObject2).getName();
        localPOJOPropertyBuilder = (POJOPropertyBuilder)_properties.get(localObject3);
        int i;
        if (localPOJOPropertyBuilder == null)
        {
          _properties.put(localObject3, localObject2);
          if (_creatorProperties == null) {
            break label224;
          }
          i = 0;
        }
        for (;;)
        {
          if (i >= _creatorProperties.size()) {
            break label231;
          }
          if (((POJOPropertyBuilder)_creatorProperties.get(i)).getInternalName().equals(((POJOPropertyBuilder)localObject2).getInternalName()))
          {
            _creatorProperties.set(i, localObject2);
            break;
            localPOJOPropertyBuilder.addAll((POJOPropertyBuilder)localObject2);
            break label160;
            break;
          }
          i += 1;
        }
      }
    }
  }
  
  protected void _renameUsing(PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    POJOPropertyBuilder[] arrayOfPOJOPropertyBuilder = (POJOPropertyBuilder[])_properties.values().toArray(new POJOPropertyBuilder[_properties.size()]);
    _properties.clear();
    int j = arrayOfPOJOPropertyBuilder.length;
    int i = 0;
    if (i < j)
    {
      POJOPropertyBuilder localPOJOPropertyBuilder = arrayOfPOJOPropertyBuilder[i];
      Object localObject = localPOJOPropertyBuilder.getFullName();
      String str = null;
      if (_forSerialization) {
        if (localPOJOPropertyBuilder.hasGetter())
        {
          str = paramPropertyNamingStrategy.nameForGetterMethod(_config, localPOJOPropertyBuilder.getGetter(), ((PropertyName)localObject).getSimpleName());
          label96:
          if ((str == null) || (((PropertyName)localObject).hasSimpleName(str))) {
            break label313;
          }
          localPOJOPropertyBuilder = localPOJOPropertyBuilder.withSimpleName(str);
          label120:
          localObject = (POJOPropertyBuilder)_properties.get(str);
          if (localObject != null) {
            break label323;
          }
          _properties.put(str, localPOJOPropertyBuilder);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        if (!localPOJOPropertyBuilder.hasField()) {
          break label96;
        }
        str = paramPropertyNamingStrategy.nameForField(_config, localPOJOPropertyBuilder.getField(), ((PropertyName)localObject).getSimpleName());
        break label96;
        if (localPOJOPropertyBuilder.hasSetter())
        {
          str = paramPropertyNamingStrategy.nameForSetterMethod(_config, localPOJOPropertyBuilder.getSetter(), ((PropertyName)localObject).getSimpleName());
          break label96;
        }
        if (localPOJOPropertyBuilder.hasConstructorParameter())
        {
          str = paramPropertyNamingStrategy.nameForConstructorParameter(_config, localPOJOPropertyBuilder.getConstructorParameter(), ((PropertyName)localObject).getSimpleName());
          break label96;
        }
        if (localPOJOPropertyBuilder.hasField())
        {
          str = paramPropertyNamingStrategy.nameForField(_config, localPOJOPropertyBuilder.getField(), ((PropertyName)localObject).getSimpleName());
          break label96;
        }
        if (!localPOJOPropertyBuilder.hasGetter()) {
          break label96;
        }
        str = paramPropertyNamingStrategy.nameForGetterMethod(_config, localPOJOPropertyBuilder.getGetter(), ((PropertyName)localObject).getSimpleName());
        break label96;
        label313:
        str = ((PropertyName)localObject).getSimpleName();
        break label120;
        label323:
        ((POJOPropertyBuilder)localObject).addAll(localPOJOPropertyBuilder);
      }
    }
  }
  
  protected void _renameWithWrappers()
  {
    Object localObject3 = _properties.entrySet().iterator();
    Object localObject1 = null;
    POJOPropertyBuilder localPOJOPropertyBuilder;
    Object localObject2;
    while (((Iterator)localObject3).hasNext())
    {
      localPOJOPropertyBuilder = (POJOPropertyBuilder)((Map.Entry)((Iterator)localObject3).next()).getValue();
      localObject2 = localPOJOPropertyBuilder.getPrimaryMember();
      if (localObject2 != null)
      {
        PropertyName localPropertyName = _annotationIntrospector.findWrapperName((Annotated)localObject2);
        if ((localPropertyName != null) && (localPropertyName.hasSimpleName()) && (!localPropertyName.equals(localPOJOPropertyBuilder.getFullName())))
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new LinkedList();
          }
          ((LinkedList)localObject2).add(localPOJOPropertyBuilder.withName(localPropertyName));
          ((Iterator)localObject3).remove();
          localObject1 = localObject2;
        }
      }
    }
    if (localObject1 != null)
    {
      localObject1 = ((LinkedList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (POJOPropertyBuilder)((Iterator)localObject1).next();
        localObject3 = ((POJOPropertyBuilder)localObject2).getName();
        localPOJOPropertyBuilder = (POJOPropertyBuilder)_properties.get(localObject3);
        if (localPOJOPropertyBuilder == null) {
          _properties.put(localObject3, localObject2);
        } else {
          localPOJOPropertyBuilder.addAll((POJOPropertyBuilder)localObject2);
        }
      }
    }
  }
  
  protected void _sortProperties()
  {
    Object localObject2 = _annotationIntrospector;
    boolean bool;
    if (localObject2 == null)
    {
      localObject1 = null;
      if (localObject1 != null) {
        break label66;
      }
      bool = _config.shouldSortPropertiesAlphabetically();
      label27:
      if (localObject2 != null) {
        break label75;
      }
    }
    label66:
    label75:
    for (localObject2 = null;; localObject2 = ((AnnotationIntrospector)localObject2).findSerializationPropertyOrder(_classDef))
    {
      if ((bool) || (_creatorProperties != null) || (localObject2 != null)) {
        break label89;
      }
      return;
      localObject1 = ((AnnotationIntrospector)localObject2).findSerializationSortAlphabetically(_classDef);
      break;
      bool = ((Boolean)localObject1).booleanValue();
      break label27;
    }
    label89:
    int i = _properties.size();
    if (bool) {}
    Object localObject3;
    for (Object localObject1 = new TreeMap();; localObject1 = new LinkedHashMap(i + i))
    {
      localObject3 = _properties.values().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (POJOPropertyBuilder)((Iterator)localObject3).next();
        ((Map)localObject1).put(((POJOPropertyBuilder)localObject4).getName(), localObject4);
      }
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(i + i);
    Object localObject5;
    if (localObject2 != null)
    {
      int j = localObject2.length;
      i = 0;
      if (i < j)
      {
        localObject5 = localObject2[i];
        localObject3 = (POJOPropertyBuilder)((Map)localObject1).get(localObject5);
        if (localObject3 != null) {
          break label478;
        }
        Iterator localIterator = _properties.values().iterator();
        while (localIterator.hasNext())
        {
          localObject4 = (POJOPropertyBuilder)localIterator.next();
          if (((String)localObject5).equals(((POJOPropertyBuilder)localObject4).getInternalName()))
          {
            localObject5 = ((POJOPropertyBuilder)localObject4).getName();
            localObject3 = localObject4;
          }
        }
      }
    }
    label478:
    for (Object localObject4 = localObject5;; localObject4 = localObject5)
    {
      if (localObject3 != null) {
        localLinkedHashMap.put(localObject4, localObject3);
      }
      i += 1;
      break;
      if (_creatorProperties != null)
      {
        if (bool)
        {
          localObject2 = new TreeMap();
          localObject3 = _creatorProperties.iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (POJOPropertyBuilder)((Iterator)localObject3).next();
            ((TreeMap)localObject2).put(((POJOPropertyBuilder)localObject4).getName(), localObject4);
          }
        }
        for (localObject2 = ((TreeMap)localObject2).values();; localObject2 = _creatorProperties)
        {
          localObject2 = ((Collection)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (POJOPropertyBuilder)((Iterator)localObject2).next();
            localLinkedHashMap.put(((POJOPropertyBuilder)localObject3).getName(), localObject3);
          }
        }
      }
      localLinkedHashMap.putAll((Map)localObject1);
      _properties.clear();
      _properties.putAll(localLinkedHashMap);
      return;
    }
  }
  
  public POJOPropertiesCollector collect()
  {
    _properties.clear();
    _addFields();
    _addMethods();
    _addCreators();
    _addInjectables();
    _removeUnwantedProperties();
    _renameProperties();
    Object localObject = _findNamingStrategy();
    if (localObject != null) {
      _renameUsing((PropertyNamingStrategy)localObject);
    }
    localObject = _properties.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      ((POJOPropertyBuilder)((Iterator)localObject).next()).trimByVisibility();
    }
    localObject = _properties.values().iterator();
    while (((Iterator)localObject).hasNext()) {
      ((POJOPropertyBuilder)((Iterator)localObject).next()).mergeAnnotations(_forSerialization);
    }
    if (_config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
      _renameWithWrappers();
    }
    _sortProperties();
    return this;
  }
  
  public Class<?> findPOJOBuilderClass()
  {
    return _annotationIntrospector.findPOJOBuilder(_classDef);
  }
  
  public AnnotationIntrospector getAnnotationIntrospector()
  {
    return _annotationIntrospector;
  }
  
  public AnnotatedMember getAnyGetter()
  {
    if (_anyGetters != null)
    {
      if (_anyGetters.size() > 1) {
        reportProblem("Multiple 'any-getters' defined (" + _anyGetters.get(0) + " vs " + _anyGetters.get(1) + ")");
      }
      return (AnnotatedMember)_anyGetters.getFirst();
    }
    return null;
  }
  
  public AnnotatedMethod getAnySetterMethod()
  {
    if (_anySetters != null)
    {
      if (_anySetters.size() > 1) {
        reportProblem("Multiple 'any-setters' defined (" + _anySetters.get(0) + " vs " + _anySetters.get(1) + ")");
      }
      return (AnnotatedMethod)_anySetters.getFirst();
    }
    return null;
  }
  
  public AnnotatedClass getClassDef()
  {
    return _classDef;
  }
  
  public MapperConfig<?> getConfig()
  {
    return _config;
  }
  
  public Set<String> getIgnoredPropertyNames()
  {
    return _ignoredPropertyNames;
  }
  
  public Map<Object, AnnotatedMember> getInjectables()
  {
    return _injectables;
  }
  
  public AnnotatedMethod getJsonValueMethod()
  {
    if (_jsonValueGetters != null)
    {
      if (_jsonValueGetters.size() > 1) {
        reportProblem("Multiple value properties defined (" + _jsonValueGetters.get(0) + " vs " + _jsonValueGetters.get(1) + ")");
      }
      return (AnnotatedMethod)_jsonValueGetters.get(0);
    }
    return null;
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    Object localObject;
    if (_annotationIntrospector == null) {
      localObject = null;
    }
    ObjectIdInfo localObjectIdInfo;
    do
    {
      return (ObjectIdInfo)localObject;
      localObjectIdInfo = _annotationIntrospector.findObjectIdInfo(_classDef);
      localObject = localObjectIdInfo;
    } while (localObjectIdInfo == null);
    return _annotationIntrospector.findObjectReferenceInfo(_classDef, localObjectIdInfo);
  }
  
  public List<BeanPropertyDefinition> getProperties()
  {
    return new ArrayList(_properties.values());
  }
  
  protected Map<String, POJOPropertyBuilder> getPropertyMap()
  {
    return _properties;
  }
  
  public JavaType getType()
  {
    return _type;
  }
  
  protected void reportProblem(String paramString)
  {
    throw new IllegalArgumentException("Problem with definition of " + _classDef + ": " + paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */