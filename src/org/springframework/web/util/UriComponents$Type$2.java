package org.springframework.web.util;

 enum UriComponents$Type$2
{
  UriComponents$Type$2()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean isAllowed(int paramInt)
  {
    return (isUnreserved(paramInt)) || (isSubDelimiter(paramInt)) || (58 == paramInt) || (64 == paramInt);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.Type.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */