package org.springframework.web.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class UriComponents$PathComponentComposite
  implements UriComponents.PathComponent
{
  private final List<UriComponents.PathComponent> pathComponents;
  
  UriComponents$PathComponentComposite(List<UriComponents.PathComponent> paramList)
  {
    pathComponents = paramList;
  }
  
  public UriComponents.PathComponent encode(String paramString)
    throws UnsupportedEncodingException
  {
    ArrayList localArrayList = new ArrayList(pathComponents.size());
    Iterator localIterator = pathComponents.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((UriComponents.PathComponent)localIterator.next()).encode(paramString));
    }
    return new PathComponentComposite(localArrayList);
  }
  
  public UriComponents.PathComponent expand(UriComponents.UriTemplateVariables paramUriTemplateVariables)
  {
    ArrayList localArrayList = new ArrayList(pathComponents.size());
    Iterator localIterator = pathComponents.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((UriComponents.PathComponent)localIterator.next()).expand(paramUriTemplateVariables));
    }
    return new PathComponentComposite(localArrayList);
  }
  
  public String getPath()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = pathComponents.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((UriComponents.PathComponent)localIterator.next()).getPath());
    }
    return localStringBuilder.toString();
  }
  
  public List<String> getPathSegments()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = pathComponents.iterator();
    while (localIterator.hasNext()) {
      localArrayList.addAll(((UriComponents.PathComponent)localIterator.next()).getPathSegments());
    }
    return localArrayList;
  }
  
  public void verify()
  {
    Iterator localIterator = pathComponents.iterator();
    while (localIterator.hasNext()) {
      ((UriComponents.PathComponent)localIterator.next()).verify();
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.PathComponentComposite
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */