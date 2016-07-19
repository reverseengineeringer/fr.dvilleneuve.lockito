package org.androidannotations.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface EBean
{
  Scope scope() default Scope.Default;
  
  public static enum Scope
  {
    Default,  Singleton;
    
    private Scope() {}
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.annotations.EBean
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */