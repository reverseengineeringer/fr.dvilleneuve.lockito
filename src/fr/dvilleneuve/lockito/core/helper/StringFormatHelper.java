package fr.dvilleneuve.lockito.core.helper;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import fr.dvilleneuve.lockito.core.UnitSystem;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager;
import fr.dvilleneuve.lockito.core.model.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

@EBean
public class StringFormatHelper
{
  private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance(3);
  public static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder().appendHours().appendSeparatorIfFieldsBefore("h").appendMinutes().appendSeparatorIfFieldsBefore("m").appendSeconds().appendSeparatorIfFieldsBefore("s").toFormatter();
  @RootContext
  Context context;
  @Bean
  PreferenceManager preferenceManager;
  
  public String formatAccuracy(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    Object localObject = preferenceManager.getUnitSystem();
    paramFloat1 = ((UnitSystem)localObject).fromMeters(paramFloat1);
    localObject = ((UnitSystem)localObject).formatDistance(paramFloat2);
    String str = String.format(Locale.getDefault(), "%.1f ± %s", new Object[] { Float.valueOf(paramFloat1), localObject });
    localObject = str;
    if (paramBoolean) {
      localObject = "{fa-bullseye} " + str;
    }
    return (String)localObject;
  }
  
  public String formatCounter(long paramLong, boolean paramBoolean)
  {
    String str2 = context.getString(2131165311, new Object[] { Long.valueOf(paramLong) });
    String str1 = str2;
    if (paramBoolean) {
      str1 = "{fa-play} " + str2;
    }
    return str1;
  }
  
  public String formatDistance(long paramLong)
  {
    if (paramLong <= 0L) {
      return context.getString(2131165319);
    }
    return preferenceManager.getUnitSystem().formatDistance((float)paramLong);
  }
  
  public String formatDistance(long paramLong, boolean paramBoolean)
  {
    String str2 = formatDistance(paramLong);
    String str1 = str2;
    if (paramBoolean) {
      str1 = "{fa-road} " + str2;
    }
    return str1;
  }
  
  public String formatDuration(long paramLong)
  {
    if (paramLong <= 0L) {
      return context.getString(2131165319);
    }
    Period localPeriod = new Period((int)(paramLong / 3600L), (int)(paramLong % 3600L / 60L), (int)(paramLong % 60L), 0);
    return PERIOD_FORMATTER.print(localPeriod);
  }
  
  public String formatDuration(long paramLong, boolean paramBoolean)
  {
    String str2 = formatDuration(paramLong);
    String str1 = str2;
    if (paramBoolean) {
      str1 = "{fa-clock-o} " + str2;
    }
    return str1;
  }
  
  public String formatLocation(Location paramLocation)
  {
    return "Location: " + Location.convert(paramLocation.getLatitude(), 0) + ", " + Location.convert(paramLocation.getLongitude(), 0) + " (± " + formatDistance(paramLocation.getAccuracy()) + ")\nSpeed: " + formatSpeed((int)paramLocation.getSpeed(), false) + "\nAltitude: " + formatDistance(paramLocation.getAltitude(), false);
  }
  
  public String formatLocation(LatLng paramLatLng)
  {
    return Location.convert(latitude, 0) + ", " + Location.convert(longitude, 0);
  }
  
  public String formatLocation(Point paramPoint)
  {
    return Location.convert(paramPoint.getLatitude(), 0) + ", " + Location.convert(paramPoint.getLongitude(), 0);
  }
  
  public String formatSpeed(float paramFloat, boolean paramBoolean)
  {
    Object localObject = preferenceManager.getUnitSystem();
    String str = ((UnitSystem)localObject).formatSpeed(((UnitSystem)localObject).fromMetersSeconds(paramFloat));
    localObject = str;
    if (paramBoolean) {
      localObject = "{fa-tachometer} " + str;
    }
    return (String)localObject;
  }
  
  public String formatUpdatedDate(Date paramDate, boolean paramBoolean)
  {
    String str = context.getString(2131165312, new Object[] { DATE_FORMAT.format(paramDate) });
    paramDate = str;
    if (paramBoolean) {
      paramDate = "{fa-pencil-square-o} " + str;
    }
    return paramDate;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.StringFormatHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */