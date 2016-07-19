package org.springframework.web.util;

 enum UriComponents$Type$8
{
  UriComponents$Type$8()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean isAllowed(int paramInt)
  {
    return (isPchar(paramInt)) || (47 == paramInt) || (63 == paramInt);
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.Type.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */