package org.springframework.web.util;

 enum UriComponents$Type$9
{
  UriComponents$Type$9()
  {
    super(paramString, paramInt, null);
  }
  
  public boolean isAllowed(int paramInt)
  {
    if ((61 == paramInt) || (43 == paramInt) || (38 == paramInt)) {}
    while ((!isPchar(paramInt)) && (47 != paramInt) && (63 != paramInt)) {
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.util.UriComponents.Type.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */