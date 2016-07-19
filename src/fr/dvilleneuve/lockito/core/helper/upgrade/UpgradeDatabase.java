package fr.dvilleneuve.lockito.core.helper.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dvilleneuve.lockito.core.model.ormlite.PointConverter;
import fr.dvilleneuve.lockito.core.model.ormlite.PointListConverter;
import fr.dvilleneuve.lockito.core.utils.ObjectMapperFactory;
import java.io.IOException;
import java.sql.SQLException;

public abstract class UpgradeDatabase
{
  protected static final ObjectMapper OBJECT_MAPPER = ;
  protected static final PointConverter POINT_CONVERTER = PointConverter.getSingleton();
  protected static final PointListConverter POINT_LIST_CONVERTER = PointListConverter.getSingleton();
  protected final Context context;
  protected final int oldVersion;
  
  public UpgradeDatabase(Context paramContext, int paramInt)
  {
    context = paramContext;
    oldVersion = paramInt;
  }
  
  public abstract void doUpgrade(SQLiteDatabase paramSQLiteDatabase)
    throws SQLException, IOException;
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.upgrade.UpgradeDatabase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */