package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class DrawableWrapperHoneycomb
  extends DrawableWrapperDonut
{
  DrawableWrapperHoneycomb(Drawable paramDrawable)
  {
    super(paramDrawable);
  }
  
  DrawableWrapperHoneycomb(DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
  }
  
  public void jumpToCurrentState()
  {
    mDrawable.jumpToCurrentState();
  }
  
  @NonNull
  DrawableWrapperDonut.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateHoneycomb(mState, null);
  }
  
  private static class DrawableWrapperStateHoneycomb
    extends DrawableWrapperDonut.DrawableWrapperState
  {
    DrawableWrapperStateHoneycomb(@Nullable DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
    {
      super(paramResources);
    }
    
    public Drawable newDrawable(@Nullable Resources paramResources)
    {
      return new DrawableWrapperHoneycomb(this, paramResources);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableWrapperHoneycomb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */