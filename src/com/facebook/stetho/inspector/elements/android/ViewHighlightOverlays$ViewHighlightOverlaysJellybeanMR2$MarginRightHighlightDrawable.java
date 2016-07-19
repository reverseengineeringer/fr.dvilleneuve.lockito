package com.facebook.stetho.inspector.elements.android;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginRightHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginRightHighlightDrawable()
  {
    super(-1426797922);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.translate(mMargins.right, -(mMargins.top + mMargins.bottom));
    super.draw(paramCanvas);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(paramView.getWidth() - mMargins.right, 0, paramView.getWidth(), paramView.getHeight() + mMargins.top + mMargins.bottom);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.MarginRightHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */