package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class PropertyBasedCreator
{
  protected final Object[] _defaultValues;
  protected final HashMap<String, SettableBeanProperty> _properties;
  protected final SettableBeanProperty[] _propertiesWithInjectables;
  protected final int _propertyCount;
  protected final ValueInstantiator _valueInstantiator;
  
  protected PropertyBasedCreator(ValueInstantiator paramValueInstantiator, SettableBeanProperty[] paramArrayOfSettableBeanProperty, Object[] paramArrayOfObject)
  {
    _valueInstantiator = paramValueInstantiator;
    _properties = new HashMap();
    int j = paramArrayOfSettableBeanProperty.length;
    _propertyCount = j;
    paramValueInstantiator = null;
    int i = 0;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = paramArrayOfSettableBeanProperty[i];
      _properties.put(localSettableBeanProperty.getName(), localSettableBeanProperty);
      Object localObject = paramValueInstantiator;
      if (localSettableBeanProperty.getInjectableValueId() != null)
      {
        localObject = paramValueInstantiator;
        if (paramValueInstantiator == null) {
          localObject = new SettableBeanProperty[j];
        }
        localObject[i] = localSettableBeanProperty;
      }
      i += 1;
      paramValueInstantiator = (ValueInstantiator)localObject;
    }
    _defaultValues = paramArrayOfObject;
    _propertiesWithInjectables = paramValueInstantiator;
  }
  
  public static PropertyBasedCreator construct(DeserializationContext paramDeserializationContext, ValueInstantiator paramValueInstantiator, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
    throws JsonMappingException
  {
    int j = paramArrayOfSettableBeanProperty.length;
    SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[j];
    int i = 0;
    Object localObject1 = null;
    Object localObject2;
    if (i < j)
    {
      localObject2 = paramArrayOfSettableBeanProperty[i];
      localObject3 = localObject2;
      if (!((SettableBeanProperty)localObject2).hasValueDeserializer()) {
        localObject3 = ((SettableBeanProperty)localObject2).withValueDeserializer(paramDeserializationContext.findContextualValueDeserializer(((SettableBeanProperty)localObject2).getType(), (BeanProperty)localObject2));
      }
      arrayOfSettableBeanProperty[i] = localObject3;
      localObject2 = ((SettableBeanProperty)localObject3).getValueDeserializer();
      if (localObject2 == null)
      {
        localObject2 = null;
        label78:
        if ((localObject2 != null) || (!((SettableBeanProperty)localObject3).getType().isPrimitive())) {
          break label172;
        }
      }
    }
    label172:
    for (Object localObject3 = ClassUtil.defaultValue(((SettableBeanProperty)localObject3).getType().getRawClass());; localObject3 = localObject2)
    {
      localObject2 = localObject1;
      if (localObject3 != null)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new Object[j];
        }
        localObject2[i] = localObject3;
      }
      i += 1;
      localObject1 = localObject2;
      break;
      localObject2 = ((JsonDeserializer)localObject2).getNullValue();
      break label78;
      return new PropertyBasedCreator(paramValueInstantiator, arrayOfSettableBeanProperty, (Object[])localObject1);
    }
  }
  
  public void assignDeserializer(SettableBeanProperty paramSettableBeanProperty, JsonDeserializer<Object> paramJsonDeserializer)
  {
    paramSettableBeanProperty = paramSettableBeanProperty.withValueDeserializer(paramJsonDeserializer);
    _properties.put(paramSettableBeanProperty.getName(), paramSettableBeanProperty);
  }
  
  public Object build(DeserializationContext paramDeserializationContext, PropertyValueBuffer paramPropertyValueBuffer)
    throws IOException
  {
    Object localObject = paramPropertyValueBuffer.handleIdValue(paramDeserializationContext, _valueInstantiator.createFromObjectWith(paramDeserializationContext, paramPropertyValueBuffer.getParameters(_defaultValues)));
    for (paramDeserializationContext = paramPropertyValueBuffer.buffered(); paramDeserializationContext != null; paramDeserializationContext = next) {
      paramDeserializationContext.assign(localObject);
    }
    return localObject;
  }
  
  public SettableBeanProperty findCreatorProperty(int paramInt)
  {
    Iterator localIterator = _properties.values().iterator();
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
      if (localSettableBeanProperty.getPropertyIndex() == paramInt) {
        return localSettableBeanProperty;
      }
    }
    return null;
  }
  
  public SettableBeanProperty findCreatorProperty(String paramString)
  {
    return (SettableBeanProperty)_properties.get(paramString);
  }
  
  public Collection<SettableBeanProperty> properties()
  {
    return _properties.values();
  }
  
  public PropertyValueBuffer startBuilding(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, ObjectIdReader paramObjectIdReader)
  {
    paramJsonParser = new PropertyValueBuffer(paramJsonParser, paramDeserializationContext, _propertyCount, paramObjectIdReader);
    if (_propertiesWithInjectables != null) {
      paramJsonParser.inject(_propertiesWithInjectables);
    }
    return paramJsonParser;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */