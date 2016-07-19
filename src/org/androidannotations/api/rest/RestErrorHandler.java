package org.androidannotations.api.rest;

import org.springframework.core.NestedRuntimeException;

public abstract interface RestErrorHandler
{
  public abstract void onRestClientExceptionThrown(NestedRuntimeException paramNestedRuntimeException);
}

/* Location:
 * Qualified Name:     org.androidannotations.api.rest.RestErrorHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */