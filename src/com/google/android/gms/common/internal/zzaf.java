package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public final class zzaf
  extends zzg<zzx>
{
  private static final zzaf za = new zzaf();
  
  private zzaf()
  {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }
  
  public static View zzb(Context paramContext, int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
    throws zzg.zza
  {
    return za.zzc(paramContext, paramInt1, paramInt2, paramArrayOfScope);
  }
  
  private View zzc(Context paramContext, int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
    throws zzg.zza
  {
    try
    {
      paramArrayOfScope = new SignInButtonConfig(paramInt1, paramInt2, paramArrayOfScope);
      zzd localzzd = zze.zzae(paramContext);
      paramContext = (View)zze.zzad(((zzx)zzcr(paramContext)).zza(localzzd, paramArrayOfScope));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      throw new zzg.zza(64 + "Could not get button with size " + paramInt1 + " and color " + paramInt2, paramContext);
    }
  }
  
  public zzx zzdx(IBinder paramIBinder)
  {
    return zzx.zza.zzdw(paramIBinder);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.internal.zzaf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */