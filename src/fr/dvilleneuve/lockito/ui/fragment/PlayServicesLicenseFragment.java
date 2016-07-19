package fr.dvilleneuve.lockito.ui.fragment;

import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesUtil;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.UiThread.Propagation;

@EFragment(2130903080)
public class PlayServicesLicenseFragment
  extends LicenseFragment
{
  @Background
  void loadLicenseInfo()
  {
    updateLoadingState(true);
    showLicenseInfo(GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(getActivity()));
    updateLoadingState(false);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    loadLicenseInfo();
  }
  
  @UiThread(propagation=UiThread.Propagation.REUSE)
  void showLicenseInfo(String paramString)
  {
    setContent(paramString);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.PlayServicesLicenseFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */