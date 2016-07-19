package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory
{
  private static ICameraUpdateFactoryDelegate agv;
  
  public static CameraUpdate newCameraPosition(CameraPosition paramCameraPosition)
  {
    try
    {
      paramCameraPosition = new CameraUpdate(zzbph().newCameraPosition(paramCameraPosition));
      return paramCameraPosition;
    }
    catch (RemoteException paramCameraPosition)
    {
      throw new RuntimeRemoteException(paramCameraPosition);
    }
  }
  
  public static CameraUpdate newLatLng(LatLng paramLatLng)
  {
    try
    {
      paramLatLng = new CameraUpdate(zzbph().newLatLng(paramLatLng));
      return paramLatLng;
    }
    catch (RemoteException paramLatLng)
    {
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt)
  {
    try
    {
      paramLatLngBounds = new CameraUpdate(zzbph().newLatLngBounds(paramLatLngBounds, paramInt));
      return paramLatLngBounds;
    }
    catch (RemoteException paramLatLngBounds)
    {
      throw new RuntimeRemoteException(paramLatLngBounds);
    }
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      paramLatLngBounds = new CameraUpdate(zzbph().newLatLngBoundsWithSize(paramLatLngBounds, paramInt1, paramInt2, paramInt3));
      return paramLatLngBounds;
    }
    catch (RemoteException paramLatLngBounds)
    {
      throw new RuntimeRemoteException(paramLatLngBounds);
    }
  }
  
  public static CameraUpdate newLatLngZoom(LatLng paramLatLng, float paramFloat)
  {
    try
    {
      paramLatLng = new CameraUpdate(zzbph().newLatLngZoom(paramLatLng, paramFloat));
      return paramLatLng;
    }
    catch (RemoteException paramLatLng)
    {
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public static CameraUpdate scrollBy(float paramFloat1, float paramFloat2)
  {
    try
    {
      CameraUpdate localCameraUpdate = new CameraUpdate(zzbph().scrollBy(paramFloat1, paramFloat2));
      return localCameraUpdate;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static CameraUpdate zoomBy(float paramFloat)
  {
    try
    {
      CameraUpdate localCameraUpdate = new CameraUpdate(zzbph().zoomBy(paramFloat));
      return localCameraUpdate;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static CameraUpdate zoomBy(float paramFloat, Point paramPoint)
  {
    try
    {
      paramPoint = new CameraUpdate(zzbph().zoomByWithFocus(paramFloat, x, y));
      return paramPoint;
    }
    catch (RemoteException paramPoint)
    {
      throw new RuntimeRemoteException(paramPoint);
    }
  }
  
  public static CameraUpdate zoomIn()
  {
    try
    {
      CameraUpdate localCameraUpdate = new CameraUpdate(zzbph().zoomIn());
      return localCameraUpdate;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static CameraUpdate zoomOut()
  {
    try
    {
      CameraUpdate localCameraUpdate = new CameraUpdate(zzbph().zoomOut());
      return localCameraUpdate;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static CameraUpdate zoomTo(float paramFloat)
  {
    try
    {
      CameraUpdate localCameraUpdate = new CameraUpdate(zzbph().zoomTo(paramFloat));
      return localCameraUpdate;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static void zza(ICameraUpdateFactoryDelegate paramICameraUpdateFactoryDelegate)
  {
    agv = (ICameraUpdateFactoryDelegate)zzab.zzaa(paramICameraUpdateFactoryDelegate);
  }
  
  private static ICameraUpdateFactoryDelegate zzbph()
  {
    return (ICameraUpdateFactoryDelegate)zzab.zzb(agv, "CameraUpdateFactory is not initialized");
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.CameraUpdateFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */