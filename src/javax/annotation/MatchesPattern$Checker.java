package javax.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

public class MatchesPattern$Checker
  implements TypeQualifierValidator<MatchesPattern>
{
  public When forConstantValue(MatchesPattern paramMatchesPattern, Object paramObject)
  {
    if (Pattern.compile(paramMatchesPattern.value(), paramMatchesPattern.flags()).matcher((String)paramObject).matches()) {
      return When.ALWAYS;
    }
    return When.NEVER;
  }
}

/* Location:
 * Qualified Name:     javax.annotation.MatchesPattern.Checker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */