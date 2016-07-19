package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Options
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Map longOpts = new HashMap();
  private Map optionGroups = new HashMap();
  private List requiredOpts = new ArrayList();
  private Map shortOpts = new HashMap();
  
  public Options addOption(String paramString1, String paramString2, boolean paramBoolean, String paramString3)
  {
    addOption(new Option(paramString1, paramString2, paramBoolean, paramString3));
    return this;
  }
  
  public Options addOption(String paramString1, boolean paramBoolean, String paramString2)
  {
    addOption(paramString1, null, paramBoolean, paramString2);
    return this;
  }
  
  public Options addOption(Option paramOption)
  {
    String str = paramOption.getKey();
    if (paramOption.hasLongOpt()) {
      longOpts.put(paramOption.getLongOpt(), paramOption);
    }
    if (paramOption.isRequired())
    {
      if (requiredOpts.contains(str)) {
        requiredOpts.remove(requiredOpts.indexOf(str));
      }
      requiredOpts.add(str);
    }
    shortOpts.put(str, paramOption);
    return this;
  }
  
  public Options addOptionGroup(OptionGroup paramOptionGroup)
  {
    Iterator localIterator = paramOptionGroup.getOptions().iterator();
    if (paramOptionGroup.isRequired()) {
      requiredOpts.add(paramOptionGroup);
    }
    while (localIterator.hasNext())
    {
      Option localOption = (Option)localIterator.next();
      localOption.setRequired(false);
      addOption(localOption);
      optionGroups.put(localOption.getKey(), paramOptionGroup);
    }
    return this;
  }
  
  public Option getOption(String paramString)
  {
    paramString = Util.stripLeadingHyphens(paramString);
    if (shortOpts.containsKey(paramString)) {
      return (Option)shortOpts.get(paramString);
    }
    return (Option)longOpts.get(paramString);
  }
  
  public OptionGroup getOptionGroup(Option paramOption)
  {
    return (OptionGroup)optionGroups.get(paramOption.getKey());
  }
  
  Collection getOptionGroups()
  {
    return new HashSet(optionGroups.values());
  }
  
  public Collection getOptions()
  {
    return Collections.unmodifiableCollection(helpOptions());
  }
  
  public List getRequiredOptions()
  {
    return requiredOpts;
  }
  
  public boolean hasOption(String paramString)
  {
    paramString = Util.stripLeadingHyphens(paramString);
    return (shortOpts.containsKey(paramString)) || (longOpts.containsKey(paramString));
  }
  
  List helpOptions()
  {
    return new ArrayList(shortOpts.values());
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[ Options: [ short ");
    localStringBuffer.append(shortOpts.toString());
    localStringBuffer.append(" ] [ long ");
    localStringBuffer.append(longOpts);
    localStringBuffer.append(" ]");
    return localStringBuffer.toString();
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.Options
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */