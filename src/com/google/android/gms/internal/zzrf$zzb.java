package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class zzrf$zzb
  extends Drawable.ConstantState
{
  int wT;
  int wU;
  
  zzrf$zzb(zzb paramzzb)
  {
    if (paramzzb != null)
    {
      wT = wT;
      wU = wU;
    }
  }
  
  public int getChangingConfigurations()
  {
    return wT;
  }
  
  public Drawable newDrawable()
  {
    return new zzrf(this);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrf.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */