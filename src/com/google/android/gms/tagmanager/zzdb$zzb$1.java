package com.google.android.gms.tagmanager;

import android.os.Handler.Callback;
import android.os.Message;

class zzdb$zzb$1
  implements Handler.Callback
{
  zzdb$zzb$1(zzdb.zzb paramzzb) {}
  
  public boolean handleMessage(Message paramMessage)
  {
    if ((1 == what) && (zzdb.zzcdc().equals(obj)))
    {
      ayv.ayu.dispatch();
      if (!zzdb.zzb(ayv.ayu)) {
        ayv.zzv(zzdb.zzc(ayv.ayu));
      }
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdb.zzb.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */