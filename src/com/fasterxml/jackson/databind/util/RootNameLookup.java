package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

public class RootNameLookup
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient LRUMap<ClassKey, SerializedString> _rootNames;
  
  public SerializedString findRootName(JavaType paramJavaType, MapperConfig<?> paramMapperConfig)
  {
    return findRootName(paramJavaType.getRawClass(), paramMapperConfig);
  }
  
  public SerializedString findRootName(Class<?> paramClass, MapperConfig<?> paramMapperConfig)
  {
    ClassKey localClassKey = new ClassKey(paramClass);
    for (;;)
    {
      try
      {
        if (_rootNames == null)
        {
          _rootNames = new LRUMap(20, 200);
          localObject = paramMapperConfig.introspectClassAnnotations(paramClass);
          paramMapperConfig = paramMapperConfig.getAnnotationIntrospector().findRootName(((BeanDescription)localObject).getClassInfo());
          if ((paramMapperConfig != null) && (paramMapperConfig.hasSimpleName())) {
            break label125;
          }
          paramClass = paramClass.getSimpleName();
          paramClass = new SerializedString(paramClass);
        }
      }
      finally {}
      label125:
      try
      {
        _rootNames.put(localClassKey, paramClass);
        return paramClass;
      }
      finally {}
      Object localObject = (SerializedString)_rootNames.get(localClassKey);
      if (localObject != null)
      {
        return (SerializedString)localObject;
        paramClass = paramMapperConfig.getSimpleName();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.RootNameLookup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */