package android.support.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

class AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState
  extends Drawable.ConstantState
{
  private final Drawable.ConstantState mDelegateState;
  
  public AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState(Drawable.ConstantState paramConstantState)
  {
    mDelegateState = paramConstantState;
  }
  
  public boolean canApplyTheme()
  {
    return mDelegateState.canApplyTheme();
  }
  
  public int getChangingConfigurations()
  {
    return mDelegateState.getChangingConfigurations();
  }
  
  public Drawable newDrawable()
  {
    AnimatedVectorDrawableCompat localAnimatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(null);
    mDelegateDrawable = mDelegateState.newDrawable();
    mDelegateDrawable.setCallback(AnimatedVectorDrawableCompat.access$100(localAnimatedVectorDrawableCompat));
    return localAnimatedVectorDrawableCompat;
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    AnimatedVectorDrawableCompat localAnimatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(null);
    mDelegateDrawable = mDelegateState.newDrawable(paramResources);
    mDelegateDrawable.setCallback(AnimatedVectorDrawableCompat.access$100(localAnimatedVectorDrawableCompat));
    return localAnimatedVectorDrawableCompat;
  }
  
  public Drawable newDrawable(Resources paramResources, Resources.Theme paramTheme)
  {
    AnimatedVectorDrawableCompat localAnimatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(null);
    mDelegateDrawable = mDelegateState.newDrawable(paramResources, paramTheme);
    mDelegateDrawable.setCallback(AnimatedVectorDrawableCompat.access$100(localAnimatedVectorDrawableCompat));
    return localAnimatedVectorDrawableCompat;
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */