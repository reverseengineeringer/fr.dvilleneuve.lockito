package fr.dvilleneuve.lockito.core.manager;

import android.os.Vibrator;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

@EBean
public class VibratorManager
{
  @SystemService
  Vibrator vibrator;
  
  public void vibrateAtDragStrat()
  {
    vibrator.vibrate(80L);
  }
  
  public void vibrateAtMarkerAdded()
  {
    vibrator.vibrate(80L);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.VibratorManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */