package org.springframework.web.util;

final class UriComponentsBuilder$1
  implements UriComponentsBuilder.PathComponentBuilder
{
  public UriComponentsBuilder.PathComponentBuilder appendPath(String paramString)
  {
    return new UriComponentsBuilder.FullPathComponentBuilder(paramString, null);
  }
  
  public UriComponentsBuilder.PathComponentBuilder appendPathSegments(String... paramVarArgs)
  {
    return new UriComponentsBuilder.PathSegmentComponentBuilder(paramVarArgs, null);
  }
  
  public UriComponents.PathComponent build()
  {
    return UriComponents.NULL_PATH_COMPONENT;
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponentsBuilder.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */