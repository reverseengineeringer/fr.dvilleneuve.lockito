package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public abstract class AbstractPrefEditorField<T extends EditorHelper<T>>
{
  protected final T editorHelper;
  protected final String key;
  
  public AbstractPrefEditorField(T paramT, String paramString)
  {
    editorHelper = paramT;
    key = paramString;
  }
  
  public final T remove()
  {
    editorHelper.getEditor().remove(key);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.AbstractPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */