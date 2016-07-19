package fr.dvilleneuve.lockito.ui.adapter;

import android.support.v4.app.Fragment;
import fr.dvilleneuve.lockito.ui.fragment.LicenseFragment_;
import fr.dvilleneuve.lockito.ui.fragment.LicenseFragment_.FragmentBuilder_;

class LicenseAdapter$Page
{
  private final Fragment fragment;
  private final String title;
  
  public LicenseAdapter$Page(LicenseAdapter paramLicenseAdapter, String paramString, Fragment paramFragment)
  {
    title = paramString;
    fragment = paramFragment;
  }
  
  public LicenseAdapter$Page(LicenseAdapter paramLicenseAdapter, String paramString1, String paramString2, String paramString3)
  {
    this(paramLicenseAdapter, paramString1, LicenseFragment_.builder().contentString(paramString3).urlString(paramString2).build());
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.LicenseAdapter.Page
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */