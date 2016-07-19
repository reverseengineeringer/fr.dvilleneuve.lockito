package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.List;

public class GnuParser
  extends Parser
{
  protected String[] flatten(Options paramOptions, String[] paramArrayOfString, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool = false;
    int j;
    for (int i = 0; i < paramArrayOfString.length; i = j + 1)
    {
      String str1 = paramArrayOfString[i];
      if ("--".equals(str1))
      {
        bool = true;
        localArrayList.add("--");
      }
      for (;;)
      {
        j = i;
        if (!bool) {
          break;
        }
        i += 1;
        for (;;)
        {
          j = i;
          if (i >= paramArrayOfString.length) {
            break;
          }
          localArrayList.add(paramArrayOfString[i]);
          i += 1;
        }
        if ("-".equals(str1))
        {
          localArrayList.add("-");
        }
        else if (str1.startsWith("-"))
        {
          String str2 = Util.stripLeadingHyphens(str1);
          if (paramOptions.hasOption(str2))
          {
            localArrayList.add(str1);
          }
          else if ((str2.indexOf('=') != -1) && (paramOptions.hasOption(str2.substring(0, str2.indexOf('=')))))
          {
            localArrayList.add(str1.substring(0, str1.indexOf('=')));
            localArrayList.add(str1.substring(str1.indexOf('=') + 1));
          }
          else if (paramOptions.hasOption(str1.substring(0, 2)))
          {
            localArrayList.add(str1.substring(0, 2));
            localArrayList.add(str1.substring(2));
          }
          else
          {
            bool = paramBoolean;
            localArrayList.add(str1);
          }
        }
        else
        {
          localArrayList.add(str1);
        }
      }
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.GnuParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */