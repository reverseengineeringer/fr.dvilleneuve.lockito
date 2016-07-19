package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;

class AppCompatDelegateImplV7$5$1
  extends ViewPropertyAnimatorListenerAdapter
{
  AppCompatDelegateImplV7$5$1(AppCompatDelegateImplV7.5 param5) {}
  
  public void onAnimationEnd(View paramView)
  {
    ViewCompat.setAlpha(this$1.this$0.mActionModeView, 1.0F);
    this$1.this$0.mFadeAnim.setListener(null);
    this$1.this$0.mFadeAnim = null;
  }
  
  public void onAnimationStart(View paramView)
  {
    this$1.this$0.mActionModeView.setVisibility(0);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatDelegateImplV7.5.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */