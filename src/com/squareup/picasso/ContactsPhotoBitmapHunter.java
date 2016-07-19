package com.squareup.picasso;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import java.io.IOException;
import java.io.InputStream;

class ContactsPhotoBitmapHunter
  extends BitmapHunter
{
  final Context context;
  
  ContactsPhotoBitmapHunter(Context paramContext, Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    super(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction);
    context = paramContext;
  }
  
  private Bitmap decodeStream(InputStream paramInputStream, Request paramRequest)
    throws IOException
  {
    if (paramInputStream == null) {
      return null;
    }
    BitmapFactory.Options localOptions = null;
    InputStream localInputStream;
    if (paramRequest.hasSize())
    {
      localOptions = new BitmapFactory.Options();
      inJustDecodeBounds = true;
      localInputStream = getInputStream();
    }
    try
    {
      BitmapFactory.decodeStream(localInputStream, null, localOptions);
      Utils.closeQuietly(localInputStream);
      calculateInSampleSize(targetWidth, targetHeight, localOptions);
      return BitmapFactory.decodeStream(paramInputStream, null, localOptions);
    }
    finally
    {
      Utils.closeQuietly(localInputStream);
    }
  }
  
  private InputStream getInputStream()
    throws IOException
  {
    ContentResolver localContentResolver = context.getContentResolver();
    Uri localUri2 = getDatauri;
    Uri localUri1 = localUri2;
    if (localUri2.toString().startsWith(ContactsContract.Contacts.CONTENT_LOOKUP_URI.toString()))
    {
      localUri2 = ContactsContract.Contacts.lookupContact(localContentResolver, localUri2);
      localUri1 = localUri2;
      if (localUri2 == null) {
        return null;
      }
    }
    if (Build.VERSION.SDK_INT < 14) {
      return ContactsContract.Contacts.openContactPhotoInputStream(localContentResolver, localUri1);
    }
    return ContactPhotoStreamIcs.get(localContentResolver, localUri1);
  }
  
  Bitmap decode(Request paramRequest)
    throws IOException
  {
    Object localObject = null;
    try
    {
      InputStream localInputStream = getInputStream();
      localObject = localInputStream;
      paramRequest = decodeStream(localInputStream, paramRequest);
      Utils.closeQuietly(localInputStream);
      return paramRequest;
    }
    finally
    {
      Utils.closeQuietly((InputStream)localObject);
    }
  }
  
  Picasso.LoadedFrom getLoadedFrom()
  {
    return Picasso.LoadedFrom.DISK;
  }
  
  @TargetApi(14)
  private static class ContactPhotoStreamIcs
  {
    static InputStream get(ContentResolver paramContentResolver, Uri paramUri)
    {
      return ContactsContract.Contacts.openContactPhotoInputStream(paramContentResolver, paramUri, true);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.ContactsPhotoBitmapHunter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */