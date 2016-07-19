package org.springframework.web.util;

 enum UriComponents$Type$4
{
  UriComponents$Type$4()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean isAllowed(int paramInt)
  {
    return (isUnreserved(paramInt)) || (isSubDelimiter(paramInt));
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.Type.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */