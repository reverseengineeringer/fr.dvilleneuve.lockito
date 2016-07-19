package com.facebook.stetho.inspector.screencast;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Base64OutputStream;
import android.view.View;
import android.view.Window;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Page.ScreencastFrameEvent;
import com.facebook.stetho.inspector.protocol.module.Page.ScreencastFrameEventMetadata;
import com.facebook.stetho.inspector.protocol.module.Page.StartScreencastRequest;
import java.io.ByteArrayOutputStream;

public final class ScreencastDispatcher
{
  private static final long FRAME_DELAY = 200L;
  private final ActivityTracker mActivityTracker = ActivityTracker.get();
  private Handler mBackgroundHandler;
  private Bitmap mBitmap;
  private final BitmapFetchRunnable mBitmapFetchRunnable = new BitmapFetchRunnable(null);
  private Canvas mCanvas;
  private Page.ScreencastFrameEvent mEvent = new Page.ScreencastFrameEvent();
  private final EventDispatchRunnable mEventDispatchRunnable = new EventDispatchRunnable(null);
  private HandlerThread mHandlerThread;
  private boolean mIsRunning;
  private final Handler mMainHandler = new Handler(Looper.getMainLooper());
  private Page.ScreencastFrameEventMetadata mMetadata = new Page.ScreencastFrameEventMetadata();
  private JsonRpcPeer mPeer;
  private Page.StartScreencastRequest mRequest;
  private ByteArrayOutputStream mStream;
  private final RectF mTempDst = new RectF();
  private final RectF mTempSrc = new RectF();
  
  public void startScreencast(JsonRpcPeer paramJsonRpcPeer, Page.StartScreencastRequest paramStartScreencastRequest)
  {
    LogUtil.d("Starting screencast");
    mRequest = paramStartScreencastRequest;
    mHandlerThread = new HandlerThread("Screencast Thread");
    mHandlerThread.start();
    mPeer = paramJsonRpcPeer;
    mIsRunning = true;
    mStream = new ByteArrayOutputStream();
    mBackgroundHandler = new Handler(mHandlerThread.getLooper());
    mMainHandler.postDelayed(mBitmapFetchRunnable, 200L);
  }
  
  public void stopScreencast()
  {
    LogUtil.d("Stopping screencast");
    mBackgroundHandler.post(new CancellationRunnable(null));
  }
  
  private class BitmapFetchRunnable
    implements Runnable
  {
    private BitmapFetchRunnable() {}
    
    private void updateScreenBitmap()
    {
      if (!mIsRunning) {}
      do
      {
        return;
        localObject = mActivityTracker.tryGetTopActivity();
      } while (localObject == null);
      Object localObject = ((Activity)localObject).getWindow().getDecorView();
      try
      {
        if (mBitmap == null)
        {
          int i = ((View)localObject).getWidth();
          int j = ((View)localObject).getHeight();
          float f = Math.min(mRequest.maxWidth / i, mRequest.maxHeight / j);
          int k = (int)(i * f);
          int m = (int)(j * f);
          ScreencastDispatcher.access$802(ScreencastDispatcher.this, Bitmap.createBitmap(k, m, Bitmap.Config.RGB_565));
          ScreencastDispatcher.access$1002(ScreencastDispatcher.this, new Canvas(mBitmap));
          Matrix localMatrix = new Matrix();
          mTempSrc.set(0.0F, 0.0F, i, j);
          mTempDst.set(0.0F, 0.0F, k, m);
          localMatrix.setRectToRect(mTempSrc, mTempDst, Matrix.ScaleToFit.CENTER);
          mCanvas.setMatrix(localMatrix);
        }
        ((View)localObject).draw(mCanvas);
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
      mBackgroundHandler.post(ScreencastDispatcher.EventDispatchRunnable.access$400(mEventDispatchRunnable, this));
    }
  }
  
  private class CancellationRunnable
    implements Runnable
  {
    private CancellationRunnable() {}
    
    public void run()
    {
      mHandlerThread.interrupt();
      mMainHandler.removeCallbacks(mBitmapFetchRunnable);
      mBackgroundHandler.removeCallbacks(mEventDispatchRunnable);
      ScreencastDispatcher.access$602(ScreencastDispatcher.this, false);
      ScreencastDispatcher.access$1802(ScreencastDispatcher.this, null);
      ScreencastDispatcher.access$802(ScreencastDispatcher.this, null);
      ScreencastDispatcher.access$1002(ScreencastDispatcher.this, null);
      ScreencastDispatcher.access$1302(ScreencastDispatcher.this, null);
    }
  }
  
  private class EventDispatchRunnable
    implements Runnable
  {
    private Runnable mEndAction;
    
    private EventDispatchRunnable() {}
    
    private EventDispatchRunnable withEndAction(Runnable paramRunnable)
    {
      mEndAction = paramRunnable;
      return this;
    }
    
    public void run()
    {
      if ((!mIsRunning) || (mBitmap == null)) {
        return;
      }
      int i = mBitmap.getWidth();
      int j = mBitmap.getHeight();
      mStream.reset();
      Base64OutputStream localBase64OutputStream = new Base64OutputStream(mStream, 0);
      Bitmap.CompressFormat localCompressFormat = Bitmap.CompressFormat.valueOf(mRequest.format.toUpperCase());
      mBitmap.compress(localCompressFormat, mRequest.quality, localBase64OutputStream);
      mEvent.data = mStream.toString();
      mMetadata.pageScaleFactor = 1;
      mMetadata.deviceWidth = i;
      mMetadata.deviceHeight = j;
      mEvent.metadata = mMetadata;
      mPeer.invokeMethod("Page.screencastFrame", mEvent, null);
      mMainHandler.postDelayed(mEndAction, 200L);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.screencast.ScreencastDispatcher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */