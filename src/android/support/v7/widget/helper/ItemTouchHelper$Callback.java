package android.support.v7.widget.helper;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R.dimen;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.List;

public abstract class ItemTouchHelper$Callback
{
  private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
  public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
  public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
  private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
  static final int RELATIVE_DIR_FLAGS = 3158064;
  private static final Interpolator sDragScrollInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat;
    }
  };
  private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      paramAnonymousFloat -= 1.0F;
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
    }
  };
  private static final ItemTouchUIUtil sUICallback = new ItemTouchUIUtilImpl.Gingerbread();
  private int mCachedMaxScrollSpeed = -1;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      sUICallback = new ItemTouchUIUtilImpl.Lollipop();
      return;
    }
    if (Build.VERSION.SDK_INT >= 11)
    {
      sUICallback = new ItemTouchUIUtilImpl.Honeycomb();
      return;
    }
  }
  
  public static int convertToRelativeDirection(int paramInt1, int paramInt2)
  {
    int i = paramInt1 & 0xC0C0C;
    if (i == 0) {
      return paramInt1;
    }
    paramInt1 &= (i ^ 0xFFFFFFFF);
    if (paramInt2 == 0) {
      return paramInt1 | i << 2;
    }
    return paramInt1 | i << 1 & 0xFFF3F3F3 | (i << 1 & 0xC0C0C) << 2;
  }
  
  public static ItemTouchUIUtil getDefaultUIUtil()
  {
    return sUICallback;
  }
  
  private int getMaxDragScroll(RecyclerView paramRecyclerView)
  {
    if (mCachedMaxScrollSpeed == -1) {
      mCachedMaxScrollSpeed = paramRecyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
    }
    return mCachedMaxScrollSpeed;
  }
  
  private boolean hasDragFlag(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
  {
    return (0xFF0000 & getAbsoluteMovementFlags(paramRecyclerView, paramViewHolder)) != 0;
  }
  
  private boolean hasSwipeFlag(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
  {
    return (0xFF00 & getAbsoluteMovementFlags(paramRecyclerView, paramViewHolder)) != 0;
  }
  
  public static int makeFlag(int paramInt1, int paramInt2)
  {
    return paramInt2 << paramInt1 * 8;
  }
  
  public static int makeMovementFlags(int paramInt1, int paramInt2)
  {
    return makeFlag(0, paramInt2 | paramInt1) | makeFlag(1, paramInt2) | makeFlag(2, paramInt1);
  }
  
  private void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, List<ItemTouchHelper.RecoverAnimation> paramList, int paramInt, float paramFloat1, float paramFloat2)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      ItemTouchHelper.RecoverAnimation localRecoverAnimation = (ItemTouchHelper.RecoverAnimation)paramList.get(i);
      localRecoverAnimation.update();
      int k = paramCanvas.save();
      onChildDraw(paramCanvas, paramRecyclerView, mViewHolder, mX, mY, mActionState, false);
      paramCanvas.restoreToCount(k);
      i += 1;
    }
    if (paramViewHolder != null)
    {
      i = paramCanvas.save();
      onChildDraw(paramCanvas, paramRecyclerView, paramViewHolder, paramFloat1, paramFloat2, paramInt, true);
      paramCanvas.restoreToCount(i);
    }
  }
  
  private void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, List<ItemTouchHelper.RecoverAnimation> paramList, int paramInt, float paramFloat1, float paramFloat2)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      ItemTouchHelper.RecoverAnimation localRecoverAnimation = (ItemTouchHelper.RecoverAnimation)paramList.get(i);
      int k = paramCanvas.save();
      onChildDrawOver(paramCanvas, paramRecyclerView, mViewHolder, mX, mY, mActionState, false);
      paramCanvas.restoreToCount(k);
      i += 1;
    }
    if (paramViewHolder != null)
    {
      i = paramCanvas.save();
      onChildDrawOver(paramCanvas, paramRecyclerView, paramViewHolder, paramFloat1, paramFloat2, paramInt, true);
      paramCanvas.restoreToCount(i);
    }
    i = 0;
    paramInt = j - 1;
    if (paramInt >= 0)
    {
      paramCanvas = (ItemTouchHelper.RecoverAnimation)paramList.get(paramInt);
      if ((ItemTouchHelper.RecoverAnimation.access$1900(paramCanvas)) && (!mIsPendingCleanup)) {
        paramList.remove(paramInt);
      }
      for (;;)
      {
        paramInt -= 1;
        break;
        if (!ItemTouchHelper.RecoverAnimation.access$1900(paramCanvas)) {
          i = 1;
        }
      }
    }
    if (i != 0) {
      paramRecyclerView.invalidate();
    }
  }
  
  public boolean canDropOver(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, RecyclerView.ViewHolder paramViewHolder2)
  {
    return true;
  }
  
  public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder paramViewHolder, List<RecyclerView.ViewHolder> paramList, int paramInt1, int paramInt2)
  {
    int n = itemView.getWidth();
    int i1 = itemView.getHeight();
    Object localObject2 = null;
    int j = -1;
    int i2 = paramInt1 - itemView.getLeft();
    int i3 = paramInt2 - itemView.getTop();
    int i4 = paramList.size();
    int k = 0;
    while (k < i4)
    {
      RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)paramList.get(k);
      Object localObject1 = localObject2;
      int i = j;
      int m;
      if (i2 > 0)
      {
        m = itemView.getRight() - (paramInt1 + n);
        localObject1 = localObject2;
        i = j;
        if (m < 0)
        {
          localObject1 = localObject2;
          i = j;
          if (itemView.getRight() > itemView.getRight())
          {
            m = Math.abs(m);
            localObject1 = localObject2;
            i = j;
            if (m > j)
            {
              i = m;
              localObject1 = localViewHolder;
            }
          }
        }
      }
      localObject2 = localObject1;
      j = i;
      if (i2 < 0)
      {
        m = itemView.getLeft() - paramInt1;
        localObject2 = localObject1;
        j = i;
        if (m > 0)
        {
          localObject2 = localObject1;
          j = i;
          if (itemView.getLeft() < itemView.getLeft())
          {
            m = Math.abs(m);
            localObject2 = localObject1;
            j = i;
            if (m > i)
            {
              j = m;
              localObject2 = localViewHolder;
            }
          }
        }
      }
      localObject1 = localObject2;
      i = j;
      if (i3 < 0)
      {
        m = itemView.getTop() - paramInt2;
        localObject1 = localObject2;
        i = j;
        if (m > 0)
        {
          localObject1 = localObject2;
          i = j;
          if (itemView.getTop() < itemView.getTop())
          {
            m = Math.abs(m);
            localObject1 = localObject2;
            i = j;
            if (m > j)
            {
              i = m;
              localObject1 = localViewHolder;
            }
          }
        }
      }
      localObject2 = localObject1;
      j = i;
      if (i3 > 0)
      {
        m = itemView.getBottom() - (paramInt2 + i1);
        localObject2 = localObject1;
        j = i;
        if (m < 0)
        {
          localObject2 = localObject1;
          j = i;
          if (itemView.getBottom() > itemView.getBottom())
          {
            m = Math.abs(m);
            localObject2 = localObject1;
            j = i;
            if (m > i)
            {
              j = m;
              localObject2 = localViewHolder;
            }
          }
        }
      }
      k += 1;
    }
    return (RecyclerView.ViewHolder)localObject2;
  }
  
  public void clearView(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
  {
    sUICallback.clearView(itemView);
  }
  
  public int convertToAbsoluteDirection(int paramInt1, int paramInt2)
  {
    int i = paramInt1 & 0x303030;
    if (i == 0) {
      return paramInt1;
    }
    paramInt1 &= (i ^ 0xFFFFFFFF);
    if (paramInt2 == 0) {
      return paramInt1 | i >> 2;
    }
    return paramInt1 | i >> 1 & 0xFFCFCFCF | (i >> 1 & 0x303030) >> 2;
  }
  
  final int getAbsoluteMovementFlags(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
  {
    return convertToAbsoluteDirection(getMovementFlags(paramRecyclerView, paramViewHolder), ViewCompat.getLayoutDirection(paramRecyclerView));
  }
  
  public long getAnimationDuration(RecyclerView paramRecyclerView, int paramInt, float paramFloat1, float paramFloat2)
  {
    paramRecyclerView = paramRecyclerView.getItemAnimator();
    if (paramRecyclerView == null)
    {
      if (paramInt == 8) {
        return 200L;
      }
      return 250L;
    }
    if (paramInt == 8) {
      return paramRecyclerView.getMoveDuration();
    }
    return paramRecyclerView.getRemoveDuration();
  }
  
  public int getBoundingBoxMargin()
  {
    return 0;
  }
  
  public float getMoveThreshold(RecyclerView.ViewHolder paramViewHolder)
  {
    return 0.5F;
  }
  
  public abstract int getMovementFlags(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder);
  
  public float getSwipeEscapeVelocity(float paramFloat)
  {
    return paramFloat;
  }
  
  public float getSwipeThreshold(RecyclerView.ViewHolder paramViewHolder)
  {
    return 0.5F;
  }
  
  public float getSwipeVelocityThreshold(float paramFloat)
  {
    return paramFloat;
  }
  
  public int interpolateOutOfBoundsScroll(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    paramInt3 = getMaxDragScroll(paramRecyclerView);
    int i = Math.abs(paramInt2);
    int j = (int)Math.signum(paramInt2);
    float f = Math.min(1.0F, 1.0F * i / paramInt1);
    paramInt1 = (int)(j * paramInt3 * sDragViewScrollCapInterpolator.getInterpolation(f));
    if (paramLong > 2000L) {}
    for (f = 1.0F;; f = (float)paramLong / 2000.0F)
    {
      paramInt1 = (int)(paramInt1 * sDragScrollInterpolator.getInterpolation(f));
      if (paramInt1 != 0) {
        return paramInt1;
      }
      if (paramInt2 <= 0) {
        break;
      }
      return 1;
    }
    return -1;
    return paramInt1;
  }
  
  public boolean isItemViewSwipeEnabled()
  {
    return true;
  }
  
  public boolean isLongPressDragEnabled()
  {
    return true;
  }
  
  public void onChildDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
  {
    sUICallback.onDraw(paramCanvas, paramRecyclerView, itemView, paramFloat1, paramFloat2, paramInt, paramBoolean);
  }
  
  public void onChildDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
  {
    sUICallback.onDrawOver(paramCanvas, paramRecyclerView, itemView, paramFloat1, paramFloat2, paramInt, paramBoolean);
  }
  
  public abstract boolean onMove(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, RecyclerView.ViewHolder paramViewHolder2);
  
  public void onMoved(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, int paramInt1, RecyclerView.ViewHolder paramViewHolder2, int paramInt2, int paramInt3, int paramInt4)
  {
    RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.getLayoutManager();
    if ((localLayoutManager instanceof ItemTouchHelper.ViewDropHandler)) {
      ((ItemTouchHelper.ViewDropHandler)localLayoutManager).prepareForDrop(itemView, itemView, paramInt3, paramInt4);
    }
    do
    {
      do
      {
        return;
        if (localLayoutManager.canScrollHorizontally())
        {
          if (localLayoutManager.getDecoratedLeft(itemView) <= paramRecyclerView.getPaddingLeft()) {
            paramRecyclerView.scrollToPosition(paramInt2);
          }
          if (localLayoutManager.getDecoratedRight(itemView) >= paramRecyclerView.getWidth() - paramRecyclerView.getPaddingRight()) {
            paramRecyclerView.scrollToPosition(paramInt2);
          }
        }
      } while (!localLayoutManager.canScrollVertically());
      if (localLayoutManager.getDecoratedTop(itemView) <= paramRecyclerView.getPaddingTop()) {
        paramRecyclerView.scrollToPosition(paramInt2);
      }
    } while (localLayoutManager.getDecoratedBottom(itemView) < paramRecyclerView.getHeight() - paramRecyclerView.getPaddingBottom());
    paramRecyclerView.scrollToPosition(paramInt2);
  }
  
  public void onSelectedChanged(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    if (paramViewHolder != null) {
      sUICallback.onSelected(itemView);
    }
  }
  
  public abstract void onSwiped(RecyclerView.ViewHolder paramViewHolder, int paramInt);
}

/* Location:
 * Qualified Name:     android.support.v7.widget.helper.ItemTouchHelper.Callback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */