package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import java.io.IOException;

public class RequestCreator
{
  private final Request.Builder data;
  private boolean deferred;
  private Drawable errorDrawable;
  private int errorResId;
  private boolean noFade;
  private final Picasso picasso;
  private Drawable placeholderDrawable;
  private int placeholderResId;
  private boolean skipMemoryCache;
  
  RequestCreator()
  {
    picasso = null;
    data = new Request.Builder(null, 0);
  }
  
  RequestCreator(Picasso paramPicasso, Uri paramUri, int paramInt)
  {
    if (shutdown) {
      throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
    }
    picasso = paramPicasso;
    data = new Request.Builder(paramUri, paramInt);
  }
  
  public RequestCreator centerCrop()
  {
    data.centerCrop();
    return this;
  }
  
  public RequestCreator centerInside()
  {
    data.centerInside();
    return this;
  }
  
  public RequestCreator error(int paramInt)
  {
    if (paramInt == 0) {
      throw new IllegalArgumentException("Error image resource invalid.");
    }
    if (errorDrawable != null) {
      throw new IllegalStateException("Error image already set.");
    }
    errorResId = paramInt;
    return this;
  }
  
  public RequestCreator error(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      throw new IllegalArgumentException("Error image may not be null.");
    }
    if (errorResId != 0) {
      throw new IllegalStateException("Error image already set.");
    }
    errorDrawable = paramDrawable;
    return this;
  }
  
  public void fetch()
  {
    if (deferred) {
      throw new IllegalStateException("Fit cannot be used with fetch.");
    }
    if (data.hasImage())
    {
      Object localObject = picasso.transformRequest(data.build());
      String str = Utils.createKey((Request)localObject);
      localObject = new FetchAction(picasso, (Request)localObject, skipMemoryCache, str);
      picasso.enqueueAndSubmit((Action)localObject);
    }
  }
  
  public RequestCreator fit()
  {
    deferred = true;
    return this;
  }
  
  public Bitmap get()
    throws IOException
  {
    
    if (deferred) {
      throw new IllegalStateException("Fit cannot be used with get.");
    }
    if (!data.hasImage()) {
      return null;
    }
    Object localObject = picasso.transformRequest(data.build());
    String str = Utils.createKey((Request)localObject);
    localObject = new GetAction(picasso, (Request)localObject, skipMemoryCache, str);
    return BitmapHunter.forRequest(picasso.context, picasso, picasso.dispatcher, picasso.cache, picasso.stats, (Action)localObject, picasso.dispatcher.downloader).hunt();
  }
  
  public void into(ImageView paramImageView)
  {
    into(paramImageView, null);
  }
  
  public void into(ImageView paramImageView, Callback paramCallback)
  {
    if (paramImageView == null) {
      throw new IllegalArgumentException("Target must not be null.");
    }
    if (!data.hasImage())
    {
      picasso.cancelRequest(paramImageView);
      PicassoDrawable.setPlaceholder(paramImageView, placeholderResId, placeholderDrawable);
    }
    Request localRequest;
    String str;
    do
    {
      return;
      if (deferred)
      {
        if (data.hasSize()) {
          throw new IllegalStateException("Fit cannot be used with resize.");
        }
        int i = paramImageView.getMeasuredWidth();
        int j = paramImageView.getMeasuredHeight();
        if ((i == 0) && (j == 0))
        {
          PicassoDrawable.setPlaceholder(paramImageView, placeholderResId, placeholderDrawable);
          picasso.defer(paramImageView, new DeferredRequestCreator(this, paramImageView, paramCallback));
          return;
        }
        data.resize(i, j);
      }
      localRequest = picasso.transformRequest(data.build());
      str = Utils.createKey(localRequest);
      if (skipMemoryCache) {
        break;
      }
      Bitmap localBitmap = picasso.quickMemoryCacheCheck(str);
      if (localBitmap == null) {
        break;
      }
      picasso.cancelRequest(paramImageView);
      PicassoDrawable.setBitmap(paramImageView, picasso.context, localBitmap, Picasso.LoadedFrom.MEMORY, noFade, picasso.debugging);
    } while (paramCallback == null);
    paramCallback.onSuccess();
    return;
    PicassoDrawable.setPlaceholder(paramImageView, placeholderResId, placeholderDrawable);
    paramImageView = new ImageViewAction(picasso, paramImageView, localRequest, skipMemoryCache, noFade, errorResId, errorDrawable, str, paramCallback);
    picasso.enqueueAndSubmit(paramImageView);
  }
  
  public void into(Target paramTarget)
  {
    if (paramTarget == null) {
      throw new IllegalArgumentException("Target must not be null.");
    }
    if (deferred) {
      throw new IllegalStateException("Fit cannot be used with a Target.");
    }
    if (placeholderResId != 0) {}
    for (Drawable localDrawable = picasso.context.getResources().getDrawable(placeholderResId); !data.hasImage(); localDrawable = placeholderDrawable)
    {
      picasso.cancelRequest(paramTarget);
      paramTarget.onPrepareLoad(localDrawable);
      return;
    }
    Request localRequest = picasso.transformRequest(data.build());
    String str = Utils.createKey(localRequest);
    if (!skipMemoryCache)
    {
      Bitmap localBitmap = picasso.quickMemoryCacheCheck(str);
      if (localBitmap != null)
      {
        picasso.cancelRequest(paramTarget);
        paramTarget.onBitmapLoaded(localBitmap, Picasso.LoadedFrom.MEMORY);
        return;
      }
    }
    paramTarget.onPrepareLoad(localDrawable);
    paramTarget = new TargetAction(picasso, paramTarget, localRequest, skipMemoryCache, str);
    picasso.enqueueAndSubmit(paramTarget);
  }
  
  public RequestCreator noFade()
  {
    noFade = true;
    return this;
  }
  
  public RequestCreator placeholder(int paramInt)
  {
    if (paramInt == 0) {
      throw new IllegalArgumentException("Placeholder image resource invalid.");
    }
    if (placeholderDrawable != null) {
      throw new IllegalStateException("Placeholder image already set.");
    }
    placeholderResId = paramInt;
    return this;
  }
  
  public RequestCreator placeholder(Drawable paramDrawable)
  {
    if (placeholderResId != 0) {
      throw new IllegalStateException("Placeholder image already set.");
    }
    placeholderDrawable = paramDrawable;
    return this;
  }
  
  public RequestCreator resize(int paramInt1, int paramInt2)
  {
    data.resize(paramInt1, paramInt2);
    return this;
  }
  
  public RequestCreator resizeDimen(int paramInt1, int paramInt2)
  {
    Resources localResources = picasso.context.getResources();
    return resize(localResources.getDimensionPixelSize(paramInt1), localResources.getDimensionPixelSize(paramInt2));
  }
  
  public RequestCreator rotate(float paramFloat)
  {
    data.rotate(paramFloat);
    return this;
  }
  
  public RequestCreator rotate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    data.rotate(paramFloat1, paramFloat2, paramFloat3);
    return this;
  }
  
  public RequestCreator skipMemoryCache()
  {
    skipMemoryCache = true;
    return this;
  }
  
  public RequestCreator transform(Transformation paramTransformation)
  {
    data.transform(paramTransformation);
    return this;
  }
  
  RequestCreator unfit()
  {
    deferred = false;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.RequestCreator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */