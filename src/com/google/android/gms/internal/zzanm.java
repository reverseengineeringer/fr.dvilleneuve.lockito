package com.google.android.gms.internal;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD})
public @interface zzanm
{
  Class<?> value();
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */