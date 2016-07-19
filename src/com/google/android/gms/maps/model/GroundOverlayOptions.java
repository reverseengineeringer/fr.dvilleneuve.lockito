package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public final class GroundOverlayOptions
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  public static final float NO_DIMENSION = -1.0F;
  private LatLngBounds agp;
  private float aif;
  private float aim;
  private boolean ain = true;
  private boolean aio = false;
  private BitmapDescriptor aiq;
  private LatLng air;
  private float ais;
  private float ait;
  private float aiu = 0.0F;
  private float aiv = 0.5F;
  private float aiw = 0.5F;
  private final int mVersionCode;
  
  public GroundOverlayOptions()
  {
    mVersionCode = 1;
  }
  
  GroundOverlayOptions(int paramInt, IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean1, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean2)
  {
    mVersionCode = paramInt;
    aiq = new BitmapDescriptor(zzd.zza.zzfc(paramIBinder));
    air = paramLatLng;
    ais = paramFloat1;
    ait = paramFloat2;
    agp = paramLatLngBounds;
    aif = paramFloat3;
    aim = paramFloat4;
    ain = paramBoolean1;
    aiu = paramFloat5;
    aiv = paramFloat6;
    aiw = paramFloat7;
    aio = paramBoolean2;
  }
  
  private GroundOverlayOptions zza(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    air = paramLatLng;
    ais = paramFloat1;
    ait = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    aiv = paramFloat1;
    aiw = paramFloat2;
    return this;
  }
  
  public GroundOverlayOptions bearing(float paramFloat)
  {
    aif = ((paramFloat % 360.0F + 360.0F) % 360.0F);
    return this;
  }
  
  public GroundOverlayOptions clickable(boolean paramBoolean)
  {
    aio = paramBoolean;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getAnchorU()
  {
    return aiv;
  }
  
  public float getAnchorV()
  {
    return aiw;
  }
  
  public float getBearing()
  {
    return aif;
  }
  
  public LatLngBounds getBounds()
  {
    return agp;
  }
  
  public float getHeight()
  {
    return ait;
  }
  
  public BitmapDescriptor getImage()
  {
    return aiq;
  }
  
  public LatLng getLocation()
  {
    return air;
  }
  
  public float getTransparency()
  {
    return aiu;
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public float getWidth()
  {
    return ais;
  }
  
  public float getZIndex()
  {
    return aim;
  }
  
  public GroundOverlayOptions image(BitmapDescriptor paramBitmapDescriptor)
  {
    aiq = paramBitmapDescriptor;
    return this;
  }
  
  public boolean isClickable()
  {
    return aio;
  }
  
  public boolean isVisible()
  {
    return ain;
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat)
  {
    boolean bool2 = true;
    if (agp == null)
    {
      bool1 = true;
      zzab.zza(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label59;
      }
      bool1 = true;
      label24:
      zzab.zzb(bool1, "Location must be specified");
      if (paramFloat < 0.0F) {
        break label64;
      }
    }
    label59:
    label64:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzab.zzb(bool1, "Width must be non-negative");
      return zza(paramLatLng, paramFloat, -1.0F);
      bool1 = false;
      break;
      bool1 = false;
      break label24;
    }
  }
  
  public GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    boolean bool2 = true;
    if (agp == null)
    {
      bool1 = true;
      zzab.zza(bool1, "Position has already been set using positionFromBounds");
      if (paramLatLng == null) {
        break label81;
      }
      bool1 = true;
      label27:
      zzab.zzb(bool1, "Location must be specified");
      if (paramFloat1 < 0.0F) {
        break label87;
      }
      bool1 = true;
      label43:
      zzab.zzb(bool1, "Width must be non-negative");
      if (paramFloat2 < 0.0F) {
        break label93;
      }
    }
    label81:
    label87:
    label93:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzab.zzb(bool1, "Height must be non-negative");
      return zza(paramLatLng, paramFloat1, paramFloat2);
      bool1 = false;
      break;
      bool1 = false;
      break label27;
      bool1 = false;
      break label43;
    }
  }
  
  public GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    if (air == null) {}
    for (boolean bool = true;; bool = false)
    {
      String str = String.valueOf(air);
      zzab.zza(bool, String.valueOf(str).length() + 46 + "Position has already been set using position: " + str);
      agp = paramLatLngBounds;
      return this;
    }
  }
  
  public GroundOverlayOptions transparency(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "Transparency must be in the range [0..1]");
      aiu = paramFloat;
      return this;
    }
  }
  
  public GroundOverlayOptions visible(boolean paramBoolean)
  {
    ain = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public GroundOverlayOptions zIndex(float paramFloat)
  {
    aim = paramFloat;
    return this;
  }
  
  IBinder zzbqg()
  {
    return aiq.zzbpg().asBinder();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.GroundOverlayOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */