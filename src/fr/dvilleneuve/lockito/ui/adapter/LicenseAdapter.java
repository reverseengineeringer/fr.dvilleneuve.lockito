package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.google.android.gms.common.GooglePlayServicesUtil;
import fr.dvilleneuve.lockito.ui.fragment.LicenseFragment_;
import fr.dvilleneuve.lockito.ui.fragment.LicenseFragment_.FragmentBuilder_;
import fr.dvilleneuve.lockito.ui.fragment.PlayServicesLicenseFragment_;
import fr.dvilleneuve.lockito.ui.fragment.PlayServicesLicenseFragment_.FragmentBuilder_;
import java.util.ArrayList;
import java.util.List;

public class LicenseAdapter
  extends FragmentPagerAdapter
{
  private List<Page> licensePages = new ArrayList();
  
  public LicenseAdapter(FragmentManager paramFragmentManager, Context paramContext)
  {
    super(paramFragmentManager);
    licensePages.add(new Page("AndroidAnnotations", paramContext.getString(2131165402), paramContext.getString(2131165401)));
    licensePages.add(new Page("BaseAdapterHelper", paramContext.getString(2131165421), paramContext.getString(2131165420)));
    licensePages.add(new Page("EnhancedEdittext", paramContext.getString(2131165404), paramContext.getString(2131165403)));
    licensePages.add(new Page("EventBus", paramContext.getString(2131165406), paramContext.getString(2131165405)));
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0) {
      licensePages.add(new Page("Google Play Services", PlayServicesLicenseFragment_.builder().urlString(paramContext.getString(2131165407)).build()));
    }
    licensePages.add(new Page("Iconify", paramContext.getString(2131165409), paramContext.getString(2131165408)));
    licensePages.add(new Page("Jackson", paramContext.getString(2131165411), paramContext.getString(2131165410)));
    licensePages.add(new Page("JodaTime", paramContext.getString(2131165413), paramContext.getString(2131165412)));
    licensePages.add(new Page("Numberpicker", paramContext.getString(2131165415), paramContext.getString(2131165414)));
    licensePages.add(new Page("OrmLite", paramContext.getString(2131165417), paramContext.getString(2131165416)));
    licensePages.add(new Page("Spring Android Rest template", paramContext.getString(2131165419), paramContext.getString(2131165418)));
  }
  
  public int getCount()
  {
    return licensePages.size();
  }
  
  public Fragment getItem(int paramInt)
  {
    return licensePages.get(paramInt)).fragment;
  }
  
  public CharSequence getPageTitle(int paramInt)
  {
    return licensePages.get(paramInt)).title;
  }
  
  class Page
  {
    private final Fragment fragment;
    private final String title;
    
    public Page(String paramString, Fragment paramFragment)
    {
      title = paramString;
      fragment = paramFragment;
    }
    
    public Page(String paramString1, String paramString2, String paramString3)
    {
      this(paramString1, LicenseFragment_.builder().contentString(paramString3).urlString(paramString2).build());
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.LicenseAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */