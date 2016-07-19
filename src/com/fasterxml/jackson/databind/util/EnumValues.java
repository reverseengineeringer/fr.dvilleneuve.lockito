package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class EnumValues
{
  private final Class<Enum<?>> _enumClass;
  private final EnumMap<?, SerializedString> _values;
  
  private EnumValues(Class<Enum<?>> paramClass, Map<Enum<?>, SerializedString> paramMap)
  {
    _enumClass = paramClass;
    _values = new EnumMap(paramMap);
  }
  
  public static EnumValues construct(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    return constructFromName(paramClass, paramAnnotationIntrospector);
  }
  
  public static EnumValues constructFromName(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    Enum[] arrayOfEnum = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null)
    {
      HashMap localHashMap = new HashMap();
      int j = arrayOfEnum.length;
      int i = 0;
      while (i < j)
      {
        Enum localEnum = arrayOfEnum[i];
        localHashMap.put(localEnum, new SerializedString(paramAnnotationIntrospector.findEnumValue(localEnum)));
        i += 1;
      }
      return new EnumValues(paramClass, localHashMap);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public static EnumValues constructFromToString(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    paramAnnotationIntrospector = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (paramAnnotationIntrospector != null)
    {
      HashMap localHashMap = new HashMap();
      int j = paramAnnotationIntrospector.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramAnnotationIntrospector[i];
        localHashMap.put(localObject, new SerializedString(((Enum)localObject).toString()));
        i += 1;
      }
      return new EnumValues(paramClass, localHashMap);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public Class<Enum<?>> getEnumClass()
  {
    return _enumClass;
  }
  
  public EnumMap<?, SerializedString> internalMap()
  {
    return _values;
  }
  
  public SerializedString serializedValueFor(Enum<?> paramEnum)
  {
    return (SerializedString)_values.get(paramEnum);
  }
  
  public Collection<SerializedString> values()
  {
    return _values.values();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.EnumValues
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */