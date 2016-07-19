package com.fasterxml.jackson.databind.introspect;

final class POJOPropertyBuilder$Linked<T>
{
  public final String explicitName;
  public final boolean isMarkedIgnored;
  public final boolean isVisible;
  public final Linked<T> next;
  public final T value;
  
  public POJOPropertyBuilder$Linked(T paramT, Linked<T> paramLinked, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    value = paramT;
    next = paramLinked;
    if (paramString == null) {}
    for (explicitName = null;; explicitName = paramT)
    {
      isVisible = paramBoolean1;
      isMarkedIgnored = paramBoolean2;
      return;
      paramT = paramString;
      if (paramString.length() == 0) {
        paramT = null;
      }
    }
  }
  
  private Linked<T> append(Linked<T> paramLinked)
  {
    if (next == null) {
      return withNext(paramLinked);
    }
    return withNext(next.append(paramLinked));
  }
  
  public String toString()
  {
    String str2 = value.toString() + "[visible=" + isVisible + "]";
    String str1 = str2;
    if (next != null) {
      str1 = str2 + ", " + next.toString();
    }
    return str1;
  }
  
  public Linked<T> trimByVisibility()
  {
    Object localObject;
    if (next == null) {
      localObject = this;
    }
    do
    {
      Linked localLinked;
      do
      {
        return (Linked<T>)localObject;
        localLinked = next.trimByVisibility();
        if (explicitName != null)
        {
          if (explicitName == null) {
            return withNext(null);
          }
          return withNext(localLinked);
        }
        localObject = localLinked;
      } while (explicitName != null);
      if (isVisible == isVisible) {
        return withNext(localLinked);
      }
      localObject = localLinked;
    } while (!isVisible);
    return withNext(null);
  }
  
  public Linked<T> withNext(Linked<T> paramLinked)
  {
    if (paramLinked == next) {
      return this;
    }
    return new Linked(value, paramLinked, explicitName, isVisible, isMarkedIgnored);
  }
  
  public Linked<T> withValue(T paramT)
  {
    if (paramT == value) {
      return this;
    }
    return new Linked(paramT, next, explicitName, isVisible, isMarkedIgnored);
  }
  
  public Linked<T> withoutIgnored()
  {
    if (isMarkedIgnored)
    {
      if (next == null) {
        return null;
      }
      return next.withoutIgnored();
    }
    if (next != null)
    {
      Linked localLinked = next.withoutIgnored();
      if (localLinked != next) {
        return withNext(localLinked);
      }
    }
    return this;
  }
  
  public Linked<T> withoutNonVisible()
  {
    if (next == null) {}
    for (Linked localLinked1 = null;; localLinked1 = next.withoutNonVisible())
    {
      Linked localLinked2 = localLinked1;
      if (isVisible) {
        localLinked2 = withNext(localLinked1);
      }
      return localLinked2;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */