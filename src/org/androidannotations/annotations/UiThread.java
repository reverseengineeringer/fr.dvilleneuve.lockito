package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface UiThread
{
  long delay() default 0L;
  
  Propagation propagation() default Propagation.ENQUEUE;
  
  public static enum Propagation
  {
    ENQUEUE,  REUSE;
    
    private Propagation() {}
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.UiThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */