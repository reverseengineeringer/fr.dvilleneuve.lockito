package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.UserProfileChangeRequest;

public abstract interface zzalt
{
  @Nullable
  public abstract FirebaseUser getCurrentUser();
  
  @NonNull
  public abstract Task<Void> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull AuthCredential paramAuthCredential);
  
  @NonNull
  public abstract Task<Void> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull UserProfileChangeRequest paramUserProfileChangeRequest);
  
  @NonNull
  public abstract Task<AuthResult> zza(@NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString);
  
  @NonNull
  public abstract Task<GetTokenResult> zza(@Nullable FirebaseUser paramFirebaseUser, boolean paramBoolean);
  
  @NonNull
  public abstract Task<Void> zzb(@NonNull FirebaseUser paramFirebaseUser);
  
  @NonNull
  public abstract Task<AuthResult> zzb(@NonNull FirebaseUser paramFirebaseUser, @NonNull AuthCredential paramAuthCredential);
  
  @NonNull
  public abstract Task<Void> zzb(@NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString);
  
  @NonNull
  public abstract Task<Void> zzc(@NonNull FirebaseUser paramFirebaseUser);
  
  @NonNull
  public abstract Task<Void> zzc(@NonNull FirebaseUser paramFirebaseUser, @NonNull String paramString);
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzalt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */