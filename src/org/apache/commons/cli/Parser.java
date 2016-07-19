package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public abstract class Parser
  implements CommandLineParser
{
  protected CommandLine cmd;
  private Options options;
  private List requiredOptions;
  
  protected void checkRequiredOptions()
    throws MissingOptionException
  {
    if (!getRequiredOptions().isEmpty()) {
      throw new MissingOptionException(getRequiredOptions());
    }
  }
  
  protected abstract String[] flatten(Options paramOptions, String[] paramArrayOfString, boolean paramBoolean);
  
  protected Options getOptions()
  {
    return options;
  }
  
  protected List getRequiredOptions()
  {
    return requiredOptions;
  }
  
  public CommandLine parse(Options paramOptions, String[] paramArrayOfString)
    throws ParseException
  {
    return parse(paramOptions, paramArrayOfString, null, false);
  }
  
  public CommandLine parse(Options paramOptions, String[] paramArrayOfString, Properties paramProperties)
    throws ParseException
  {
    return parse(paramOptions, paramArrayOfString, paramProperties, false);
  }
  
  public CommandLine parse(Options paramOptions, String[] paramArrayOfString, Properties paramProperties, boolean paramBoolean)
    throws ParseException
  {
    Iterator localIterator = paramOptions.helpOptions().iterator();
    while (localIterator.hasNext()) {
      ((Option)localIterator.next()).clearValues();
    }
    setOptions(paramOptions);
    cmd = new CommandLine();
    int j = 0;
    paramOptions = paramArrayOfString;
    if (paramArrayOfString == null) {
      paramOptions = new String[0];
    }
    paramOptions = Arrays.asList(flatten(getOptions(), paramOptions, paramBoolean)).listIterator();
    if (paramOptions.hasNext())
    {
      paramArrayOfString = (String)paramOptions.next();
      int i;
      if ("--".equals(paramArrayOfString)) {
        i = 1;
      }
      for (;;)
      {
        j = i;
        if (i == 0) {
          break;
        }
        for (;;)
        {
          j = i;
          if (!paramOptions.hasNext()) {
            break;
          }
          paramArrayOfString = (String)paramOptions.next();
          if (!"--".equals(paramArrayOfString)) {
            cmd.addArg(paramArrayOfString);
          }
        }
        if ("-".equals(paramArrayOfString))
        {
          if (paramBoolean)
          {
            i = 1;
          }
          else
          {
            cmd.addArg(paramArrayOfString);
            i = j;
          }
        }
        else if (paramArrayOfString.startsWith("-"))
        {
          if ((paramBoolean) && (!getOptions().hasOption(paramArrayOfString)))
          {
            i = 1;
            cmd.addArg(paramArrayOfString);
          }
          else
          {
            processOption(paramArrayOfString, paramOptions);
            i = j;
          }
        }
        else
        {
          cmd.addArg(paramArrayOfString);
          i = j;
          if (paramBoolean) {
            i = 1;
          }
        }
      }
    }
    processProperties(paramProperties);
    checkRequiredOptions();
    return cmd;
  }
  
  public CommandLine parse(Options paramOptions, String[] paramArrayOfString, boolean paramBoolean)
    throws ParseException
  {
    return parse(paramOptions, paramArrayOfString, null, paramBoolean);
  }
  
  public void processArgs(Option paramOption, ListIterator paramListIterator)
    throws ParseException
  {
    String str;
    if (paramListIterator.hasNext())
    {
      str = (String)paramListIterator.next();
      if ((!getOptions().hasOption(str)) || (!str.startsWith("-"))) {
        break label69;
      }
      paramListIterator.previous();
    }
    for (;;)
    {
      if ((paramOption.getValues() != null) || (paramOption.hasOptionalArg())) {
        return;
      }
      throw new MissingArgumentException(paramOption);
      try
      {
        label69:
        paramOption.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
      }
      catch (RuntimeException localRuntimeException)
      {
        paramListIterator.previous();
      }
    }
  }
  
  protected void processOption(String paramString, ListIterator paramListIterator)
    throws ParseException
  {
    if (!getOptions().hasOption(paramString)) {
      throw new UnrecognizedOptionException("Unrecognized option: " + paramString, paramString);
    }
    paramString = (Option)getOptions().getOption(paramString).clone();
    if (paramString.isRequired()) {
      getRequiredOptions().remove(paramString.getKey());
    }
    if (getOptions().getOptionGroup(paramString) != null)
    {
      OptionGroup localOptionGroup = getOptions().getOptionGroup(paramString);
      if (localOptionGroup.isRequired()) {
        getRequiredOptions().remove(localOptionGroup);
      }
      localOptionGroup.setSelected(paramString);
    }
    if (paramString.hasArg()) {
      processArgs(paramString, paramListIterator);
    }
    cmd.addOption(paramString);
  }
  
  protected void processProperties(Properties paramProperties)
  {
    label4:
    Enumeration localEnumeration;
    if (paramProperties == null) {
      return;
    } else {
      localEnumeration = paramProperties.propertyNames();
    }
    for (;;)
    {
      if (!localEnumeration.hasMoreElements()) {
        break label4;
      }
      String str = localEnumeration.nextElement().toString();
      if (cmd.hasOption(str)) {
        break;
      }
      Option localOption = getOptions().getOption(str);
      str = paramProperties.getProperty(str);
      if ((!localOption.hasArg()) || ((localOption.getValues() == null) || (localOption.getValues().length == 0))) {}
      try
      {
        localOption.addValueForProcessing(str);
        do
        {
          cmd.addOption(localOption);
          break;
        } while (("yes".equalsIgnoreCase(str)) || ("true".equalsIgnoreCase(str)) || ("1".equalsIgnoreCase(str)));
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;) {}
      }
    }
  }
  
  protected void setOptions(Options paramOptions)
  {
    options = paramOptions;
    requiredOptions = new ArrayList(paramOptions.getRequiredOptions());
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.Parser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */