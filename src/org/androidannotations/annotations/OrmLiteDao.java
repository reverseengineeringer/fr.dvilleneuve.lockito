package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface OrmLiteDao
{
  Class<?> helper();
  
  @Deprecated
  Class<?> model() default Void.class;
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.OrmLiteDao
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */