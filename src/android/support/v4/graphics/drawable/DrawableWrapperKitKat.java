package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class DrawableWrapperKitKat
  extends DrawableWrapperHoneycomb
{
  DrawableWrapperKitKat(Drawable paramDrawable)
  {
    super(paramDrawable);
  }
  
  DrawableWrapperKitKat(DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
  }
  
  public boolean isAutoMirrored()
  {
    return mDrawable.isAutoMirrored();
  }
  
  @NonNull
  DrawableWrapperDonut.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateKitKat(mState, null);
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    mDrawable.setAutoMirrored(paramBoolean);
  }
  
  private static class DrawableWrapperStateKitKat
    extends DrawableWrapperDonut.DrawableWrapperState
  {
    DrawableWrapperStateKitKat(@Nullable DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, @Nullable Resources paramResources)
    {
      super(paramResources);
    }
    
    public Drawable newDrawable(@Nullable Resources paramResources)
    {
      return new DrawableWrapperKitKat(this, paramResources);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.graphics.drawable.DrawableWrapperKitKat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */