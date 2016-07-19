package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import fr.dvilleneuve.lockito.core.model.Bound;
import fr.dvilleneuve.lockito.core.model.Point;
import fr.dvilleneuve.lockito.core.model.ormlite.CollectionConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointListConverter;
import fr.dvilleneuve.lockito.core.utils.CollectionUtils;
import fr.dvilleneuve.lockito.core.utils.GeometryUtils;
import fr.dvilleneuve.lockito.core.utils.collection.ImmutableFlattenList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@DatabaseTable
public class Itinerary
  extends AbstractEntity
{
  public static final String COLUMN_NAME_LEGS = "legs";
  public static Parcelable.Creator<Itinerary> CREATOR = new Parcelable.Creator()
  {
    public Itinerary createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Itinerary(paramAnonymousParcel);
    }
    
    public Itinerary[] newArray(int paramAnonymousInt)
    {
      return new Itinerary[paramAnonymousInt];
    }
  };
  @ForeignCollectionField(columnName="legs", eager=false, orderColumnName="id")
  private Collection<Leg> innerLegs = new ArrayList();
  private List<Leg> legs = new ArrayList();
  @DatabaseField(persisterClass=PointListConverter.class)
  private List<Point> waypoints = new ArrayList();
  
  public Itinerary() {}
  
  public Itinerary(Parcel paramParcel)
  {
    id = paramParcel.readLong();
    paramParcel.readTypedList(waypoints, Point.CREATOR);
    legs = ((List)CollectionConverter.deserialize(paramParcel.readString()));
  }
  
  public Itinerary(Itinerary paramItinerary)
  {
    waypoints = paramItinerary.getWaypoints();
    legs = CollectionUtils.clone(paramItinerary.getLegs());
  }
  
  public void clear()
  {
    waypoints.clear();
    legs.clear();
  }
  
  public Itinerary clone()
  {
    return new Itinerary(this);
  }
  
  public ImmutableFlattenList<Leg, Point> getAllPoints()
  {
    new ImmutableFlattenList(legs)
    {
      public List<Point> getSubList(Leg paramAnonymousLeg)
      {
        return paramAnonymousLeg.getPoints();
      }
    };
  }
  
  public Bound getBound()
  {
    Bound localBound = new Bound();
    Iterator localIterator = legs.iterator();
    while (localIterator.hasNext()) {
      GeometryUtils.expandBound(localBound, ((Leg)localIterator.next()).getBound());
    }
    return localBound;
  }
  
  public Leg getLeg(int paramInt)
  {
    return (Leg)legs.get(paramInt);
  }
  
  public List<Leg> getLegs()
  {
    return legs;
  }
  
  public List<Point> getWaypoints()
  {
    return waypoints;
  }
  
  public boolean isSinglePoint()
  {
    return waypoints.size() == 1;
  }
  
  public void postLoad()
  {
    legs.clear();
    legs.addAll(innerLegs);
  }
  
  public void preSave()
  {
    innerLegs.clear();
    innerLegs.addAll(legs);
  }
  
  protected void setLegs(List<Leg> paramList)
  {
    legs = paramList;
  }
  
  public void setWaypoints(List<Point> paramList)
  {
    waypoints = paramList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(id);
    paramParcel.writeTypedList(waypoints);
    paramParcel.writeString(CollectionConverter.serialize(legs));
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.Itinerary
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */