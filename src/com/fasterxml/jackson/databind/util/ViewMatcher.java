package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class ViewMatcher
{
  public static ViewMatcher construct(Class<?>[] paramArrayOfClass)
  {
    if (paramArrayOfClass == null) {
      return Empty.instance;
    }
    switch (paramArrayOfClass.length)
    {
    default: 
      return new Multi(paramArrayOfClass);
    case 0: 
      return Empty.instance;
    }
    return new Single(paramArrayOfClass[0]);
  }
  
  public abstract boolean isVisibleForView(Class<?> paramClass);
  
  private static final class Empty
    extends ViewMatcher
    implements Serializable
  {
    static final Empty instance = new Empty();
    private static final long serialVersionUID = 1L;
    
    public boolean isVisibleForView(Class<?> paramClass)
    {
      return false;
    }
  }
  
  private static final class Multi
    extends ViewMatcher
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final Class<?>[] _views;
    
    public Multi(Class<?>[] paramArrayOfClass)
    {
      _views = paramArrayOfClass;
    }
    
    public boolean isVisibleForView(Class<?> paramClass)
    {
      boolean bool2 = false;
      int j = _views.length;
      int i = 0;
      for (;;)
      {
        boolean bool1 = bool2;
        if (i < j)
        {
          Class localClass = _views[i];
          if ((paramClass == localClass) || (localClass.isAssignableFrom(paramClass))) {
            bool1 = true;
          }
        }
        else
        {
          return bool1;
        }
        i += 1;
      }
    }
  }
  
  private static final class Single
    extends ViewMatcher
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final Class<?> _view;
    
    public Single(Class<?> paramClass)
    {
      _view = paramClass;
    }
    
    public boolean isVisibleForView(Class<?> paramClass)
    {
      return (paramClass == _view) || (_view.isAssignableFrom(paramClass));
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ViewMatcher
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */