package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer
  extends StdScalarDeserializer<Enum<?>>
{
  private static final long serialVersionUID = -5893263645879532318L;
  protected final EnumResolver<?> _resolver;
  
  public EnumDeserializer(EnumResolver<?> paramEnumResolver)
  {
    super(Enum.class);
    _resolver = paramEnumResolver;
  }
  
  public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass, AnnotatedMethod paramAnnotatedMethod)
  {
    Class localClass = paramAnnotatedMethod.getRawParameterType(0);
    if (localClass == String.class) {
      localClass = null;
    }
    for (;;)
    {
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(paramAnnotatedMethod.getMember());
      }
      return new FactoryBasedDeserializer(paramClass, paramAnnotatedMethod, localClass);
      if ((localClass == Integer.TYPE) || (localClass == Integer.class))
      {
        localClass = Integer.class;
      }
      else
      {
        if ((localClass != Long.TYPE) && (localClass != Long.class)) {
          break;
        }
        localClass = Long.class;
      }
    }
    throw new IllegalArgumentException("Parameter #0 type for factory method (" + paramAnnotatedMethod + ") not suitable, must be java.lang.String or int/Integer/long/Long");
  }
  
  public Enum<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    String str;
    if ((localObject == JsonToken.VALUE_STRING) || (localObject == JsonToken.FIELD_NAME))
    {
      str = paramJsonParser.getText();
      localObject = _resolver.findEnum(str);
      paramJsonParser = (JsonParser)localObject;
      if (localObject == null)
      {
        if ((!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) || ((str.length() != 0) && (str.trim().length() != 0))) {
          break label80;
        }
        paramJsonParser = null;
      }
    }
    label80:
    int i;
    do
    {
      do
      {
        do
        {
          return paramJsonParser;
          paramJsonParser = (JsonParser)localObject;
        } while (paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL));
        throw paramDeserializationContext.weirdStringException(str, _resolver.getEnumClass(), "value not one of declared Enum instance names: " + _resolver.getEnums());
        if (localObject != JsonToken.VALUE_NUMBER_INT) {
          break;
        }
        if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
          throw paramDeserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        }
        i = paramJsonParser.getIntValue();
        localObject = _resolver.getEnum(i);
        paramJsonParser = (JsonParser)localObject;
      } while (localObject != null);
      paramJsonParser = (JsonParser)localObject;
    } while (paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL));
    throw paramDeserializationContext.weirdNumberException(Integer.valueOf(i), _resolver.getEnumClass(), "index value outside legal index range [0.." + _resolver.lastValidIndex() + "]");
    throw paramDeserializationContext.mappingException(_resolver.getEnumClass());
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  protected static class FactoryBasedDeserializer
    extends StdScalarDeserializer<Object>
  {
    private static final long serialVersionUID = -7775129435872564122L;
    protected final Class<?> _enumClass;
    protected final Method _factory;
    protected final Class<?> _inputType;
    
    public FactoryBasedDeserializer(Class<?> paramClass1, AnnotatedMethod paramAnnotatedMethod, Class<?> paramClass2)
    {
      super();
      _enumClass = paramClass1;
      _factory = paramAnnotatedMethod.getAnnotated();
      _inputType = paramClass2;
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if (_inputType == null) {
        paramJsonParser = paramJsonParser.getText();
      }
      for (;;)
      {
        try
        {
          paramJsonParser = _factory.invoke(_enumClass, new Object[] { paramJsonParser });
          return paramJsonParser;
        }
        catch (Exception paramJsonParser)
        {
          paramJsonParser = ClassUtil.getRootCause(paramJsonParser);
          if (!(paramJsonParser instanceof IOException)) {
            continue;
          }
          throw ((IOException)paramJsonParser);
          throw paramDeserializationContext.instantiationException(_enumClass, paramJsonParser);
        }
        if (_inputType == Integer.class)
        {
          paramJsonParser = Integer.valueOf(paramJsonParser.getValueAsInt());
        }
        else
        {
          if (_inputType != Long.class) {
            continue;
          }
          paramJsonParser = Long.valueOf(paramJsonParser.getValueAsLong());
        }
      }
      throw paramDeserializationContext.mappingException(_enumClass);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.EnumDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */