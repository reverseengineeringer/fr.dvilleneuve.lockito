package android.support.graphics.drawable;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;

class AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState
  extends Drawable.ConstantState
{
  ArrayList<Animator> mAnimators;
  int mChangingConfigurations;
  ArrayMap<Animator, String> mTargetNameMap;
  VectorDrawableCompat mVectorDrawable;
  
  public AnimatedVectorDrawableCompat$AnimatedVectorDrawableCompatState(Context paramContext, AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, Drawable.Callback paramCallback, Resources paramResources)
  {
    if (paramAnimatedVectorDrawableCompatState != null)
    {
      mChangingConfigurations = mChangingConfigurations;
      if (mVectorDrawable != null)
      {
        paramContext = mVectorDrawable.getConstantState();
        if (paramResources == null) {
          break label215;
        }
      }
      label215:
      for (mVectorDrawable = ((VectorDrawableCompat)paramContext.newDrawable(paramResources));; mVectorDrawable = ((VectorDrawableCompat)paramContext.newDrawable()))
      {
        mVectorDrawable = ((VectorDrawableCompat)mVectorDrawable.mutate());
        mVectorDrawable.setCallback(paramCallback);
        mVectorDrawable.setBounds(mVectorDrawable.getBounds());
        mVectorDrawable.setAllowCaching(false);
        if (mAnimators == null) {
          break;
        }
        int j = mAnimators.size();
        mAnimators = new ArrayList(j);
        mTargetNameMap = new ArrayMap(j);
        int i = 0;
        while (i < j)
        {
          paramCallback = (Animator)mAnimators.get(i);
          paramContext = paramCallback.clone();
          paramCallback = (String)mTargetNameMap.get(paramCallback);
          paramContext.setTarget(mVectorDrawable.getTargetByName(paramCallback));
          mAnimators.add(paramContext);
          mTargetNameMap.put(paramContext, paramCallback);
          i += 1;
        }
      }
    }
  }
  
  public int getChangingConfigurations()
  {
    return mChangingConfigurations;
  }
  
  public Drawable newDrawable()
  {
    throw new IllegalStateException("No constant state support for SDK < 23.");
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    throw new IllegalStateException("No constant state support for SDK < 23.");
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.AnimatedVectorDrawableCompat.AnimatedVectorDrawableCompatState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */