package org.springframework.http;

import java.util.Comparator;
import java.util.Map;

final class MediaType$1
  implements Comparator<MediaType>
{
  public int compare(MediaType paramMediaType1, MediaType paramMediaType2)
  {
    int j = -1;
    int i;
    if ((paramMediaType1.isWildcardType()) && (!paramMediaType2.isWildcardType())) {
      i = 1;
    }
    int k;
    int m;
    do
    {
      do
      {
        do
        {
          return i;
          if (!paramMediaType2.isWildcardType()) {
            break;
          }
          i = j;
        } while (!paramMediaType1.isWildcardType());
        if (!paramMediaType1.getType().equals(paramMediaType2.getType())) {
          return 0;
        }
        if ((paramMediaType1.isWildcardSubtype()) && (!paramMediaType2.isWildcardSubtype())) {
          return 1;
        }
        if (!paramMediaType2.isWildcardSubtype()) {
          break;
        }
        i = j;
      } while (!paramMediaType1.isWildcardSubtype());
      if (!paramMediaType1.getSubtype().equals(paramMediaType2.getSubtype())) {
        return 0;
      }
      double d = paramMediaType1.getQualityValue();
      i = Double.compare(paramMediaType2.getQualityValue(), d);
      if (i != 0) {
        return i;
      }
      k = MediaType.access$000(paramMediaType1).size();
      m = MediaType.access$000(paramMediaType2).size();
      i = j;
    } while (m < k);
    if (m == k) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.MediaType.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */