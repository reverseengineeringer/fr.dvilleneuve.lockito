package fr.dvilleneuve.lockito.core;

public enum ItinerariesOrder
{
  LAST_CREATED_FIRST("creationDate", false),  LAST_UPDATED_FIRST("updatedDate", false),  MOST_PLAYED_FIRST("playCounter", false);
  
  private final boolean ascending;
  private final String columnName;
  
  private ItinerariesOrder(String paramString, boolean paramBoolean)
  {
    columnName = paramString;
    ascending = paramBoolean;
  }
  
  public String getColumnName()
  {
    return columnName;
  }
  
  public boolean isAscending()
  {
    return ascending;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.ItinerariesOrder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */