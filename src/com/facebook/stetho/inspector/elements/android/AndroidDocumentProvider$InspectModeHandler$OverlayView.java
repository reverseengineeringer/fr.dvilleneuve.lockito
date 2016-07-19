package com.facebook.stetho.inspector.elements.android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.stetho.common.android.ViewUtil;
import com.facebook.stetho.inspector.elements.DocumentProviderListener;

final class AndroidDocumentProvider$InspectModeHandler$OverlayView
  extends DocumentHiddenView
{
  public AndroidDocumentProvider$InspectModeHandler$OverlayView(AndroidDocumentProvider.InspectModeHandler paramInspectModeHandler, Context paramContext)
  {
    super(paramContext);
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
      View localView = ViewUtil.hitTest((View)getParent(), paramMotionEvent.getX(), paramMotionEvent.getY(), AndroidDocumentProvider.InspectModeHandler.access$600(this$1));
      if ((paramMotionEvent.getAction() != 3) && (localView != null))
      {
        AndroidDocumentProvider.access$700(this$1.this$0).setHighlightedView(localView, 1077952767);
        if ((paramMotionEvent.getAction() == 1) && (AndroidDocumentProvider.access$100(this$1.this$0) != null)) {
          AndroidDocumentProvider.access$100(this$1.this$0).onInspectRequested(localView);
        }
      }
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider.InspectModeHandler.OverlayView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */