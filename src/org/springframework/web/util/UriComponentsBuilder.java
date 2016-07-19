package org.springframework.web.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class UriComponentsBuilder
{
  private static final String HOST_PATTERN = "([^/?#:]*)";
  private static final String HTTP_PATTERN = "(http|https):";
  private static final Pattern HTTP_URL_PATTERN = Pattern.compile("^(http|https):(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?(.*))?");
  private static final String LAST_PATTERN = "(.*)";
  private static PathComponentBuilder NULL_PATH_COMPONENT_BUILDER = new PathComponentBuilder()
  {
    public UriComponentsBuilder.PathComponentBuilder appendPath(String paramAnonymousString)
    {
      return new UriComponentsBuilder.FullPathComponentBuilder(paramAnonymousString, null);
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPathSegments(String... paramAnonymousVarArgs)
    {
      return new UriComponentsBuilder.PathSegmentComponentBuilder(paramAnonymousVarArgs, null);
    }
    
    public UriComponents.PathComponent build()
    {
      return UriComponents.NULL_PATH_COMPONENT;
    }
  };
  private static final String PATH_PATTERN = "([^?#]*)";
  private static final String PORT_PATTERN = "(\\d*)";
  private static final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)=?([^&=]+)?");
  private static final String QUERY_PATTERN = "([^#]*)";
  private static final String SCHEME_PATTERN = "([^:/?#]+):";
  private static final Pattern URI_PATTERN = Pattern.compile("^(([^:/?#]+):)?(//(([^@/]*)@)?([^/?#:]*)(:(\\d*))?)?([^?#]*)(\\?([^#]*))?(#(.*))?");
  private static final String USERINFO_PATTERN = "([^@/]*)";
  private String fragment;
  private String host;
  private PathComponentBuilder pathBuilder = NULL_PATH_COMPONENT_BUILDER;
  private int port = -1;
  private final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap();
  private String scheme;
  private String userInfo;
  
  public static UriComponentsBuilder fromHttpUrl(String paramString)
  {
    Assert.notNull(paramString, "'httpUrl' must not be null");
    Matcher localMatcher = HTTP_URL_PATTERN.matcher(paramString);
    if (localMatcher.matches())
    {
      paramString = new UriComponentsBuilder();
      paramString.scheme(localMatcher.group(1));
      paramString.userInfo(localMatcher.group(4));
      paramString.host(localMatcher.group(5));
      String str = localMatcher.group(7);
      if (StringUtils.hasLength(str)) {
        paramString.port(Integer.parseInt(str));
      }
      paramString.path(localMatcher.group(8));
      paramString.query(localMatcher.group(10));
      return paramString;
    }
    throw new IllegalArgumentException("[" + paramString + "] is not a valid HTTP URL");
  }
  
  public static UriComponentsBuilder fromPath(String paramString)
  {
    UriComponentsBuilder localUriComponentsBuilder = new UriComponentsBuilder();
    localUriComponentsBuilder.path(paramString);
    return localUriComponentsBuilder;
  }
  
  public static UriComponentsBuilder fromUri(URI paramURI)
  {
    UriComponentsBuilder localUriComponentsBuilder = new UriComponentsBuilder();
    localUriComponentsBuilder.uri(paramURI);
    return localUriComponentsBuilder;
  }
  
  public static UriComponentsBuilder fromUriString(String paramString)
  {
    Assert.hasLength(paramString, "'uri' must not be empty");
    Matcher localMatcher = URI_PATTERN.matcher(paramString);
    if (localMatcher.matches())
    {
      paramString = new UriComponentsBuilder();
      paramString.scheme(localMatcher.group(2));
      paramString.userInfo(localMatcher.group(5));
      paramString.host(localMatcher.group(6));
      String str = localMatcher.group(8);
      if (StringUtils.hasLength(str)) {
        paramString.port(Integer.parseInt(str));
      }
      paramString.path(localMatcher.group(9));
      paramString.query(localMatcher.group(11));
      paramString.fragment(localMatcher.group(13));
      return paramString;
    }
    throw new IllegalArgumentException("[" + paramString + "] is not a valid URI");
  }
  
  public static UriComponentsBuilder newInstance()
  {
    return new UriComponentsBuilder();
  }
  
  public UriComponents build()
  {
    return build(false);
  }
  
  public UriComponents build(boolean paramBoolean)
  {
    return new UriComponents(scheme, userInfo, host, port, pathBuilder.build(), queryParams, fragment, paramBoolean, true);
  }
  
  public UriComponents buildAndExpand(Map<String, ?> paramMap)
  {
    return build(false).expand(paramMap);
  }
  
  public UriComponents buildAndExpand(Object... paramVarArgs)
  {
    return build(false).expand(paramVarArgs);
  }
  
  public UriComponentsBuilder fragment(String paramString)
  {
    if (paramString != null)
    {
      Assert.hasLength(paramString, "'fragment' must not be empty");
      fragment = paramString;
      return this;
    }
    fragment = null;
    return this;
  }
  
  public UriComponentsBuilder host(String paramString)
  {
    host = paramString;
    return this;
  }
  
  public UriComponentsBuilder path(String paramString)
  {
    if (paramString != null)
    {
      pathBuilder = pathBuilder.appendPath(paramString);
      return this;
    }
    pathBuilder = NULL_PATH_COMPONENT_BUILDER;
    return this;
  }
  
  public UriComponentsBuilder pathSegment(String... paramVarArgs)
    throws IllegalArgumentException
  {
    Assert.notNull(paramVarArgs, "'segments' must not be null");
    pathBuilder = pathBuilder.appendPathSegments(paramVarArgs);
    return this;
  }
  
  public UriComponentsBuilder port(int paramInt)
  {
    if (paramInt >= -1) {}
    for (boolean bool = true;; bool = false)
    {
      Assert.isTrue(bool, "'port' must not be < -1");
      port = paramInt;
      return this;
    }
  }
  
  public UriComponentsBuilder query(String paramString)
  {
    if (paramString != null)
    {
      paramString = QUERY_PARAM_PATTERN.matcher(paramString);
      while (paramString.find()) {
        queryParam(paramString.group(1), new Object[] { paramString.group(2) });
      }
    }
    queryParams.clear();
    return this;
  }
  
  public UriComponentsBuilder queryParam(String paramString, Object... paramVarArgs)
  {
    Assert.notNull(paramString, "'name' must not be null");
    if (!ObjectUtils.isEmpty(paramVarArgs))
    {
      int j = paramVarArgs.length;
      int i = 0;
      if (i < j)
      {
        Object localObject = paramVarArgs[i];
        if (localObject != null) {}
        for (localObject = localObject.toString();; localObject = null)
        {
          queryParams.add(paramString, localObject);
          i += 1;
          break;
        }
      }
    }
    else
    {
      queryParams.add(paramString, null);
    }
    return this;
  }
  
  public UriComponentsBuilder replacePath(String paramString)
  {
    pathBuilder = NULL_PATH_COMPONENT_BUILDER;
    path(paramString);
    return this;
  }
  
  public UriComponentsBuilder replaceQuery(String paramString)
  {
    queryParams.clear();
    query(paramString);
    return this;
  }
  
  public UriComponentsBuilder replaceQueryParam(String paramString, Object... paramVarArgs)
  {
    Assert.notNull(paramString, "'name' must not be null");
    queryParams.remove(paramString);
    if (!ObjectUtils.isEmpty(paramVarArgs)) {
      queryParam(paramString, paramVarArgs);
    }
    return this;
  }
  
  public UriComponentsBuilder scheme(String paramString)
  {
    scheme = paramString;
    return this;
  }
  
  public UriComponentsBuilder uri(URI paramURI)
  {
    Assert.notNull(paramURI, "'uri' must not be null");
    if (!paramURI.isOpaque()) {}
    for (boolean bool = true;; bool = false)
    {
      Assert.isTrue(bool, "Opaque URI [" + paramURI + "] not supported");
      scheme = paramURI.getScheme();
      if (paramURI.getUserInfo() != null) {
        userInfo = paramURI.getUserInfo();
      }
      if (paramURI.getHost() != null) {
        host = paramURI.getHost();
      }
      if (paramURI.getPort() != -1) {
        port = paramURI.getPort();
      }
      if (StringUtils.hasLength(paramURI.getPath())) {
        pathBuilder = new FullPathComponentBuilder(paramURI.getPath(), null);
      }
      if (StringUtils.hasLength(paramURI.getQuery()))
      {
        queryParams.clear();
        query(paramURI.getQuery());
      }
      if (paramURI.getFragment() != null) {
        fragment = paramURI.getFragment();
      }
      return this;
    }
  }
  
  public UriComponentsBuilder userInfo(String paramString)
  {
    userInfo = paramString;
    return this;
  }
  
  private static class FullPathComponentBuilder
    implements UriComponentsBuilder.PathComponentBuilder
  {
    private final StringBuilder path;
    
    private FullPathComponentBuilder(String paramString)
    {
      path = new StringBuilder(paramString);
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPath(String paramString)
    {
      path.append(paramString);
      return this;
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPathSegments(String... paramVarArgs)
    {
      UriComponentsBuilder.PathComponentCompositeBuilder localPathComponentCompositeBuilder = new UriComponentsBuilder.PathComponentCompositeBuilder(this, null);
      localPathComponentCompositeBuilder.appendPathSegments(paramVarArgs);
      return localPathComponentCompositeBuilder;
    }
    
    public UriComponents.PathComponent build()
    {
      return new UriComponents.FullPathComponent(path.toString());
    }
  }
  
  private static abstract interface PathComponentBuilder
  {
    public abstract PathComponentBuilder appendPath(String paramString);
    
    public abstract PathComponentBuilder appendPathSegments(String... paramVarArgs);
    
    public abstract UriComponents.PathComponent build();
  }
  
  private static class PathComponentCompositeBuilder
    implements UriComponentsBuilder.PathComponentBuilder
  {
    private final List<UriComponentsBuilder.PathComponentBuilder> pathComponentBuilders = new ArrayList();
    
    private PathComponentCompositeBuilder(UriComponentsBuilder.PathComponentBuilder paramPathComponentBuilder)
    {
      pathComponentBuilders.add(paramPathComponentBuilder);
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPath(String paramString)
    {
      pathComponentBuilders.add(new UriComponentsBuilder.FullPathComponentBuilder(paramString, null));
      return this;
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPathSegments(String... paramVarArgs)
    {
      pathComponentBuilders.add(new UriComponentsBuilder.PathSegmentComponentBuilder(paramVarArgs, null));
      return this;
    }
    
    public UriComponents.PathComponent build()
    {
      ArrayList localArrayList = new ArrayList(pathComponentBuilders.size());
      Iterator localIterator = pathComponentBuilders.iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(((UriComponentsBuilder.PathComponentBuilder)localIterator.next()).build());
      }
      return new UriComponents.PathComponentComposite(localArrayList);
    }
  }
  
  private static class PathSegmentComponentBuilder
    implements UriComponentsBuilder.PathComponentBuilder
  {
    private final List<String> pathSegments = new ArrayList();
    
    private PathSegmentComponentBuilder(String... paramVarArgs)
    {
      pathSegments.addAll(removeEmptyPathSegments(paramVarArgs));
    }
    
    private Collection<String> removeEmptyPathSegments(String... paramVarArgs)
    {
      ArrayList localArrayList = new ArrayList();
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        String str = paramVarArgs[i];
        if (StringUtils.hasText(str)) {
          localArrayList.add(str);
        }
        i += 1;
      }
      return localArrayList;
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPath(String paramString)
    {
      UriComponentsBuilder.PathComponentCompositeBuilder localPathComponentCompositeBuilder = new UriComponentsBuilder.PathComponentCompositeBuilder(this, null);
      localPathComponentCompositeBuilder.appendPath(paramString);
      return localPathComponentCompositeBuilder;
    }
    
    public UriComponentsBuilder.PathComponentBuilder appendPathSegments(String... paramVarArgs)
    {
      pathSegments.addAll(removeEmptyPathSegments(paramVarArgs));
      return this;
    }
    
    public UriComponents.PathComponent build()
    {
      return new UriComponents.PathSegmentComponent(pathSegments);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponentsBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */