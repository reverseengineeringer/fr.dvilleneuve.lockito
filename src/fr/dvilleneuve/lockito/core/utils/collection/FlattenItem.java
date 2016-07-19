package fr.dvilleneuve.lockito.core.utils.collection;

public class FlattenItem<E>
{
  private final E item;
  private final int subListIndex;
  
  public FlattenItem(E paramE, int paramInt)
  {
    item = paramE;
    subListIndex = paramInt;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (FlattenItem)paramObject;
      if (item == null) {
        break;
      }
    } while (item.equals(item));
    for (;;)
    {
      return false;
      if (item == null) {
        break;
      }
    }
  }
  
  public E getItem()
  {
    return (E)item;
  }
  
  public int getSubListIndex()
  {
    return subListIndex;
  }
  
  public int hashCode()
  {
    if (item != null) {
      return item.hashCode();
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.collection.FlattenItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */