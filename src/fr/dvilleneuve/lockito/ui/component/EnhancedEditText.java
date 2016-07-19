package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import fr.dvilleneuve.lockito.R.styleable;

public class EnhancedEditText
  extends EditText
  implements View.OnTouchListener
{
  public static final int DRAWABLE_CLICK_PADDING = 10;
  private OnClickDrawableListener onClickDrawableListener;
  private ColorStateList prefixColors;
  private String prefixIcon;
  private IconDrawable prefixIconDrawable;
  private String prefixText;
  private TextDrawable prefixTextDrawable;
  private ColorStateList suffixColors;
  private String suffixIcon;
  private IconDrawable suffixIconDrawable;
  private String suffixText;
  private TextDrawable suffixTextDrawable;
  
  public EnhancedEditText(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public EnhancedEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initAttrs(paramContext, paramAttributeSet, 2131427676);
    init();
  }
  
  public EnhancedEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initAttrs(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private int getCurrentDrawablColor(ColorStateList paramColorStateList)
  {
    return paramColorStateList.getColorForState(getDrawableState(), getCurrentTextColor());
  }
  
  private IconDrawable getIconDrawable(FontAwesomeIcons paramFontAwesomeIcons, ColorStateList paramColorStateList)
  {
    if (isInEditMode()) {}
    while (paramFontAwesomeIcons == null) {
      return null;
    }
    return new IconDrawable(getContext(), paramFontAwesomeIcons).sizePx((int)getTextSize()).color(getCurrentDrawablColor(paramColorStateList));
  }
  
  private TextDrawable getTextDrawable(String paramString, ColorStateList paramColorStateList)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return null;
    }
    return new TextDrawable(getContext(), paramString).sizePx(getTextSize()).typeface(getTypeface()).color(getCurrentDrawablColor(paramColorStateList));
  }
  
  private void init()
  {
    setCompoundDrawablePadding(16);
    if (!isInEditMode())
    {
      if (prefixIcon != null) {
        setPrefixIcon(FontAwesomeIcons.valueOf(prefixIcon));
      }
      if (suffixIcon != null) {
        setSuffixIcon(FontAwesomeIcons.valueOf(suffixIcon));
      }
    }
    setPrefixText(prefixText);
    setSuffixText(suffixText);
    updateTextColor();
  }
  
  private void initAttrs(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.EnhancedEditText, paramInt, 0);
    prefixIcon = paramContext.getString(0);
    prefixText = paramContext.getString(1);
    if (paramContext.hasValue(2))
    {
      prefixColors = paramContext.getColorStateList(2);
      suffixIcon = paramContext.getString(3);
      suffixText = paramContext.getString(4);
      if (!paramContext.hasValue(5)) {
        break label97;
      }
    }
    label97:
    for (suffixColors = paramContext.getColorStateList(5);; suffixColors = getTextColors())
    {
      paramContext.recycle();
      return;
      prefixColors = getTextColors();
      break;
    }
  }
  
  private boolean touchesDrawable(MotionEvent paramMotionEvent, Drawable paramDrawable, DrawablePosition paramDrawablePosition)
  {
    if (paramDrawable != null)
    {
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      paramMotionEvent = paramDrawable.getBounds();
      if ((j >= (getHeight() - paramMotionEvent.height()) / 2 - 10) && (j <= (getHeight() + paramMotionEvent.height()) / 2 + 10))
      {
        if ((paramDrawablePosition == DrawablePosition.PREFIX) && (i <= paramMotionEvent.width() + 10)) {}
        while ((paramDrawablePosition == DrawablePosition.SUFFIX) && (i >= getWidth() - paramMotionEvent.width() - 10)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void updateCompoundDrawables()
  {
    Object localObject1;
    if (prefixIconDrawable != null)
    {
      localObject1 = prefixIconDrawable;
      if (suffixIconDrawable == null) {
        break label41;
      }
    }
    label41:
    for (Object localObject2 = suffixIconDrawable;; localObject2 = suffixTextDrawable)
    {
      setCompoundDrawablesWithIntrinsicBounds((Drawable)localObject1, null, (Drawable)localObject2, null);
      return;
      localObject1 = prefixTextDrawable;
      break;
    }
  }
  
  private void updateTextColor()
  {
    int i = getCurrentDrawablColor(prefixColors);
    if (prefixIconDrawable != null) {
      prefixIconDrawable.color(i);
    }
    if (prefixTextDrawable != null) {
      prefixTextDrawable.color(i);
    }
    i = getCurrentDrawablColor(suffixColors);
    if (suffixIconDrawable != null) {
      suffixIconDrawable.color(i);
    }
    if (suffixTextDrawable != null) {
      suffixTextDrawable.color(i);
    }
  }
  
  private void updateTextSize()
  {
    float f = getTextSize();
    if (prefixIconDrawable != null) {
      prefixIconDrawable.sizePx((int)f);
    }
    if (prefixTextDrawable != null) {
      prefixTextDrawable.sizePx((int)f);
    }
    if (suffixIconDrawable != null) {
      suffixIconDrawable.sizePx((int)f);
    }
    if (suffixTextDrawable != null) {
      suffixTextDrawable.sizePx((int)f);
    }
  }
  
  public void drawableStateChanged()
  {
    super.drawableStateChanged();
    updateTextColor();
  }
  
  public Drawable getPrefixDrawable()
  {
    if (prefixTextDrawable != null) {
      return prefixTextDrawable;
    }
    return prefixIconDrawable;
  }
  
  public Drawable getSuffixDrawable()
  {
    if (suffixTextDrawable != null) {
      return suffixTextDrawable;
    }
    return suffixIconDrawable;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 1) && (onClickDrawableListener != null))
    {
      paramView = getPrefixDrawable();
      if (touchesDrawable(paramMotionEvent, paramView, DrawablePosition.PREFIX))
      {
        onClickDrawableListener.onClickDrawable(paramView, DrawablePosition.PREFIX);
        return true;
      }
      paramView = getSuffixDrawable();
      if (touchesDrawable(paramMotionEvent, paramView, DrawablePosition.SUFFIX))
      {
        onClickDrawableListener.onClickDrawable(paramView, DrawablePosition.SUFFIX);
        return true;
      }
    }
    return false;
  }
  
  public void setOnClickDrawableListener(OnClickDrawableListener paramOnClickDrawableListener)
  {
    onClickDrawableListener = paramOnClickDrawableListener;
    if (paramOnClickDrawableListener != null)
    {
      setOnTouchListener(this);
      return;
    }
    setOnTouchListener(null);
  }
  
  public void setPrefixColor(int paramInt)
  {
    setPrefixColors(ColorStateList.valueOf(paramInt));
  }
  
  public void setPrefixColorRes(int paramInt)
  {
    setPrefixColor(getContext().getResources().getColor(paramInt));
  }
  
  public void setPrefixColors(int paramInt)
  {
    setPrefixColors(getContext().getResources().getColorStateList(paramInt));
  }
  
  public void setPrefixColors(ColorStateList paramColorStateList)
  {
    prefixColors = paramColorStateList;
    updateTextColor();
  }
  
  public void setPrefixIcon(FontAwesomeIcons paramFontAwesomeIcons)
  {
    prefixIconDrawable = getIconDrawable(paramFontAwesomeIcons, prefixColors);
    updateCompoundDrawables();
  }
  
  public void setPrefixText(String paramString)
  {
    prefixTextDrawable = getTextDrawable(paramString, prefixColors);
    updateCompoundDrawables();
  }
  
  public void setPrefixTextRes(int paramInt)
  {
    setPrefixText(getContext().getString(paramInt));
  }
  
  public void setSuffixColor(int paramInt)
  {
    setSuffixColors(ColorStateList.valueOf(paramInt));
  }
  
  public void setSuffixColorRes(int paramInt)
  {
    setSuffixColor(getContext().getResources().getColor(paramInt));
  }
  
  public void setSuffixColors(int paramInt)
  {
    setSuffixColors(getContext().getResources().getColorStateList(paramInt));
  }
  
  public void setSuffixColors(ColorStateList paramColorStateList)
  {
    suffixColors = paramColorStateList;
    updateTextColor();
  }
  
  public void setSuffixIcon(FontAwesomeIcons paramFontAwesomeIcons)
  {
    suffixIconDrawable = getIconDrawable(paramFontAwesomeIcons, suffixColors);
    updateCompoundDrawables();
  }
  
  public void setSuffixText(String paramString)
  {
    suffixTextDrawable = getTextDrawable(paramString, suffixColors);
    updateCompoundDrawables();
  }
  
  public void setSuffixTextRes(int paramInt)
  {
    setSuffixText(getContext().getString(paramInt));
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    super.setTextSize(paramInt, paramFloat);
    updateTextSize();
  }
  
  public static enum DrawablePosition
  {
    PREFIX,  SUFFIX;
    
    private DrawablePosition() {}
  }
  
  public static abstract interface OnClickDrawableListener
  {
    public abstract void onClickDrawable(Drawable paramDrawable, EnhancedEditText.DrawablePosition paramDrawablePosition);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.EnhancedEditText
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */