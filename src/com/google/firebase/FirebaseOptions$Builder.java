package com.google.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzab;

public final class FirebaseOptions$Builder
{
  private String aMW;
  private String aMX;
  private String gs;
  private String uS;
  private String uV;
  private String uW;
  
  public FirebaseOptions$Builder() {}
  
  public FirebaseOptions$Builder(FirebaseOptions paramFirebaseOptions)
  {
    gs = FirebaseOptions.zza(paramFirebaseOptions);
    uS = FirebaseOptions.zzb(paramFirebaseOptions);
    aMW = FirebaseOptions.zzc(paramFirebaseOptions);
    aMX = FirebaseOptions.zzd(paramFirebaseOptions);
    uV = FirebaseOptions.zze(paramFirebaseOptions);
    uW = FirebaseOptions.zzf(paramFirebaseOptions);
  }
  
  public FirebaseOptions build()
  {
    return new FirebaseOptions(gs, uS, aMW, aMX, uV, uW, null);
  }
  
  public Builder setApiKey(@NonNull String paramString)
  {
    uS = zzab.zzh(paramString, "ApiKey must be set.");
    return this;
  }
  
  public Builder setApplicationId(@NonNull String paramString)
  {
    gs = zzab.zzh(paramString, "ApplicationId must be set.");
    return this;
  }
  
  public Builder setDatabaseUrl(@Nullable String paramString)
  {
    aMW = paramString;
    return this;
  }
  
  public Builder setGcmSenderId(@Nullable String paramString)
  {
    uV = paramString;
    return this;
  }
  
  public Builder setStorageBucket(@Nullable String paramString)
  {
    uW = paramString;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.firebase.FirebaseOptions.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */