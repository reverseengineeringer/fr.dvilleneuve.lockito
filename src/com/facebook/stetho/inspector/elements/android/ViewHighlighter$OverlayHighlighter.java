package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.facebook.stetho.common.Util;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

@TargetApi(18)
final class ViewHighlighter$OverlayHighlighter
  extends ViewHighlighter
{
  private AtomicInteger mContentColor = new AtomicInteger();
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private final ViewHighlightOverlays mHighlightOverlays = ViewHighlightOverlays.newInstance();
  private final Runnable mHighlightViewOnUiThreadRunnable = new Runnable()
  {
    public void run()
    {
      ViewHighlighter.OverlayHighlighter.this.highlightViewOnUiThread();
    }
  };
  private View mHighlightedView;
  private AtomicReference<View> mViewToHighlight = new AtomicReference();
  
  private void highlightViewOnUiThread()
  {
    View localView = (View)mViewToHighlight.getAndSet(null);
    if (localView == mHighlightedView) {}
    do
    {
      return;
      if (mHighlightedView != null) {
        mHighlightOverlays.removeHighlight(mHighlightedView);
      }
    } while (localView == null);
    mHighlightOverlays.highlightView(localView, mContentColor.get());
    mHighlightedView = localView;
  }
  
  private void setHighlightedViewImpl(@Nullable View paramView, int paramInt)
  {
    mHandler.removeCallbacks(mHighlightViewOnUiThreadRunnable);
    mViewToHighlight.set(paramView);
    mContentColor.set(paramInt);
    mHandler.postDelayed(mHighlightViewOnUiThreadRunnable, 100L);
  }
  
  public void clearHighlight()
  {
    setHighlightedViewImpl(null, 0);
  }
  
  public void setHighlightedView(View paramView, int paramInt)
  {
    setHighlightedViewImpl((View)Util.throwIfNull(paramView), paramInt);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlighter.OverlayHighlighter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */