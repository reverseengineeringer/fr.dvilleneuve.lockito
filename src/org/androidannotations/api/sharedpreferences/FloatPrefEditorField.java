package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class FloatPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  FloatPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(float paramFloat)
  {
    editorHelper.getEditor().putFloat(key, paramFloat);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.FloatPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */