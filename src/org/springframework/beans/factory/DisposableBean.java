package org.springframework.beans.factory;

public abstract interface DisposableBean
{
  public abstract void destroy()
    throws Exception;
}

/* Location:
 * Qualified Name:     org.springframework.beans.factory.DisposableBean
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */