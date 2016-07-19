package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.zzrf;
import com.google.android.gms.internal.zzrg;
import com.google.android.gms.internal.zzrh;
import java.lang.ref.WeakReference;

public abstract class zza
{
  private boolean wA = true;
  final zza wu;
  protected int wv = 0;
  protected int ww = 0;
  protected boolean wx = false;
  private boolean wy = true;
  private boolean wz = false;
  
  public zza(Uri paramUri, int paramInt)
  {
    wu = new zza(paramUri);
    ww = paramInt;
  }
  
  private Drawable zza(Context paramContext, zzrh paramzzrh, int paramInt)
  {
    return paramContext.getResources().getDrawable(paramInt);
  }
  
  protected zzrf zza(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null)
    {
      localDrawable = paramDrawable1;
      if (!(paramDrawable1 instanceof zzrf)) {}
    }
    for (Drawable localDrawable = ((zzrf)paramDrawable1).zzarm();; localDrawable = null) {
      return new zzrf(localDrawable, paramDrawable2);
    }
  }
  
  void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    zzb.zzw(paramBitmap);
    zza(new BitmapDrawable(paramContext.getResources(), paramBitmap), paramBoolean, false, true);
  }
  
  void zza(Context paramContext, zzrh paramzzrh)
  {
    if (wA) {
      zza(null, false, true, false);
    }
  }
  
  void zza(Context paramContext, zzrh paramzzrh, boolean paramBoolean)
  {
    Drawable localDrawable = null;
    if (ww != 0) {
      localDrawable = zza(paramContext, paramzzrh, ww);
    }
    zza(localDrawable, paramBoolean, false, false);
  }
  
  protected abstract void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  protected boolean zzc(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (wy) && (!paramBoolean2) && (!paramBoolean1);
  }
  
  public void zzfu(int paramInt)
  {
    ww = paramInt;
  }
  
  static final class zza
  {
    public final Uri uri;
    
    public zza(Uri paramUri)
    {
      uri = paramUri;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof zza)) {
        return false;
      }
      if (this == paramObject) {
        return true;
      }
      return zzaa.equal(uri, uri);
    }
    
    public int hashCode()
    {
      return zzaa.hashCode(new Object[] { uri });
    }
  }
  
  public static final class zzb
    extends zza
  {
    private WeakReference<ImageView> wB;
    
    public zzb(ImageView paramImageView, int paramInt)
    {
      super(paramInt);
      zzb.zzw(paramImageView);
      wB = new WeakReference(paramImageView);
    }
    
    public zzb(ImageView paramImageView, Uri paramUri)
    {
      super(0);
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
  
  public static final class zzc
    extends zza
  {
    private WeakReference<ImageManager.OnImageLoadedListener> wC;
    
    public zzc(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
    {
      super(0);
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */