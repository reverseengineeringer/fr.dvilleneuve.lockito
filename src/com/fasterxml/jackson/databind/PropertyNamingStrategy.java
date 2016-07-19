package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;

public abstract class PropertyNamingStrategy
  implements Serializable
{
  public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = new LowerCaseWithUnderscoresStrategy();
  public static final PropertyNamingStrategy PASCAL_CASE_TO_CAMEL_CASE = new PascalCaseStrategy();
  
  public String nameForConstructorParameter(MapperConfig<?> paramMapperConfig, AnnotatedParameter paramAnnotatedParameter, String paramString)
  {
    return paramString;
  }
  
  public String nameForField(MapperConfig<?> paramMapperConfig, AnnotatedField paramAnnotatedField, String paramString)
  {
    return paramString;
  }
  
  public String nameForGetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return paramString;
  }
  
  public String nameForSetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return paramString;
  }
  
  public static class LowerCaseWithUnderscoresStrategy
    extends PropertyNamingStrategy.PropertyNamingStrategyBase
  {
    public String translate(String paramString)
    {
      if (paramString == null) {
        return paramString;
      }
      int n = paramString.length();
      StringBuilder localStringBuilder = new StringBuilder(n * 2);
      int m = 0;
      int j = 0;
      int i = 0;
      label33:
      int k;
      if (m < n)
      {
        char c = paramString.charAt(m);
        if ((m <= 0) && (c == '_')) {
          break label170;
        }
        if (Character.isUpperCase(c))
        {
          k = i;
          if (j == 0)
          {
            k = i;
            if (i > 0)
            {
              k = i;
              if (localStringBuilder.charAt(i - 1) != '_')
              {
                localStringBuilder.append('_');
                k = i + 1;
              }
            }
          }
          c = Character.toLowerCase(c);
          i = 1;
          j = k;
          label120:
          localStringBuilder.append(c);
          j += 1;
        }
      }
      for (;;)
      {
        m += 1;
        k = i;
        i = j;
        j = k;
        break label33;
        j = i;
        i = 0;
        break label120;
        if (i <= 0) {
          break;
        }
        return localStringBuilder.toString();
        label170:
        k = j;
        j = i;
        i = k;
      }
    }
  }
  
  public static class PascalCaseStrategy
    extends PropertyNamingStrategy.PropertyNamingStrategyBase
  {
    public String translate(String paramString)
    {
      if ((paramString == null) || (paramString.length() == 0)) {}
      char c;
      do
      {
        return paramString;
        c = paramString.charAt(0);
      } while (Character.isUpperCase(c));
      paramString = new StringBuilder(paramString);
      paramString.setCharAt(0, Character.toUpperCase(c));
      return paramString.toString();
    }
  }
  
  public static abstract class PropertyNamingStrategyBase
    extends PropertyNamingStrategy
  {
    public String nameForConstructorParameter(MapperConfig<?> paramMapperConfig, AnnotatedParameter paramAnnotatedParameter, String paramString)
    {
      return translate(paramString);
    }
    
    public String nameForField(MapperConfig<?> paramMapperConfig, AnnotatedField paramAnnotatedField, String paramString)
    {
      return translate(paramString);
    }
    
    public String nameForGetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
    {
      return translate(paramString);
    }
    
    public String nameForSetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
    {
      return translate(paramString);
    }
    
    public abstract String translate(String paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.PropertyNamingStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */