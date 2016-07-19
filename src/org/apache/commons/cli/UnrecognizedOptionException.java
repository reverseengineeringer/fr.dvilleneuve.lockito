package org.apache.commons.cli;

public class UnrecognizedOptionException
  extends ParseException
{
  private String option;
  
  public UnrecognizedOptionException(String paramString)
  {
    super(paramString);
  }
  
  public UnrecognizedOptionException(String paramString1, String paramString2)
  {
    this(paramString1);
    option = paramString2;
  }
  
  public String getOption()
  {
    return option;
  }
}

/* Location:
 * Qualified Name:     org.apache.commons.cli.UnrecognizedOptionException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */