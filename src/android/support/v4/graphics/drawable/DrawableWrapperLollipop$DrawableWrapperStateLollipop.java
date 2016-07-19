package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

class DrawableWrapperLollipop$DrawableWrapperStateLollipop
  extends DrawableWrapperDonut.DrawableWrapperState
{
  DrawableWrapperLollipop$DrawableWrapperStateLollipop(@Nullable DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
  }
  
  public Drawable newDrawable(@Nullable Resources paramResources)
  {
    return new DrawableWrapperLollipop(this, paramResources);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableWrapperLollipop.DrawableWrapperStateLollipop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */