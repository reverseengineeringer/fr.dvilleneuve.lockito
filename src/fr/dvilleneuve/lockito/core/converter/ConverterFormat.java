package fr.dvilleneuve.lockito.core.converter;

public enum ConverterFormat
{
  GPX("gpx"),  KML("kml");
  
  private final String extension;
  
  private ConverterFormat(String paramString)
  {
    extension = paramString;
  }
  
  public String getExtension()
  {
    return extension;
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.converter.ConverterFormat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */