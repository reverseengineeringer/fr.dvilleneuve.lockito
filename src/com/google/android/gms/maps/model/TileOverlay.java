package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.model.internal.zzh;

public final class TileOverlay
{
  private final zzh aiT;
  
  public TileOverlay(zzh paramzzh)
  {
    aiT = ((zzh)zzab.zzaa(paramzzh));
  }
  
  public void clearTileCache()
  {
    try
    {
      aiT.clearTileCache();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof TileOverlay)) {
      return false;
    }
    try
    {
      boolean bool = aiT.zza(aiT);
      return bool;
    }
    catch (RemoteException paramObject)
    {
      throw new RuntimeRemoteException((RemoteException)paramObject);
    }
  }
  
  public boolean getFadeIn()
  {
    try
    {
      boolean bool = aiT.getFadeIn();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public String getId()
  {
    try
    {
      String str = aiT.getId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getTransparency()
  {
    try
    {
      float f = aiT.getTransparency();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public float getZIndex()
  {
    try
    {
      float f = aiT.getZIndex();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = aiT.hashCodeRemote();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public boolean isVisible()
  {
    try
    {
      boolean bool = aiT.isVisible();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void remove()
  {
    try
    {
      aiT.remove();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setFadeIn(boolean paramBoolean)
  {
    try
    {
      aiT.setFadeIn(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setTransparency(float paramFloat)
  {
    try
    {
      aiT.setTransparency(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setVisible(boolean paramBoolean)
  {
    try
    {
      aiT.setVisible(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public void setZIndex(float paramFloat)
  {
    try
    {
      aiT.setZIndex(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.TileOverlay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */