package android.support.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

class AnimatedVectorDrawableCompat$1
  implements Drawable.Callback
{
  AnimatedVectorDrawableCompat$1(AnimatedVectorDrawableCompat paramAnimatedVectorDrawableCompat) {}
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    this$0.invalidateSelf();
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    this$0.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    this$0.unscheduleSelf(paramRunnable);
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.AnimatedVectorDrawableCompat.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */