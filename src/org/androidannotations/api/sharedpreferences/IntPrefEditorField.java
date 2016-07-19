package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences.Editor;

public final class IntPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  IntPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(int paramInt)
  {
    editorHelper.getEditor().putInt(key, paramInt);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.IntPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */