package org.springframework.http.converter;

import org.springframework.core.NestedRuntimeException;

public class HttpMessageConversionException
  extends NestedRuntimeException
{
  private static final long serialVersionUID = 1L;
  
  public HttpMessageConversionException(String paramString)
  {
    super(paramString);
  }
  
  public HttpMessageConversionException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.HttpMessageConversionException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */