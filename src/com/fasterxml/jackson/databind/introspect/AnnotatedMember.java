package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Collections;

public abstract class AnnotatedMember
  extends Annotated
  implements Serializable
{
  private static final long serialVersionUID = 7364428299211355871L;
  protected final transient AnnotationMap _annotations;
  
  protected AnnotatedMember(AnnotationMap paramAnnotationMap)
  {
    _annotations = paramAnnotationMap;
  }
  
  public final void addIfNotPresent(Annotation paramAnnotation)
  {
    _annotations.addIfNotPresent(paramAnnotation);
  }
  
  public final void addOrOverride(Annotation paramAnnotation)
  {
    _annotations.add(paramAnnotation);
  }
  
  public Iterable<Annotation> annotations()
  {
    if (_annotations == null) {
      return Collections.emptyList();
    }
    return _annotations.annotations();
  }
  
  public final void fixAccess()
  {
    ClassUtil.checkAndFixAccess(getMember());
  }
  
  protected AnnotationMap getAllAnnotations()
  {
    return _annotations;
  }
  
  public abstract Class<?> getDeclaringClass();
  
  public abstract Member getMember();
  
  public abstract Object getValue(Object paramObject)
    throws UnsupportedOperationException, IllegalArgumentException;
  
  public abstract void setValue(Object paramObject1, Object paramObject2)
    throws UnsupportedOperationException, IllegalArgumentException;
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.AnnotatedMember
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */