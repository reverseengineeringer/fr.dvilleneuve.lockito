package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

class ViewUtil$ViewCompat
{
  private static final ViewCompat sInstance = new ViewCompat();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      sInstance = new ViewCompatHoneycomb(null);
      return;
    }
  }
  
  public static ViewCompat getInstance()
  {
    return sInstance;
  }
  
  public float getAlpha(View paramView)
  {
    return 1.0F;
  }
  
  @TargetApi(11)
  private static class ViewCompatHoneycomb
    extends ViewUtil.ViewCompat
  {
    public float getAlpha(View paramView)
    {
      return paramView.getAlpha();
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.ViewUtil.ViewCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */