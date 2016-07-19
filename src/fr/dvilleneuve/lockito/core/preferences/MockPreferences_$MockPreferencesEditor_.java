package fr.dvilleneuve.lockito.core.preferences;

import android.content.SharedPreferences;
import org.androidannotations.api.sharedpreferences.EditorHelper;
import org.androidannotations.api.sharedpreferences.FloatPrefEditorField;
import org.androidannotations.api.sharedpreferences.IntPrefEditorField;
import org.androidannotations.api.sharedpreferences.StringPrefEditorField;

public final class MockPreferences_$MockPreferencesEditor_
  extends EditorHelper<MockPreferencesEditor_>
{
  MockPreferences_$MockPreferencesEditor_(SharedPreferences paramSharedPreferences)
  {
    super(paramSharedPreferences);
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

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.preferences.MockPreferences_.MockPreferencesEditor_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */