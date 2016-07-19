package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.field.AbstractPartialFieldProperty;

public class Partial$Property
  extends AbstractPartialFieldProperty
  implements Serializable
{
  private static final long serialVersionUID = 53278362873888L;
  private final int iFieldIndex;
  private final Partial iPartial;
  
  Partial$Property(Partial paramPartial, int paramInt)
  {
    iPartial = paramPartial;
    iFieldIndex = paramInt;
  }
  
  public Partial addToCopy(int paramInt)
  {
    int[] arrayOfInt = iPartial.getValues();
    arrayOfInt = getField().add(iPartial, iFieldIndex, arrayOfInt, paramInt);
    return new Partial(iPartial, arrayOfInt);
  }
  
  public Partial addWrapFieldToCopy(int paramInt)
  {
    int[] arrayOfInt = iPartial.getValues();
    arrayOfInt = getField().addWrapField(iPartial, iFieldIndex, arrayOfInt, paramInt);
    return new Partial(iPartial, arrayOfInt);
  }
  
  public int get()
  {
    return iPartial.getValue(iFieldIndex);
  }
  
  public DateTimeField getField()
  {
    return iPartial.getField(iFieldIndex);
  }
  
  public Partial getPartial()
  {
    return iPartial;
  }
  
  protected ReadablePartial getReadablePartial()
  {
    return iPartial;
  }
  
  public Partial setCopy(int paramInt)
  {
    int[] arrayOfInt = iPartial.getValues();
    arrayOfInt = getField().set(iPartial, iFieldIndex, arrayOfInt, paramInt);
    return new Partial(iPartial, arrayOfInt);
  }
  
  public Partial setCopy(String paramString)
  {
    return setCopy(paramString, null);
  }
  
  public Partial setCopy(String paramString, Locale paramLocale)
  {
    int[] arrayOfInt = iPartial.getValues();
    paramString = getField().set(iPartial, iFieldIndex, arrayOfInt, paramString, paramLocale);
    return new Partial(iPartial, paramString);
  }
  
  public Partial withMaximumValue()
  {
    return setCopy(getMaximumValue());
  }
  
  public Partial withMinimumValue()
  {
    return setCopy(getMinimumValue());
  }
}

/* Location:
 * Qualified Name:     org.joda.time.Partial.Property
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */