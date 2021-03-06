package com.google.firebase.iid;

import android.support.annotation.Nullable;

public class zzc
{
  private final FirebaseInstanceId baD;
  
  private zzc(FirebaseInstanceId paramFirebaseInstanceId)
  {
    baD = paramFirebaseInstanceId;
  }
  
  public static zzc zzcwt()
  {
    return new zzc(FirebaseInstanceId.getInstance());
  }
  
  public String getId()
  {
    return baD.getId();
  }
  
  @Nullable
  public String getToken()
  {
    return baD.getToken();
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.iid.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */