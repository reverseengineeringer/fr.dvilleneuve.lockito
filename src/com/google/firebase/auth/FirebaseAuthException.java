package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzab;
import com.google.firebase.FirebaseException;

public class FirebaseAuthException
  extends FirebaseException
{
  private final String aNs;
  
  public FirebaseAuthException(@NonNull String paramString1, @NonNull String paramString2)
  {
    super(paramString2);
    aNs = zzab.zzhs(paramString1);
  }
  
  @NonNull
  public String getErrorCode()
  {
    return aNs;
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.FirebaseAuthException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */