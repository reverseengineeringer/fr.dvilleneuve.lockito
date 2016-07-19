package org.springframework.web.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class UriComponents$PathSegmentComponent
  implements UriComponents.PathComponent
{
  private final List<String> pathSegments;
  
  UriComponents$PathSegmentComponent(List<String> paramList)
  {
    pathSegments = Collections.unmodifiableList(paramList);
  }
  
  public UriComponents.PathComponent encode(String paramString)
    throws UnsupportedEncodingException
  {
    Object localObject = getPathSegments();
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(UriComponents.encodeUriComponent((String)((Iterator)localObject).next(), paramString, UriComponents.Type.PATH_SEGMENT));
    }
    return new PathSegmentComponent(localArrayList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof PathSegmentComponent))
    {
      paramObject = (PathSegmentComponent)paramObject;
      return getPathSegments().equals(((PathSegmentComponent)paramObject).getPathSegments());
    }
    return false;
  }
  
  public UriComponents.PathComponent expand(UriComponents.UriTemplateVariables paramUriTemplateVariables)
  {
    Object localObject = getPathSegments();
    ArrayList localArrayList = new ArrayList(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(UriComponents.access$200((String)((Iterator)localObject).next(), paramUriTemplateVariables));
    }
    return new PathSegmentComponent(localArrayList);
  }
  
  public String getPath()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('/');
    Iterator localIterator = pathSegments.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append((String)localIterator.next());
      if (localIterator.hasNext()) {
        localStringBuilder.append('/');
      }
    }
    return localStringBuilder.toString();
  }
  
  public List<String> getPathSegments()
  {
    return pathSegments;
  }
  
  public int hashCode()
  {
    return getPathSegments().hashCode();
  }
  
  public void verify()
  {
    Iterator localIterator = getPathSegments().iterator();
    while (localIterator.hasNext()) {
      UriComponents.access$100((String)localIterator.next(), UriComponents.Type.PATH_SEGMENT);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.PathSegmentComponent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */