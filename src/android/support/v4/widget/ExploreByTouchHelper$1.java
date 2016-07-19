package android.support.v4.widget;

import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

final class ExploreByTouchHelper$1
  implements FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>
{
  public void obtainBounds(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat, Rect paramRect)
  {
    paramAccessibilityNodeInfoCompat.getBoundsInParent(paramRect);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.ExploreByTouchHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */