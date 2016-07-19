package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzpy
  implements Releasable, Result
{
  protected final Status cc;
  protected final DataHolder tk;
  
  protected zzpy(DataHolder paramDataHolder, Status paramStatus)
  {
    cc = paramStatus;
    tk = paramDataHolder;
  }
  
  public Status getStatus()
  {
    return cc;
  }
  
  public void release()
  {
    if (tk != null) {
      tk.close();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzpy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */