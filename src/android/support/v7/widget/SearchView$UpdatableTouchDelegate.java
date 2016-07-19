package android.support.v7.widget;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;

class SearchView$UpdatableTouchDelegate
  extends TouchDelegate
{
  private final Rect mActualBounds;
  private boolean mDelegateTargeted;
  private final View mDelegateView;
  private final int mSlop;
  private final Rect mSlopBounds;
  private final Rect mTargetBounds;
  
  public SearchView$UpdatableTouchDelegate(Rect paramRect1, Rect paramRect2, View paramView)
  {
    super(paramRect1, paramView);
    mSlop = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
    mTargetBounds = new Rect();
    mSlopBounds = new Rect();
    mActualBounds = new Rect();
    setBounds(paramRect1, paramRect2);
    mDelegateView = paramView;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int k = (int)paramMotionEvent.getX();
    int m = (int)paramMotionEvent.getY();
    boolean bool1 = false;
    int j = 1;
    boolean bool2 = false;
    int i;
    switch (paramMotionEvent.getAction())
    {
    default: 
      i = j;
      if (bool1)
      {
        if ((i == 0) || (mActualBounds.contains(k, m))) {
          break label209;
        }
        paramMotionEvent.setLocation(mDelegateView.getWidth() / 2, mDelegateView.getHeight() / 2);
      }
      break;
    }
    for (;;)
    {
      bool2 = mDelegateView.dispatchTouchEvent(paramMotionEvent);
      return bool2;
      i = j;
      if (!mTargetBounds.contains(k, m)) {
        break;
      }
      mDelegateTargeted = true;
      bool1 = true;
      i = j;
      break;
      boolean bool3 = mDelegateTargeted;
      i = j;
      bool1 = bool3;
      if (!bool3) {
        break;
      }
      i = j;
      bool1 = bool3;
      if (mSlopBounds.contains(k, m)) {
        break;
      }
      i = 0;
      bool1 = bool3;
      break;
      bool1 = mDelegateTargeted;
      mDelegateTargeted = false;
      i = j;
      break;
      label209:
      paramMotionEvent.setLocation(k - mActualBounds.left, m - mActualBounds.top);
    }
  }
  
  public void setBounds(Rect paramRect1, Rect paramRect2)
  {
    mTargetBounds.set(paramRect1);
    mSlopBounds.set(paramRect1);
    mSlopBounds.inset(-mSlop, -mSlop);
    mActualBounds.set(paramRect2);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.SearchView.UpdatableTouchDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */