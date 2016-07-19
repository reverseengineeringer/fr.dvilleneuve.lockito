package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.EnumIntegerType;
import com.j256.ormlite.table.DatabaseTable;
import fr.dvilleneuve.lockito.core.model.ItineraryMode;
import fr.dvilleneuve.lockito.core.model.SimulationConfig;
import java.util.Date;

@DatabaseTable
public class ItineraryInfo
  extends AbstractEntity
  implements SimulationConfig
{
  public static Parcelable.Creator<ItineraryInfo> CREATOR = new Parcelable.Creator()
  {
    public ItineraryInfo createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ItineraryInfo(paramAnonymousParcel);
    }
    
    public ItineraryInfo[] newArray(int paramAnonymousInt)
    {
      return new ItineraryInfo[paramAnonymousInt];
    }
  };
  public static final float DEFAULT_ACCURACY_BASE = 40.0F;
  public static final float DEFAULT_ACCURACY_DELTA = 10.0F;
  public static final float DEFAULT_ALTITUDE = 0.0F;
  public static final float DEFAULT_SPEED = 13.89F;
  @DatabaseField
  private float accuracyBase = 40.0F;
  @DatabaseField
  private float accuracyDelta = 10.0F;
  @DatabaseField
  private float altitude = 0.0F;
  @DatabaseField(dataType=DataType.DATE)
  private Date creationDate = new Date();
  @DatabaseField
  private String description;
  @DatabaseField
  private long distance = -1L;
  @DatabaseField
  private long duration = -1L;
  @DatabaseField(foreign=true)
  private Itinerary itinerary = new Itinerary();
  @DatabaseField(persisterClass=EnumIntegerType.class)
  private ItineraryMode itineraryMode = ItineraryMode.GOOGLE_CAR;
  @DatabaseField
  private String name;
  @DatabaseField
  private long playCounter = 0L;
  @DatabaseField
  private float speed = 13.89F;
  private boolean unsavedChanges;
  @DatabaseField(dataType=DataType.DATE)
  private Date updatedDate = new Date();
  
  public ItineraryInfo() {}
  
  public ItineraryInfo(Parcel paramParcel)
  {
    id = paramParcel.readLong();
    name = paramParcel.readString();
    description = paramParcel.readString();
    creationDate = ((Date)paramParcel.readSerializable());
    updatedDate = ((Date)paramParcel.readSerializable());
    itineraryMode = ((ItineraryMode)paramParcel.readSerializable());
    distance = paramParcel.readLong();
    duration = paramParcel.readLong();
    altitude = paramParcel.readFloat();
    speed = paramParcel.readFloat();
    accuracyBase = paramParcel.readFloat();
    accuracyDelta = paramParcel.readFloat();
    playCounter = paramParcel.readLong();
  }
  
  public ItineraryInfo(ItineraryInfo paramItineraryInfo)
  {
    name = paramItineraryInfo.getName();
    description = paramItineraryInfo.getDescription();
    creationDate = paramItineraryInfo.getCreationDate();
    updatedDate = paramItineraryInfo.getUpdatedDate();
    itineraryMode = paramItineraryInfo.getItineraryMode();
    distance = paramItineraryInfo.getDistance();
    duration = paramItineraryInfo.getDuration();
    altitude = paramItineraryInfo.getAltitude();
    speed = paramItineraryInfo.getSpeed();
    accuracyBase = paramItineraryInfo.getAccuracyBase();
    accuracyDelta = paramItineraryInfo.getAccuracyDelta();
    playCounter = paramItineraryInfo.getPlayCounter();
    itinerary = paramItineraryInfo.getItinerary().clone();
  }
  
  public ItineraryInfo(String paramString)
  {
    name = paramString;
  }
  
  public void clearNewChangesState()
  {
    unsavedChanges = false;
  }
  
  public ItineraryInfo clone()
  {
    return new ItineraryInfo(this);
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
  
  public Date getCreationDate()
  {
    return creationDate;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public String getDescription(String paramString)
  {
    if (description != null) {
      paramString = description;
    }
    return paramString;
  }
  
  public long getDistance()
  {
    return distance;
  }
  
  public long getDuration()
  {
    return duration;
  }
  
  public Itinerary getItinerary()
  {
    return itinerary;
  }
  
  public ItineraryMode getItineraryMode()
  {
    return itineraryMode;
  }
  
  public String getName()
  {
    return name;
  }
  
  public long getPlayCounter()
  {
    return playCounter;
  }
  
  public float getSpeed()
  {
    return speed;
  }
  
  public Date getUpdatedDate()
  {
    return updatedDate;
  }
  
  public boolean hasUnsavedChanges()
  {
    return unsavedChanges;
  }
  
  public void incPlayCounter()
  {
    playCounter += 1L;
  }
  
  public void makHasNewChanges()
  {
    unsavedChanges = true;
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
  
  public void setCreationDate(Date paramDate)
  {
    creationDate = paramDate;
  }
  
  public void setDescription(String paramString)
  {
    description = paramString;
  }
  
  public void setDistance(long paramLong)
  {
    distance = paramLong;
  }
  
  public void setDuration(long paramLong)
  {
    duration = paramLong;
  }
  
  public void setItinerary(Itinerary paramItinerary)
  {
    itinerary = paramItinerary;
  }
  
  public void setItineraryMode(ItineraryMode paramItineraryMode)
  {
    itineraryMode = paramItineraryMode;
  }
  
  public void setName(String paramString)
  {
    name = paramString;
  }
  
  public void setPlayCounter(long paramLong)
  {
    playCounter = paramLong;
  }
  
  public void setSpeed(float paramFloat)
  {
    speed = paramFloat;
  }
  
  public void setUpdatedDate(Date paramDate)
  {
    updatedDate = paramDate;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(id);
    paramParcel.writeString(name);
    paramParcel.writeString(description);
    paramParcel.writeSerializable(creationDate);
    paramParcel.writeSerializable(updatedDate);
    paramParcel.writeSerializable(itineraryMode);
    paramParcel.writeLong(distance);
    paramParcel.writeLong(duration);
    paramParcel.writeFloat(altitude);
    paramParcel.writeFloat(speed);
    paramParcel.writeFloat(accuracyBase);
    paramParcel.writeFloat(accuracyDelta);
    paramParcel.writeLong(playCounter);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */