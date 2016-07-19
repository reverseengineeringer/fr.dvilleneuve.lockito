package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.IOException;

class ResourceBitmapHunter
  extends BitmapHunter
{
  private final Context context;
  
  ResourceBitmapHunter(Context paramContext, Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    super(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    context = paramContext;
  }
  
  private Bitmap decodeResource(Resources paramResources, Request paramRequest)
  {
    int i = resourceId;
    BitmapFactory.Options localOptions = null;
    if (paramRequest.hasSize())
    {
      localOptions = new BitmapFactory.Options();
      inJustDecodeBounds = true;
      BitmapFactory.decodeResource(paramResources, i, localOptions);
      calculateInSampleSize(targetWidth, targetHeight, localOptions);
    }
    return BitmapFactory.decodeResource(paramResources, i, localOptions);
  }
  
  Bitmap decode(Request paramRequest)
    throws IOException
  {
    return decodeResource(context.getResources(), paramRequest);
  }
  
  Picasso.LoadedFrom getLoadedFrom()
  {
    return Picasso.LoadedFrom.DISK;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.ResourceBitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */