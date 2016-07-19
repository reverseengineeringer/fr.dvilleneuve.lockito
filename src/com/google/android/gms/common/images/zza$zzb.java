package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.zzrf;
import com.google.android.gms.internal.zzrg;
import java.lang.ref.WeakReference;

public final class zza$zzb
  extends zza
{
  private WeakReference<ImageView> wB;
  
  public zza$zzb(ImageView paramImageView, int paramInt)
  {
    super(null, paramInt);
    zzb.zzw(paramImageView);
    wB = new WeakReference(paramImageView);
  }
  
  public zza$zzb(ImageView paramImageView, Uri paramUri)
  {
    super(paramUri, 0);
    zzb.zzw(paramImageView);
    wB = new WeakReference(paramImageView);
  }
  
  private void zza(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((!paramBoolean2) && (!paramBoolean3)) {}
    for (int i = 1; (i != 0) && ((paramImageView instanceof zzrg)); i = 0)
    {
      int j = ((zzrg)paramImageView).zzaro();
      if ((ww == 0) || (j != ww)) {
        break;
      }
      return;
    }
    paramBoolean1 = zzc(paramBoolean1, paramBoolean2);
    if (paramBoolean1) {
      paramDrawable = zza(paramImageView.getDrawable(), paramDrawable);
    }
    for (;;)
    {
      paramImageView.setImageDrawable(paramDrawable);
      zzrg localzzrg;
      if ((paramImageView instanceof zzrg))
      {
        localzzrg = (zzrg)paramImageView;
        if (!paramBoolean3) {
          break label149;
        }
        paramImageView = wu.uri;
        label110:
        localzzrg.zzp(paramImageView);
        if (i == 0) {
          break label154;
        }
      }
      label149:
      label154:
      for (i = ww;; i = 0)
      {
        localzzrg.zzfw(i);
        if (!paramBoolean1) {
          break;
        }
        ((zzrf)paramDrawable).startTransition(250);
        return;
        paramImageView = null;
        break label110;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzb)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    Object localObject = (zzb)paramObject;
    paramObject = (ImageView)wB.get();
    localObject = (ImageView)wB.get();
    if ((localObject != null) && (paramObject != null) && (zzaa.equal(localObject, paramObject))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    ImageView localImageView = (ImageView)wB.get();
    if (localImageView != null) {
      zza(localImageView, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.zza.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */