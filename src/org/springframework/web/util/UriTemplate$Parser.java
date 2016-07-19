package org.springframework.web.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

class UriTemplate$Parser
{
  private final StringBuilder patternBuilder = new StringBuilder();
  private final List<String> variableNames = new LinkedList();
  
  private UriTemplate$Parser(String paramString)
  {
    Assert.hasText(paramString, "'uriTemplate' must not be null");
    Matcher localMatcher = UriTemplate.access$300().matcher(paramString);
    int i = 0;
    if (localMatcher.find())
    {
      patternBuilder.append(quote(paramString, i, localMatcher.start()));
      String str1 = localMatcher.group(1);
      i = str1.indexOf(':');
      if (i == -1)
      {
        patternBuilder.append("(.*)");
        variableNames.add(str1);
      }
      for (;;)
      {
        i = localMatcher.end();
        break;
        if (i + 1 == str1.length()) {
          throw new IllegalArgumentException("No custom regular expression specified after ':' in \"" + str1 + "\"");
        }
        String str2 = str1.substring(i + 1, str1.length());
        patternBuilder.append('(');
        patternBuilder.append(str2);
        patternBuilder.append(')');
        str1 = str1.substring(0, i);
        variableNames.add(str1);
      }
    }
    patternBuilder.append(quote(paramString, i, paramString.length()));
    i = patternBuilder.length() - 1;
    if ((i >= 0) && (patternBuilder.charAt(i) == '/')) {
      patternBuilder.deleteCharAt(i);
    }
  }
  
  private Pattern getMatchPattern()
  {
    return Pattern.compile(patternBuilder.toString());
  }
  
  private List<String> getVariableNames()
  {
    return Collections.unmodifiableList(variableNames);
  }
  
  private String quote(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return "";
    }
    return Pattern.quote(paramString.substring(paramInt1, paramInt2));
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriTemplate.Parser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */