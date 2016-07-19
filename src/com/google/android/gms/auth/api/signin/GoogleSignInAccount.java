package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zza();
  public static zze dF = zzh.zzavi();
  private static Comparator<Scope> dO = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzaoh().compareTo(paramAnonymousScope2.zzaoh());
    }
  };
  List<Scope> cx;
  private String dG;
  private String dH;
  private Uri dI;
  private String dJ;
  private long dK;
  private String dL;
  private String dM;
  private String dN;
  private String dd;
  final int versionCode;
  private String zzbgk;
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList, String paramString7, String paramString8)
  {
    versionCode = paramInt;
    zzbgk = paramString1;
    dd = paramString2;
    dG = paramString3;
    dH = paramString4;
    dI = paramUri;
    dJ = paramString5;
    dK = paramLong;
    dL = paramString6;
    cx = paramList;
    dM = paramString7;
    dN = paramString8;
  }
  
  public static GoogleSignInAccount zza(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString7, @NonNull Set<Scope> paramSet)
  {
    Long localLong = paramLong;
    if (paramLong == null) {
      localLong = Long.valueOf(dF.currentTimeMillis() / 1000L);
    }
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, localLong.longValue(), zzab.zzhs(paramString7), new ArrayList((Collection)zzab.zzaa(paramSet)), paramString5, paramString6);
  }
  
  private JSONObject zzafp()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (getId() != null) {
        localJSONObject.put("id", getId());
      }
      if (getIdToken() != null) {
        localJSONObject.put("tokenId", getIdToken());
      }
      if (getEmail() != null) {
        localJSONObject.put("email", getEmail());
      }
      if (getDisplayName() != null) {
        localJSONObject.put("displayName", getDisplayName());
      }
      if (getGivenName() != null) {
        localJSONObject.put("givenName", getGivenName());
      }
      if (getFamilyName() != null) {
        localJSONObject.put("familyName", getFamilyName());
      }
      if (getPhotoUrl() != null) {
        localJSONObject.put("photoUrl", getPhotoUrl().toString());
      }
      if (getServerAuthCode() != null) {
        localJSONObject.put("serverAuthCode", getServerAuthCode());
      }
      localJSONObject.put("expirationTime", dK);
      localJSONObject.put("obfuscatedIdentifier", zzafm());
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(cx, dO);
      Iterator localIterator = cx.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzaoh());
      }
      localJSONException.put("grantedScopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    return localJSONException;
  }
  
  @Nullable
  public static GoogleSignInAccount zzfp(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = Uri.parse(paramString);; paramString = null)
    {
      long l = Long.parseLong(localJSONObject.getString("expirationTime"));
      HashSet localHashSet = new HashSet();
      JSONArray localJSONArray = localJSONObject.getJSONArray("grantedScopes");
      int j = localJSONArray.length();
      int i = 0;
      while (i < j)
      {
        localHashSet.add(new Scope(localJSONArray.getString(i)));
        i += 1;
      }
      return zza(localJSONObject.optString("id"), localJSONObject.optString("tokenId", null), localJSONObject.optString("email", null), localJSONObject.optString("displayName", null), localJSONObject.optString("givenName", null), localJSONObject.optString("familyName", null), paramString, Long.valueOf(l), localJSONObject.getString("obfuscatedIdentifier"), localHashSet).zzfq(localJSONObject.optString("serverAuthCode", null));
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof GoogleSignInAccount)) {
      return false;
    }
    return ((GoogleSignInAccount)paramObject).zzafn().equals(zzafn());
  }
  
  @Nullable
  public String getDisplayName()
  {
    return dH;
  }
  
  @Nullable
  public String getEmail()
  {
    return dG;
  }
  
  @Nullable
  public String getFamilyName()
  {
    return dN;
  }
  
  @Nullable
  public String getGivenName()
  {
    return dM;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes()
  {
    return new HashSet(cx);
  }
  
  @Nullable
  public String getId()
  {
    return zzbgk;
  }
  
  @Nullable
  public String getIdToken()
  {
    return dd;
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    return dI;
  }
  
  @Nullable
  public String getServerAuthCode()
  {
    return dJ;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public boolean zza()
  {
    return dF.currentTimeMillis() / 1000L >= dK - 300L;
  }
  
  public long zzafl()
  {
    return dK;
  }
  
  @NonNull
  public String zzafm()
  {
    return dL;
  }
  
  public String zzafn()
  {
    return zzafp().toString();
  }
  
  public String zzafo()
  {
    JSONObject localJSONObject = zzafp();
    localJSONObject.remove("serverAuthCode");
    return localJSONObject.toString();
  }
  
  public GoogleSignInAccount zzfq(String paramString)
  {
    dJ = paramString;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.auth.api.signin.GoogleSignInAccount
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */