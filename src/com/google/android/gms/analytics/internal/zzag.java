package com.google.android.gms.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzab;

class zzag
  extends BroadcastReceiver
{
  static final String ad = zzag.class.getName();
  private boolean ae;
  private boolean af;
  private final zzf zzcws;
  
  zzag(zzf paramzzf)
  {
    zzab.zzaa(paramzzf);
    zzcws = paramzzf;
  }
  
  private Context getContext()
  {
    return zzcws.getContext();
  }
  
  private void zzadh()
  {
    zzyx();
    zzwd();
  }
  
  private zzb zzwd()
  {
    return zzcws.zzwd();
  }
  
  private zzaf zzyx()
  {
    return zzcws.zzyx();
  }
  
  public boolean isConnected()
  {
    if (!ae) {
      zzcws.zzyx().zzel("Connectivity unknown. Receiver not registered");
    }
    return af;
  }
  
  public boolean isRegistered()
  {
    return ae;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    zzadh();
    paramContext = paramIntent.getAction();
    zzcws.zzyx().zza("NetworkBroadcastReceiver received action", paramContext);
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext))
    {
      boolean bool = zzadj();
      if (af != bool)
      {
        af = bool;
        zzwd().zzas(bool);
      }
    }
    do
    {
      return;
      if (!"com.google.analytics.RADIO_POWERED".equals(paramContext)) {
        break;
      }
    } while (paramIntent.hasExtra(ad));
    zzwd().zzys();
    return;
    zzcws.zzyx().zzd("NetworkBroadcastReceiver received unknown action", paramContext);
  }
  
  public void unregister()
  {
    if (!isRegistered()) {
      return;
    }
    zzcws.zzyx().zzei("Unregistering connectivity change receiver");
    ae = false;
    af = false;
    Context localContext = getContext();
    try
    {
      localContext.unregisterReceiver(this);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      zzyx().zze("Failed to unregister the network broadcast receiver", localIllegalArgumentException);
    }
  }
  
  public void zzadg()
  {
    zzadh();
    if (ae) {
      return;
    }
    Context localContext = getContext();
    localContext.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    IntentFilter localIntentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
    localIntentFilter.addCategory(localContext.getPackageName());
    localContext.registerReceiver(this, localIntentFilter);
    af = zzadj();
    zzcws.zzyx().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(af));
    ae = true;
  }
  
  public void zzadi()
  {
    if (Build.VERSION.SDK_INT <= 10) {
      return;
    }
    Context localContext = getContext();
    Intent localIntent = new Intent("com.google.analytics.RADIO_POWERED");
    localIntent.addCategory(localContext.getPackageName());
    localIntent.putExtra(ad, true);
    localContext.sendOrderedBroadcast(localIntent, null);
  }
  
  protected boolean zzadj()
  {
    Object localObject = (ConnectivityManager)getContext().getSystemService("connectivity");
    try
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if (localObject != null)
      {
        boolean bool = ((NetworkInfo)localObject).isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (SecurityException localSecurityException) {}
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.internal.zzag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */