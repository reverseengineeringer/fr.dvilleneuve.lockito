package org.springframework.web.util;

 enum UriComponents$Type$3
{
  UriComponents$Type$3()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean isAllowed(int paramInt)
  {
    return (isUnreserved(paramInt)) || (isSubDelimiter(paramInt)) || (58 == paramInt);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.Type.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */