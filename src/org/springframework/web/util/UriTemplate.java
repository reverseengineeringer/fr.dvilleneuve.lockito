package org.springframework.web.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

public class UriTemplate
  implements Serializable
{
  private static final String DEFAULT_VARIABLE_PATTERN = "(.*)";
  private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");
  private static final long serialVersionUID = 1L;
  private final Pattern matchPattern;
  private final UriComponents uriComponents;
  private final String uriTemplate;
  private final List<String> variableNames;
  
  public UriTemplate(String paramString)
  {
    Parser localParser = new Parser(paramString, null);
    uriTemplate = paramString;
    variableNames = localParser.getVariableNames();
    matchPattern = localParser.getMatchPattern();
    uriComponents = UriComponentsBuilder.fromUriString(paramString).build();
  }
  
  @Deprecated
  protected URI encodeUri(String paramString)
  {
    try
    {
      URI localURI = new URI(UriUtils.encodeUri(paramString, "UTF-8"));
      return localURI;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new IllegalStateException(paramString);
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new IllegalArgumentException("Could not create URI from [" + paramString + "]: " + localURISyntaxException, localURISyntaxException);
    }
  }
  
  public URI expand(Map<String, ?> paramMap)
  {
    return uriComponents.expand(paramMap).encode().toUri();
  }
  
  public URI expand(Object... paramVarArgs)
  {
    return uriComponents.expand(paramVarArgs).encode().toUri();
  }
  
  public List<String> getVariableNames()
  {
    return variableNames;
  }
  
  public Map<String, String> match(String paramString)
  {
    Assert.notNull(paramString, "'uri' must not be null");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(variableNames.size());
    paramString = matchPattern.matcher(paramString);
    if (paramString.find())
    {
      int i = 1;
      while (i <= paramString.groupCount())
      {
        localLinkedHashMap.put((String)variableNames.get(i - 1), paramString.group(i));
        i += 1;
      }
    }
    return localLinkedHashMap;
  }
  
  public boolean matches(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return matchPattern.matcher(paramString).matches();
  }
  
  public String toString()
  {
    return uriTemplate;
  }
  
  private static class Parser
  {
    private final StringBuilder patternBuilder = new StringBuilder();
    private final List<String> variableNames = new LinkedList();
    
    private Parser(String paramString)
    {
      Assert.hasText(paramString, "'uriTemplate' must not be null");
      Matcher localMatcher = UriTemplate.NAMES_PATTERN.matcher(paramString);
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
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriTemplate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */