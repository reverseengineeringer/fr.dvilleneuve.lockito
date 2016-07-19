package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$PaddingRightHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$PaddingRightHighlightDrawable()
  {
    super(-1430332746);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(paramView.getWidth() - mPaddings.right, 0, paramView.getWidth(), paramView.getHeight());
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.PaddingRightHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */