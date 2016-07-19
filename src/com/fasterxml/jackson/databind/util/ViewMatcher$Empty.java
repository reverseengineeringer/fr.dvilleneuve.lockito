package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

final class ViewMatcher$Empty
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

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ViewMatcher.Empty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */