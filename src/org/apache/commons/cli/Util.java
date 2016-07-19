package org.apache.commons.cli;

class Util
{
  static String stripLeadingAndTrailingQuotes(String paramString)
  {
    String str = paramString;
    if (paramString.startsWith("\"")) {
      str = paramString.substring(1, paramString.length());
    }
    paramString = str;
    if (str.endsWith("\"")) {
      paramString = str.substring(0, str.length() - 1);
    }
    return paramString;
  }
  
  static String stripLeadingHyphens(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    do
    {
      return str;
      if (paramString.startsWith("--")) {
        return paramString.substring(2, paramString.length());
      }
      str = paramString;
    } while (!paramString.startsWith("-"));
    return paramString.substring(1, paramString.length());
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.Util
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */