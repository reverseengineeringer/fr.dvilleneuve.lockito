package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

public abstract interface InputStreamSource
{
  public abstract InputStream getInputStream()
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.core.io.InputStreamSource
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */