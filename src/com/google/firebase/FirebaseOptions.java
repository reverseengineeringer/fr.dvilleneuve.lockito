package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.util.zzw;

public final class FirebaseOptions
{
  private final String aMW;
  private final String aMX;
  private final String gs;
  private final String uS;
  private final String uV;
  private final String uW;
  
  private FirebaseOptions(@NonNull String paramString1, @NonNull String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6)
  {
    if (!zzw.zzic(paramString1)) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zza(bool, "ApplicationId must be set.");
      gs = paramString1;
      uS = paramString2;
      aMW = paramString3;
      aMX = paramString4;
      uV = paramString5;
      uW = paramString6;
      return;
    }
  }
  
  public static FirebaseOptions fromResource(Context paramContext)
  {
    paramContext = new zzai(paramContext);
    String str = paramContext.getString("google_app_id");
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return new FirebaseOptions(str, paramContext.getString("google_api_key"), paramContext.getString("firebase_database_url"), paramContext.getString("ga_trackingId"), paramContext.getString("gcm_defaultSenderId"), paramContext.getString("google_storage_bucket"));
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseOptions)) {}
    do
    {
      return false;
      paramObject = (FirebaseOptions)paramObject;
    } while ((!zzaa.equal(gs, gs)) || (!zzaa.equal(uS, uS)) || (!zzaa.equal(aMW, aMW)) || (!zzaa.equal(aMX, aMX)) || (!zzaa.equal(uV, uV)) || (!zzaa.equal(uW, uW)));
    return true;
  }
  
  public String getApiKey()
  {
    return uS;
  }
  
  public String getApplicationId()
  {
    return gs;
  }
  
  public String getDatabaseUrl()
  {
    return aMW;
  }
  
  public String getGcmSenderId()
  {
    return uV;
  }
  
  public String getStorageBucket()
  {
    return uW;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { gs, uS, aMW, aMX, uV, uW });
  }
  
  public String toString()
  {
    return zzaa.zzz(this).zzg("applicationId", gs).zzg("apiKey", uS).zzg("databaseUrl", aMW).zzg("gcmSenderId", uV).zzg("storageBucket", uW).toString();
  }
  
  public static final class Builder
  {
    private String aMW;
    private String aMX;
    private String gs;
    private String uS;
    private String uV;
    private String uW;
    
    public Builder() {}
    
    public Builder(FirebaseOptions paramFirebaseOptions)
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
}

/* Location:
 * Qualified Name:     com.google.firebase.FirebaseOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */