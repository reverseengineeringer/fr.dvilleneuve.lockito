package android.support.v4.widget;

import android.graphics.Rect;
import java.util.Comparator;

class FocusStrategy$SequentialComparator<T>
  implements Comparator<T>
{
  private final FocusStrategy.BoundsAdapter<T> mAdapter;
  private final boolean mIsLayoutRtl;
  private final Rect mTemp1 = new Rect();
  private final Rect mTemp2 = new Rect();
  
  public FocusStrategy$SequentialComparator(boolean paramBoolean, FocusStrategy.BoundsAdapter<T> paramBoundsAdapter)
  {
    mIsLayoutRtl = paramBoolean;
    mAdapter = paramBoundsAdapter;
  }
  
  public int compare(T paramT1, T paramT2)
  {
    int j = 1;
    int i = 1;
    Rect localRect1 = mTemp1;
    Rect localRect2 = mTemp2;
    mAdapter.obtainBounds(paramT1, localRect1);
    mAdapter.obtainBounds(paramT2, localRect2);
    if (top < top) {}
    do
    {
      do
      {
        do
        {
          return -1;
          if (top > top) {
            return 1;
          }
          if (left < left)
          {
            if (mIsLayoutRtl) {}
            for (;;)
            {
              return i;
              i = -1;
            }
          }
          if (left <= left) {
            break;
          }
        } while (mIsLayoutRtl);
        return 1;
      } while (bottom < bottom);
      if (bottom > bottom) {
        return 1;
      }
      if (right < right)
      {
        if (mIsLayoutRtl) {}
        for (i = j;; i = -1) {
          return i;
        }
      }
      if (right <= right) {
        break;
      }
    } while (mIsLayoutRtl);
    return 1;
    return 0;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.FocusStrategy.SequentialComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */