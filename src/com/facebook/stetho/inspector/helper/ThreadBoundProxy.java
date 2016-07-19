package com.facebook.stetho.inspector.helper;

import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;

public abstract class ThreadBoundProxy
  implements ThreadBound
{
  private final ThreadBound mEnforcer;
  
  public ThreadBoundProxy(ThreadBound paramThreadBound)
  {
    mEnforcer = ((ThreadBound)Util.throwIfNull(paramThreadBound));
  }
  
  public final boolean checkThreadAccess()
  {
    return mEnforcer.checkThreadAccess();
  }
  
  public final <V> V postAndWait(UncheckedCallable<V> paramUncheckedCallable)
  {
    return (V)mEnforcer.postAndWait(paramUncheckedCallable);
  }
  
  public final void postAndWait(Runnable paramRunnable)
  {
    mEnforcer.postAndWait(paramRunnable);
  }
  
  public final void postDelayed(Runnable paramRunnable, long paramLong)
  {
    mEnforcer.postDelayed(paramRunnable, paramLong);
  }
  
  public final void removeCallbacks(Runnable paramRunnable)
  {
    mEnforcer.removeCallbacks(paramRunnable);
  }
  
  public final void verifyThreadAccess()
  {
    mEnforcer.verifyThreadAccess();
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.helper.ThreadBoundProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */