package javax.annotation;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

public class RegEx$Checker
  implements TypeQualifierValidator<RegEx>
{
  public When forConstantValue(RegEx paramRegEx, Object paramObject)
  {
    if (!(paramObject instanceof String)) {
      return When.NEVER;
    }
    try
    {
      Pattern.compile((String)paramObject);
      return When.ALWAYS;
    }
    catch (PatternSyntaxException paramRegEx) {}
    return When.NEVER;
  }
}

/* Location:
 * Qualified Name:     javax.annotation.RegEx.Checker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */