package android.support.v7.util;

import android.support.annotation.WorkerThread;

public abstract class AsyncListUtil$DataCallback<T>
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

/* Location:
 * Qualified Name:     android.support.v7.util.AsyncListUtil.DataCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */