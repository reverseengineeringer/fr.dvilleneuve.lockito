package android.support.v4.app;

import android.support.annotation.CallSuper;
import android.support.v4.os.BuildCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class FragmentManagerImpl$AnimateOnHWLayerIfNeededListener
  implements Animation.AnimationListener
{
  private Animation.AnimationListener mOrignalListener;
  private boolean mShouldRunOnHWLayer;
  private View mView;
  
  public FragmentManagerImpl$AnimateOnHWLayerIfNeededListener(View paramView, Animation paramAnimation)
  {
    if ((paramView == null) || (paramAnimation == null)) {
      return;
    }
    mView = paramView;
  }
  
  public FragmentManagerImpl$AnimateOnHWLayerIfNeededListener(View paramView, Animation paramAnimation, Animation.AnimationListener paramAnimationListener)
  {
    if ((paramView == null) || (paramAnimation == null)) {
      return;
    }
    mOrignalListener = paramAnimationListener;
    mView = paramView;
    mShouldRunOnHWLayer = true;
  }
  
  @CallSuper
  public void onAnimationEnd(Animation paramAnimation)
  {
    if ((mView != null) && (mShouldRunOnHWLayer))
    {
      if ((!ViewCompat.isAttachedToWindow(mView)) && (!BuildCompat.isAtLeastN())) {
        break label64;
      }
      mView.post(new Runnable()
      {
        public void run()
        {
          ViewCompat.setLayerType(mView, 0, null);
        }
      });
    }
    for (;;)
    {
      if (mOrignalListener != null) {
        mOrignalListener.onAnimationEnd(paramAnimation);
      }
      return;
      label64:
      ViewCompat.setLayerType(mView, 0, null);
    }
  }
  
  public void onAnimationRepeat(Animation paramAnimation)
  {
    if (mOrignalListener != null) {
      mOrignalListener.onAnimationRepeat(paramAnimation);
    }
  }
  
  @CallSuper
  public void onAnimationStart(Animation paramAnimation)
  {
    if (mOrignalListener != null) {
      mOrignalListener.onAnimationStart(paramAnimation);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentManagerImpl.AnimateOnHWLayerIfNeededListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */