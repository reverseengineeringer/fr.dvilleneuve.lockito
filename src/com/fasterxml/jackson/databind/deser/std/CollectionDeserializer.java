package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer
  extends ContainerDeserializerBase<Collection<Object>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = -2003828398549708958L;
  protected final JavaType _collectionType;
  protected final JsonDeserializer<Object> _delegateDeserializer;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator)
  {
    this(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramValueInstantiator, null);
  }
  
  protected CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer1, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator, JsonDeserializer<Object> paramJsonDeserializer2)
  {
    super(paramJavaType);
    _collectionType = paramJavaType;
    _valueDeserializer = paramJsonDeserializer1;
    _valueTypeDeserializer = paramTypeDeserializer;
    _valueInstantiator = paramValueInstantiator;
    _delegateDeserializer = paramJsonDeserializer2;
  }
  
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer)
  {
    super(_collectionType);
    _collectionType = _collectionType;
    _valueDeserializer = _valueDeserializer;
    _valueTypeDeserializer = _valueTypeDeserializer;
    _valueInstantiator = _valueInstantiator;
    _delegateDeserializer = _delegateDeserializer;
  }
  
  public CollectionDeserializer createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (_valueInstantiator != null)
    {
      localObject1 = localObject2;
      if (_valueInstantiator.canCreateUsingDelegate())
      {
        localObject1 = _valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
        if (localObject1 == null) {
          throw new IllegalArgumentException("Invalid delegate-creator definition for " + _collectionType + ": value instantiator (" + _valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
        }
        localObject1 = findDeserializer(paramDeserializationContext, (JavaType)localObject1, paramBeanProperty);
      }
    }
    localObject2 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, _valueDeserializer);
    if (localObject2 == null) {}
    for (paramDeserializationContext = paramDeserializationContext.findContextualValueDeserializer(_collectionType.getContentType(), paramBeanProperty);; paramDeserializationContext = paramDeserializationContext.handleSecondaryContextualization((JsonDeserializer)localObject2, paramBeanProperty))
    {
      TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
      localObject2 = localTypeDeserializer;
      if (localTypeDeserializer != null) {
        localObject2 = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withResolved((JsonDeserializer)localObject1, paramDeserializationContext, (TypeDeserializer)localObject2);
    }
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (_delegateDeserializer != null) {
      return (Collection)_valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
    {
      String str = paramJsonParser.getText();
      if (str.length() == 0) {
        return (Collection)_valueInstantiator.createFromString(paramDeserializationContext, str);
      }
    }
    return deserialize(paramJsonParser, paramDeserializationContext, (Collection)_valueInstantiator.createUsingDefault(paramDeserializationContext));
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException, JsonProcessingException
  {
    Object localObject;
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      localObject = handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection);
    }
    JsonDeserializer localJsonDeserializer;
    TypeDeserializer localTypeDeserializer;
    JsonToken localJsonToken;
    do
    {
      return (Collection<Object>)localObject;
      localJsonDeserializer = _valueDeserializer;
      localTypeDeserializer = _valueTypeDeserializer;
      localJsonToken = paramJsonParser.nextToken();
      localObject = paramCollection;
    } while (localJsonToken == JsonToken.END_ARRAY);
    if (localJsonToken == JsonToken.VALUE_NULL) {
      localObject = null;
    }
    for (;;)
    {
      paramCollection.add(localObject);
      break;
      if (localTypeDeserializer == null) {
        localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      } else {
        localObject = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return _valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return _collectionType.getContentType();
  }
  
  protected final Collection<Object> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException, JsonProcessingException
  {
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(_collectionType.getRawClass());
    }
    JsonDeserializer localJsonDeserializer = _valueDeserializer;
    TypeDeserializer localTypeDeserializer = _valueTypeDeserializer;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      paramJsonParser = null;
    }
    for (;;)
    {
      paramCollection.add(paramJsonParser);
      return paramCollection;
      if (localTypeDeserializer == null) {
        paramJsonParser = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      } else {
        paramJsonParser = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      }
    }
  }
  
  protected CollectionDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer)
  {
    if ((paramJsonDeserializer1 == _delegateDeserializer) && (paramJsonDeserializer2 == _valueDeserializer) && (paramTypeDeserializer == _valueTypeDeserializer)) {
      return this;
    }
    return new CollectionDeserializer(_collectionType, paramJsonDeserializer2, paramTypeDeserializer, _valueInstantiator, paramJsonDeserializer1);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.CollectionDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */