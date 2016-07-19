package org.springframework.core.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract interface WritableResource
  extends Resource
{
  public abstract OutputStream getOutputStream()
    throws IOException;
  
  public abstract boolean isWritable();
}

/* Location:
 * Qualified Name:     org.springframework.core.io.WritableResource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */