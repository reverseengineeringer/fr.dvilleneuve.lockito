package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;

public final class zzrf
  extends Drawable
  implements Drawable.Callback
{
  private int mFrom;
  private int wE = 0;
  private int wF;
  private int wG = 255;
  private int wH;
  private int wI = 0;
  private boolean wJ;
  private zzb wK;
  private Drawable wL;
  private Drawable wM;
  private boolean wN;
  private boolean wO;
  private boolean wP;
  private int wQ;
  private boolean wy = true;
  private long zzczk;
  
  public zzrf(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    Object localObject = paramDrawable1;
    if (paramDrawable1 == null) {
      localObject = zza.zzarn();
    }
    wL = ((Drawable)localObject);
    ((Drawable)localObject).setCallback(this);
    paramDrawable1 = wK;
    wU |= ((Drawable)localObject).getChangingConfigurations();
    paramDrawable1 = paramDrawable2;
    if (paramDrawable2 == null) {
      paramDrawable1 = zza.zzarn();
    }
    wM = paramDrawable1;
    paramDrawable1.setCallback(this);
    paramDrawable2 = wK;
    wU |= paramDrawable1.getChangingConfigurations();
  }
  
  zzrf(zzb paramzzb)
  {
    wK = new zzb(paramzzb);
  }
  
  public boolean canConstantState()
  {
    if (!wN) {
      if ((wL.getConstantState() == null) || (wM.getConstantState() == null)) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      wO = bool;
      wN = true;
      return wO;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int j = 1;
    int i = 1;
    int k = 0;
    switch (wE)
    {
    }
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    do
    {
      for (;;)
      {
        j = wI;
        bool = wy;
        localDrawable1 = wL;
        localDrawable2 = wM;
        if (i == 0) {
          break;
        }
        if ((!bool) || (j == 0)) {
          localDrawable1.draw(paramCanvas);
        }
        if (j == wG)
        {
          localDrawable2.setAlpha(wG);
          localDrawable2.draw(paramCanvas);
        }
        return;
        zzczk = SystemClock.uptimeMillis();
        wE = 2;
        i = k;
      }
    } while (zzczk < 0L);
    float f = (float)(SystemClock.uptimeMillis() - zzczk) / wH;
    if (f >= 1.0F) {}
    for (i = j;; i = 0)
    {
      if (i != 0) {
        wE = 0;
      }
      wI = ((int)(Math.min(f, 1.0F) * (wF + 0) + 0.0F));
      break;
    }
    if (bool) {
      localDrawable1.setAlpha(wG - j);
    }
    localDrawable1.draw(paramCanvas);
    if (bool) {
      localDrawable1.setAlpha(wG);
    }
    if (j > 0)
    {
      localDrawable2.setAlpha(j);
      localDrawable2.draw(paramCanvas);
      localDrawable2.setAlpha(wG);
    }
    invalidateSelf();
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | wK.wT | wK.wU;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState())
    {
      wK.wT = getChangingConfigurations();
      return wK;
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    return Math.max(wL.getIntrinsicHeight(), wM.getIntrinsicHeight());
  }
  
  public int getIntrinsicWidth()
  {
    return Math.max(wL.getIntrinsicWidth(), wM.getIntrinsicWidth());
  }
  
  public int getOpacity()
  {
    if (!wP)
    {
      wQ = Drawable.resolveOpacity(wL.getOpacity(), wM.getOpacity());
      wP = true;
    }
    return wQ;
  }
  
  @TargetApi(11)
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (zzs.zzavj())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.invalidateDrawable(this);
      }
    }
  }
  
  public Drawable mutate()
  {
    if ((!wJ) && (super.mutate() == this))
    {
      if (!canConstantState()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      wL.mutate();
      wM.mutate();
      wJ = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    wL.setBounds(paramRect);
    wM.setBounds(paramRect);
  }
  
  @TargetApi(11)
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (zzs.zzavj())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
      }
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (wI == wG) {
      wI = paramInt;
    }
    wG = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    wL.setColorFilter(paramColorFilter);
    wM.setColorFilter(paramColorFilter);
  }
  
  public void startTransition(int paramInt)
  {
    mFrom = 0;
    wF = wG;
    wI = 0;
    wH = paramInt;
    wE = 1;
    invalidateSelf();
  }
  
  @TargetApi(11)
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (zzs.zzavj())
    {
      paramDrawable = getCallback();
      if (paramDrawable != null) {
        paramDrawable.unscheduleDrawable(this, paramRunnable);
      }
    }
  }
  
  public Drawable zzarm()
  {
    return wM;
  }
  
  private static final class zza
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
  
  static final class zzb
    extends Drawable.ConstantState
  {
    int wT;
    int wU;
    
    zzb(zzb paramzzb)
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
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzrf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */