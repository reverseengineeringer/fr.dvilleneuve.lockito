package com.facebook.stetho.inspector.helper;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.view.ViewDebug.ExportedProperty;

class IntegerFormatter$IntegerFormatterWithHex
  extends IntegerFormatter
{
  private IntegerFormatter$IntegerFormatterWithHex()
  {
    super(null);
  }
  
  @TargetApi(21)
  public String format(Integer paramInteger, @Nullable ViewDebug.ExportedProperty paramExportedProperty)
  {
    if ((paramExportedProperty != null) && (paramExportedProperty.formatToHexString())) {
      return "0x" + Integer.toHexString(paramInteger.intValue());
    }
    return super.format(paramInteger, paramExportedProperty);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.helper.IntegerFormatter.IntegerFormatterWithHex
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */