package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PosixParser
  extends Parser
{
  private Option currentOption;
  private boolean eatTheRest;
  private Options options;
  private List tokens = new ArrayList();
  
  private void gobble(Iterator paramIterator)
  {
    if (eatTheRest) {
      while (paramIterator.hasNext()) {
        tokens.add(paramIterator.next());
      }
    }
  }
  
  private void init()
  {
    eatTheRest = false;
    tokens.clear();
  }
  
  private void processNonOptionToken(String paramString, boolean paramBoolean)
  {
    if ((paramBoolean) && ((currentOption == null) || (!currentOption.hasArg())))
    {
      eatTheRest = true;
      tokens.add("--");
    }
    tokens.add(paramString);
  }
  
  private void processOptionToken(String paramString, boolean paramBoolean)
  {
    if ((paramBoolean) && (!options.hasOption(paramString))) {
      eatTheRest = true;
    }
    if (options.hasOption(paramString)) {
      currentOption = options.getOption(paramString);
    }
    tokens.add(paramString);
  }
  
  protected void burstToken(String paramString, boolean paramBoolean)
  {
    int i = 1;
    for (;;)
    {
      if (i < paramString.length())
      {
        String str = String.valueOf(paramString.charAt(i));
        if (options.hasOption(str))
        {
          tokens.add("-" + str);
          currentOption = options.getOption(str);
          if ((!currentOption.hasArg()) || (paramString.length() == i + 1)) {
            break label140;
          }
          tokens.add(paramString.substring(i + 1));
        }
      }
      else
      {
        return;
      }
      if (paramBoolean)
      {
        processNonOptionToken(paramString.substring(i), true);
        return;
      }
      tokens.add(paramString);
      return;
      label140:
      i += 1;
    }
  }
  
  protected String[] flatten(Options paramOptions, String[] paramArrayOfString, boolean paramBoolean)
  {
    init();
    options = paramOptions;
    Iterator localIterator = Arrays.asList(paramArrayOfString).iterator();
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int i;
      if (str.startsWith("--"))
      {
        i = str.indexOf('=');
        if (i == -1)
        {
          paramArrayOfString = str;
          label70:
          if (paramOptions.hasOption(paramArrayOfString)) {
            break label106;
          }
          processNonOptionToken(str, paramBoolean);
        }
      }
      for (;;)
      {
        gobble(localIterator);
        break;
        paramArrayOfString = str.substring(0, i);
        break label70;
        label106:
        currentOption = paramOptions.getOption(paramArrayOfString);
        tokens.add(paramArrayOfString);
        if (i != -1)
        {
          tokens.add(str.substring(i + 1));
          continue;
          if ("-".equals(str)) {
            tokens.add(str);
          } else if (str.startsWith("-"))
          {
            if ((str.length() == 2) || (paramOptions.hasOption(str))) {
              processOptionToken(str, paramBoolean);
            } else {
              burstToken(str, paramBoolean);
            }
          }
          else {
            processNonOptionToken(str, paramBoolean);
          }
        }
      }
    }
    return (String[])tokens.toArray(new String[tokens.size()]);
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.PosixParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */