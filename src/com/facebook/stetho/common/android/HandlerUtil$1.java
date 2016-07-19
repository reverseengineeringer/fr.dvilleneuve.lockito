package com.facebook.stetho.common.android;

import com.facebook.stetho.common.UncheckedCallable;

final class HandlerUtil$1
  extends HandlerUtil.WaitableRunnable<V>
{
  HandlerUtil$1(UncheckedCallable paramUncheckedCallable) {}
  
  protected V onRun()
  {
    return (V)val$c.call();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.HandlerUtil.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */