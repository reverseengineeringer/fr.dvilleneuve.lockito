package com.google.firebase.auth;

import android.net.Uri;
import android.support.annotation.Nullable;

public class UserProfileChangeRequest$Builder
{
  private boolean aNu;
  private boolean aNv;
  private Uri aNw;
  private String dH;
  
  public UserProfileChangeRequest build()
  {
    String str2 = dH;
    if (aNw == null) {}
    for (String str1 = null;; str1 = aNw.toString()) {
      return new UserProfileChangeRequest(1, str2, str1, aNu, aNv);
    }
  }
  
  public Builder setDisplayName(@Nullable String paramString)
  {
    if (paramString == null)
    {
      aNu = true;
      return this;
    }
    dH = paramString;
    return this;
  }
  
  public Builder setPhotoUri(@Nullable Uri paramUri)
  {
    if (paramUri == null)
    {
      aNv = true;
      return this;
    }
    aNw = paramUri;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.UserProfileChangeRequest.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */