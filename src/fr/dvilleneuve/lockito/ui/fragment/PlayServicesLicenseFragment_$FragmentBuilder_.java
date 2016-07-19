package fr.dvilleneuve.lockito.ui.fragment;

import android.os.Bundle;
import org.androidannotations.api.builder.FragmentBuilder;

public class PlayServicesLicenseFragment_$FragmentBuilder_
  extends FragmentBuilder<FragmentBuilder_, PlayServicesLicenseFragment>
{
  public PlayServicesLicenseFragment build()
  {
    PlayServicesLicenseFragment_ localPlayServicesLicenseFragment_ = new PlayServicesLicenseFragment_();
    localPlayServicesLicenseFragment_.setArguments(args);
    return localPlayServicesLicenseFragment_;
  }
  
  public FragmentBuilder_ contentString(String paramString)
  {
    args.putString("contentString", paramString);
    return this;
  }
  
  public FragmentBuilder_ urlString(String paramString)
  {
    args.putString("urlString", paramString);
    return this;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.PlayServicesLicenseFragment_.FragmentBuilder_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */