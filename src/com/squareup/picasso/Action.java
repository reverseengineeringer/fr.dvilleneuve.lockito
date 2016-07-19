package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class Action<T>
{
  boolean cancelled;
  final Request data;
  final Drawable errorDrawable;
  final int errorResId;
  final String key;
  final boolean noFade;
  final Picasso picasso;
  final boolean skipCache;
  final WeakReference<T> target;
  
  Action(Picasso paramPicasso, T paramT, Request paramRequest, boolean paramBoolean1, boolean paramBoolean2, int paramInt, Drawable paramDrawable, String paramString)
  {
    picasso = paramPicasso;
    data = paramRequest;
    target = new RequestWeakReference(this, paramT, referenceQueue);
    skipCache = paramBoolean1;
    noFade = paramBoolean2;
    errorResId = paramInt;
    errorDrawable = paramDrawable;
    key = paramString;
  }
  
  void cancel()
  {
    cancelled = true;
  }
  
  abstract void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom);
  
  abstract void error();
  
  Request getData()
  {
    return data;
  }
  
  String getKey()
  {
    return key;
  }
  
  Picasso getPicasso()
  {
    return picasso;
  }
  
  T getTarget()
  {
    return (T)target.get();
  }
  
  boolean isCancelled()
  {
    return cancelled;
  }
  
  static class RequestWeakReference<T>
    extends WeakReference<T>
  {
    final Action action;
    
    public RequestWeakReference(Action paramAction, T paramT, ReferenceQueue<? super T> paramReferenceQueue)
    {
      super(paramReferenceQueue);
      action = paramAction;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Action
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */