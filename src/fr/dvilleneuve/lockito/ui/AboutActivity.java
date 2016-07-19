package fr.dvilleneuve.lockito.ui;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import org.androidannotations.annotations.EActivity;

@EActivity
public class AboutActivity
  extends PreferenceActivity
{
  private static final int ICON_SIZE = 32;
  
  @TargetApi(11)
  private void setIcon(String paramString, FontAwesomeIcons paramFontAwesomeIcons, int paramInt)
  {
    findPreference(paramString).setIcon(new IconDrawable(this, paramFontAwesomeIcons).sizeDp(32).colorRes(paramInt));
  }
  
  private void setIcons()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      setIcon("pref_sendFeedback", FontAwesomeIcons.fa_comments_o, 2131558404);
      setIcon("pref_followOnTwitter", FontAwesomeIcons.fa_twitter, 2131558406);
      setIcon("pref_rateApplication", FontAwesomeIcons.fa_star, 2131558405);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131034112);
    setIcons();
    paramBundle = getString(2131165338, new Object[] { "2.7.0" });
    findPreference("pref_version").setSummary(paramBundle);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.AboutActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */