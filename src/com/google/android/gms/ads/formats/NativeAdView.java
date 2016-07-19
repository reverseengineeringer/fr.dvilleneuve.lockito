package com.google.android.gms.ads.formats;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdw;

public abstract class NativeAdView
  extends FrameLayout
{
  private final FrameLayout zzaiz = zzf(paramContext);
  private final zzdw zzaja = zzdi();
  
  public NativeAdView(Context paramContext)
  {
    super(paramContext);
  }
  
  public NativeAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public NativeAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public NativeAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  private zzdw zzdi()
  {
    zzab.zzb(zzaiz, "createDelegate must be called after mOverlayFrame has been created");
    return zzm.zzix().zza(zzaiz.getContext(), this, zzaiz);
  }
  
  private FrameLayout zzf(Context paramContext)
  {
    paramContext = zzg(paramContext);
    paramContext.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    addView(paramContext);
    return paramContext;
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
    super.bringChildToFront(zzaiz);
  }
  
  public void bringChildToFront(View paramView)
  {
    super.bringChildToFront(paramView);
    if (zzaiz != paramView) {
      super.bringChildToFront(zzaiz);
    }
  }
  
  public void destroy()
  {
    try
    {
      zzaja.destroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Unable to destroy native ad view", localRemoteException);
    }
  }
  
  public void removeAllViews()
  {
    super.removeAllViews();
    super.addView(zzaiz);
  }
  
  public void removeView(View paramView)
  {
    if (zzaiz == paramView) {
      return;
    }
    super.removeView(paramView);
  }
  
  public void setNativeAd(NativeAd paramNativeAd)
  {
    try
    {
      zzaja.zze((zzd)paramNativeAd.zzdh());
      return;
    }
    catch (RemoteException paramNativeAd)
    {
      zzb.zzb("Unable to call setNativeAd on delegate", paramNativeAd);
    }
  }
  
  protected void zza(String paramString, View paramView)
  {
    try
    {
      zzaja.zzc(paramString, zze.zzae(paramView));
      return;
    }
    catch (RemoteException paramString)
    {
      zzb.zzb("Unable to call setAssetView on delegate", paramString);
    }
  }
  
  FrameLayout zzg(Context paramContext)
  {
    return new FrameLayout(paramContext);
  }
  
  protected View zzq(String paramString)
  {
    try
    {
      paramString = zzaja.zzap(paramString);
      if (paramString != null)
      {
        paramString = (View)zze.zzad(paramString);
        return paramString;
      }
    }
    catch (RemoteException paramString)
    {
      zzb.zzb("Unable to call getAssetView on delegate", paramString);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.formats.NativeAdView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */