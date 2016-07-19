package org.androidannotations.api.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OnViewChangedNotifier
{
  private static OnViewChangedNotifier currentNotifier;
  private final List<OnViewChangedListener> listeners = new LinkedList();
  
  public static void registerOnViewChangedListener(OnViewChangedListener paramOnViewChangedListener)
  {
    if (currentNotifier != null) {
      currentNotifierlisteners.add(paramOnViewChangedListener);
    }
  }
  
  public static OnViewChangedNotifier replaceNotifier(OnViewChangedNotifier paramOnViewChangedNotifier)
  {
    OnViewChangedNotifier localOnViewChangedNotifier = currentNotifier;
    currentNotifier = paramOnViewChangedNotifier;
    return localOnViewChangedNotifier;
  }
  
  public void notifyViewChanged(HasViews paramHasViews)
  {
    Iterator localIterator = listeners.iterator();
    while (localIterator.hasNext()) {
      ((OnViewChangedListener)localIterator.next()).onViewChanged(paramHasViews);
    }
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.view.OnViewChangedNotifier
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */