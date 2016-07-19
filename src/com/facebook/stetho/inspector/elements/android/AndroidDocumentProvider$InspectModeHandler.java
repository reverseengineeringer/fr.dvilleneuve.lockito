package com.facebook.stetho.inspector.elements.android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Predicate;
import com.facebook.stetho.common.android.ViewUtil;
import com.facebook.stetho.inspector.elements.DocumentProviderListener;
import java.util.ArrayList;
import java.util.List;

final class AndroidDocumentProvider$InspectModeHandler
{
  private List<View> mOverlays;
  private final Predicate<View> mViewSelector = new Predicate()
  {
    public boolean apply(View paramAnonymousView)
    {
      return !(paramAnonymousView instanceof DocumentHiddenView);
    }
  };
  
  private AndroidDocumentProvider$InspectModeHandler(AndroidDocumentProvider paramAndroidDocumentProvider) {}
  
  public void disable()
  {
    this$0.verifyThreadAccess();
    if (mOverlays == null) {
      return;
    }
    int i = 0;
    while (i < mOverlays.size())
    {
      View localView = (View)mOverlays.get(i);
      ((ViewGroup)localView.getParent()).removeView(localView);
      i += 1;
    }
    mOverlays = null;
  }
  
  public void enable()
  {
    this$0.verifyThreadAccess();
    if (mOverlays != null) {
      disable();
    }
    mOverlays = new ArrayList();
    AndroidDocumentProvider.access$500(this$0, new Accumulator()
    {
      public void store(Window paramAnonymousWindow)
      {
        if ((paramAnonymousWindow.peekDecorView() instanceof ViewGroup))
        {
          paramAnonymousWindow = (ViewGroup)paramAnonymousWindow.peekDecorView();
          AndroidDocumentProvider.InspectModeHandler.OverlayView localOverlayView = new AndroidDocumentProvider.InspectModeHandler.OverlayView(AndroidDocumentProvider.InspectModeHandler.this, AndroidDocumentProvider.access$300(this$0));
          WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
          width = -1;
          height = -1;
          paramAnonymousWindow.addView(localOverlayView, localLayoutParams);
          paramAnonymousWindow.bringChildToFront(localOverlayView);
          mOverlays.add(localOverlayView);
        }
      }
    });
  }
  
  private final class OverlayView
    extends DocumentHiddenView
  {
    public OverlayView(Context paramContext)
    {
      super();
    }
    
    protected void onDraw(Canvas paramCanvas)
    {
      paramCanvas.drawColor(1090519039);
      super.onDraw(paramCanvas);
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if ((getParent() instanceof View))
      {
        View localView = ViewUtil.hitTest((View)getParent(), paramMotionEvent.getX(), paramMotionEvent.getY(), mViewSelector);
        if ((paramMotionEvent.getAction() != 3) && (localView != null))
        {
          AndroidDocumentProvider.access$700(this$0).setHighlightedView(localView, 1077952767);
          if ((paramMotionEvent.getAction() == 1) && (AndroidDocumentProvider.access$100(this$0) != null)) {
            AndroidDocumentProvider.access$100(this$0).onInspectRequested(localView);
          }
        }
      }
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider.InspectModeHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */