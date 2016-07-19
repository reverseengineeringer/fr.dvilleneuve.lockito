package fr.dvilleneuve.lockito.core.utils;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionUtils
{
  @Nullable
  public static <T> List<T> clone(@Nullable List<T> paramList)
  {
    if (paramList == null) {}
    do
    {
      return null;
      if ((paramList instanceof ArrayList)) {
        return (List)((ArrayList)paramList).clone();
      }
    } while (!(paramList instanceof LinkedList));
    return (List)((LinkedList)paramList).clone();
  }
  
  @Nullable
  public static <T> T elementAt(@Nullable List<T> paramList, int paramInt)
  {
    if (isEmpty(paramList)) {}
    while ((paramInt < 0) || (paramInt >= paramList.size())) {
      return null;
    }
    return (T)paramList.get(paramInt);
  }
  
  public static boolean isEmpty(@Nullable List<?> paramList)
  {
    return (paramList == null) || (paramList.isEmpty());
  }
  
  @Nullable
  public static <T> T lastElement(@Nullable List<T> paramList)
  {
    if (isEmpty(paramList)) {
      return null;
    }
    return (T)paramList.get(paramList.size() - 1);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.CollectionUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */