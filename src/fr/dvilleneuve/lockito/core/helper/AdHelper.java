package fr.dvilleneuve.lockito.core.helper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import fr.dvilleneuve.lockito.core.logger.Logger;
import org.androidannotations.annotations.EBean;

@EBean
public class AdHelper
{
  public void loadView(AdView paramAdView)
  {
    paramAdView.setAdListener(new AdViewLoggerListener(paramAdView));
    paramAdView.loadAd(new AdRequest.Builder().addTestDevice("4ACAC83A09116AC973C9B07BD6690B40").build());
  }
  
  private class AdViewLoggerListener
    extends AdListener
  {
    private final AdView adView;
    
    public AdViewLoggerListener(AdView paramAdView)
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
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.AdHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */