package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzau
  extends Thread
  implements zzat
{
  private static zzau awu;
  private final LinkedBlockingQueue<Runnable> awt = new LinkedBlockingQueue();
  private volatile zzav awv;
  private volatile boolean mClosed = false;
  private final Context mContext;
  private volatile boolean zzbti = false;
  
  private zzau(Context paramContext)
  {
    super("GAThread");
    if (paramContext != null) {}
    for (mContext = paramContext.getApplicationContext();; mContext = paramContext)
    {
      start();
      return;
    }
  }
  
  static zzau zzdz(Context paramContext)
  {
    if (awu == null) {
      awu = new zzau(paramContext);
    }
    return awu;
  }
  
  private String zze(Throwable paramThrowable)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream localPrintStream = new PrintStream(localByteArrayOutputStream);
    paramThrowable.printStackTrace(localPrintStream);
    localPrintStream.flush();
    return new String(localByteArrayOutputStream.toByteArray());
  }
  
  public void run()
  {
    for (;;)
    {
      if (!mClosed) {
        try
        {
          Runnable localRunnable = (Runnable)awt.take();
          if (!zzbti) {
            localRunnable.run();
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          zzbn.zzcx(localInterruptedException.toString());
        }
        catch (Throwable localThrowable)
        {
          String str = String.valueOf(zze(localThrowable));
          if (str.length() != 0) {}
          for (str = "Error on Google TagManager Thread: ".concat(str);; str = new String("Error on Google TagManager Thread: "))
          {
            zzbn.e(str);
            zzbn.e("Google TagManager is shutting down.");
            zzbti = true;
            break;
          }
        }
      }
    }
  }
  
  void zzl(String paramString, final long paramLong)
  {
    zzo(new Runnable()
    {
      public void run()
      {
        if (zzau.zza(zzau.this) == null)
        {
          zzdb localzzdb = zzdb.zzccy();
          localzzdb.zza(zzau.zzb(zzau.this), jdField_this);
          zzau.zza(zzau.this, localzzdb.zzcdb());
        }
        zzau.zza(zzau.this).zzg(paramLong, zzbjl);
      }
    });
  }
  
  public void zzo(Runnable paramRunnable)
  {
    awt.add(paramRunnable);
  }
  
  public void zzof(String paramString)
  {
    zzl(paramString, System.currentTimeMillis());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzau
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */