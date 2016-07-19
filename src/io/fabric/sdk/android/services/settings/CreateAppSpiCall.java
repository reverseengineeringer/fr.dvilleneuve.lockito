package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequestFactory;

public class CreateAppSpiCall
  extends AbstractAppSpiCall
{
  public CreateAppSpiCall(Kit paramKit, String paramString1, String paramString2, HttpRequestFactory paramHttpRequestFactory)
  {
    super(paramKit, paramString1, paramString2, paramHttpRequestFactory, HttpMethod.POST);
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.settings.CreateAppSpiCall
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */