package fr.dvilleneuve.lockito.core.helper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import fr.dvilleneuve.lockito.core.logger.Logger;

class AdHelper$AdViewLoggerListener
  extends AdListener
{
  private final AdView adView;
  
  public AdHelper$AdViewLoggerListener(AdHelper paramAdHelper, AdView paramAdView)
  {
    adView = paramAdView;
  }
  
  public void onAdClosed()
  {
    Logger.debug("ad %s closed", new Object[] { adView.getAdUnitId() });
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    Logger.debug("ad %s failed to load: %d", new Object[] { adView.getAdUnitId(), Integer.valueOf(paramInt) });
    adView.setVisibility(8);
  }
  
  public void onAdLeftApplication()
  {
    Logger.debug("ad %s left application", new Object[] { adView.getAdUnitId() });
  }
  
  public void onAdLoaded()
  {
    Logger.debug("ad %s loaded", new Object[] { adView.getAdUnitId() });
  }
  
  public void onAdOpened()
  {
    Logger.debug("ad %s opened", new Object[] { adView.getAdUnitId() });
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.AdHelper.AdViewLoggerListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */