package fr.dvilleneuve.lockito.core.manager;

import de.greenrobot.event.EventBus;
import org.androidannotations.annotations.EBean;

@EBean
public class BusManager
{
  public void post(Object paramObject)
  {
    EventBus.getDefault().post(paramObject);
  }
  
  public void register(Object paramObject)
  {
    EventBus.getDefault().register(paramObject);
  }
  
  public void unregister(Object paramObject)
  {
    EventBus.getDefault().unregister(paramObject);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.BusManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */