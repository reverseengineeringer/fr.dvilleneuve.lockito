package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Member;
import java.util.HashMap;

public class CreatorCollector
{
  protected final BeanDescription _beanDesc;
  protected AnnotatedWithParams _booleanCreator;
  protected final boolean _canFixAccess;
  protected AnnotatedWithParams _defaultConstructor;
  protected CreatorProperty[] _delegateArgs;
  protected AnnotatedWithParams _delegateCreator;
  protected AnnotatedWithParams _doubleCreator;
  protected AnnotatedParameter _incompleteParameter;
  protected AnnotatedWithParams _intCreator;
  protected AnnotatedWithParams _longCreator;
  protected CreatorProperty[] _propertyBasedArgs = null;
  protected AnnotatedWithParams _propertyBasedCreator;
  protected AnnotatedWithParams _stringCreator;
  
  public CreatorCollector(BeanDescription paramBeanDescription, boolean paramBoolean)
  {
    _beanDesc = paramBeanDescription;
    _canFixAccess = paramBoolean;
  }
  
  private <T extends AnnotatedMember> T _fixAccess(T paramT)
  {
    if ((paramT != null) && (_canFixAccess)) {
      ClassUtil.checkAndFixAccess((Member)paramT.getAnnotated());
    }
    return paramT;
  }
  
  public void addBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _booleanCreator = verifyNonDup(paramAnnotatedWithParams, _booleanCreator, "boolean");
  }
  
  public void addDelegatingCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty)
  {
    _delegateCreator = verifyNonDup(paramAnnotatedWithParams, _delegateCreator, "delegate");
    _delegateArgs = paramArrayOfCreatorProperty;
  }
  
  public void addDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _doubleCreator = verifyNonDup(paramAnnotatedWithParams, _doubleCreator, "double");
  }
  
  public void addIncompeteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    if (_incompleteParameter == null) {
      _incompleteParameter = paramAnnotatedParameter;
    }
  }
  
  public void addIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _intCreator = verifyNonDup(paramAnnotatedWithParams, _intCreator, "int");
  }
  
  public void addLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _longCreator = verifyNonDup(paramAnnotatedWithParams, _longCreator, "long");
  }
  
  public void addPropertyCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty)
  {
    _propertyBasedCreator = verifyNonDup(paramAnnotatedWithParams, _propertyBasedCreator, "property-based");
    if (paramArrayOfCreatorProperty.length > 1)
    {
      paramAnnotatedWithParams = new HashMap();
      int j = paramArrayOfCreatorProperty.length;
      int i = 0;
      if (i < j)
      {
        String str = paramArrayOfCreatorProperty[i].getName();
        if ((str.length() == 0) && (paramArrayOfCreatorProperty[i].getInjectableValueId() != null)) {}
        Integer localInteger;
        do
        {
          i += 1;
          break;
          localInteger = (Integer)paramAnnotatedWithParams.put(str, Integer.valueOf(i));
        } while (localInteger == null);
        throw new IllegalArgumentException("Duplicate creator property \"" + str + "\" (index " + localInteger + " vs " + i + ")");
      }
    }
    _propertyBasedArgs = paramArrayOfCreatorProperty;
  }
  
  public void addStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _stringCreator = verifyNonDup(paramAnnotatedWithParams, _stringCreator, "String");
  }
  
  public ValueInstantiator constructValueInstantiator(DeserializationConfig paramDeserializationConfig)
  {
    StdValueInstantiator localStdValueInstantiator = new StdValueInstantiator(paramDeserializationConfig, _beanDesc.getType());
    if (_delegateCreator == null)
    {
      paramDeserializationConfig = null;
      localStdValueInstantiator.configureFromObjectSettings(_defaultConstructor, _delegateCreator, paramDeserializationConfig, _delegateArgs, _propertyBasedCreator, _propertyBasedArgs);
      localStdValueInstantiator.configureFromStringCreator(_stringCreator);
      localStdValueInstantiator.configureFromIntCreator(_intCreator);
      localStdValueInstantiator.configureFromLongCreator(_longCreator);
      localStdValueInstantiator.configureFromDoubleCreator(_doubleCreator);
      localStdValueInstantiator.configureFromBooleanCreator(_booleanCreator);
      localStdValueInstantiator.configureIncompleteParameter(_incompleteParameter);
      return localStdValueInstantiator;
    }
    int i;
    if (_delegateArgs != null)
    {
      int j = _delegateArgs.length;
      i = 0;
      label124:
      if (i < j) {
        if (_delegateArgs[i] != null) {}
      }
    }
    for (;;)
    {
      paramDeserializationConfig = _beanDesc.bindingsForBeanType().resolveType(_delegateCreator.getGenericParameterType(i));
      break;
      i += 1;
      break label124;
      i = 0;
    }
  }
  
  public boolean hasDefaultCreator()
  {
    return _defaultConstructor != null;
  }
  
  public void setDefaultCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    _defaultConstructor = ((AnnotatedWithParams)_fixAccess(paramAnnotatedWithParams));
  }
  
  protected AnnotatedWithParams verifyNonDup(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, String paramString)
  {
    if ((paramAnnotatedWithParams2 != null) && (paramAnnotatedWithParams2.getClass() == paramAnnotatedWithParams1.getClass())) {
      throw new IllegalArgumentException("Conflicting " + paramString + " creators: already had " + paramAnnotatedWithParams2 + ", encountered " + paramAnnotatedWithParams1);
    }
    return (AnnotatedWithParams)_fixAccess(paramAnnotatedWithParams1);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.CreatorCollector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */