package org.apache.commons.cli;

public class MissingArgumentException
  extends ParseException
{
  private Option option;
  
  public MissingArgumentException(String paramString)
  {
    super(paramString);
  }
  
  public MissingArgumentException(Option paramOption)
  {
    this("Missing argument for option: " + paramOption.getKey());
    option = paramOption;
  }
  
  public Option getOption()
  {
    return option;
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.MissingArgumentException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */