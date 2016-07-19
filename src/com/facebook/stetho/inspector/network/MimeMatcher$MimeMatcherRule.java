package com.facebook.stetho.inspector.network;

import android.annotation.SuppressLint;

@SuppressLint({"BadMethodUse-java.lang.String.length"})
class MimeMatcher$MimeMatcherRule
{
  private final boolean mHasWildcard;
  private final String mMatchPrefix;
  private final T mResultIfMatched;
  
  public MimeMatcher$MimeMatcherRule(String paramString, T paramT)
  {
    if (paramT.endsWith("*")) {
      mHasWildcard = true;
    }
    for (mMatchPrefix = paramT.substring(0, paramT.length() - 1); mMatchPrefix.contains("*"); mMatchPrefix = paramT)
    {
      throw new IllegalArgumentException("Multiple wildcards present in rule expression " + paramT);
      mHasWildcard = false;
    }
    Object localObject;
    mResultIfMatched = localObject;
  }
  
  public T getResultIfMatched()
  {
    return (T)mResultIfMatched;
  }
  
  public boolean match(String paramString)
  {
    if (!paramString.startsWith(mMatchPrefix)) {}
    while ((!mHasWildcard) && (paramString.length() != mMatchPrefix.length())) {
      return false;
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.MimeMatcher.MimeMatcherRule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */