package javax.annotation;

import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

public class Nonnull$Checker
  implements TypeQualifierValidator<Nonnull>
{
  public When forConstantValue(Nonnull paramNonnull, Object paramObject)
  {
    if (paramObject == null) {
      return When.NEVER;
    }
    return When.ALWAYS;
  }
}

/* Location:
 * Qualified Name:     javax.annotation.Nonnull.Checker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */