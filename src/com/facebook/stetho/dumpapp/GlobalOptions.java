package com.facebook.stetho.dumpapp;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class GlobalOptions
{
  public final Option optionHelp = new Option("h", "help", false, "Print this help");
  public final Option optionListPlugins = new Option("l", "list", false, "List available plugins");
  public final Option optionProcess = new Option("p", "process", true, "Specify target process");
  public final Options options = new Options();
  
  public GlobalOptions()
  {
    options.addOption(optionHelp);
    options.addOption(optionListPlugins);
    options.addOption(optionProcess);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.GlobalOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */