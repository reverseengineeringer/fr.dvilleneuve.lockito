package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class zzrf$zza
  extends Drawable
{
  private static final zza wR = new zza();
  private static final zza wS = new zza(null);
  
  public void draw(Canvas paramCanvas) {}
  
  public Drawable.ConstantState getConstantState()
  {
    return wS;
  }
  
  public int getOpacity()
  {
    return -2;
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
  
  private static final class zza
    extends Drawable.ConstantState
  {
    public int getChangingConfigurations()
    {
      return 0;
    }
    
    public Drawable newDrawable()
    {
      return zzrf.zza.zzarn();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrf.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */