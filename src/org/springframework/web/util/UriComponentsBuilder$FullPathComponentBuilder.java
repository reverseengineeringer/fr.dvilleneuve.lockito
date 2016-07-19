package org.springframework.web.util;

class UriComponentsBuilder$FullPathComponentBuilder
  implements UriComponentsBuilder.PathComponentBuilder
{
  private final StringBuilder path;
  
  private UriComponentsBuilder$FullPathComponentBuilder(String paramString)
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

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponentsBuilder.FullPathComponentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */