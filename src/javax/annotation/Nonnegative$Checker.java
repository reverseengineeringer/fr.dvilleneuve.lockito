package javax.annotation;

import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

public class Nonnegative$Checker
  implements TypeQualifierValidator<Nonnegative>
{
  public When forConstantValue(Nonnegative paramNonnegative, Object paramObject)
  {
    int j = 1;
    int k = 1;
    int m = 1;
    int i = 1;
    if (!(paramObject instanceof Number)) {
      return When.NEVER;
    }
    paramNonnegative = (Number)paramObject;
    if ((paramNonnegative instanceof Long))
    {
      if (paramNonnegative.longValue() < 0L) {}
      while (i != 0)
      {
        return When.NEVER;
        i = 0;
      }
    }
    if ((paramNonnegative instanceof Double))
    {
      if (paramNonnegative.doubleValue() < 0.0D) {}
      for (i = j;; i = 0) {
        break;
      }
    }
    if ((paramNonnegative instanceof Float))
    {
      if (paramNonnegative.floatValue() < 0.0F) {}
      for (i = k;; i = 0) {
        break;
      }
    }
    if (paramNonnegative.intValue() < 0) {}
    for (i = m;; i = 0) {
      break;
    }
    return When.ALWAYS;
  }
}

/* Location:
 * Qualified Name:     javax.annotation.Nonnegative.Checker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */