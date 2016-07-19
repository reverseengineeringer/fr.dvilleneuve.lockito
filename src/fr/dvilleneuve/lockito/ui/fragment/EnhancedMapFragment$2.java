package fr.dvilleneuve.lockito.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;

class EnhancedMapFragment$2
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  EnhancedMapFragment$2(EnhancedMapFragment paramEnhancedMapFragment, View paramView, CameraUpdate paramCameraUpdate) {}
  
  @SuppressLint({"NewApi"})
  public void onGlobalLayout()
  {
    if (Build.VERSION.SDK_INT < 16) {
      val$mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
    for (;;)
    {
      EnhancedMapFragment.access$000(this$0).moveCamera(val$cameraUpdate);
      return;
      val$mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */