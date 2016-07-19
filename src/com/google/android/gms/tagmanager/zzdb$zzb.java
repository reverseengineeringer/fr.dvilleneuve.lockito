package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class zzdb$zzb
  implements zzdb.zza
{
  private Handler handler = new Handler(zzdb.zza(ayu).getMainLooper(), new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      if ((1 == what) && (zzdb.zzcdc().equals(obj)))
      {
        ayu.dispatch();
        if (!zzdb.zzb(ayu)) {
          zzv(zzdb.zzc(ayu));
        }
      }
      return true;
    }
  });
  
  private zzdb$zzb(zzdb paramzzdb) {}
  
  private Message obtainMessage()
  {
    return handler.obtainMessage(1, zzdb.zzcdc());
  }
  
  public void cancel()
  {
    handler.removeMessages(1, zzdb.zzcdc());
  }
  
  public void zzcdd()
  {
    handler.removeMessages(1, zzdb.zzcdc());
    handler.sendMessage(obtainMessage());
  }
  
  public void zzv(long paramLong)
  {
    handler.removeMessages(1, zzdb.zzcdc());
    handler.sendMessageDelayed(obtainMessage(), paramLong);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdb.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */