package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

public abstract interface Downloader
{
  public abstract Response load(Uri paramUri, boolean paramBoolean)
    throws IOException;
  
  public static class Response
  {
    final Bitmap bitmap;
    final boolean cached;
    final InputStream stream;
    
    public Response(Bitmap paramBitmap, boolean paramBoolean)
    {
      if (paramBitmap == null) {
        throw new IllegalArgumentException("Bitmap may not be null.");
      }
      stream = null;
      bitmap = paramBitmap;
      cached = paramBoolean;
    }
    
    public Response(InputStream paramInputStream, boolean paramBoolean)
    {
      if (paramInputStream == null) {
        throw new IllegalArgumentException("Stream may not be null.");
      }
      stream = paramInputStream;
      bitmap = null;
      cached = paramBoolean;
    }
    
    public Bitmap getBitmap()
    {
      return bitmap;
    }
    
    public InputStream getInputStream()
    {
      return stream;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Downloader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */