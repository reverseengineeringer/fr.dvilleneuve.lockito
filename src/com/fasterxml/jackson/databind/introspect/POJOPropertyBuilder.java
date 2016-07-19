package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.util.BeanUtil;

public class POJOPropertyBuilder
  extends BeanPropertyDefinition
  implements Comparable<POJOPropertyBuilder>
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected Linked<AnnotatedParameter> _ctorParameters;
  protected Linked<AnnotatedField> _fields;
  protected final boolean _forSerialization;
  protected Linked<AnnotatedMethod> _getters;
  protected final PropertyName _internalName;
  protected final PropertyName _name;
  protected Linked<AnnotatedMethod> _setters;
  
  public POJOPropertyBuilder(PropertyName paramPropertyName, AnnotationIntrospector paramAnnotationIntrospector, boolean paramBoolean)
  {
    _internalName = paramPropertyName;
    _name = paramPropertyName;
    _annotationIntrospector = paramAnnotationIntrospector;
    _forSerialization = paramBoolean;
  }
  
  public POJOPropertyBuilder(POJOPropertyBuilder paramPOJOPropertyBuilder, PropertyName paramPropertyName)
  {
    _internalName = _internalName;
    _name = paramPropertyName;
    _annotationIntrospector = _annotationIntrospector;
    _fields = _fields;
    _ctorParameters = _ctorParameters;
    _getters = _getters;
    _setters = _setters;
    _forSerialization = _forSerialization;
  }
  
  @Deprecated
  public POJOPropertyBuilder(String paramString, AnnotationIntrospector paramAnnotationIntrospector, boolean paramBoolean)
  {
    this(new PropertyName(paramString), paramAnnotationIntrospector, paramBoolean);
  }
  
  private <T> boolean _anyExplicitNames(Linked<T> paramLinked)
  {
    while (paramLinked != null)
    {
      if ((explicitName != null) && (explicitName.length() > 0)) {
        return true;
      }
      paramLinked = next;
    }
    return false;
  }
  
  private <T> boolean _anyIgnorals(Linked<T> paramLinked)
  {
    while (paramLinked != null)
    {
      if (isMarkedIgnored) {
        return true;
      }
      paramLinked = next;
    }
    return false;
  }
  
  private <T> boolean _anyVisible(Linked<T> paramLinked)
  {
    while (paramLinked != null)
    {
      if (isVisible) {
        return true;
      }
      paramLinked = next;
    }
    return false;
  }
  
  private AnnotationMap _mergeAnnotations(int paramInt, Linked<? extends AnnotatedMember>... paramVarArgs)
  {
    AnnotationMap localAnnotationMap = ((AnnotatedMember)value).getAllAnnotations();
    paramInt += 1;
    while (paramInt < paramVarArgs.length)
    {
      if (paramVarArgs[paramInt] != null) {
        return AnnotationMap.merge(localAnnotationMap, _mergeAnnotations(paramInt, paramVarArgs));
      }
      paramInt += 1;
    }
    return localAnnotationMap;
  }
  
  private <T> Linked<T> _removeIgnored(Linked<T> paramLinked)
  {
    if (paramLinked == null) {
      return paramLinked;
    }
    return paramLinked.withoutIgnored();
  }
  
  private <T> Linked<T> _removeNonVisible(Linked<T> paramLinked)
  {
    if (paramLinked == null) {
      return paramLinked;
    }
    return paramLinked.withoutNonVisible();
  }
  
  private <T> Linked<T> _trimByVisibility(Linked<T> paramLinked)
  {
    if (paramLinked == null) {
      return paramLinked;
    }
    return paramLinked.trimByVisibility();
  }
  
  private Linked<? extends AnnotatedMember> findRenamed(Linked<? extends AnnotatedMember> paramLinked1, Linked<? extends AnnotatedMember> paramLinked2)
  {
    if (paramLinked1 != null)
    {
      String str = explicitName;
      Linked<? extends AnnotatedMember> localLinked;
      if (str == null) {
        localLinked = paramLinked2;
      }
      label50:
      do
      {
        for (;;)
        {
          paramLinked1 = next;
          paramLinked2 = localLinked;
          break;
          localLinked = paramLinked2;
          if (!str.equals(_name))
          {
            if (paramLinked2 != null) {
              break label50;
            }
            localLinked = paramLinked1;
          }
        }
        localLinked = paramLinked2;
      } while (str.equals(explicitName));
      throw new IllegalStateException("Conflicting property name definitions: '" + explicitName + "' (for " + value + ") vs '" + explicitName + "' (for " + value + ")");
    }
    return paramLinked2;
  }
  
  private static <T> Linked<T> merge(Linked<T> paramLinked1, Linked<T> paramLinked2)
  {
    if (paramLinked1 == null) {
      return paramLinked2;
    }
    if (paramLinked2 == null) {
      return paramLinked1;
    }
    return paramLinked1.append(paramLinked2);
  }
  
  protected String _findDescription()
  {
    (String)fromMemberAnnotations(new WithMember()
    {
      public String withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return _annotationIntrospector.findPropertyDescription(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected Boolean _findRequired()
  {
    (Boolean)fromMemberAnnotations(new WithMember()
    {
      public Boolean withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return _annotationIntrospector.hasRequiredMarker(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  public void addAll(POJOPropertyBuilder paramPOJOPropertyBuilder)
  {
    _fields = merge(_fields, _fields);
    _ctorParameters = merge(_ctorParameters, _ctorParameters);
    _getters = merge(_getters, _getters);
    _setters = merge(_setters, _setters);
  }
  
  public void addCtor(AnnotatedParameter paramAnnotatedParameter, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    _ctorParameters = new Linked(paramAnnotatedParameter, _ctorParameters, paramString, paramBoolean1, paramBoolean2);
  }
  
  public void addField(AnnotatedField paramAnnotatedField, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    _fields = new Linked(paramAnnotatedField, _fields, paramString, paramBoolean1, paramBoolean2);
  }
  
  public void addGetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    _getters = new Linked(paramAnnotatedMethod, _getters, paramString, paramBoolean1, paramBoolean2);
  }
  
  public void addSetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    _setters = new Linked(paramAnnotatedMethod, _setters, paramString, paramBoolean1, paramBoolean2);
  }
  
  public boolean anyIgnorals()
  {
    return (_anyIgnorals(_fields)) || (_anyIgnorals(_getters)) || (_anyIgnorals(_setters)) || (_anyIgnorals(_ctorParameters));
  }
  
  public boolean anyVisible()
  {
    return (_anyVisible(_fields)) || (_anyVisible(_getters)) || (_anyVisible(_setters)) || (_anyVisible(_ctorParameters));
  }
  
  public int compareTo(POJOPropertyBuilder paramPOJOPropertyBuilder)
  {
    if (_ctorParameters != null)
    {
      if (_ctorParameters == null) {
        return -1;
      }
    }
    else if (_ctorParameters != null) {
      return 1;
    }
    return getName().compareTo(paramPOJOPropertyBuilder.getName());
  }
  
  public boolean couldSerialize()
  {
    return (_getters != null) || (_fields != null);
  }
  
  public String findNewName()
  {
    Linked localLinked = findRenamed(_fields, null);
    localLinked = findRenamed(_getters, localLinked);
    localLinked = findRenamed(_setters, localLinked);
    localLinked = findRenamed(_ctorParameters, localLinked);
    if (localLinked == null) {
      return null;
    }
    return explicitName;
  }
  
  public ObjectIdInfo findObjectIdInfo()
  {
    (ObjectIdInfo)fromMemberAnnotations(new WithMember()
    {
      public ObjectIdInfo withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        ObjectIdInfo localObjectIdInfo2 = _annotationIntrospector.findObjectIdInfo(paramAnonymousAnnotatedMember);
        ObjectIdInfo localObjectIdInfo1 = localObjectIdInfo2;
        if (localObjectIdInfo2 != null) {
          localObjectIdInfo1 = _annotationIntrospector.findObjectReferenceInfo(paramAnonymousAnnotatedMember, localObjectIdInfo2);
        }
        return localObjectIdInfo1;
      }
    });
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType()
  {
    (AnnotationIntrospector.ReferenceProperty)fromMemberAnnotations(new WithMember()
    {
      public AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return _annotationIntrospector.findReferenceType(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  public Class<?>[] findViews()
  {
    (Class[])fromMemberAnnotations(new WithMember()
    {
      public Class<?>[] withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return _annotationIntrospector.findViews(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected <T> T fromMemberAnnotations(WithMember<T> paramWithMember)
  {
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject1 = null;
    if (_annotationIntrospector != null)
    {
      if (!_forSerialization) {
        break label79;
      }
      if (_getters != null) {
        localObject1 = paramWithMember.withMember((AnnotatedMember)_getters.value);
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = localObject1;
        if (_fields != null) {
          localObject2 = paramWithMember.withMember((AnnotatedMember)_fields.value);
        }
      }
      return (T)localObject2;
      label79:
      localObject2 = localObject3;
      if (_ctorParameters != null) {
        localObject2 = paramWithMember.withMember((AnnotatedMember)_ctorParameters.value);
      }
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = localObject2;
        if (_setters != null) {
          localObject1 = paramWithMember.withMember((AnnotatedMember)_setters.value);
        }
      }
    }
  }
  
  public AnnotatedMember getAccessor()
  {
    AnnotatedMethod localAnnotatedMethod = getGetter();
    Object localObject = localAnnotatedMethod;
    if (localAnnotatedMethod == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedParameter getConstructorParameter()
  {
    if (_ctorParameters == null) {
      return null;
    }
    Linked localLinked = _ctorParameters;
    for (;;)
    {
      if ((((AnnotatedParameter)value).getOwner() instanceof AnnotatedConstructor)) {
        return (AnnotatedParameter)value;
      }
      localLinked = next;
      if (localLinked == null) {
        return (AnnotatedParameter)_ctorParameters.value;
      }
    }
  }
  
  public AnnotatedField getField()
  {
    if (_fields == null) {
      localObject2 = null;
    }
    Object localObject1;
    Linked localLinked;
    do
    {
      return (AnnotatedField)localObject2;
      localObject1 = (AnnotatedField)_fields.value;
      localLinked = _fields.next;
      localObject2 = localObject1;
    } while (localLinked == null);
    Object localObject2 = (AnnotatedField)value;
    Class localClass1 = ((AnnotatedField)localObject1).getDeclaringClass();
    Class localClass2 = ((AnnotatedField)localObject2).getDeclaringClass();
    if (localClass1 != localClass2)
    {
      if (localClass1.isAssignableFrom(localClass2)) {
        localObject1 = localObject2;
      }
      while (localClass2.isAssignableFrom(localClass1))
      {
        localLinked = next;
        break;
      }
    }
    throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + ((AnnotatedField)localObject1).getFullName() + " vs " + ((AnnotatedField)localObject2).getFullName());
  }
  
  public PropertyName getFullName()
  {
    return _name;
  }
  
  public AnnotatedMethod getGetter()
  {
    if (_getters == null) {
      localObject2 = null;
    }
    Object localObject1;
    Linked localLinked;
    do
    {
      return (AnnotatedMethod)localObject2;
      localObject1 = (AnnotatedMethod)_getters.value;
      localLinked = _getters.next;
      localObject2 = localObject1;
    } while (localLinked == null);
    Object localObject2 = (AnnotatedMethod)value;
    Class localClass1 = ((AnnotatedMethod)localObject1).getDeclaringClass();
    Class localClass2 = ((AnnotatedMethod)localObject2).getDeclaringClass();
    if (localClass1 != localClass2)
    {
      if (localClass1.isAssignableFrom(localClass2)) {}
      for (;;)
      {
        localLinked = next;
        localObject1 = localObject2;
        break;
        if (!localClass2.isAssignableFrom(localClass1)) {
          break label110;
        }
        localObject2 = localObject1;
      }
    }
    label110:
    int i;
    if (BeanUtil.okNameForIsGetter((AnnotatedMethod)localObject1, ((AnnotatedMethod)localObject1).getName()) != null)
    {
      i = 1;
      label123:
      if (BeanUtil.okNameForIsGetter((AnnotatedMethod)localObject2, ((AnnotatedMethod)localObject2).getName()) == null) {
        break label158;
      }
    }
    label158:
    for (int j = 1;; j = 0)
    {
      if (i == j) {
        break label163;
      }
      if (i != 0) {
        break;
      }
      localObject2 = localObject1;
      break;
      i = 0;
      break label123;
    }
    label163:
    throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod)localObject1).getFullName() + " vs " + ((AnnotatedMethod)localObject2).getFullName());
  }
  
  public String getInternalName()
  {
    return _internalName.getSimpleName();
  }
  
  public PropertyMetadata getMetadata()
  {
    Boolean localBoolean = _findRequired();
    String str = _findDescription();
    if (localBoolean == null)
    {
      if (str == null) {
        return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      }
      return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL.withDescription(str);
    }
    return PropertyMetadata.construct(localBoolean.booleanValue(), _findDescription());
  }
  
  public AnnotatedMember getMutator()
  {
    Object localObject2 = getConstructorParameter();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = getSetter();
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = getField();
      }
    }
    return (AnnotatedMember)localObject1;
  }
  
  public String getName()
  {
    if (_name == null) {
      return null;
    }
    return _name.getSimpleName();
  }
  
  public AnnotatedMember getNonConstructorMutator()
  {
    AnnotatedMethod localAnnotatedMethod = getSetter();
    Object localObject = localAnnotatedMethod;
    if (localAnnotatedMethod == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedMember getPrimaryMember()
  {
    if (_forSerialization) {
      return getAccessor();
    }
    return getMutator();
  }
  
  public AnnotatedMethod getSetter()
  {
    if (_setters == null) {
      localObject2 = null;
    }
    Object localObject1;
    Linked localLinked;
    do
    {
      return (AnnotatedMethod)localObject2;
      localObject1 = (AnnotatedMethod)_setters.value;
      localLinked = _setters.next;
      localObject2 = localObject1;
    } while (localLinked == null);
    Object localObject2 = (AnnotatedMethod)value;
    Class localClass1 = ((AnnotatedMethod)localObject1).getDeclaringClass();
    Class localClass2 = ((AnnotatedMethod)localObject2).getDeclaringClass();
    if (localClass1 != localClass2)
    {
      if (localClass1.isAssignableFrom(localClass2)) {
        localObject1 = localObject2;
      }
      while (localClass2.isAssignableFrom(localClass1))
      {
        localLinked = next;
        break;
      }
    }
    throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod)localObject1).getFullName() + " vs " + ((AnnotatedMethod)localObject2).getFullName());
  }
  
  public PropertyName getWrapperName()
  {
    AnnotatedMember localAnnotatedMember = getPrimaryMember();
    if ((localAnnotatedMember == null) || (_annotationIntrospector == null)) {
      return null;
    }
    return _annotationIntrospector.findWrapperName(localAnnotatedMember);
  }
  
  public boolean hasConstructorParameter()
  {
    return _ctorParameters != null;
  }
  
  public boolean hasField()
  {
    return _fields != null;
  }
  
  public boolean hasGetter()
  {
    return _getters != null;
  }
  
  public boolean hasSetter()
  {
    return _setters != null;
  }
  
  public boolean isExplicitlyIncluded()
  {
    return (_anyExplicitNames(_fields)) || (_anyExplicitNames(_getters)) || (_anyExplicitNames(_setters)) || (_anyExplicitNames(_ctorParameters));
  }
  
  public boolean isTypeId()
  {
    Boolean localBoolean = (Boolean)fromMemberAnnotations(new WithMember()
    {
      public Boolean withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return _annotationIntrospector.isTypeId(paramAnonymousAnnotatedMember);
      }
    });
    return (localBoolean != null) && (localBoolean.booleanValue());
  }
  
  public void mergeAnnotations(boolean paramBoolean)
  {
    if (paramBoolean) {
      if (_getters != null)
      {
        localAnnotationMap = _mergeAnnotations(0, new Linked[] { _getters, _fields, _ctorParameters, _setters });
        _getters = _getters.withValue(((AnnotatedMethod)_getters.value).withAnnotations(localAnnotationMap));
      }
    }
    do
    {
      do
      {
        return;
      } while (_fields == null);
      localAnnotationMap = _mergeAnnotations(0, new Linked[] { _fields, _ctorParameters, _setters });
      _fields = _fields.withValue(((AnnotatedField)_fields.value).withAnnotations(localAnnotationMap));
      return;
      if (_ctorParameters != null)
      {
        localAnnotationMap = _mergeAnnotations(0, new Linked[] { _ctorParameters, _setters, _fields, _getters });
        _ctorParameters = _ctorParameters.withValue(((AnnotatedParameter)_ctorParameters.value).withAnnotations(localAnnotationMap));
        return;
      }
      if (_setters != null)
      {
        localAnnotationMap = _mergeAnnotations(0, new Linked[] { _setters, _fields, _getters });
        _setters = _setters.withValue(((AnnotatedMethod)_setters.value).withAnnotations(localAnnotationMap));
        return;
      }
    } while (_fields == null);
    AnnotationMap localAnnotationMap = _mergeAnnotations(0, new Linked[] { _fields, _getters });
    _fields = _fields.withValue(((AnnotatedField)_fields.value).withAnnotations(localAnnotationMap));
  }
  
  public void removeIgnored()
  {
    _fields = _removeIgnored(_fields);
    _getters = _removeIgnored(_getters);
    _setters = _removeIgnored(_setters);
    _ctorParameters = _removeIgnored(_ctorParameters);
  }
  
  @Deprecated
  public void removeNonVisible()
  {
    removeNonVisible(false);
  }
  
  public void removeNonVisible(boolean paramBoolean)
  {
    _getters = _removeNonVisible(_getters);
    _ctorParameters = _removeNonVisible(_ctorParameters);
    if ((paramBoolean) || (_getters == null))
    {
      _fields = _removeNonVisible(_fields);
      _setters = _removeNonVisible(_setters);
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[Property '").append(_name).append("'; ctors: ").append(_ctorParameters).append(", field(s): ").append(_fields).append(", getter(s): ").append(_getters).append(", setter(s): ").append(_setters);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void trimByVisibility()
  {
    _fields = _trimByVisibility(_fields);
    _getters = _trimByVisibility(_getters);
    _setters = _trimByVisibility(_setters);
    _ctorParameters = _trimByVisibility(_ctorParameters);
  }
  
  public POJOPropertyBuilder withName(PropertyName paramPropertyName)
  {
    return new POJOPropertyBuilder(this, paramPropertyName);
  }
  
  @Deprecated
  public POJOPropertyBuilder withName(String paramString)
  {
    return withSimpleName(paramString);
  }
  
  public POJOPropertyBuilder withSimpleName(String paramString)
  {
    paramString = _name.withSimpleName(paramString);
    if (paramString == _name) {
      return this;
    }
    return new POJOPropertyBuilder(this, paramString);
  }
  
  private static final class Linked<T>
  {
    public final String explicitName;
    public final boolean isMarkedIgnored;
    public final boolean isVisible;
    public final Linked<T> next;
    public final T value;
    
    public Linked(T paramT, Linked<T> paramLinked, String paramString, boolean paramBoolean1, boolean paramBoolean2)
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
  
  private static abstract interface WithMember<T>
  {
    public abstract T withMember(AnnotatedMember paramAnnotatedMember);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */