package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.appcompat.R.attr;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class AppCompatRadioButton
  extends RadioButton
  implements TintableCompoundButton
{
  private AppCompatCompoundButtonHelper mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this, mDrawableManager);
  private AppCompatDrawableManager mDrawableManager = AppCompatDrawableManager.get();
  
  public AppCompatRadioButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatRadioButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.radioButtonStyle);
  }
  
  public AppCompatRadioButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mCompoundButtonHelper.loadFromAttributes(paramAttributeSet, paramInt);
  }
  
  public int getCompoundPaddingLeft()
  {
    int j = super.getCompoundPaddingLeft();
    int i = j;
    if (mCompoundButtonHelper != null) {
      i = mCompoundButtonHelper.getCompoundPaddingLeft(j);
    }
    return i;
  }
  
  @Nullable
  public ColorStateList getSupportButtonTintList()
  {
    if (mCompoundButtonHelper != null) {
      return mCompoundButtonHelper.getSupportButtonTintList();
    }
    return null;
  }
  
  @Nullable
  public PorterDuff.Mode getSupportButtonTintMode()
  {
    if (mCompoundButtonHelper != null) {
      return mCompoundButtonHelper.getSupportButtonTintMode();
    }
    return null;
  }
  
  public void setButtonDrawable(@DrawableRes int paramInt)
  {
    if (mDrawableManager != null) {}
    for (Drawable localDrawable = mDrawableManager.getDrawable(getContext(), paramInt);; localDrawable = ContextCompat.getDrawable(getContext(), paramInt))
    {
      setButtonDrawable(localDrawable);
      return;
    }
  }
  
  public void setButtonDrawable(Drawable paramDrawable)
  {
    super.setButtonDrawable(paramDrawable);
    if (mCompoundButtonHelper != null) {
      mCompoundButtonHelper.onSetButtonDrawable();
    }
  }
  
  public void setSupportButtonTintList(@Nullable ColorStateList paramColorStateList)
  {
    if (mCompoundButtonHelper != null) {
      mCompoundButtonHelper.setSupportButtonTintList(paramColorStateList);
    }
  }
  
  public void setSupportButtonTintMode(@Nullable PorterDuff.Mode paramMode)
  {
    if (mCompoundButtonHelper != null) {
      mCompoundButtonHelper.setSupportButtonTintMode(paramMode);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatRadioButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */