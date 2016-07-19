package org.springframework.web.util;

 enum UriComponents$Type$1
{
  UriComponents$Type$1()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean isAllowed(int paramInt)
  {
    return (isAlpha(paramInt)) || (isDigit(paramInt)) || (43 == paramInt) || (45 == paramInt) || (46 == paramInt);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.Type.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */