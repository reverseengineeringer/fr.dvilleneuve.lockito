package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpRequest;

public abstract interface RequestCallback
{
  public abstract void doWithRequest(ClientHttpRequest paramClientHttpRequest)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RequestCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */