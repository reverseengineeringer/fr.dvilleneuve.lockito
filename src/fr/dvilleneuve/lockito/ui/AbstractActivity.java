package fr.dvilleneuve.lockito.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import fr.dvilleneuve.lockito.core.helper.GAnalyticsHelper;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity
public abstract class AbstractActivity
  extends ActionBarActivity
{
  @Bean
  GAnalyticsHelper analyticsHelper;
  private boolean isDestroyed = false;
  
  public boolean isDestroyed()
  {
    return isDestroyed;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    supportRequestWindowFeature(5);
    setSupportProgressBarIndeterminateVisibility(false);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    isDestroyed = true;
  }
  
  @UiThread
  void showToast(int paramInt1, int paramInt2, Object... paramVarArgs)
  {
    showToast(paramInt1, getString(paramInt2, paramVarArgs));
  }
  
  @UiThread
  void showToast(int paramInt, String paramString)
  {
    Toast.makeText(this, paramString, paramInt).show();
  }
  
  @UiThread
  void updateLoadingState(boolean paramBoolean)
  {
    setSupportProgressBarIndeterminateVisibility(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.AbstractActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */