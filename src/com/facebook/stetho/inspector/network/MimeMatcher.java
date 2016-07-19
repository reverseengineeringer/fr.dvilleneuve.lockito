package com.facebook.stetho.inspector.network;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class MimeMatcher<T>
{
  private final ArrayList<MimeMatcher<T>.MimeMatcherRule> mRuleMap = new ArrayList();
  
  public void addRule(String paramString, T paramT)
  {
    mRuleMap.add(new MimeMatcherRule(paramString, paramT));
  }
  
  public void clear()
  {
    mRuleMap.clear();
  }
  
  @Nullable
  public T match(String paramString)
  {
    int j = mRuleMap.size();
    int i = 0;
    while (i < j)
    {
      MimeMatcherRule localMimeMatcherRule = (MimeMatcherRule)mRuleMap.get(i);
      if (localMimeMatcherRule.match(paramString)) {
        return (T)localMimeMatcherRule.getResultIfMatched();
      }
      i += 1;
    }
    return null;
  }
  
  @SuppressLint({"BadMethodUse-java.lang.String.length"})
  private class MimeMatcherRule
  {
    private final boolean mHasWildcard;
    private final String mMatchPrefix;
    private final T mResultIfMatched;
    
    public MimeMatcherRule(T paramT)
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
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.MimeMatcher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */