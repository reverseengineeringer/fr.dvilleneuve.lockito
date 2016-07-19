package com.google.android.gms.ads;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.gms.ads.internal.client.zzae;
import com.google.android.gms.internal.zzir;

@zzir
public final class NativeExpressAdView
  extends BaseAdView
{
  public NativeExpressAdView(Context paramContext)
  {
    super(paramContext, 2);
  }
  
  public NativeExpressAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, 2);
  }
  
  public NativeExpressAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt, 2);
  }
  
  public VideoController getVideoController()
  {
    return zzaih.getVideoController();
  }
  
  public VideoOptions getVideoOptions()
  {
    return zzaih.getVideoOptions();
  }
  
  public void setVideoOptions(VideoOptions paramVideoOptions)
  {
    zzaih.setVideoOptions(paramVideoOptions);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.NativeExpressAdView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */