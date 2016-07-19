package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;

public class BuilderBasedDeserializer
  extends BeanDeserializerBase
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedMethod _buildMethod;
  
  public BuilderBasedDeserializer(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, BeanPropertyMap paramBeanPropertyMap, Map<String, SettableBeanProperty> paramMap, HashSet<String> paramHashSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramBeanDeserializerBuilder, paramBeanDescription, paramBeanPropertyMap, paramMap, paramHashSet, paramBoolean1, paramBoolean2);
    _buildMethod = paramBeanDeserializerBuilder.getBuildMethod();
    if (_objectIdReader != null) {
      throw new IllegalArgumentException("Can not use Object Id with Builder-based deserialization (type " + paramBeanDescription.getType() + ")");
    }
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer)
  {
    this(paramBuilderBasedDeserializer, _ignoreAllUnknown);
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, ObjectIdReader paramObjectIdReader)
  {
    super(paramBuilderBasedDeserializer, paramObjectIdReader);
    _buildMethod = _buildMethod;
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, NameTransformer paramNameTransformer)
  {
    super(paramBuilderBasedDeserializer, paramNameTransformer);
    _buildMethod = _buildMethod;
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, HashSet<String> paramHashSet)
  {
    super(paramBuilderBasedDeserializer, paramHashSet);
    _buildMethod = _buildMethod;
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, boolean paramBoolean)
  {
    super(paramBuilderBasedDeserializer, paramBoolean);
    _buildMethod = _buildMethod;
  }
  
  private final Object vanillaDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonToken paramJsonToken)
    throws IOException, JsonProcessingException
  {
    paramJsonToken = _valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
    {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      Object localObject = _beanProperties.find(str);
      if (localObject != null) {}
      for (;;)
      {
        try
        {
          localObject = ((SettableBeanProperty)localObject).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramJsonToken);
          paramJsonToken = (JsonToken)localObject;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramJsonToken, str, paramDeserializationContext);
          continue;
        }
        paramJsonParser.nextToken();
        break;
        handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramJsonToken, str);
      }
    }
    return paramJsonToken;
  }
  
  protected final Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (_injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    if (_unwrappedPropertyHandler != null) {
      paramObject = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext, paramObject);
    }
    Object localObject1;
    do
    {
      return paramObject;
      if (_externalTypeIdHandler != null) {
        return deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, paramObject);
      }
      if (_needViewProcesing)
      {
        localObject1 = paramDeserializationContext.getActiveView();
        if (localObject1 != null) {
          return deserializeWithView(paramJsonParser, paramDeserializationContext, paramObject, (Class)localObject1);
        }
      }
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      localObject2 = localJsonToken;
      localObject1 = paramObject;
      if (localJsonToken == JsonToken.START_OBJECT)
      {
        localObject2 = paramJsonParser.nextToken();
        localObject1 = paramObject;
      }
      paramObject = localObject1;
    } while (localObject2 != JsonToken.FIELD_NAME);
    Object localObject2 = paramJsonParser.getCurrentName();
    paramJsonParser.nextToken();
    paramObject = _beanProperties.find((String)localObject2);
    if (paramObject != null) {}
    for (;;)
    {
      try
      {
        paramObject = ((SettableBeanProperty)paramObject).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
        localObject1 = paramObject;
      }
      catch (Exception paramObject)
      {
        wrapAndThrow((Throwable)paramObject, localObject1, (String)localObject2, paramDeserializationContext);
        continue;
      }
      localObject2 = paramJsonParser.nextToken();
      break;
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, handledType(), (String)localObject2);
    }
  }
  
  protected final Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    PropertyBasedCreator localPropertyBasedCreator = _propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, _objectIdReader);
    Object localObject2 = paramJsonParser.getCurrentToken();
    Object localObject1 = null;
    Object localObject4;
    Object localObject3;
    for (;;)
    {
      if (localObject2 == JsonToken.FIELD_NAME)
      {
        localObject4 = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        SettableBeanProperty localSettableBeanProperty = localPropertyBasedCreator.findCreatorProperty((String)localObject4);
        if (localSettableBeanProperty != null)
        {
          Object localObject5 = localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
          localObject2 = localObject1;
          if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty.getCreatorIndex(), localObject5))
          {
            paramJsonParser.nextToken();
            try
            {
              localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
              if (localObject2.getClass() == _beanType.getRawClass()) {
                break label164;
              }
              return handlePolymorphic(paramJsonParser, paramDeserializationContext, localObject2, (TokenBuffer)localObject1);
            }
            catch (Exception localException)
            {
              wrapAndThrow(localException, _beanType.getRawClass(), (String)localObject4, paramDeserializationContext);
              localObject3 = localObject1;
            }
          }
          else
          {
            localObject4 = paramJsonParser.nextToken();
            localObject1 = localObject3;
            localObject3 = localObject4;
            continue;
          }
          label164:
          if (localObject1 == null) {
            break label392;
          }
        }
      }
    }
    label390:
    label392:
    for (localObject1 = handleUnknownProperties(paramDeserializationContext, localObject3, (TokenBuffer)localObject1);; localObject1 = localObject3)
    {
      return _deserialize(paramJsonParser, paramDeserializationContext, localObject1);
      localObject3 = localObject1;
      if (localPropertyValueBuffer.readIdProperty((String)localObject4)) {
        break;
      }
      localObject3 = _beanProperties.find((String)localObject4);
      if (localObject3 != null)
      {
        localPropertyValueBuffer.bufferProperty((SettableBeanProperty)localObject3, ((SettableBeanProperty)localObject3).deserialize(paramJsonParser, paramDeserializationContext));
        localObject3 = localObject1;
        break;
      }
      if ((_ignorableProps != null) && (_ignorableProps.contains(localObject4)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), (String)localObject4);
        localObject3 = localObject1;
        break;
      }
      if (_anySetter != null)
      {
        localPropertyValueBuffer.bufferAnyProperty(_anySetter, (String)localObject4, _anySetter.deserialize(paramJsonParser, paramDeserializationContext));
        localObject3 = localObject1;
        break;
      }
      localObject3 = localObject1;
      if (localObject1 == null) {
        localObject3 = new TokenBuffer(paramJsonParser);
      }
      ((TokenBuffer)localObject3).writeFieldName((String)localObject4);
      ((TokenBuffer)localObject3).copyCurrentStructure(paramJsonParser);
      break;
      try
      {
        paramJsonParser = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        if (localObject1 == null) {
          break label390;
        }
        if (paramJsonParser.getClass() != _beanType.getRawClass()) {
          return handlePolymorphic(null, paramDeserializationContext, paramJsonParser, (TokenBuffer)localObject1);
        }
      }
      catch (Exception paramJsonParser)
      {
        wrapInstantiationProblem(paramJsonParser, paramDeserializationContext);
        return null;
      }
      return handleUnknownProperties(paramDeserializationContext, paramJsonParser, (TokenBuffer)localObject1);
      return paramJsonParser;
    }
  }
  
  protected BeanAsArrayBuilderDeserializer asArrayDeserializer()
  {
    return new BeanAsArrayBuilderDeserializer(this, _beanProperties.getPropertiesInInsertionOrder(), _buildMethod);
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.START_OBJECT)
    {
      localJsonToken = paramJsonParser.nextToken();
      if (_vanillaProcessing) {
        return finishBuild(paramDeserializationContext, vanillaDeserialize(paramJsonParser, paramDeserializationContext, localJsonToken));
      }
      return finishBuild(paramDeserializationContext, deserializeFromObject(paramJsonParser, paramDeserializationContext));
    }
    switch (localJsonToken)
    {
    default: 
      throw paramDeserializationContext.mappingException(handledType());
    case ???: 
      return finishBuild(paramDeserializationContext, deserializeFromString(paramJsonParser, paramDeserializationContext));
    case ???: 
      return finishBuild(paramDeserializationContext, deserializeFromNumber(paramJsonParser, paramDeserializationContext));
    case ???: 
      return finishBuild(paramDeserializationContext, deserializeFromDouble(paramJsonParser, paramDeserializationContext));
    case ???: 
      return paramJsonParser.getEmbeddedObject();
    case ???: 
    case ???: 
      return finishBuild(paramDeserializationContext, deserializeFromBoolean(paramJsonParser, paramDeserializationContext));
    case ???: 
      return finishBuild(paramDeserializationContext, deserializeFromArray(paramJsonParser, paramDeserializationContext));
    }
    return finishBuild(paramDeserializationContext, deserializeFromObject(paramJsonParser, paramDeserializationContext));
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    return finishBuild(paramDeserializationContext, _deserialize(paramJsonParser, paramDeserializationContext, paramObject));
  }
  
  /* Error */
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 354	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_nonStandardCreation	Z
    //   4: ifeq +42 -> 46
    //   7: aload_0
    //   8: getfield 160	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_unwrappedPropertyHandler	Lcom/fasterxml/jackson/databind/deser/impl/UnwrappedPropertyHandler;
    //   11: ifnull +14 -> 25
    //   14: aload_0
    //   15: aload_1
    //   16: aload_2
    //   17: invokevirtual 356	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:deserializeWithUnwrapped	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   20: astore 4
    //   22: aload 4
    //   24: areturn
    //   25: aload_0
    //   26: getfield 167	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_externalTypeIdHandler	Lcom/fasterxml/jackson/databind/deser/impl/ExternalTypeHandler;
    //   29: ifnull +10 -> 39
    //   32: aload_0
    //   33: aload_1
    //   34: aload_2
    //   35: invokevirtual 358	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:deserializeWithExternalTypeId	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   38: areturn
    //   39: aload_0
    //   40: aload_1
    //   41: aload_2
    //   42: invokevirtual 361	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:deserializeFromObjectUsingNonDefault	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   45: areturn
    //   46: aload_0
    //   47: getfield 98	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_valueInstantiator	Lcom/fasterxml/jackson/databind/deser/ValueInstantiator;
    //   50: aload_2
    //   51: invokevirtual 104	com/fasterxml/jackson/databind/deser/ValueInstantiator:createUsingDefault	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   54: astore 4
    //   56: aload_0
    //   57: getfield 152	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_injectables	[Lcom/fasterxml/jackson/databind/deser/impl/ValueInjector;
    //   60: ifnull +10 -> 70
    //   63: aload_0
    //   64: aload_2
    //   65: aload 4
    //   67: invokevirtual 156	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:injectValues	(Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;)V
    //   70: aload 4
    //   72: astore_3
    //   73: aload_0
    //   74: getfield 173	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_needViewProcesing	Z
    //   77: ifeq +45 -> 122
    //   80: aload_2
    //   81: invokevirtual 179	com/fasterxml/jackson/databind/DeserializationContext:getActiveView	()Ljava/lang/Class;
    //   84: astore 5
    //   86: aload 4
    //   88: astore_3
    //   89: aload 5
    //   91: ifnull +31 -> 122
    //   94: aload_0
    //   95: aload_1
    //   96: aload_2
    //   97: aload 4
    //   99: aload 5
    //   101: invokevirtual 183	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:deserializeWithView	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
    //   104: areturn
    //   105: astore 4
    //   107: aload_0
    //   108: aload 4
    //   110: aload_3
    //   111: aload 5
    //   113: aload_2
    //   114: invokevirtual 142	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:wrapAndThrow	(Ljava/lang/Throwable;Ljava/lang/Object;Ljava/lang/String;Lcom/fasterxml/jackson/databind/DeserializationContext;)V
    //   117: aload_1
    //   118: invokevirtual 122	com/fasterxml/jackson/core/JsonParser:nextToken	()Lcom/fasterxml/jackson/core/JsonToken;
    //   121: pop
    //   122: aload_3
    //   123: astore 4
    //   125: aload_1
    //   126: invokevirtual 110	com/fasterxml/jackson/core/JsonParser:getCurrentToken	()Lcom/fasterxml/jackson/core/JsonToken;
    //   129: getstatic 116	com/fasterxml/jackson/core/JsonToken:END_OBJECT	Lcom/fasterxml/jackson/core/JsonToken;
    //   132: if_acmpeq -110 -> 22
    //   135: aload_1
    //   136: invokevirtual 119	com/fasterxml/jackson/core/JsonParser:getCurrentName	()Ljava/lang/String;
    //   139: astore 5
    //   141: aload_1
    //   142: invokevirtual 122	com/fasterxml/jackson/core/JsonParser:nextToken	()Lcom/fasterxml/jackson/core/JsonToken;
    //   145: pop
    //   146: aload_0
    //   147: getfield 126	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:_beanProperties	Lcom/fasterxml/jackson/databind/deser/impl/BeanPropertyMap;
    //   150: aload 5
    //   152: invokevirtual 132	com/fasterxml/jackson/databind/deser/impl/BeanPropertyMap:find	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;
    //   155: astore 4
    //   157: aload 4
    //   159: ifnull +19 -> 178
    //   162: aload 4
    //   164: aload_1
    //   165: aload_2
    //   166: aload_3
    //   167: invokevirtual 138	com/fasterxml/jackson/databind/deser/SettableBeanProperty:deserializeSetAndReturn	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;)Ljava/lang/Object;
    //   170: astore 4
    //   172: aload 4
    //   174: astore_3
    //   175: goto -58 -> 117
    //   178: aload_0
    //   179: aload_1
    //   180: aload_2
    //   181: aload_3
    //   182: aload 5
    //   184: invokevirtual 146	com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer:handleUnknownVanilla	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;Ljava/lang/String;)V
    //   187: goto -70 -> 117
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	BuilderBasedDeserializer
    //   0	190	1	paramJsonParser	JsonParser
    //   0	190	2	paramDeserializationContext	DeserializationContext
    //   72	110	3	localObject1	Object
    //   20	78	4	localObject2	Object
    //   105	4	4	localException	Exception
    //   123	50	4	localObject3	Object
    //   84	99	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   162	172	105	java/lang/Exception
  }
  
  protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
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
        if (localObject3.getClass() != _beanType.getRawClass()) {
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
    Object localObject1;
    if (_needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      localExternalTypeHandler = _externalTypeIdHandler.start();
      if (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT) {
        break label238;
      }
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localObject1 = _beanProperties.find(str);
      if (localObject1 == null) {
        break label130;
      }
      if ((localClass == null) || (((SettableBeanProperty)localObject1).visibleInView(localClass))) {
        break label99;
      }
      paramJsonParser.skipChildren();
      localObject1 = paramObject;
    }
    for (;;)
    {
      paramJsonParser.nextToken();
      paramObject = localObject1;
      break label22;
      localClass = null;
      break;
      label99:
      Object localObject2;
      try
      {
        localObject1 = ((SettableBeanProperty)localObject1).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException1)
      {
        wrapAndThrow(localException1, paramObject, str, paramDeserializationContext);
        localObject2 = paramObject;
      }
      continue;
      label130:
      if ((_ignorableProps != null) && (_ignorableProps.contains(str)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
        localObject2 = paramObject;
      }
      else
      {
        localObject2 = paramObject;
        if (!localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, paramObject))
        {
          Object localObject3;
          if (_anySetter != null)
          {
            try
            {
              _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str);
              localObject2 = paramObject;
            }
            catch (Exception localException2)
            {
              wrapAndThrow(localException2, paramObject, str, paramDeserializationContext);
              localObject3 = paramObject;
            }
          }
          else
          {
            handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
            localObject3 = paramObject;
          }
        }
      }
    }
    label238:
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
    Object localObject1 = _valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (_injectables != null) {
      injectValues(paramDeserializationContext, localObject1);
    }
    Class localClass;
    String str;
    Object localObject2;
    if (_needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      if (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT) {
        break label300;
      }
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localObject2 = _beanProperties.find(str);
      if (localObject2 == null) {
        break label200;
      }
      if ((localClass == null) || (((SettableBeanProperty)localObject2).visibleInView(localClass))) {
        break label166;
      }
      paramJsonParser.skipChildren();
      localObject2 = localObject1;
    }
    for (;;)
    {
      paramJsonParser.nextToken();
      localObject1 = localObject2;
      break;
      localClass = null;
      break;
      label166:
      Object localObject3;
      try
      {
        localObject2 = ((SettableBeanProperty)localObject2).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
      }
      catch (Exception localException1)
      {
        wrapAndThrow(localException1, localObject1, str, paramDeserializationContext);
        localObject3 = localObject1;
      }
      continue;
      label200:
      if ((_ignorableProps != null) && (_ignorableProps.contains(str)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, localObject1, str);
        localObject3 = localObject1;
      }
      else
      {
        localTokenBuffer.writeFieldName(str);
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
        localObject3 = localObject1;
        if (_anySetter != null) {
          try
          {
            _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject1, str);
            localObject3 = localObject1;
          }
          catch (Exception localException2)
          {
            wrapAndThrow(localException2, localObject1, str, paramDeserializationContext);
            Object localObject4 = localObject1;
          }
        }
      }
    }
    label300:
    localTokenBuffer.writeEndObject();
    _unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject1, localTokenBuffer);
    return localObject1;
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
    Object localObject3;
    if (_needViewProcesing)
    {
      localObject2 = paramDeserializationContext.getActiveView();
      if (localObject1 != JsonToken.FIELD_NAME) {
        break label234;
      }
      localObject1 = paramJsonParser.getCurrentName();
      localObject3 = _beanProperties.find((String)localObject1);
      paramJsonParser.nextToken();
      if (localObject3 == null) {
        break label159;
      }
      if ((localObject2 == null) || (((SettableBeanProperty)localObject3).visibleInView((Class)localObject2))) {
        break label128;
      }
      paramJsonParser.skipChildren();
      localObject3 = paramObject;
    }
    for (;;)
    {
      localObject1 = paramJsonParser.nextToken();
      paramObject = localObject3;
      break;
      localObject2 = null;
      break;
      label128:
      Object localObject4;
      try
      {
        localObject3 = ((SettableBeanProperty)localObject3).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, (String)localObject1, paramDeserializationContext);
        localObject4 = paramObject;
      }
      continue;
      label159:
      if ((_ignorableProps != null) && (_ignorableProps.contains(localObject1)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, (String)localObject1);
        localObject4 = paramObject;
      }
      else
      {
        localTokenBuffer.writeFieldName((String)localObject1);
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
        localObject4 = paramObject;
        if (_anySetter != null)
        {
          _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, (String)localObject1);
          localObject4 = paramObject;
        }
      }
    }
    label234:
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
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localObject = _beanProperties.find(str);
      if (localObject != null) {
        if (!((SettableBeanProperty)localObject).visibleInView(paramClass)) {
          paramJsonParser.skipChildren();
        }
      }
      for (;;)
      {
        localObject = paramJsonParser.nextToken();
        break;
        try
        {
          localObject = ((SettableBeanProperty)localObject).deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
          paramObject = localObject;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
        }
        continue;
        handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramObject, str);
      }
    }
    return paramObject;
  }
  
  protected final Object finishBuild(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    try
    {
      paramObject = _buildMethod.getMember().invoke(paramObject, new Object[0]);
      return paramObject;
    }
    catch (Exception paramObject)
    {
      wrapInstantiationProblem((Throwable)paramObject, paramDeserializationContext);
    }
    return null;
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    return new BuilderBasedDeserializer(this, paramNameTransformer);
  }
  
  public BuilderBasedDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BuilderBasedDeserializer(this, paramHashSet);
  }
  
  public BuilderBasedDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BuilderBasedDeserializer(this, paramObjectIdReader);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */