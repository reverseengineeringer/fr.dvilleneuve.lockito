package com.facebook.stetho;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Stetho$PluginBuilder<T>
{
  private boolean mFinished;
  private final ArrayList<T> mPlugins = new ArrayList();
  private final Set<String> mProvidedNames = new HashSet();
  private final Set<String> mRemovedNames = new HashSet();
  
  private void throwIfFinished()
  {
    if (mFinished) {
      throw new IllegalStateException("Must not continue to build after finish()");
    }
  }
  
  public Iterable<T> finish()
  {
    mFinished = true;
    return mPlugins;
  }
  
  public void provide(String paramString, T paramT)
  {
    throwIfFinished();
    mPlugins.add(paramT);
    mProvidedNames.add(paramString);
  }
  
  public void provideIfDesired(String paramString, T paramT)
  {
    throwIfFinished();
    if ((!mRemovedNames.contains(paramString)) && (mProvidedNames.add(paramString))) {
      mPlugins.add(paramT);
    }
  }
  
  public void remove(String paramString)
  {
    throwIfFinished();
    mRemovedNames.remove(paramString);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.PluginBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */