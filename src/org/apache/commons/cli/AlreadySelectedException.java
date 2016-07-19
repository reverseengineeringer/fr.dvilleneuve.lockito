package org.apache.commons.cli;

public class AlreadySelectedException
  extends ParseException
{
  private OptionGroup group;
  private Option option;
  
  public AlreadySelectedException(String paramString)
  {
    super(paramString);
  }
  
  public AlreadySelectedException(OptionGroup paramOptionGroup, Option paramOption)
  {
    this("The option '" + paramOption.getKey() + "' was specified but an option from this group " + "has already been selected: '" + paramOptionGroup.getSelected() + "'");
    group = paramOptionGroup;
    option = paramOption;
  }
  
  public Option getOption()
  {
    return option;
  }
  
  public OptionGroup getOptionGroup()
  {
    return group;
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.AlreadySelectedException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */