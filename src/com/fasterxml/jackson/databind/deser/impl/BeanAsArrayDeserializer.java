package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.HashSet;

public class BeanAsArrayDeserializer
  extends BeanDeserializerBase
{
  private static final long serialVersionUID = 1L;
  protected final BeanDeserializerBase _delegate;
  protected final SettableBeanProperty[] _orderedProperties;
  
  public BeanAsArrayDeserializer(BeanDeserializerBase paramBeanDeserializerBase, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    super(paramBeanDeserializerBase);
    _delegate = paramBeanDeserializerBase;
    _orderedProperties = paramArrayOfSettableBeanProperty;
  }
  
  protected Object _deserializeFromNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    throw paramDeserializationContext.mappingException("Can not deserialize a POJO (of type " + _beanType.getRawClass().getName() + ") from non-Array representation (token: " + paramJsonParser.getCurrentToken() + "): type/property designed to be serialized as JSON Array");
  }
  
  protected Object _deserializeNonVanilla(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_nonStandardCreation) {
      return _deserializeWithCreator(paramJsonParser, paramDeserializationContext);
    }
    Object localObject = _valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (_injectables != null) {
      injectValues(paramDeserializationContext, localObject);
    }
    Class localClass;
    SettableBeanProperty[] arrayOfSettableBeanProperty;
    int i;
    int j;
    if (_needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      arrayOfSettableBeanProperty = _orderedProperties;
      i = 0;
      j = arrayOfSettableBeanProperty.length;
    }
    for (;;)
    {
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        return localObject;
        localClass = null;
        break;
      }
      if (i == j)
      {
        if (_ignoreAllUnknown) {
          break label195;
        }
        throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most " + j + " properties (in JSON Array)");
      }
      SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
      i += 1;
      if ((localSettableBeanProperty != null) && ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass)))) {
        try
        {
          localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject);
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, localObject, localSettableBeanProperty.getName(), paramDeserializationContext);
        }
      } else {
        paramJsonParser.skipChildren();
      }
    }
    label195:
    while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
      paramJsonParser.skipChildren();
    }
    return localObject;
  }
  
  protected final Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    PropertyBasedCreator localPropertyBasedCreator = _propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, _objectIdReader);
    SettableBeanProperty[] arrayOfSettableBeanProperty = _orderedProperties;
    int j = arrayOfSettableBeanProperty.length;
    int i = 0;
    Object localObject1 = null;
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
    {
      Object localObject5;
      label57:
      Object localObject2;
      if (i < j)
      {
        localObject5 = arrayOfSettableBeanProperty[i];
        if (localObject5 != null) {
          break label88;
        }
        paramJsonParser.skipChildren();
        localObject2 = localObject1;
      }
      for (;;)
      {
        i += 1;
        localObject1 = localObject2;
        break;
        localObject5 = null;
        break label57;
        label88:
        Object localObject3;
        if (localObject1 != null)
        {
          try
          {
            ((SettableBeanProperty)localObject5).deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject1);
            localObject2 = localObject1;
          }
          catch (Exception localException1)
          {
            wrapAndThrow(localException1, localObject1, ((SettableBeanProperty)localObject5).getName(), paramDeserializationContext);
            localObject3 = localObject1;
          }
        }
        else
        {
          String str = ((SettableBeanProperty)localObject5).getName();
          SettableBeanProperty localSettableBeanProperty = localPropertyBasedCreator.findCreatorProperty(str);
          Object localObject4;
          if (localSettableBeanProperty != null)
          {
            localObject5 = localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
            localObject3 = localObject1;
            if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty.getCreatorIndex(), localObject5)) {
              try
              {
                localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
                localObject1 = localObject3;
                localObject3 = localObject1;
                if (localObject1.getClass() == _beanType.getRawClass()) {
                  continue;
                }
                throw paramDeserializationContext.mappingException("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type " + _beanType.getRawClass().getName() + ", actual type " + localObject1.getClass().getName());
              }
              catch (Exception localException2)
              {
                wrapAndThrow(localException2, _beanType.getRawClass(), str, paramDeserializationContext);
                localObject4 = localObject1;
              }
            }
          }
          else
          {
            localObject4 = localObject1;
            if (!localPropertyValueBuffer.readIdProperty(str))
            {
              localPropertyValueBuffer.bufferProperty((SettableBeanProperty)localObject5, ((SettableBeanProperty)localObject5).deserialize(paramJsonParser, paramDeserializationContext));
              localObject4 = localObject1;
            }
          }
        }
      }
    }
    paramJsonParser = (JsonParser)localObject1;
    if (localObject1 == null) {}
    try
    {
      paramJsonParser = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
      return paramJsonParser;
    }
    catch (Exception paramJsonParser)
    {
      wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
    }
    return null;
  }
  
  protected Object _deserializeWithCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
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
  
  protected BeanDeserializerBase asArrayDeserializer()
  {
    return this;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
      return _deserializeFromNonArray(paramJsonParser, paramDeserializationContext);
    }
    if (!_vanillaProcessing) {
      return _deserializeNonVanilla(paramJsonParser, paramDeserializationContext);
    }
    Object localObject = _valueInstantiator.createUsingDefault(paramDeserializationContext);
    SettableBeanProperty[] arrayOfSettableBeanProperty = _orderedProperties;
    int i = 0;
    int j = arrayOfSettableBeanProperty.length;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      return localObject;
    }
    if (i == j)
    {
      if (!_ignoreAllUnknown) {
        throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most " + j + " properties (in JSON Array)");
      }
    }
    else
    {
      SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
      if (localSettableBeanProperty != null) {}
      for (;;)
      {
        try
        {
          localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject);
          i += 1;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, localObject, localSettableBeanProperty.getName(), paramDeserializationContext);
          continue;
        }
        paramJsonParser.skipChildren();
      }
    }
    while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
      paramJsonParser.skipChildren();
    }
    return localObject;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (_injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    SettableBeanProperty[] arrayOfSettableBeanProperty = _orderedProperties;
    int i = 0;
    int j = arrayOfSettableBeanProperty.length;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {}
    for (;;)
    {
      return paramObject;
      if (i == j)
      {
        if (!_ignoreAllUnknown) {
          throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most " + j + " properties (in JSON Array)");
        }
      }
      else
      {
        SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
        if (localSettableBeanProperty != null) {}
        for (;;)
        {
          try
          {
            localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
            i += 1;
          }
          catch (Exception localException)
          {
            wrapAndThrow(localException, paramObject, localSettableBeanProperty.getName(), paramDeserializationContext);
            continue;
          }
          paramJsonParser.skipChildren();
        }
      }
      while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        paramJsonParser.skipChildren();
      }
    }
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    return _deserializeFromNonArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    return _delegate.unwrappingDeserializer(paramNameTransformer);
  }
  
  public BeanAsArrayDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BeanAsArrayDeserializer(_delegate.withIgnorableProperties(paramHashSet), _orderedProperties);
  }
  
  public BeanAsArrayDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BeanAsArrayDeserializer(_delegate.withObjectIdReader(paramObjectIdReader), _orderedProperties);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */