package org.springframework.core;

import java.io.IOException;

public class NestedIOException
  extends IOException
{
  private static final long serialVersionUID = 1L;
  
  static
  {
    NestedExceptionUtils.class.getName();
  }
  
  public NestedIOException(String paramString)
  {
    super(paramString);
  }
  
  public NestedIOException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
  
  public String getMessage()
  {
    return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
  }
}

/* Location:
 * Qualified Name:     org.springframework.core.NestedIOException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */