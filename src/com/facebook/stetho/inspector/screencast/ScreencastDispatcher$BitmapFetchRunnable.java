package com.facebook.stetho.inspector.screencast;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.protocol.module.Page.StartScreencastRequest;

class ScreencastDispatcher$BitmapFetchRunnable
  implements Runnable
{
  private ScreencastDispatcher$BitmapFetchRunnable(ScreencastDispatcher paramScreencastDispatcher) {}
  
  private void updateScreenBitmap()
  {
    if (!ScreencastDispatcher.access$600(this$0)) {}
    do
    {
      return;
      localObject = ScreencastDispatcher.access$700(this$0).tryGetTopActivity();
    } while (localObject == null);
    Object localObject = ((Activity)localObject).getWindow().getDecorView();
    try
    {
      if (ScreencastDispatcher.access$800(this$0) == null)
      {
        int i = ((View)localObject).getWidth();
        int j = ((View)localObject).getHeight();
        float f = Math.min(access$900this$0).maxWidth / i, access$900this$0).maxHeight / j);
        int k = (int)(i * f);
        int m = (int)(j * f);
        ScreencastDispatcher.access$802(this$0, Bitmap.createBitmap(k, m, Bitmap.Config.RGB_565));
        ScreencastDispatcher.access$1002(this$0, new Canvas(ScreencastDispatcher.access$800(this$0)));
        Matrix localMatrix = new Matrix();
        ScreencastDispatcher.access$1100(this$0).set(0.0F, 0.0F, i, j);
        ScreencastDispatcher.access$1200(this$0).set(0.0F, 0.0F, k, m);
        localMatrix.setRectToRect(ScreencastDispatcher.access$1100(this$0), ScreencastDispatcher.access$1200(this$0), Matrix.ScaleToFit.CENTER);
        ScreencastDispatcher.access$1000(this$0).setMatrix(localMatrix);
      }
      ((View)localObject).draw(ScreencastDispatcher.access$1000(this$0));
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      LogUtil.w("Out of memory trying to allocate screencast Bitmap.");
    }
  }
  
  public void run()
  {
    updateScreenBitmap();
    ScreencastDispatcher.access$500(this$0).post(ScreencastDispatcher.EventDispatchRunnable.access$400(ScreencastDispatcher.access$300(this$0), this));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.screencast.ScreencastDispatcher.BitmapFetchRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */