package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;

class AppCompatDelegateImplV7$6
  extends ViewPropertyAnimatorListenerAdapter
{
  AppCompatDelegateImplV7$6(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7) {}
  
  public void onAnimationEnd(View paramView)
  {
    ViewCompat.setAlpha(this$0.mActionModeView, 1.0F);
    this$0.mFadeAnim.setListener(null);
    this$0.mFadeAnim = null;
  }
  
  public void onAnimationStart(View paramView)
  {
    this$0.mActionModeView.setVisibility(0);
    this$0.mActionModeView.sendAccessibilityEvent(32);
    if (this$0.mActionModeView.getParent() != null) {
      ViewCompat.requestApplyInsets((View)this$0.mActionModeView.getParent());
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV7.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */