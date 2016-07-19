package com.google.android.gms.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class zzamp
{
  private final Field bdR;
  
  public zzamp(Field paramField)
  {
    zzanq.zzaa(paramField);
    bdR = paramField;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    return bdR.getAnnotation(paramClass);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzamp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */