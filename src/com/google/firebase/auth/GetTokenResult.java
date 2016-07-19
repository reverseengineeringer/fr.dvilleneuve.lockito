package com.google.firebase.auth;

import android.support.annotation.Nullable;

public class GetTokenResult
{
  private String ct;
  
  public GetTokenResult(String paramString)
  {
    ct = paramString;
  }
  
  @Nullable
  public String getToken()
  {
    return ct;
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.GetTokenResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */