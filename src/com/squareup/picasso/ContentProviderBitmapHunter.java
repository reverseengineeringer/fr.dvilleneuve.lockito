package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;

class ContentProviderBitmapHunter
  extends ContentStreamBitmapHunter
{
  private static final String[] CONTENT_ORIENTATION = { "orientation" };
  
  ContentProviderBitmapHunter(Context paramContext, Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    super(paramContext, paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
  }
  
  static int getContentProviderExifRotation(ContentResolver paramContentResolver, Uri paramUri)
  {
    ContentResolver localContentResolver2 = null;
    ContentResolver localContentResolver1 = null;
    try
    {
      paramContentResolver = paramContentResolver.query(paramUri, CONTENT_ORIENTATION, null, null, null);
      if (paramContentResolver != null)
      {
        localContentResolver1 = paramContentResolver;
        localContentResolver2 = paramContentResolver;
        boolean bool = paramContentResolver.moveToFirst();
        if (bool) {}
      }
      else
      {
        if (paramContentResolver != null) {
          paramContentResolver.close();
        }
        i = 0;
        return i;
      }
      localContentResolver1 = paramContentResolver;
      localContentResolver2 = paramContentResolver;
      int j = paramContentResolver.getInt(0);
      int i = j;
      return j;
    }
    catch (RuntimeException paramContentResolver)
    {
      return 0;
    }
    finally
    {
      if (localContentResolver2 != null) {
        localContentResolver2.close();
      }
    }
  }
  
  Bitmap decode(Request paramRequest)
    throws IOException
  {
    setExifRotation(getContentProviderExifRotation(context.getContentResolver(), uri));
    return super.decodeContentStream(paramRequest);
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.ContentProviderBitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */