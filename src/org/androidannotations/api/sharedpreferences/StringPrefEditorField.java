package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class StringPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  StringPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(String paramString)
  {
    editorHelper.getEditor().putString(key, paramString);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.StringPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */