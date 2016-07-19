package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

final class ViewMatcher$Single
  extends ViewMatcher
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final Class<?> _view;
  
  public ViewMatcher$Single(Class<?> paramClass)
  {
    _view = paramClass;
  }
  
  public boolean isVisibleForView(Class<?> paramClass)
  {
    return (paramClass == _view) || (_view.isAssignableFrom(paramClass));
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ViewMatcher.Single
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */