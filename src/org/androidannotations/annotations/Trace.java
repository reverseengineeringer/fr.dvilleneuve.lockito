package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface Trace
{
  public static final String DEFAULT_TAG = "NO_TAG";
  
  int level() default 4;
  
  String tag() default "NO_TAG";
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.Trace
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */