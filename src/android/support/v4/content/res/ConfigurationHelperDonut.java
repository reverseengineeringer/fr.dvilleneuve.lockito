package android.support.v4.content.res;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

class ConfigurationHelperDonut
{
  static int getDensityDpi(@NonNull Resources paramResources)
  {
    return getDisplayMetricsdensityDpi;
  }
  
  static int getScreenHeightDp(@NonNull Resources paramResources)
  {
    paramResources = paramResources.getDisplayMetrics();
    return (int)(heightPixels / density);
  }
  
  static int getScreenWidthDp(@NonNull Resources paramResources)
  {
    paramResources = paramResources.getDisplayMetrics();
    return (int)(widthPixels / density);
  }
  
  static int getSmallestScreenWidthDp(@NonNull Resources paramResources)
  {
    return Math.min(getScreenWidthDp(paramResources), getScreenHeightDp(paramResources));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.res.ConfigurationHelperDonut
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */