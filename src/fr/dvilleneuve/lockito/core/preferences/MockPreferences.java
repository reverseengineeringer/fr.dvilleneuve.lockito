package fr.dvilleneuve.lockito.core.preferences;

import org.androidannotations.annotations.sharedpreferences.SharedPref;
import org.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@Deprecated
@SharedPref(SharedPref.Scope.APPLICATION_DEFAULT)
public abstract interface MockPreferences
{
  public abstract int accuracyBase();
  
  public abstract int accuracyDelta();
  
  public abstract float destinationX();
  
  public abstract float destinationY();
  
  public abstract float originX();
  
  public abstract float originY();
  
  public abstract int speed();
  
  public abstract String waypoints();
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.preferences.MockPreferences
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */