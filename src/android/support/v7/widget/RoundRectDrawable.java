package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

class RoundRectDrawable
  extends Drawable
{
  private ColorStateList mBackground;
  private final RectF mBoundsF;
  private final Rect mBoundsI;
  private boolean mInsetForPadding = false;
  private boolean mInsetForRadius = true;
  private float mPadding;
  private final Paint mPaint;
  private float mRadius;
  private ColorStateList mTint;
  private PorterDuffColorFilter mTintFilter;
  private PorterDuff.Mode mTintMode = PorterDuff.Mode.SRC_IN;
  
  public RoundRectDrawable(ColorStateList paramColorStateList, float paramFloat)
  {
    mRadius = paramFloat;
    mPaint = new Paint(5);
    setBackground(paramColorStateList);
    mBoundsF = new RectF();
    mBoundsI = new Rect();
  }
  
  private PorterDuffColorFilter createTintFilter(ColorStateList paramColorStateList, PorterDuff.Mode paramMode)
  {
    if ((paramColorStateList == null) || (paramMode == null)) {
      return null;
    }
    return new PorterDuffColorFilter(paramColorStateList.getColorForState(getState(), 0), paramMode);
  }
  
  private void setBackground(ColorStateList paramColorStateList)
  {
    ColorStateList localColorStateList = paramColorStateList;
    if (paramColorStateList == null) {
      localColorStateList = ColorStateList.valueOf(0);
    }
    mBackground = localColorStateList;
    mPaint.setColor(mBackground.getColorForState(getState(), mBackground.getDefaultColor()));
  }
  
  private void updateBounds(Rect paramRect)
  {
    Rect localRect = paramRect;
    if (paramRect == null) {
      localRect = getBounds();
    }
    mBoundsF.set(left, top, right, bottom);
    mBoundsI.set(localRect);
    if (mInsetForPadding)
    {
      float f1 = RoundRectDrawableWithShadow.calculateVerticalPadding(mPadding, mRadius, mInsetForRadius);
      float f2 = RoundRectDrawableWithShadow.calculateHorizontalPadding(mPadding, mRadius, mInsetForRadius);
      mBoundsI.inset((int)Math.ceil(f2), (int)Math.ceil(f1));
      mBoundsF.set(mBoundsI);
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    Paint localPaint = mPaint;
    if ((mTintFilter != null) && (localPaint.getColorFilter() == null)) {
      localPaint.setColorFilter(mTintFilter);
    }
    for (int i = 1;; i = 0)
    {
      paramCanvas.drawRoundRect(mBoundsF, mRadius, mRadius, localPaint);
      if (i != 0) {
        localPaint.setColorFilter(null);
      }
      return;
    }
  }
  
  public ColorStateList getColor()
  {
    return mBackground;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public void getOutline(Outline paramOutline)
  {
    paramOutline.setRoundRect(mBoundsI, mRadius);
  }
  
  float getPadding()
  {
    return mPadding;
  }
  
  public float getRadius()
  {
    return mRadius;
  }
  
  public boolean isStateful()
  {
    return ((mTint != null) && (mTint.isStateful())) || ((mBackground != null) && (mBackground.isStateful())) || (super.isStateful());
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    updateBounds(paramRect);
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    int i = mBackground.getColorForState(paramArrayOfInt, mBackground.getDefaultColor());
    if (i != mPaint.getColor()) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      if (bool1) {
        mPaint.setColor(i);
      }
      boolean bool2 = bool1;
      if (mTint != null)
      {
        bool2 = bool1;
        if (mTintMode != null)
        {
          mTintFilter = createTintFilter(mTint, mTintMode);
          bool2 = true;
        }
      }
      return bool2;
    }
  }
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
  }
  
  public void setColor(@Nullable ColorStateList paramColorStateList)
  {
    setBackground(paramColorStateList);
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  void setPadding(float paramFloat, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramFloat == mPadding) && (mInsetForPadding == paramBoolean1) && (mInsetForRadius == paramBoolean2)) {
      return;
    }
    mPadding = paramFloat;
    mInsetForPadding = paramBoolean1;
    mInsetForRadius = paramBoolean2;
    updateBounds(null);
    invalidateSelf();
  }
  
  void setRadius(float paramFloat)
  {
    if (paramFloat == mRadius) {
      return;
    }
    mRadius = paramFloat;
    updateBounds(null);
    invalidateSelf();
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    mTint = paramColorStateList;
    mTintFilter = createTintFilter(mTint, mTintMode);
    invalidateSelf();
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    mTintMode = paramMode;
    mTintFilter = createTintFilter(mTint, mTintMode);
    invalidateSelf();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.RoundRectDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */