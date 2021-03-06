package android.support.v7.util;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil<T>
{
  private static final boolean DEBUG = false;
  private static final String TAG = "AsyncListUtil";
  private boolean mAllowScrollHints;
  private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback = new ThreadUtil.BackgroundCallback()
  {
    private int mFirstRequiredTileStart;
    private int mGeneration;
    private int mItemCount;
    private int mLastRequiredTileStart;
    final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
    private TileList.Tile<T> mRecycledRoot;
    
    private TileList.Tile<T> acquireTile()
    {
      if (mRecycledRoot != null)
      {
        TileList.Tile localTile = mRecycledRoot;
        mRecycledRoot = mRecycledRoot.mNext;
        return localTile;
      }
      return new TileList.Tile(mTClass, mTileSize);
    }
    
    private void addTile(TileList.Tile<T> paramAnonymousTile)
    {
      mLoadedTiles.put(mStartPosition, true);
      mMainThreadProxy.addTile(mGeneration, paramAnonymousTile);
    }
    
    private void flushTileCache(int paramAnonymousInt)
    {
      int i = mDataCallback.getMaxCachedTiles();
      while (mLoadedTiles.size() >= i)
      {
        int j = mLoadedTiles.keyAt(0);
        int k = mLoadedTiles.keyAt(mLoadedTiles.size() - 1);
        int m = mFirstRequiredTileStart - j;
        int n = k - mLastRequiredTileStart;
        if ((m > 0) && ((m >= n) || (paramAnonymousInt == 2)))
        {
          removeTile(j);
        }
        else
        {
          if ((n <= 0) || ((m >= n) && (paramAnonymousInt != 1))) {
            break;
          }
          removeTile(k);
        }
      }
    }
    
    private int getTileStart(int paramAnonymousInt)
    {
      return paramAnonymousInt - paramAnonymousInt % mTileSize;
    }
    
    private boolean isTileLoaded(int paramAnonymousInt)
    {
      return mLoadedTiles.get(paramAnonymousInt);
    }
    
    private void log(String paramAnonymousString, Object... paramAnonymousVarArgs)
    {
      Log.d("AsyncListUtil", "[BKGR] " + String.format(paramAnonymousString, paramAnonymousVarArgs));
    }
    
    private void removeTile(int paramAnonymousInt)
    {
      mLoadedTiles.delete(paramAnonymousInt);
      mMainThreadProxy.removeTile(mGeneration, paramAnonymousInt);
    }
    
    private void requestTiles(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, boolean paramAnonymousBoolean)
    {
      int i = paramAnonymousInt1;
      if (i <= paramAnonymousInt2)
      {
        if (paramAnonymousBoolean) {}
        for (int j = paramAnonymousInt2 + paramAnonymousInt1 - i;; j = i)
        {
          mBackgroundProxy.loadTile(j, paramAnonymousInt3);
          i += mTileSize;
          break;
        }
      }
    }
    
    public void loadTile(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (isTileLoaded(paramAnonymousInt1)) {
        return;
      }
      TileList.Tile localTile = acquireTile();
      mStartPosition = paramAnonymousInt1;
      mItemCount = Math.min(mTileSize, mItemCount - mStartPosition);
      mDataCallback.fillData(mItems, mStartPosition, mItemCount);
      flushTileCache(paramAnonymousInt2);
      addTile(localTile);
    }
    
    public void recycleTile(TileList.Tile<T> paramAnonymousTile)
    {
      mDataCallback.recycleData(mItems, mItemCount);
      mNext = mRecycledRoot;
      mRecycledRoot = paramAnonymousTile;
    }
    
    public void refresh(int paramAnonymousInt)
    {
      mGeneration = paramAnonymousInt;
      mLoadedTiles.clear();
      mItemCount = mDataCallback.refreshData();
      mMainThreadProxy.updateItemCount(mGeneration, mItemCount);
    }
    
    public void updateRange(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5)
    {
      if (paramAnonymousInt1 > paramAnonymousInt2) {
        return;
      }
      paramAnonymousInt1 = getTileStart(paramAnonymousInt1);
      paramAnonymousInt2 = getTileStart(paramAnonymousInt2);
      mFirstRequiredTileStart = getTileStart(paramAnonymousInt3);
      mLastRequiredTileStart = getTileStart(paramAnonymousInt4);
      if (paramAnonymousInt5 == 1)
      {
        requestTiles(mFirstRequiredTileStart, paramAnonymousInt2, paramAnonymousInt5, true);
        requestTiles(mTileSize + paramAnonymousInt2, mLastRequiredTileStart, paramAnonymousInt5, false);
        return;
      }
      requestTiles(paramAnonymousInt1, mLastRequiredTileStart, paramAnonymousInt5, false);
      requestTiles(mFirstRequiredTileStart, paramAnonymousInt1 - mTileSize, paramAnonymousInt5, true);
    }
  };
  final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
  final DataCallback<T> mDataCallback;
  int mDisplayedGeneration = 0;
  private int mItemCount = 0;
  private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback = new ThreadUtil.MainThreadCallback()
  {
    private boolean isRequestedGeneration(int paramAnonymousInt)
    {
      return paramAnonymousInt == mRequestedGeneration;
    }
    
    private void recycleAllTiles()
    {
      int i = 0;
      while (i < mTileList.size())
      {
        mBackgroundProxy.recycleTile(mTileList.getAtIndex(i));
        i += 1;
      }
      mTileList.clear();
    }
    
    public void addTile(int paramAnonymousInt, TileList.Tile<T> paramAnonymousTile)
    {
      if (!isRequestedGeneration(paramAnonymousInt)) {
        mBackgroundProxy.recycleTile(paramAnonymousTile);
      }
      for (;;)
      {
        return;
        TileList.Tile localTile = mTileList.addOrReplace(paramAnonymousTile);
        if (localTile != null)
        {
          Log.e("AsyncListUtil", "duplicate tile @" + mStartPosition);
          mBackgroundProxy.recycleTile(localTile);
        }
        int i = mStartPosition;
        int j = mItemCount;
        paramAnonymousInt = 0;
        while (paramAnonymousInt < mMissingPositions.size())
        {
          int k = mMissingPositions.keyAt(paramAnonymousInt);
          if ((mStartPosition <= k) && (k < i + j))
          {
            mMissingPositions.removeAt(paramAnonymousInt);
            mViewCallback.onItemLoaded(k);
          }
          else
          {
            paramAnonymousInt += 1;
          }
        }
      }
    }
    
    public void removeTile(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (!isRequestedGeneration(paramAnonymousInt1)) {
        return;
      }
      TileList.Tile localTile = mTileList.removeAtPos(paramAnonymousInt2);
      if (localTile == null)
      {
        Log.e("AsyncListUtil", "tile not found @" + paramAnonymousInt2);
        return;
      }
      mBackgroundProxy.recycleTile(localTile);
    }
    
    public void updateItemCount(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (!isRequestedGeneration(paramAnonymousInt1)) {
        return;
      }
      AsyncListUtil.access$002(AsyncListUtil.this, paramAnonymousInt2);
      mViewCallback.onDataRefresh();
      mDisplayedGeneration = mRequestedGeneration;
      recycleAllTiles();
      AsyncListUtil.access$102(AsyncListUtil.this, false);
      AsyncListUtil.this.updateRange();
    }
  };
  final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
  private final SparseIntArray mMissingPositions = new SparseIntArray();
  final int[] mPrevRange = new int[2];
  int mRequestedGeneration = mDisplayedGeneration;
  private int mScrollHint = 0;
  final Class<T> mTClass;
  final TileList<T> mTileList;
  final int mTileSize;
  final int[] mTmpRange = new int[2];
  final int[] mTmpRangeExtended = new int[2];
  final ViewCallback mViewCallback;
  
  public AsyncListUtil(Class<T> paramClass, int paramInt, DataCallback<T> paramDataCallback, ViewCallback paramViewCallback)
  {
    mTClass = paramClass;
    mTileSize = paramInt;
    mDataCallback = paramDataCallback;
    mViewCallback = paramViewCallback;
    mTileList = new TileList(mTileSize);
    paramClass = new MessageThreadUtil();
    mMainThreadProxy = paramClass.getMainThreadProxy(mMainThreadCallback);
    mBackgroundProxy = paramClass.getBackgroundProxy(mBackgroundCallback);
    refresh();
  }
  
  private boolean isRefreshPending()
  {
    return mRequestedGeneration != mDisplayedGeneration;
  }
  
  private void log(String paramString, Object... paramVarArgs)
  {
    Log.d("AsyncListUtil", "[MAIN] " + String.format(paramString, paramVarArgs));
  }
  
  private void updateRange()
  {
    mViewCallback.getItemRangeInto(mTmpRange);
    if ((mTmpRange[0] > mTmpRange[1]) || (mTmpRange[0] < 0)) {}
    while (mTmpRange[1] >= mItemCount) {
      return;
    }
    if (!mAllowScrollHints) {
      mScrollHint = 0;
    }
    for (;;)
    {
      mPrevRange[0] = mTmpRange[0];
      mPrevRange[1] = mTmpRange[1];
      mViewCallback.extendRangeInto(mTmpRange, mTmpRangeExtended, mScrollHint);
      mTmpRangeExtended[0] = Math.min(mTmpRange[0], Math.max(mTmpRangeExtended[0], 0));
      mTmpRangeExtended[1] = Math.max(mTmpRange[1], Math.min(mTmpRangeExtended[1], mItemCount - 1));
      mBackgroundProxy.updateRange(mTmpRange[0], mTmpRange[1], mTmpRangeExtended[0], mTmpRangeExtended[1], mScrollHint);
      return;
      if ((mTmpRange[0] > mPrevRange[1]) || (mPrevRange[0] > mTmpRange[1])) {
        mScrollHint = 0;
      } else if (mTmpRange[0] < mPrevRange[0]) {
        mScrollHint = 1;
      } else if (mTmpRange[0] > mPrevRange[0]) {
        mScrollHint = 2;
      }
    }
  }
  
  public T getItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mItemCount)) {
      throw new IndexOutOfBoundsException(paramInt + " is not within 0 and " + mItemCount);
    }
    Object localObject = mTileList.getItemAt(paramInt);
    if ((localObject == null) && (!isRefreshPending())) {
      mMissingPositions.put(paramInt, 0);
    }
    return (T)localObject;
  }
  
  public int getItemCount()
  {
    return mItemCount;
  }
  
  public void onRangeChanged()
  {
    if (isRefreshPending()) {
      return;
    }
    updateRange();
    mAllowScrollHints = true;
  }
  
  public void refresh()
  {
    mMissingPositions.clear();
    ThreadUtil.BackgroundCallback localBackgroundCallback = mBackgroundProxy;
    int i = mRequestedGeneration + 1;
    mRequestedGeneration = i;
    localBackgroundCallback.refresh(i);
  }
  
  public static abstract class DataCallback<T>
  {
    @WorkerThread
    public abstract void fillData(T[] paramArrayOfT, int paramInt1, int paramInt2);
    
    @WorkerThread
    public int getMaxCachedTiles()
    {
      return 10;
    }
    
    @WorkerThread
    public void recycleData(T[] paramArrayOfT, int paramInt) {}
    
    @WorkerThread
    public abstract int refreshData();
  }
  
  public static abstract class ViewCallback
  {
    public static final int HINT_SCROLL_ASC = 2;
    public static final int HINT_SCROLL_DESC = 1;
    public static final int HINT_SCROLL_NONE = 0;
    
    @UiThread
    public void extendRangeInto(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
    {
      int i = paramArrayOfInt1[1] - paramArrayOfInt1[0] + 1;
      int j = i / 2;
      int m = paramArrayOfInt1[0];
      int k;
      if (paramInt == 1)
      {
        k = i;
        paramArrayOfInt2[0] = (m - k);
        k = paramArrayOfInt1[1];
        if (paramInt != 2) {
          break label65;
        }
      }
      for (;;)
      {
        paramArrayOfInt2[1] = (k + i);
        return;
        k = j;
        break;
        label65:
        i = j;
      }
    }
    
    @UiThread
    public abstract void getItemRangeInto(int[] paramArrayOfInt);
    
    @UiThread
    public abstract void onDataRefresh();
    
    @UiThread
    public abstract void onItemLoaded(int paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.util.AsyncListUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */