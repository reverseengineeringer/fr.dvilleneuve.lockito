package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager
{
  private static TagManager ayG;
  private final DataLayer ava;
  private final zzs axA;
  private final zza ayD;
  private final zzda ayE;
  private final ConcurrentMap<zzo, Boolean> ayF;
  private final Context mContext;
  
  TagManager(Context paramContext, zza paramzza, DataLayer paramDataLayer, zzda paramzzda)
  {
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    mContext = paramContext.getApplicationContext();
    ayE = paramzzda;
    ayD = paramzza;
    ayF = new ConcurrentHashMap();
    ava = paramDataLayer;
    ava.zza(new DataLayer.zzb()
    {
      public void zzaw(Map<String, Object> paramAnonymousMap)
      {
        paramAnonymousMap = paramAnonymousMap.get("event");
        if (paramAnonymousMap != null) {
          TagManager.zza(TagManager.this, paramAnonymousMap.toString());
        }
      }
    });
    ava.zza(new zzd(mContext));
    axA = new zzs();
    zzcde();
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public static TagManager getInstance(Context paramContext)
  {
    try
    {
      if (ayG != null) {
        break label68;
      }
      if (paramContext == null)
      {
        zzbn.e("TagManager.getInstance requires non-null context.");
        throw new NullPointerException();
      }
    }
    finally {}
    ayG = new TagManager(paramContext, new zza()new DataLayernew zzw
    {
      public zzp zza(Context paramAnonymousContext, TagManager paramAnonymousTagManager, Looper paramAnonymousLooper, String paramAnonymousString, int paramAnonymousInt, zzs paramAnonymouszzs)
      {
        return new zzp(paramAnonymousContext, paramAnonymousTagManager, paramAnonymousLooper, paramAnonymousString, paramAnonymousInt, paramAnonymouszzs);
      }
    }, new DataLayer(new zzw(paramContext)), zzdb.zzccy());
    label68:
    paramContext = ayG;
    return paramContext;
  }
  
  @TargetApi(14)
  private void zzcde()
  {
    if (Build.VERSION.SDK_INT >= 14) {
      mContext.registerComponentCallbacks(new ComponentCallbacks2()
      {
        public void onConfigurationChanged(Configuration paramAnonymousConfiguration) {}
        
        public void onLowMemory() {}
        
        public void onTrimMemory(int paramAnonymousInt)
        {
          if (paramAnonymousInt == 20) {
            dispatch();
          }
        }
      });
    }
  }
  
  private void zzoo(String paramString)
  {
    Iterator localIterator = ayF.keySet().iterator();
    while (localIterator.hasNext()) {
      ((zzo)localIterator.next()).zznq(paramString);
    }
  }
  
  public void dispatch()
  {
    ayE.dispatch();
  }
  
  public DataLayer getDataLayer()
  {
    return ava;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, @RawRes int paramInt)
  {
    paramString = ayD.zza(mContext, this, null, paramString, paramInt, axA);
    paramString.zzcam();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerDefaultOnly(String paramString, @RawRes int paramInt, Handler paramHandler)
  {
    paramString = ayD.zza(mContext, this, paramHandler.getLooper(), paramString, paramInt, axA);
    paramString.zzcam();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, @RawRes int paramInt)
  {
    paramString = ayD.zza(mContext, this, null, paramString, paramInt, axA);
    paramString.zzcao();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferFresh(String paramString, @RawRes int paramInt, Handler paramHandler)
  {
    paramString = ayD.zza(mContext, this, paramHandler.getLooper(), paramString, paramInt, axA);
    paramString.zzcao();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, @RawRes int paramInt)
  {
    paramString = ayD.zza(mContext, this, null, paramString, paramInt, axA);
    paramString.zzcan();
    return paramString;
  }
  
  public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String paramString, @RawRes int paramInt, Handler paramHandler)
  {
    paramString = ayD.zza(mContext, this, paramHandler.getLooper(), paramString, paramInt, axA);
    paramString.zzcan();
    return paramString;
  }
  
  public void setVerboseLoggingEnabled(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 2;; i = 5)
    {
      zzbn.setLogLevel(i);
      return;
    }
  }
  
  public void zza(zzo paramzzo)
  {
    ayF.put(paramzzo, Boolean.valueOf(true));
  }
  
  public boolean zzb(zzo paramzzo)
  {
    return ayF.remove(paramzzo) != null;
  }
  
  boolean zzt(Uri paramUri)
  {
    for (;;)
    {
      boolean bool;
      Object localObject2;
      try
      {
        localObject1 = zzci.zzcce();
        if (!((zzci)localObject1).zzt(paramUri)) {
          break label229;
        }
        paramUri = ((zzci)localObject1).getContainerId();
        int i = 4.ayI[localObject1.zzccf().ordinal()];
        switch (i)
        {
        default: 
          bool = true;
          return bool;
        }
      }
      finally {}
      Object localObject1 = ayF.keySet().iterator();
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (zzo)((Iterator)localObject1).next();
        if (((zzo)localObject2).getContainerId().equals(paramUri))
        {
          ((zzo)localObject2).zzns(null);
          ((zzo)localObject2).refresh();
        }
      }
      else
      {
        continue;
        localObject2 = ayF.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          zzo localzzo = (zzo)((Iterator)localObject2).next();
          if (localzzo.getContainerId().equals(paramUri))
          {
            localzzo.zzns(((zzci)localObject1).zzccg());
            localzzo.refresh();
          }
          else if (localzzo.zzcaj() != null)
          {
            localzzo.zzns(null);
            localzzo.refresh();
          }
        }
        continue;
        label229:
        bool = false;
      }
    }
  }
  
  public static abstract interface zza
  {
    public abstract zzp zza(Context paramContext, TagManager paramTagManager, Looper paramLooper, String paramString, int paramInt, zzs paramzzs);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.TagManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */