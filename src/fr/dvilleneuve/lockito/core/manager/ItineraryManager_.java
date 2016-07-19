package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;
import android.util.Log;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import fr.dvilleneuve.lockito.core.RestClient_;
import fr.dvilleneuve.lockito.core.dto.converter.GItineraryConverter_;
import fr.dvilleneuve.lockito.core.helper.DatabaseHelper;
import fr.dvilleneuve.lockito.core.model.entity.Itinerary;
import fr.dvilleneuve.lockito.core.model.entity.ItineraryInfo;
import fr.dvilleneuve.lockito.core.model.entity.Leg;
import java.sql.SQLException;

public final class ItineraryManager_
  extends ItineraryManager
{
  private Context context_;
  private DatabaseHelper databaseHelper_;
  
  private ItineraryManager_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static ItineraryManager_ getInstance_(Context paramContext)
  {
    return new ItineraryManager_(paramContext);
  }
  
  private void init_()
  {
    restClient = new RestClient_(context_);
    databaseHelper_ = ((DatabaseHelper)OpenHelperManager.getHelper(context_, DatabaseHelper.class));
    try
    {
      itineraryInfoDao = databaseHelper_.getDao(ItineraryInfo.class);
    }
    catch (SQLException localSQLException2)
    {
      try
      {
        legDao = databaseHelper_.getDao(Leg.class);
      }
      catch (SQLException localSQLException2)
      {
        try
        {
          for (;;)
          {
            itineraryDao = databaseHelper_.getDao(Itinerary.class);
            context = context_;
            gItineraryConverter = GItineraryConverter_.getInstance_(context_);
            return;
            localSQLException1 = localSQLException1;
            Log.e("ItineraryManager_", "Could not create DAO itineraryInfoDao", localSQLException1);
            continue;
            localSQLException2 = localSQLException2;
            Log.e("ItineraryManager_", "Could not create DAO legDao", localSQLException2);
          }
        }
        catch (SQLException localSQLException3)
        {
          for (;;)
          {
            Log.e("ItineraryManager_", "Could not create DAO itineraryDao", localSQLException3);
          }
        }
      }
    }
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.ItineraryManager_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */