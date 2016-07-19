package com.google.android.gms.analytics;

import android.os.Process;

class zzi$zzc
  extends Thread
{
  zzi$zzc(Runnable paramRunnable, String paramString)
  {
    super(paramRunnable, paramString);
  }
  
  public void run()
  {
    Process.setThreadPriority(10);
    super.run();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.zzi.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */