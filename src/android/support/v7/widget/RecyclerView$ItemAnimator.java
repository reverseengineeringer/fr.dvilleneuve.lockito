package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerView$ItemAnimator
{
  public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
  public static final int FLAG_CHANGED = 2;
  public static final int FLAG_INVALIDATED = 4;
  public static final int FLAG_MOVED = 2048;
  public static final int FLAG_REMOVED = 8;
  private long mAddDuration = 120L;
  private long mChangeDuration = 250L;
  private ArrayList<ItemAnimatorFinishedListener> mFinishedListeners = new ArrayList();
  private ItemAnimatorListener mListener = null;
  private long mMoveDuration = 250L;
  private long mRemoveDuration = 120L;
  
  static int buildAdapterChangeFlagsForAnimations(RecyclerView.ViewHolder paramViewHolder)
  {
    int j = RecyclerView.ViewHolder.access$6500(paramViewHolder) & 0xE;
    if (paramViewHolder.isInvalid()) {
      return 4;
    }
    int i = j;
    if ((j & 0x4) == 0)
    {
      int k = paramViewHolder.getOldPosition();
      int m = paramViewHolder.getAdapterPosition();
      i = j;
      if (k != -1)
      {
        i = j;
        if (m != -1)
        {
          i = j;
          if (k != m) {
            i = j | 0x800;
          }
        }
      }
    }
    return i;
  }
  
  public abstract boolean animateAppearance(@NonNull RecyclerView.ViewHolder paramViewHolder, @Nullable ItemHolderInfo paramItemHolderInfo1, @NonNull ItemHolderInfo paramItemHolderInfo2);
  
  public abstract boolean animateChange(@NonNull RecyclerView.ViewHolder paramViewHolder1, @NonNull RecyclerView.ViewHolder paramViewHolder2, @NonNull ItemHolderInfo paramItemHolderInfo1, @NonNull ItemHolderInfo paramItemHolderInfo2);
  
  public abstract boolean animateDisappearance(@NonNull RecyclerView.ViewHolder paramViewHolder, @NonNull ItemHolderInfo paramItemHolderInfo1, @Nullable ItemHolderInfo paramItemHolderInfo2);
  
  public abstract boolean animatePersistence(@NonNull RecyclerView.ViewHolder paramViewHolder, @NonNull ItemHolderInfo paramItemHolderInfo1, @NonNull ItemHolderInfo paramItemHolderInfo2);
  
  public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder paramViewHolder)
  {
    return true;
  }
  
  public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder paramViewHolder, @NonNull List<Object> paramList)
  {
    return canReuseUpdatedViewHolder(paramViewHolder);
  }
  
  public final void dispatchAnimationFinished(RecyclerView.ViewHolder paramViewHolder)
  {
    onAnimationFinished(paramViewHolder);
    if (mListener != null) {
      mListener.onAnimationFinished(paramViewHolder);
    }
  }
  
  public final void dispatchAnimationStarted(RecyclerView.ViewHolder paramViewHolder)
  {
    onAnimationStarted(paramViewHolder);
  }
  
  public final void dispatchAnimationsFinished()
  {
    int j = mFinishedListeners.size();
    int i = 0;
    while (i < j)
    {
      ((ItemAnimatorFinishedListener)mFinishedListeners.get(i)).onAnimationsFinished();
      i += 1;
    }
    mFinishedListeners.clear();
  }
  
  public abstract void endAnimation(RecyclerView.ViewHolder paramViewHolder);
  
  public abstract void endAnimations();
  
  public long getAddDuration()
  {
    return mAddDuration;
  }
  
  public long getChangeDuration()
  {
    return mChangeDuration;
  }
  
  public long getMoveDuration()
  {
    return mMoveDuration;
  }
  
  public long getRemoveDuration()
  {
    return mRemoveDuration;
  }
  
  public abstract boolean isRunning();
  
  public final boolean isRunning(ItemAnimatorFinishedListener paramItemAnimatorFinishedListener)
  {
    boolean bool = isRunning();
    if (paramItemAnimatorFinishedListener != null)
    {
      if (!bool) {
        paramItemAnimatorFinishedListener.onAnimationsFinished();
      }
    }
    else {
      return bool;
    }
    mFinishedListeners.add(paramItemAnimatorFinishedListener);
    return bool;
  }
  
  public ItemHolderInfo obtainHolderInfo()
  {
    return new ItemHolderInfo();
  }
  
  public void onAnimationFinished(RecyclerView.ViewHolder paramViewHolder) {}
  
  public void onAnimationStarted(RecyclerView.ViewHolder paramViewHolder) {}
  
  @NonNull
  public ItemHolderInfo recordPostLayoutInformation(@NonNull RecyclerView.State paramState, @NonNull RecyclerView.ViewHolder paramViewHolder)
  {
    return obtainHolderInfo().setFrom(paramViewHolder);
  }
  
  @NonNull
  public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State paramState, @NonNull RecyclerView.ViewHolder paramViewHolder, int paramInt, @NonNull List<Object> paramList)
  {
    return obtainHolderInfo().setFrom(paramViewHolder);
  }
  
  public abstract void runPendingAnimations();
  
  public void setAddDuration(long paramLong)
  {
    mAddDuration = paramLong;
  }
  
  public void setChangeDuration(long paramLong)
  {
    mChangeDuration = paramLong;
  }
  
  void setListener(ItemAnimatorListener paramItemAnimatorListener)
  {
    mListener = paramItemAnimatorListener;
  }
  
  public void setMoveDuration(long paramLong)
  {
    mMoveDuration = paramLong;
  }
  
  public void setRemoveDuration(long paramLong)
  {
    mRemoveDuration = paramLong;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface AdapterChanges {}
  
  public static abstract interface ItemAnimatorFinishedListener
  {
    public abstract void onAnimationsFinished();
  }
  
  static abstract interface ItemAnimatorListener
  {
    public abstract void onAnimationFinished(RecyclerView.ViewHolder paramViewHolder);
  }
  
  public static class ItemHolderInfo
  {
    public int bottom;
    public int changeFlags;
    public int left;
    public int right;
    public int top;
    
    public ItemHolderInfo setFrom(RecyclerView.ViewHolder paramViewHolder)
    {
      return setFrom(paramViewHolder, 0);
    }
    
    public ItemHolderInfo setFrom(RecyclerView.ViewHolder paramViewHolder, int paramInt)
    {
      paramViewHolder = itemView;
      left = paramViewHolder.getLeft();
      top = paramViewHolder.getTop();
      right = paramViewHolder.getRight();
      bottom = paramViewHolder.getBottom();
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.RecyclerView.ItemAnimator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */