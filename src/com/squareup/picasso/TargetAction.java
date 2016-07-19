package com.squareup.picasso;

import android.graphics.Bitmap;

final class TargetAction
  extends Action<Target>
{
  TargetAction(Picasso paramPicasso, Target paramTarget, Request paramRequest, boolean paramBoolean, String paramString)
  {
    super(paramPicasso, paramTarget, paramRequest, paramBoolean, false, 0, null, paramString);
  }
  
  void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom)
  {
    if (paramBitmap == null) {
      throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[] { this }));
    }
    Target localTarget = (Target)getTarget();
    if (localTarget != null)
    {
      localTarget.onBitmapLoaded(paramBitmap, paramLoadedFrom);
      if (paramBitmap.isRecycled()) {
        throw new IllegalStateException("Target callback must not recycle bitmap!");
      }
    }
  }
  
  void error()
  {
    Target localTarget = (Target)getTarget();
    if (localTarget != null) {
      localTarget.onBitmapFailed(errorDrawable);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.TargetAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */