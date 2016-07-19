package fr.dvilleneuve.lockito.core.model;

import java.io.Serializable;

public class Bound
  implements Serializable
{
  public double maxLatitude = -100000.0D;
  public double maxLongitude = -100000.0D;
  public double minLatitude = 100000.0D;
  public double minLongitude = 100000.0D;
  
  public Bound() {}
  
  public Bound(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    minLatitude = paramDouble1;
    minLongitude = paramDouble2;
    maxLatitude = paramDouble3;
    maxLongitude = paramDouble4;
  }
  
  public void clear()
  {
    minLongitude = 100000.0D;
    minLatitude = 100000.0D;
    maxLongitude = -100000.0D;
    maxLatitude = -100000.0D;
  }
  
  public boolean contains(Point paramPoint)
  {
    return (paramPoint.getLatitude() >= minLatitude) && (paramPoint.getLatitude() <= maxLatitude) && (paramPoint.getLongitude() >= minLongitude) && (paramPoint.getLongitude() <= maxLongitude);
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
      paramObject = (Bound)paramObject;
      if (Double.compare(maxLatitude, maxLatitude) != 0) {
        return false;
      }
      if (Double.compare(maxLongitude, maxLongitude) != 0) {
        return false;
      }
      if (Double.compare(minLatitude, minLatitude) != 0) {
        return false;
      }
    } while (Double.compare(minLongitude, minLongitude) == 0);
    return false;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(minLatitude);
    int i = (int)(l >>> 32 ^ l);
    l = Double.doubleToLongBits(maxLatitude);
    int j = (int)(l >>> 32 ^ l);
    l = Double.doubleToLongBits(minLongitude);
    int k = (int)(l >>> 32 ^ l);
    l = Double.doubleToLongBits(maxLongitude);
    return ((i * 31 + j) * 31 + k) * 31 + (int)(l >>> 32 ^ l);
  }
  
  public String toString()
  {
    return "Bound{minLatitude=" + minLatitude + ", minLongitude=" + minLongitude + ", maxLatitude=" + maxLatitude + ", maxLongitude=" + maxLongitude + '}';
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.Bound
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */