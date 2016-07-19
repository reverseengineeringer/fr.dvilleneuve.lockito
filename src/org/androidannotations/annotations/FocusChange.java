package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface FocusChange
{
  String[] resName() default {""};
  
  int[] value() default {-1};
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.FocusChange
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */