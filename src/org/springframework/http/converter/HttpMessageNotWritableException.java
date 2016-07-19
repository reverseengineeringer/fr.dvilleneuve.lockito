package org.springframework.http.converter;

public class HttpMessageNotWritableException
  extends HttpMessageConversionException
{
  private static final long serialVersionUID = 1L;
  
  public HttpMessageNotWritableException(String paramString)
  {
    super(paramString);
  }
  
  public HttpMessageNotWritableException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.HttpMessageNotWritableException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */