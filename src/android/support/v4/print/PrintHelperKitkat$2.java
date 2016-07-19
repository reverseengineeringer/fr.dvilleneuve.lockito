package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo.Builder;
import java.io.FileNotFoundException;

class PrintHelperKitkat$2
  extends PrintDocumentAdapter
{
  private PrintAttributes mAttributes;
  Bitmap mBitmap = null;
  AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
  
  PrintHelperKitkat$2(PrintHelperKitkat paramPrintHelperKitkat, String paramString, Uri paramUri, PrintHelperKitkat.OnPrintFinishCallback paramOnPrintFinishCallback, int paramInt) {}
  
  private void cancelLoad()
  {
    synchronized (PrintHelperKitkat.access$500(this$0))
    {
      if (this$0.mDecodeOptions != null)
      {
        this$0.mDecodeOptions.requestCancelDecode();
        this$0.mDecodeOptions = null;
      }
      return;
    }
  }
  
  public void onFinish()
  {
    super.onFinish();
    cancelLoad();
    if (mLoadBitmap != null) {
      mLoadBitmap.cancel(true);
    }
    if (val$callback != null) {
      val$callback.onFinish();
    }
    if (mBitmap != null)
    {
      mBitmap.recycle();
      mBitmap = null;
    }
  }
  
  public void onLayout(final PrintAttributes paramPrintAttributes1, final PrintAttributes paramPrintAttributes2, final CancellationSignal paramCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
  {
    boolean bool = true;
    try
    {
      mAttributes = paramPrintAttributes2;
      if (paramCancellationSignal.isCanceled())
      {
        paramLayoutResultCallback.onLayoutCancelled();
        return;
      }
    }
    finally {}
    if (mBitmap != null)
    {
      paramCancellationSignal = new PrintDocumentInfo.Builder(val$jobName).setContentType(1).setPageCount(1).build();
      if (!paramPrintAttributes2.equals(paramPrintAttributes1)) {}
      for (;;)
      {
        paramLayoutResultCallback.onLayoutFinished(paramCancellationSignal, bool);
        return;
        bool = false;
      }
    }
    mLoadBitmap = new AsyncTask()
    {
      protected Bitmap doInBackground(Uri... paramAnonymousVarArgs)
      {
        try
        {
          paramAnonymousVarArgs = PrintHelperKitkat.access$200(this$0, val$imageFile, 3500);
          return paramAnonymousVarArgs;
        }
        catch (FileNotFoundException paramAnonymousVarArgs) {}
        return null;
      }
      
      protected void onCancelled(Bitmap paramAnonymousBitmap)
      {
        paramLayoutResultCallback.onLayoutCancelled();
        mLoadBitmap = null;
      }
      
      protected void onPostExecute(Bitmap paramAnonymousBitmap)
      {
        super.onPostExecute(paramAnonymousBitmap);
        Object localObject = paramAnonymousBitmap;
        if (paramAnonymousBitmap != null) {
          if (this$0.mPrintActivityRespectsOrientation)
          {
            localObject = paramAnonymousBitmap;
            if (this$0.mOrientation != 0) {
              break label108;
            }
          }
        }
        for (;;)
        {
          try
          {
            PrintAttributes.MediaSize localMediaSize = mAttributes.getMediaSize();
            localObject = paramAnonymousBitmap;
            if (localMediaSize != null)
            {
              localObject = paramAnonymousBitmap;
              if (localMediaSize.isPortrait() != PrintHelperKitkat.access$400(paramAnonymousBitmap))
              {
                localObject = new Matrix();
                ((Matrix)localObject).postRotate(90.0F);
                localObject = Bitmap.createBitmap(paramAnonymousBitmap, 0, 0, paramAnonymousBitmap.getWidth(), paramAnonymousBitmap.getHeight(), (Matrix)localObject, true);
              }
            }
            label108:
            mBitmap = ((Bitmap)localObject);
            if (localObject == null) {
              break label190;
            }
            paramAnonymousBitmap = new PrintDocumentInfo.Builder(val$jobName).setContentType(1).setPageCount(1).build();
            if (!paramPrintAttributes2.equals(paramPrintAttributes1))
            {
              bool = true;
              paramLayoutResultCallback.onLayoutFinished(paramAnonymousBitmap, bool);
              mLoadBitmap = null;
              return;
            }
          }
          finally {}
          boolean bool = false;
          continue;
          label190:
          paramLayoutResultCallback.onLayoutFailed(null);
        }
      }
      
      protected void onPreExecute()
      {
        paramCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
        {
          public void onCancel()
          {
            PrintHelperKitkat.2.this.cancelLoad();
            cancel(false);
          }
        });
      }
    }.execute(new Uri[0]);
  }
  
  public void onWrite(PageRange[] paramArrayOfPageRange, ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
  {
    PrintHelperKitkat.access$000(this$0, mAttributes, val$fittingMode, mBitmap, paramParcelFileDescriptor, paramWriteResultCallback);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.print.PrintHelperKitkat.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */