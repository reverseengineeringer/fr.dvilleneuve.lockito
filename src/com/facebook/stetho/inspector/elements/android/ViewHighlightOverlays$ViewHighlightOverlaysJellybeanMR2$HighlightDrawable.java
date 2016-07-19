package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

abstract class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$HighlightDrawable
  extends ColorDrawable
{
  protected final Rect mMargins = new Rect();
  protected final Rect mPaddings = new Rect();
  
  public ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$HighlightDrawable() {}
  
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$HighlightDrawable(int paramInt)
  {
    super(paramInt);
  }
  
  void highlightView(View paramView)
  {
    Object localObject = paramView.getLayoutParams();
    if ((localObject instanceof ViewGroup.MarginLayoutParams))
    {
      localObject = (ViewGroup.MarginLayoutParams)localObject;
      mMargins.left = leftMargin;
      mMargins.top = topMargin;
      mMargins.right = rightMargin;
    }
    for (mMargins.bottom = bottomMargin;; mMargins.bottom = 0)
    {
      mPaddings.left = paramView.getPaddingLeft();
      mPaddings.top = paramView.getPaddingTop();
      mPaddings.right = paramView.getPaddingRight();
      mPaddings.bottom = paramView.getPaddingBottom();
      return;
      mMargins.left = 0;
      mMargins.top = 0;
      mMargins.right = 0;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */