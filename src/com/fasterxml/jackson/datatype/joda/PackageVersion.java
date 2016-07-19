package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion
  implements Versioned
{
  public static final Version VERSION = VersionUtil.parseVersion("2.3.1", "com.fasterxml.jackson.datatype", "jackson-datatype-joda");
  
  public Version version()
  {
    return VERSION;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.PackageVersion
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */