package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewParent;

public abstract class ForwardingListener
  implements View.OnTouchListener
{
  private int mActivePointerId;
  private Runnable mDisallowIntercept;
  private boolean mForwarding;
  private final int mLongPressTimeout;
  private final float mScaledTouchSlop;
  private final View mSrc;
  private final int mTapTimeout;
  private final int[] mTmpLocation = new int[2];
  private Runnable mTriggerLongPress;
  
  public ForwardingListener(View paramView)
  {
    mSrc = paramView;
    mScaledTouchSlop = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
    mTapTimeout = ViewConfiguration.getTapTimeout();
    mLongPressTimeout = ((mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2);
  }
  
  private void clearCallbacks()
  {
    if (mTriggerLongPress != null) {
      mSrc.removeCallbacks(mTriggerLongPress);
    }
    if (mDisallowIntercept != null) {
      mSrc.removeCallbacks(mDisallowIntercept);
    }
  }
  
  private void onLongPress()
  {
    clearCallbacks();
    View localView = mSrc;
    if ((!localView.isEnabled()) || (localView.isLongClickable())) {}
    while (!onForwardingStarted()) {
      return;
    }
    localView.getParent().requestDisallowInterceptTouchEvent(true);
    long l = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
    localView.onTouchEvent(localMotionEvent);
    localMotionEvent.recycle();
    mForwarding = true;
  }
  
  private boolean onTouchForwarded(MotionEvent paramMotionEvent)
  {
    boolean bool1 = true;
    View localView = mSrc;
    Object localObject = getPopup();
    if ((localObject == null) || (!((ShowableListMenu)localObject).isShowing())) {}
    do
    {
      return false;
      localObject = (DropDownListView)((ShowableListMenu)localObject).getListView();
    } while ((localObject == null) || (!((DropDownListView)localObject).isShown()));
    MotionEvent localMotionEvent = MotionEvent.obtainNoHistory(paramMotionEvent);
    toGlobalMotionEvent(localView, localMotionEvent);
    toLocalMotionEvent((View)localObject, localMotionEvent);
    boolean bool2 = ((DropDownListView)localObject).onForwardedEvent(localMotionEvent, mActivePointerId);
    localMotionEvent.recycle();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((i != 1) && (i != 3))
    {
      i = 1;
      if ((!bool2) || (i == 0)) {
        break label131;
      }
    }
    for (;;)
    {
      return bool1;
      i = 0;
      break;
      label131:
      bool1 = false;
    }
  }
  
  private boolean onTouchObserved(MotionEvent paramMotionEvent)
  {
    View localView = mSrc;
    if (!localView.isEnabled()) {}
    int i;
    do
    {
      return false;
      switch (MotionEventCompat.getActionMasked(paramMotionEvent))
      {
      default: 
        return false;
      case 0: 
        mActivePointerId = paramMotionEvent.getPointerId(0);
        if (mDisallowIntercept == null) {
          mDisallowIntercept = new DisallowIntercept(null);
        }
        localView.postDelayed(mDisallowIntercept, mTapTimeout);
        if (mTriggerLongPress == null) {
          mTriggerLongPress = new TriggerLongPress(null);
        }
        localView.postDelayed(mTriggerLongPress, mLongPressTimeout);
        return false;
      case 2: 
        i = paramMotionEvent.findPointerIndex(mActivePointerId);
      }
    } while ((i < 0) || (pointInView(localView, paramMotionEvent.getX(i), paramMotionEvent.getY(i), mScaledTouchSlop)));
    clearCallbacks();
    localView.getParent().requestDisallowInterceptTouchEvent(true);
    return true;
    clearCallbacks();
    return false;
  }
  
  private static boolean pointInView(View paramView, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat1 >= -paramFloat3) && (paramFloat2 >= -paramFloat3) && (paramFloat1 < paramView.getRight() - paramView.getLeft() + paramFloat3) && (paramFloat2 < paramView.getBottom() - paramView.getTop() + paramFloat3);
  }
  
  private boolean toGlobalMotionEvent(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = mTmpLocation;
    paramView.getLocationOnScreen(arrayOfInt);
    paramMotionEvent.offsetLocation(arrayOfInt[0], arrayOfInt[1]);
    return true;
  }
  
  private boolean toLocalMotionEvent(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = mTmpLocation;
    paramView.getLocationOnScreen(arrayOfInt);
    paramMotionEvent.offsetLocation(-arrayOfInt[0], -arrayOfInt[1]);
    return true;
  }
  
  public abstract ShowableListMenu getPopup();
  
  protected boolean onForwardingStarted()
  {
    ShowableListMenu localShowableListMenu = getPopup();
    if ((localShowableListMenu != null) && (!localShowableListMenu.isShowing())) {
      localShowableListMenu.show();
    }
    return true;
  }
  
  protected boolean onForwardingStopped()
  {
    ShowableListMenu localShowableListMenu = getPopup();
    if ((localShowableListMenu != null) && (localShowableListMenu.isShowing())) {
      localShowableListMenu.dismiss();
    }
    return true;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool3 = false;
    boolean bool4 = mForwarding;
    boolean bool1;
    if (bool4)
    {
      if ((onTouchForwarded(paramMotionEvent)) || (!onForwardingStopped())) {}
      for (bool1 = true;; bool1 = false)
      {
        mForwarding = bool1;
        if (!bool1)
        {
          bool1 = bool3;
          if (!bool4) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      }
    }
    if ((onTouchObserved(paramMotionEvent)) && (onForwardingStarted())) {}
    for (boolean bool2 = true;; bool2 = false)
    {
      bool1 = bool2;
      if (!bool2) {
        break;
      }
      long l = SystemClock.uptimeMillis();
      paramView = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
      mSrc.onTouchEvent(paramView);
      paramView.recycle();
      bool1 = bool2;
      break;
    }
  }
  
  private class DisallowIntercept
    implements Runnable
  {
    private DisallowIntercept() {}
    
    public void run()
    {
      mSrc.getParent().requestDisallowInterceptTouchEvent(true);
    }
  }
  
  private class TriggerLongPress
    implements Runnable
  {
    private TriggerLongPress() {}
    
    public void run()
    {
      ForwardingListener.this.onLongPress();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ForwardingListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */