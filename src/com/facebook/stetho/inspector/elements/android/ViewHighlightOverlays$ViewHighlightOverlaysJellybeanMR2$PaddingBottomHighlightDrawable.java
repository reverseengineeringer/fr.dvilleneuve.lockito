package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$PaddingBottomHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$PaddingBottomHighlightDrawable()
  {
    super(-1430332746);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(mPaddings.left, paramView.getHeight() - mPaddings.bottom, paramView.getWidth() - mPaddings.right, paramView.getHeight());
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.PaddingBottomHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */