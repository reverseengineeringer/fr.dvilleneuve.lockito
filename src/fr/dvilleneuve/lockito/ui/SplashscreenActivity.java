package fr.dvilleneuve.lockito.ui;

import android.app.Activity;
import android.widget.ProgressBar;
import fr.dvilleneuve.lockito.core.logger.Logger;
import fr.dvilleneuve.lockito.core.manager.ItineraryManager;
import fr.dvilleneuve.lockito.core.model.ItineraryMode;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.preferences.MockPreferences_;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.api.sharedpreferences.FloatPrefField;
import org.androidannotations.api.sharedpreferences.IntPrefField;
import org.androidannotations.api.sharedpreferences.StringPrefField;

@EActivity(2130903072)
@NoTitle
public class SplashscreenActivity
  extends Activity
{
  @Bean
  ItineraryManager itineraryManager;
  @ViewById
  ProgressBar loadingProgress;
  @Pref
  MockPreferences_ preferences;
  
  private void loadItineraryFromPreferences()
  {
    ItineraryInfo localItineraryInfo;
    ArrayList localArrayList;
    Object localObject;
    if (preferences.speed().exists())
    {
      Logger.info("Migration of old itineraryInfo from preferences to databse", new Object[0]);
      localItineraryInfo = new ItineraryInfo();
      localItineraryInfo.setName(getString(2131165300));
      localItineraryInfo.setSpeed(preferences.speed().getOr(40));
      localItineraryInfo.setAccuracyBase(preferences.accuracyBase().getOr(15));
      localItineraryInfo.setAccuracyDelta(preferences.accuracyDelta().getOr(8));
      localArrayList = new ArrayList();
      float f1;
      float f2;
      if (preferences.originX().exists())
      {
        f1 = preferences.originX().get();
        f2 = preferences.originY().get();
        localArrayList.add(new Point(f1, f2));
      }
      if (preferences.waypoints().exists())
      {
        String str = preferences.waypoints().get();
        localObject = str.split("/");
        Logger.info("locations = %s", new Object[] { str });
        int j = localObject.length;
        int i = 0;
        for (;;)
        {
          if (i < j)
          {
            str = localObject[i];
            String[] arrayOfString = str.split(";");
            try
            {
              localArrayList.add(new Point(Float.parseFloat(arrayOfString[0]), Float.parseFloat(arrayOfString[1])));
              i += 1;
            }
            catch (NumberFormatException localNumberFormatException)
            {
              for (;;)
              {
                Logger.error("Can't deserialize this location : %s", new Object[] { str, localNumberFormatException });
              }
            }
          }
        }
      }
      if (preferences.destinationX().exists())
      {
        f1 = preferences.destinationX().get();
        f2 = preferences.destinationY().get();
        localArrayList.add(new Point(f1, f2));
      }
      localObject = new Itinerary();
      ((Itinerary)localObject).setWaypoints(localArrayList);
      if (localArrayList.size() > 0) {
        updateLoadingState(true);
      }
    }
    try
    {
      itineraryManager.retrieveItinerary(localItineraryInfo, localArrayList, ItineraryMode.GOOGLE_CAR);
      updateLoadingState(false);
      localItineraryInfo.setItinerary((Itinerary)localObject);
      itineraryManager.save(localItineraryInfo);
      preferences.clear();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Logger.error("Can't retrieve itinerary points", localException, new Object[0]);
      }
    }
  }
  
  @AfterViews
  void init()
  {
    upgradeData();
  }
  
  @UiThread
  void updateLoadingState(boolean paramBoolean)
  {
    ProgressBar localProgressBar = loadingProgress;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      localProgressBar.setVisibility(i);
      return;
    }
  }
  
  @Background
  void upgradeData()
  {
    loadItineraryFromPreferences();
    ItinerariesActivity_.intent(this).start();
    finish();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SplashscreenActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */