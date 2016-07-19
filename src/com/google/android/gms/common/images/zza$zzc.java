package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzb;
import java.lang.ref.WeakReference;

public final class zza$zzc
  extends zza
{
  private WeakReference<ImageManager.OnImageLoadedListener> wC;
  
  public zza$zzc(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    super(paramUri, 0);
    zzb.zzw(paramOnImageLoadedListener);
    wC = new WeakReference(paramOnImageLoadedListener);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzc)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (zzc)paramObject;
    ImageManager.OnImageLoadedListener localOnImageLoadedListener1 = (ImageManager.OnImageLoadedListener)wC.get();
    ImageManager.OnImageLoadedListener localOnImageLoadedListener2 = (ImageManager.OnImageLoadedListener)wC.get();
    if ((localOnImageLoadedListener2 != null) && (localOnImageLoadedListener1 != null) && (zzaa.equal(localOnImageLoadedListener2, localOnImageLoadedListener1)) && (zzaa.equal(wu, wu))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { wu });
  }
  
  protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (!paramBoolean2)
    {
      ImageManager.OnImageLoadedListener localOnImageLoadedListener = (ImageManager.OnImageLoadedListener)wC.get();
      if (localOnImageLoadedListener != null) {
        localOnImageLoadedListener.onImageLoaded(wu.uri, paramDrawable, paramBoolean3);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.zza.zzc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */