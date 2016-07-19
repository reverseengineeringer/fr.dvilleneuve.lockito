package fr.dvilleneuve.lockito.ui.component;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import fr.dvilleneuve.lockito.core.helper.StringFormatHelper;
import fr.dvilleneuve.lockito.core.model.Point;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EView;

@EView
public class AddressEditText
  extends TextView
  implements View.OnTouchListener
{
  public static final int DRAWABLE_CLICK_PADDING = 10;
  private IconDrawable iconDrawable;
  private Point point;
  private boolean showAddress = true;
  @Bean
  StringFormatHelper stringFormatHelper;
  
  public AddressEditText(Context paramContext)
  {
    super(paramContext);
  }
  
  public AddressEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public AddressEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private IconDrawable getIconDrawable()
  {
    if (isInEditMode()) {
      return null;
    }
    if (iconDrawable == null) {
      iconDrawable = new IconDrawable(getContext(), FontAwesomeIcons.fa_exchange).sizePx((int)getTextSize()).color(17170432);
    }
    return iconDrawable;
  }
  
  private void showAddress()
  {
    setText(point.getAddress());
  }
  
  private void showCoordinates()
  {
    setText(stringFormatHelper.formatLocation(point));
  }
  
  private boolean touchesDrawable(MotionEvent paramMotionEvent, Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      paramMotionEvent = paramDrawable.getBounds();
      if ((j >= (getHeight() - paramMotionEvent.height()) / 2 - 10) && (j <= (getHeight() + paramMotionEvent.height()) / 2 + 10) && (i >= getWidth() - paramMotionEvent.width() - 10)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (touchesDrawable(paramMotionEvent, iconDrawable)))
    {
      if (showAddress)
      {
        showCoordinates();
        showAddress = false;
        return true;
      }
      showAddress();
      showAddress = true;
      return true;
    }
    return false;
  }
  
  public void setPoint(Point paramPoint)
  {
    if (paramPoint == null) {
      return;
    }
    point = paramPoint;
    if (paramPoint.getAddress() != null)
    {
      setCompoundDrawablesWithIntrinsicBounds(null, null, getIconDrawable(), null);
      setOnTouchListener(this);
      if (showAddress)
      {
        showAddress();
        return;
      }
      showCoordinates();
      return;
    }
    setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    setOnTouchListener(null);
    showCoordinates();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.component.AddressEditText
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */