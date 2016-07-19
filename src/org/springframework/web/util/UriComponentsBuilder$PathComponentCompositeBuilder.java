package org.springframework.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class UriComponentsBuilder$PathComponentCompositeBuilder
  implements UriComponentsBuilder.PathComponentBuilder
{
  private final List<UriComponentsBuilder.PathComponentBuilder> pathComponentBuilders = new ArrayList();
  
  private UriComponentsBuilder$PathComponentCompositeBuilder(UriComponentsBuilder.PathComponentBuilder paramPathComponentBuilder)
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

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponentsBuilder.PathComponentCompositeBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */