package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
public @interface RequiresPermission$Read
{
  RequiresPermission value() default @RequiresPermission;
}

/* Location:
 * Qualified Name:     android.support.annotation.RequiresPermission.Read
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */