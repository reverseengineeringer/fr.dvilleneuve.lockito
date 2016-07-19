package com.facebook.stetho.dumpapp.plugins;

import com.facebook.stetho.common.ExceptionUtil;

class CrashDumperPlugin$ThrowRunnable
  implements Runnable
{
  private final Throwable mThrowable;
  
  public CrashDumperPlugin$ThrowRunnable(Throwable paramThrowable)
  {
    mThrowable = paramThrowable;
  }
  
  public void run()
  {
    ExceptionUtil.sneakyThrow(mThrowable);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin.ThrowRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */