package org.apache.commons.cli;

import java.util.Comparator;

class HelpFormatter$OptionComparator
  implements Comparator
{
  private HelpFormatter$OptionComparator() {}
  
  HelpFormatter$OptionComparator(HelpFormatter.1 param1)
  {
    this();
  }
  
  public int compare(Object paramObject1, Object paramObject2)
  {
    paramObject1 = (Option)paramObject1;
    paramObject2 = (Option)paramObject2;
    return ((Option)paramObject1).getKey().compareToIgnoreCase(((Option)paramObject2).getKey());
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.HelpFormatter.OptionComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */