package org.springframework.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.util.StringUtils;

class UriComponentsBuilder$PathSegmentComponentBuilder
  implements UriComponentsBuilder.PathComponentBuilder
{
  private final List<String> pathSegments = new ArrayList();
  
  private UriComponentsBuilder$PathSegmentComponentBuilder(String... paramVarArgs)
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

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponentsBuilder.PathSegmentComponentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */