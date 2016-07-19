package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer$FactoryBasedDeserializer
  extends StdScalarDeserializer<Object>
{
  private static final long serialVersionUID = -7775129435872564122L;
  protected final Class<?> _enumClass;
  protected final Method _factory;
  protected final Class<?> _inputType;
  
  public EnumDeserializer$FactoryBasedDeserializer(Class<?> paramClass1, AnnotatedMethod paramAnnotatedMethod, Class<?> paramClass2)
  {
    super(Enum.class);
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

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.EnumDeserializer.FactoryBasedDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */