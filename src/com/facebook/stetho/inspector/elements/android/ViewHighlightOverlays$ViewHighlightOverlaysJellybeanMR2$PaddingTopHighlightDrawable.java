package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$PaddingTopHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$PaddingTopHighlightDrawable()
  {
    super(-1430332746);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(mPaddings.left, 0, paramView.getWidth() - mPaddings.right, mPaddings.top);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.PaddingTopHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */