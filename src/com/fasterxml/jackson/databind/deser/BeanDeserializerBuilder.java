package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.Annotations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanDeserializerBuilder
{
  protected SettableAnyProperty _anySetter;
  protected HashMap<String, SettableBeanProperty> _backRefProperties;
  protected final BeanDescription _beanDesc;
  protected AnnotatedMethod _buildMethod;
  protected JsonPOJOBuilder.Value _builderConfig;
  protected final boolean _defaultViewInclusion;
  protected HashSet<String> _ignorableProps;
  protected boolean _ignoreAllUnknown;
  protected List<ValueInjector> _injectables;
  protected ObjectIdReader _objectIdReader;
  protected final Map<String, SettableBeanProperty> _properties = new LinkedHashMap();
  protected ValueInstantiator _valueInstantiator;
  
  public BeanDeserializerBuilder(BeanDescription paramBeanDescription, DeserializationConfig paramDeserializationConfig)
  {
    _beanDesc = paramBeanDescription;
    _defaultViewInclusion = paramDeserializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
  }
  
  protected BeanDeserializerBuilder(BeanDeserializerBuilder paramBeanDeserializerBuilder)
  {
    _beanDesc = _beanDesc;
    _defaultViewInclusion = _defaultViewInclusion;
    _anySetter = _anySetter;
    _ignoreAllUnknown = _ignoreAllUnknown;
    _properties.putAll(_properties);
    _backRefProperties = _copy(_backRefProperties);
    _ignorableProps = _ignorableProps;
    _valueInstantiator = _valueInstantiator;
    _objectIdReader = _objectIdReader;
    _buildMethod = _buildMethod;
    _builderConfig = _builderConfig;
  }
  
  private static HashMap<String, SettableBeanProperty> _copy(HashMap<String, SettableBeanProperty> paramHashMap)
  {
    if (paramHashMap == null) {
      return null;
    }
    return new HashMap(paramHashMap);
  }
  
  public void addBackReferenceProperty(String paramString, SettableBeanProperty paramSettableBeanProperty)
  {
    if (_backRefProperties == null) {
      _backRefProperties = new HashMap(4);
    }
    _backRefProperties.put(paramString, paramSettableBeanProperty);
    if (_properties != null) {
      _properties.remove(paramSettableBeanProperty.getName());
    }
  }
  
  public void addCreatorProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    addProperty(paramSettableBeanProperty);
  }
  
  public void addIgnorable(String paramString)
  {
    if (_ignorableProps == null) {
      _ignorableProps = new HashSet();
    }
    _ignorableProps.add(paramString);
  }
  
  public void addInjectable(PropertyName paramPropertyName, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, Object paramObject)
  {
    if (_injectables == null) {
      _injectables = new ArrayList();
    }
    _injectables.add(new ValueInjector(paramPropertyName, paramJavaType, paramAnnotations, paramAnnotatedMember, paramObject));
  }
  
  @Deprecated
  public void addInjectable(String paramString, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, Object paramObject)
  {
    addInjectable(new PropertyName(paramString), paramJavaType, paramAnnotations, paramAnnotatedMember, paramObject);
  }
  
  public void addOrReplaceProperty(SettableBeanProperty paramSettableBeanProperty, boolean paramBoolean)
  {
    _properties.put(paramSettableBeanProperty.getName(), paramSettableBeanProperty);
  }
  
  public void addProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)_properties.put(paramSettableBeanProperty.getName(), paramSettableBeanProperty);
    if ((localSettableBeanProperty != null) && (localSettableBeanProperty != paramSettableBeanProperty)) {
      throw new IllegalArgumentException("Duplicate property '" + paramSettableBeanProperty.getName() + "' for " + _beanDesc.getType());
    }
  }
  
  public JsonDeserializer<?> build()
  {
    boolean bool2 = true;
    Object localObject = _properties.values();
    BeanPropertyMap localBeanPropertyMap = new BeanPropertyMap((Collection)localObject);
    localBeanPropertyMap.assignIndexes();
    boolean bool1;
    if (!_defaultViewInclusion)
    {
      bool1 = true;
      if (bool1) {
        break label136;
      }
      localObject = ((Collection)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((SettableBeanProperty)((Iterator)localObject).next()).hasViews()) {
          bool1 = bool2;
        }
      }
    }
    label136:
    for (;;)
    {
      localObject = localBeanPropertyMap;
      if (_objectIdReader != null) {
        localObject = localBeanPropertyMap.withProperty(new ObjectIdValueProperty(_objectIdReader, PropertyMetadata.STD_REQUIRED));
      }
      return new BeanDeserializer(this, _beanDesc, (BeanPropertyMap)localObject, _backRefProperties, _ignorableProps, _ignoreAllUnknown, bool1);
      bool1 = false;
      break;
    }
  }
  
  public AbstractDeserializer buildAbstract()
  {
    return new AbstractDeserializer(this, _beanDesc, _backRefProperties);
  }
  
  public JsonDeserializer<?> buildBuilderBased(JavaType paramJavaType, String paramString)
  {
    boolean bool2 = true;
    if (_buildMethod == null) {
      throw new IllegalArgumentException("Builder class " + _beanDesc.getBeanClass().getName() + " does not have build method '" + paramString + "()'");
    }
    paramString = _buildMethod.getRawReturnType();
    if (!paramJavaType.getRawClass().isAssignableFrom(paramString)) {
      throw new IllegalArgumentException("Build method '" + _buildMethod.getFullName() + " has bad return type (" + paramString.getName() + "), not compatible with POJO type (" + paramJavaType.getRawClass().getName() + ")");
    }
    paramJavaType = _properties.values();
    paramString = new BeanPropertyMap(paramJavaType);
    paramString.assignIndexes();
    boolean bool1;
    if (!_defaultViewInclusion)
    {
      bool1 = true;
      if (bool1) {
        break label279;
      }
      paramJavaType = paramJavaType.iterator();
      while (paramJavaType.hasNext()) {
        if (((SettableBeanProperty)paramJavaType.next()).hasViews()) {
          bool1 = bool2;
        }
      }
    }
    label279:
    for (;;)
    {
      paramJavaType = paramString;
      if (_objectIdReader != null) {
        paramJavaType = paramString.withProperty(new ObjectIdValueProperty(_objectIdReader, PropertyMetadata.STD_REQUIRED));
      }
      return new BuilderBasedDeserializer(this, _beanDesc, paramJavaType, _backRefProperties, _ignorableProps, _ignoreAllUnknown, bool1);
      bool1 = false;
      break;
    }
  }
  
  public SettableBeanProperty findProperty(PropertyName paramPropertyName)
  {
    return (SettableBeanProperty)_properties.get(paramPropertyName.getSimpleName());
  }
  
  @Deprecated
  public SettableBeanProperty findProperty(String paramString)
  {
    return (SettableBeanProperty)_properties.get(paramString);
  }
  
  public SettableAnyProperty getAnySetter()
  {
    return _anySetter;
  }
  
  public AnnotatedMethod getBuildMethod()
  {
    return _buildMethod;
  }
  
  public JsonPOJOBuilder.Value getBuilderConfig()
  {
    return _builderConfig;
  }
  
  public List<ValueInjector> getInjectables()
  {
    return _injectables;
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return _objectIdReader;
  }
  
  public Iterator<SettableBeanProperty> getProperties()
  {
    return _properties.values().iterator();
  }
  
  public ValueInstantiator getValueInstantiator()
  {
    return _valueInstantiator;
  }
  
  public boolean hasProperty(PropertyName paramPropertyName)
  {
    return findProperty(paramPropertyName) != null;
  }
  
  @Deprecated
  public boolean hasProperty(String paramString)
  {
    return findProperty(paramString) != null;
  }
  
  public SettableBeanProperty removeProperty(PropertyName paramPropertyName)
  {
    return (SettableBeanProperty)_properties.remove(paramPropertyName.getSimpleName());
  }
  
  @Deprecated
  public SettableBeanProperty removeProperty(String paramString)
  {
    return (SettableBeanProperty)_properties.remove(paramString);
  }
  
  public void setAnySetter(SettableAnyProperty paramSettableAnyProperty)
  {
    if ((_anySetter != null) && (paramSettableAnyProperty != null)) {
      throw new IllegalStateException("_anySetter already set to non-null");
    }
    _anySetter = paramSettableAnyProperty;
  }
  
  public void setIgnoreUnknownProperties(boolean paramBoolean)
  {
    _ignoreAllUnknown = paramBoolean;
  }
  
  public void setObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    _objectIdReader = paramObjectIdReader;
  }
  
  public void setPOJOBuilder(AnnotatedMethod paramAnnotatedMethod, JsonPOJOBuilder.Value paramValue)
  {
    _buildMethod = paramAnnotatedMethod;
    _builderConfig = paramValue;
  }
  
  public void setValueInstantiator(ValueInstantiator paramValueInstantiator)
  {
    _valueInstantiator = paramValueInstantiator;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */