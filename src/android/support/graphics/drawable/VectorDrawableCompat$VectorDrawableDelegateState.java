package android.support.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.VectorDrawable;

class VectorDrawableCompat$VectorDrawableDelegateState
  extends Drawable.ConstantState
{
  private final Drawable.ConstantState mDelegateState;
  
  public VectorDrawableCompat$VectorDrawableDelegateState(Drawable.ConstantState paramConstantState)
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
    VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat(null);
    mDelegateDrawable = ((VectorDrawable)mDelegateState.newDrawable());
    return localVectorDrawableCompat;
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat(null);
    mDelegateDrawable = ((VectorDrawable)mDelegateState.newDrawable(paramResources));
    return localVectorDrawableCompat;
  }
  
  public Drawable newDrawable(Resources paramResources, Resources.Theme paramTheme)
  {
    VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat(null);
    mDelegateDrawable = ((VectorDrawable)mDelegateState.newDrawable(paramResources, paramTheme));
    return localVectorDrawableCompat;
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VectorDrawableDelegateState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */