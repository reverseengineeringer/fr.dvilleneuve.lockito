package com.facebook.stetho.inspector.elements.android;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewOverlay;

@TargetApi(18)
class ViewHighlightOverlays$ViewHighlightOverlaysJellybeanMR2
  extends ViewHighlightOverlays
{
  private static final int MARGIN_OVERLAY_COLOR = -1426797922;
  private static final int PADDING_OVERLAY_COLOR = -1430332746;
  private final HighlightDrawable[] mHighlightDrawables = { mMainHighlightDrawable, new PaddingTopHighlightDrawable(), new PaddingBottomHighlightDrawable(), new PaddingRightHighlightDrawable(), new PaddingLeftHighlightDrawable(), new MarginTopHighlightDrawable(), new MarginBottomHighlightDrawable(), new MarginRightHighlightDrawable(), new MarginLeftHighlightDrawable() };
  private final MainHighlightDrawable mMainHighlightDrawable = new MainHighlightDrawable();
  
  void highlightView(View paramView, int paramInt)
  {
    mMainHighlightDrawable.setColor(paramInt);
    int i = mHighlightDrawables.length;
    paramInt = 0;
    while (paramInt < i)
    {
      HighlightDrawable localHighlightDrawable = mHighlightDrawables[paramInt];
      localHighlightDrawable.highlightView(paramView);
      paramView.getOverlay().add(localHighlightDrawable);
      paramInt += 1;
    }
  }
  
  void removeHighlight(View paramView)
  {
    HighlightDrawable[] arrayOfHighlightDrawable = mHighlightDrawables;
    int j = arrayOfHighlightDrawable.length;
    int i = 0;
    while (i < j)
    {
      HighlightDrawable localHighlightDrawable = arrayOfHighlightDrawable[i];
      paramView.getOverlay().remove(localHighlightDrawable);
      i += 1;
    }
  }
  
  static abstract class HighlightDrawable
    extends ColorDrawable
  {
    protected final Rect mMargins = new Rect();
    protected final Rect mPaddings = new Rect();
    
    public HighlightDrawable() {}
    
    HighlightDrawable(int paramInt)
    {
      super();
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
  
  static class MainHighlightDrawable
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
  
  static class MarginBottomHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    MarginBottomHighlightDrawable()
    {
      super();
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
  
  static class MarginLeftHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    MarginLeftHighlightDrawable()
    {
      super();
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
  
  static class MarginRightHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    MarginRightHighlightDrawable()
    {
      super();
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
  
  static class MarginTopHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    MarginTopHighlightDrawable()
    {
      super();
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
  
  static class PaddingBottomHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    PaddingBottomHighlightDrawable()
    {
      super();
    }
    
    void highlightView(View paramView)
    {
      super.highlightView(paramView);
      setBounds(mPaddings.left, paramView.getHeight() - mPaddings.bottom, paramView.getWidth() - mPaddings.right, paramView.getHeight());
    }
  }
  
  static class PaddingLeftHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    PaddingLeftHighlightDrawable()
    {
      super();
    }
    
    void highlightView(View paramView)
    {
      super.highlightView(paramView);
      setBounds(0, 0, mPaddings.left, paramView.getHeight());
    }
  }
  
  static class PaddingRightHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    PaddingRightHighlightDrawable()
    {
      super();
    }
    
    void highlightView(View paramView)
    {
      super.highlightView(paramView);
      setBounds(paramView.getWidth() - mPaddings.right, 0, paramView.getWidth(), paramView.getHeight());
    }
  }
  
  static class PaddingTopHighlightDrawable
    extends ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2.HighlightDrawable
  {
    PaddingTopHighlightDrawable()
    {
      super();
    }
    
    void highlightView(View paramView)
    {
      super.highlightView(paramView);
      setBounds(mPaddings.left, 0, paramView.getWidth() - mPaddings.right, mPaddings.top);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewHighlightOverlays.ViewHighlightOverlaysJellybeanMR2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */