package com.google.firebase.auth;

import android.net.Uri;
import android.support.annotation.Nullable;

public abstract interface UserInfo
{
  @Nullable
  public abstract String getDisplayName();
  
  @Nullable
  public abstract String getEmail();
  
  @Nullable
  public abstract Uri getPhotoUrl();
  
  public abstract String getProviderId();
  
  public abstract String getUid();
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.UserInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */