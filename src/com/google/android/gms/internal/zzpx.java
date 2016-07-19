package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzpx<L>
  implements zzqs.zzb<L>
{
  private final DataHolder tk;
  
  protected zzpx(DataHolder paramDataHolder)
  {
    tk = paramDataHolder;
  }
  
  protected abstract void zza(L paramL, DataHolder paramDataHolder);
  
  public void zzapg()
  {
    if (tk != null) {
      tk.close();
    }
  }
  
  public final void zzu(L paramL)
  {
    zza(paramL, tk);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzpx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */