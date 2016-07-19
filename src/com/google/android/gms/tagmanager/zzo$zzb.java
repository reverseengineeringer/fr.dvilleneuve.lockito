package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class zzo$zzb
  extends Handler
{
  private final ContainerHolder.ContainerAvailableListener avm;
  
  public zzo$zzb(zzo paramzzo, ContainerHolder.ContainerAvailableListener paramContainerAvailableListener, Looper paramLooper)
  {
    super(paramLooper);
    avm = paramContainerAvailableListener;
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      zzbn.e("Don't know how to handle this message.");
      return;
    }
    zznu((String)obj);
  }
  
  public void zznt(String paramString)
  {
    sendMessage(obtainMessage(1, paramString));
  }
  
  protected void zznu(String paramString)
  {
    avm.onContainerAvailable(avn, paramString);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzo.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */