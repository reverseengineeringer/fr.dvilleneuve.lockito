package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.widget.TextView;

abstract interface TextViewCompat$TextViewCompatImpl
{
  public abstract int getMaxLines(TextView paramTextView);
  
  public abstract int getMinLines(TextView paramTextView);
  
  public abstract void setCompoundDrawablesRelative(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4);
  
  public abstract void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @DrawableRes int paramInt1, @DrawableRes int paramInt2, @DrawableRes int paramInt3, @DrawableRes int paramInt4);
  
  public abstract void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4);
  
  public abstract void setTextAppearance(@NonNull TextView paramTextView, @StyleRes int paramInt);
}

/* Location:
 * Qualified Name:     android.support.v4.widget.TextViewCompat.TextViewCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */