package fr.dvilleneuve.lockito.core.model.entity;

import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public abstract class AbstractEntity
  implements Parcelable, Cloneable
{
  @DatabaseField(generatedId=true)
  protected long id;
  
  public int describeContents()
  {
    return 0;
  }
  
  public long getId()
  {
    return id;
  }
  
  public void setId(long paramLong)
  {
    id = paramLong;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.model.entity.AbstractEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */