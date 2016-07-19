package com.joanzapata.iconify.internal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;
import com.joanzapata.iconify.Icon;

public class CustomTypefaceSpan
  extends ReplacementSpan
{
  private static final float BASELINE_RATIO = 0.14285715F;
  private static final Paint LOCAL_PAINT = new Paint();
  private static final int ROTATION_DURATION = 2000;
  private static final Rect TEXT_BOUNDS = new Rect();
  private final boolean baselineAligned;
  private final String icon;
  private final int iconColor;
  private final float iconSizePx;
  private final float iconSizeRatio;
  private final boolean rotate;
  private final long rotationStartTime;
  private final Typeface type;
  
  public CustomTypefaceSpan(Icon paramIcon, Typeface paramTypeface, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    rotate = paramBoolean1;
    baselineAligned = paramBoolean2;
    icon = String.valueOf(paramIcon.character());
    type = paramTypeface;
    iconSizePx = paramFloat1;
    iconSizeRatio = paramFloat2;
    iconColor = paramInt;
    rotationStartTime = System.currentTimeMillis();
  }
  
  private void applyCustomTypeFace(Paint paramPaint, Typeface paramTypeface)
  {
    paramPaint.setFakeBoldText(false);
    paramPaint.setTextSkewX(0.0F);
    paramPaint.setTypeface(paramTypeface);
    if (rotate) {
      paramPaint.clearShadowLayer();
    }
    if (iconSizeRatio > 0.0F) {
      paramPaint.setTextSize(paramPaint.getTextSize() * iconSizeRatio);
    }
    for (;;)
    {
      if (iconColor < Integer.MAX_VALUE) {
        paramPaint.setColor(iconColor);
      }
      return;
      if (iconSizePx > 0.0F) {
        paramPaint.setTextSize(iconSizePx);
      }
    }
  }
  
  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint)
  {
    applyCustomTypeFace(paramPaint, type);
    paramPaint.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
    paramCanvas.save();
    if (baselineAligned) {}
    for (float f = 0.0F;; f = 0.14285715F)
    {
      if (rotate) {
        paramCanvas.rotate((float)(System.currentTimeMillis() - rotationStartTime) / 2000.0F * 360.0F, paramFloat + TEXT_BOUNDS.width() / 2.0F, paramInt4 - TEXT_BOUNDS.height() / 2.0F + TEXT_BOUNDS.height() * f);
      }
      paramCanvas.drawText(icon, paramFloat - TEXT_BOUNDSleft, paramInt4 - TEXT_BOUNDSbottom + TEXT_BOUNDS.height() * f, paramPaint);
      paramCanvas.restore();
      return;
    }
  }
  
  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt)
  {
    LOCAL_PAINT.set(paramPaint);
    applyCustomTypeFace(LOCAL_PAINT, type);
    LOCAL_PAINT.getTextBounds(icon, 0, 1, TEXT_BOUNDS);
    if (paramFontMetricsInt != null) {
      if (!baselineAligned) {
        break label109;
      }
    }
    label109:
    for (float f = 0.0F;; f = 0.14285715F)
    {
      descent = ((int)(TEXT_BOUNDS.height() * f));
      ascent = (-(TEXT_BOUNDS.height() - descent));
      top = ascent;
      bottom = descent;
      return TEXT_BOUNDS.width();
    }
  }
  
  public boolean isAnimated()
  {
    return rotate;
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.internal.CustomTypefaceSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */