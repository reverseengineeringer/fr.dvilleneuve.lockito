package org.springframework.web.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

public abstract class UriUtils
{
  private static final String HOST_PATTERN = "([^/?#:]*)";
  private static final String HTTP_PATTERN = "(http|https):";
  private static final Pattern HTTP_URL_PATTERN = Pattern.compile("^(http|https):(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?(.*))?");
  private static final String LAST_PATTERN = "(.*)";
  private static final String PATH_PATTERN = "([^?#]*)";
  private static final String PORT_PATTERN = "(\\d*)";
  private static final String QUERY_PATTERN = "([^#]*)";
  private static final String SCHEME_PATTERN = "([^:/?#]+):";
  private static final Pattern URI_PATTERN = Pattern.compile("^(([^:/?#]+):)?(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?([^#]*))?(#(.*))?");
  private static final String USERINFO_PATTERN = "([^@/]*)";
  
  public static String decode(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    Assert.notNull(paramString1, "'source' must not be null");
    Assert.hasLength(paramString2, "'encoding' must not be empty");
    int k = paramString1.length();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(k);
    int j = 0;
    int i = 0;
    if (i < k)
    {
      int m = paramString1.charAt(i);
      if (m == 37) {
        if (i + 2 < k)
        {
          char c1 = paramString1.charAt(i + 1);
          char c2 = paramString1.charAt(i + 2);
          j = Character.digit(c1, 16);
          m = Character.digit(c2, 16);
          if ((j == -1) || (m == -1)) {
            throw new IllegalArgumentException("Invalid encoded sequence \"" + paramString1.substring(i) + "\"");
          }
          localByteArrayOutputStream.write((char)((j << 4) + m));
          i += 2;
          j = 1;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        throw new IllegalArgumentException("Invalid encoded sequence \"" + paramString1.substring(i) + "\"");
        localByteArrayOutputStream.write(m);
      }
    }
    if (j != 0) {
      paramString1 = new String(localByteArrayOutputStream.toByteArray(), paramString2);
    }
    return paramString1;
  }
  
  public static String encodeAuthority(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.AUTHORITY);
  }
  
  public static String encodeFragment(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.FRAGMENT);
  }
  
  public static String encodeHost(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.HOST);
  }
  
  public static String encodeHttpUrl(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    Assert.notNull(paramString1, "'httpUrl' must not be null");
    Assert.hasLength(paramString2, "'encoding' must not be empty");
    Matcher localMatcher = HTTP_URL_PATTERN.matcher(paramString1);
    if (localMatcher.matches()) {
      return encodeUriComponents(localMatcher.group(1), localMatcher.group(2), localMatcher.group(4), localMatcher.group(5), localMatcher.group(7), localMatcher.group(8), localMatcher.group(10), null, paramString2);
    }
    throw new IllegalArgumentException("[" + paramString1 + "] is not a valid HTTP URL");
  }
  
  public static String encodePath(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.PATH);
  }
  
  public static String encodePathSegment(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.PATH_SEGMENT);
  }
  
  public static String encodePort(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.PORT);
  }
  
  public static String encodeQuery(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.QUERY);
  }
  
  public static String encodeQueryParam(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.QUERY_PARAM);
  }
  
  public static String encodeScheme(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.SCHEME);
  }
  
  public static String encodeUri(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    Assert.notNull(paramString1, "'uri' must not be null");
    Assert.hasLength(paramString2, "'encoding' must not be empty");
    Matcher localMatcher = URI_PATTERN.matcher(paramString1);
    if (localMatcher.matches()) {
      return encodeUriComponents(localMatcher.group(2), localMatcher.group(3), localMatcher.group(5), localMatcher.group(6), localMatcher.group(8), localMatcher.group(9), localMatcher.group(11), localMatcher.group(13), paramString2);
    }
    throw new IllegalArgumentException("[" + paramString1 + "] is not a valid URI");
  }
  
  public static String encodeUriComponents(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
    throws UnsupportedEncodingException
  {
    Assert.hasLength(paramString9, "'encoding' must not be empty");
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString1 != null)
    {
      localStringBuilder.append(encodeScheme(paramString1, paramString9));
      localStringBuilder.append(':');
    }
    if (paramString2 != null)
    {
      localStringBuilder.append("//");
      if (paramString3 != null)
      {
        localStringBuilder.append(encodeUserInfo(paramString3, paramString9));
        localStringBuilder.append('@');
      }
      if (paramString4 != null) {
        localStringBuilder.append(encodeHost(paramString4, paramString9));
      }
      if (paramString5 != null)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(encodePort(paramString5, paramString9));
      }
    }
    localStringBuilder.append(encodePath(paramString6, paramString9));
    if (paramString7 != null)
    {
      localStringBuilder.append('?');
      localStringBuilder.append(encodeQuery(paramString7, paramString9));
    }
    if (paramString8 != null)
    {
      localStringBuilder.append('#');
      localStringBuilder.append(encodeFragment(paramString8, paramString9));
    }
    return localStringBuilder.toString();
  }
  
  public static String encodeUserInfo(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return UriComponents.encodeUriComponent(paramString1, paramString2, UriComponents.Type.USER_INFO);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */