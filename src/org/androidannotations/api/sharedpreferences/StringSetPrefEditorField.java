package org.androidannotations.api.sharedpreferences;

import java.util.Set;

public final class StringSetPrefEditorField<T extends EditorHelper<T>>
  extends AbstractPrefEditorField<T>
{
  StringSetPrefEditorField(T paramT, String paramString)
  {
    super(paramT, paramString);
  }
  
  public T put(Set<String> paramSet)
  {
    SharedPreferencesCompat.putStringSet(editorHelper.getEditor(), key, paramSet);
    return editorHelper;
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.sharedpreferences.StringSetPrefEditorField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */