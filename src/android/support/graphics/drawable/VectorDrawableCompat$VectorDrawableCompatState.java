package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

class VectorDrawableCompat$VectorDrawableCompatState
  extends Drawable.ConstantState
{
  boolean mAutoMirrored;
  boolean mCacheDirty;
  boolean mCachedAutoMirrored;
  Bitmap mCachedBitmap;
  int mCachedRootAlpha;
  int[] mCachedThemeAttrs;
  ColorStateList mCachedTint;
  PorterDuff.Mode mCachedTintMode;
  int mChangingConfigurations;
  Paint mTempPaint;
  ColorStateList mTint = null;
  PorterDuff.Mode mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
  VectorDrawableCompat.VPathRenderer mVPathRenderer;
  
  public VectorDrawableCompat$VectorDrawableCompatState()
  {
    mVPathRenderer = new VectorDrawableCompat.VPathRenderer();
  }
  
  public VectorDrawableCompat$VectorDrawableCompatState(VectorDrawableCompatState paramVectorDrawableCompatState)
  {
    if (paramVectorDrawableCompatState != null)
    {
      mChangingConfigurations = mChangingConfigurations;
      mVPathRenderer = new VectorDrawableCompat.VPathRenderer(mVPathRenderer);
      if (VectorDrawableCompat.VPathRenderer.access$400(mVPathRenderer) != null) {
        VectorDrawableCompat.VPathRenderer.access$402(mVPathRenderer, new Paint(VectorDrawableCompat.VPathRenderer.access$400(mVPathRenderer)));
      }
      if (VectorDrawableCompat.VPathRenderer.access$500(mVPathRenderer) != null) {
        VectorDrawableCompat.VPathRenderer.access$502(mVPathRenderer, new Paint(VectorDrawableCompat.VPathRenderer.access$500(mVPathRenderer)));
      }
      mTint = mTint;
      mTintMode = mTintMode;
      mAutoMirrored = mAutoMirrored;
    }
  }
  
  public boolean canReuseBitmap(int paramInt1, int paramInt2)
  {
    return (paramInt1 == mCachedBitmap.getWidth()) && (paramInt2 == mCachedBitmap.getHeight());
  }
  
  public boolean canReuseCache()
  {
    return (!mCacheDirty) && (mCachedTint == mTint) && (mCachedTintMode == mTintMode) && (mCachedAutoMirrored == mAutoMirrored) && (mCachedRootAlpha == mVPathRenderer.getRootAlpha());
  }
  
  public void createCachedBitmapIfNeeded(int paramInt1, int paramInt2)
  {
    if ((mCachedBitmap == null) || (!canReuseBitmap(paramInt1, paramInt2)))
    {
      mCachedBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      mCacheDirty = true;
    }
  }
  
  public void drawCachedBitmapWithRootAlpha(Canvas paramCanvas, ColorFilter paramColorFilter, Rect paramRect)
  {
    paramColorFilter = getPaint(paramColorFilter);
    paramCanvas.drawBitmap(mCachedBitmap, null, paramRect, paramColorFilter);
  }
  
  public int getChangingConfigurations()
  {
    return mChangingConfigurations;
  }
  
  public Paint getPaint(ColorFilter paramColorFilter)
  {
    if ((!hasTranslucentRoot()) && (paramColorFilter == null)) {
      return null;
    }
    if (mTempPaint == null)
    {
      mTempPaint = new Paint();
      mTempPaint.setFilterBitmap(true);
    }
    mTempPaint.setAlpha(mVPathRenderer.getRootAlpha());
    mTempPaint.setColorFilter(paramColorFilter);
    return mTempPaint;
  }
  
  public boolean hasTranslucentRoot()
  {
    return mVPathRenderer.getRootAlpha() < 255;
  }
  
  public Drawable newDrawable()
  {
    return new VectorDrawableCompat(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    return new VectorDrawableCompat(this, null);
  }
  
  public void updateCacheStates()
  {
    mCachedTint = mTint;
    mCachedTintMode = mTintMode;
    mCachedRootAlpha = mVPathRenderer.getRootAlpha();
    mCachedAutoMirrored = mAutoMirrored;
    mCacheDirty = false;
  }
  
  public void updateCachedBitmap(int paramInt1, int paramInt2)
  {
    mCachedBitmap.eraseColor(0);
    Canvas localCanvas = new Canvas(mCachedBitmap);
    mVPathRenderer.draw(localCanvas, paramInt1, paramInt2, null);
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VectorDrawableCompatState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */