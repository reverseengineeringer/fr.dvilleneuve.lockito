package com.facebook.stetho.inspector.elements.android;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.view.View;

class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2$MainHighlightDrawable
  extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
{
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = paramCanvas.getClipBounds();
    localRect.inset(-(mMargins.right + mMargins.left), -(mMargins.top + mMargins.bottom));
    paramCanvas.clipRect(localRect, Region.Op.REPLACE);
    super.draw(paramCanvas);
  }
  
  void highlightView(View paramView)
  {
    super.highlightView(paramView);
    setBounds(0, 0, paramView.getWidth(), paramView.getHeight());
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.MainHighlightDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */