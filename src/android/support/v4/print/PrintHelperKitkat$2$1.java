package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.print.PrintAttributes;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentInfo.Builder;
import java.io.FileNotFoundException;

class PrintHelperKitkat$2$1
  extends AsyncTask<Uri, Boolean, Bitmap>
{
  PrintHelperKitkat$2$1(PrintHelperKitkat.2 param2, CancellationSignal paramCancellationSignal, PrintAttributes paramPrintAttributes1, PrintAttributes paramPrintAttributes2, PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback) {}
  
  protected Bitmap doInBackground(Uri... paramVarArgs)
  {
    try
    {
      paramVarArgs = PrintHelperKitkat.access$200(this$1.this$0, this$1.val$imageFile, 3500);
      return paramVarArgs;
    }
    catch (FileNotFoundException paramVarArgs) {}
    return null;
  }
  
  protected void onCancelled(Bitmap paramBitmap)
  {
    val$layoutResultCallback.onLayoutCancelled();
    this$1.mLoadBitmap = null;
  }
  
  protected void onPostExecute(Bitmap paramBitmap)
  {
    super.onPostExecute(paramBitmap);
    Object localObject = paramBitmap;
    if (paramBitmap != null) {
      if (this$1.this$0.mPrintActivityRespectsOrientation)
      {
        localObject = paramBitmap;
        if (this$1.this$0.mOrientation != 0) {
          break label108;
        }
      }
    }
    for (;;)
    {
      try
      {
        PrintAttributes.MediaSize localMediaSize = PrintHelperKitkat.2.access$300(this$1).getMediaSize();
        localObject = paramBitmap;
        if (localMediaSize != null)
        {
          localObject = paramBitmap;
          if (localMediaSize.isPortrait() != PrintHelperKitkat.access$400(paramBitmap))
          {
            localObject = new Matrix();
            ((Matrix)localObject).postRotate(90.0F);
            localObject = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject, true);
          }
        }
        label108:
        this$1.mBitmap = ((Bitmap)localObject);
        if (localObject == null) {
          break label190;
        }
        paramBitmap = new PrintDocumentInfo.Builder(this$1.val$jobName).setContentType(1).setPageCount(1).build();
        if (!val$newPrintAttributes.equals(val$oldPrintAttributes))
        {
          bool = true;
          val$layoutResultCallback.onLayoutFinished(paramBitmap, bool);
          this$1.mLoadBitmap = null;
          return;
        }
      }
      finally {}
      boolean bool = false;
      continue;
      label190:
      val$layoutResultCallback.onLayoutFailed(null);
    }
  }
  
  protected void onPreExecute()
  {
    val$cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
    {
      public void onCancel()
      {
        PrintHelperKitkat.2.access$100(this$1);
        cancel(false);
      }
    });
  }
}

/* Location:
 * Qualified Name:     android.support.v4.print.PrintHelperKitkat.2.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */