package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzab;

public class StreetViewPanoramaCamera
  implements SafeParcelable
{
  public static final zzj CREATOR = new zzj();
  private StreetViewPanoramaOrientation aiS;
  public final float bearing;
  private final int mVersionCode;
  public final float tilt;
  public final float zoom;
  
  public StreetViewPanoramaCamera(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this(1, paramFloat1, paramFloat2, paramFloat3);
  }
  
  StreetViewPanoramaCamera(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    boolean bool;
    if ((-90.0F <= paramFloat2) && (paramFloat2 <= 90.0F))
    {
      bool = true;
      zzab.zzb(bool, "Tilt needs to be between -90 and 90 inclusive");
      mVersionCode = paramInt;
      float f = paramFloat1;
      if (paramFloat1 <= 0.0D) {
        f = 0.0F;
      }
      zoom = f;
      tilt = (paramFloat2 + 0.0F);
      if (paramFloat3 > 0.0D) {
        break label114;
      }
    }
    label114:
    for (paramFloat1 = paramFloat3 % 360.0F + 360.0F;; paramFloat1 = paramFloat3)
    {
      bearing = (paramFloat1 % 360.0F);
      aiS = new StreetViewPanoramaOrientation.Builder().tilt(paramFloat2).bearing(paramFloat3).build();
      return;
      bool = false;
      break;
    }
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  public static Builder builder(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    return new Builder(paramStreetViewPanoramaCamera);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof StreetViewPanoramaCamera)) {
        return false;
      }
      paramObject = (StreetViewPanoramaCamera)paramObject;
    } while ((Float.floatToIntBits(zoom) == Float.floatToIntBits(zoom)) && (Float.floatToIntBits(tilt) == Float.floatToIntBits(tilt)) && (Float.floatToIntBits(bearing) == Float.floatToIntBits(bearing)));
    return false;
  }
  
  public StreetViewPanoramaOrientation getOrientation()
  {
    return aiS;
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { Float.valueOf(zoom), Float.valueOf(tilt), Float.valueOf(bearing) });
  }
  
  public String toString()
  {
    return zzaa.zzz(this).zzg("zoom", Float.valueOf(zoom)).zzg("tilt", Float.valueOf(tilt)).zzg("bearing", Float.valueOf(bearing)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    public float bearing;
    public float tilt;
    public float zoom;
    
    public Builder() {}
    
    public Builder(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
    {
      zoom = zoom;
      bearing = bearing;
      tilt = tilt;
    }
    
    public Builder bearing(float paramFloat)
    {
      bearing = paramFloat;
      return this;
    }
    
    public StreetViewPanoramaCamera build()
    {
      return new StreetViewPanoramaCamera(zoom, tilt, bearing);
    }
    
    public Builder orientation(StreetViewPanoramaOrientation paramStreetViewPanoramaOrientation)
    {
      tilt = tilt;
      bearing = bearing;
      return this;
    }
    
    public Builder tilt(float paramFloat)
    {
      tilt = paramFloat;
      return this;
    }
    
    public Builder zoom(float paramFloat)
    {
      zoom = paramFloat;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.StreetViewPanoramaCamera
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */