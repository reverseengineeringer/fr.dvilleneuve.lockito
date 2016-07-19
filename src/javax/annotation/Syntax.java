package javax.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.When;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@TypeQualifier(applicableTo=String.class)
public @interface Syntax
{
  String value();
  
  When when() default When.ALWAYS;
}

/* Location:
 * Qualified Name:     javax.annotation.Syntax
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */