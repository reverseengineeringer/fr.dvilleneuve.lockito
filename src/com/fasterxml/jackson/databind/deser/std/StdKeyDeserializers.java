package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class StdKeyDeserializers
  implements KeyDeserializers, Serializable
{
  private static final long serialVersionUID = 923268084968181479L;
  
  public static KeyDeserializer constructDelegatingKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer)
  {
    return new StdKeyDeserializer.DelegatingKD(paramJavaType.getRawClass(), paramJsonDeserializer);
  }
  
  public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> paramEnumResolver)
  {
    return new StdKeyDeserializer.EnumKD(paramEnumResolver, null);
  }
  
  public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> paramEnumResolver, AnnotatedMethod paramAnnotatedMethod)
  {
    return new StdKeyDeserializer.EnumKD(paramEnumResolver, paramAnnotatedMethod);
  }
  
  @Deprecated
  public static KeyDeserializer constructStringKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    return StdKeyDeserializer.StringKD.forType(paramJavaType.getRawClass());
  }
  
  public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    paramJavaType = paramDeserializationConfig.introspect(paramJavaType);
    Constructor localConstructor = paramJavaType.findSingleArgConstructor(new Class[] { String.class });
    if (localConstructor != null)
    {
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(localConstructor);
      }
      return new StdKeyDeserializer.StringCtorKeyDeserializer(localConstructor);
    }
    paramJavaType = paramJavaType.findFactoryMethod(new Class[] { String.class });
    if (paramJavaType != null)
    {
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(paramJavaType);
      }
      return new StdKeyDeserializer.StringFactoryKeyDeserializer(paramJavaType);
    }
    return null;
  }
  
  public KeyDeserializer findKeyDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    paramDeserializationConfig = paramJavaType.getRawClass();
    if ((paramDeserializationConfig == String.class) || (paramDeserializationConfig == Object.class)) {
      return StdKeyDeserializer.StringKD.forType(paramDeserializationConfig);
    }
    if (paramDeserializationConfig == UUID.class) {
      return new StdKeyDeserializer.UuidKD();
    }
    paramJavaType = paramDeserializationConfig;
    if (paramDeserializationConfig.isPrimitive()) {
      paramJavaType = ClassUtil.wrapperType(paramDeserializationConfig);
    }
    if (paramJavaType == Integer.class) {
      return new StdKeyDeserializer.IntKD();
    }
    if (paramJavaType == Long.class) {
      return new StdKeyDeserializer.LongKD();
    }
    if (paramJavaType == Date.class) {
      return new StdKeyDeserializer.DateKD();
    }
    if (paramJavaType == Calendar.class) {
      return new StdKeyDeserializer.CalendarKD();
    }
    if (paramJavaType == Boolean.class) {
      return new StdKeyDeserializer.BoolKD();
    }
    if (paramJavaType == Byte.class) {
      return new StdKeyDeserializer.ByteKD();
    }
    if (paramJavaType == Character.class) {
      return new StdKeyDeserializer.CharKD();
    }
    if (paramJavaType == Short.class) {
      return new StdKeyDeserializer.ShortKD();
    }
    if (paramJavaType == Float.class) {
      return new StdKeyDeserializer.FloatKD();
    }
    if (paramJavaType == Double.class) {
      return new StdKeyDeserializer.DoubleKD();
    }
    if (paramJavaType == Locale.class) {
      return new StdKeyDeserializer.LocaleKD();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */