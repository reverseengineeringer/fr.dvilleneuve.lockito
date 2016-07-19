package javax.annotation.meta;

import java.lang.annotation.Annotation;
import javax.annotation.Nonnull;

public abstract interface TypeQualifierValidator<A extends Annotation>
{
  @Nonnull
  public abstract When forConstantValue(@Nonnull A paramA, Object paramObject);
}

/* Location:
 * Qualified Name:     javax.annotation.meta.TypeQualifierValidator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */