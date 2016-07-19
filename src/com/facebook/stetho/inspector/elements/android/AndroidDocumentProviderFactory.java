package com.facebook.stetho.inspector.elements.android;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.common.android.HandlerUtil;
import com.facebook.stetho.inspector.elements.DocumentProvider;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;

public final class AndroidDocumentProviderFactory
  implements DocumentProviderFactory, ThreadBound
{
  private final Application mApplication;
  private final Handler mHandler;
  
  public AndroidDocumentProviderFactory(Application paramApplication)
  {
    mApplication = ((Application)Util.throwIfNull(paramApplication));
    mHandler = new Handler(Looper.getMainLooper());
  }
  
  public boolean checkThreadAccess()
  {
    return HandlerUtil.checkThreadAccess(mHandler);
  }
  
  public DocumentProvider create()
  {
    return new AndroidDocumentProvider(mApplication, this);
  }
  
  public <V> V postAndWait(UncheckedCallable<V> paramUncheckedCallable)
  {
    return (V)HandlerUtil.postAndWait(mHandler, paramUncheckedCallable);
  }
  
  public void postAndWait(Runnable paramRunnable)
  {
    HandlerUtil.postAndWait(mHandler, paramRunnable);
  }
  
  public void postDelayed(Runnable paramRunnable, long paramLong)
  {
    if (!mHandler.postDelayed(paramRunnable, paramLong)) {
      throw new RuntimeException("Handler.postDelayed() returned false");
    }
  }
  
  public void removeCallbacks(Runnable paramRunnable)
  {
    mHandler.removeCallbacks(paramRunnable);
  }
  
  public void verifyThreadAccess()
  {
    HandlerUtil.verifyThreadAccess(mHandler);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProviderFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */