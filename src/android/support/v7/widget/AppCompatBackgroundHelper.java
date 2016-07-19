package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.View;

class AppCompatBackgroundHelper
{
  private TintInfo mBackgroundTint;
  private final AppCompatDrawableManager mDrawableManager;
  private TintInfo mInternalBackgroundTint;
  private TintInfo mTmpInfo;
  private final View mView;
  
  AppCompatBackgroundHelper(View paramView, AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    mView = paramView;
    mDrawableManager = paramAppCompatDrawableManager;
  }
  
  private boolean applyFrameworkTintUsingColorFilter(@NonNull Drawable paramDrawable)
  {
    if (mTmpInfo == null) {
      mTmpInfo = new TintInfo();
    }
    TintInfo localTintInfo = mTmpInfo;
    localTintInfo.clear();
    Object localObject = ViewCompat.getBackgroundTintList(mView);
    if (localObject != null)
    {
      mHasTintList = true;
      mTintList = ((ColorStateList)localObject);
    }
    localObject = ViewCompat.getBackgroundTintMode(mView);
    if (localObject != null)
    {
      mHasTintMode = true;
      mTintMode = ((PorterDuff.Mode)localObject);
    }
    if ((mHasTintList) || (mHasTintMode))
    {
      AppCompatDrawableManager.tintDrawable(paramDrawable, localTintInfo, mView.getDrawableState());
      return true;
    }
    return false;
  }
  
  void applySupportBackgroundTint()
  {
    Drawable localDrawable = mView.getBackground();
    if ((localDrawable == null) || ((Build.VERSION.SDK_INT == 21) && (applyFrameworkTintUsingColorFilter(localDrawable)))) {}
    do
    {
      return;
      if (mBackgroundTint != null)
      {
        AppCompatDrawableManager.tintDrawable(localDrawable, mBackgroundTint, mView.getDrawableState());
        return;
      }
    } while (mInternalBackgroundTint == null);
    AppCompatDrawableManager.tintDrawable(localDrawable, mInternalBackgroundTint, mView.getDrawableState());
  }
  
  ColorStateList getSupportBackgroundTintList()
  {
    if (mBackgroundTint != null) {
      return mBackgroundTint.mTintList;
    }
    return null;
  }
  
  PorterDuff.Mode getSupportBackgroundTintMode()
  {
    if (mBackgroundTint != null) {
      return mBackgroundTint.mTintMode;
    }
    return null;
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, R.styleable.ViewBackgroundHelper, paramInt, 0);
    try
    {
      if (paramAttributeSet.hasValue(R.styleable.ViewBackgroundHelper_android_background))
      {
        ColorStateList localColorStateList = mDrawableManager.getTintList(mView.getContext(), paramAttributeSet.getResourceId(R.styleable.ViewBackgroundHelper_android_background, -1));
        if (localColorStateList != null) {
          setInternalBackgroundTint(localColorStateList);
        }
      }
      if (paramAttributeSet.hasValue(R.styleable.ViewBackgroundHelper_backgroundTint)) {
        ViewCompat.setBackgroundTintList(mView, paramAttributeSet.getColorStateList(R.styleable.ViewBackgroundHelper_backgroundTint));
      }
      if (paramAttributeSet.hasValue(R.styleable.ViewBackgroundHelper_backgroundTintMode)) {
        ViewCompat.setBackgroundTintMode(mView, DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
      }
      return;
    }
    finally
    {
      paramAttributeSet.recycle();
    }
  }
  
  void onSetBackgroundDrawable(Drawable paramDrawable)
  {
    setInternalBackgroundTint(null);
  }
  
  void onSetBackgroundResource(int paramInt)
  {
    if (mDrawableManager != null) {}
    for (ColorStateList localColorStateList = mDrawableManager.getTintList(mView.getContext(), paramInt);; localColorStateList = null)
    {
      setInternalBackgroundTint(localColorStateList);
      return;
    }
  }
  
  void setInternalBackgroundTint(ColorStateList paramColorStateList)
  {
    if (paramColorStateList != null)
    {
      if (mInternalBackgroundTint == null) {
        mInternalBackgroundTint = new TintInfo();
      }
      mInternalBackgroundTint.mTintList = paramColorStateList;
      mInternalBackgroundTint.mHasTintList = true;
    }
    for (;;)
    {
      applySupportBackgroundTint();
      return;
      mInternalBackgroundTint = null;
    }
  }
  
  void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    if (mBackgroundTint == null) {
      mBackgroundTint = new TintInfo();
    }
    mBackgroundTint.mTintList = paramColorStateList;
    mBackgroundTint.mHasTintList = true;
    applySupportBackgroundTint();
  }
  
  void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (mBackgroundTint == null) {
      mBackgroundTint = new TintInfo();
    }
    mBackgroundTint.mTintMode = paramMode;
    mBackgroundTint.mHasTintMode = true;
    applySupportBackgroundTint();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatBackgroundHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */