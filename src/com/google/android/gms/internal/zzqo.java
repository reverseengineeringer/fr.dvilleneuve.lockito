package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzqo
{
  protected final zzqp va;
  
  protected zzqo(zzqp paramzzqp)
  {
    va = paramzzqp;
  }
  
  protected static zzqp zzc(zzqn paramzzqn)
  {
    if (paramzzqn.zzaqm()) {
      return zzra.zza(paramzzqn.zzaqo());
    }
    return zzqq.zzt(paramzzqn.zzaqn());
  }
  
  protected static zzqp zzs(Activity paramActivity)
  {
    return zzc(new zzqn(paramActivity));
  }
  
  @MainThread
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public Activity getActivity()
  {
    return va.zzaqp();
  }
  
  @MainThread
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  @MainThread
  public void onCreate(Bundle paramBundle) {}
  
  @MainThread
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  @MainThread
  public void onStart() {}
  
  @MainThread
  public void onStop() {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */