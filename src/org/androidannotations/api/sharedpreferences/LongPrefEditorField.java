package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class LongPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  LongPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(long paramLong)
  {
    editorHelper.getEditor().putLong(key, paramLong);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.LongPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */