package org.springframework.http.converter;

public class HttpMessageNotReadableException
  extends HttpMessageConversionException
{
  private static final long serialVersionUID = 1L;
  
  public HttpMessageNotReadableException(String paramString)
  {
    super(paramString);
  }
  
  public HttpMessageNotReadableException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.HttpMessageNotReadableException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */