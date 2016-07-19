package fr.dvilleneuve.lockito.ui;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import fr.dvilleneuve.lockito.ui.adapter.LicenseAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(2130903070)
public class LicenseActivity
  extends AbstractActivity
  implements ActionBar.TabListener
{
  @ViewById
  ViewPager viewPager;
  
  @AfterViews
  void afterViews()
  {
    LicenseAdapter localLicenseAdapter = new LicenseAdapter(getSupportFragmentManager(), this);
    viewPager.setAdapter(localLicenseAdapter);
    final ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setDisplayHomeAsUpEnabled(true);
      localActionBar.setHomeButtonEnabled(true);
      localActionBar.setNavigationMode(2);
    }
    viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
    {
      public void onPageSelected(int paramAnonymousInt)
      {
        localActionBar.setSelectedNavigationItem(paramAnonymousInt);
      }
    });
    int i = 0;
    while (i < localLicenseAdapter.getCount())
    {
      localActionBar.addTab(localActionBar.newTab().setText(localLicenseAdapter.getPageTitle(i)).setTabListener(this));
      i += 1;
    }
  }
  
  @OptionsItem({16908332})
  void menuHome()
  {
    finish();
  }
  
  public void onTabReselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction) {}
  
  public void onTabSelected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction)
  {
    viewPager.setCurrentItem(paramTab.getPosition());
  }
  
  public void onTabUnselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction) {}
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.LicenseActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */