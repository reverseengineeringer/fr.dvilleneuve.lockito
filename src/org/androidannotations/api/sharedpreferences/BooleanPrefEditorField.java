package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class BooleanPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  BooleanPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(boolean paramBoolean)
  {
    editorHelper.getEditor().putBoolean(key, paramBoolean);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.BooleanPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */