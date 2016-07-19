package fr.dvilleneuve.lockito;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.manager.NotificationManager;
import fr.dvilleneuve.lockito.core.manager.SimulationManager;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import net.danlew.android.joda.ResourceZoneInfoProvider;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;

@EApplication
public class LockitoApplication
  extends Application
{
  @Bean
  NotificationManager notificationManager;
  @Bean
  SimulationManager simulationManager;
  
  @Background
  void loadUserIdentifier()
  {
    try
    {
      Crashlytics.setUserIdentifier(AdvertisingIdClient.getAdvertisingIdInfo(this).getId());
      return;
    }
    catch (Exception localException)
    {
      Logger.error("Couldn't retrieve unique id", localException, new Object[0]);
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    Fabric.with(this, new Kit[] { new Crashlytics() });
    ResourceZoneInfoProvider.init(this);
    MobileAds.initialize(this, getString(2131165390));
    Iconify.with(new FontAwesomeModule());
    loadUserIdentifier();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.LockitoApplication
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */