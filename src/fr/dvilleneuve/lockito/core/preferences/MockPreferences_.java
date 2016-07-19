package fr.dvilleneuve.lockito.core.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.androidannotations.api.sharedpreferences.EditorHelper;
import org.androidannotations.api.sharedpreferences.FloatPrefEditorField;
import org.androidannotations.api.sharedpreferences.FloatPrefField;
import org.androidannotations.api.sharedpreferences.IntPrefEditorField;
import org.androidannotations.api.sharedpreferences.IntPrefField;
import org.androidannotations.api.sharedpreferences.SharedPreferencesHelper;
import org.androidannotations.api.sharedpreferences.StringPrefEditorField;
import org.androidannotations.api.sharedpreferences.StringPrefField;

@Deprecated
public final class MockPreferences_
  extends SharedPreferencesHelper
{
  public MockPreferences_(Context paramContext)
  {
    super(PreferenceManager.getDefaultSharedPreferences(paramContext));
  }
  
  public IntPrefField accuracyBase()
  {
    return intField("accuracyBase", 0);
  }
  
  public IntPrefField accuracyDelta()
  {
    return intField("accuracyDelta", 0);
  }
  
  public FloatPrefField destinationX()
  {
    return floatField("destinationX", 0.0F);
  }
  
  public FloatPrefField destinationY()
  {
    return floatField("destinationY", 0.0F);
  }
  
  public MockPreferencesEditor_ edit()
  {
    return new MockPreferencesEditor_(getSharedPreferences());
  }
  
  public FloatPrefField originX()
  {
    return floatField("originX", 0.0F);
  }
  
  public FloatPrefField originY()
  {
    return floatField("originY", 0.0F);
  }
  
  public IntPrefField speed()
  {
    return intField("speed", 0);
  }
  
  public StringPrefField waypoints()
  {
    return stringField("waypoints", "");
  }
  
  public static final class MockPreferencesEditor_
    extends EditorHelper<MockPreferencesEditor_>
  {
    MockPreferencesEditor_(SharedPreferences paramSharedPreferences)
    {
      super();
    }
    
    public IntPrefEditorField<MockPreferencesEditor_> accuracyBase()
    {
      return intField("accuracyBase");
    }
    
    public IntPrefEditorField<MockPreferencesEditor_> accuracyDelta()
    {
      return intField("accuracyDelta");
    }
    
    public FloatPrefEditorField<MockPreferencesEditor_> destinationX()
    {
      return floatField("destinationX");
    }
    
    public FloatPrefEditorField<MockPreferencesEditor_> destinationY()
    {
      return floatField("destinationY");
    }
    
    public FloatPrefEditorField<MockPreferencesEditor_> originX()
    {
      return floatField("originX");
    }
    
    public FloatPrefEditorField<MockPreferencesEditor_> originY()
    {
      return floatField("originY");
    }
    
    public IntPrefEditorField<MockPreferencesEditor_> speed()
    {
      return intField("speed");
    }
    
    public StringPrefEditorField<MockPreferencesEditor_> waypoints()
    {
      return stringField("waypoints");
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.preferences.MockPreferences_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */