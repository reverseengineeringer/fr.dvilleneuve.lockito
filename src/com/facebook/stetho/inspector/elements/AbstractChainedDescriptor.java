package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

public abstract class AbstractChainedDescriptor<E>
  extends Descriptor
  implements ChainedDescriptor
{
  private Descriptor mSuper;
  
  public final void getAttributes(Object paramObject, AttributeAccumulator paramAttributeAccumulator)
  {
    mSuper.getAttributes(paramObject, paramAttributeAccumulator);
    onGetAttributes(paramObject, paramAttributeAccumulator);
  }
  
  public final void getChildren(Object paramObject, Accumulator<Object> paramAccumulator)
  {
    mSuper.getChildren(paramObject, paramAccumulator);
    onGetChildren(paramObject, paramAccumulator);
  }
  
  public final String getLocalName(Object paramObject)
  {
    return onGetLocalName(paramObject);
  }
  
  public final String getNodeName(Object paramObject)
  {
    return onGetNodeName(paramObject);
  }
  
  public final NodeType getNodeType(Object paramObject)
  {
    return onGetNodeType(paramObject);
  }
  
  public final String getNodeValue(Object paramObject)
  {
    return onGetNodeValue(paramObject);
  }
  
  public final void getStyles(Object paramObject, StyleAccumulator paramStyleAccumulator)
  {
    mSuper.getStyles(paramObject, paramStyleAccumulator);
    onGetStyles(paramObject, paramStyleAccumulator);
  }
  
  final Descriptor getSuper()
  {
    return mSuper;
  }
  
  public final void hook(Object paramObject)
  {
    verifyThreadAccess();
    mSuper.hook(paramObject);
    onHook(paramObject);
  }
  
  protected void onGetAttributes(E paramE, AttributeAccumulator paramAttributeAccumulator) {}
  
  protected void onGetChildren(E paramE, Accumulator<Object> paramAccumulator) {}
  
  protected String onGetLocalName(E paramE)
  {
    return mSuper.getLocalName(paramE);
  }
  
  protected String onGetNodeName(E paramE)
  {
    return mSuper.getNodeName(paramE);
  }
  
  protected NodeType onGetNodeType(E paramE)
  {
    return mSuper.getNodeType(paramE);
  }
  
  @Nullable
  public String onGetNodeValue(E paramE)
  {
    return mSuper.getNodeValue(paramE);
  }
  
  protected void onGetStyles(E paramE, StyleAccumulator paramStyleAccumulator) {}
  
  protected void onHook(E paramE) {}
  
  protected void onSetAttributesAsText(E paramE, String paramString)
  {
    mSuper.setAttributesAsText(paramE, paramString);
  }
  
  protected void onUnhook(E paramE) {}
  
  public final void setAttributesAsText(Object paramObject, String paramString)
  {
    onSetAttributesAsText(paramObject, paramString);
  }
  
  public void setSuper(Descriptor paramDescriptor)
  {
    Util.throwIfNull(paramDescriptor);
    if (paramDescriptor != mSuper)
    {
      if (mSuper != null) {
        throw new IllegalStateException();
      }
      mSuper = paramDescriptor;
    }
  }
  
  public final void unhook(Object paramObject)
  {
    verifyThreadAccess();
    onUnhook(paramObject);
    mSuper.unhook(paramObject);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.AbstractChainedDescriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */