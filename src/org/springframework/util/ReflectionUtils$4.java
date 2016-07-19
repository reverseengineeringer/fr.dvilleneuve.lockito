package org.springframework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

final class ReflectionUtils$4
  implements ReflectionUtils.FieldFilter
{
  public boolean matches(Field paramField)
  {
    return (!Modifier.isStatic(paramField.getModifiers())) && (!Modifier.isFinal(paramField.getModifiers()));
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.ReflectionUtils.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */