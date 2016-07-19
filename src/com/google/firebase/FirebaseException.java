package com.google.firebase;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzab;

public class FirebaseException
  extends Exception
{
  @Deprecated
  protected FirebaseException() {}
  
  public FirebaseException(@NonNull String paramString)
  {
    super(zzab.zzh(paramString, "Detail message must not be empty"));
  }
  
  public FirebaseException(@NonNull String paramString, Throwable paramThrowable)
  {
    super(zzab.zzh(paramString, "Detail message must not be empty"), paramThrowable);
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.FirebaseException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */