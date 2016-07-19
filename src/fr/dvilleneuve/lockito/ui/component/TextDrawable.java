package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

public class TextDrawable
  extends Drawable
{
  private final Rect bounds = new Rect();
  private final Context context;
  private Paint.FontMetricsInt fontMetrics;
  private final Paint paint = new Paint();
  private final String text;
  
  public TextDrawable(Context paramContext, String paramString)
  {
    context = paramContext;
    text = paramString;
    paint.setColor(-16777216);
    paint.setTextSize(convertSpToPx(15.0F));
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setTextAlign(Paint.Align.RIGHT);
  }
  
  private float convertDpToPx(float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, context.getResources().getDisplayMetrics());
  }
  
  private float convertSpToPx(float paramFloat)
  {
    return TypedValue.applyDimension(2, paramFloat, context.getResources().getDisplayMetrics());
  }
  
  private Paint.FontMetricsInt getFontMetrics()
  {
    if (fontMetrics == null) {
      fontMetrics = paint.getFontMetricsInt();
    }
    return fontMetrics;
  }
  
  public TextDrawable align(Paint.Align paramAlign)
  {
    paint.setTextAlign(paramAlign);
    invalidateSelf();
    return this;
  }
  
  public TextDrawable alpha(int paramInt)
  {
    setAlpha(paramInt);
    invalidateSelf();
    return this;
  }
  
  public TextDrawable bold(boolean paramBoolean)
  {
    paint.setFakeBoldText(paramBoolean);
    invalidateSelf();
    return this;
  }
  
  public TextDrawable color(int paramInt)
  {
    paint.setColor(paramInt);
    invalidateSelf();
    return this;
  }
  
  public TextDrawable colorRes(int paramInt)
  {
    return color(context.getResources().getColor(paramInt));
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.drawText(text, getIntrinsicWidth(), getIntrinsicHeight() - getFontMetricsdescent, paint);
  }
  
  public int getIntrinsicHeight()
  {
    return getFontMetricsbottom - getFontMetricstop;
  }
  
  public int getIntrinsicWidth()
  {
    paint.getTextBounds(text, 0, text.length(), bounds);
    return bounds.width();
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public void setAlpha(int paramInt)
  {
    paint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    paint.setColorFilter(paramColorFilter);
  }
  
  public TextDrawable sizeDp(float paramFloat)
  {
    return sizePx(convertDpToPx(paramFloat));
  }
  
  public TextDrawable sizePx(float paramFloat)
  {
    paint.setTextSize(paramFloat);
    invalidateSelf();
    return this;
  }
  
  public TextDrawable sizeRes(int paramInt)
  {
    return sizePx(context.getResources().getDimensionPixelSize(paramInt));
  }
  
  public TextDrawable typeface(Typeface paramTypeface)
  {
    paint.setTypeface(paramTypeface);
    invalidateSelf();
    return this;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.TextDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */