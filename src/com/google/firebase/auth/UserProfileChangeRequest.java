package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class UserProfileChangeRequest
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<UserProfileChangeRequest> CREATOR = new zza();
  private String Ld;
  private boolean aNu;
  private boolean aNv;
  private Uri aNw;
  private String dH;
  public final int mVersionCode;
  
  UserProfileChangeRequest(int paramInt, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    mVersionCode = paramInt;
    dH = paramString1;
    Ld = paramString2;
    aNu = paramBoolean1;
    aNv = paramBoolean2;
    if (TextUtils.isEmpty(paramString2)) {}
    for (paramString1 = null;; paramString1 = Uri.parse(paramString2))
    {
      aNw = paramString1;
      return;
    }
  }
  
  @Nullable
  public String getDisplayName()
  {
    return dH;
  }
  
  @Nullable
  public Uri getPhotoUri()
  {
    return aNw;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public String zzckv()
  {
    return Ld;
  }
  
  public boolean zzckw()
  {
    return aNu;
  }
  
  public boolean zzckx()
  {
    return aNv;
  }
  
  public static class Builder
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
}

/* Location:
 * Qualified Name:     com.google.firebase.auth.UserProfileChangeRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */