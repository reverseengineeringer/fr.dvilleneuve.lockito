package com.facebook.stetho.inspector.elements.android;

import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.facebook.stetho.common.Accumulator;
import java.util.List;

class AndroidDocumentProvider$InspectModeHandler$2
  implements Accumulator<Window>
{
  AndroidDocumentProvider$InspectModeHandler$2(AndroidDocumentProvider.InspectModeHandler paramInspectModeHandler) {}
  
  public void store(Window paramWindow)
  {
    if ((paramWindow.peekDecorView() instanceof ViewGroup))
    {
      paramWindow = (ViewGroup)paramWindow.peekDecorView();
      AndroidDocumentProvider.InspectModeHandler.OverlayView localOverlayView = new AndroidDocumentProvider.InspectModeHandler.OverlayView(this$1, AndroidDocumentProvider.access$300(this$1.this$0));
      WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
      width = -1;
      height = -1;
      paramWindow.addView(localOverlayView, localLayoutParams);
      paramWindow.bringChildToFront(localOverlayView);
      AndroidDocumentProvider.InspectModeHandler.access$400(this$1).add(localOverlayView);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDocumentProvider.InspectModeHandler.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */