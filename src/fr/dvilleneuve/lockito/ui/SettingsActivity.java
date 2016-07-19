package fr.dvilleneuve.lockito.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import org.androidannotations.annotations.EActivity;

@EActivity
public class SettingsActivity
  extends PreferenceActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131034114);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SettingsActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */