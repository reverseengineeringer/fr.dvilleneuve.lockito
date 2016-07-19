package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzk
{
  private static final Lock eu = new ReentrantLock();
  private static zzk ev;
  private final Lock ew = new ReentrantLock();
  private final SharedPreferences ex;
  
  zzk(Context paramContext)
  {
    ex = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  public static zzk zzbc(Context paramContext)
  {
    zzab.zzaa(paramContext);
    eu.lock();
    try
    {
      if (ev == null) {
        ev = new zzk(paramContext.getApplicationContext());
      }
      paramContext = ev;
      return paramContext;
    }
    finally
    {
      eu.unlock();
    }
  }
  
  private String zzy(String paramString1, String paramString2)
  {
    String str = String.valueOf(":");
    return String.valueOf(paramString1).length() + 0 + String.valueOf(str).length() + String.valueOf(paramString2).length() + paramString1 + str + paramString2;
  }
  
  void zza(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions)
  {
    zzab.zzaa(paramGoogleSignInAccount);
    zzab.zzaa(paramGoogleSignInOptions);
    String str = paramGoogleSignInAccount.zzafm();
    zzx(zzy("googleSignInAccount", str), paramGoogleSignInAccount.zzafo());
    zzx(zzy("googleSignInOptions", str), paramGoogleSignInOptions.zzafn());
  }
  
  public GoogleSignInAccount zzagj()
  {
    return zzft(zzfv("defaultGoogleSignInAccount"));
  }
  
  public GoogleSignInOptions zzagk()
  {
    return zzfu(zzfv("defaultGoogleSignInAccount"));
  }
  
  public void zzagl()
  {
    String str = zzfv("defaultGoogleSignInAccount");
    zzfx("defaultGoogleSignInAccount");
    zzfw(str);
  }
  
  public void zzb(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions)
  {
    zzab.zzaa(paramGoogleSignInAccount);
    zzab.zzaa(paramGoogleSignInOptions);
    zzx("defaultGoogleSignInAccount", paramGoogleSignInAccount.zzafm());
    zza(paramGoogleSignInAccount, paramGoogleSignInOptions);
  }
  
  GoogleSignInAccount zzft(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      paramString = zzfv(zzy("googleSignInAccount", paramString));
    } while (paramString == null);
    try
    {
      paramString = GoogleSignInAccount.zzfp(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
  }
  
  GoogleSignInOptions zzfu(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      paramString = zzfv(zzy("googleSignInOptions", paramString));
    } while (paramString == null);
    try
    {
      paramString = GoogleSignInOptions.zzfr(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
  }
  
  protected String zzfv(String paramString)
  {
    ew.lock();
    try
    {
      paramString = ex.getString(paramString, null);
      return paramString;
    }
    finally
    {
      ew.unlock();
    }
  }
  
  void zzfw(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    zzfx(zzy("googleSignInAccount", paramString));
    zzfx(zzy("googleSignInOptions", paramString));
  }
  
  protected void zzfx(String paramString)
  {
    ew.lock();
    try
    {
      ex.edit().remove(paramString).apply();
      return;
    }
    finally
    {
      ew.unlock();
    }
  }
  
  protected void zzx(String paramString1, String paramString2)
  {
    ew.lock();
    try
    {
      ex.edit().putString(paramString1, paramString2).apply();
      return;
    }
    finally
    {
      ew.unlock();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.auth.api.signin.internal.zzk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */