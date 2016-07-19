package fr.dvilleneuve.lockito.ui.fragment;

import android.os.Bundle;
import org.androidannotations.api.builder.FragmentBuilder;

public class LicenseFragment_$FragmentBuilder_
  extends FragmentBuilder<FragmentBuilder_, LicenseFragment>
{
  public LicenseFragment build()
  {
    LicenseFragment_ localLicenseFragment_ = new LicenseFragment_();
    localLicenseFragment_.setArguments(args);
    return localLicenseFragment_;
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
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.LicenseFragment_.FragmentBuilder_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */