package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest
{
  private final Date zzfp;
  private final AdRequest.Gender zzfq;
  private final Set<String> zzfr;
  private final boolean zzfs;
  private final Location zzft;
  
  public MediationAdRequest(Date paramDate, AdRequest.Gender paramGender, Set<String> paramSet, boolean paramBoolean, Location paramLocation)
  {
    zzfp = paramDate;
    zzfq = paramGender;
    zzfr = paramSet;
    zzfs = paramBoolean;
    zzft = paramLocation;
  }
  
  public Integer getAgeInYears()
  {
    if (zzfp != null)
    {
      Calendar localCalendar1 = Calendar.getInstance();
      Calendar localCalendar2 = Calendar.getInstance();
      localCalendar1.setTime(zzfp);
      Integer localInteger2 = Integer.valueOf(localCalendar2.get(1) - localCalendar1.get(1));
      Integer localInteger1;
      if (localCalendar2.get(2) >= localCalendar1.get(2))
      {
        localInteger1 = localInteger2;
        if (localCalendar2.get(2) == localCalendar1.get(2))
        {
          localInteger1 = localInteger2;
          if (localCalendar2.get(5) >= localCalendar1.get(5)) {}
        }
      }
      else
      {
        localInteger1 = Integer.valueOf(localInteger2.intValue() - 1);
      }
      return localInteger1;
    }
    return null;
  }
  
  public Date getBirthday()
  {
    return zzfp;
  }
  
  public AdRequest.Gender getGender()
  {
    return zzfq;
  }
  
  public Set<String> getKeywords()
  {
    return zzfr;
  }
  
  public Location getLocation()
  {
    return zzft;
  }
  
  public boolean isTesting()
  {
    return zzfs;
  }
}

/* Location:
 * Qualified Name:     com.google.ads.mediation.MediationAdRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */