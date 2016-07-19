package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions
  extends AbstractSafeParcelable
  implements Api.ApiOptions.Optional
{
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzb();
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  private static Comparator<Scope> dO = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzaoh().compareTo(paramAnonymousScope2.zzaoh());
    }
  };
  public static final Scope dP = new Scope("profile");
  public static final Scope dQ = new Scope("email");
  public static final Scope dR = new Scope("openid");
  private Account aP;
  private final ArrayList<Scope> dS;
  private boolean dT;
  private final boolean dU;
  private final boolean dV;
  private String dW;
  private String dX;
  final int versionCode;
  
  static
  {
    DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2)
  {
    versionCode = paramInt;
    dS = paramArrayList;
    aP = paramAccount;
    dT = paramBoolean1;
    dU = paramBoolean2;
    dV = paramBoolean3;
    dW = paramString1;
    dX = paramString2;
  }
  
  private GoogleSignInOptions(Set<Scope> paramSet, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2)
  {
    this(2, new ArrayList(paramSet), paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2);
  }
  
  private JSONObject zzafp()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(dS, dO);
      Iterator localIterator = dS.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzaoh());
      }
      localJSONException.put("scopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    if (aP != null) {
      localJSONException.put("accountName", aP.name);
    }
    localJSONException.put("idTokenRequested", dT);
    localJSONException.put("forceCodeForRefreshToken", dV);
    localJSONException.put("serverAuthRequested", dU);
    if (!TextUtils.isEmpty(dW)) {
      localJSONException.put("serverClientId", dW);
    }
    if (!TextUtils.isEmpty(dX)) {
      localJSONException.put("hostedDomain", dX);
    }
    return localJSONException;
  }
  
  @Nullable
  public static GoogleSignInOptions zzfr(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    HashSet localHashSet = new HashSet();
    paramString = localJSONObject.getJSONArray("scopes");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new Scope(paramString.getString(i)));
      i += 1;
    }
    paramString = localJSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = new Account(paramString, "com.google");; paramString = null) {
      return new GoogleSignInOptions(localHashSet, paramString, localJSONObject.getBoolean("idTokenRequested"), localJSONObject.getBoolean("serverAuthRequested"), localJSONObject.getBoolean("forceCodeForRefreshToken"), localJSONObject.optString("serverClientId", null), localJSONObject.optString("hostedDomain", null));
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return false;
      try
      {
        paramObject = (GoogleSignInOptions)paramObject;
        if ((dS.size() != ((GoogleSignInOptions)paramObject).zzafq().size()) || (!dS.containsAll(((GoogleSignInOptions)paramObject).zzafq()))) {
          continue;
        }
        if (aP == null)
        {
          if (((GoogleSignInOptions)paramObject).getAccount() != null) {
            continue;
          }
          label56:
          if (!TextUtils.isEmpty(dW)) {
            break label128;
          }
          if (!TextUtils.isEmpty(((GoogleSignInOptions)paramObject).zzafu())) {
            continue;
          }
        }
        while ((dV == ((GoogleSignInOptions)paramObject).zzaft()) && (dT == ((GoogleSignInOptions)paramObject).zzafr()) && (dU == ((GoogleSignInOptions)paramObject).zzafs()))
        {
          return true;
          if (!aP.equals(((GoogleSignInOptions)paramObject).getAccount())) {
            break;
          }
          break label56;
          label128:
          boolean bool = dW.equals(((GoogleSignInOptions)paramObject).zzafu());
          if (!bool) {
            break;
          }
        }
        return false;
      }
      catch (ClassCastException paramObject) {}
    }
  }
  
  public Account getAccount()
  {
    return aP;
  }
  
  public Scope[] getScopeArray()
  {
    return (Scope[])dS.toArray(new Scope[dS.size()]);
  }
  
  public int hashCode()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = dS.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Scope)localIterator.next()).zzaoh());
    }
    Collections.sort(localArrayList);
    return new zze().zzr(localArrayList).zzr(aP).zzr(dW).zzba(dV).zzba(dT).zzba(dU).zzagc();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public String zzafn()
  {
    return zzafp().toString();
  }
  
  public ArrayList<Scope> zzafq()
  {
    return new ArrayList(dS);
  }
  
  public boolean zzafr()
  {
    return dT;
  }
  
  public boolean zzafs()
  {
    return dU;
  }
  
  public boolean zzaft()
  {
    return dV;
  }
  
  public String zzafu()
  {
    return dW;
  }
  
  public String zzafv()
  {
    return dX;
  }
  
  public static final class Builder
  {
    private Account aP;
    private boolean dT;
    private boolean dU;
    private boolean dV;
    private String dW;
    private String dX;
    private Set<Scope> dY = new HashSet();
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions paramGoogleSignInOptions)
    {
      zzab.zzaa(paramGoogleSignInOptions);
      dY = new HashSet(GoogleSignInOptions.zzb(paramGoogleSignInOptions));
      dU = GoogleSignInOptions.zzc(paramGoogleSignInOptions);
      dV = GoogleSignInOptions.zzd(paramGoogleSignInOptions);
      dT = GoogleSignInOptions.zze(paramGoogleSignInOptions);
      dW = GoogleSignInOptions.zzf(paramGoogleSignInOptions);
      aP = GoogleSignInOptions.zzg(paramGoogleSignInOptions);
      dX = GoogleSignInOptions.zzh(paramGoogleSignInOptions);
    }
    
    private String zzfs(String paramString)
    {
      zzab.zzhs(paramString);
      if ((dW == null) || (dW.equals(paramString))) {}
      for (boolean bool = true;; bool = false)
      {
        zzab.zzb(bool, "two different server client ids provided");
        return paramString;
      }
    }
    
    public GoogleSignInOptions build()
    {
      if ((dT) && ((aP == null) || (!dY.isEmpty()))) {
        requestId();
      }
      return new GoogleSignInOptions(dY, aP, dT, dU, dV, dW, dX, null);
    }
    
    public Builder requestEmail()
    {
      dY.add(GoogleSignInOptions.dQ);
      return this;
    }
    
    public Builder requestId()
    {
      dY.add(GoogleSignInOptions.dR);
      return this;
    }
    
    public Builder requestIdToken(String paramString)
    {
      dT = true;
      dW = zzfs(paramString);
      return this;
    }
    
    public Builder requestProfile()
    {
      dY.add(GoogleSignInOptions.dP);
      return this;
    }
    
    public Builder requestScopes(Scope paramScope, Scope... paramVarArgs)
    {
      dY.add(paramScope);
      dY.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public Builder requestServerAuthCode(String paramString)
    {
      return requestServerAuthCode(paramString, false);
    }
    
    public Builder requestServerAuthCode(String paramString, boolean paramBoolean)
    {
      dU = true;
      dW = zzfs(paramString);
      dV = paramBoolean;
      return this;
    }
    
    public Builder setAccountName(String paramString)
    {
      aP = new Account(zzab.zzhs(paramString), "com.google");
      return this;
    }
    
    public Builder setHostedDomain(String paramString)
    {
      dX = zzab.zzhs(paramString);
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.auth.api.signin.GoogleSignInOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */