package org.springframework.http;

import java.util.Comparator;

final class ContentCodingType$1
  implements Comparator<ContentCodingType>
{
  public int compare(ContentCodingType paramContentCodingType1, ContentCodingType paramContentCodingType2)
  {
    double d = paramContentCodingType1.getQualityValue();
    int i = Double.compare(paramContentCodingType2.getQualityValue(), d);
    if (i != 0) {
      return i;
    }
    if ((paramContentCodingType1.isWildcardType()) && (!paramContentCodingType2.isWildcardType())) {
      return 1;
    }
    if ((paramContentCodingType2.isWildcardType()) && (!paramContentCodingType1.isWildcardType())) {
      return -1;
    }
    if (!paramContentCodingType1.getType().equals(paramContentCodingType2.getType())) {
      return 0;
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.ContentCodingType.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */