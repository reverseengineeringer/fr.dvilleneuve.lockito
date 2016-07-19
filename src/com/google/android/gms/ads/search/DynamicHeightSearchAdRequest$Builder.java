package com.google.android.gms.ads.search;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class DynamicHeightSearchAdRequest$Builder
{
  private final SearchAdRequest.Builder zzcrc = new SearchAdRequest.Builder();
  private final Bundle zzcrd = new Bundle();
  
  public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
  {
    zzcrc.addCustomEventExtrasBundle(paramClass, paramBundle);
    return this;
  }
  
  public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
  {
    zzcrc.addNetworkExtras(paramNetworkExtras);
    return this;
  }
  
  public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
  {
    zzcrc.addNetworkExtrasBundle(paramClass, paramBundle);
    return this;
  }
  
  public DynamicHeightSearchAdRequest build()
  {
    zzcrc.addNetworkExtrasBundle(AdMobAdapter.class, zzcrd);
    return new DynamicHeightSearchAdRequest(this, null);
  }
  
  public Builder setAdBorderSelectors(String paramString)
  {
    zzcrd.putString("csa_adBorderSelectors", paramString);
    return this;
  }
  
  public Builder setAdTest(boolean paramBoolean)
  {
    Bundle localBundle = zzcrd;
    if (paramBoolean) {}
    for (String str = "on";; str = "off")
    {
      localBundle.putString("csa_adtest", str);
      return this;
    }
  }
  
  public Builder setAdjustableLineHeight(int paramInt)
  {
    zzcrd.putString("csa_adjustableLineHeight", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setAdvancedOptionValue(String paramString1, String paramString2)
  {
    zzcrd.putString(paramString1, paramString2);
    return this;
  }
  
  public Builder setAttributionSpacingBelow(int paramInt)
  {
    zzcrd.putString("csa_attributionSpacingBelow", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setBorderSelections(String paramString)
  {
    zzcrd.putString("csa_borderSelections", paramString);
    return this;
  }
  
  public Builder setChannel(String paramString)
  {
    zzcrd.putString("csa_channel", paramString);
    return this;
  }
  
  public Builder setColorAdBorder(String paramString)
  {
    zzcrd.putString("csa_colorAdBorder", paramString);
    return this;
  }
  
  public Builder setColorAdSeparator(String paramString)
  {
    zzcrd.putString("csa_colorAdSeparator", paramString);
    return this;
  }
  
  public Builder setColorAnnotation(String paramString)
  {
    zzcrd.putString("csa_colorAnnotation", paramString);
    return this;
  }
  
  public Builder setColorAttribution(String paramString)
  {
    zzcrd.putString("csa_colorAttribution", paramString);
    return this;
  }
  
  public Builder setColorBackground(String paramString)
  {
    zzcrd.putString("csa_colorBackground", paramString);
    return this;
  }
  
  public Builder setColorBorder(String paramString)
  {
    zzcrd.putString("csa_colorBorder", paramString);
    return this;
  }
  
  public Builder setColorDomainLink(String paramString)
  {
    zzcrd.putString("csa_colorDomainLink", paramString);
    return this;
  }
  
  public Builder setColorText(String paramString)
  {
    zzcrd.putString("csa_colorText", paramString);
    return this;
  }
  
  public Builder setColorTitleLink(String paramString)
  {
    zzcrd.putString("csa_colorTitleLink", paramString);
    return this;
  }
  
  public Builder setCssWidth(int paramInt)
  {
    zzcrd.putString("csa_width", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setDetailedAttribution(boolean paramBoolean)
  {
    zzcrd.putString("csa_detailedAttribution", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setFontFamily(int paramInt)
  {
    zzcrd.putString("csa_fontFamily", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setFontFamilyAttribution(String paramString)
  {
    zzcrd.putString("csa_fontFamilyAttribution", paramString);
    return this;
  }
  
  public Builder setFontSizeAnnotation(int paramInt)
  {
    zzcrd.putString("csa_fontSizeAnnotation", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setFontSizeAttribution(int paramInt)
  {
    zzcrd.putString("csa_fontSizeAttribution", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setFontSizeDescription(int paramInt)
  {
    zzcrd.putString("csa_fontSizeDescription", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setFontSizeDomainLink(int paramInt)
  {
    zzcrd.putString("csa_fontSizeDomainLink", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setFontSizeTitle(int paramInt)
  {
    zzcrd.putString("csa_fontSizeTitle", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setHostLanguage(String paramString)
  {
    zzcrd.putString("csa_hl", paramString);
    return this;
  }
  
  public Builder setIsClickToCallEnabled(boolean paramBoolean)
  {
    zzcrd.putString("csa_clickToCall", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setIsLocationEnabled(boolean paramBoolean)
  {
    zzcrd.putString("csa_location", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setIsPlusOnesEnabled(boolean paramBoolean)
  {
    zzcrd.putString("csa_plusOnes", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setIsSellerRatingsEnabled(boolean paramBoolean)
  {
    zzcrd.putString("csa_sellerRatings", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setIsSiteLinksEnabled(boolean paramBoolean)
  {
    zzcrd.putString("csa_siteLinks", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setIsTitleBold(boolean paramBoolean)
  {
    zzcrd.putString("csa_titleBold", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setIsTitleUnderlined(boolean paramBoolean)
  {
    Bundle localBundle = zzcrd;
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      localBundle.putString("csa_noTitleUnderline", Boolean.toString(paramBoolean));
      return this;
    }
  }
  
  public Builder setLocationColor(String paramString)
  {
    zzcrd.putString("csa_colorLocation", paramString);
    return this;
  }
  
  public Builder setLocationFontSize(int paramInt)
  {
    zzcrd.putString("csa_fontSizeLocation", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setLongerHeadlines(boolean paramBoolean)
  {
    zzcrd.putString("csa_longerHeadlines", Boolean.toString(paramBoolean));
    return this;
  }
  
  public Builder setNumber(int paramInt)
  {
    zzcrd.putString("csa_number", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setPage(int paramInt)
  {
    zzcrd.putString("csa_adPage", Integer.toString(paramInt));
    return this;
  }
  
  public Builder setQuery(String paramString)
  {
    zzcrc.setQuery(paramString);
    return this;
  }
  
  public Builder setVerticalSpacing(int paramInt)
  {
    zzcrd.putString("csa_verticalSpacing", Integer.toString(paramInt));
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.search.DynamicHeightSearchAdRequest.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */