package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer
  extends BeanDeserializerBase
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  protected BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase)
  {
    super(paramBeanDeserializerBase, _ignoreAllUnknown);
  }
  
  public BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, ObjectIdReader paramObjectIdReader)
  {
    super(paramBeanDeserializerBase, paramObjectIdReader);
  }
  
  protected BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, NameTransformer paramNameTransformer)
  {
    super(paramBeanDeserializerBase, paramNameTransformer);
  }
  
  public BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, HashSet<String> paramHashSet)
  {
    super(paramBeanDeserializerBase, paramHashSet);
  }
  
  protected BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, boolean paramBoolean)
  {
    super(paramBeanDeserializerBase, paramBoolean);
  }
  
  public BeanDeserializer(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, BeanPropertyMap paramBeanPropertyMap, Map<String, SettableBeanProperty> paramMap, HashSet<String> paramHashSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramBeanDeserializerBuilder, paramBeanDescription, paramBeanPropertyMap, paramMap, paramHashSet, paramBoolean1, paramBoolean2);
  }
  
  private final Object vanillaDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonToken paramJsonToken)
    throws IOException, JsonProcessingException
  {
    paramJsonToken = _valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
    {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty = _beanProperties.find(str);
      if (localSettableBeanProperty != null) {}
      for (;;)
      {
        try
        {
          localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramJsonToken);
          paramJsonParser.nextToken();
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramJsonToken, str, paramDeserializationContext);
          continue;
        }
        handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramJsonToken, str);
      }
    }
    return paramJsonToken;
  }
  
  protected final Object _deserializeOther(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonToken paramJsonToken)
    throws IOException, JsonProcessingException
  {
    if (paramJsonToken == null) {
      return _missingToken(paramJsonParser, paramDeserializationContext);
    }
    switch (paramJsonToken)
    {
    default: 
      throw paramDeserializationContext.mappingException(handledType());
    case ???: 
      return deserializeFromString(paramJsonParser, paramDeserializationContext);
    case ???: 
      return deserializeFromNumber(paramJsonParser, paramDeserializationContext);
    case ???: 
      return deserializeFromDouble(paramJsonParser, paramDeserializationContext);
    case ???: 
      return paramJsonParser.getEmbeddedObject();
    case ???: 
    case ???: 
      return deserializeFromBoolean(paramJsonParser, paramDeserializationContext);
    case ???: 
      return deserializeFromArray(paramJsonParser, paramDeserializationContext);
    }
    if (_vanillaProcessing) {
      return vanillaDeserialize(paramJsonParser, paramDeserializationContext, paramJsonToken);
    }
    if (_objectIdReader != null) {
      return deserializeWithObjectId(paramJsonParser, paramDeserializationContext);
    }
    return deserializeFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  protected Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject4 = null;
    PropertyBasedCreator localPropertyBasedCreator = _propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, _objectIdReader);
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = null;
    if (localObject2 == JsonToken.FIELD_NAME)
    {
      Object localObject5 = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty = localPropertyBasedCreator.findCreatorProperty((String)localObject5);
      Object localObject3;
      if (localSettableBeanProperty != null)
      {
        Object localObject6 = localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
        localObject2 = localObject1;
        if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty.getCreatorIndex(), localObject6))
        {
          paramJsonParser.nextToken();
          try
          {
            localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
            if (localObject2.getClass() != _beanType.getRawClass()) {
              return handlePolymorphic(paramJsonParser, paramDeserializationContext, localObject2, (TokenBuffer)localObject1);
            }
          }
          catch (Exception localException)
          {
            for (;;)
            {
              wrapAndThrow(localException, _beanType.getRawClass(), (String)localObject5, paramDeserializationContext);
              localObject3 = localObject4;
            }
            localObject4 = localObject3;
            if (localObject1 != null) {
              localObject4 = handleUnknownProperties(paramDeserializationContext, localObject3, (TokenBuffer)localObject1);
            }
            return deserialize(paramJsonParser, paramDeserializationContext, localObject4);
          }
        }
      }
      else
      {
        if (!localPropertyValueBuffer.readIdProperty((String)localObject5)) {
          break label211;
        }
        localObject3 = localObject1;
      }
      for (;;)
      {
        localObject5 = paramJsonParser.nextToken();
        localObject1 = localObject3;
        localObject3 = localObject5;
        break;
        label211:
        localObject3 = _beanProperties.find((String)localObject5);
        if (localObject3 != null)
        {
          localPropertyValueBuffer.bufferProperty((SettableBeanProperty)localObject3, ((SettableBeanProperty)localObject3).deserialize(paramJsonParser, paramDeserializationContext));
          localObject3 = localObject1;
        }
        else if ((_ignorableProps != null) && (_ignorableProps.contains(localObject5)))
        {
          handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), (String)localObject5);
          localObject3 = localObject1;
        }
        else if (_anySetter != null)
        {
          localPropertyValueBuffer.bufferAnyProperty(_anySetter, (String)localObject5, _anySetter.deserialize(paramJsonParser, paramDeserializationContext));
          localObject3 = localObject1;
        }
        else
        {
          localObject3 = localObject1;
          if (localObject1 == null) {
            localObject3 = new TokenBuffer(paramJsonParser);
          }
          ((TokenBuffer)localObject3).writeFieldName((String)localObject5);
          ((TokenBuffer)localObject3).copyCurrentStructure(paramJsonParser);
        }
      }
    }
    try
    {
      paramJsonParser = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
      if (localObject1 == null) {
        return paramJsonParser;
      }
      if (paramJsonParser.getClass() != _beanType.getRawClass()) {
        return handlePolymorphic(null, paramDeserializationContext, paramJsonParser, (TokenBuffer)localObject1);
      }
    }
    catch (Exception paramJsonParser)
    {
      for (;;)
      {
        wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
        paramJsonParser = null;
      }
    }
    return handleUnknownProperties(paramDeserializationContext, paramJsonParser, (TokenBuffer)localObject1);
    return paramJsonParser;
  }
  
  protected Object _missingToken(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws JsonProcessingException
  {
    throw paramDeserializationContext.endOfInputException(handledType());
  }
  
  protected BeanDeserializerBase asArrayDeserializer()
  {
    return new BeanAsArrayDeserializer(this, _beanProperties.getPropertiesInInsertionOrder());
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.START_OBJECT)
    {
      if (_vanillaProcessing) {
        return vanillaDeserialize(paramJsonParser, paramDeserializationContext, paramJsonParser.nextToken());
      }
      paramJsonParser.nextToken();
      if (_objectIdReader != null) {
        return deserializeWithObjectId(paramJsonParser, paramDeserializationContext);
      }
      return deserializeFromObject(paramJsonParser, paramDeserializationContext);
    }
    return _deserializeOther(paramJsonParser, paramDeserializationContext, localJsonToken);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (_injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    if (_unwrappedPropertyHandler != null) {
      localObject1 = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext, paramObject);
    }
    do
    {
      return localObject1;
      if (_externalTypeIdHandler != null) {
        return deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, paramObject);
      }
      localObject2 = paramJsonParser.getCurrentToken();
      localObject1 = localObject2;
      if (localObject2 == JsonToken.START_OBJECT) {
        localObject1 = paramJsonParser.nextToken();
      }
      localObject2 = localObject1;
      if (_needViewProcesing)
      {
        Class localClass = paramDeserializationContext.getActiveView();
        localObject2 = localObject1;
        if (localClass != null) {
          return deserializeWithView(paramJsonParser, paramDeserializationContext, paramObject, localClass);
        }
      }
      localObject1 = paramObject;
    } while (localObject2 != JsonToken.FIELD_NAME);
    Object localObject1 = paramJsonParser.getCurrentName();
    paramJsonParser.nextToken();
    Object localObject2 = _beanProperties.find((String)localObject1);
    if (localObject2 != null) {}
    for (;;)
    {
      try
      {
        ((SettableBeanProperty)localObject2).deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
        localObject2 = paramJsonParser.nextToken();
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, (String)localObject1, paramDeserializationContext);
        continue;
      }
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramObject, (String)localObject1);
    }
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_nonStandardCreation) {
      if (_unwrappedPropertyHandler != null) {
        localObject1 = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext);
      }
    }
    Object localObject2;
    do
    {
      return localObject1;
      if (_externalTypeIdHandler != null) {
        return deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext);
      }
      return deserializeFromObjectUsingNonDefault(paramJsonParser, paramDeserializationContext);
      localObject2 = _valueInstantiator.createUsingDefault(paramDeserializationContext);
      if (paramJsonParser.canReadObjectId())
      {
        localObject1 = paramJsonParser.getObjectId();
        if (localObject1 != null) {
          _handleTypedObjectId(paramJsonParser, paramDeserializationContext, localObject2, localObject1);
        }
      }
      if (_injectables != null) {
        injectValues(paramDeserializationContext, localObject2);
      }
      if (_needViewProcesing)
      {
        localObject1 = paramDeserializationContext.getActiveView();
        if (localObject1 != null) {
          return deserializeWithView(paramJsonParser, paramDeserializationContext, localObject2, (Class)localObject1);
        }
      }
      localObject1 = localObject2;
    } while (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT);
    Object localObject1 = paramJsonParser.getCurrentName();
    paramJsonParser.nextToken();
    SettableBeanProperty localSettableBeanProperty = _beanProperties.find((String)localObject1);
    if (localSettableBeanProperty != null) {}
    for (;;)
    {
      try
      {
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject2);
        paramJsonParser.nextToken();
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, localObject2, (String)localObject1, paramDeserializationContext);
        continue;
      }
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, localObject2, (String)localObject1);
    }
  }
  
  protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    ExternalTypeHandler localExternalTypeHandler = _externalTypeIdHandler.start();
    PropertyBasedCreator localPropertyBasedCreator = _propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, _objectIdReader);
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser);
    localTokenBuffer.writeStartObject();
    Object localObject1 = paramJsonParser.getCurrentToken();
    if (localObject1 == JsonToken.FIELD_NAME)
    {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localObject1 = localPropertyBasedCreator.findCreatorProperty(str);
      if (localObject1 != null) {
        if (!localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, localPropertyValueBuffer)) {}
      }
      for (;;)
      {
        localObject1 = paramJsonParser.nextToken();
        break;
        Object localObject2 = ((SettableBeanProperty)localObject1).deserialize(paramJsonParser, paramDeserializationContext);
        if (localPropertyValueBuffer.assignParameter(((SettableBeanProperty)localObject1).getCreatorIndex(), localObject2))
        {
          localObject1 = paramJsonParser.nextToken();
          try
          {
            localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
            while (localObject1 == JsonToken.FIELD_NAME)
            {
              paramJsonParser.nextToken();
              localTokenBuffer.copyCurrentStructure(paramJsonParser);
              localObject1 = paramJsonParser.nextToken();
            }
          }
          catch (Exception localException)
          {
            wrapAndThrow(localException, _beanType.getRawClass(), str, paramDeserializationContext);
          }
          if (localObject2.getClass() != _beanType.getRawClass()) {
            throw paramDeserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
          }
          return localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, localObject2);
          if (!localPropertyValueBuffer.readIdProperty(str))
          {
            SettableBeanProperty localSettableBeanProperty = _beanProperties.find(str);
            if (localSettableBeanProperty != null) {
              localPropertyValueBuffer.bufferProperty(localSettableBeanProperty, localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext));
            } else if (!localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, null)) {
              if ((_ignorableProps != null) && (_ignorableProps.contains(str))) {
                handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), str);
              } else if (_anySetter != null) {
                localPropertyValueBuffer.bufferAnyProperty(_anySetter, str, _anySetter.deserialize(paramJsonParser, paramDeserializationContext));
              }
            }
          }
        }
      }
    }
    try
    {
      paramJsonParser = localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, localPropertyValueBuffer, localPropertyBasedCreator);
      return paramJsonParser;
    }
    catch (Exception paramJsonParser)
    {
      wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
    }
    return null;
  }
  
  protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    PropertyBasedCreator localPropertyBasedCreator = _propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, _objectIdReader);
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser);
    localTokenBuffer.writeStartObject();
    Object localObject1 = paramJsonParser.getCurrentToken();
    Object localObject2;
    if (localObject1 == JsonToken.FIELD_NAME)
    {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localObject1 = localPropertyBasedCreator.findCreatorProperty(str);
      Object localObject3;
      if (localObject1 != null)
      {
        localObject3 = ((SettableBeanProperty)localObject1).deserialize(paramJsonParser, paramDeserializationContext);
        if (localPropertyValueBuffer.assignParameter(((SettableBeanProperty)localObject1).getCreatorIndex(), localObject3))
        {
          localObject1 = paramJsonParser.nextToken();
          try
          {
            localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
            while (localObject1 == JsonToken.FIELD_NAME)
            {
              paramJsonParser.nextToken();
              localTokenBuffer.copyCurrentStructure(paramJsonParser);
              localObject1 = paramJsonParser.nextToken();
              continue;
              localObject2 = paramJsonParser.nextToken();
            }
          }
          catch (Exception localException)
          {
            wrapAndThrow(localException, _beanType.getRawClass(), str, paramDeserializationContext);
          }
        }
      }
      for (;;)
      {
        break;
        localTokenBuffer.writeEndObject();
        if (localObject3.getClass() != _beanType.getRawClass())
        {
          localTokenBuffer.close();
          throw paramDeserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
        }
        return _unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject3, localTokenBuffer);
        if (!localPropertyValueBuffer.readIdProperty(str))
        {
          localObject2 = _beanProperties.find(str);
          if (localObject2 != null)
          {
            localPropertyValueBuffer.bufferProperty((SettableBeanProperty)localObject2, ((SettableBeanProperty)localObject2).deserialize(paramJsonParser, paramDeserializationContext));
          }
          else if ((_ignorableProps != null) && (_ignorableProps.contains(str)))
          {
            handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), str);
          }
          else
          {
            localTokenBuffer.writeFieldName(str);
            localTokenBuffer.copyCurrentStructure(paramJsonParser);
            if (_anySetter != null) {
              localPropertyValueBuffer.bufferAnyProperty(_anySetter, str, _anySetter.deserialize(paramJsonParser, paramDeserializationContext));
            }
          }
        }
      }
    }
    try
    {
      localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
      return _unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject2, localTokenBuffer);
    }
    catch (Exception paramJsonParser)
    {
      wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
    }
    return null;
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_propertyBasedCreator != null) {
      return deserializeUsingPropertyBasedWithExternalTypeId(paramJsonParser, paramDeserializationContext);
    }
    return deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, _valueInstantiator.createUsingDefault(paramDeserializationContext));
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    Class localClass;
    ExternalTypeHandler localExternalTypeHandler;
    label22:
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (_needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      localExternalTypeHandler = _externalTypeIdHandler.start();
      if (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT) {
        break label233;
      }
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localSettableBeanProperty = _beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label140;
      }
      if (paramJsonParser.getCurrentToken().isScalarValue()) {
        localExternalTypeHandler.handleTypePropertyValue(paramJsonParser, paramDeserializationContext, str, paramObject);
      }
      if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
        break label114;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      paramJsonParser.nextToken();
      break label22;
      localClass = null;
      break;
      try
      {
        label114:
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException1)
      {
        wrapAndThrow(localException1, paramObject, str, paramDeserializationContext);
      }
      continue;
      label140:
      if ((_ignorableProps != null) && (_ignorableProps.contains(str))) {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
      } else if (!localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, paramObject)) {
        if (_anySetter != null) {
          try
          {
            _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str);
          }
          catch (Exception localException2)
          {
            wrapAndThrow(localException2, paramObject, str, paramDeserializationContext);
          }
        } else {
          handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
        }
      }
    }
    label233:
    return localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_delegateDeserializer != null) {
      return _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (_propertyBasedCreator != null) {
      return deserializeUsingPropertyBasedWithUnwrapped(paramJsonParser, paramDeserializationContext);
    }
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser);
    localTokenBuffer.writeStartObject();
    Object localObject = _valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (_injectables != null) {
      injectValues(paramDeserializationContext, localObject);
    }
    Class localClass;
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (_needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      if (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT) {
        break label270;
      }
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localSettableBeanProperty = _beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label186;
      }
      if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
        break label158;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      paramJsonParser.nextToken();
      break;
      localClass = null;
      break;
      try
      {
        label158:
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject);
      }
      catch (Exception localException1)
      {
        wrapAndThrow(localException1, localObject, str, paramDeserializationContext);
      }
      continue;
      label186:
      if ((_ignorableProps != null) && (_ignorableProps.contains(str)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, localObject, str);
      }
      else
      {
        localTokenBuffer.writeFieldName(str);
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
        if (_anySetter != null) {
          try
          {
            _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject, str);
          }
          catch (Exception localException2)
          {
            wrapAndThrow(localException2, localObject, str, paramDeserializationContext);
          }
        }
      }
    }
    label270:
    localTokenBuffer.writeEndObject();
    _unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject, localTokenBuffer);
    return localObject;
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = localObject2;
    if (localObject2 == JsonToken.START_OBJECT) {
      localObject1 = paramJsonParser.nextToken();
    }
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser);
    localTokenBuffer.writeStartObject();
    SettableBeanProperty localSettableBeanProperty;
    if (_needViewProcesing)
    {
      localObject2 = paramDeserializationContext.getActiveView();
      if (localObject1 != JsonToken.FIELD_NAME) {
        break label214;
      }
      localObject1 = paramJsonParser.getCurrentName();
      localSettableBeanProperty = _beanProperties.find((String)localObject1);
      paramJsonParser.nextToken();
      if (localSettableBeanProperty == null) {
        break label148;
      }
      if ((localObject2 == null) || (localSettableBeanProperty.visibleInView((Class)localObject2))) {
        break label122;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      localObject1 = paramJsonParser.nextToken();
      break;
      localObject2 = null;
      break;
      try
      {
        label122:
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, (String)localObject1, paramDeserializationContext);
      }
      continue;
      label148:
      if ((_ignorableProps != null) && (_ignorableProps.contains(localObject1)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, (String)localObject1);
      }
      else
      {
        localTokenBuffer.writeFieldName((String)localObject1);
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
        if (_anySetter != null) {
          _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, (String)localObject1);
        }
      }
    }
    label214:
    localTokenBuffer.writeEndObject();
    _unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, paramObject, localTokenBuffer);
    return paramObject;
  }
  
  protected final Object deserializeWithView(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, Class<?> paramClass)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    if (localObject == JsonToken.FIELD_NAME)
    {
      localObject = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty = _beanProperties.find((String)localObject);
      if (localSettableBeanProperty != null) {
        if (!localSettableBeanProperty.visibleInView(paramClass)) {
          paramJsonParser.skipChildren();
        }
      }
      for (;;)
      {
        localObject = paramJsonParser.nextToken();
        break;
        try
        {
          localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramObject, (String)localObject, paramDeserializationContext);
        }
        continue;
        handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramObject, (String)localObject);
      }
    }
    return paramObject;
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    if (getClass() != BeanDeserializer.class) {
      return this;
    }
    return new BeanDeserializer(this, paramNameTransformer);
  }
  
  public BeanDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BeanDeserializer(this, paramHashSet);
  }
  
  public BeanDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BeanDeserializer(this, paramObjectIdReader);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.BeanDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */