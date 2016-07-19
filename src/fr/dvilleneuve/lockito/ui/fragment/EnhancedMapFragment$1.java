package fr.dvilleneuve.lockito.ui.fragment;

import android.graphics.Bitmap;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import fr.dvilleneuve.lockito.core.utils.BitmapUtils;
import java.io.File;

class EnhancedMapFragment$1
  implements GoogleMap.SnapshotReadyCallback
{
  EnhancedMapFragment$1(EnhancedMapFragment paramEnhancedMapFragment, File paramFile) {}
  
  public void onSnapshotReady(Bitmap paramBitmap)
  {
    BitmapUtils.writeThumbnails(val$file, paramBitmap);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.EnhancedMapFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */