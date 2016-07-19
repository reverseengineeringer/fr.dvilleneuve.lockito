package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import fr.dvilleneuve.lockito.core.model.Bound;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.SimulationConfig;
import fr.dvilleneuve.lockito.core.model.ormlite.PointConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointGeometricListConverter;
import fr.dvilleneuve.lockito.core.utils.collection.GeometricList;
import java.util.Collection;

@DatabaseTable
public class Leg
  extends AbstractEntity
  implements SimulationConfig
{
  public static Parcelable.Creator<Leg> CREATOR = new Parcelable.Creator()
  {
    public Leg createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Leg(paramAnonymousParcel);
    }
    
    public Leg[] newArray(int paramAnonymousInt)
    {
      return new Leg[paramAnonymousInt];
    }
  };
  public static final String ITINERARY_ID = "itinerary_id";
  @DatabaseField
  private float accuracyBase;
  @DatabaseField
  private float accuracyDelta;
  @DatabaseField
  private float altitude;
  @DatabaseField
  private long distance = -1L;
  @DatabaseField(persisterClass=PointConverter.class)
  private Point endWaypoint;
  @DatabaseField(columnName="itinerary_id", foreign=true)
  private Itinerary itinerary;
  @DatabaseField(persisterClass=PointGeometricListConverter.class)
  private GeometricList<Point> points = new GeometricList();
  @DatabaseField
  private float speed;
  @DatabaseField(persisterClass=PointConverter.class)
  private Point startWaypoint;
  
  public Leg() {}
  
  public Leg(Parcel paramParcel)
  {
    id = paramParcel.readInt();
    startWaypoint = ((Point)paramParcel.readSerializable());
    endWaypoint = ((Point)paramParcel.readSerializable());
    paramParcel.readTypedList(points, Point.CREATOR);
    distance = paramParcel.readLong();
    altitude = paramParcel.readFloat();
    speed = paramParcel.readFloat();
    accuracyBase = paramParcel.readFloat();
    accuracyDelta = paramParcel.readFloat();
  }
  
  public Leg(Leg paramLeg)
  {
    startWaypoint = paramLeg.getStartWaypoint();
    endWaypoint = paramLeg.getEndWaypoint();
    points = paramLeg.getPoints();
    distance = paramLeg.getDistance();
    altitude = paramLeg.getAltitude();
    speed = paramLeg.getSpeed();
    accuracyBase = paramLeg.getAccuracyBase();
    accuracyDelta = paramLeg.getAccuracyDelta();
  }
  
  public void add(Point paramPoint)
  {
    points.add(paramPoint);
  }
  
  public void addAll(Collection<Point> paramCollection)
  {
    points.addAll(paramCollection);
  }
  
  public Leg clone()
  {
    return new Leg(this);
  }
  
  public Point get(int paramInt)
  {
    return (Point)points.get(paramInt);
  }
  
  public float getAccuracyBase()
  {
    return accuracyBase;
  }
  
  public float getAccuracyDelta()
  {
    return accuracyDelta;
  }
  
  public float getAltitude()
  {
    return altitude;
  }
  
  public Bound getBound()
  {
    return points.getBound();
  }
  
  public long getDistance()
  {
    return distance;
  }
  
  public Point getEndWaypoint()
  {
    return endWaypoint;
  }
  
  public Itinerary getItinerary()
  {
    return itinerary;
  }
  
  public GeometricList<Point> getPoints()
  {
    return points;
  }
  
  public float getSpeed()
  {
    return speed;
  }
  
  public Point getStartWaypoint()
  {
    return startWaypoint;
  }
  
  public void setAccuracyBase(float paramFloat)
  {
    accuracyBase = paramFloat;
  }
  
  public void setAccuracyDelta(float paramFloat)
  {
    accuracyDelta = paramFloat;
  }
  
  public void setAltitude(float paramFloat)
  {
    altitude = paramFloat;
  }
  
  public void setDistance(long paramLong)
  {
    distance = paramLong;
  }
  
  public void setEndWaypoint(Point paramPoint)
  {
    endWaypoint = paramPoint;
  }
  
  public void setItinerary(Itinerary paramItinerary)
  {
    itinerary = paramItinerary;
  }
  
  public void setPoints(GeometricList<Point> paramGeometricList)
  {
    points = paramGeometricList;
  }
  
  public void setSpeed(float paramFloat)
  {
    speed = paramFloat;
  }
  
  public void setStartWaypoint(Point paramPoint)
  {
    startWaypoint = paramPoint;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(id);
    paramParcel.writeSerializable(startWaypoint);
    paramParcel.writeSerializable(endWaypoint);
    paramParcel.writeTypedList(points);
    paramParcel.writeLong(distance);
    paramParcel.writeFloat(altitude);
    paramParcel.writeFloat(speed);
    paramParcel.writeFloat(accuracyBase);
    paramParcel.writeFloat(accuracyDelta);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.Leg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */