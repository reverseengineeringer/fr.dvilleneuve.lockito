package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzab;

public final class LatLngBounds
  implements SafeParcelable
{
  public static final zzd CREATOR = new zzd();
  private final int mVersionCode;
  public final LatLng northeast;
  public final LatLng southwest;
  
  LatLngBounds(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2)
  {
    zzab.zzb(paramLatLng1, "null southwest");
    zzab.zzb(paramLatLng2, "null northeast");
    if (latitude >= latitude) {}
    for (boolean bool = true;; bool = false)
    {
      zzab.zzb(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(latitude), Double.valueOf(latitude) });
      mVersionCode = paramInt;
      southwest = paramLatLng1;
      northeast = paramLatLng2;
      return;
    }
  }
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    this(1, paramLatLng1, paramLatLng2);
  }
  
  public static Builder builder()
  {
    return new Builder();
  }
  
  private static double zzb(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 - paramDouble2 + 360.0D) % 360.0D;
  }
  
  private static double zzc(double paramDouble1, double paramDouble2)
  {
    return (paramDouble2 - paramDouble1 + 360.0D) % 360.0D;
  }
  
  private boolean zzh(double paramDouble)
  {
    return (southwest.latitude <= paramDouble) && (paramDouble <= northeast.latitude);
  }
  
  private boolean zzi(double paramDouble)
  {
    boolean bool = false;
    if (southwest.longitude <= northeast.longitude) {
      return (southwest.longitude <= paramDouble) && (paramDouble <= northeast.longitude);
    }
    if ((southwest.longitude <= paramDouble) || (paramDouble <= northeast.longitude)) {
      bool = true;
    }
    return bool;
  }
  
  public boolean contains(LatLng paramLatLng)
  {
    return (zzh(latitude)) && (zzi(longitude));
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
      if (!(paramObject instanceof LatLngBounds)) {
        return false;
      }
      paramObject = (LatLngBounds)paramObject;
    } while ((southwest.equals(southwest)) && (northeast.equals(northeast)));
    return false;
  }
  
  public LatLng getCenter()
  {
    double d2 = (southwest.latitude + northeast.latitude) / 2.0D;
    double d1 = northeast.longitude;
    double d3 = southwest.longitude;
    if (d3 <= d1) {}
    for (d1 = (d1 + d3) / 2.0D;; d1 = (d1 + 360.0D + d3) / 2.0D) {
      return new LatLng(d2, d1);
    }
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { southwest, northeast });
  }
  
  public LatLngBounds including(LatLng paramLatLng)
  {
    double d4 = Math.min(southwest.latitude, latitude);
    double d5 = Math.max(northeast.latitude, latitude);
    double d2 = northeast.longitude;
    double d3 = southwest.longitude;
    double d1 = longitude;
    if (!zzi(d1)) {
      if (zzb(d3, d1) >= zzc(d2, d1)) {}
    }
    for (;;)
    {
      return new LatLngBounds(new LatLng(d4, d1), new LatLng(d5, d2));
      d2 = d1;
      d1 = d3;
      continue;
      d1 = d3;
    }
  }
  
  public String toString()
  {
    return zzaa.zzz(this).zzg("southwest", southwest).zzg("northeast", northeast).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private double aiA = Double.NEGATIVE_INFINITY;
    private double aiB = NaN.0D;
    private double aiC = NaN.0D;
    private double aiz = Double.POSITIVE_INFINITY;
    
    private boolean zzi(double paramDouble)
    {
      boolean bool = false;
      if (aiB <= aiC) {
        return (aiB <= paramDouble) && (paramDouble <= aiC);
      }
      if ((aiB <= paramDouble) || (paramDouble <= aiC)) {
        bool = true;
      }
      return bool;
    }
    
    public LatLngBounds build()
    {
      if (!Double.isNaN(aiB)) {}
      for (boolean bool = true;; bool = false)
      {
        zzab.zza(bool, "no included points");
        return new LatLngBounds(new LatLng(aiz, aiB), new LatLng(aiA, aiC));
      }
    }
    
    public Builder include(LatLng paramLatLng)
    {
      aiz = Math.min(aiz, latitude);
      aiA = Math.max(aiA, latitude);
      double d = longitude;
      if (Double.isNaN(aiB))
      {
        aiB = d;
        aiC = d;
      }
      while (zzi(d)) {
        return this;
      }
      if (LatLngBounds.zzd(aiB, d) < LatLngBounds.zze(aiC, d))
      {
        aiB = d;
        return this;
      }
      aiC = d;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.maps.model.LatLngBounds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */