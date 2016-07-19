package com.facebook.stetho.inspector.elements.android;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginTopHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginTopHighlightDrawable()
  {
    super(-1426797922);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.translate(0.0F, -mMargins.top);
    super.draw(paramCanvas);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(0, 0, paramView.getWidth(), mMargins.top);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.MarginTopHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */