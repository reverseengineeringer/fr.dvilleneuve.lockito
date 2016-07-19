package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

@SuppressLint({"NewApi"})
public final class zzb
  extends zzc.zza
{
  private Fragment Ma;
  
  private zzb(Fragment paramFragment)
  {
    Ma = paramFragment;
  }
  
  public static zzb zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzb(paramFragment);
    }
    return null;
  }
  
  public Bundle getArguments()
  {
    return Ma.getArguments();
  }
  
  public int getId()
  {
    return Ma.getId();
  }
  
  public boolean getRetainInstance()
  {
    return Ma.getRetainInstance();
  }
  
  public String getTag()
  {
    return Ma.getTag();
  }
  
  public int getTargetRequestCode()
  {
    return Ma.getTargetRequestCode();
  }
  
  public boolean getUserVisibleHint()
  {
    return Ma.getUserVisibleHint();
  }
  
  public zzd getView()
  {
    return zze.zzae(Ma.getView());
  }
  
  public boolean isAdded()
  {
    return Ma.isAdded();
  }
  
  public boolean isDetached()
  {
    return Ma.isDetached();
  }
  
  public boolean isHidden()
  {
    return Ma.isHidden();
  }
  
  public boolean isInLayout()
  {
    return Ma.isInLayout();
  }
  
  public boolean isRemoving()
  {
    return Ma.isRemoving();
  }
  
  public boolean isResumed()
  {
    return Ma.isResumed();
  }
  
  public boolean isVisible()
  {
    return Ma.isVisible();
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    Ma.setHasOptionsMenu(paramBoolean);
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    Ma.setMenuVisibility(paramBoolean);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    Ma.setRetainInstance(paramBoolean);
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    Ma.setUserVisibleHint(paramBoolean);
  }
  
  public void startActivity(Intent paramIntent)
  {
    Ma.startActivity(paramIntent);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    Ma.startActivityForResult(paramIntent, paramInt);
  }
  
  public void zzab(zzd paramzzd)
  {
    paramzzd = (View)zze.zzad(paramzzd);
    Ma.registerForContextMenu(paramzzd);
  }
  
  public void zzac(zzd paramzzd)
  {
    paramzzd = (View)zze.zzad(paramzzd);
    Ma.unregisterForContextMenu(paramzzd);
  }
  
  public zzd zzbcs()
  {
    return zze.zzae(Ma.getActivity());
  }
  
  public zzc zzbct()
  {
    return zza(Ma.getParentFragment());
  }
  
  public zzd zzbcu()
  {
    return zze.zzae(Ma.getResources());
  }
  
  public zzc zzbcv()
  {
    return zza(Ma.getTargetFragment());
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.dynamic.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */