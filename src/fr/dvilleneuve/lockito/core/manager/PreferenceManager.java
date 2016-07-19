package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import fr.dvilleneuve.lockito.core.ItinerariesOrder;
import fr.dvilleneuve.lockito.core.UnitSystem;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class PreferenceManager
{
  private static final String PREFKEY_CHANGELOG_LAST_DISPLAY_FOR_VERSION = "PREFKEY_CHANGELOG_LAST_DISPLAY_FOR_VERSION";
  @RootContext
  Context context;
  private SharedPreferences sharedPreferences;
  
  private boolean getBooleanPref(int paramInt1, int paramInt2)
  {
    return sharedPreferences.getBoolean(getPrefKey(paramInt1), context.getResources().getBoolean(paramInt2));
  }
  
  private int getIntegerPref(int paramInt1, int paramInt2)
  {
    return sharedPreferences.getInt(getPrefKey(paramInt1), context.getResources().getInteger(paramInt2));
  }
  
  private String getPrefKey(int paramInt)
  {
    return context.getString(paramInt);
  }
  
  private String getStringPref(int paramInt1, int paramInt2)
  {
    return sharedPreferences.getString(getPrefKey(paramInt1), context.getString(paramInt2));
  }
  
  public int getDelayBetweenLocations()
  {
    return getIntegerPref(2131165427, 2131689476);
  }
  
  public ItinerariesOrder getItinerariesOrder()
  {
    Object localObject = getStringPref(2131165431, 2131165430);
    try
    {
      localObject = ItinerariesOrder.valueOf((String)localObject);
      return (ItinerariesOrder)localObject;
    }
    catch (Exception localException) {}
    return ItinerariesOrder.LAST_CREATED_FIRST;
  }
  
  public UnitSystem getUnitSystem()
  {
    Object localObject = getStringPref(2131165429, 2131165428);
    try
    {
      localObject = UnitSystem.valueOf((String)localObject);
      return (UnitSystem)localObject;
    }
    catch (Exception localException) {}
    return UnitSystem.METRIC;
  }
  
  @AfterInject
  void init()
  {
    sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
  }
  
  public void setChangeLogDisplayed()
  {
    sharedPreferences.edit().putInt("PREFKEY_CHANGELOG_LAST_DISPLAY_FOR_VERSION", 2070000).apply();
  }
  
  public boolean shouldDisplayChangeLog()
  {
    boolean bool = false;
    if (sharedPreferences.getInt("PREFKEY_CHANGELOG_LAST_DISPLAY_FOR_VERSION", 0) < 2070000) {
      bool = true;
    }
    return bool;
  }
  
  public boolean shouldNotifyIfUnsaved()
  {
    return getBooleanPref(2131165434, 2131492871);
  }
  
  public boolean shouldShowThumbnails()
  {
    return getBooleanPref(2131165432, 2131492869);
  }
  
  public boolean shouldStopWhenFinished()
  {
    return getBooleanPref(2131165435, 2131492872);
  }
  
  public boolean shouldUseWakelock()
  {
    return getBooleanPref(2131165433, 2131492870);
  }
  
  public boolean shouldVibrateOnMarkerUpdate()
  {
    return getBooleanPref(2131165436, 2131492873);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.PreferenceManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */