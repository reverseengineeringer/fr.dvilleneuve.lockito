package com.google.firebase.auth;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzalt;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;

public abstract class FirebaseUser
  implements UserInfo
{
  @NonNull
  public Task<Void> delete()
  {
    return zzcks().zzcjz().zzc(this);
  }
  
  @Nullable
  public abstract String getDisplayName();
  
  @Nullable
  public abstract String getEmail();
  
  @Nullable
  public abstract Uri getPhotoUrl();
  
  @NonNull
  public abstract List<? extends UserInfo> getProviderData();
  
  @NonNull
  public abstract String getProviderId();
  
  @Nullable
  public abstract List<String> getProviders();
  
  @NonNull
  public Task<GetTokenResult> getToken(boolean paramBoolean)
  {
    return zzcks().zzcjz().zza(this, paramBoolean);
  }
  
  @NonNull
  public abstract String getUid();
  
  public abstract boolean isAnonymous();
  
  @NonNull
  public Task<AuthResult> linkWithCredential(@NonNull AuthCredential paramAuthCredential)
  {
    zzab.zzaa(paramAuthCredential);
    return zzcks().zzcjz().zzb(this, paramAuthCredential);
  }
  
  public Task<Void> reauthenticate(@NonNull AuthCredential paramAuthCredential)
  {
    zzab.zzaa(paramAuthCredential);
    return zzcks().zzcjz().zza(this, paramAuthCredential);
  }
  
  @NonNull
  public Task<Void> reload()
  {
    return zzcks().zzcjz().zzb(this);
  }
  
  public Task<AuthResult> unlink(@NonNull String paramString)
  {
    zzab.zzhs(paramString);
    return zzcks().zzcjz().zza(this, paramString);
  }
  
  @NonNull
  public Task<Void> updateEmail(@NonNull String paramString)
  {
    zzab.zzhs(paramString);
    return zzcks().zzcjz().zzb(this, paramString);
  }
  
  @NonNull
  public Task<Void> updatePassword(@NonNull String paramString)
  {
    zzab.zzhs(paramString);
    return zzcks().zzcjz().zzc(this, paramString);
  }
  
  @NonNull
  public Task<Void> updateProfile(@NonNull UserProfileChangeRequest paramUserProfileChangeRequest)
  {
    zzab.zzaa(paramUserProfileChangeRequest);
    return zzcks().zzcjz().zza(this, paramUserProfileChangeRequest);
  }
  
  @NonNull
  public abstract FirebaseUser zzan(@NonNull List<? extends UserInfo> paramList);
  
  @NonNull
  public abstract FirebaseApp zzcks();
  
  @NonNull
  public abstract String zzckt();
  
  public abstract FirebaseUser zzcm(boolean paramBoolean);
  
  public abstract void zzql(@NonNull String paramString);
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.FirebaseUser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */