package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.widget.ImageView;

final class PicassoDrawable
  extends Drawable
{
  private static final Paint DEBUG_PAINT = new Paint();
  private static final float FADE_DURATION = 200.0F;
  boolean animating;
  private final boolean debugging;
  private final float density;
  final BitmapDrawable image;
  private final Picasso.LoadedFrom loadedFrom;
  Drawable placeholder;
  long startTimeMillis;
  
  PicassoDrawable(Context paramContext, Drawable paramDrawable, Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramContext = paramContext.getResources();
    debugging = paramBoolean2;
    density = getDisplayMetricsdensity;
    loadedFrom = paramLoadedFrom;
    image = new BitmapDrawable(paramContext, paramBitmap);
    if ((paramLoadedFrom != Picasso.LoadedFrom.MEMORY) && (!paramBoolean1)) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        placeholder = paramDrawable;
        animating = true;
        startTimeMillis = SystemClock.uptimeMillis();
      }
      return;
    }
  }
  
  private void drawDebugIndicator(Canvas paramCanvas)
  {
    DEBUG_PAINT.setColor(-1);
    paramCanvas.drawPath(getTrianglePath(new Point(0, 0), (int)(16.0F * density)), DEBUG_PAINT);
    DEBUG_PAINT.setColor(loadedFrom.debugColor);
    paramCanvas.drawPath(getTrianglePath(new Point(0, 0), (int)(15.0F * density)), DEBUG_PAINT);
  }
  
  private static Path getTrianglePath(Point paramPoint, int paramInt)
  {
    Point localPoint1 = new Point(x + paramInt, y);
    Point localPoint2 = new Point(x, y + paramInt);
    Path localPath = new Path();
    localPath.moveTo(x, y);
    localPath.lineTo(x, y);
    localPath.lineTo(x, y);
    return localPath;
  }
  
  static void setBitmap(ImageView paramImageView, Context paramContext, Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramImageView.setImageDrawable(new PicassoDrawable(paramContext, paramImageView.getDrawable(), paramBitmap, paramLoadedFrom, paramBoolean1, paramBoolean2));
  }
  
  private void setBounds(Drawable paramDrawable)
  {
    Rect localRect = getBounds();
    int i = localRect.width();
    int j = localRect.height();
    float f = i / j;
    int k = paramDrawable.getIntrinsicWidth();
    int m = paramDrawable.getIntrinsicHeight();
    if (k / m < f)
    {
      f = j / m;
      j = (int)(k * f);
      i = left - (j - i) / 2;
      paramDrawable.setBounds(i, top, i + j, bottom);
      return;
    }
    f = i / k;
    i = (int)(m * f);
    j = top - (i - j) / 2;
    paramDrawable.setBounds(left, j, right, j + i);
  }
  
  static void setPlaceholder(ImageView paramImageView, int paramInt, Drawable paramDrawable)
  {
    if (paramInt != 0)
    {
      paramImageView.setImageResource(paramInt);
      return;
    }
    paramImageView.setImageDrawable(paramDrawable);
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (!animating) {
      image.draw(paramCanvas);
    }
    for (;;)
    {
      if (debugging) {
        drawDebugIndicator(paramCanvas);
      }
      return;
      float f = (float)(SystemClock.uptimeMillis() - startTimeMillis) / 200.0F;
      if (f >= 1.0F)
      {
        animating = false;
        placeholder = null;
        image.draw(paramCanvas);
      }
      else
      {
        if (placeholder != null) {
          placeholder.draw(paramCanvas);
        }
        int i = (int)(255.0F * f);
        image.setAlpha(i);
        image.draw(paramCanvas);
        image.setAlpha(255);
        invalidateSelf();
      }
    }
  }
  
  public int getIntrinsicHeight()
  {
    return image.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    return image.getIntrinsicWidth();
  }
  
  public int getOpacity()
  {
    return image.getOpacity();
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    image.setBounds(paramRect);
    if (placeholder != null) {
      setBounds(placeholder);
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (placeholder != null) {
      placeholder.setAlpha(paramInt);
    }
    image.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    if (placeholder != null) {
      placeholder.setColorFilter(paramColorFilter);
    }
    image.setColorFilter(paramColorFilter);
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.PicassoDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */