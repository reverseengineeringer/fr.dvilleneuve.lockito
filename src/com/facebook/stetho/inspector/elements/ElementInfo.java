package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.Util;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class ElementInfo
{
  public final List<Object> children;
  public final Object element;
  public final Object parentElement;
  
  public ElementInfo(Object paramObject1, Object paramObject2, List<Object> paramList)
  {
    element = Util.throwIfNull(paramObject1);
    parentElement = paramObject2;
    children = ListUtil.copyToImmutableList(paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof ElementInfo)) {
        break;
      }
      paramObject = (ElementInfo)paramObject;
    } while ((element == element) && (parentElement == parentElement) && (ListUtil.identityEquals(children, children)));
    return false;
    return false;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.ElementInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */