package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzab;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class GoogleSignInOptions$Builder
{
  private Account aP;
  private boolean dT;
  private boolean dU;
  private boolean dV;
  private String dW;
  private String dX;
  private Set<Scope> dY = new HashSet();
  
  public GoogleSignInOptions$Builder() {}
  
  public GoogleSignInOptions$Builder(@NonNull GoogleSignInOptions paramGoogleSignInOptions)
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

/* Location:
 * Qualified Name:     com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */