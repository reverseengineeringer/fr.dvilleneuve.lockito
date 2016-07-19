package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;

public abstract interface ActionBarDrawerToggle$Delegate
{
  public abstract Context getActionBarThemedContext();
  
  public abstract Drawable getThemeUpIndicator();
  
  public abstract boolean isNavigationVisible();
  
  public abstract void setActionBarDescription(@StringRes int paramInt);
  
  public abstract void setActionBarUpIndicator(Drawable paramDrawable, @StringRes int paramInt);
}

/* Location:
 * Qualified Name:     android.support.v7.app.ActionBarDrawerToggle.Delegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */