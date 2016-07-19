package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.databind.PropertyName;

public class ObjectIdInfo
{
  protected final boolean _alwaysAsId;
  protected final Class<? extends ObjectIdGenerator<?>> _generator;
  protected final PropertyName _propertyName;
  protected final Class<?> _scope;
  
  public ObjectIdInfo(PropertyName paramPropertyName, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1)
  {
    this(paramPropertyName, paramClass, paramClass1, false);
  }
  
  protected ObjectIdInfo(PropertyName paramPropertyName, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1, boolean paramBoolean)
  {
    _propertyName = paramPropertyName;
    _scope = paramClass;
    _generator = paramClass1;
    _alwaysAsId = paramBoolean;
  }
  
  @Deprecated
  public ObjectIdInfo(String paramString, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1)
  {
    this(new PropertyName(paramString), paramClass, paramClass1, false);
  }
  
  public boolean getAlwaysAsId()
  {
    return _alwaysAsId;
  }
  
  public Class<? extends ObjectIdGenerator<?>> getGeneratorType()
  {
    return _generator;
  }
  
  public PropertyName getPropertyName()
  {
    return _propertyName;
  }
  
  public Class<?> getScope()
  {
    return _scope;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("ObjectIdInfo: propName=").append(_propertyName).append(", scope=");
    if (_scope == null)
    {
      str = "null";
      localStringBuilder = localStringBuilder.append(str).append(", generatorType=");
      if (_generator != null) {
        break label88;
      }
    }
    label88:
    for (String str = "null";; str = _generator.getName())
    {
      return str + ", alwaysAsId=" + _alwaysAsId;
      str = _scope.getName();
      break;
    }
  }
  
  public ObjectIdInfo withAlwaysAsId(boolean paramBoolean)
  {
    if (_alwaysAsId == paramBoolean) {
      return this;
    }
    return new ObjectIdInfo(_propertyName, _scope, _generator, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.ObjectIdInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */