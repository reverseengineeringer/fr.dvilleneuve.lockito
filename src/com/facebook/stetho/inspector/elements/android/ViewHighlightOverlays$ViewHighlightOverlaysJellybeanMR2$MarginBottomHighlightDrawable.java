package com.facebook.stetho.inspector.elements.android;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginBottomHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MarginBottomHighlightDrawable()
  {
    super(-1426797922);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.translate(0.0F, mMargins.bottom + mMargins.top);
    super.draw(paramCanvas);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(0, paramView.getHeight() - mMargins.bottom, paramView.getWidth(), paramView.getHeight());
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.MarginBottomHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */