package org.apache.commons.cli;

class OptionValidator
{
  private static boolean isValidChar(char paramChar)
  {
    return Character.isJavaIdentifierPart(paramChar);
  }
  
  private static boolean isValidOpt(char paramChar)
  {
    return (isValidChar(paramChar)) || (paramChar == ' ') || (paramChar == '?') || (paramChar == '@');
  }
  
  static void validateOption(String paramString)
    throws IllegalArgumentException
  {
    if (paramString == null) {}
    for (;;)
    {
      return;
      if (paramString.length() == 1)
      {
        char c = paramString.charAt(0);
        if (!isValidOpt(c)) {
          throw new IllegalArgumentException("illegal option value '" + c + "'");
        }
      }
      else
      {
        paramString = paramString.toCharArray();
        int i = 0;
        while (i < paramString.length)
        {
          if (!isValidChar(paramString[i])) {
            throw new IllegalArgumentException("opt contains illegal character value '" + paramString[i] + "'");
          }
          i += 1;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.OptionValidator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */