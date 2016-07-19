package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.HashSet;

public class ThrowableDeserializer
  extends BeanDeserializer
{
  protected static final String PROP_NAME_MESSAGE = "message";
  private static final long serialVersionUID = 1L;
  
  public ThrowableDeserializer(BeanDeserializer paramBeanDeserializer)
  {
    super(paramBeanDeserializer);
    _vanillaProcessing = false;
  }
  
  protected ThrowableDeserializer(BeanDeserializer paramBeanDeserializer, NameTransformer paramNameTransformer)
  {
    super(paramBeanDeserializer, paramNameTransformer);
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    int k = 0;
    if (_propertyBasedCreator != null) {
      paramJsonParser = _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext);
    }
    boolean bool1;
    int i;
    Object localObject2;
    Object localObject1;
    int j;
    do
    {
      return paramJsonParser;
      if (_delegateDeserializer != null) {
        return _valueInstantiator.createUsingDelegate(paramDeserializationContext, _delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      }
      if (_beanType.isAbstract()) {
        throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + _beanType + " (need to add/enable type information?)");
      }
      bool1 = _valueInstantiator.canCreateFromString();
      boolean bool2 = _valueInstantiator.canCreateUsingDefault();
      if ((!bool1) && (!bool2)) {
        throw new JsonMappingException("Can not deserialize Throwable of type " + _beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
      }
      i = 0;
      localObject2 = null;
      localObject1 = null;
      if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
      {
        Object localObject3 = paramJsonParser.getCurrentName();
        SettableBeanProperty localSettableBeanProperty = _beanProperties.find((String)localObject3);
        paramJsonParser.nextToken();
        if (localSettableBeanProperty != null) {
          if (localObject1 != null)
          {
            localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject1);
            localObject3 = localObject2;
            localObject2 = localObject1;
            localObject1 = localObject3;
          }
        }
        for (;;)
        {
          paramJsonParser.nextToken();
          localObject3 = localObject2;
          localObject2 = localObject1;
          localObject1 = localObject3;
          break;
          localObject3 = localObject2;
          if (localObject2 == null)
          {
            j = _beanProperties.size();
            localObject3 = new Object[j + j];
          }
          j = i + 1;
          localObject3[i] = localSettableBeanProperty;
          i = j + 1;
          localObject3[j] = localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
          localObject2 = localObject1;
          localObject1 = localObject3;
          continue;
          if (("message".equals(localObject3)) && (bool1))
          {
            localObject3 = _valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
            localObject1 = localObject3;
            if (localObject2 != null)
            {
              j = 0;
              while (j < i)
              {
                ((SettableBeanProperty)localObject2[j]).set(localObject3, localObject2[(j + 1)]);
                j += 2;
              }
              localObject2 = localObject3;
              localObject1 = null;
            }
          }
          else
          {
            if ((_ignorableProps != null) && (_ignorableProps.contains(localObject3)))
            {
              paramJsonParser.skipChildren();
              localObject3 = localObject1;
              localObject1 = localObject2;
              localObject2 = localObject3;
              continue;
            }
            if (_anySetter != null)
            {
              _anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject1, (String)localObject3);
              localObject3 = localObject1;
              localObject1 = localObject2;
              localObject2 = localObject3;
              continue;
            }
            handleUnknownProperty(paramJsonParser, paramDeserializationContext, localObject1, (String)localObject3);
          }
          localObject3 = localObject1;
          localObject1 = localObject2;
          localObject2 = localObject3;
        }
      }
      paramJsonParser = (JsonParser)localObject1;
    } while (localObject1 != null);
    if (bool1) {}
    for (paramDeserializationContext = _valueInstantiator.createFromString(paramDeserializationContext, null);; paramDeserializationContext = _valueInstantiator.createUsingDefault(paramDeserializationContext))
    {
      paramJsonParser = paramDeserializationContext;
      if (localObject2 == null) {
        break;
      }
      j = k;
      for (;;)
      {
        paramJsonParser = paramDeserializationContext;
        if (j >= i) {
          break;
        }
        ((SettableBeanProperty)localObject2[j]).set(paramDeserializationContext, localObject2[(j + 1)]);
        j += 2;
      }
    }
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    if (getClass() != ThrowableDeserializer.class) {
      return this;
    }
    return new ThrowableDeserializer(this, paramNameTransformer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */