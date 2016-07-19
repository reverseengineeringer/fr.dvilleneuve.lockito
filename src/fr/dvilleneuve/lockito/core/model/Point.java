package fr.dvilleneuve.lockito.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;

public class Point
  implements Parcelable, Serializable, SimulationConfig
{
  public static Parcelable.Creator<Point> CREATOR = new Parcelable.Creator()
  {
    public Point createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Point(paramAnonymousParcel);
    }
    
    public Point[] newArray(int paramAnonymousInt)
    {
      return new Point[paramAnonymousInt];
    }
  };
  public float accuracy;
  public String address;
  public float altitude;
  public double latitude;
  public double longitude;
  public float speed;
  
  public Point() {}
  
  public Point(double paramDouble1, double paramDouble2)
  {
    this(null, paramDouble1, paramDouble2, 0.0F, 0.0F);
  }
  
  public Point(Parcel paramParcel)
  {
    address = paramParcel.readString();
    latitude = paramParcel.readDouble();
    longitude = paramParcel.readDouble();
    altitude = paramParcel.readFloat();
    speed = paramParcel.readFloat();
    accuracy = paramParcel.readFloat();
  }
  
  public Point(LatLng paramLatLng)
  {
    this(latitude, longitude);
  }
  
  public Point(String paramString, double paramDouble1, double paramDouble2, float paramFloat1, float paramFloat2)
  {
    address = paramString;
    latitude = paramDouble1;
    longitude = paramDouble2;
    speed = paramFloat1;
    accuracy = paramFloat2;
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
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (Point)paramObject;
      if (Float.compare(accuracy, accuracy) != 0) {
        return false;
      }
      if (Double.compare(latitude, latitude) != 0) {
        return false;
      }
      if (Double.compare(longitude, longitude) != 0) {
        return false;
      }
      if (Double.compare(altitude, altitude) != 0) {
        return false;
      }
      if (Float.compare(speed, speed) != 0) {
        return false;
      }
      if (address == null) {
        break;
      }
    } while (address.equals(address));
    for (;;)
    {
      return false;
      if (address == null) {
        break;
      }
    }
  }
  
  public float getAccuracy()
  {
    return accuracy;
  }
  
  public float getAccuracyBase()
  {
    return accuracy;
  }
  
  public float getAccuracyDelta()
  {
    return 0.0F;
  }
  
  public String getAddress()
  {
    return address;
  }
  
  public float getAltitude()
  {
    return altitude;
  }
  
  public double getLatitude()
  {
    return latitude;
  }
  
  public double getLongitude()
  {
    return longitude;
  }
  
  public float getSpeed()
  {
    return speed;
  }
  
  public int hashCode()
  {
    int m = 0;
    int i;
    int n;
    int i1;
    int j;
    if (address != null)
    {
      i = address.hashCode();
      long l = Double.doubleToLongBits(latitude);
      n = (int)(l >>> 32 ^ l);
      l = Double.doubleToLongBits(longitude);
      i1 = (int)(l >>> 32 ^ l);
      if (altitude == 0.0F) {
        break label145;
      }
      j = Float.floatToIntBits(altitude);
      label75:
      if (speed == 0.0F) {
        break label150;
      }
    }
    label145:
    label150:
    for (int k = Float.floatToIntBits(speed);; k = 0)
    {
      if (accuracy != 0.0F) {
        m = Float.floatToIntBits(accuracy);
      }
      return ((((i * 31 + n) * 31 + i1) * 31 + j) * 31 + k) * 31 + m;
      i = 0;
      break;
      j = 0;
      break label75;
    }
  }
  
  public void setAccuracy(float paramFloat)
  {
    accuracy = paramFloat;
  }
  
  public void setAccuracyBase(float paramFloat)
  {
    accuracy = paramFloat;
  }
  
  public void setAccuracyDelta(float paramFloat) {}
  
  public void setAddress(String paramString)
  {
    address = paramString;
  }
  
  public void setAltitude(float paramFloat)
  {
    altitude = paramFloat;
  }
  
  public void setLatitude(double paramDouble)
  {
    latitude = paramDouble;
  }
  
  public void setLongitude(double paramDouble)
  {
    longitude = paramDouble;
  }
  
  public void setSpeed(float paramFloat)
  {
    speed = paramFloat;
  }
  
  public LatLng toLatLng()
  {
    return new LatLng(latitude, longitude);
  }
  
  public String toString()
  {
    return "(" + address + "," + latitude + "," + longitude + "," + altitude + "," + speed + "," + accuracy + ")";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(address);
    paramParcel.writeDouble(latitude);
    paramParcel.writeDouble(longitude);
    paramParcel.writeFloat(altitude);
    paramParcel.writeFloat(speed);
    paramParcel.writeFloat(accuracy);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.Point
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */