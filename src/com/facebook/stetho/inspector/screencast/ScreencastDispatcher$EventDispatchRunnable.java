package com.facebook.stetho.inspector.screencast;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.util.Base64OutputStream;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Page.StartScreencastRequest;
import java.io.ByteArrayOutputStream;

class ScreencastDispatcher$EventDispatchRunnable
  implements Runnable
{
  private Runnable mEndAction;
  
  private ScreencastDispatcher$EventDispatchRunnable(ScreencastDispatcher paramScreencastDispatcher) {}
  
  private EventDispatchRunnable withEndAction(Runnable paramRunnable)
  {
    mEndAction = paramRunnable;
    return this;
  }
  
  public void run()
  {
    if ((!ScreencastDispatcher.access$600(this$0)) || (ScreencastDispatcher.access$800(this$0) == null)) {
      return;
    }
    int i = ScreencastDispatcher.access$800(this$0).getWidth();
    int j = ScreencastDispatcher.access$800(this$0).getHeight();
    ScreencastDispatcher.access$1300(this$0).reset();
    Base64OutputStream localBase64OutputStream = new Base64OutputStream(ScreencastDispatcher.access$1300(this$0), 0);
    Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.valueOf(access$900this$0).format.toUpperCase());
    ScreencastDispatcher.access$800(this$0).compress(localCompressFormat, access$900this$0).quality, localBase64OutputStream);
    access$1400this$0).data = ScreencastDispatcher.access$1300(this$0).toString();
    access$1500this$0).pageScaleFactor = 1;
    access$1500this$0).deviceWidth = i;
    access$1500this$0).deviceHeight = j;
    access$1400this$0).metadata = ScreencastDispatcher.access$1500(this$0);
    ScreencastDispatcher.access$1600(this$0).invokeMethod("Page.screencastFrame", ScreencastDispatcher.access$1400(this$0), null);
    ScreencastDispatcher.access$1700(this$0).postDelayed(mEndAction, 200L);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.screencast.ScreencastDispatcher.EventDispatchRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */