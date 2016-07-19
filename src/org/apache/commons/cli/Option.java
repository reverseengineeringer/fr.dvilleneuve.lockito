package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Option
  implements Cloneable, Serializable
{
  public static final int UNINITIALIZED = -1;
  public static final int UNLIMITED_VALUES = -2;
  private static final long serialVersionUID = 1L;
  private String argName = "arg";
  private String description;
  private String longOpt;
  private int numberOfArgs = -1;
  private String opt;
  private boolean optionalArg;
  private boolean required;
  private Object type;
  private List values = new ArrayList();
  private char valuesep;
  
  public Option(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    this(paramString1, null, false, paramString2);
  }
  
  public Option(String paramString1, String paramString2, boolean paramBoolean, String paramString3)
    throws IllegalArgumentException
  {
    OptionValidator.validateOption(paramString1);
    opt = paramString1;
    longOpt = paramString2;
    if (paramBoolean) {
      numberOfArgs = 1;
    }
    description = paramString3;
  }
  
  public Option(String paramString1, boolean paramBoolean, String paramString2)
    throws IllegalArgumentException
  {
    this(paramString1, null, paramBoolean, paramString2);
  }
  
  private void add(String paramString)
  {
    if ((numberOfArgs > 0) && (values.size() > numberOfArgs - 1)) {
      throw new RuntimeException("Cannot add value, list full.");
    }
    values.add(paramString);
  }
  
  private boolean hasNoValues()
  {
    return values.isEmpty();
  }
  
  private void processValue(String paramString)
  {
    String str = paramString;
    int j;
    if (hasValueSeparator()) {
      j = getValueSeparator();
    }
    for (int i = paramString.indexOf(j);; i = paramString.indexOf(j))
    {
      str = paramString;
      if (i != -1)
      {
        if (values.size() == numberOfArgs - 1) {
          str = paramString;
        }
      }
      else
      {
        add(str);
        return;
      }
      add(paramString.substring(0, i));
      paramString = paramString.substring(i + 1);
    }
  }
  
  public boolean addValue(String paramString)
  {
    throw new UnsupportedOperationException("The addValue method is not intended for client use. Subclasses should use the addValueForProcessing method instead. ");
  }
  
  void addValueForProcessing(String paramString)
  {
    switch (numberOfArgs)
    {
    default: 
      processValue(paramString);
      return;
    }
    throw new RuntimeException("NO_ARGS_ALLOWED");
  }
  
  void clearValues()
  {
    values.clear();
  }
  
  public Object clone()
  {
    try
    {
      Option localOption = (Option)super.clone();
      values = new ArrayList(values);
      return localOption;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException("A CloneNotSupportedException was thrown: " + localCloneNotSupportedException.getMessage());
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (Option)paramObject;
      if (opt != null)
      {
        if (opt.equals(opt)) {}
      }
      else {
        while (opt != null) {
          return false;
        }
      }
      if (longOpt == null) {
        break;
      }
    } while (longOpt.equals(longOpt));
    for (;;)
    {
      return false;
      if (longOpt == null) {
        break;
      }
    }
  }
  
  public String getArgName()
  {
    return argName;
  }
  
  public int getArgs()
  {
    return numberOfArgs;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public int getId()
  {
    return getKey().charAt(0);
  }
  
  String getKey()
  {
    if (opt == null) {
      return longOpt;
    }
    return opt;
  }
  
  public String getLongOpt()
  {
    return longOpt;
  }
  
  public String getOpt()
  {
    return opt;
  }
  
  public Object getType()
  {
    return type;
  }
  
  public String getValue()
  {
    if (hasNoValues()) {
      return null;
    }
    return (String)values.get(0);
  }
  
  public String getValue(int paramInt)
    throws IndexOutOfBoundsException
  {
    if (hasNoValues()) {
      return null;
    }
    return (String)values.get(paramInt);
  }
  
  public String getValue(String paramString)
  {
    String str = getValue();
    if (str != null) {
      return str;
    }
    return paramString;
  }
  
  public char getValueSeparator()
  {
    return valuesep;
  }
  
  public String[] getValues()
  {
    if (hasNoValues()) {
      return null;
    }
    return (String[])values.toArray(new String[values.size()]);
  }
  
  public List getValuesList()
  {
    return values;
  }
  
  public boolean hasArg()
  {
    return (numberOfArgs > 0) || (numberOfArgs == -2);
  }
  
  public boolean hasArgName()
  {
    return (argName != null) && (argName.length() > 0);
  }
  
  public boolean hasArgs()
  {
    return (numberOfArgs > 1) || (numberOfArgs == -2);
  }
  
  public boolean hasLongOpt()
  {
    return longOpt != null;
  }
  
  public boolean hasOptionalArg()
  {
    return optionalArg;
  }
  
  public boolean hasValueSeparator()
  {
    return valuesep > 0;
  }
  
  public int hashCode()
  {
    int j = 0;
    if (opt != null) {}
    for (int i = opt.hashCode();; i = 0)
    {
      if (longOpt != null) {
        j = longOpt.hashCode();
      }
      return i * 31 + j;
    }
  }
  
  public boolean isRequired()
  {
    return required;
  }
  
  public void setArgName(String paramString)
  {
    argName = paramString;
  }
  
  public void setArgs(int paramInt)
  {
    numberOfArgs = paramInt;
  }
  
  public void setDescription(String paramString)
  {
    description = paramString;
  }
  
  public void setLongOpt(String paramString)
  {
    longOpt = paramString;
  }
  
  public void setOptionalArg(boolean paramBoolean)
  {
    optionalArg = paramBoolean;
  }
  
  public void setRequired(boolean paramBoolean)
  {
    required = paramBoolean;
  }
  
  public void setType(Object paramObject)
  {
    type = paramObject;
  }
  
  public void setValueSeparator(char paramChar)
  {
    valuesep = paramChar;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer().append("[ option: ");
    localStringBuffer.append(opt);
    if (longOpt != null) {
      localStringBuffer.append(" ").append(longOpt);
    }
    localStringBuffer.append(" ");
    if (hasArgs()) {
      localStringBuffer.append("[ARG...]");
    }
    for (;;)
    {
      localStringBuffer.append(" :: ").append(description);
      if (type != null) {
        localStringBuffer.append(" :: ").append(type);
      }
      localStringBuffer.append(" ]");
      return localStringBuffer.toString();
      if (hasArg()) {
        localStringBuffer.append(" [ARG]");
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.Option
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */