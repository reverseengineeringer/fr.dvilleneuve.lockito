package fr.dvilleneuve.lockito.ui;

import android.support.v7.app.ActionBar;
import android.webkit.WebView;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(2130903066)
public class ChangelogActivity
  extends AbstractActivity
{
  @Bean
  PreferenceManager preferenceManager;
  @ViewById
  WebView webview;
  
  @AfterViews
  void init()
  {
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setDisplayHomeAsUpEnabled(true);
      localActionBar.setHomeButtonEnabled(true);
    }
    updateLoadingState(true);
    webview.loadUrl("file:///android_asset/changelog.html");
    updateLoadingState(false);
    preferenceManager.setChangeLogDisplayed();
  }
  
  @OptionsItem({16908332})
  void menuHome()
  {
    finish();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.ChangelogActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */