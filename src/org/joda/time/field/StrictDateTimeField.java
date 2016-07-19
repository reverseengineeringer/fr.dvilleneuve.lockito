package org.joda.time.field;

import org.joda.time.DateTimeField;

public class StrictDateTimeField
  extends DelegatedDateTimeField
{
  private static final long serialVersionUID = 3154803964207950910L;
  
  protected StrictDateTimeField(DateTimeField paramDateTimeField)
  {
    super(paramDateTimeField);
  }
  
  public static DateTimeField getInstance(DateTimeField paramDateTimeField)
  {
    if (paramDateTimeField == null) {
      paramDateTimeField = null;
    }
    DateTimeField localDateTimeField;
    do
    {
      return paramDateTimeField;
      localDateTimeField = paramDateTimeField;
      if ((paramDateTimeField instanceof LenientDateTimeField)) {
        localDateTimeField = ((LenientDateTimeField)paramDateTimeField).getWrappedField();
      }
      paramDateTimeField = localDateTimeField;
    } while (!localDateTimeField.isLenient());
    return new StrictDateTimeField(localDateTimeField);
  }
  
  public final boolean isLenient()
  {
    return false;
  }
  
  public long set(long paramLong, int paramInt)
  {
    FieldUtils.verifyValueBounds(this, paramInt, getMinimumValue(paramLong), getMaximumValue(paramLong));
    return super.set(paramLong, paramInt);
  }
}

/* Location:
 * Qualified Name:     org.joda.time.field.StrictDateTimeField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */