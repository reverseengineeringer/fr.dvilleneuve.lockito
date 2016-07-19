package com.joanzapata.iconify;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.TypedValue;
import com.joanzapata.iconify.internal.IconFontDescriptorWrapper;

public class IconDrawable
  extends Drawable
{
  public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
  private int alpha = 255;
  private Context context;
  private Icon icon;
  private TextPaint paint;
  private int size = -1;
  
  public IconDrawable(Context paramContext, Icon paramIcon)
  {
    init(paramContext, paramIcon);
  }
  
  public IconDrawable(Context paramContext, String paramString)
  {
    Icon localIcon = Iconify.findIconForKey(paramString);
    if (localIcon == null) {
      throw new IllegalArgumentException("No icon with that key \"" + paramString + "\".");
    }
    init(paramContext, localIcon);
  }
  
  private int convertDpToPx(Context paramContext, float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  private void init(Context paramContext, Icon paramIcon)
  {
    context = paramContext;
    icon = paramIcon;
    paint = new TextPaint();
    IconFontDescriptorWrapper localIconFontDescriptorWrapper = Iconify.findTypefaceOf(paramIcon);
    if (localIconFontDescriptorWrapper == null) {
      throw new IllegalStateException("Unable to find the module associated with icon " + paramIcon.key() + ", have you registered the module " + "you are trying to use with Iconify.with(...) in your Application?");
    }
    paint.setTypeface(localIconFontDescriptorWrapper.getTypeface(paramContext));
    paint.setStyle(Paint.Style.FILL);
    paint.setTextAlign(Paint.Align.CENTER);
    paint.setUnderlineText(false);
    paint.setColor(-16777216);
    paint.setAntiAlias(true);
  }
  
  private boolean isEnabled(int[] paramArrayOfInt)
  {
    boolean bool2 = false;
    int j = paramArrayOfInt.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramArrayOfInt[i] == 16842910) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public IconDrawable actionBarSize()
  {
    return sizeDp(24);
  }
  
  public IconDrawable alpha(int paramInt)
  {
    setAlpha(paramInt);
    invalidateSelf();
    return this;
  }
  
  public void clearColorFilter()
  {
    paint.setColorFilter(null);
  }
  
  public IconDrawable color(int paramInt)
  {
    paint.setColor(paramInt);
    invalidateSelf();
    return this;
  }
  
  public IconDrawable colorRes(int paramInt)
  {
    paint.setColor(context.getResources().getColor(paramInt));
    invalidateSelf();
    return this;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect1 = getBounds();
    int i = localRect1.height();
    paint.setTextSize(i);
    Rect localRect2 = new Rect();
    String str = String.valueOf(icon.character());
    paint.getTextBounds(str, 0, 1, localRect2);
    int j = localRect2.height();
    float f1 = top;
    float f2 = (i - j) / 2.0F;
    float f3 = j;
    float f4 = bottom;
    paramCanvas.drawText(str, localRect1.exactCenterX(), f1 + f2 + f3 - f4, paint);
  }
  
  public int getIntrinsicHeight()
  {
    return size;
  }
  
  public int getIntrinsicWidth()
  {
    return size;
  }
  
  public int getOpacity()
  {
    return alpha;
  }
  
  public boolean isStateful()
  {
    return true;
  }
  
  public void setAlpha(int paramInt)
  {
    alpha = paramInt;
    paint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    paint.setColorFilter(paramColorFilter);
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    int j = paint.getAlpha();
    if (isEnabled(paramArrayOfInt)) {}
    for (int i = alpha;; i = alpha / 2)
    {
      paint.setAlpha(i);
      if (j == i) {
        break;
      }
      return true;
    }
    return false;
  }
  
  public void setStyle(Paint.Style paramStyle)
  {
    paint.setStyle(paramStyle);
  }
  
  public IconDrawable sizeDp(int paramInt)
  {
    return sizePx(convertDpToPx(context, paramInt));
  }
  
  public IconDrawable sizePx(int paramInt)
  {
    size = paramInt;
    setBounds(0, 0, paramInt, paramInt);
    invalidateSelf();
    return this;
  }
  
  public IconDrawable sizeRes(int paramInt)
  {
    return sizePx(context.getResources().getDimensionPixelSize(paramInt));
  }
}

/* Location:
 * Qualified Name:     com.joanzapata.iconify.IconDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */