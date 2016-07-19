package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class EditorHelper<T extends EditorHelper<T>>
{
  private final SharedPreferences.Editor editor;
  
  public EditorHelper(SharedPreferences paramSharedPreferences)
  {
    editor = paramSharedPreferences.edit();
  }
  
  private T cast()
  {
    return this;
  }
  
  public final void apply()
  {
    SharedPreferencesCompat.apply(editor);
  }
  
  protected BooleanPrefEditorField<T> booleanField(String paramString)
  {
    return new BooleanPrefEditorField(cast(), paramString);
  }
  
  public final T clear()
  {
    editor.clear();
    return cast();
  }
  
  protected FloatPrefEditorField<T> floatField(String paramString)
  {
    return new FloatPrefEditorField(cast(), paramString);
  }
  
  protected SharedPreferences.Editor getEditor()
  {
    return editor;
  }
  
  protected IntPrefEditorField<T> intField(String paramString)
  {
    return new IntPrefEditorField(cast(), paramString);
  }
  
  protected LongPrefEditorField<T> longField(String paramString)
  {
    return new LongPrefEditorField(cast(), paramString);
  }
  
  protected StringPrefEditorField<T> stringField(String paramString)
  {
    return new StringPrefEditorField(cast(), paramString);
  }
  
  protected StringSetPrefEditorField<T> stringSetField(String paramString)
  {
    return new StringSetPrefEditorField(cast(), paramString);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.EditorHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */