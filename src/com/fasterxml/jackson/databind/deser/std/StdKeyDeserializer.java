package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public abstract class StdKeyDeserializer
  extends KeyDeserializer
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _keyClass;
  
  protected StdKeyDeserializer(Class<?> paramClass)
  {
    _keyClass = paramClass;
  }
  
  protected abstract Object _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws Exception;
  
  protected double _parseDouble(String paramString)
    throws IllegalArgumentException
  {
    return NumberInput.parseDouble(paramString);
  }
  
  protected int _parseInt(String paramString)
    throws IllegalArgumentException
  {
    return Integer.parseInt(paramString);
  }
  
  protected long _parseLong(String paramString)
    throws IllegalArgumentException
  {
    return Long.parseLong(paramString);
  }
  
  public final Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramString == null) {}
    do
    {
      return null;
      try
      {
        Object localObject = _parse(paramString, paramDeserializationContext);
        if (localObject != null) {
          return localObject;
        }
      }
      catch (Exception localException)
      {
        throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "not a valid representation: " + localException.getMessage());
      }
    } while ((_keyClass.isEnum()) && (paramDeserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)));
    throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "not a valid representation");
  }
  
  public Class<?> getKeyClass()
  {
    return _keyClass;
  }
  
  @JacksonStdImpl
  static final class BoolKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    BoolKD()
    {
      super();
    }
    
    public Boolean _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      if ("true".equals(paramString)) {
        return Boolean.TRUE;
      }
      if ("false".equals(paramString)) {
        return Boolean.FALSE;
      }
      throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "value not 'true' or 'false'");
    }
  }
  
  @JacksonStdImpl
  static final class ByteKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    ByteKD()
    {
      super();
    }
    
    public Byte _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      int i = _parseInt(paramString);
      if ((i < -128) || (i > 255)) {
        throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "overflow, value can not be represented as 8-bit value");
      }
      return Byte.valueOf((byte)i);
    }
  }
  
  @JacksonStdImpl
  static final class CalendarKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    protected CalendarKD()
    {
      super();
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException, JsonMappingException
    {
      paramString = paramDeserializationContext.parseDate(paramString);
      if (paramString == null) {
        return null;
      }
      return paramDeserializationContext.constructCalendar(paramString);
    }
  }
  
  @JacksonStdImpl
  static final class CharKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    CharKD()
    {
      super();
    }
    
    public Character _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      if (paramString.length() == 1) {
        return Character.valueOf(paramString.charAt(0));
      }
      throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "can only convert 1-character Strings");
    }
  }
  
  @JacksonStdImpl
  static final class DateKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    protected DateKD()
    {
      super();
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException, JsonMappingException
    {
      return paramDeserializationContext.parseDate(paramString);
    }
  }
  
  static final class DelegatingKD
    extends KeyDeserializer
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer<?> _delegate;
    protected final Class<?> _keyClass;
    
    protected DelegatingKD(Class<?> paramClass, JsonDeserializer<?> paramJsonDeserializer)
    {
      _keyClass = paramClass;
      _delegate = paramJsonDeserializer;
    }
    
    public final Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Object localObject1;
      if (paramString == null) {
        localObject1 = null;
      }
      for (;;)
      {
        return localObject1;
        try
        {
          Object localObject2 = _delegate.deserialize(paramDeserializationContext.getParser(), paramDeserializationContext);
          localObject1 = localObject2;
          if (localObject2 != null) {
            continue;
          }
          throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "not a valid representation");
        }
        catch (Exception localException)
        {
          throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "not a valid representation: " + localException.getMessage());
        }
      }
    }
    
    public Class<?> getKeyClass()
    {
      return _keyClass;
    }
  }
  
  @JacksonStdImpl
  static final class DoubleKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    DoubleKD()
    {
      super();
    }
    
    public Double _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      return Double.valueOf(_parseDouble(paramString));
    }
  }
  
  @JacksonStdImpl
  static final class EnumKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final AnnotatedMethod _factory;
    protected final EnumResolver<?> _resolver;
    
    protected EnumKD(EnumResolver<?> paramEnumResolver, AnnotatedMethod paramAnnotatedMethod)
    {
      super();
      _resolver = paramEnumResolver;
      _factory = paramAnnotatedMethod;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      if (_factory != null) {}
      do
      {
        Enum localEnum;
        do
        {
          try
          {
            Object localObject1 = _factory.call1(paramString);
            return localObject1;
          }
          catch (Exception localException)
          {
            ClassUtil.unwrapAndThrowAsIAE(localException);
          }
          localEnum = _resolver.findEnum(paramString);
          localObject2 = localEnum;
        } while (localEnum != null);
        Object localObject2 = localEnum;
      } while (paramDeserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL));
      throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "not one of values for Enum class");
    }
  }
  
  @JacksonStdImpl
  static final class FloatKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    FloatKD()
    {
      super();
    }
    
    public Float _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      return Float.valueOf((float)_parseDouble(paramString));
    }
  }
  
  @JacksonStdImpl
  static final class IntKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    IntKD()
    {
      super();
    }
    
    public Integer _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      return Integer.valueOf(_parseInt(paramString));
    }
  }
  
  @JacksonStdImpl
  static final class LocaleKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected JdkDeserializers.LocaleDeserializer _localeDeserializer = new JdkDeserializers.LocaleDeserializer();
    
    LocaleKD()
    {
      super();
    }
    
    protected Locale _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      try
      {
        Locale localLocale = _localeDeserializer._deserialize(paramString, paramDeserializationContext);
        return localLocale;
      }
      catch (IOException localIOException)
      {
        throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "unable to parse key as locale");
      }
    }
  }
  
  @JacksonStdImpl
  static final class LongKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    LongKD()
    {
      super();
    }
    
    public Long _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      return Long.valueOf(_parseLong(paramString));
    }
  }
  
  @JacksonStdImpl
  static final class ShortKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    ShortKD()
    {
      super();
    }
    
    public Short _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      int i = _parseInt(paramString);
      if ((i < 32768) || (i > 32767)) {
        throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "overflow, value can not be represented as 16-bit value");
      }
      return Short.valueOf((short)i);
    }
  }
  
  static final class StringCtorKeyDeserializer
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final Constructor<?> _ctor;
    
    public StringCtorKeyDeserializer(Constructor<?> paramConstructor)
    {
      super();
      _ctor = paramConstructor;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws Exception
    {
      return _ctor.newInstance(new Object[] { paramString });
    }
  }
  
  static final class StringFactoryKeyDeserializer
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    final Method _factoryMethod;
    
    public StringFactoryKeyDeserializer(Method paramMethod)
    {
      super();
      _factoryMethod = paramMethod;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws Exception
    {
      return _factoryMethod.invoke(null, new Object[] { paramString });
    }
  }
  
  @JacksonStdImpl
  static final class StringKD
    extends StdKeyDeserializer
  {
    private static final StringKD sObject = new StringKD(Object.class);
    private static final StringKD sString = new StringKD(String.class);
    private static final long serialVersionUID = 1L;
    
    private StringKD(Class<?> paramClass)
    {
      super();
    }
    
    public static StringKD forType(Class<?> paramClass)
    {
      if (paramClass == String.class) {
        return sString;
      }
      if (paramClass == Object.class) {
        return sObject;
      }
      return new StringKD(paramClass);
    }
    
    public String _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      return paramString;
    }
  }
  
  @JacksonStdImpl
  static final class UuidKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    
    protected UuidKD()
    {
      super();
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException, JsonMappingException
    {
      return UUID.fromString(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */