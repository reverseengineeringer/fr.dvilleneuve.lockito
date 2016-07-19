package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JodaMapper
  extends ObjectMapper
{
  private static final long serialVersionUID = 1L;
  
  public JodaMapper()
  {
    registerModule(new JodaModule());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.datatype.joda.JodaMapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */