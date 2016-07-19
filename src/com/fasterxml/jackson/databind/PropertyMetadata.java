package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata
  implements Serializable
{
  public static final PropertyMetadata STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null);
  public static final PropertyMetadata STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null);
  public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null);
  private static final long serialVersionUID = -1L;
  protected final String _description;
  protected final Boolean _required;
  
  protected PropertyMetadata(Boolean paramBoolean, String paramString)
  {
    _required = paramBoolean;
    _description = paramString;
  }
  
  public static PropertyMetadata construct(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {}
    for (PropertyMetadata localPropertyMetadata1 = STD_REQUIRED;; localPropertyMetadata1 = STD_OPTIONAL)
    {
      PropertyMetadata localPropertyMetadata2 = localPropertyMetadata1;
      if (paramString != null) {
        localPropertyMetadata2 = localPropertyMetadata1.withDescription(paramString);
      }
      return localPropertyMetadata2;
    }
  }
  
  public String getDescription()
  {
    return _description;
  }
  
  public Boolean getRequired()
  {
    return _required;
  }
  
  public boolean isRequired()
  {
    return (_required != null) && (_required.booleanValue());
  }
  
  protected Object readResolve()
  {
    if (_description == null)
    {
      if (_required == null) {
        return STD_REQUIRED_OR_OPTIONAL;
      }
      if (_required.booleanValue()) {
        return STD_REQUIRED;
      }
      return STD_OPTIONAL;
    }
    return this;
  }
  
  public PropertyMetadata withDescription(String paramString)
  {
    return new PropertyMetadata(_required, paramString);
  }
  
  public PropertyMetadata withRequired(Boolean paramBoolean)
  {
    if (paramBoolean == null)
    {
      if (_required != null) {}
    }
    else {
      while ((_required != null) && (_required.booleanValue() == paramBoolean.booleanValue())) {
        return this;
      }
    }
    return new PropertyMetadata(paramBoolean, _description);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.PropertyMetadata
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */