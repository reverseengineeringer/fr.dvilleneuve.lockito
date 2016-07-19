package org.springframework.web.util;

abstract interface UriComponentsBuilder$PathComponentBuilder
{
  public abstract PathComponentBuilder appendPath(String paramString);
  
  public abstract PathComponentBuilder appendPathSegments(String... paramVarArgs);
  
  public abstract UriComponents.PathComponent build();
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponentsBuilder.PathComponentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */