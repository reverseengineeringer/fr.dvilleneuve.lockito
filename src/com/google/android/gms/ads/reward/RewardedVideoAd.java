package com.google.android.gms.ads.reward;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;

public abstract interface RewardedVideoAd
{
  @Deprecated
  public abstract void destroy();
  
  public abstract void destroy(Context paramContext);
  
  public abstract RewardedVideoAdListener getRewardedVideoAdListener();
  
  @Deprecated
  public abstract String getUserId();
  
  public abstract boolean isLoaded();
  
  public abstract void loadAd(String paramString, AdRequest paramAdRequest);
  
  @Deprecated
  public abstract void pause();
  
  public abstract void pause(Context paramContext);
  
  @Deprecated
  public abstract void resume();
  
  public abstract void resume(Context paramContext);
  
  public abstract void setRewardedVideoAdListener(RewardedVideoAdListener paramRewardedVideoAdListener);
  
  @Deprecated
  public abstract void setUserId(String paramString);
  
  public abstract void show();
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.reward.RewardedVideoAd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */