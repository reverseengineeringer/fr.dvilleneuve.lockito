package com.facebook.stetho.inspector.elements.android;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginLeftHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginLeftHighlightDrawable()
  {
    super(-1426797922);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.translate(-(mMargins.left + mMargins.right), 0.0F);
    super.draw(paramCanvas);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(0, 0, mMargins.left, paramView.getHeight() + mMargins.top + mMargins.bottom);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.MarginLeftHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */